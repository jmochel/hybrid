h13860
s 00243/00000/00000
d D 1.1 99/11/17 12:48:22 jmochel 2 1
cC
cK38624
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:48:18 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/w32/SubstreamReadWin32.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45322
cPC++/src/io/stream/w32/SubstreamReadWin32.cpp
cR2f93d7ab5cb6ba86
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 2
/*
    .Contains Win32IOReadSubStream

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

#include <stdio.h>
#include <stdlib.h>

#include <algorithm>

// Local Library Headers

#include "Reporter.hpp"
#include "Util.hpp"

// Local Headers

#include "w32/SubStreamReadWin32.hpp"

// Local Function Objects

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

Win32IOReadSubStream::Win32IOReadSubStream(void)
{
    _CurrRegion= 0;
    _DefBfrSize= 0;
    _FileHandle = 0;
}

// Destructor

Win32IOReadSubStream::~Win32IOReadSubStream(void)
{
}

// Other methods

bool Win32IOReadSubStream::Open(const StreamSpec& Spec, StreamInfo& Info)
{
    // Validate Parameters

    // BreakIf(Info.GetMode() != Strm::Read);

    // Open the file

    _FileHandle = CreateFile( (LPCTSTR) Spec.GetName().c_str(), GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_ARCHIVE, NULL);

    if (_FileHandle == INVALID_HANDLE_VALUE)
    {
        perror(__FILE__);
        exit(EXIT_FAILURE);
    }

    // Initialize the stream region management

    _DefBfrSize = Info.GetBfrSize();

    return(true);
}

bool  Win32IOReadSubStream::Close(void)
{
    //  Clean up the whole region list

    _Regions.erase(_Regions.begin(), _Regions.end());
    _FreeRegions.erase(_FreeRegions.begin(), _FreeRegions.end());

    // Close the I/O Service

    CloseHandle(_FileHandle);

    return(true);
}

Byte*  Win32IOReadSubStream::Alloc(size_t Len)
{
    StreamOffset Offset;

    // Set the offset for the new region

    Offset = (_CurrRegion == 0 ) ? 0 : _CurrRegion->GetOffset()+_CurrRegion->GetBfrLen();

    return(Alloc(Len, Offset));
}

Byte*  Win32IOReadSubStream::Alloc(size_t Len, StreamOffset Offset)
{
    DWORD BytesRead;
    DWORD BytesToRead;
    BOOL  ReadWasSuccessful;

    // Get a new region 

    if ( _FreeRegions.empty() == false )
    {
        // Check if the first entry is large enough.

        if ( _FreeRegions.back().GetBfrLen() >= Len ) 
        {
            RefCntdStreamRegion TempRegion(_FreeRegions.back());

            TempRegion.Reset();
            TempRegion.SetOffset(Offset);
            _Regions.push_back(TempRegion);
            _FreeRegions.pop_back();
            _CurrRegion = &(_Regions.back());
        }
        else 
        {
            _Regions.push_back(RefCntdStreamRegion(Max(Len,_DefBfrSize), Offset));
            _CurrRegion = &(_Regions.back());
        }
    }
    else 
    {
        _Regions.push_back(RefCntdStreamRegion(Max(Len,_DefBfrSize), Offset));
        _CurrRegion = &(_Regions.back());
    }

    // Read in the file

    BytesToRead = _CurrRegion->GetBfrLen() - _CurrRegion->GetBfrUsed(); 
    ReadWasSuccessful = ReadFile(_FileHandle, (VOID*) _CurrRegion->GetBfrNextPos(), BytesToRead, &BytesRead, NULL);
    ReturnFalseIf( ReadWasSuccessful == FALSE);

    return(Update(Len));
}

Byte*  Win32IOReadSubStream::Alloc(size_t NewLen, Byte* Bfr)
{
    BreakIf(true == true);
    return(0);
}

Byte*  Win32IOReadSubStream::Alloc(size_t Len, Byte Delim)
{
    BreakIf(true == true);
    return(0);
}


void Win32IOReadSubStream::Free(Byte* Bfr)
{
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

    // Clean up the memory

    while ((_Regions.size() != 1) && ( _Regions.front().GetRefCnt() == 0)) 
    {
        if ( _FreeRegions.size() < 4 ) 
        {
            _FreeRegions.push_back(_Regions.front());
        }

        _Regions.pop_front();
    }

    // Set the Current Region

    _CurrRegion = &(_Regions.back());
}

StreamOffset Win32IOReadSubStream::Tell(void)
{
    BreakIf(true == true);
    return(0);
}

bool Win32IOReadSubStream::Seek(Strm::Whence Whence, StreamOffset Offset)
{
    BreakIf(true == true);
    return(false);
}

size_t Win32IOReadSubStream::BytesLeft(void)
{
    BreakIf(true == true);
    return(0);
}


void    Win32IOReadSubStream::Dereference(Byte* Bfr)
{
    list<RefCntdStreamRegion>::iterator Iter;
    Iter =  find_if(_Regions.begin(), _Regions.end(), ContainsBfr(Bfr));

    (*Iter).Dereference();
}


#ifdef TEST_SSRUIO

#include <stdlib.h>
#include "jacl/unx/ssruio.hpp"

int main(int argc, char* argv[])
{
    return(EXIT_SUCCESS);
}
#endif // TEST_SSRUIO



E 2
I 1
E 1
