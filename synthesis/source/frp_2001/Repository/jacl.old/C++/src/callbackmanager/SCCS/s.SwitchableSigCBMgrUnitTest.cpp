h02632
s 00142/00000/00000
d D 1.1 99/11/17 12:46:52 jmochel 2 1
cC
cK25067
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:49 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/callbackmanager/SwitchableSigCBMgrUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45313
cPC++/src/callbackmanager/SwitchableSigCBMgrUnitTest.cpp
cR2f93d7845cb6ba86
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

    .Contains: SwitchableSigCBMgr Unit Test Function

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/


#pragma message("SwitchableSigCBMgrUnitTest.cpp : Check that all Exceptions are caught and handled.")
#pragma message("SwitchableSigCBMgrUnitTest.cpp : Check local variable and parameter naming")
#pragma message("SwitchableSigCBMgrUnitTest.cpp : Lint")
#pragma message("SwitchableSigCBMgrUnitTest.cpp : PRofile")

#include <cstdlib>

#include "SwitchableSigCBMgr.hpp"

/*
    @MethodDesc
        
          Test Function

    @MethodNotes
        
        At the moment this does nothing significant other than get called.
*/


bool TestCallbackFxn0(void* Owner, int& CfgData, int& CallData)
{
    return(true);
}

/*
    @MethodDesc
        
          Test Function

    @MethodNotes
        
        At the moment this does nothing significant other than get called.
*/

bool TestCallbackFxn1(void* Owner, int& CfgData, int& CallData)
{
    return(true);
}

/*
    @MethodDesc
        
          Test Function

    @MethodNotes
        
        At the moment this does nothing significant other than get called.
*/

bool TestCallbackFxn2(void* Owner, int& CfgData, int& CallData)
{
    return(true);
}

/*
    @MethodDesc
        
          Test Function

    @MethodNotes
        
        At the moment this does nothing significant other than get called.
*/

bool TestCallbackFxn3(void* Owner, int& CfgData, int& CallData)
{
    return(true);
}

/*
    @ProcDesc

        Unit test of SwitchableSigCBMgr

    @ProcNotes

        <???>
*/

int SwitchableSigCBMgrUnitTest(int argCnt, char* argValues[])
{

    SwitchableSigCBMgr<void, int, int, std::string>*    dynamicCBMgr;

    int calldata[4] = {1, 2, 3, 4};
    int cfgdata[4] = {4, 3, 2, 1};
    std::string signatures[4] = { std::string("1"), std::string("2"), std::string("3"), std::string("4") };

    try
    {
        SwitchableSigCBMgr<void, int, int, std::string> stackCBMgr;

        // Test the dynamic allocation 

        dynamicCBMgr = new SwitchableSigCBMgr<void, int, int, std::string>();

        // Populate both Callback Manager's

        stackCBMgr.Register(signatures[0], cfgdata[0], TestCallbackFxn0);
        stackCBMgr.Register(signatures[1], cfgdata[1], TestCallbackFxn1);
        stackCBMgr.Register(signatures[2], cfgdata[2], TestCallbackFxn2);
        stackCBMgr.Register(signatures[3], cfgdata[3], TestCallbackFxn3);

        // Invoke those puppies

        stackCBMgr.InvokeAll(std::string("1"),2);

        delete(dynamicCBMgr);
    }
    catch (...)
    {
        // Clean up to known state

        delete(dynamicCBMgr);

        throw;
    }

    return(EXIT_SUCCESS);
}

E 2
I 1
E 1
