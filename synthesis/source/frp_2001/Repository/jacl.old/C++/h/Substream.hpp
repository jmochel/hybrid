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
