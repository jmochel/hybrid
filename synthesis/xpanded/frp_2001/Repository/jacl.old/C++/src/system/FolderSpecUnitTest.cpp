
/*
    .Contains: FolderSpec Unit Test 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("FolderSpecUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("FolderSpecUnitTest.cpp : Check local variable and parameter naming")
#pragma message("FolderSpecUnitTest.cpp : Lint")
#pragma message("FolderSpecUnitTest.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "FolderSpec.hpp"


// Unit Test 

Reporter GlobalReporter;

/*
    .ProcDesc

        Main for unit test of FolderSpec

    .ProcNotes

        <???>
*/

int main(int ArgC, char* ArgV[])
{
    FolderSpec*   ADynamicFolderSpec;

    try
    {
        FolderSpec    AStackFolderSpec;
    
        // Test the dynamic allocation 
    
        ADynamicFolderSpec = new FolderSpec();
    
        delete(ADynamicFolderSpec);
    }
    catch (...)
    {
        delete(ADynamicFolderSpec);
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

