h21092
s 00072/00000/00000
d D 1.1 99/11/17 08:47:27 jmochel 2 1
cC
cK44346
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:24 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/StreamSpecManipulatorUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43876
cPC++/src/system/StreamSpecManipulatorUnitTest.cpp
cR16d40f1f5cb6bb65
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

E 2
I 1
E 1
