h05819
s 00109/00000/00000
d D 1.1 99/11/17 12:45:55 jmochel 2 1
cC
cK33105
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:51 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/w32/SubStreamReadWin32.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45307
cPC++/h/w32/SubStreamReadWin32.hpp
cR2f93d7945cb6ba86
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
#ifndef SSRW32_HPP
#define SSRW32_HPP

/*
    .Contains Win32IOReadSubStream

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// Standard Library Headers

#include <cstdlib>

#include <string>
#include <list>


#include <windows.h>

// Local Library Headers

#include "Types.hpp"

// Local Headers

#include "Substream.hpp"

/*
    .ClassDesc
        The I/O implementation of a substreams

    .ClassNotes
*/

class Win32IOReadSubStream : public SubStream
{
    public:

        // Constructors

        Win32IOReadSubStream(void);

        // Destructor

        ~Win32IOReadSubStream(void);

        // I/O Stream overloads

        friend ostream& operator << (ostream& os,const Win32IOReadSubStream& Win32IOReadSubStream);

        // Other methods

        bool  Open(const StreamSpec& Spec, StreamInfo& Info);
        bool  Close(void);

        Byte* Alloc(size_t Len);
        Byte* Alloc(size_t Len, StreamOffset Offset);
        Byte* Alloc(size_t NewLen, Byte* Bfr);
        Byte* Alloc(size_t Len, Byte Delim);

        void  Free(Byte* Bfr);

		virtual bool  Seek(Strm::Whence Whence, StreamOffset Offset);
		virtual AbsStreamOffset Tell(void);

		size_t BytesLeft(void);

	protected:

		// Stream Region List managment

		inline bool		  BfrAlloc(size_t Len, StreamOffset Offset);
		inline void		  BfrFree(StreamRegion& Region);
		inline void		  Dereference(Byte* Bfr);

	protected:

		// Stream Region List data members

        size_t			_DefBfrSize;	// The default size of any region buffer allocated
        list<RefCntdStreamRegion> _Regions;	// The actual list of regions
        list<RefCntdStreamRegion> _FreeRegions;// The list of available regions

    private:

        // Assignment 

        Win32IOReadSubStream& operator = (const Win32IOReadSubStream& Win32IOReadSubStream);



    protected:

        HANDLE _FileHandle; // The file descriptor for reading from

};

#endif  // SSRW32_HPP
E 2
I 1
E 1
