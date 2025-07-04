h00902
s 00070/00000/00000
d D 1.1 99/11/17 08:47:43 jmochel 2 1
cC
cK26508
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:39 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/VolumeSpecUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43878
cPC++/src/system/VolumeSpecUnitTest.cpp
cR3fb1ff5cb6bb65
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

E 2
I 1
E 1
