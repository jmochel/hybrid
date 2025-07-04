h63740
s 00314/00000/00000
d D 1.1 99/11/17 12:48:30 jmochel 2 1
cC
cK21583
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:48:26 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/w32/SubstreamWriteMemMapWin32.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45323
cPC++/src/io/stream/w32/SubstreamWriteMemMapWin32.cpp
cR2f93d7a95cb6ba86
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
    .Contains Win32MMFWriteSubStream

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

#include "w32/SubStreamWriteMemMapWin32.hpp"

#define RoundUp(Val, Gran) ( ((Val) + (Gran) - 1)/(Gran) * (Gran))
#define RoundDown(Val, Gran) ( (Val)/(Gran) * (Gran) )

// Constructors

Win32MMFWriteSubStream::Win32MMFWriteSubStream(void)
{
    _DefBfrSize= 0;
    _FileHandle = 0;
    _FileMap = 0;
    _ViewOfMap = 0;
    _CurrRegion = 0;
}

// Destructor

Win32MMFWriteSubStream::~Win32MMFWriteSubStream(void)
{
}

// Other methods

/*
        .MethodDesc

                Opens the substream

        .MethodNotes

                Does a variety of things to set the size of the file to be read.

*/

bool Win32MMFWriteSubStream::Open(const StreamSpec& Spec, StreamInfo& Info)
{
    DWORD           ErrorCode;
    SYSTEM_INFO     SystemInfo;
    DWORD           AllocationGranularity;
    size_t          ExpectedFileSize;

    // Determine the size of the increment

    GetSystemInfo(&SystemInfo);
    AllocationGranularity = SystemInfo.dwAllocationGranularity;

    _DefBfrSize = RoundUp(Info.GetBfrSize(), AllocationGranularity);

    // Open the file

    if ( Info.GetMode() && Strm::Update )
    {
        _FileHandle = CreateFile( (LPCTSTR) Spec.GetName().c_str(), GENERIC_WRITE | GENERIC_READ , 0, NULL, OPEN_ALWAYS, FILE_ATTRIBUTE_ARCHIVE, NULL);
    }
    else 
    {
        _FileHandle = CreateFile( (LPCTSTR) Spec.GetName().c_str(), GENERIC_WRITE | GENERIC_READ , 0, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_ARCHIVE, NULL);
    }

    BreakIf(_FileHandle == INVALID_HANDLE_VALUE);

    // Create the file mapping

    if ( Info.GetExpectedFileSize() != 0 )
    {
        ExpectedFileSize = RoundUp(Info.GetExpectedFileSize(), AllocationGranularity);
    }
    else 
    {
        ExpectedFileSize = RoundUp(64 * _DefBfrSize, AllocationGranularity);
    }

    _FileMap = CreateFileMapping( _FileHandle,  0, PAGE_READWRITE, 0, ExpectedFileSize, "null");

    if (_FileMap == NULL)
    {
        ErrorCode = GetLastError();
        cout << ErrorCode << "\n";
        exit(EXIT_FAILURE);
    }

    _ViewOfMap = MapViewOfFile(_FileMap, FILE_MAP_WRITE, 0, 0, _DefBfrSize);

    if (_ViewOfMap == NULL)
    {
        ErrorCode = GetLastError();
        cout << ErrorCode << "\n";
        exit(EXIT_FAILURE);
    }

    // Set up the current region with appropriate data

    _CurrRegion = new MMFStreamRegion(_DefBfrSize, (Byte*)  _ViewOfMap, 0);
    BreakIf(_CurrRegion == 0);

    return(true);
}

bool  Win32MMFWriteSubStream::Close(void)
{
    // Unmap the view

    UnmapViewOfFile(_ViewOfMap);

    // Close the Mapping

    CloseHandle(_FileMap);

    // Set the size of the file

    SetFilePointer(_FileHandle, _CurrRegion->GetBfrUsed(), 0, FILE_BEGIN);
    SetEndOfFile(_FileHandle);

    // Close the I/O Service

    CloseHandle(_FileHandle);

    return(true);
}

