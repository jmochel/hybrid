h08532
s 00103/00000/00000
d D 1.1 99/11/17 08:10:06 jmochel 2 1
cC
cK36457
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:10:03 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/StreamWithStdHdr.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43652
cPC++/h/StreamWithStdHdr.hpp
cRdf02b12f5cb6bb60
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
#ifndef STREAMWITHSTDHDR_HPP
#define STREAMWITHSTDHDR_HPP

/*
    .Contains StreamWithStdHdr

    .Author Jim Jackl-Mochel

    .Date 02.10.95

    .Copyright  This code is in the public domain.

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

#ifndef STREAM_HPP
#include "Stream.hpp"
#endif

#ifndef STDHDR_HPP
#include "StdHdr.hpp"
#endif

/*
    .ClassDesc 
    
        StreamWithStdHdr is just that, a stream with a standardised header

    .ClassLongDesc
    
        The standardised header is an integral part of the file format. So much
        that it is available upon opening and written (if applicable) upon closing.
        
        All offsets taken from and given to the StreamWithStdHdr are 
        exclusive of the size of the header. So Offset 0 is the first byte 
        after the header. This allows file formats to reference the same record 
        in a StreamWithStdHdr without worrying about the header changing size.
        
    .ClassNotes

*/

class StreamWithStdHdr : public Stream
{
    public:

        // Constructors

        StreamWithStdHdr();

        // Destructor

        virtual ~StreamWithStdHdr(void);

        // Other methods

        virtual bool  Open(const StreamSpec& Spec, StreamInfo& Info);
        virtual bool  Close(void);

        StdHdr  GetHdr(void);
        void    SetHdr(StdHdr& Hdr);

    protected:
    
        void    Read(StdHdr& Hdr);
        void    Read(std::string& aString);
        void    Read(DateTime& aDateTime);
        void    Read(Revision& aRevision);
        void    Read(UInt16& AnInt);
        void    Read(UInt32& AnInt);

        void    Write(StdHdr& Hdr);
        void    Write(std::string& aString);
        void    Write(DateTime& aDateTime);
        void    Write(Revision& aRevision);
        void    Write(UInt16 AnInt);
        void    Write(UInt32 AnInt);

    private:

        // Assignment

        Stream& operator = (const Stream& Stream);
        void    SetDefaults(void);

    protected:

        StdHdr  _Hdr;
};

#endif  // STREAMWITHSTDHDR_HPP
E 2
I 1
E 1
