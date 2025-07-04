h44745
s 00161/00000/00000
d D 1.1 99/11/17 12:46:42 jmochel 2 1
cC
cK03707
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:38 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/callbackmanager/SigCBMgrUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45312
cPC++/src/callbackmanager/SigCBMgrUnitTest.cpp
cR2f93d7875cb6ba86
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

    .Contains Signature Based Callback Manager Unit Test

    .Author Jim Jackl-Mochel

    .Date 12.17.96

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

#include <string>

using namespace std;

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#ifndef CALLBACK_HPP
#include "Callback.hpp"
#endif

#ifndef SIGCBMGR_HPP
#include "SigCBMgr.hpp"
#endif


/*
    @MethodDesc
        
          Test Function

    @MethodNotes
        
        At the moment this does nothing significant other than get called.
*/


bool TestCallbackFxn0(void* Owner, int& CfgData, int& CallData)
{
    if ( CallData > CfgData )
    {
        return(true);
    }

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
    if ( CallData > CfgData )
    {
        return(true);
    }

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
    if ( CallData > CfgData )
    {
        return(true);
    }

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
    if ( CallData > CfgData )
    {
        return(true);
    }

    return(true);
}


Reporter    GlobalReporter(AppInfo(string("sigcbmgrunittest"),string("1.00.00")));

/*
    @MethodDesc
        
          Main unit test function

    @MethodNotes
        
         This simply exercises the signature callback manager 
*/

int main(int argc, char* argv[])
{
    int i = 0;
    int j = 3;
    int k[4] = {1, 2, 3, 4};

    // Instantiate Callback Manager

    SignatureCallbackMgr<void, int, int, int> SignatureCBMgr;

    // Register the signature callbacks

    SignatureCBMgr.Register(0, k[0], TestCallbackFxn0, k[1]);
    SignatureCBMgr.Register(0, k[1], TestCallbackFxn1, k[1]);
    SignatureCBMgr.Register(0, k[2], TestCallbackFxn2, k[1]);
    SignatureCBMgr.Register(0, k[3], TestCallbackFxn3, k[1]);

    // Invoke those puppies

    SignatureCBMgr.InvokeAll(k[1],2);
    SignatureCBMgr.InvokeAll(k[1],3);

	// Return

	return(EXIT_SUCCESS);
}


E 2
I 1
E 1
