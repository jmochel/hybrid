/*
    @doc

    .Contains: Chronon Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#pragma message("Chronon.cpp : Check that all Exceptions are thrown.")
#pragma message("Chronon.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("Chronon.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("Chronon.cpp : Check local variable and parameter naming")
#pragma message("Chronon.cpp : Check Pre and Postconditions")
#pragma message("Chronon.cpp : Lint")
#pragma message("Chronon.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "Chronon.hpp"




/*
    ===============================================================================

                                    Constructors/Destructor

    ===============================================================================

*/


/*
    @MethodDesc
        
          Void constructor

    @MethodNotes
        
        <???>
*/

Chronon::Chronon()
{
	SetDefaults();
}


/*
    @MethodDesc
        
          Copy constructor

    @MethodNotes
        
        <???>
*/

Chronon::Chronon(const Chronon& chronon)
{
    _DataBlock = chronon._DataBlock;

}

/*
    @MethodDesc
        
          Constructor

    @MethodNotes
        
        <???>

    @Parm        <???>

*/

Chronon::Chronon(const std::string& dataBlock)
{
    _DataBlock = dataBlock;
}

/*
    @MethodDesc
        
          Destructor

    @MethodNotes
        
        <???>

*/


Chronon::~Chronon()
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

    @MethodNotes
        
        <???>

*/

void Chronon::SetDefaults(void)
{
    _DataBlock = "";
}


/*
    ===============================================================================

                                    Accessors

    ===============================================================================

*/


/*
    @MethodDesc
        
          Gets DataBlock

    @MethodNotes
        
        <???>

*/

const std::string& Chronon::GetDataBlock(void) const
{
    return(_DataBlock);
}


/*
    ===============================================================================

                                    Mutators

    ===============================================================================

*/


/*
    @MethodDesc
        
          Sets DataBlock

    @MethodNotes
        
        <???>
*/

void Chronon::SetDataBlock(const std::string& dataBlock)
{
	_DataBlock = dataBlock;
}


/*
    ===============================================================================

                                    Logic Operators

    ===============================================================================

*/


/*
    @MethodDesc

        Compares two Chronon objects
    
    @ReturnDesc
    
        -1 if *this falls into sequence before aChronon
        0 if the two objects are equal.
        +1 if *this falls into sequence after aChronon
    
    @MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  Chronon::CompareTo(const Chronon& chronon)
{
    #pragma message(__FILE__ "(1) :"  "Chronon::CompareTo needs to be implemented or removed")

    return(0);
}

/*
    @MethodDesc

        Checks two Chronon objects for equality
    
    @ReturnDesc
    
        true if the two objects are equal false if they are not
    
    @MethodNotes
    
        Explain the equality criteria.
*/    

bool Chronon::operator ==(const Chronon& chronon)
{
    return(CompareTo(chronon) == 0);
}

/*
    ===============================================================================

                                    Assignment

    ===============================================================================

*/

/*
    @MethodDesc
        
          Assignment Operator

    @MethodNotes
        
        <???>

*/

Chronon& Chronon::operator =(const Chronon& chronon)
{
    if ( this == &chronon )
    {
        return(*this);
    }

    _DataBlock = chronon._DataBlock;

    return(*this);
}
