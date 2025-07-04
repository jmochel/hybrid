
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

