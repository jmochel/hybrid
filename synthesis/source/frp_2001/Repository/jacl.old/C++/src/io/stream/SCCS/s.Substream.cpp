h33851
s 00108/00000/00000
d D 1.1 99/11/17 12:48:00 jmochel 2 1
cC
cK60766
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:47:56 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/Substream.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45320
cPC++/src/io/stream/Substream.cpp
cR2f93d7b15cb6ba86
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

E 2
I 1
E 1
