h53154
s 00100/00000/00000
d D 1.1 99/11/17 12:45:44 jmochel 2 1
cC
cK15221
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:41 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/w32/ReporterImpWin32.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45306
cPC++/h/w32/ReporterImpWin32.hpp
cR2f93d7975cb6ba86
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
#ifndef REPORTERIMPWIN32_HPP
#define REPORTERIMPWIN32_HPP

/*
    .Contains Win32ReporterImp

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

        The Win32 Logging implementation of a reporter class.

    .ClassNotes

        At the moment this is an empty class since there is no system level
        logging facility for Windows 95 and I have not supplied a substitute yet.
*/

class Win32LogReporterImp : public ReporterImp
{
    public:

        // Constructors

        Win32LogReporterImp(ReporterCfg& Cfg);

        // Destructor

        ~Win32LogReporterImp(void);

        // Other

        virtual void    Emit(Rprtr::MsgType MsgType, std::string& Msg);
        virtual void    Init(Rprtr::Registry& Registry, AppInfo& anAppInfo);

    private:

        // Assignment 

        Win32LogReporterImp& operator = (const Win32LogReporterImp& Win32ReporterImp);
};

/*
    .ClassDesc

        The Win32 Console implementation of a reporter class
*/

class Win32ConsoleReporterImp : public ReporterImp
{
    public:

        // Constructors

        Win32ConsoleReporterImp(ReporterCfg& Cfg);

        // Destructor

        ~Win32ConsoleReporterImp(void);

        // Other

        virtual void    Emit(Rprtr::MsgType MsgType, std::string& Msg);
        virtual void    Init(Rprtr::Registry& Registry, AppInfo& anAppInfo);

    private:

        // Assignment 

        Win32ConsoleReporterImp& operator = (const Win32ConsoleReporterImp& Win32ReporterImp);

};

#endif  // REPORTERIMPWIN32_HPP
E 2
I 1
E 1
