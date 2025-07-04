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
