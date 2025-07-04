/*
    .Contains: VolumeSpec Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("VolumeSpec.cpp : Check that all Exceptions are thrown.")
#pragma message("VolumeSpec.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("VolumeSpec.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("VolumeSpec.cpp : Check local variable and parameter naming")
#pragma message("VolumeSpec.cpp : Check Pre and Postconditions")
#pragma message("VolumeSpec.cpp : Lint")
#pragma message("VolumeSpec.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "VolumeSpec.hpp"


// Constructors

VolumeSpec::VolumeSpec()
{
	SetDefaults();
}

VolumeSpec::VolumeSpec(const VolumeSpec& volumeSpec)
{
    _Name = volumeSpec._Name;

}

VolumeSpec::VolumeSpec(const std::string& name)
{
    _Name = name;
}

// Set Defaults

void VolumeSpec::SetDefaults(void)
{
    _Name = "";
}

// Destructors

VolumeSpec::~VolumeSpec()
{
}

// Accessors

const std::string& VolumeSpec::GetName(void) const
{
    return(_Name);
}


// Mutators

void VolumeSpec::SetName(const std::string& name)
{
	_Name = name;
}


// Logic operators

/*
    .MethodDesc Compares two VolumeSpec objects
    
    .MethodReturn
    
        -1 if *this falls into sequence before aVolumeSpec
        0 if the two objects are equal.
        +1 if *this falls into sequence after aVolumeSpec
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  VolumeSpec::CompareTo(const VolumeSpec& volumeSpec)
{
    #pragma message(__FILE__ "(1) :"  "VolumeSpec::CompareTo needs to be implemented or removed")

    return(0);
}

/*
    .MethodDesc Compares two VolumeSpec objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool VolumeSpec::operator ==(const VolumeSpec& volumeSpec)
{
    return(CompareTo(volumeSpec) == 0);
}

// Assignment

VolumeSpec& VolumeSpec::operator =(const VolumeSpec& volumeSpec)
{
    if ( this == &volumeSpec )
    {
        return(*this);
    }

    _Name = volumeSpec._Name;

    return(*this);
}
