h15574
s 00109/00000/00000
d D 1.1 99/11/17 08:10:14 jmochel 2 1
cC
cK44725
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:10:11 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Substream.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43653
cPC++/h/Substream.hpp
cRc3e7bbef5cb6bb60
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
#ifndef SUBSTREAM_HPP
#define SUBSTREAM_HPP

/*
    .Contains SubStream

    .Author Jim Jackl-Mochel

    .Date 10.01.95

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// Forward declarations

#include <string>

// Local Library Headers

#include "Types.hpp"

// Local headers

#include "StreamSpec.hpp"
#include "StreamInfo.hpp"
#include "Strm.hpp"
#include "StreamRegion.hpp"

/*
    .ClassDesc

        An encapsulation of the I/O portion of streams

    .ClassNotes
*/

class SubStream
{
    public:

        // Constructors

        SubStream(void);
        SubStream(const StreamSpec& Spec, const StreamInfo& Info);

        // Destructor

        ~SubStream(void);

        // Accessors

        const StreamSpec GetSpec(void) const;
        const StreamInfo GetInfo(void) const;
		
		size_t GetBytesFound(void) const;

		StreamRegion* GetCurrRegion(void) const
		{
			return(_CurrRegion);
		}

        // Mutators

        void SetSpec(const StreamSpec& Spec);
        void SetInfo(const StreamInfo& Info);

        // Open and Close

        virtual bool  Open(const StreamSpec& Spec, StreamInfo& Info) = 0;
        virtual bool  Close(void) = 0;
		
		// Allocate and Free

        virtual Byte* Alloc(size_t Len, Strm::Whence Whence =
            Strm::Curr, StreamOffset Offset = 0) = 0;
        virtual Byte* Alloc(size_t NewLen, Byte* Bfr) = 0;
		virtual Byte* VarAlloc(Byte Delim);

        virtual void  Free(Byte* Bfr) = 0;

		// Gneeral Status and such

		virtual bool  Seek(Strm::Whence Whence, StreamOffset Offset = 0) = 0;
		virtual AbsStreamOffset Tell(void) = 0;
		virtual bool  IsEOS(void);
		
    private:

        // Assignment

        SubStream& operator = (const SubStream& SubStream);
        SubStream(const SubStream& SubStream);

    protected:

        StreamSpec      _Spec;    // The full specification needed to name the stream,
        StreamInfo      _Info;    // The stat's needed to describe the stream
		StreamRegion*   _CurrRegion; // The current region as supplied by the substream instance
		size_t		    _BytesFound; // The size of bytes found in a variable length read.
};

#endif  // SUBSTREAM_HPP
E 2
I 1
E 1
