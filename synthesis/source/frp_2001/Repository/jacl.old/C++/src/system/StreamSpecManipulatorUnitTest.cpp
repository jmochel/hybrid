
/*
    @doc

    .Contains: StreamSpecManipulator Unit Test 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("StreamSpecManipulatorUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("StreamSpecManipulatorUnitTest.cpp : Check local variable and parameter naming")
#pragma message("StreamSpecManipulatorUnitTest.cpp : Lint")
#pragma message("StreamSpecManipulatorUnitTest.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "StreamSpecManipulator.hpp"


// Unit Test 

Reporter GlobalReporter;

/*
    @ProcDesc

        Main for unit test of StreamSpecManipulator

    @ProcNotes

        <???>
*/

int main(int argCnt, char* argValues[])
{
    StreamSpecManipulator*   ADynamicStreamSpecManipulator;

    try
    {
        StreamSpecManipulator    AStackStreamSpecManipulator;
    
        // Test the dynamic allocation 
    
        ADynamicStreamSpecManipulator = new StreamSpecManipulator();
    
        delete(ADynamicStreamSpecManipulator);
    }
    catch (...)
    {
        delete(ADynamicStreamSpecManipulator);
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

