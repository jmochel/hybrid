h60670
s 00062/00000/00000
d D 1.1 99/11/17 12:46:35 jmochel 2 1
cC
cK19497
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:31 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/callbackmanager/CallbackUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45311
cPC++/src/callbackmanager/CallbackUnitTest.cpp
cR2f93d7895cb6ba86
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
E 2
I 1
E 1
