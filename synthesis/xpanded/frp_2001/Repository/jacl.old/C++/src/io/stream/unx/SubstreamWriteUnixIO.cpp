/*
    .Contains UnixIOWriteSubStream

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

#include <algo.h>

#include <fcntl.h>
#include <io.h>
#include <sys/stat.h>

// Local Library Headers

#include "Reporter.hpp"
#include "Util.hpp"

// Local Headers

#include "SubStreamWriteUnixIO.hpp"

// Local Function Objects

struct PrintRegion
{
    bool operator()(const StreamRegion& Region) const
    {
        cout << Region << endl;
        return(true);
    }

};

struct RegionIsNotWriteable
{
    bool operator()(const StreamRegion& Region) const
    {
        if ( Region.GetRefCnt() != 0)
        {
            return(true);
        }

        return(false);
    }
};

struct IsLargeEnough
{
    IsLargeEnough(size_t Len)
    {
        _Len = Len;
    }

    bool operator()(const StreamRegion& Region) const
    {
        if ( Region.GetBfrLen()  <= _Len)       
        {
            return(true);
        }

        return(false);
    }

    size_t _Len;
};


struct WriteRegion
{
    int _FileHandle;

    WriteRegion(int FileHandle) : _FileHandle(FileHandle)
    {
    }

    bool operator()(StreamRegion& Region)
    {
        int BytesWritten;

        lseek(_FileHandle, Region.GetOffset(), SEEK_SET);           
        BytesWritten = write(_FileHandle, Region.GetBfr(), Region.GetBfrUsed());

        if ( BytesWritten < 1 )
        {
            return(false);
        }

        return(true);
    }
};

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

struct AlwaysDoThis
{
    bool operator()(const StreamRegion& Region) const
    {
        return(true);
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

UnixIOWriteSubStream::UnixIOWriteSubStream(void) : SubStream()
{
    _CurrRegion= 0;
    _DefBfrSize= 0;
    _FileHandle = 0;
}

// Destructor

UnixIOWriteSubStream::~UnixIOWriteSubStream(void)
{
}

// Other methods

bool UnixIOWriteSubStream::Open(const StreamSpec& Spec, StreamInfo& Info)
{
    // Validate Parameters

    BreakIf(Info.GetMode() != Strm::Write);

    // Open the file

    _FileHandle = open(Spec.data(), O_CREAT | O_WRONLY, S_IWRITE);

    if (_FileHandle == -1)
    {
        perror(__FILE__);
        exit(EXIT_FAILURE);
    }

    // Initialize the lists 

    _DefBfrSize = Info.GetBfrSize();

    return(true);
}

bool  UnixIOWriteSubStream::Close(void)
{

    // Write the regions  out
    // This currently ignores any existing allocations the use may still have.
    // It assumes that a close means "write all remaining stuff out"

    for_each(_Regions.begin(), _Regions.end(), WriteRegion(_FileHandle));

    // Clean up the memory

    _Regions.erase(_Regions.begin(), _Regions.end());
    _FreeRegions.erase(_FreeRegions.begin(), _FreeRegions.end());

    // Close the rest of the service

    close(_FileHandle);

    return(true);
}


Byte*  UnixIOWriteSubStream::Alloc(size_t Len)
{
    StreamOffset Offset;
    bool BfrAllocSucceeded;

    // Set the offset for the new region

    if ( _CurrRegion == 0 )
    {
        Offset = 0;
    }
    else 
    {
        Offset = _CurrRegion->GetOffset()+_CurrRegion->GetBfrLen();
    }

    BfrAllocSucceeded = BfrAlloc(Len, Offset);
    BreakIf(BfrAllocSucceeded == false);

    return(Update(Len));
}

Byte*  UnixIOWriteSubStream::Alloc(size_t Len, StreamOffset Offset)
{
    bool BfrAllocSucceeded;

    BfrAllocSucceeded = BfrAlloc(Len, Offset);
    BreakIf(BfrAllocSucceeded == false);

    return(Update(Len));
}

Byte* UnixIOWriteSubStream::Alloc(size_t NewLen, Byte* Bfr)
{
    BreakIf(true == true);
    return(0);
}

Byte* UnixIOWriteSubStream::Alloc(size_t Len, Byte Delim)
{
    BreakIf(true == true);
    return(0);
}

void UnixIOWriteSubStream::Free(Byte* Bfr)
{
    // If this is the first and last region in the list we are not ready to write this out.
    // But we must dereference it !

    if ( _Regions.size() == 1 ) 
    {
        Dereference(Bfr);
        return;
    }

    // Find the the region that contains this buffer

    list<StreamRegion>::iterator FirstRegion;
    list<StreamRegion>::iterator LastRegion;
    list<StreamRegion>::iterator RegionWithBfr;
    list<StreamRegion>::iterator LastRemovedRegion;

    FirstRegion = _Regions.begin();

    // cout << "_Regions \n";
    // cout << "=============\n";
    // for_each(_Regions.begin(), _Regions.end(), PrintRegion());

    // cout << "_FreeRegions \n";
    // cout << "=============\n";
    // for_each(_FreeRegions.begin(), _FreeRegions.end(), PrintRegion());

    RegionWithBfr = find_if(_Regions.begin(), _Regions.end(), ContainsBfr(Bfr));
    (*RegionWithBfr).Dereference();

    // cout << "RegionWithBfr===\n" << (*RegionWithBfr);

    // Find the first non-writeable region that occurs before the newly dereferenced region

    LastRegion = find_if(_Regions.begin(), RegionWithBfr, RegionIsNotWriteable());

    // Write the regions  out

    for_each(_Regions.begin(), LastRegion, WriteRegion(_FileHandle));

    //  cout << "To Last Writeable \n";
    // for_each(_Regions.begin(), LastRegion, PrintRegion());

    // Clean up the memory

    BfrFree(Bfr);

    // cout << "AFTER MOVEMENT \n\n";
    //cout << "_Regions \n";
    //cout << "=============\n";
    //for_each(_Regions.begin(), _Regions.end(), PrintRegion());

    //cout << "_FreeRegions \n";
    //cout << "=============\n";
    //for_each(_FreeRegions.begin(), _FreeRegions.end(), PrintRegion());
}

StreamOffset UnixIOWriteSubStream::Tell(void)
{
    BreakIf(true == true);
    return(0);
}

bool UnixIOWriteSubStream::Seek(Strm::Whence Whence, StreamOffset Offset)
{
    BreakIf(true == true);
    return(false);
}


size_t UnixIOWriteSubStream::BytesLeft(void)
{
    BreakIf(true == true);
    return(0);
}

/*
    Stream Region List Management

*/

