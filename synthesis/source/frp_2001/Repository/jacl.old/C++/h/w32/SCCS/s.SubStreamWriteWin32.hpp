h20551
s 00106/00000/00000
d D 1.1 99/11/17 12:46:03 jmochel 2 1
cC
cK47538
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:59 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/w32/SubStreamWriteWin32.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45308
cPC++/h/w32/SubStreamWriteWin32.hpp
cR2f93d7925cb6ba86
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
#ifndef SSWW32_HPP
#define SSWW32_HPP

/*
    .Contains Win32IOWriteSubStream

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#pragma warning(disable:4786) // Shut off warning about brain dead debug symbol limit

// Standard Library Headers

#include <string>
#include <cstdio>
#include <list>

#include <windows.h>

// Local Library Headers

#include "Types.hpp"

// Local Headers

#include "Substream.hpp"

// Declarations


/*
    .ClassDesc
                The Win32 I/O implementation of a substreams

    .ClassNotes
*/

class Win32IOWriteSubStream : public SubStream
{
    public:

        // Constructors

        Win32IOWriteSubStream(void);

        // Destructor

        ~Win32IOWriteSubStream(void);

        // I/O Stream overloads

        friend ostream& operator << (ostream& os,const Win32IOWriteSubStream& Win32IOWriteSubStream);

        // Other methods

        virtual bool  Open(const StreamSpec& Spec, StreamInfo& Info);
        virtual bool  Close(void);

        virtual Byte* Alloc(size_t Len, Strm::Whence Whence, StreamOffset Offset);
		virtual Byte* Alloc(size_t Len, Byte* Bfr);
        virtual void  Free(Byte* Bfr);

		virtual bool  Seek(Strm::Whence Whence, StreamOffset Offset);
		virtual AbsStreamOffset Tell(void);

	protected:

		// Stream Region List managment

		inline bool WriteRegion(RefCntdStreamRegion& Region);
		inline void BfrFree(StreamRegion& Region);

		inline bool BfrAlloc(size_t Len, StreamOffset Offset);
		inline void Dereference(Byte* Bfr);

    private:

        // Assignment

        Win32IOWriteSubStream& operator = (const Win32IOWriteSubStream& Win32IOWriteSubStream);

    protected:

		// I/O related data members

        HANDLE			_FileHandle; // The file descriptor for writing to.

		// Stream Region List data members

        size_t		   _DefBfrSize;	// The default size of any region buffer allocated

        list<RefCntdStreamRegion> _Regions;	// The actual list of regions
        list<RefCntdStreamRegion> _FreeRegions;// The list of available regions
};

#endif  // SSWW32_HPP
E 2
I 1
E 1
