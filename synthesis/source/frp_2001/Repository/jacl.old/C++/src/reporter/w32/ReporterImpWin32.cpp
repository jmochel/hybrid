/*
    .Contains: Win32 Reporter Implementations

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

#include <iostream>

#include <windows.h>

#include "w32/ReporterImpWin32.hpp"

Win32LogReporterImp::Win32LogReporterImp(ReporterCfg& Cfg) : ReporterImp(Cfg)
{
}

Win32LogReporterImp::~Win32LogReporterImp(void)
{
}

void Win32LogReporterImp::Emit(Rprtr::MsgType MsgType, std::string& Msg)
{
}

void Win32LogReporterImp::Init(Rprtr::Registry& Registry, AppInfo& anAppInfo)
{
}

Win32ConsoleReporterImp::Win32ConsoleReporterImp(ReporterCfg& Cfg) : ReporterImp(Cfg)
{
}

Win32ConsoleReporterImp::~Win32ConsoleReporterImp(void)
{
}

void Win32ConsoleReporterImp::Emit(Rprtr::MsgType MsgType, std::string& Msg)
{
    cout << Msg.c_str();
}

void Win32ConsoleReporterImp::Init(Rprtr::Registry& Registry, AppInfo& anAppInfo)
{
}
