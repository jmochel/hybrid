h49369
s 00135/00000/00000
d D 1.1 99/11/17 12:47:49 jmochel 2 1
cC
cK10612
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:47:46 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/StreamSpec.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45318
cPC++/src/io/stream/StreamSpec.cpp
cR2f93d7b45cb6ba86
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
    .Contains: StreamSpec Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/


#pragma message("StreamSpec.cpp : Check that all Exceptions are thrown.")
#pragma message("StreamSpec.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("StreamSpec.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("StreamSpec.cpp : Check local variable and parameter naming")
#pragma message("StreamSpec.cpp : Check Pre and Postconditions")
#pragma message("StreamSpec.cpp : Lint")
#pragma message("StreamSpec.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "StreamSpec.hpp"


// Constructors

StreamSpec::StreamSpec() : CommonSpec()
{
	SetDefaults();
}

StreamSpec::StreamSpec(const StreamSpec& streamSpec) : CommonSpec(CommonSpec::Stream, streamSpec.GetName())
{

}

StreamSpec::StreamSpec(std::string& name) : CommonSpec(CommonSpec::Stream, name)
{

}

// Set Defaults

void StreamSpec::SetDefaults(void)
{
}

// Destructors

StreamSpec::~StreamSpec()
{
}

// Logic operators

/*
    .MethodDesc Compares two StreamSpec objects
    
    .MethodReturn
    
        -1 if *this falls into sequence before aStreamSpec
        0 if the two objects are equal.
        +1 if *this falls into sequence after aStreamSpec
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  StreamSpec::CompareTo(const StreamSpec& spec) const
{
    return(_Name.compare(spec._Name));
}

/*
    .MethodDesc Compares two StreamSpec objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool StreamSpec::operator ==(const StreamSpec& streamSpec) const
{
    return(CompareTo(streamSpec) == 0);
}

bool StreamSpec::operator <(const StreamSpec& streamSpec) const
{
    return(CompareTo(streamSpec) == -1);
}

// Assignment

StreamSpec& StreamSpec::operator =(const StreamSpec& streamSpec) 
{
    if ( this == &streamSpec )
    {
        return(*this);
    }

    _Name = streamSpec._Name;

    return(*this);
}

// Assignment

StreamSpec& StreamSpec::operator =(const char* spec)
{
    _Name = spec;

    return(*this);
}
E 2
I 1
E 1
