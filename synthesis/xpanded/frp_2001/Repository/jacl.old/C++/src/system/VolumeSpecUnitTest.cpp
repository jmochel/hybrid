
/*
    .Contains: VolumeSpec Unit Test 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("VolumeSpecUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("VolumeSpecUnitTest.cpp : Check local variable and parameter naming")
#pragma message("VolumeSpecUnitTest.cpp : Lint")
#pragma message("VolumeSpecUnitTest.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "VolumeSpec.hpp"


// Unit Test 

Reporter GlobalReporter;

/*
    .ProcDesc

        Main for unit test of VolumeSpec

    .ProcNotes

        <???>
*/

int main(int argCnt, char* argValues[])
{
    VolumeSpec*   ADynamicVolumeSpec;

    try
    {
        VolumeSpec    AStackVolumeSpec;
    
        // Test the dynamic allocation 
    
        ADynamicVolumeSpec = new VolumeSpec();
    
        delete(ADynamicVolumeSpec);
    }
    catch (...)
    {
        delete(ADynamicVolumeSpec);
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

