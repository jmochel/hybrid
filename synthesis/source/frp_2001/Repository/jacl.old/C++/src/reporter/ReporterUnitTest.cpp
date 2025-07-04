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
