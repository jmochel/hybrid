
/*
    .Contains: SystemSpec Unit Test 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("SystemSpecUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("SystemSpecUnitTest.cpp : Check local variable and parameter naming")
#pragma message("SystemSpecUnitTest.cpp : Lint")
#pragma message("SystemSpecUnitTest.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "SystemSpec.hpp"


// Unit Test 

Reporter GlobalReporter;

/*
    .ProcDesc

        Main for unit test of SystemSpec

    .ProcNotes

        <???>
*/

int main(int argCnt, char* argValues[])
{
    SystemSpec*   ADynamicSystemSpec;

    try
    {
        SystemSpec    AStackSystemSpec;
    
        // Test the dynamic allocation 
    
        ADynamicSystemSpec = new SystemSpec();
    
        delete(ADynamicSystemSpec);
    }
    catch (...)
    {
        delete(ADynamicSystemSpec);
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

