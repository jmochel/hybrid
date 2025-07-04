h45074
s 00051/00000/00000
d D 1.1 99/11/17 12:49:05 jmochel 2 1
cC
cK04994
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:49:01 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/reporter/ReporterUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45326
cPC++/src/reporter/ReporterUnitTest.cpp
cR2f93d7df5cb6ba86
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

    .Author: Jim JM

    .Date: 09.12.97

    .Contains   Unit Test for Reporter class

	.Copyright

		This code is in the public domain

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $

*/


#include "Reporter.hpp"

Reporter    GlobalReporter(AppInfo(std::string("reporterunittest"),std::string("1.00.00")));

/*
    @ProcDesc

            Main for the unit test of the Reporter class
*/

int main(int argc, char* argv[])
{
    try 
    {
        Reporter::Set("w32.logfile","reporterunittest");

        GlobalReporter.Emit(Rprtr::Error, "Testing .....");
    }
    catch (...)
    {
        return(EXIT_FAILURE);
    }
    
//        Banner("This is a test of the %s network\n", "Silly");
//        Status(__HERE__,"This is a test of the %s\n", "Silly Network");
//        Warning(__HERE__,"This is a test of the %s\n", "Silly WorkNet");

    return(EXIT_SUCCESS);
}
E 2
I 1
E 1
