h53519
s 00095/00000/00000
d D 1.1 99/11/17 12:45:51 jmochel 2 1
cC
cK14120
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:48 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/w32/SubStreamReadMemMapWin32.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45307
cPC++/h/w32/SubStreamReadMemMapWin32.hpp
cR2f93d7955cb6ba86
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
#ifndef SSRW32MM_HPP
#define SSRW32MM_HPP

/*
    .Contains Win32MMFReadSubStream

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

#include <string>
#include <cstdlib>

#include <windows.h>

// Local Library Headers

#include "Types.hpp"

// Local Headers

#include "Substream.hpp"

/*
    .ClassDesc
        The I/O implementation of a Win32 Memory Mapped File substreams

    .ClassNotes
*/

class Win32MMFReadSubStream : public SubStream
{
    public:

        // Constructors

        Win32MMFReadSubStream(void);

        // Destructor

        ~Win32MMFReadSubStream(void);

        // Other methods

        virtual bool  Open(const StreamSpec& Spec, StreamInfo& Info);
        virtual bool  Close(void);

		// Alloc and Free

        virtual Byte* Alloc(size_t Len, Strm::Whence Whence, StreamOffset Offset);
		virtual Byte* Alloc(size_t Len, Byte* Bfr);
		virtual Byte* VarAlloc(Byte Delim);

        virtual void  Free(Byte* Bfr);

		// Misc and Status

		virtual bool  Seek(Strm::Whence Whence, StreamOffset Offset);
		virtual AbsStreamOffset Tell(void);
		virtual bool  IsEOS(void);

	protected:

		// Stream Region List data members

		size_t			_DefBfrSize;	// The default size of any region buffer allocated
		bool			_IsEOS;			// End of Stream indicator

    private:

        // Assignment 

        Win32MMFReadSubStream& operator = (const Win32MMFReadSubStream& Win32MMFReadSubStream);


    protected:

        HANDLE _FileHandle; // The file descriptor for reading from
		HANDLE _FileMap;    // The Mapping for the file
		LPVOID _ViewOfMap;  // The View of the mapping of the file that Jack built....
};

#endif  // SSRW32MM_HPP
E 2
I 1
E 1
