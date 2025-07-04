#ifndef STREAMINFO_HPP
#define STREAMINFO_HPP

/*
    .Contains StreamInfo

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

// Local Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef STRM_HPP
#include "Strm.hpp"
#endif


/*
    .ClassDesc

            An encapsulation of status information for streams

    .ClassNotes
        


*/

class StreamInfo
{

    public:

        // Constructors

        StreamInfo(void);
        StreamInfo(const StreamInfo& StreamInfo);
        StreamInfo(Strm::Mode aMode, Strm::Service aService, Strm::AccessOrder anOrder);

        // Destructor

        ~StreamInfo(void);

        // Accessors

        const Strm::Mode GetMode(void) const;
        const Strm::Service GetService(void) const;
        const Strm::AccessOrder GetOrder(void) const;
        const size_t GetBfrSize(void) const;
        const size_t GetExpectedFileSize(void) const;

        // Mutators

        void SetMode(const Strm::Mode Mode);
        void SetService(const Strm::Service Service);
        void SetOrder(const Strm::AccessOrder Order);
        void SetBfrSize(size_t BfrSize);
        void SetExpectedFileSize(size_t ExpectedFileSize);

        // Comparison

        int Compare(const StreamInfo& StreamInfo) const;

        // Assignment

        StreamInfo& operator = (const StreamInfo& StreamInfo);
		StreamInfo& operator = (const Strm::Mode Mode);
		StreamInfo& operator = (const Strm::Service Service);
		StreamInfo& operator = (const size_t BfrSize);

        // Overloaded logic operators

        bool operator ==(const StreamInfo& aStreamInfo) const;
        bool operator !=(const StreamInfo& aStreamInfo) const;
        bool operator >(const StreamInfo& aStreamInfo) const;
        bool operator <(const StreamInfo& aStreamInfo) const;
        bool operator >=(const StreamInfo& aStreamInfo) const;
        bool operator <=(const StreamInfo& aStreamInfo) const;

        // I/O Stream overloads

        friend ostream& operator << (ostream& os,const StreamInfo& StreamInfo);

    protected:

        Strm::Mode      _Mode;          // Access mode for the stream
        Strm::Service   _Service;       // The type of stream
        Strm::AccessOrder _Order;       // The ordering method used to access the stream.
        size_t          _BfrSize;       // The size of the buffer to use
        size_t          _ExpectedFileSize;   // The expected file size 
};

#endif  // STREAMINFO_HPP
