h00811
s 00057/00000/00000
d D 1.1 99/11/17 12:49:09 jmochel 2 1
cC
cK25986
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:49:05 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/reporter/w32/ReporterImpWin32.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45326
cPC++/src/reporter/w32/ReporterImpWin32.cpp
cR2f93d7de5cb6ba86
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
E 2
I 1
E 1
