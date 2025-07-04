h14027
s 00345/00000/00000
d D 1.1 99/11/17 12:46:45 jmochel 2 1
cC
cK38130
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:42 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/callbackmanager/SwitchableSigCBMgr.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45312
cPC++/src/callbackmanager/SwitchableSigCBMgr.cpp
cR2f93d7865cb6ba86
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

    .Contains: SwitchableSigCBMgr Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/


#pragma message("SwitchableSigCBMgr.cpp : Check that all Exceptions are thrown.")
#pragma message("SwitchableSigCBMgr.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("SwitchableSigCBMgr.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("SwitchableSigCBMgr.cpp : Check local variable and parameter naming")
#pragma message("SwitchableSigCBMgr.cpp : Check Pre and Postconditions")
#pragma message("SwitchableSigCBMgr.cpp : Lint")
#pragma message("SwitchableSigCBMgr.cpp : Profile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "SwitchableSigCBMgr.hpp"


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

SwitchableSigCBMgr::SwitchableSigCBMgr()
{
	SetDefaults();
}


/*
    @MethodDesc
        
          Copy constructor

    @MethodNotes
        
        <???>
*/

SwitchableSigCBMgr::SwitchableSigCBMgr(const SwitchableSigCBMgr& switchableSigCBMgr)
{
    _Owner = switchableSigCBMgr._Owner;
    _Callbacks = switchableSigCBMgr._Callbacks;

}

/*
    @MethodDesc
        
          Constructor

    @Parm        <???>
    @Parm        <???>

    @MethodNotes
        
        <???>

*/

SwitchableSigCBMgr::SwitchableSigCBMgr(
OwnerT owner,
multimap callbacks,
        )
{
    Precondition(owner != 0);
    Precondition(callbacks != 0);
    _Owner = owner;
    _Callbacks = callbacks;
}

/*
    @MethodDesc
        
          Destructor

    @MethodNotes
        
        <???>

*/


SwitchableSigCBMgr::~SwitchableSigCBMgr()
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

void SwitchableSigCBMgr::SetDefaults(void)
{
    _Owner = 0;
    _Callbacks = 0;
}


/*
    ===============================================================================

                                    Accessors

    ===============================================================================

*/


/*
    @MethodDesc
        
          Gets Owner

    @MethodNotes
        
        <???>

*/

const OwnerT SwitchableSigCBMgr::GetOwner(void) const
{
    return(_Owner);
}


/*
    @MethodDesc
        
          Gets Callbacks

    @MethodNotes
        
        <???>

*/

const multimap SwitchableSigCBMgr::GetCallbacks(void) const
{
    return(_Callbacks);
}


/*
    ===============================================================================

                                    Mutators

    ===============================================================================

*/


/*
    @MethodDesc
        
          Sets Owner

    @MethodNotes
        
        <???>
*/

void SwitchableSigCBMgr::SetOwner(const OwnerT& owner)
{
	_Owner = owner;
}


/*
    @MethodDesc
        
          Sets Callbacks

    @MethodNotes
        
        <???>
*/

void SwitchableSigCBMgr::SetCallbacks(const multimap& callbacks)
{
	_Callbacks = callbacks;
}


/*
    ===============================================================================

                                    Logic Operators

    ===============================================================================

*/


/*
    @MethodDesc

        Compares two SwitchableSigCBMgr objects
    
    @ReturnDesc
    
        -1 if *this falls into sequence before aSwitchableSigCBMgr
        0 if the two objects are equal.
        +1 if *this falls into sequence after aSwitchableSigCBMgr
    
    @MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  SwitchableSigCBMgr::CompareTo(const SwitchableSigCBMgr& switchableSigCBMgr)
const
{
    #pragma message(__FILE__ "(1) :"  "SwitchableSigCBMgr::CompareTo needs to be implemented or removed")

    return(0);
}

/*
    @MethodDesc

        Checks two SwitchableSigCBMgr objects for equality
    
    @ReturnDesc
    
        true if the two objects are equal false if they are not
    
    @MethodNotes
    
        Explain the equality criteria.
*/    

bool  SwitchableSigCBMgr::operator ==(const SwitchableSigCBMgr& switchableSigCBMgr) const
{
    return(CompareTo(switchableSigCBMgr) == 0);
}

/*
    @MethodDesc

        Checks two SwitchableSigCBMgr objects for the less than relationship
    
    @ReturnDesc
    
        true if the the left hand object is less than the right
    
    @MethodNotes
    
        Explain the equality criteria.
*/    

bool  SwitchableSigCBMgr::operator <(const SwitchableSigCBMgr& switchableSigCBMgr) const
{
    return(CompareTo(switchableSigCBMgr) == -1);
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

SwitchableSigCBMgr& SwitchableSigCBMgr::operator =(const SwitchableSigCBMgr& switchableSigCBMgr)
{
    if ( this == &switchableSigCBMgr )
    {
        return(*this);
    }

    _Owner = switchableSigCBMgr._Owner;
    _Callbacks = switchableSigCBMgr._Callbacks;

    return(*this);
}
E 2
I 1
E 1
