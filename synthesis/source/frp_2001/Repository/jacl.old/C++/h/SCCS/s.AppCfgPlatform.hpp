h47213
s 00057/00000/00000
d D 1.1 99/11/17 08:07:42 jmochel 2 1
cC
cK09839
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:07:38 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/AppCfgPlatform.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43638
cPC++/h/AppCfgPlatform.hpp
cR68faeadf5cb6bb7f
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
E 2
I 1
E 1
