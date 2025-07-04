#ifndef REPORTERIMPWINNT_HPP
#define REPORTERIMPWINNT_HPP

/*
    .Contains WinNTReporterImp

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

// Standard Library Headers

#include <cstdlib>

#include <windows.h>

// Local Library Headers

#include "Types.hpp"

// Local Headers

#include "ReporterImp.hpp"

/*
    .ClassDesc

        The WinNT Logging implementation of a reporter class.

    .ClassNotes

        At the moment this is an empty class since there is no system level
        logging facility for Windows 95 and I have not supplied a substitute yet.
*/

class WinNTLogReporterImp : public ReporterImp
{
    public:

        // Constructors

        WinNTLogReporterImp(ReporterCfg& Cfg);

        // Destructor

        ~WinNTLogReporterImp(void);

        // Other

        virtual void    Emit(Rprtr::MsgType MsgType, std::string& Msg);
        virtual void    Init(Rprtr::Registry& Registry, AppInfo& anAppInfo);

    private:

        // Assignment 

        WinNTLogReporterImp& operator = (const WinNTLogReporterImp& WinNTReporterImp);

    private:

        HANDLE  _EventSrc;      // The destination of the logged messages
};

/*
    .ClassDesc

        The WinNT Console implementation of a reporter class
*/

class WinNTConsoleReporterImp : public ReporterImp
{
    public:

        // Constructors

        WinNTConsoleReporterImp(ReporterCfg& Cfg);

        // Destructor

        ~WinNTConsoleReporterImp(void);

        // Other

        virtual void    Emit(Rprtr::MsgType MsgType, std::string& Msg);
        virtual void    Init(Rprtr::Registry& Registry, AppInfo& anAppInfo);

    private:

        // Assignment 

        WinNTConsoleReporterImp& operator = (const WinNTConsoleReporterImp& WinNTReporterImp);

};

#endif  // REPORTERIMPWINNT_HPP