bool  UnixIOWriteSubStream::BfrAlloc(size_t Len, StreamOffset Offset)
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
        (*FoundRegion).SetOffset(Offset);
        _Regions.push_back(*FoundRegion);
        _FreeRegions.erase(FoundRegion);
    }

    _CurrRegion = &(_Regions.back());

    return(true);
}

/*
    .MethodDesc

        Manages the removal of the StreamRegion containing Bfr from the region list

    .MethodNotes

        Finds and removes only the given buffer.
*/

template <class ForwardIterator, class OutputIterator, class Predicate>
OutputIterator copy_erase_only( ForwardIterator start, ForwardIterator end, OutputIterator target,  Predicate pred )
{
    ForwardIterator loc = start;

    while( start != end )
    {
        if( pred( *start ) )
            *target++ = *start++;
        else
            *loc++ = *start++;
    }

    return target;
}


void    UnixIOWriteSubStream::BfrFree(Byte* Bfr)
{
    list<StreamRegion>::iterator    RegionWithBfr;
    list<StreamRegion>::iterator    LastRemovedRegion;
    list<StreamRegion>::iterator    First;
    list<StreamRegion>::iterator    TempFirst;

    // Find the region containing the buffer

    First = _Regions.begin();
    RegionWithBfr = find_if(First, _Regions.end(), ContainsBfr(Bfr));
    BreakIf(RegionWithBfr == _Regions.end());

    // Copy the discardable regions over to the FreeRegion list and erase them

    while ( First != RegionWithBfr ) 
    {
        if ( (*First).GetRefCnt() == 0 ) 
        {
            _FreeRegions.push_back(*First);
            _Regions.erase(First++);
        }
        else 
        {
            break;
        }
    }

    // Set the Current Region

    _CurrRegion = &(_Regions.back());
}


void    UnixIOWriteSubStream::Dereference(Byte* Bfr)
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

#ifdef TEST_SSWUIO

#    include <stdlib.h>

#    include "jacl/reporter.hpp"

#    include "jacl/sswuio.hpp"

Reporter   GlobalReporter(__FILE__);

int main(int argc, char* argv[])
{
    RegionList     List;
    SubStream*  StreamImp;
    bool        ListOpWorked;
    bool        StreamOpWorked;
    Byte*       Bfr;

    // Create the substream we are going to use.

    StreamImp = new UnixIOWriteSubStream();
    BreakIf(StreamImp == 0);

    //  Open the substream and init the region list

    ListOpWorked = List.Init();
    BreakIf(ListOpWorked == false);

    StreamOpWorked = StreamImp->Open(StreamSpec("d:\\test"),StreamInfo(Strm::Write, Strm::File, Strm::Random));
    BreakIf(StreamOpWorked == false);

    // Allocate a block of memory and do appropriate things

    ListOpWorked = List.Create(512,0L);
    BreakIf(ListOpWorked == false);

    StreamOpWorked = StreamImp->Fullfill(List, Strm::Alloc);
    BreakIf(StreamOpWorked == false);

    // Get a Bfr from that block.

    Bfr = List.Update(130);

    // Put something in that block.

    memset(Bfr, 'g', 130);

    // Free a block of memory.

    List.Dereference(Bfr);
    StreamOpWorked = StreamImp->Fullfill(List, Strm::Free);
    BreakIf(StreamOpWorked == false);

    // Get a Bfr from that block.

    Bfr = List.Update(100);

    // Put something in that block.

    memset(Bfr, 'h', 100);

    // Free a block of memory.

    List.Dereference(Bfr);
    StreamOpWorked = StreamImp->Fullfill(List, Strm::Free);
    BreakIf(StreamOpWorked == false);

    // Close and Deinit

    StreamOpWorked = StreamImp->Fullfill(List, Strm::Close);
    BreakIf(StreamOpWorked == false);

    ListOpWorked = List.Deinit();
    BreakIf(ListOpWorked == false);

    StreamOpWorked = StreamImp->Close();
    BreakIf(StreamOpWorked == false);

    return(EXIT_SUCCESS);
}
#endif // TEST_SSWUIO

