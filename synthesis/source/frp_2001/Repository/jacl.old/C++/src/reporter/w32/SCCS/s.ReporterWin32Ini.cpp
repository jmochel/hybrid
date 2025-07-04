h26765
s 00044/00000/00000
d D 1.1 99/11/17 12:49:16 jmochel 2 1
cC
cK51965
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:49:12 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/reporter/w32/ReporterWin32Ini.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45327
cPC++/src/reporter/w32/ReporterWin32Ini.cpp
cR2f93d7dc5cb6ba86
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
    .Contains Win32 Stream Initialization Function

    .Author Jim Jackl-Mochel

    .Date 02.07.96

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#include "w32/ReporterImpWin32.hpp"

ReporterImp* Win32ReporterInitFxn(ReporterCfg& Cfg)
{
    ReporterImp*  Imp;

    try
    {
        if ( Cfg.GetServiceMask() & Rprtr::Console )
        {
            Imp  = new Win32ConsoleReporterImp(Cfg);
        }
        else if ( Cfg.GetServiceMask() & Rprtr::Log )
        {
            Imp  = new Win32LogReporterImp(Cfg);
        }
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
