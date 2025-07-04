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




