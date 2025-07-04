h21931
s 00140/00000/00000
d D 1.1 99/11/17 12:49:12 jmochel 2 1
cC
cK47006
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:49:09 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/reporter/w32/ReporterImpWinNT.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45327
cPC++/src/reporter/w32/ReporterImpWinNT.cpp
cR2f93d7dd5cb6ba86
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
    .Contains: WinNT Reporter Implementations

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

#include "w32/ReporterImpWinNT.hpp"

WinNTLogReporterImp::WinNTLogReporterImp(ReporterCfg& Cfg) : ReporterImp(Cfg)
{
    _EventSrc = NULL;
}

WinNTLogReporterImp::~WinNTLogReporterImp(void)
{
    CloseHandle(_EventSrc);
}

void WinNTLogReporterImp::Emit(Rprtr::MsgType MsgType, std::string& Msg)
{
    try 
    {
        BOOL LoggingWasSuccessful;
        WORD EventType;
        char MsgBfr[2048];
        char*   MsgBfrArray[2];

        MsgBfrArray[0] = &(MsgBfr[0]);
        MsgBfrArray[1] = 0;

        // Determine the event type
        
        switch (MsgType )
        {
        case Rprtr::Error: 
            EventType = EVENTLOG_ERROR_TYPE;
            break;

        case Rprtr::Warning: 
            EventType = EVENTLOG_WARNING_TYPE;
            break;

        case Rprtr::Status: 
            EventType = EVENTLOG_INFORMATION_TYPE;
            break;

        case Rprtr::Banner: 
            EventType = EVENTLOG_AUDIT_SUCCESS;
            break;

        default:
            break;
        }

        // Copy the message into the temp buffer

        strncpy(MsgBfrArray[0], Msg.c_str(), Msg.size());

        // Pitch the event

        LoggingWasSuccessful = ReportEvent(_EventSrc, EventType, 0, 0, NULL, 1, 0, (const char**) &MsgBfrArray[0], NULL);

        // Throw an exception even though it will not be handled !
        // This will force the use of the terminate function.

        if ( LoggingWasSuccessful == false )
        {
            throw(ExternalError(std::string("$ReporterImpWinNT1$ Unable to issue a message to the event log")));
        }
    }
    catch (...)
    {
        throw;
    }
}

void WinNTLogReporterImp::Init(Rprtr::Registry& Registry, AppInfo& anAppInfo)
{
    Rprtr::Registry::iterator KeyAndValue;
    std::string  LogFile;

    try 
    {
        // Get the LogFile that generates the EventSource location

        LogFile = Registry[std::string("w32.logfile")];

        if (LogFile.empty() == true)
        {
            throw(StartupInputError(std::string("$WinNTConsoleReporterImp2$ Unable to find the MiniRegistry entry for w32.logfile")));
        }
        
        // Now open the Event source

        _EventSrc = RegisterEventSource(NULL, LogFile.c_str());

        if ( _EventSrc == NULL )
        {
            throw(StartupInputError(std::string("$WinNTConsoleReporterImp3$ Unable to open the logfile")));
        }
    }
    catch (...)
    {
        throw;
    }
}

WinNTConsoleReporterImp::WinNTConsoleReporterImp(ReporterCfg& Cfg) : ReporterImp(Cfg)
{
}

WinNTConsoleReporterImp::~WinNTConsoleReporterImp(void)
{
}

void WinNTConsoleReporterImp::Emit(Rprtr::MsgType MsgType, std::string& Msg)
{
    cout << Msg.c_str();
}

void WinNTConsoleReporterImp::Init(Rprtr::Registry& Registry, AppInfo& anAppInfo)
{
}
E 2
I 1
E 1
