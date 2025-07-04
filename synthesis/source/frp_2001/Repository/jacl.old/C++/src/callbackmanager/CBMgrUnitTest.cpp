/*
    .Contains Callback Manager Unit Test

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
#include <list>
#include <vector>
#include <deque>
#include <algorithm>

using namespace std;

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#ifndef CBMGR_HPP
#include "CBMgr.hpp"
#endif

Reporter    GlobalReporter(AppInfo(string("cbmgrunittest"),string("1.00.00")));

typedef bool (CallbackFxn1)(void* Owner, int& CfgData, int& CallData);

bool TestCallbackFxn1(void* Owner, int& CfgData, int& CallData)
{
    if ( CallData > CfgData )
    {
        cout << "Test Callback Function 1 has been called" << endl;

        return(true);
    }

    return(true);
}

bool TestCallbackFxn2(void* Owner, int& CfgData, int& CallData)
{
    if ( CallData > CfgData )
    {
        cout << "Test Callback Function 2 has been called" << endl;

        return(true);
    }

    return(true);
}

bool TestCallbackFxn3(void* Owner, int& CfgData, int& CallData)
{
    if ( CallData > CfgData )
    {
        cout << "Test Callback Function 3 has been called" << endl;

        return(true);
    }

    return(true);
}

int main(int argc, char* argv[])
{
    int i = 0;
    int j = 3;
    int k[4] = {1, 2, 3, 4};

    void*   TheOwner = (void*) 7;

    // Instantiate Callback

    Callback<void,int,int>  ACallback(TheOwner, j , TestCallbackFxn1);

    // Instantiate Callback Manager

     CallbackMgr<void, int, int> ACBMgr;

    // Register the callbacks

    ACBMgr.Register(TheOwner, k[0], TestCallbackFxn1);
    ACBMgr.Register(TheOwner, k[1], TestCallbackFxn2);
    ACBMgr.Register(TheOwner, k[2], TestCallbackFxn3);
    ACBMgr.Register(TheOwner, k[3], TestCallbackFxn1);

    // Invoke those puppies

    ACBMgr.InvokeAll(k[1]);
    ACBMgr.InvokeAll(k[3]);

	// Return

	return(EXIT_SUCCESS);
}
