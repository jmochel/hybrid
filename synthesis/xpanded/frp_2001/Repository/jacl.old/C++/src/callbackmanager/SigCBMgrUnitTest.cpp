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


