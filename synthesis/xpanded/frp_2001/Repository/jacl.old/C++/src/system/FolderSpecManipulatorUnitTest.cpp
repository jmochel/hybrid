
/*
    @doc

    .Contains: FolderSpecManipulator Unit Test 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("FolderSpecManipulatorUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("FolderSpecManipulatorUnitTest.cpp : Check local variable and parameter naming")
#pragma message("FolderSpecManipulatorUnitTest.cpp : Lint")
#pragma message("FolderSpecManipulatorUnitTest.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "FolderSpecManipulator.hpp"


// Unit Test 

Reporter GlobalReporter;

/*
    @ProcDesc

        Main for unit test of FolderSpecManipulator

    @ProcNotes

        <???>
*/

int main(int argCnt, char* argValues[])
{
    FolderSpecManipulator*   ADynamicFolderSpecManipulator;

    try
    {
        FolderSpecManipulator    AStackFolderSpecManipulator;
    
        // Test the dynamic allocation 
    
        ADynamicFolderSpecManipulator = new FolderSpecManipulator();
    
        delete(ADynamicFolderSpecManipulator);
    }
    catch (...)
    {
        delete(ADynamicFolderSpecManipulator);
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

