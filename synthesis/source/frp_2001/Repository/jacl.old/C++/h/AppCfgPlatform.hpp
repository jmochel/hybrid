

#ifndef APPCFGPLATFORM_HPP
#define APPCFGPLATFORM_HPP

/*
    @doc

    .Contains: AppCfg Platform Specific Initialisation Function Definitions

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/

#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit 


#pragma message("AppCfgPlatform.hpp : Check API Naming")
#pragma message("AppCfgPlatform.hpp : Check API Types")
#pragma message("AppCfgPlatform.hpp : Check API Returns")
#pragma message("AppCfgPlatform.hpp : Check API Exceptions")
#pragma message("AppCfgPlatform.hpp : Check API Exception Specifications")
#pragma message("AppCfgPlatform.hpp : Check Comments")


#ifndef PLATFORM_HPP
#include "Platform.hpp"
#endif



#if ( JACL_OS == JACL_WINNT )
#pragma message("Info: JACL AppCfg is being configured for use on WinNT platform")
extern AppCfgImp* WinNTAppCfgInitFxn(AppInfo& cfg);
const AppCfgInitFxn DefAppCfgInitFxn = WinNTAppCfgInitFxn;
#elif ( JACL_OS == JACL_WIN32 )
#pragma message("Info: JACL AppCfg is being configured for use on Win32 platform")
extern AppCfgImp* Win32AppCfgInitFxn(AppInfo& cfg);
const AppCfgInitFxn DefAppCfgInitFxn = Win32AppCfgInitFxn;
#elif ( JACL_OS == JACL_GENERICUNIX )
#pragma message("Info: JACL AppCfg is being configured for use on Generic UNIX platforms")
extern AppCfgImp* UnixAppCfgInitFxn(AppInfo& cfg);
const AppCfgInitFxn DefAppCfgInitFxn = UnixAppCfgInitFxn;
#else
#	error "There is no DefAppCfgInitFxn defined for the AppCfgs classes on this platform"
#endif

#endif  // APPCFGPLATFORM_HPP
