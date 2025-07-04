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




