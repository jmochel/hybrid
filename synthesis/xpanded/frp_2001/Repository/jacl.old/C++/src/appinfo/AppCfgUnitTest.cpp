
/*
    @doc

    .Contains: AppCfg Unit Test Function

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("AppCfgUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("AppCfgUnitTest.cpp : Check local variable and parameter naming")
#pragma message("AppCfgUnitTest.cpp : Lint")
#pragma message("AppCfgUnitTest.cpp : PRofile")


#include <cstdlib>

#include "AppCfg.hpp"



/*
    @ProcDesc

        Unit test of AppCfg

    @ProcNotes

        <???>
*/

int AppCfgUnitTest(int argCnt, char* argValues[])
{
    AppCfg*   aDynamicAppCfg;

    try
    {
        AppCfg    aStackAppCfg;
    
        // Test the dynamic allocation 
    
        aDynamicAppCfg = new AppCfg();

        delete(aDynamicAppCfg);
    }
    catch (...)
    {
        // Clean up to known state

        delete(aDynamicAppCfg);

        throw;
    }

    return(EXIT_SUCCESS);
}

