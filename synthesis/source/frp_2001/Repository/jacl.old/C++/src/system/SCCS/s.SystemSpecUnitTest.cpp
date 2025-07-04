h01180
s 00070/00000/00000
d D 1.1 99/11/17 08:47:35 jmochel 2 1
cC
cK26703
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:32 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/SystemSpecUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43877
cPC++/src/system/SystemSpecUnitTest.cpp
cR1b9797bf5cb6bb65
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

E 2
I 1
E 1
