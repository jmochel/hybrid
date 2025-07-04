/*
    .Author: Jim JM

    .Date: 12/22/94 

    .Contains   StreamRgn

	.Copyright

		This code is in the public domain

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $

*/

// JACL Library Headers

#include "Util.hpp"

// Local Headers

#include "StreamRegion.hpp"

// Constructors

StreamRegion::StreamRegion(void)
{
    _BfrAvail = 0;
    _BfrExtent = 0;
    _BfrNextPos = 0;
    _RefCnt = 0;
    _Offset = 0;
}

StreamRegion::StreamRegion(const StreamRegion& Region)
{
    _BfrAvail = Region._BfrAvail;
    _BfrExtent = Region._BfrExtent;
    _BfrNextPos = Region._BfrNextPos;
    _RefCnt = Region._RefCnt;
    _Offset = Region._Offset;
}


StreamRegion::StreamRegion(size_t BfrLen, AbsStreamOffset Offset)
{
    // Initialize the other values

    _BfrAvail = 0;
    _BfrExtent = 0;
    _BfrNextPos = 0;
    _RefCnt = 0;
    _Offset = Offset;
}

// Destructor

StreamRegion::~StreamRegion(void)
{
}

// Accessors

size_t StreamRegion::GetBfrAvail(void) const
{
    return((const size_t) _BfrAvail);
}

size_t StreamRegion::GetBfrUsed(void) const
{
    return((const size_t) (_BfrExtent - GetBfr() + 1) );
}

Byte* StreamRegion::GetBfrExtent(void) const
{
    return(_BfrExtent);
}

Byte* StreamRegion::GetBfrNextPos(void) const
{
    return(_BfrNextPos);
}

size_t StreamRegion::GetRefCnt(void) const
{
    return((const size_t) _RefCnt);
}

AbsStreamOffset StreamRegion::GetOffset(void) const
{
    return((const AbsStreamOffset) _Offset);
}

Byte*  StreamRegion::GetBfrEnd(void) const
{
    return(GetBfr() + GetBfrLen());
}

AbsStreamOffset  StreamRegion::GetBfrEndOffset(void) const
{
    return(_Offset + GetBfrLen());
}

AbsStreamOffset  StreamRegion::GetBfrNextPosOffset(void) const
{
        return(_Offset + _BfrNextPos - GetBfr());
}

size_t StreamRegion::GetBytesForwardFromBfr(Byte* Bfr) const
{
        return( GetBfrLen() - ( Bfr - GetBfr()));
}


// Mutators

void StreamRegion::SetOffset(AbsStreamOffset Offset)
{
    _Offset = Offset;
}

void StreamRegion::SetBfrAvail(size_t BfrAvail)
{
    _BfrAvail = BfrAvail;
}

void StreamRegion::SetBfrNextPos(Byte* NextPos)
{
    _BfrNextPos = NextPos;
}

void StreamRegion::SetBfrExtent(Byte* Extent)
{
    _BfrExtent = Extent;
}

// Assignment

StreamRegion& StreamRegion::operator =(const StreamRegion& Region)
{
    if ( this == &Region )
    {
        return(*this);
    }

    _BfrAvail = Region._BfrAvail;
    _BfrExtent = Region._BfrExtent;
    _BfrNextPos = Region._BfrNextPos;
    _RefCnt = Region._RefCnt;
    _Offset = Region._Offset;

    return(*this);
}

// Comparison

/*
   .MethodDesc Compares two

   .MethodNotes

         Ordering is based on Offset and Bfr
*/


int StreamRegion::Compare(const StreamRegion& aStreamRegion) const
{

    if ( _Offset == aStreamRegion._Offset )
    {
        return(0);
    }
    else if (_Offset > aStreamRegion._Offset)
    {
        return(1);
    }

    return(-1);
}

// Overloaded logic operators

bool StreamRegion::operator ==(const StreamRegion& aStreamRegion) const
{
    if ( Compare(aStreamRegion) == 0 )
    {
        return(true);
    }

    return(false);
}

bool StreamRegion::operator !=(const StreamRegion& aStreamRegion) const
{
    if ( Compare(aStreamRegion) == 0 )
    {
        return(false);
    }

    return(true);
}

bool StreamRegion::operator >(const StreamRegion& StreamRegion) const
{
    return((Compare(StreamRegion) == 1) ? true : false );
}

bool StreamRegion::operator <(const StreamRegion& StreamRegion) const
{
    return((Compare(StreamRegion) == -1) ? true : false );
}

bool StreamRegion::operator >=(const StreamRegion& StreamRegion) const
{
    return((Compare(StreamRegion) >= 0) ? true : false );
}

bool StreamRegion::operator <=(const StreamRegion& StreamRegion) const
{
    return((Compare(StreamRegion) <= 0) ? true : false );
}


