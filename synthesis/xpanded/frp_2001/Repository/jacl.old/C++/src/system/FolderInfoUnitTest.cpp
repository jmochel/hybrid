
/*
    .Contains: FolderInfo Unit Test 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("FolderInfoUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("FolderInfoUnitTest.cpp : Check local variable and parameter naming")
#pragma message("FolderInfoUnitTest.cpp : Lint")
#pragma message("FolderInfoUnitTest.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "FolderInfo.hpp"


// Unit Test 

Reporter GlobalReporter;

/*
    .ProcDesc

        Main for unit test of FolderInfo

    .ProcNotes

        <???>
*/

int main(int ArgC, char* ArgV[])
{
    FolderInfo*   ADynamicFolderInfo;

    try
    {
        FolderInfo    AStackFolderInfo;
    
        // Test the dynamic allocation 
    
        ADynamicFolderInfo = new FolderInfo();
    
        delete(ADynamicFolderInfo);
    }
    catch (...)
    {
        delete(ADynamicFolderInfo);
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

