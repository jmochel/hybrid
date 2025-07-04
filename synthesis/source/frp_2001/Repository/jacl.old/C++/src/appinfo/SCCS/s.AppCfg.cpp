h28967
s 00375/00000/00000
d D 1.1 99/11/17 12:46:06 jmochel 2 1
cC
cK57033
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:03 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/appinfo/AppCfg.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45308
cPC++/src/appinfo/AppCfg.cpp
cR2f93d7915cb6ba86
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

    .Contains: AppCfg Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("AppCfg.cpp : Check that all Exceptions are thrown.")
#pragma message("AppCfg.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("AppCfg.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("AppCfg.cpp : Check local variable and parameter naming")
#pragma message("AppCfg.cpp : Check Pre and Postconditions")
#pragma message("AppCfg.cpp : Lint")
#pragma message("AppCfg.cpp : Profile")



#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#ifndef APPCFG_HPP
#include "AppCfg.hpp"
#endif



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


AppCfg::AppCfg()
{
	SetDefaults();
}

/*
    @MethodDesc
        
          Copy constructor

    @MethodNotes
        
        <???>
*/

AppCfg::AppCfg(const AppCfg& appCfg)
{

    _Info = appCfg._Info;
    _KeyAndValue = appCfg._KeyAndValue;
    _Value = appCfg._Value;
    _KeyAndValueMap = appCfg._KeyAndValueMap;

}

/*
    @MethodDesc
        
          Constructor

    @Parm        <???>
    @Parm        <???>
    @Parm        <???>
    @Parm        <???>

    @MethodNotes
        
        <???>
*/

AppCfg::AppCfg(AppInfo& info, AppCfgInitFxn initFxn)
{
    try 
    {
    	SetDefaults();

        _InitFxn = initFxn;
        _Info = info;
        _Imp = initFxn(info);
    }
    catch (...)
    {
        throw;
    }
}



/*
    @MethodDesc
        
          Destructor

    @MethodNotes
        
        <???>

*/



AppCfg::~AppCfg()
{
    if ( _Imp != 0 )
    {
        delete(_Imp);
    }
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


void AppCfg::SetDefaults(void)
{
}


/*
    ===============================================================================

                                    Accessors

    ===============================================================================

*/


/*
    @MethodDesc
        
          Gets Info

    @MethodNotes
        
        <???>

*/

const AppInfo& AppCfg::GetInfo(void) const
{
    return(_Info);
}


/*
    @MethodDesc
        
          Gets KeyAndValue

    @MethodNotes
        
        <???>

*/

const string& AppCfg::GetKeyAndValue(void) const
{
    return(_KeyAndValue);
}


/*
    @MethodDesc
        
          Gets Value

    @MethodNotes
        
        <???>

*/

const string& AppCfg::GetValue(void) const
{
    return(_Value);
}

/*
    ===============================================================================

                                    Mutators

    ===============================================================================

*/

/*
    @MethodDesc
        
          Sets Info

    @MethodNotes
        
        <???>
*/

void AppCfg::SetInfo(const AppInfo& Info)
{
	_Info = Info;
}

/*
    @MethodDesc
        
          Sets KeyAndValue

    @MethodNotes
        
        <???>
*/

void AppCfg::SetKeyAndValue(const string& KeyAndValue)
{
	_KeyAndValue = KeyAndValue;
}

/*
    @MethodDesc
        
          Sets Value

    @MethodNotes
        
        <???>
*/

void AppCfg::SetValue(const string& Value)
{
	_Value = Value;
}

/*
    ===============================================================================

                                    Logic Operators

    ===============================================================================

*/

/*
    @MethodDesc

        Compares two AppCfg objects
    
    @ReturnDesc
    
        -1 if *this falls into sequence before aAppCfg
        0 if the two objects are equal.
        +1 if *this falls into sequence after aAppCfg
    
    @MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  AppCfg::CompareTo(const AppCfg& appCfg)
{
    #pragma message(__FILE__ "(1) :"  "AppCfg::CompareTo needs to be implemented or removed")

    return(0);
}

/*
    @MethodDesc

        Checks two AppCfg objects for equality
    
    @ReturnDesc
    
        true if the two objects are equal false if they are not
    
    @MethodNotes
    
        Explain the equality criteria.
*/

bool AppCfg::operator ==(const AppCfg& appCfg)
{
    return(CompareTo(appCfg) == 0);
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

AppCfg& AppCfg::operator =(const AppCfg& appCfg)
{
    if ( this == &appCfg )
    {
        return(*this);
    }

    _Info = appCfg._Info;
    _KeyAndValue = appCfg._KeyAndValue;
    _Value = appCfg._Value;
    _KeyAndValueMap = appCfg._KeyAndValueMap;

    return(*this);
}

E 2
I 1
E 1