// Other methods

/*
   .MethodDesc

         Updates the region for a length of Len

   .MethodReturn

        Returns the address for the requested block of memory.  

   .MethodNotes

                This also increments the reference count of the region.

*/

Byte* StreamRegion::Update(size_t Len)
{
    Byte*   Bfr;

//    Precondition(Len != 0);
//    Precondition(_BfrAvail != 0); // Something is is seriously wrong if this happens (helpful comment....)
    
    Bfr = _BfrNextPos;

    // Update the position and available memory

    _BfrAvail -= Len;
    _BfrNextPos += Len;

    // Move the extent if necessary 

    if ( _BfrNextPos >  _BfrExtent ) 
    {
        _BfrExtent = _BfrNextPos - 1;
    }

    // Up the references to the region

    _RefCnt++;

    return(Bfr);
}



/*
   .MethodDesc

         Updates the region for a length of Len without referncing it

   .MethodReturn

        Returns the address for the requested block of memory.  

   .MethodNotes

                This does not increment the reference count of the region.

*/

void StreamRegion::ResetTo(size_t Len)
{
    Precondition(_BfrAvail != 0);
    Precondition(Len != 0);

    // Update the position and available memory

    _BfrAvail -= Len;
    _BfrNextPos += Len;

    // Move the extent if necessary 

    if ( _BfrNextPos >  _BfrExtent ) 
    {
        _BfrExtent = _BfrNextPos - 1;
    }

}

/*
   .MethodDesc

         Updates the region for a given position in the bfr

   .MethodReturn

        Returns the address for the requested block of memory.  

   .MethodNotes

                This does not increment the reference count of the region.

*/

void StreamRegion::ResetTo(Byte* Bfr)
{
    // parameter check

    Precondition(Bfr != 0);

    // Update the position and available memory

    _BfrNextPos = Bfr;
    _BfrAvail = GetBfrLen() - (_BfrNextPos - GetBfr());

    // Move the extent if necessary 

    if ( _BfrNextPos >  _BfrExtent ) 
    {
        _BfrExtent = _BfrNextPos - 1;
    }
}





/*
   .MethodDesc

         Updates the region for a length of Len without referncing it

   .MethodReturn

        Returns the address for the requested block of memory.  

   .MethodNotes

                This does not increment the reference count of the region.

*/

void StreamRegion::ResetToOffset(AbsStreamOffset AbsOffset)
{
    size_t  Len = AbsOffset;

    // parameter check

    Precondition(AbsOffset >= _Offset);
    Precondition(AbsOffset <= _Offset + GetBfrLen());

    // Update the position and available memory

    _BfrNextPos = GetBfr() + _Offset - Len;
    _BfrAvail = GetBfrLen() - _Offset - Len;

    // Move the extent if necessary 

    if ( _BfrNextPos >  _BfrExtent ) 
    {
        _BfrExtent = _BfrNextPos - 1;
    }
}

void StreamRegion::Dereference(void)
{
    Precondition(_RefCnt != 0);

    _RefCnt--;
}

// IO

ostream& operator << (ostream& os, const StreamRegion& Region)
{
    os << "StreamRegion ==========================\n";

    os << "BfrAvail " << Region.GetBfrAvail() << endl ;
    os << "BfrUsed  " << Region.GetBfrUsed()<< endl ;
    os << "BfrExtent  " << Region.GetBfrExtent()<< endl ;
    os << "BfrNextPos " << Region.GetBfrNextPos() << endl ;
    os << "RefCnt " << Region.GetRefCnt()<< endl ;
    os << "Offset " << Region.GetOffset()<< endl ;

    return(os);
}

RefCntdStreamRegion::RefCntdStreamRegion(void) : StreamRegion()
{
    _Rep = 0;
}

RefCntdStreamRegion::RefCntdStreamRegion(const RefCntdStreamRegion& Region) : StreamRegion(Region)
{
    // Transfer the region rep.

    _Rep = Region._Rep;
    _Rep->_Cnt++;
}

RefCntdStreamRegion::RefCntdStreamRegion(size_t BfrLen, AbsStreamOffset Offset) : StreamRegion(BfrLen, Offset)
{
    // Allocate the buffer

    _Rep = new StreamRegionRep(BfrLen);

    // Initialize the other values

    _BfrAvail= _Rep->_Len;
    _BfrNextPos= _Rep->_Bfr;
}

// Destructor

RefCntdStreamRegion::~RefCntdStreamRegion(void)
{
    // Delete the rep if necessary.

    if (--(_Rep->_Cnt) == 0)
    {
        delete(_Rep);
    }
}


// Accessors

Byte* RefCntdStreamRegion::GetBfr(void) const
{
    return(_Rep->_Bfr);
}

