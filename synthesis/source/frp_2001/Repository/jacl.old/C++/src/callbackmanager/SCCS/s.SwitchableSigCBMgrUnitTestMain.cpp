h46525
s 00057/00000/00000
d D 1.1 99/11/17 12:46:56 jmochel 2 1
cC
cK02646
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:52 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/callbackmanager/SwitchableSigCBMgrUnitTestMain.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45313
cPC++/src/callbackmanager/SwitchableSigCBMgrUnitTestMain.cpp
cR2f93d7835cb6ba86
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

    .Contains: SwitchableSigCBMgr Unit Test Main 

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

#include <cstdlib>

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "SwitchableSigCBMgr.hpp"

extern int SwitchableSigCBMgrUnitTest(int argCnt, char* argValues[]);

// Unit Test 

Reporter GlobalReporter(AppInfo(string("switchablesigcbmgrunittest"),string("1.00.00")));

/*
    @ProcDesc

        Main for unit test of SwitchableSigCBMgr

    @ProcNotes

        <???>
*/

int main(int argCnt, char* argValues[])
{
    try
    {
        return( SwitchableSigCBMgrUnitTest(argCnt,argValues));
    }
    catch (...)
    {
        return(EXIT_FAILURE);
    }

    return(EXIT_SUCCESS);
}

E 2
I 1
E 1
