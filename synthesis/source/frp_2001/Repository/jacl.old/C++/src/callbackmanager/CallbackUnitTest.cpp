/*
    .Contains Callback Unit Test

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

#ifndef CALLBACK_HPP
#include "Callback.hpp"
#endif

Reporter    GlobalReporter(AppInfo(string("callbackunittest"),string("1.00.00")));

typedef bool (CallbackFxn)(void* Owner, int& CfgData, int& CallData);

bool TestCallbackFxn(void* Owner, int& CfgData, int& CallData)
{
    if ( CallData > CfgData )
    {
        cout << CallData << " is greater than " << CfgData << "\n";
        return(true);
    }

    return(true);
}

int main(int argc, char* argv[])
{
    int i = 0;
    int j = 3;
    
    // Instantiate callback

    Callback<void, int, int, CallbackFxn> Callback0((void*) 0, j, TestCallbackFxn);

    Callback0.Invoke(i);

	// Return

	return(EXIT_SUCCESS);
}
