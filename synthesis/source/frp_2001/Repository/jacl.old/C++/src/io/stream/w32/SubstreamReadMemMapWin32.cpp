/*
    .Contains Win32MMFReadSubStream

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

#include <cstdio>
#include <cstdlib>

#include <iostream>

#include <algorithm>

// Local Library Headers

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

// Local Headers

#include "w32/SubStreamReadMemMapWin32.hpp"


// Constructors

Win32MMFReadSubStream::Win32MMFReadSubStream(void)
{
    _DefBfrSize= 0;
    _FileHandle = 0;
    _FileMap = 0;
    _ViewOfMap = 0;
    _CurrRegion = 0;
        _IsEOS = false;
}

// Destructor

Win32MMFReadSubStream::~Win32MMFReadSubStream(void)
{
}

// Other methods

bool Win32MMFReadSubStream::Open(const StreamSpec& spec, StreamInfo& info)
{
    // Validate Parameters

    Precondition(info.GetMode() == Strm::Read);

    // Open the file

    _FileHandle = CreateFile( (LPCTSTR) spec.GetName().data(), GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_ARCHIVE, NULL);

    #pragma message("JSJM:Failure to open a file should simply cause a return of false")

    if (_FileHandle == INVALID_HANDLE_VALUE)
    {
        throw (Strm::OpenError(std::string("$SubstreamReadMemMapWin321$ Unable to open the substream ")));
    }

    // Create the file mapping

    _FileMap = CreateFileMapping( _FileHandle,  0, PAGE_READONLY, 0, 0, "");

    if (_FileMap == NULL)
    {
        throw (Strm::OpenError(std::string("$SubstreamReadMemMapWin322$ Unable to create a mappiing for the substream ")));
    }


    _ViewOfMap = MapViewOfFile(_FileMap, FILE_MAP_READ, 0, 0, 0);

    if (_ViewOfMap == NULL)
    {
        throw (Strm::OpenError(std::string("$SubstreamReadMemMapWin322$ Unable to map the substream ")));
    }

    // Set up the current region with appropriate data

    DWORD FileSize = GetFileSize(_FileHandle, NULL);
    ReturnFalseIf(FileSize == 0 );

    _CurrRegion = new MMFStreamRegion(FileSize, (Byte*)  _ViewOfMap, 0);
    ReturnFalseIf(_CurrRegion == 0);

    return(true);
}

bool  Win32MMFReadSubStream::Close(void)
{
    // Unmap the view

    UnmapViewOfFile(_ViewOfMap);

    // Close the Mapping

    CloseHandle(_FileMap);

    // Close the I/O Service

    CloseHandle(_FileHandle);

    return(true);
}

Byte*  Win32MMFReadSubStream::Alloc(size_t Len, Strm::Whence Whence, StreamOffset Offset)
{
    return(0);
}

Byte*  Win32MMFReadSubStream::Alloc(size_t Len, Byte* Bfr)
{
    return(0);
}

/*
        .MethodDesc

                        Gives you the data delimited by delim 
*/

Byte* Win32MMFReadSubStream::VarAlloc(Byte Delim)
{
        Byte* CurrBfr;
        size_t MaxSearchable;
        size_t i;
        
        // Set up the variables

        _BytesFound = 0;
                _IsEOS = false;
        
                MaxSearchable = _CurrRegion->GetBfrAvail();

                // Set for EOS

                if ( MaxSearchable == 0 )
                {
                        _IsEOS = true;
                        return(0);
                }
                
        CurrBfr = _CurrRegion->GetBfrNextPos();

        // Loop through the buffer looking for the delimiter

        for (i = 0; i < MaxSearchable; i++)
        {
                if ( *(CurrBfr++) == Delim )
                {
                        // Set the length found and return.

                        _BytesFound = i + 1;
                        return(_CurrRegion->Update(i + 1));
                }                       
        }

        // Failure time

        return(0);
}

void Win32MMFReadSubStream::Free(Byte* Bfr)
{
    _CurrRegion->Dereference();
}

bool Win32MMFReadSubStream::Seek(Strm::Whence Whence, StreamOffset Offset)
{
    switch(Whence)
    {
        case Strm::Curr:                

            // Parameter check

            Precondition(Offset <= _CurrRegion->GetBfrAvail());
            ReturnFalseIf(Offset > _CurrRegion->GetBfrAvail());

            // Reset the Current regions pointers relative to the current position

            _CurrRegion->ResetTo(Offset);

            break;

        case Strm::Begin:

            // Parameter check

            Precondition(Offset >= 0);
            Precondition(Offset <= _CurrRegion->GetBfrLen());
            ReturnFalseIf(Offset < 0);
            ReturnFalseIf(Offset > _CurrRegion->GetBfrLen());

            // Reset the Current regions pointers relative to buffers beginning

            _CurrRegion->ResetToOffset(Offset);

            break;

        case Strm::End:

            // Parameter check

            Precondition(Offset <= 0);
            Precondition((-1 * Offset) <= _CurrRegion->GetBfrLen());
            ReturnFalseIf(Offset > 0);
            ReturnFalseIf((-1 * Offset) > _CurrRegion->GetBfrLen());

            // Reset the Current regions pointers relative to buffers beginning

            _CurrRegion->SetBfrNextPos( _CurrRegion->GetBfr() + _CurrRegion->GetBfrLen() + Offset);
            _CurrRegion->SetBfrAvail(-1 * Offset);

            break;

        default:

            Precondition(true == true);
            return(false);
    }

    return(true);
}

AbsStreamOffset Win32MMFReadSubStream::Tell(void)
{
        return(_CurrRegion->GetBfrNextPosOffset());
}

bool  Win32MMFReadSubStream::IsEOS(void)
{
        return(_IsEOS);
}

