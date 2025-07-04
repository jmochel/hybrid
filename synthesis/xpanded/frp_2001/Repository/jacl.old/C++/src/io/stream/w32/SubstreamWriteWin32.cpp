/*
    .Contains Win32IOWriteSubStream

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

// Standard Library Headers

#include <stdlib.h>
#include <stdio.h>
#include <algorithm>

// Local Library Headers

#ifndef RPRTR_HPP
#include "Reporter.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

// Local Headers

#include "w32/SubStreamWriteWIn32.hpp"

// Local Functors

struct ContainsBfr
{
    Byte* _Bfr;

    ContainsBfr(Byte* Bfr) : _Bfr(Bfr)
    {
    }

    bool operator()(const RefCntdStreamRegion& Region) const
    {
        return(Region.Contains(_Bfr));
    }
};

// Constructors

Win32IOWriteSubStream::Win32IOWriteSubStream(void) : SubStream()
{
    _CurrRegion= 0;
    _DefBfrSize= 0;
    _FileHandle = 0;
}

// Destructor

Win32IOWriteSubStream::~Win32IOWriteSubStream(void)
{
}

// Other methods

bool Win32IOWriteSubStream::Open(const StreamSpec& spec, StreamInfo& info)
{
    // Validate Parameters

    Precondition(info.GetMode() == Strm::Write);

    // Open the file


    _FileHandle = CreateFile((LPCTSTR) spec.GetName().c_str(), GENERIC_WRITE, 0,0, CREATE_ALWAYS, FILE_ATTRIBUTE_ARCHIVE, NULL);

    if (_FileHandle == INVALID_HANDLE_VALUE)
    {
        perror(__FILE__);
        exit(EXIT_FAILURE);
    }

    // Initialize the lists 

    _DefBfrSize = info.GetBfrSize();

    return(true);
}

bool  Win32IOWriteSubStream::Close(void)
{

    // Write the regions  out
    // This currently ignores any existing allocations the use may still have.
    // It assumes that a close means "write all remaining stuff out"

    while ((_Regions.empty() == false)) 
    {
        WriteRegion(_Regions.front());
        _Regions.pop_front();
    }

    // Clean up the memory

    _Regions.erase(_Regions.begin(), _Regions.end());
    _FreeRegions.erase(_FreeRegions.begin(), _FreeRegions.end());

    // Close the rest of the service

    CloseHandle(_FileHandle);

    return(true);
}


Byte*  Win32IOWriteSubStream::Alloc(size_t len, Strm::Whence whence, StreamOffset offset)
{
    AbsStreamOffset absOffset;

    // Set the offset appropriately 

    if ( _CurrRegion != 0 )
    {               
        absOffset = _CurrRegion->GetBfrEndOffset();
    }
    else 
    {
        absOffset = 0;
    }

    // If there are items in the free list

    if ( _FreeRegions.empty() == false )
    {
        // Check if the first entry is large enough.

        if ( _FreeRegions.back().GetBfrLen() >= len ) 
        {
            RefCntdStreamRegion tempRegion(_FreeRegions.back());

            tempRegion.Reset();
            tempRegion.SetOffset(absOffset);

            _Regions.push_back(tempRegion);
            _FreeRegions.pop_back();
            _CurrRegion = &(_Regions.back());
        }
        else 
        {
            _Regions.push_back(RefCntdStreamRegion(Max(len,_DefBfrSize), absOffset));
            _CurrRegion = &(_Regions.back());
        }
    }
    else 
    {
        _Regions.push_back(RefCntdStreamRegion(Max(len,_DefBfrSize), absOffset));
        _CurrRegion = &(_Regions.back());
    }

    return(_CurrRegion->Update(len));
}

Byte*  Win32IOWriteSubStream::Alloc(size_t Len, Byte* Bfr)
{
        return(0);
}

void Win32IOWriteSubStream::Free(Byte* Bfr)
{

    // Dereference the Region with the Bfr

    // If this is the current region, don't bother freeing it, just dereference it

    if ( _CurrRegion->Contains(Bfr) ) 
    {
        _CurrRegion->Dereference();

        if (_Regions.size() == 1 )
            return;
    }
    else 
    {
        Dereference(Bfr);
    }

    // Write out any eligible regions

    while ((_Regions.size() != 1) && ( _Regions.front().GetRefCnt() == 0)) 
    {
        WriteRegion(_Regions.front());

        if ( _FreeRegions.size() < 4 ) 
        {
            _FreeRegions.push_back(_Regions.front());
        }

        _Regions.pop_front();
    }

    // Set the Current Region

    _CurrRegion = &(_Regions.back());
}


bool Win32IOWriteSubStream::Seek(Strm::Whence Whence, StreamOffset Offset)
{
    AbsStreamOffset AbsOffset;

    if ((Whence == Strm::Curr) && ( Offset == 0))
    {
        return(true);
    }

    /*
            Calculate the absolute offset.
    */

    switch(Whence)
    {
        case Strm::Curr:                

            // Calculate the absolute offset

            AbsOffset = _CurrRegion->GetOffset()  + Offset;
            break;

        case Strm::Begin:

            // Parameter check

            Precondition(Offset >= 0);
            ReturnFalseIf(Offset < 0);

            // Calculate the absolute offset

            AbsOffset = Offset;
            break;

        case Strm::End:

            // Calculate the absolute offset

            Precondition(true == true);
            break;

        default:

            Precondition(true == true);
            return(false);
    }

    /*
            Now set up an appropriate region.

            We could implement this so that it first checked for the correct
            region in _Regions, but that may be overkill so we will skip that for now.
    */

    // If there are items in the free list

    if ( _FreeRegions.empty() == false )
    {
        // Check if the first entry is large enough.

        if ( _FreeRegions.back().GetBfrLen() >= _DefBfrSize ) 
        {
            RefCntdStreamRegion TempRegion(_FreeRegions.back());

            TempRegion.Reset();
            TempRegion.SetOffset(AbsOffset);

            _Regions.push_back(TempRegion);
            _FreeRegions.pop_back();
            _CurrRegion = &(_Regions.back());
        }
        else 
        {
            _Regions.push_back(RefCntdStreamRegion(_DefBfrSize, AbsOffset));
            _CurrRegion = &(_Regions.back());
        }
    }
    else 
    {
        _Regions.push_back(RefCntdStreamRegion(_DefBfrSize, AbsOffset));
        _CurrRegion = &(_Regions.back());
    }

    return(true);
}

AbsStreamOffset Win32IOWriteSubStream::Tell(void)
{
        return(_CurrRegion->GetBfrNextPosOffset());
}


bool Win32IOWriteSubStream::WriteRegion(RefCntdStreamRegion& Region)
{
    BOOL  WriteWasSuccessful;  
    DWORD BytesWritten;

    // Seek to the appropriate locale in the file, if necessary.

    SetFilePointer(_FileHandle, Region.GetOffset(), NULL, FILE_BEGIN);           

    // Write the data out.

    WriteWasSuccessful = WriteFile(_FileHandle, (CONST VOID*) Region.GetBfr(), (DWORD) Region.GetBfrUsed(), &BytesWritten, 0);

    if ( WriteWasSuccessful == FALSE ) 
    {
        return(false);
    }

    return(true);
}

void    Win32IOWriteSubStream::Dereference(Byte* Bfr)
{
    // If the current region contains the bfr, deference it.

    if ( _CurrRegion->Contains(Bfr) == true)
    {
        _CurrRegion->Dereference();
    }
    else 
    {
        list<RefCntdStreamRegion>::iterator Iter;
        Iter =  find_if(_Regions.begin(), _Regions.end(), ContainsBfr(Bfr));

        (*Iter).Dereference();
    }
}

