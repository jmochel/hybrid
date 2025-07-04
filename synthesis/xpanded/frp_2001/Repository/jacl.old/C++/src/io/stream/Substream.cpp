/*
    .Contains SubStream

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $

*/


// Standard Library Headers

#include <cstdlib>
#include <iostream>

// Local Library Headers

#include "Condition.hpp"
#include "Substream.hpp"

SubStream::SubStream(void) : _Spec(), _Info()
{
    _CurrRegion = 0;
    _BytesFound = 0;
}

/*
        .MethodDesc

        .MethodNotes 

                This is a private function and should never be called.
*/

SubStream::SubStream(const SubStream& aSubStream)
{

}

SubStream::SubStream(const StreamSpec& Spec, const StreamInfo& Info)
{
    _Spec = Spec;
    _Info = Info;
    _CurrRegion = 0;
    _BytesFound = 0;
}

// Destructor

SubStream::~SubStream(void)
{
}

// Accessors

const StreamSpec SubStream::GetSpec(void) const
{
    return((const StreamSpec) _Spec);
}

const StreamInfo SubStream::GetInfo(void) const
{
    return((const StreamInfo) _Info);
}

size_t SubStream::GetBytesFound(void) const
{
    return(_BytesFound);
}

// Mutators

void SubStream::SetSpec(const StreamSpec& Spec)
{
    _Spec= Spec;
}

void SubStream::SetInfo(const StreamInfo& Info)
{
    _Info= Info;
}

Byte* SubStream::VarAlloc(Byte Delim)
{
        // If this gets called something is wrong !

        Precondition(true == true);

        return(0);
}

bool SubStream::IsEOS(void)
{
        // If this gets called something is wrong !

        Precondition(true == true);

        return(0);
}

