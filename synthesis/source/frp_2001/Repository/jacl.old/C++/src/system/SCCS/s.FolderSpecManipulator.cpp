h52530
s 00439/00000/00000
d D 1.1 99/11/17 08:47:04 jmochel 2 1
cC
cK11850
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:00 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/FolderSpecManipulator.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43874
cPC++/src/system/FolderSpecManipulator.cpp
cRe8d3ed3f5cb6bb65
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
    @doc

    .Contains: FolderSpecManipulator Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("FolderSpecManipulator.cpp : Check that all Exceptions are thrown.")
#pragma message("FolderSpecManipulator.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("FolderSpecManipulator.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("FolderSpecManipulator.cpp : Check local variable and parameter naming")
#pragma message("FolderSpecManipulator.cpp : Check Pre and Postconditions")
#pragma message("FolderSpecManipulator.cpp : Lint")
#pragma message("FolderSpecManipulator.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "FolderSpecManipulator.hpp"




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

FolderSpecManipulator::FolderSpecManipulator()
{
	SetDefaults();
}


/*
    @MethodDesc
        
          Copy constructor

    @MethodNotes
        
        <???>
*/

FolderSpecManipulator::FolderSpecManipulator(const FolderSpecManipulator& folderSpecManipulator)
{
    _System = folderSpecManipulator._System;
    _Volume = folderSpecManipulator._Volume;
    _Canonical = folderSpecManipulator._Canonical;
    _Path = folderSpecManipulator._Path;
    _Name = folderSpecManipulator._Name;

}

/*
    @MethodDesc
        
          Constructor

    @MethodNotes
        
        <???>

    @Parm        <???>

*/

FolderSpecManipulator::FolderSpecManipulator(const FolderSpec& spec)
{
    char drive[_MAX_DRIVE];
    char dir[_MAX_DIR];
    char fname[_MAX_FNAME];
    char ext[_MAX_EXT];

    _Canonical = spec;

    _splitpath( spec.GetName().c_str(), drive, dir, fname, ext );

    _Volume = VolumeSpec(drive);
    _Path = dir;
    _Name = fname;
}

/*
    @MethodDesc
        
          Destructor

    @MethodNotes
        
        <???>

*/


FolderSpecManipulator::~FolderSpecManipulator()
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

void FolderSpecManipulator::SetDefaults(void)
{
    _System = SystemSpec(std::string(""));
    _Volume = VolumeSpec(std::string(""));
    _Canonical = FolderSpec(std::string(""));
    _Path = "";
    _Name = "";
}


/*
    ===============================================================================

                                    Accessors

    ===============================================================================

*/


/*
    @MethodDesc
        
          Gets System

    @MethodNotes
        
        <???>

*/

const SystemSpec& FolderSpecManipulator::GetSystem(void) const
{
    return(_System);
}


/*
    @MethodDesc
        
          Gets Volume

    @MethodNotes
        
        <???>

*/

const VolumeSpec& FolderSpecManipulator::GetVolume(void) const
{
    return(_Volume);
}


/*
    @MethodDesc
        
          Gets Canonical

    @MethodNotes
        
        <???>

*/

const FolderSpec& FolderSpecManipulator::GetCanonical(void) const
{
    return(_Canonical);
}


/*
    @MethodDesc
        
          Gets Path

    @MethodNotes
        
        <???>

*/

const std::string& FolderSpecManipulator::GetPath(void) const
{
    return(_Path);
}


/*
    @MethodDesc
        
          Gets Name

    @MethodNotes
        
        <???>

*/

const std::string& FolderSpecManipulator::GetName(void) const
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
        
          Sets System

    @MethodNotes
        
        <???>
*/

void FolderSpecManipulator::SetSystem(const SystemSpec& system)
{
	_System = system;
}


/*
    @MethodDesc
        
          Sets Volume

    @MethodNotes
        
        <???>
*/

void FolderSpecManipulator::SetVolume(const VolumeSpec& volume)
{
	_Volume = volume;
}


/*
    @MethodDesc
        
          Sets Canonical

    @MethodNotes
        
        <???>
*/

void FolderSpecManipulator::SetCanonical(const FolderSpec& canonical)
{
	_Canonical = canonical;
}


/*
    @MethodDesc
        
          Sets Path

    @MethodNotes
        
        <???>
*/

void FolderSpecManipulator::SetPath(const std::string& path)
{
	_Path = path;
}


/*
    @MethodDesc
        
          Sets Name

    @MethodNotes
        
        <???>
*/

void FolderSpecManipulator::SetName(const std::string& name)
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

        Compares two FolderSpecManipulator objects
    
    @ReturnDesc
    
        -1 if *this falls into sequence before aFolderSpecManipulator
        0 if the two objects are equal.
        +1 if *this falls into sequence after aFolderSpecManipulator
    
    @MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  FolderSpecManipulator::CompareTo(const FolderSpecManipulator& folderSpecManipulator)
{
    #pragma message(__FILE__ "(1) :"  "FolderSpecManipulator::CompareTo needs to be implemented or removed")

    return(0);
}

/*
    @MethodDesc

        Checks two FolderSpecManipulator objects for equality
    
    @ReturnDesc
    
        true if the two objects are equal false if they are not
    
    @MethodNotes
    
        Explain the equality criteria.
*/    

bool FolderSpecManipulator::operator ==(const FolderSpecManipulator& folderSpecManipulator)
{
    return(CompareTo(folderSpecManipulator) == 0);
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

FolderSpecManipulator& FolderSpecManipulator::operator =(const FolderSpecManipulator& folderSpecManipulator)
{
    if ( this == &folderSpecManipulator )
    {
        return(*this);
    }

    _System = folderSpecManipulator._System;
    _Volume = folderSpecManipulator._Volume;
    _Canonical = folderSpecManipulator._Canonical;
    _Path = folderSpecManipulator._Path;
    _Name = folderSpecManipulator._Name;

    return(*this);
}
E 2
I 1
E 1
