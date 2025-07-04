/*
    @doc 

    .Author: Jim JM

    .Date: 12.11.94 

    .Contains   Reporter is a message reporting class

	.Copyright

		This code is in the public domain

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $

*/

#include <cstdarg>
#include <cstdlib>

#ifndef BASICSTRINGUTIL_HPP
#include "BasicStringUtil.hpp"
#endif

#include "Reporter.hpp"

// Static Initialization

Rprtr::Registry  Reporter::_MiniRegistry;      // Maps keys and values for bootstrap configuration

/*
    @MethodDesc    Constructor
*/

Reporter::Reporter(const AppInfo& anAppInfo, ReporterInitFxn anInitFxn)
{
    _AppInfo = anAppInfo;
    _InitFxn = anInitFxn;

    try 
    {
        // Set the default mappings

        Register(Rprtr::Error, ReporterCfg(Rprtr::Console | Rprtr::Log) );
        Register(Rprtr::Warning, ReporterCfg(Rprtr::Console | Rprtr::Log) );
        Register(Rprtr::Status, ReporterCfg(Rprtr::Console | Rprtr::Log) );
        Register(Rprtr::Banner, ReporterCfg(Rprtr::Console | Rprtr::Log) );

        _LogReporter = anInitFxn(ReporterCfg(Rprtr::Log));
        _LogReporter->Init(_MiniRegistry, _AppInfo);

        _ConsoleReporter = anInitFxn(ReporterCfg(Rprtr::Console));
        _ConsoleReporter->Init(_MiniRegistry, _AppInfo);
    }
    catch (...)
    {
        throw;
    }
}

// Destructor

Reporter::~Reporter(void)
{
    if ( _LogReporter != 0 )
    {
        delete(_LogReporter);
    }

    if ( _ConsoleReporter != 0 )
    {
        delete(_ConsoleReporter);
    }
}

void Reporter::Register(Rprtr::MsgType aMsgType, ReporterCfg& Cfg)
{
    try 
    {
        _CfgMap.insert(map<Rprtr::MsgType, ReporterCfg>::value_type(aMsgType, Cfg));
    }
    catch (...)
    {
        throw;
    }
}

void Reporter::Emit(Rprtr::MsgType Type, const char* FormatStr, ...)
{
    va_list ArgList;

    try 
    {
        va_start(ArgList, FormatStr);

        std::string TempStr = Format(FormatStr, ArgList);
        
        if ( _CfgMap[Type].GetServiceMask() & Rprtr::Log )
        {
            _LogReporter->Emit(Type, TempStr);
        }

        if ( _CfgMap[Type].GetServiceMask() & Rprtr::Console )
        {
            _ConsoleReporter->Emit(Type, TempStr);
        }

        va_end(ArgList);
    }
    catch (...)
    {
        throw;
    }
}

void Reporter::Emit(Rprtr::MsgType Type, std::string& FormatStr, ...)
{
    va_list ArgList;

    try 
    {
        va_start(ArgList, FormatStr);

        std::string TempStr = Format(FormatStr, ArgList);
        
        if ( _CfgMap[Type].GetServiceMask() & Rprtr::Log )
        {
            _LogReporter->Emit(Type, TempStr);
        }

        if ( _CfgMap[Type].GetServiceMask() & Rprtr::Console )
        {
            _ConsoleReporter->Emit(Type, TempStr);
        }

        va_end(ArgList);
    }
    catch (...)
    {
        throw;
    }
}

void Reporter::Emit(Exception& error)
{
    try 
    {
        std::string TempStr(error.what());
        
        if ( _CfgMap[Rprtr::Error].GetServiceMask() & Rprtr::Log )
        {
            _LogReporter->Emit(Rprtr::Error, TempStr);
        }

        if ( _CfgMap[Rprtr::Error].GetServiceMask() & Rprtr::Console )
        {
            _ConsoleReporter->Emit(Rprtr::Error, TempStr);
        }
    }
    catch (...)
    {
        throw;
    }
}


void Reporter::Set(const char* Key, const char*  Value)
{
	try 
	{
        _MiniRegistry.insert(Rprtr::Registry::value_type(std::string(Key), std::string(Value)));        
    }
	catch (...)
	{
		throw;
	}
}


void Reporter::Set(std::string& Key, std::string& Value)
{
	try 
	{
        _MiniRegistry.insert(Rprtr::Registry::value_type(Key, Value));
    }
	catch (...)
	{
		throw;
	}
}

