h62186
s 00067/00000/00000
d D 1.1 99/11/17 12:46:17 jmochel 2 1
cC
cK22230
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:13 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/appinfo/AppCfgUnitTestMain.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45309
cPC++/src/appinfo/AppCfgUnitTestMain.cpp
cR2f93d78e5cb6ba86
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

    .Contains: AppCfg Unit Test Main 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("AppCfgUnitTestMain.cpp : Check that all Exceptions are caught and handled.")
#pragma message("AppCfgUnitTestMain.cpp : Check local variable and parameter naming")
#pragma message("AppCfgUnitTestMain.cpp : Lint")
#pragma message("AppCfgUnitTestMain.cpp : PRofile")


#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "AppCfg.hpp"


extern int AppCfgUnitTest(int argCnt, char* argValues[]);

// Unit Test 

Reporter GlobalReporter(AppInfo(string("appcfgunittest"),string("1.00.00")));

/*
    @ProcDesc

        Main for unit test of AppCfg

    @ProcNotes

        <???>
*/

int main(int argCnt, char* ArgValues[])
{
    try
    {
        return( AppCfgUnitTest(argCnt,argValues));
    }
    catch (...)
    {
        // Get out of this

        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

E 2
I 1
E 1
