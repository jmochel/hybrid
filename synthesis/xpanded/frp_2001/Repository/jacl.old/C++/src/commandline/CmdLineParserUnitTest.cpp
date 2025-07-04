/*
    @doc

    .Contains: CmdLineParser Unit Test

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#include <cstdlib>

#include "Reporter.hpp"
#include "CmdLineParser.hpp"

Reporter    GlobalReporter(AppInfo(std::string("cmdlineparserunittest"),std::string("1.00.00")));

/*
    @ProcDesc 

        Unit Test Main for Command Line Parsing
*/

int main( int argCnt, char* argValues[])
{
    char* argV[] = { "BogusnameProgramName", "/ifile=bush", "/ofile=bush", "/debug=7", "/report=1" };

    CmdLineParser     CmdLine(5, argV);
    
    // Register validation info

    CmdLine.Register(std::string("ifile"), CmdLineParser::AlphaNumeric, CmdLineParser::Required, std::string("Desc"), StdValidation);
    CmdLine.Register(std::string("ofile"), CmdLineParser::AlphaNumeric, CmdLineParser::Required, std::string("Desc"), StdValidation);
    CmdLine.Register(std::string("debug"), CmdLineParser::Numeric, CmdLineParser::Required, std::string("Desc"), StdValidation);
    CmdLine.Register(std::string("report"), CmdLineParser::Boolean, CmdLineParser::Required, std::string("Desc"), StdValidation);

    // Validate command line

    try 
    {
        CmdLine.Validate();
    }
    catch (CmdLineParser::InvalidArgumentError& error) {
        Report(error);
        return(EXIT_FAILURE);
    }
    catch (CmdLineParser::TooFewArgumentsError& error) {
        Report(error);
        return(EXIT_FAILURE);
    }
    catch (CmdLineParser::TooManyArgumentsError& error) {
        Report(error);
        return(EXIT_FAILURE);
    }
    catch(...)
    {
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}
