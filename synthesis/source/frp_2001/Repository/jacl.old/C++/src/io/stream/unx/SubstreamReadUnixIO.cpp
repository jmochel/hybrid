/*
    .Contains UnixIOReadSubStream

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

#include <algo.h>

#include <fcntl.h>
#include <io.h>
#include <sys/stat.h>

// Local Library Headers

#include "Reporter.hpp"
#include "Util.hpp"

// Local Headers

#include "SubStreamReadUnixIO.hpp"


// Local Function Objects

struct ContainsBfr
{
    Byte* _Bfr;

    ContainsBfr(Byte* Bfr) : _Bfr(Bfr)
    {
    }

    bool operator()(const StreamRegion& Region) const
    {
        return(Region.Contains(_Bfr));
    }
};


struct IsDiscardable
{
    bool operator()(const StreamRegion& Region) const
    {
        if ( Region.GetRefCnt() == 0)
        {
            return(true);
        }

        return(false);
    }
};


struct ResetStreamRegion
{
    ResetStreamRegion()
    {
    }

    void operator() (StreamRegion& Region)
    {
        Region.Reset();
    }

};


struct IsLargeEnough
{
    size_t _BfrSize;

    IsLargeEnough(size_t BfrSize) : _BfrSize(BfrSize)
    {
    }

    bool operator()(StreamRegion& Region)
    {
        if ( Region.GetBfrAvail() <= _BfrSize)
        {
            return(true);
        }

        return(false);
    }
};


UnixIOReadSubStream::UnixIOReadSubStream(void)
{
    _CurrRegion= 0;
    _DefBfrSize= 0;
    _FileHandle = 0;
}

// Destructor

UnixIOReadSubStream::~UnixIOReadSubStream(void)
{
}

// Other methods

bool UnixIOReadSubStream::Open(const StreamSpec& Spec, StreamInfo& Info)
{
    // Validate Parameters

    BreakIf(Info.GetMode() != Strm::Read);

    // Open the file

    _FileHandle = open(Spec.data(), O_RDONLY, S_IWRITE);

    if (_FileHandle == -1)
    {
        perror(__FILE__);
        exit(EXIT_FAILURE);
    }

    // Initialize the stream region management

    _DefBfrSize = Info.GetBfrSize();

    return(true);
}

bool  UnixIOReadSubStream::Close(void)
{
    //  Clean up the whole region list

    _Regions.erase(_Regions.begin(), _Regions.end());
    _FreeRegions.erase(_FreeRegions.begin(), _FreeRegions.end());

    // Close the I/O Service

    close(_FileHandle);

    return(true);
}

Byte*  UnixIOReadSubStream::Alloc(size_t Len)
{
    StreamOffset Offset;

    // Set the offset for the new region

    if ( _CurrRegion == 0 )
    {
        Offset = 0;
    }
    else 
    {
        Offset = _CurrRegion->GetOffset()+_CurrRegion->GetBfrLen();
    }

    return(Alloc(Len, Offset));
}

Byte*  UnixIOReadSubStream::Alloc(size_t Len, StreamOffset Offset)
{
    size_t BytesRead;
    size_t BytesToRead;
    bool BfrAllocSucceeded;

    // Create the region in memory

    BfrAllocSucceeded = BfrAlloc(Len, Offset);
    BreakIf(BfrAllocSucceeded == false);

    // Read in the file

    BytesToRead = _CurrRegion->GetBfrLen() - _CurrRegion->GetBfrUsed(); 

    BytesRead = read(_FileHandle,_CurrRegion->GetBfrNextPos(), BytesToRead);
    ReturnFalseIf( BytesRead != BytesToRead);

    return(Update(Len));
}

Byte*  UnixIOReadSubStream::Alloc(size_t NewLen, Byte* Bfr)
{
    BreakIf(true == true);
    return(0);
}

Byte*  UnixIOReadSubStream::Alloc(size_t Len, Byte Delim)
{
    BreakIf(true == true);
    return(0);
}


void UnixIOReadSubStream::Free(Byte* Bfr)
{
    // If this is the first and last region in the list we are not ready to free it.

    if ( _Regions.size() == 1 ) 
    {
        Dereference(Bfr);
        return;
    }

    // Clean up the memory

    BfrFree(Bfr);
}

StreamOffset UnixIOReadSubStream::Tell(void)
{
    BreakIf(true == true);
    return(0);
}

bool UnixIOReadSubStream::Seek(Strm::Whence Whence, StreamOffset Offset)
{
    BreakIf(true == true);
    return(false);
}

size_t UnixIOReadSubStream::BytesLeft(void)
{
    BreakIf(true == true);
    return(0);
}

/*
    Stream Region List Management

*/

bool  UnixIOReadSubStream::BfrAlloc(size_t Len, StreamOffset Offset)
{
    list<StreamRegion>::iterator    FoundRegion;

    // Check and see iif there is a region available for reuse

    FoundRegion = find_if(_FreeRegions.begin(), _FreeRegions.end(), IsLargeEnough(Len));

    if ( FoundRegion == _FreeRegions.end() )
    {
        _Regions.push_back(StreamRegion(Max(Len,_DefBfrSize), Offset));
    }
    else 
    {
        (*FoundRegion).Reset();
        _Regions.push_back(*FoundRegion);
        _FreeRegions.erase(FoundRegion);
    }

    _CurrRegion = &(_Regions.back());



    //  _Regions.push_back(StreamRegion(Max(Len,_DefBfrSize), Offset));
    //  _CurrRegion = &(_Regions.back());

    return(true);
}

/*
    .MethodDesc

        Manages the removal of the StreamRegion containing Bfr from the region list

    .MethodNotes

        Finds and removes only the given buffer.
*/

void    UnixIOReadSubStream::BfrFree(Byte* Bfr)
{
    list<StreamRegion>::iterator    RegionWithBfr;
    list<StreamRegion>::iterator    LastRemovedRegion;
    list<StreamRegion>::iterator    First;
    list<StreamRegion>::iterator    TempFirst;

    // Find the region containing the buffer

    First = _Regions.begin();
    RegionWithBfr = find_if(First, _Regions.end(), ContainsBfr(Bfr));
    BreakIf(RegionWithBfr == _Regions.end());

    // Copy the discardable regions over to the FreeRegion list.

    TempFirst = First;

    while (TempFirst != RegionWithBfr ) 
    {
        if ( (*TempFirst).GetRefCnt() == 0 )
        {
            _FreeRegions.push_back(*TempFirst);
            TempFirst++;
        }
        else 
        {
            break;
        }
    }

    // Erase the freed items from the region list.

    _Regions.erase(_Regions.begin(), TempFirst);

    // Set the Current Region

    _CurrRegion = &(_Regions.back());
}

void    UnixIOReadSubStream::Dereference(Byte* Bfr)
{
    // If the current region contains the bfr, deference it.

    if ( _CurrRegion->Contains(Bfr) == true)
    {
        _CurrRegion->Dereference();
    }
    else 
    {
        list<StreamRegion>::iterator Iter;
        Iter =  find_if(_Regions.begin(), _Regions.end(), ContainsBfr(Bfr));

        (*Iter).Dereference();
    }
}


#ifdef TEST_SSRUIO

#include <stdlib.h>
#include "jacl/ssruio.hpp"

int main(int argc, char* argv[])
{
    return(EXIT_SUCCESS);
}
#endif // TEST_SSRUIO

