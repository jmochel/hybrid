h20812
s 00072/00000/00000
d D 1.1 99/11/17 08:47:08 jmochel 2 1
cC
cK44106
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:04 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/FolderSpecManipulatorUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43874
cPC++/src/system/FolderSpecManipulatorUnitTest.cpp
cReb21069f5cb6bb65
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

E 2
I 1
E 1
