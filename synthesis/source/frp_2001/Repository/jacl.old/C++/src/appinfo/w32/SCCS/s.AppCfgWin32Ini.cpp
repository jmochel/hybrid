h18798
s 00060/00000/00000
d D 1.1 99/11/17 12:46:28 jmochel 2 1
cC
cK44905
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:24 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/appinfo/w32/AppCfgWin32Ini.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45310
cPC++/src/appinfo/w32/AppCfgWin32Ini.cpp
cR2f93d78b5cb6ba86
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

    .Contains: AppCfg Win32 Platform Specific Initialisation Function 

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

#pragma message("AppCfgWin32Ini.cpp : Check that all Exceptions are thrown.")
#pragma message("AppCfgWin32Ini.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("AppCfgWin32Ini.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("AppCfgWin32Ini.cpp : Check local variable and parameter naming")
#pragma message("AppCfgWin32Ini.cpp : Check Pre and Postconditions")
#pragma message("AppCfgWin32Ini.cpp : Lint")
#pragma message("AppCfgWin32Ini.cpp : PRofile")



#include "w32/AppCfgImpWin32.hpp"

/*
    @ProcDesc

        Creates a platform specific appCfgImp class

    @ProcNotes

        A Factory procedure that creates a platform specific appCfgImp class
        appropriate to the platform and the requested Cfg.
*/    

appCfgImp* Win32appCfgInitFxn(appCfgCfg& cfg)
{
    appCfgImp*  Imp;

    try
    {
        Imp  = new Win32appCfgImp(cfg);
    }
    catch(...)
    {
        throw;
    }

    return(Imp);
}

E 2
I 1
E 1
