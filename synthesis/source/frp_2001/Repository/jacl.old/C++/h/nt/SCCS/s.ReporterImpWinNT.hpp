h54376
s 00100/00000/00000
d D 1.1 99/11/17 12:45:30 jmochel 2 1
cC
cK16258
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:26 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/nt/ReporterImpWinNT.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45305
cPC++/h/nt/ReporterImpWinNT.hpp
cR2f93d79b5cb6ba86
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

#endif  // REPORTERIMPWIN32_HPP
E 2
I 1
E 1
