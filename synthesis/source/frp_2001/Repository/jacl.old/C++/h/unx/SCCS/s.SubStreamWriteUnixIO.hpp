h13874
s 00109/00000/00000
d D 1.1 99/11/17 12:45:37 jmochel 2 1
cC
cK40269
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:34 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/unx/SubStreamWriteUnixIO.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45305
cPC++/h/unx/SubStreamWriteUnixIO.hpp
cR2f93d7995cb6ba86
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
#ifndef SSWUIO_HPP
#define SSWUIO_HPP

/*
    .Contains UnixIOWriteSubStream

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// Forward declarations

class ostream;
class string;

// Standard Library Headers

#include <stdio.h>

#include "list.h"

// Local Library Headers

#include "jacl/types.hpp"

// Local Headers

#include "jacl/sstrm.hpp"

// Declarations


/*
    .ClassDesc
                The I/O implementation of a substreams

    .ClassNotes
*/

class UnixIOWriteSubStream : public SubStream
{
    public:

        // Constructors

        UnixIOWriteSubStream(void);

        // Destructor

        ~UnixIOWriteSubStream(void);

        // I/O Stream overloads

        friend ostream& operator << (ostream& os,const UnixIOWriteSubStream& UnixIOWriteSubStream);

        // Other methods

        virtual bool  Open(const StreamSpec& Spec, StreamInfo& Info);
        virtual bool  Close(void);

        virtual Byte* Alloc(size_t Len);
        virtual Byte* Alloc(size_t Len, StreamOffset Offset);
        virtual Byte* Alloc(size_t NewLen, Byte* Bfr);
        virtual Byte* Alloc(size_t Len, Byte Delim);

        void  Free(Byte* Bfr);

		virtual bool  Seek(Strm::Whence Whence, StreamOffset Offset);
		virtual StreamOffset  Tell(void);
		virtual size_t BytesLeft(void);

	protected:

		// Stream Region List managment

		bool		  BfrAlloc(size_t Len, StreamOffset Offset);
		void		  BfrFree(Byte* Bfr);
		void		  Dereference(Byte* Bfr);

	protected:

		// Stream Region List data members

        size_t			_DefBfrSize;	// The default size of any region buffer allocated
        list<RefCntdStreamRegion> _Regions;	// The actual list of regions
        list<RefCntdStreamRegion> _FreeRegions;// The list of available regions

    private:

        // Assignment

        UnixIOWriteSubStream& operator = (const UnixIOWriteSubStream& UnixIOWriteSubStream);

    protected:

        int _FileHandle; // The file descriptor for writing to
};

#endif  // SSWUIO_HPP
E 2
I 1
E 1