size_t RefCntdStreamRegion::GetBfrLen(void) const
{
    return(_Rep->_Len);
}

// Assignment

RefCntdStreamRegion& RefCntdStreamRegion::operator =(const RefCntdStreamRegion& Region) 
{
    // Test if this is the same

    if ( _Rep == Region._Rep )
    {
        return(*this);
    }

    // Copy the base class

    StreamRegion::operator = (Region);

    // If there is a current rep, clean it up.

    if (_Rep != 0 )
    {
        if ( --(_Rep->_Cnt) == 0 )
        {
            delete(_Rep);
        }
    }

    // Update the region rep

    _Rep = Region._Rep;
    _Rep->_Cnt++;

    return(*this);
}

// Other methods

/*
   .MethodDesc

         Updates the region for a length of Len

   .MethodReturn

        Returns the address for the requested block of memory.  
*/

void RefCntdStreamRegion::Reset(void)
{
    _BfrAvail = _Rep->_Len;
    _BfrNextPos = _Rep->_Bfr;
    _BfrExtent = _BfrNextPos;
    _RefCnt = 0;
}

/*
   .MethodDesc

         Determines if the region contains Bfr
*/

bool RefCntdStreamRegion::Contains(Byte* Bfr) const
{
    if ((Bfr >= _Rep->_Bfr) && (Bfr <= (_Rep->_Bfr + _Rep->_Len)))
    {
        return(true);
    }

    return(false);
}

bool RefCntdStreamRegion::Contains(const Byte* Bfr) const
{
    if ((Bfr >= _Rep->_Bfr) && (Bfr <= (_Rep->_Bfr + _Rep->_Len)))
    {
        return(true);
    }

    return(false);
}


MMFStreamRegion::MMFStreamRegion(void) : StreamRegion()
{
    _Bfr = 0;
    _BfrLen = 0;
}

MMFStreamRegion::MMFStreamRegion(const MMFStreamRegion& Region) : StreamRegion(Region)
{
    _Bfr = Region._Bfr;
    _BfrLen = Region._BfrLen;
}

MMFStreamRegion::MMFStreamRegion(size_t BfrLen, Byte* Bfr, StreamOffset Offset)
{
    _Bfr = Bfr;
    _BfrLen = BfrLen;
    _Offset = Offset;
    _BfrNextPos = Bfr;
    _BfrExtent = Bfr;
    _BfrAvail = BfrLen;
}

// Destructor

MMFStreamRegion::~MMFStreamRegion(void)
{
}

// Mutators

void MMFStreamRegion::SetBfrLen(size_t Len)
{
    _BfrLen = Len;
}

void MMFStreamRegion::SetBfrAvail(size_t Avail)
{
    _BfrAvail = Avail;
}

// Assignment

MMFStreamRegion& MMFStreamRegion::operator =(const MMFStreamRegion& Region) 
{
    if ( this == &Region ) 
    {
        return(*this);
    }

    StreamRegion::operator = (Region);

    _Bfr = Region._Bfr;
    _BfrLen = Region._BfrLen;

    return(*this);
}

// Other methods

/*
   .MethodDesc

         Updates the region for a length of Len

   .MethodReturn

        Returns the address for the requested block of memory.  
*/

void MMFStreamRegion::Reset(void)
{
    _BfrLen = 0;
    _Bfr = 0;
    _BfrExtent = 0;
    _RefCnt = 0;
}

/*
   .MethodDesc

         Determines if the region contains Bfr
*/

bool MMFStreamRegion::Contains(Byte* Bfr) const
{
    if ((Bfr >= _Bfr) && (Bfr <= (_Bfr + _BfrLen)))
    {
        return(true);
    }

    return(false);
}

bool MMFStreamRegion::Contains(const Byte* Bfr) const
{
    if ((Bfr >= _Bfr) && (Bfr <= (_Bfr + _BfrLen)))
    {
        return(true);
    }

    return(false);
}


#ifdef TEST_STRMRGN


#include <stdlib.h>
#include "strmrgn.hpp"
#include "jacl/reporter.hpp"

/*
    .MethodDesc

          Unit test main entry point

    .MethodNotes

        Performs basic tests

*/

int main(int argc, char* argv[])
{
    StreamRgn   Rgn1;
    StreamRgn   Rgn2(1024);
    StreamRgn   Rgn3(2048);

    // Test the assignment

    Rgn1 = Rgn2;
    Rgn2 = Rgn3;

    // Test the stream region's methods

    // Updates region 2's allocated buffer

    Rgn1.Update(512);
    Rgn1.Update(512);

    // Updates region's allocated buffer

    Rgn2.Update(512);
    Rgn2.Update(512);
    Rgn2.Update(512);
    Rgn2.Update(512);

    return(EXIT_SUCCESS);
}
#endif // TEST_STRMRGN






