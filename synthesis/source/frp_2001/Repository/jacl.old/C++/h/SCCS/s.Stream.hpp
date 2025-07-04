h61372
s 00151/00000/00000
d D 1.1 99/11/17 08:09:42 jmochel 2 1
cC
cK25625
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:39 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Stream.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43650
cPC++/h/Stream.hpp
cRd0ee7cff5cb6bb60
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
#ifndef STREAM_HPP
#define STREAM_HPP

/*
    @doc

    Jim Jackl-Mochel

  Copyright  This code is in the public domain.

  Revision Information
  ====================
  $Author: jmochel $
  $Revision: 1.1.1.1 $
  $Date: 1998/06/12 16:36:30 $
*/

#include <string>

// Local Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef STREAMSPEC_HPP
#include "StreamSpec.hpp"
#endif

#ifndef STREAMINFO_HPP
#include "StreamInfo.hpp"
#endif

#ifndef SUBSTREAM_HPP
#include "SubStream.hpp"
#endif

// Declarations

typedef SubStream* (*StreamInitFxn)(const StreamSpec& Spec, StreamInfo& Info);

#include "StreamPlatform.hpp"

/*
    @ClassDesc The Basic I/O Stream

    @ClassNotes This is a Envelope class for a SubStream object
*/

class Stream
{
     // @Access Public

     public:

        // @MemberDesc Constructor

        Stream(StreamInitFxn anInitFxn = DefStreamInitFxn);

        // @MemberDesc Destructor

        ~Stream(void);

        //  @MemberDesc     Opens a stream

        bool  Open(const StreamSpec& Spec, StreamInfo& Info);

        // @MemberDesc      Closes a stream

        bool  Close(void);

        // @MemberDesc Allocate a block of memory from the given offset

        Byte* Alloc(size_t Len, Strm::Whence Whence, StreamOffset Offset);

        // @MemberDesc Allocate a block of memory from the current offset

        Byte* Alloc(size_t Len);

        // @MemberDesc Reallocate a block of memory for the given address

        Byte* Alloc(size_t NewLen, Byte* Bfr);

        // @MemberDesc Allocate a block of memory up to the first example of Delim that you find.

        Byte* VarAlloc(Byte Delim);

        // @MemberDesc Frees a given memory pointer

        inline void  Free(Byte* Bfr);

        // @MemberDesc Seek to the given offset

        virtual bool  Seek(Strm::Whence Whence, StreamOffset Offset = 0);

        // @MemberDesc Get the absolute address of the current offset.

        virtual AbsStreamOffset Tell(void);

        // @MemberDesc Test if we are at the end of the stream

        virtual bool  IsEOS(void);

        // @MemberDesc Get the number of bytes read in by a <c VarAlloc> call
        
        size_t GetBytesFound(void) const;

    // @Access Private

    private:

        // @MemberDesc Assignment

        Stream& operator = (const Stream& Stream);

        // @MemberDesc Sets the defaults for the class

        void  SetDefaults(void);

    // @Access Protected

    protected:

        // @MemberDesc The I/O SubStream facility

        SubStream*      _SubStream;

        // @MemberDesc The initialisation routine

        StreamInitFxn   _InitFxn;

        // @MemberDesc The Current Region

        StreamRegion*   _CurrRegion;
};


/*
    @MethodDesc 

        Free the given buffer
*/

void  Stream::Free(Byte* Bfr)
{
  Precondition(Bfr != 0);

  _SubStream->Free(Bfr);
}

#endif  // STREAM_HPP
E 2
I 1
E 1
