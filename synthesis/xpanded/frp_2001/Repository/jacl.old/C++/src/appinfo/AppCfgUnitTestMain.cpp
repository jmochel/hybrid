
/*
    @doc

    .Contains: AppCfg Unit Test Main 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("AppCfgUnitTestMain.cpp : Check that all Exceptions are caught and handled.")
#pragma message("AppCfgUnitTestMain.cpp : Check local variable and parameter naming")
#pragma message("AppCfgUnitTestMain.cpp : Lint")
#pragma message("AppCfgUnitTestMain.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "AppCfg.hpp"


extern int AppCfgUnitTest(int argCnt, char* argValues[]);

// Unit Test 

Reporter GlobalReporter(AppInfo(string("appcfgunittest"),string("1.00.00")));

/*
    @ProcDesc

        Main for unit test of AppCfg

    @ProcNotes

        <???>
*/

int main(int argCnt, char* ArgValues[])
{
    try
    {
        return( AppCfgUnitTest(argCnt,argValues));
    }
    catch (...)
    {
        // Get out of this

        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

