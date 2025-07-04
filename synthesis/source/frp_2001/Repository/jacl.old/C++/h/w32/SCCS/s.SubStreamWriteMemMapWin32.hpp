h47522
s 00089/00000/00000
d D 1.1 99/11/17 12:45:59 jmochel 2 1
cC
cK07820
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:56 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/w32/SubStreamWriteMemMapWin32.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45307
cPC++/h/w32/SubStreamWriteMemMapWin32.hpp
cR2f93d7935cb6ba86
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
#ifndef SSWW32MM_HPP
#define SSWW32MM_HPP

/*
    .Contains Win32MMFWriteSubStream

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

#include <iostream>
#include <string>
#include <cstdlib>

#include <windows.h>

// Local Library Headers

#include "Types.hpp"
#include "Substream.hpp"

/*
    .ClassDesc
        The I/O implementation of a Win32 Memory Mapped File substreams

    .ClassNotes
*/

class Win32MMFWriteSubStream : public SubStream
{
    public:

        // Constructors

        Win32MMFWriteSubStream(void);

        // Destructor

        ~Win32MMFWriteSubStream(void);

        // I/O Stream overloads

        friend ostream& operator << (ostream& os,const Win32MMFWriteSubStream& Win32MMFWriteSubStream);

        // Other methods

        bool  Open(const StreamSpec& Spec, StreamInfo& Info);
        bool  Close(void);

        Byte* Alloc(size_t Len, Strm::Whence Whence, StreamOffset Offset);
    
        void  Free(Byte* Bfr);

		virtual bool  Seek(Strm::Whence Whence, StreamOffset Offset);
		virtual AbsStreamOffset Tell(void);

	protected:

		// Stream Region List data members

		size_t			_DefBfrSize;	// The default size of any region buffer allocated

    private:

        // Assignment 

        Win32MMFWriteSubStream& operator = (const Win32MMFWriteSubStream& Win32MMFWriteSubStream);


    protected:

        HANDLE _FileHandle; // The file descriptor for writing to
		HANDLE _FileMap;    // The Mapping for the file
		LPVOID _ViewOfMap;  // The View of the mapping of the file that Jack built....
};

#endif  // SSWW32MM_HPP
E 2
I 1
E 1
