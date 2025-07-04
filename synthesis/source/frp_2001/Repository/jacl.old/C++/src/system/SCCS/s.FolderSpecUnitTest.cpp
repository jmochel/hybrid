h65272
s 00070/00000/00000
d D 1.1 99/11/17 08:47:11 jmochel 2 1
cC
cK25260
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:08 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/FolderSpecUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43875
cPC++/src/system/FolderSpecUnitTest.cpp
cRed6f30ff5cb6bb65
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

E 2
I 1
E 1
