


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

