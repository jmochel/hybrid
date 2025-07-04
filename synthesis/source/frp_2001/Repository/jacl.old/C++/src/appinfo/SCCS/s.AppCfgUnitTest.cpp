h53989
s 00068/00000/00000
d D 1.1 99/11/17 12:46:13 jmochel 2 1
cC
cK14797
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:10 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/appinfo/AppCfgUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45309
cPC++/src/appinfo/AppCfgUnitTest.cpp
cR2f93d78f5cb6ba86
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

    .Contains: AppCfg Unit Test Function

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("AppCfgUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("AppCfgUnitTest.cpp : Check local variable and parameter naming")
#pragma message("AppCfgUnitTest.cpp : Lint")
#pragma message("AppCfgUnitTest.cpp : PRofile")


#include <cstdlib>

#include "AppCfg.hpp"



/*
    @ProcDesc

        Unit test of AppCfg

    @ProcNotes

        <???>
*/

int AppCfgUnitTest(int argCnt, char* argValues[])
{
    AppCfg*   aDynamicAppCfg;

    try
    {
        AppCfg    aStackAppCfg;
    
        // Test the dynamic allocation 
    
        aDynamicAppCfg = new AppCfg();

        delete(aDynamicAppCfg);
    }
    catch (...)
    {
        // Clean up to known state

        delete(aDynamicAppCfg);

        throw;
    }

    return(EXIT_SUCCESS);
}

E 2
I 1
E 1
