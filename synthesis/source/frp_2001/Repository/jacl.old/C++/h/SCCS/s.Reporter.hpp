h05172
s 00137/00000/00000
d D 1.1 99/11/17 08:09:12 jmochel 2 1
cC
cK34537
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:08 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Reporter.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43647
cPC++/h/Reporter.hpp
cRbe8be67f5cb6bb60
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
#ifndef REPORTER_HPP
#define REPORTER_HPP

/*
    @doc

    .Contains Reporter, a message reporting class and some standard exception handling stuff.

    .Author Jim Jackl-Mochel

    .Date 04.17.92

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit 

#include <map>

#ifndef DATETIME_HPP
#include "Datetime.hpp"
#endif

#ifndef APPINFO_HPP
#include "AppInfo.hpp"
#endif

#ifndef REPORTERIMP_HPP
#include "ReporterImp.hpp"
#endif

// Declarations

typedef ReporterImp* (*ReporterInitFxn)(ReporterCfg& Cfg);

#ifndef REPORTERPLATFORM_HPP
#include "ReporterPlatform.hpp"
#endif

/*
    @ClassDesc

		Reporter is a message reporting class

     
*/

class Reporter
{
    // @Access Public

    public:

        //  @MemberDesc Constructor

        Reporter(const AppInfo& Info, ReporterInitFxn anInitFxn = DefReporterInitFxn);

        //  @MemberDesc Destructor

        virtual ~Reporter(void);

        //  @MemberDesc Registers a level of reporting for a message type.

        void  Register(Rprtr::MsgType aMsgType, ReporterCfg& Cfg);

        //  @MemberDesc Emit a message

        void  Emit(Rprtr::MsgType aMsgType, const char* FormatStr, ...);

        //  @MemberDesc Emit a message

        void  Emit(Rprtr::MsgType aMsgType, std::string& FormatStr, ...);

        //  @MemberDesc Emit an exception

        void  Emit(Exception& error);

        //  @MemberDesc Static configuration

        static void Reporter::Set(const char* Key, const char*  Value);

        //  @MemberDesc Static configuration

        static void Reporter::Set(std::string& Key, std::string& Value);
        
	protected:

        // @MemberDesc Information describing the reporter's current environment.

        AppInfo         _AppInfo;   

        // @MemberDesc Initialization function 

        ReporterInitFxn _InitFxn;   

        // @MemberDesc A convienient date time for date stamping messages.

        DateTime        _DateTime;  

        // @MemberDesc  Maps the index of the message to the configuration for where it should go

        map<Rprtr::MsgType,ReporterCfg>   _CfgMap;        

        // @MemberDesc  The place where the Logged messages go

        ReporterImp*    _LogReporter;       

        //  @MemberDesc  The place where the Console messages go

        ReporterImp*    _ConsoleReporter;

        //   @MemberDesc  Maps keys and values for bootstrap configuration

        static Rprtr::Registry     _MiniRegistry;
};

/*
	General reporting macros
*/

// This defines the standard destination for messages

#define StdGlobalReporter	GlobalReporter

// Error, Status, and Other reporting

#define Report		StdGlobalReporter.Emit

extern Reporter GlobalReporter;

#endif // REPORTER_HPP
E 2
I 1
E 1
