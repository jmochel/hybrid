/*
    @doc

    .Contains: CommonSpec Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#pragma message("CommonSpec.cpp : Check that all Exceptions are thrown.")
#pragma message("CommonSpec.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("CommonSpec.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("CommonSpec.cpp : Check local variable and parameter naming")
#pragma message("CommonSpec.cpp : Check Pre and Postconditions")
#pragma message("CommonSpec.cpp : Lint")
#pragma message("CommonSpec.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "CommonSpec.hpp"

/*
    ===============================================================================

                                    Constructors/Destructor

    ===============================================================================

*/


/*
    @MethodDesc
        
          Void constructor
*/

CommonSpec::CommonSpec()
{
	SetDefaults();
}


/*
    @MethodDesc
        
          Copy constructor

*/

CommonSpec::CommonSpec(const CommonSpec& commonSpec)
{
    _Type = commonSpec._Type;
    _Name = commonSpec._Name;

}

/*
    @MethodDesc
        
          Constructor

    @Parm        The Type of the spec
    @Parm        The full text std::string identifying the resource in question.

*/

CommonSpec::CommonSpec(CommonSpec::Type type, const std::string& name)
{
    _Type = type;
    _Name = name;
}

/*
    @MethodDesc
        
          Destructor
*/


CommonSpec::~CommonSpec()
{
}


/*
    ===============================================================================

                                    Initialization

    ===============================================================================

*/

/*
    @MethodDesc
        
          Sets the class to the default state.
*/

void CommonSpec::SetDefaults(void)
{
    _Type = Stream;
    _Name = "";
}


/*
    ===============================================================================

                                    Accessors

    ===============================================================================

*/

/*
    @MethodDesc
        
          Gets Type
*/

const CommonSpec::Type CommonSpec::GetType(void) const
{
    return(_Type);
}


/*
    @MethodDesc
        
          Gets Name
*/

const std::string& CommonSpec::GetName(void) const
{
    return(_Name);
}


/*
    ===============================================================================

                                    Mutators

    ===============================================================================

*/


/*
    @MethodDesc
        
          Sets Type

*/

void CommonSpec::SetType(const CommonSpec::Type& type)
{
	_Type = type;
}


/*
    @MethodDesc
        
          Sets Name
*/

void CommonSpec::SetName(const std::string& name)
{
	_Name = name;
}


/*
    ===============================================================================

                                    Logic Operators

    ===============================================================================

*/


/*
    @MethodDesc

        Compares two CommonSpec objects
    
    @ReturnDesc
    
        -1 if *this falls into sequence before aCommonSpec
        0 if the two objects are equal.
        +1 if *this falls into sequence after aCommonSpec
    
    @MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  CommonSpec::CompareTo(const CommonSpec& commonSpec) const
{
    if ( _Type == commonSpec._Type )
    {
        return(_Name.compare(commonSpec._Name));
    }

    if ( _Type == CommonSpec::Folder )
    {
        return(-1);
    }

    return(1);
}

/*
    @MethodDesc

        Checks two CommonSpec objects for equality
    
    @ReturnDesc
    
        true if the two objects are equal false if they are not
    
    @MethodNotes
    
        Explain the equality criteria.
*/    

bool CommonSpec::operator ==(const CommonSpec& commonSpec) const
{
    return(CompareTo(commonSpec) == 0);
}

/*
    @MethodDesc

        Checks two CommonSpec objects less than relationship
    
    @ReturnDesc
    
        true if the two objects are equal false if they are not
    
    @MethodNotes
    
        Explain the equality criteria.
*/    

bool CommonSpec::operator <(const CommonSpec& commonSpec) const
{
    return(CompareTo(commonSpec) == 0);
}


/*
    @MethodDesc

        Checks for CommonSpec objects inequality relationship
    
    @ReturnDesc
    
        false if the two objects are equal true if they are not
    
    @MethodNotes
    
        Explain the inequality criteria.
*/    

bool CommonSpec::operator !=(const CommonSpec& spec) const
{
    return(CompareTo(spec) != 0);
}

/*
    @MethodDesc

        Checks for CommonSpec object inequality with a char* 
    
    @ReturnDesc
    
        false if the two objects are equal true if they are not
    
    @MethodNotes
    
        Explain the inequality criteria.
*/    

bool CommonSpec::operator !=(const char* spec) const
{
    return(_Name.compare(spec) != 0);
}

        
/*
    ===============================================================================

                                    Assignment

    ===============================================================================

*/

/*
    @MethodDesc
        
          Assignment Operator

*/

CommonSpec& CommonSpec::operator =(const CommonSpec& commonSpec)
{
    if ( this == &commonSpec )
    {
        return(*this);
    }

    _Type = commonSpec._Type;
    _Name = commonSpec._Name;

    return(*this);
}
