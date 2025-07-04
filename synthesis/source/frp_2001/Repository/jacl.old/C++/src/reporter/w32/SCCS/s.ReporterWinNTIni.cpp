h27188
s 00044/00000/00000
d D 1.1 99/11/17 12:49:19 jmochel 2 1
cC
cK52270
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:49:16 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/reporter/w32/ReporterWinNTIni.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45327
cPC++/src/reporter/w32/ReporterWinNTIni.cpp
cR2f93d7db5cb6ba86
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
    .Contains WinNT Stream Initialization Function

    .Author Jim Jackl-Mochel

    .Date 02.07.96

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#include "w32/ReporterImpWinNT.hpp"

ReporterImp* WinNTReporterInitFxn(ReporterCfg& Cfg)
{
    ReporterImp*  Imp;

    try
    {
        if ( Cfg.GetServiceMask() & Rprtr::Console )
        {
            Imp  = new WinNTConsoleReporterImp(Cfg);
        }
        else if ( Cfg.GetServiceMask() & Rprtr::Log )
        {
            Imp  = new WinNTLogReporterImp(Cfg);
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
