
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

