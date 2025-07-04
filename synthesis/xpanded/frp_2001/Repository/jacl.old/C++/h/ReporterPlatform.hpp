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
