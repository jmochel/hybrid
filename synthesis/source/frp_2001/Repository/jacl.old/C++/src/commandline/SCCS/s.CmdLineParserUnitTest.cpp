h51584
s 00069/00000/00000
d D 1.1 99/11/17 12:47:07 jmochel 2 1
cC
cK10182
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:47:03 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/commandline/CmdLineParserUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45314
cPC++/src/commandline/CmdLineParserUnitTest.cpp
cR2f93d7805cb6ba86
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
E 2
I 1
E 1
