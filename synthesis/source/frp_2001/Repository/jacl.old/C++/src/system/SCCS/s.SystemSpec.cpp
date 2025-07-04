h46757
s 00141/00000/00000
d D 1.1 99/11/17 08:47:31 jmochel 2 1
cC
cK08420
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:28 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/SystemSpec.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43877
cPC++/src/system/SystemSpec.cpp
cR19365bcf5cb6bb65
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
    .Contains: SystemSpec Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("SystemSpec.cpp : Check that all Exceptions are thrown.")
#pragma message("SystemSpec.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("SystemSpec.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("SystemSpec.cpp : Check local variable and parameter naming")
#pragma message("SystemSpec.cpp : Check Pre and Postconditions")
#pragma message("SystemSpec.cpp : Lint")
#pragma message("SystemSpec.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "SystemSpec.hpp"


// Constructors

SystemSpec::SystemSpec()
{
	SetDefaults();
}

SystemSpec::SystemSpec(const SystemSpec& systemSpec)
{
    _Name = systemSpec._Name;

}

SystemSpec::SystemSpec(const std::string& name)
{
    _Name = name;
}

// Set Defaults

void SystemSpec::SetDefaults(void)
{
    _Name = "";
}

// Destructors

SystemSpec::~SystemSpec()
{
}

// Accessors

const std::string& SystemSpec::GetName(void) const
{
    return(_Name);
}


// Mutators

void SystemSpec::SetName(const std::string& name)
{
	_Name = name;
}


// Logic operators

/*
    .MethodDesc Compares two SystemSpec objects
    
    .MethodReturn
    
        -1 if *this falls into sequence before aSystemSpec
        0 if the two objects are equal.
        +1 if *this falls into sequence after aSystemSpec
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  SystemSpec::CompareTo(const SystemSpec& systemSpec)
{
    #pragma message(__FILE__ "(1) :"  "SystemSpec::CompareTo needs to be implemented or removed")

    return(0);
}

/*
    .MethodDesc Compares two SystemSpec objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool SystemSpec::operator ==(const SystemSpec& systemSpec)
{
    return(CompareTo(systemSpec) == 0);
}

// Assignment

SystemSpec& SystemSpec::operator =(const SystemSpec& systemSpec)
{
    if ( this == &systemSpec )
    {
        return(*this);
    }

    _Name = systemSpec._Name;

    return(*this);
}
E 2
I 1
E 1
