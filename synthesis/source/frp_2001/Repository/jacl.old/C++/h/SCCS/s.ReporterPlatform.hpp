h13235
s 00040/00000/00000
d D 1.1 99/11/17 08:09:23 jmochel 2 1
cC
cK41047
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:20 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/ReporterPlatform.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43648
cPC++/h/ReporterPlatform.hpp
cRa57008ef5cb6bb60
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
#ifndef REPORTERPLATFORM_HPP
#define REPORTERPLATFORM_HPP

/*
    .Contains Stream Platform Specific Initializations

    .Author Jim Jackl-Mochel

    .Date 02.07.96

    .Copyright  This code is in the public domain.

    Revision Information 
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#ifndef PLATFORM_HPP
#include "Platform.hpp"
#endif

#if ( JACL_OS == JACL_WINNT )
#pragma message("Info: JACL Reporter is being configured for use on WinNT platform")
extern ReporterImp* WinNTReporterInitFxn(ReporterCfg& Cfg);
const ReporterInitFxn DefReporterInitFxn = WinNTReporterInitFxn;
#elif ( JACL_OS == JACL_WIN32 )
#pragma message("Info: JACL Reporter is being configured for use on Win32 platform")
extern ReporterImp* Win32ReporterInitFxn(ReporterCfg& Cfg);
const ReporterInitFxn DefReporterInitFxn = Win32ReporterInitFxn;
#elif ( JACL_OS == JACL_GENERICUNIX )
#pragma message("Info: JACL Reporter is being configured for use on Generic UNIX platforms")
extern ReporterImp* UnixReporterInitFxn(ReporterCfg& Cfg);
const ReporterInitFxn DefReporterInitFxn = UnixReporterInitFxn;
#else
#	error "There is no DefReporterInitFxn defined for the streams classes on this platform"
#endif

#endif  // REPORTERPLATFORM_HPP
E 2
I 1
E 1
