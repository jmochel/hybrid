h32273
s 00269/00000/00000
d D 1.1 99/11/17 12:46:10 jmochel 2 1
cC
cK59742
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:06 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/appinfo/AppCfgImp.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45309
cPC++/src/appinfo/AppCfgImp.cpp
cR2f93d7905cb6ba86
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

    .Contains: AppCfgImp Implementation

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
#pragma message("AppCfg.cpp : PRofile")


#ifndef APPCFGIMP_HPP
#include "AppCfgImp.hpp"
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

AppCfgImp::AppCfgImp()
{
	SetDefaults();
}

/*
    @MethodDesc
        
          Copy Constructor

    @MethodNotes
        
        <???>
*/

AppCfgImp::AppCfgImp(const AppCfgImp& appCfgImp)
{

    _Info = appCfgImp._Info;
    _KeyAndValue = appCfgImp._KeyAndValue;
    _Value = appCfgImp._Value;
    _KeyAndValueMap = appCfgImp._KeyAndValueMap;

}

/*
    @MethodDesc
        
          Constructor

    @MethodNotes
        
        <???>

    @Parm        <???>
    @Parm        <???>
    @Parm        <???>
    @Parm        <???>

*/


AppCfgImp::AppCfgImp(AppInfo& info)
{
    try 
    {
    	SetDefaults();
        _Info = info;
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


AppCfgImp::~AppCfgImp()
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

void AppCfgImp::SetDefaults(void)
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

const AppInfo AppCfgImp::GetInfo(void) const
{
    return(_Info);
}


/*
    @MethodDesc
        
          Gets KeyAndValue

    @MethodNotes
        
        <???>

*/

const string AppCfgImp::GetKeyAndValue(void) const
{
    return(_KeyAndValue);
}


/*
    @MethodDesc
        
          Gets Value

    @MethodNotes
        
        <???>

*/

const string AppCfgImp::GetValue(void) const
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

void AppCfgImp::SetInfo(const AppInfo& info)
{
	_Info = info;
}

/*
    @MethodDesc
        
          Sets KeyAndValue

    @MethodNotes
        
        <???>

*/

void AppCfgImp::SetKeyAndValue(const string& KeyAndValue)
{
	_KeyAndValue = KeyAndValue;
}

/*
    @MethodDesc
        
          Sets Value

    @MethodNotes
        
        <???>

*/

void AppCfgImp::SetValue(const string& Value)
{
	_Value = Value;
}

E 2
I 1
E 1
