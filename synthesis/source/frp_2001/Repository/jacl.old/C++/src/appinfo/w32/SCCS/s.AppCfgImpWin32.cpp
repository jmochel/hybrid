h04773
s 00067/00000/00000
d D 1.1 99/11/17 12:46:24 jmochel 2 1
cC
cK30871
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:20 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/appinfo/w32/AppCfgImpWin32.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45310
cPC++/src/appinfo/w32/AppCfgImpWin32.cpp
cR2f93d78c5cb6ba86
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

    .Contains: Win32AppCfgImp  Implementations

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit 


#pragma message("AppCfgImpWin32.cpp : Check that all Exceptions are thrown.")
#pragma message("AppCfgImpWin32.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("AppCfgImpWin32.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("AppCfgImpWin32.cpp : Check local variable and parameter naming")
#pragma message("AppCfgImpWin32.cpp : Check Pre and Postconditions")
#pragma message("AppCfgImpWin32.cpp : Lint")
#pragma message("AppCfgImpWin32.cpp : PRofile")


#include <iostream>

#include <windows.h>

#include "w32/AppCfgImpWin32.hpp"

/*
    @ProcDesc

        <???>

    @ProcNotes

        <???>
*/

Win32AppCfgImp::Win32AppCfgImp(AppCfgCfg& cfg) : AppCfgImp(Cfg)
{
}

/*
    @ProcDesc

        <???>

    @ProcNotes

        <???>
*/

Win32AppCfgImp::~Win32AppCfgImp(void)
{
}

E 2
I 1
E 1
