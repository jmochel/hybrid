/*
    @doc

    .Contains: StreamSpecManipulator Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("StreamSpecManipulator.cpp : Check that all Exceptions are thrown.")
#pragma message("StreamSpecManipulator.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("StreamSpecManipulator.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("StreamSpecManipulator.cpp : Check local variable and parameter naming")
#pragma message("StreamSpecManipulator.cpp : Check Pre and Postconditions")
#pragma message("StreamSpecManipulator.cpp : Lint")
#pragma message("StreamSpecManipulator.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "StreamSpecManipulator.hpp"




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

StreamSpecManipulator::StreamSpecManipulator()
{
	SetDefaults();
}


/*
    @MethodDesc
        
          Copy constructor

    @MethodNotes
        
        <???>
*/

StreamSpecManipulator::StreamSpecManipulator(const StreamSpecManipulator& streamSpecManipulator)
{
    _FolderSpec = streamSpecManipulator._FolderSpec;
    _Canonical = streamSpecManipulator._Canonical;
    _Root = streamSpecManipulator._Root;
    _Extension = streamSpecManipulator._Extension;
    _Name = streamSpecManipulator._Name;

}

/*
    @MethodDesc
        
          Constructor

    @MethodNotes
        
        <???>

    @Parm        <???>

*/

StreamSpecManipulator::StreamSpecManipulator(const StreamSpec& spec)
{
    _Canonical = spec;
}

/*
    @MethodDesc
        
          Destructor

    @MethodNotes
        
        <???>

*/


StreamSpecManipulator::~StreamSpecManipulator()
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

void StreamSpecManipulator::SetDefaults(void)
{
    _FolderSpec = FolderSpec(std::string(""));
    _Canonical = StreamSpec(std::string(""));
    _Root = "";
    _Extension = "";
    _Name = "";
}


/*
    ===============================================================================

                                    Accessors

    ===============================================================================

*/


/*
    @MethodDesc
        
          Gets FolderSpec

    @MethodNotes
        
        <???>

*/

const FolderSpec& StreamSpecManipulator::GetFolderSpec(void) const
{
    return(_FolderSpec);
}


/*
    @MethodDesc
        
          Gets Canonical

    @MethodNotes
        
        <???>

*/

const StreamSpec& StreamSpecManipulator::GetCanonical(void) const
{
    return(_Canonical);
}


/*
    @MethodDesc
        
          Gets Root

    @MethodNotes
        
        <???>

*/

const std::string& StreamSpecManipulator::GetRoot(void) const
{
    return(_Root);
}


/*
    @MethodDesc
        
          Gets Extension

    @MethodNotes
        
        <???>

*/

const std::string& StreamSpecManipulator::GetExtension(void) const
{
    return(_Extension);
}


/*
    @MethodDesc
        
          Gets Name

    @MethodNotes
        
        <???>

*/

const std::string& StreamSpecManipulator::GetName(void) const
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
        
          Sets FolderSpec

    @MethodNotes
        
        <???>
*/

void StreamSpecManipulator::SetFolderSpec(const FolderSpec& folderSpec)
{
	_FolderSpec = folderSpec;
}


/*
    @MethodDesc
        
          Sets Canonical

    @MethodNotes
        
        <???>
*/

void StreamSpecManipulator::SetCanonical(const StreamSpec& canonical)
{
	_Canonical = canonical;
}


/*
    @MethodDesc
        
          Sets Root

    @MethodNotes
        
        <???>
*/

void StreamSpecManipulator::SetRoot(const std::string& root)
{
	_Root = root;
}


/*
    @MethodDesc
        
          Sets Extension

    @MethodNotes
        
        <???>
*/

void StreamSpecManipulator::SetExtension(const std::string& extension)
{
	_Extension = extension;
}


/*
    @MethodDesc
        
          Sets Name

    @MethodNotes
        
        <???>
*/

void StreamSpecManipulator::SetName(const std::string& name)
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

        Compares two StreamSpecManipulator objects
    
    @ReturnDesc
    
        -1 if *this falls into sequence before aStreamSpecManipulator
        0 if the two objects are equal.
        +1 if *this falls into sequence after aStreamSpecManipulator
    
    @MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  StreamSpecManipulator::CompareTo(const StreamSpecManipulator& streamSpecManipulator)
{
    #pragma message(__FILE__ "(1) :"  "StreamSpecManipulator::CompareTo needs to be implemented or removed")

    return(0);
}

/*
    @MethodDesc

        Checks two StreamSpecManipulator objects for equality
    
    @ReturnDesc
    
        true if the two objects are equal false if they are not
    
    @MethodNotes
    
        Explain the equality criteria.
*/    

bool StreamSpecManipulator::operator ==(const StreamSpecManipulator& streamSpecManipulator)
{
    return(CompareTo(streamSpecManipulator) == 0);
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

StreamSpecManipulator& StreamSpecManipulator::operator =(const StreamSpecManipulator& streamSpecManipulator)
{
    if ( this == &streamSpecManipulator )
    {
        return(*this);
    }

    _FolderSpec = streamSpecManipulator._FolderSpec;
    _Canonical = streamSpecManipulator._Canonical;
    _Root = streamSpecManipulator._Root;
    _Extension = streamSpecManipulator._Extension;
    _Name = streamSpecManipulator._Name;

    return(*this);
}
