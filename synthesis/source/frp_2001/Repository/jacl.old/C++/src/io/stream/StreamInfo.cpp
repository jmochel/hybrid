/*
    .Contains StreamInfo

    .Author Jim Jackl-Mochel

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $

    This code is in the public domain

*/


// Standard Library Headers

#include <cstdlib>

// Local Library Headers

#include "Reporter.hpp"
#include "Condition.hpp"
#include "Util.hpp"

// Local Headers

#include "StreamInfo.hpp"

StreamInfo::StreamInfo(void)
{
    _Mode = Strm::Read;
    _Service = Strm::File;
    _Order = Strm::Random;
    _BfrSize = 0;
    _ExpectedFileSize = 0;
}

StreamInfo::StreamInfo(const StreamInfo& aStreamInfo)
{
    _Mode = aStreamInfo._Mode;
    _Service = aStreamInfo._Service;
    _Order = aStreamInfo._Order;
    _BfrSize = aStreamInfo._BfrSize;
    _ExpectedFileSize = 0;
}

StreamInfo::StreamInfo(Strm::Mode Mode, Strm::Service Service, Strm::AccessOrder Order)
{
    _Mode = Mode;
    _Service = Service;
    _Order = Order;
    _BfrSize = 0;
    _ExpectedFileSize = 0;
}

// Destructor

StreamInfo::~StreamInfo(void)
{
}

// Accessors

const Strm::Mode StreamInfo::GetMode(void) const
{
    return((const Strm::Mode) _Mode);
}

const Strm::Service StreamInfo::GetService(void) const
{
    return((const Strm::Service) _Service);
}

const Strm::AccessOrder StreamInfo::GetOrder(void) const
{
    return((const Strm::AccessOrder) _Order);
}

const size_t StreamInfo::GetBfrSize(void) const
{
    return((const size_t) _BfrSize);
}

const size_t StreamInfo::GetExpectedFileSize(void) const
{
    return((const size_t) _ExpectedFileSize);
}

// Mutators

void StreamInfo::SetMode(const Strm::Mode Mode)
{
    _Mode= Mode;
}

void StreamInfo::SetService(const Strm::Service Service)
{
    _Service= Service;
}

void StreamInfo::SetOrder(const Strm::AccessOrder Order)
{
    _Order= Order;
}

void StreamInfo::SetBfrSize(size_t BfrSize)
{
    _BfrSize = BfrSize;
}

void StreamInfo::SetExpectedFileSize(size_t ExpectedFileSize)
{
    _ExpectedFileSize = ExpectedFileSize;
}

// Comparison

/*
    .MethodDesc Compares two objects

    .MethodNotes Detail out the comparison values

    .MethodReturn -1 if ...
                  0 if equal
                  1 if ...

*/
int StreamInfo::Compare(const StreamInfo& aStreamInfo) const
{
    return(1);
}

// Assignment 


StreamInfo& StreamInfo::operator= (const StreamInfo& aStreamInfo)
{
    // Handle assignment to self

    if ( this == &aStreamInfo)
    {
        return(*this);
    }

    // Assign the class members 

    _Mode = aStreamInfo._Mode;
    _Service = aStreamInfo._Service;
    _Order = aStreamInfo._Order;

    return(*this);
}

StreamInfo& StreamInfo::operator = (const Strm::Mode Mode)
{
    _Mode = Mode;

    return(*this);
}

StreamInfo& StreamInfo::operator = (const Strm::Service Service)
{
    _Service = Service;

    return(*this);
}

StreamInfo& StreamInfo::operator = (const size_t BfrSize)
{
    _BfrSize = BfrSize;

    return(*this);
}


// Overloaded logic operators

bool StreamInfo::operator ==(const StreamInfo& aStreamInfo) const
{
    ReturnTrueIf(Compare(aStreamInfo) == 0);

    return(false);
}

bool StreamInfo::operator !=(const StreamInfo& aStreamInfo) const
{
    return(true);
}

bool StreamInfo::operator >(const StreamInfo& aStreamInfo) const
{
    return(true);
}

bool StreamInfo::operator <(const StreamInfo& aStreamInfo) const
{
    return(true);
}

bool StreamInfo::operator >=(const StreamInfo& aStreamInfo) const
{
    return(true);
}

bool StreamInfo::operator <=(const StreamInfo& aStreamInfo) const
{
    return(true);
}

// Other methods

#ifdef TEST_SSINFO


#include <stdlib.h>

#include "SSINFO.hpp"


int main(int argc, char* argv[])
{
    return(EXIT_SUCCESS);
}
#endif // TEST_SSINFO



