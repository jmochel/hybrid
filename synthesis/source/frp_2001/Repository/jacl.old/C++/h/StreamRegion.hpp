#ifndef STRMRGN_HPP
#define STRMRGN_HPP

/*
    .Contains StreamRegion Class Definition

    .Author Jim Jackl-Mochel

    .Date 07.12.94

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// System Headers

#include <string>

// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

/*
    .ClassName StreamRgnRep

    .ClassDesc A region representation for reference counting

    .ClassNotes

*/

class StreamRegion;

class StreamRegionRep
{
    friend class StreamRegion;

    public:

        // Constructors

        StreamRegionRep(size_t Len)
        {
            // Allocate

            _Bfr = new Byte[Len];

            // Initialize

            _Len = Len;
            _Cnt = 1;
        }

        // Destructor

        ~StreamRegionRep(void)
        {
            delete[] _Bfr;
        }

    public:

        Byte*    _Bfr;    // Pointer to a block of memory
        size_t   _Len;    // Length of the block of memory
        size_t   _Cnt;    // Number of times the rep is referenced
};

/*
    .ClassName 
			
			StreamRegion

    .ClassDesc 
	
			An abstract base class for buffer management

*/


class StreamRegion
{
   public:

    // Constructors

    StreamRegion(void);
    StreamRegion(const StreamRegion& aStreamRegion);
    StreamRegion(size_t BfrLen, AbsStreamOffset Offset);

    // Destructor

    virtual ~StreamRegion(void);

    // Accessors

    virtual Byte*  GetBfr(void) const = 0;
    virtual size_t GetBfrLen(void) const = 0;
    
	size_t GetBfrAvail(void) const;
    size_t GetBfrUsed(void) const;
    Byte*  GetBfrNextPos(void) const;
    Byte*  GetBfrExtent(void) const;
	size_t GetRefCnt(void) const;
    AbsStreamOffset GetOffset(void) const;

	Byte*  GetBfrEnd(void) const;
	AbsStreamOffset  GetBfrEndOffset(void) const;
	AbsStreamOffset  GetBfrNextPosOffset(void) const;
	size_t GetBytesForwardFromBfr(Byte* Bfr) const;

    // Mutators

    void SetOffset(AbsStreamOffset);
	void SetBfrAvail(size_t BfrAvail);
	void SetBfrUsed(size_t BfrUsed);
	void SetBfrNextPos(Byte* NextPos);
	void SetBfrExtent(Byte* Extent);

    // Assignment

    StreamRegion& operator =(const StreamRegion& Region);

    // Comparison

    int Compare(const StreamRegion& StreamRegion) const;

    // Overloaded logic operators

    bool operator ==(const StreamRegion& StreamRegion) const;
    bool operator !=(const StreamRegion& StreamRegion) const;
    bool operator >(const StreamRegion& StreamRegion) const;
    bool operator <(const StreamRegion& StreamRegion) const;
    bool operator >=(const StreamRegion& StreamRegion) const;
    bool operator <=(const StreamRegion& StreamRegion) const;

    // Other methods

    Byte* Update(size_t Len);

    void ResetTo(size_t Len);
	void ResetTo(Byte* Bfr);
	void ResetToOffset(AbsStreamOffset AbsOffset);

    void Dereference(void);

    virtual bool Contains(Byte* Bfr) const = 0;
    virtual bool Contains(const Byte* Bfr) const = 0;
    virtual void Reset(void) = 0;

    // IO

    friend ostream& operator << (ostream& os, const StreamRegion& Region);

	protected:

      size_t   _BfrAvail;  // Number of bytes available in the Bfr
      Byte*    _BfrExtent;   // End Pos of used memory
      Byte*    _BfrNextPos; // Pointer to the next usable portion of the block of memory
      size_t   _RefCnt;    // Number of times the region is referenced
      AbsStreamOffset _Offset; // Offset into the stream that this Bfr starts at.
};

/*
    .ClassName RefCntdStreamRegion

    .ClassDesc A buffer management structure for streams

    .ClassNotes

      A Stream Region contains a Stream Region Rep and information that tracks
      the use of that buffer by the stream.
*/


class RefCntdStreamRegion : public StreamRegion
{
   public:

    // Constructors

    RefCntdStreamRegion(void);
    RefCntdStreamRegion(const RefCntdStreamRegion& aRefCntdStreamRegion);
    RefCntdStreamRegion(size_t BfrLen, AbsStreamOffset Offset);

    // Destructor

    ~RefCntdStreamRegion(void);

        // Accessors

    virtual Byte*  GetBfr(void) const;
    virtual size_t GetBfrLen(void) const;

        // Accessors

    RefCntdStreamRegion& operator =(const RefCntdStreamRegion& Region);
            
        // Other

    bool Contains(Byte* Bfr) const;
    bool Contains(const Byte* Bfr) const;
        void Reset(void);

   protected:

                StreamRegionRep* _Rep;   // A Reference to an existing buffer
};

/*
    .ClassName RefCntdStreamRegion

    .ClassDesc A buffer management structure for Memory Mapped File Streams

    .ClassNotes

*/

class MMFStreamRegion : public StreamRegion
{
   public:

    // Constructors

    MMFStreamRegion(void);
    MMFStreamRegion(const MMFStreamRegion& aMMFStreamRegion);
    MMFStreamRegion(size_t BfrLen, Byte* Bfr, StreamOffset Offset);

    // Destructor

    ~MMFStreamRegion(void);

        // Accessors

    virtual Byte*  GetBfr(void) const
        {
                return(_Bfr);
        }

    virtual  size_t GetBfrLen(void) const
        {
                return(_BfrLen);
        }

    MMFStreamRegion& operator =(const MMFStreamRegion& Region);

    // Mutators

    void SetBfrLen(size_t Len);
    void SetBfrAvail(size_t Avail);

    // Other

    bool Contains(Byte* Bfr) const;
    bool Contains(const Byte* Bfr) const;
        void Reset(void);

   protected:

                Byte*   _Bfr;           // Base buffer
                size_t  _BfrLen;        // Len of the base buffer.
};

#endif  // STRMRGN_HPP