Byte*  Win32MMFWriteSubStream::Alloc(size_t Len,Strm::Whence Whence, StreamOffset Offset)
{
    bool    MappingOpWasSuccessful;
    ULong   NewMapSize;
    ULong   NewFileSize;
    LPVOID  NewViewOfMap;
    DWORD       ErrorCode;

    // Parameter checks

    BreakIf(Len == 0);

    // Determine how much memory is needed

    NewMapSize = _CurrRegion->GetBfrLen() + RoundUp(Len, _DefBfrSize);

    // Grow the current view of the file map
    // Unmap the current view

    MappingOpWasSuccessful = UnmapViewOfFile(_ViewOfMap);
    BreakIf(MappingOpWasSuccessful == false);

    // Create a new view starting at the same place in memory.

    NewViewOfMap = MapViewOfFileEx(_FileMap, FILE_MAP_WRITE, 0, 0, NewMapSize, _ViewOfMap);

    if (NewViewOfMap == NULL)
    {
        // If we fail we need to increase the size of the FileMap itself

        NewFileSize = 2 * NewMapSize;

        // Close the current map

        CloseHandle(_FileMap);

        // Create a new one.

        _FileMap = CreateFileMapping( _FileHandle,  0, PAGE_READWRITE, 0, NewFileSize, NULL);

        // If the mapping didn't work we are, indeed, hosed

        if (_FileMap == NULL)
        {
            ErrorCode = GetLastError();
            cout << ErrorCode << "\n";
            exit(EXIT_FAILURE);
        }

        // Otherwise, try mapping it again !

        NewViewOfMap = MapViewOfFileEx(_FileMap, FILE_MAP_WRITE, 0, 0, NewMapSize, _ViewOfMap);
    }

    BreakIf(NewViewOfMap != _ViewOfMap);
    _ViewOfMap = NewViewOfMap;

    // Update the current region for the new sizes.

    ((MMFStreamRegion*) _CurrRegion)->SetBfrLen(NewMapSize);
    ((MMFStreamRegion*) _CurrRegion)->SetBfrAvail(_CurrRegion->GetBfr() + NewMapSize - _CurrRegion->GetBfrNextPos());

    return(_CurrRegion->Update(Len));
}

Byte*  Win32MMFWriteSubStream::Alloc(size_t NewLen, Byte* Bfr)
{
    BreakIf(true == true);
    return(0);
}

Byte*  Win32MMFWriteSubStream::Alloc(size_t Len, Byte Delim)
{
    BreakIf(true == true);
    return(0);
}

void Win32MMFWriteSubStream::Free(Byte* Bfr)
{
    _CurrRegion->Dereference();
}

StreamOffset Win32MMFWriteSubStream::Tell(void)
{
    BreakIf(true == true);
    return(0);
}

bool Win32MMFWriteSubStream::Seek(Strm::Whence Whence, StreamOffset Offset)
{
    AbsStreamOffset AbsOffset;
    Byte*                   BfrLocale;

    // First create the absolute offset from the Relative Offset.

    switch(Whence)
    {
        case Strm::Curr:

            AbsOffset = _CurrRegion->GetOffset() + Offset;
            break;

        case Strm::End:

            AbsOffset = _CurrRegion->GetOffset() + _CurrRegion->GetBfrLen() + Offset;

            break;

        case Strm::Begin:

            AbsOffset = (AbsStreamOffset) Offset;
            break;

        default:

            BreakIf(true == true);
            break;
    }

    /*
            Now set the _CurrRegion so that it is ready for new writes.
    */

    // Translate the absolute offset into an appropriate buffer pointer

    BfrLocale = _CurrRegion->GetBfr() + AbsOffset;

    // If the pointer is within the buffer then simply go to that 
    // position.

    if ( (BfrLocale >= _CurrRegion->GetBfr()) && ( BfrLocale <= (_CurrRegion->GetBfr() + _CurrRegion->GetBfrLen()) ))
    {
        // Buffer space available space becomes reset to allow use to continue

        _CurrRegion->SetBfrAvail(_CurrRegion->GetBfrLen() - (size_t)(BfrLocale - _CurrRegion->GetBfr()));

        // Set the buffer used value to the furthest extent 

        if ( BfrLocale > _CurrRegion->GetBfrExtent())
        {
            _CurrRegion->SetBfrExtent(BfrLocale);
        }

        _CurrRegion->SetBfrNextPos(BfrLocale);
    }
    else if ( BfrLocale <= (_CurrRegion->GetBfr() + _CurrRegion->GetBfrLen()))
    {
        BreakIf(true == true);
    }


    return(true);
}

size_t Win32MMFWriteSubStream::BytesLeft(void)
{
    BreakIf(true == true);
    return(0);
}




E 2
I 1
E 1
