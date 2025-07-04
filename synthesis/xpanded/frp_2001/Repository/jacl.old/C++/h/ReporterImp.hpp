#ifndef REPORTERIMP_HPP
#define REPORTERIMP_HPP
/*
    .Contains ReporterImp, a platform specific reporting class 

    .Author Jim Jackl-Mochel

    .Date 07.10.97

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit 

#ifndef APPINFO_HPP
#include "AppInfo.hpp"
#endif

#ifndef REPORTERCFG_HPP
#include "ReporterCfg.hpp"
#endif

/*
    .ClassDesc

		ReporterImp is a base class for platform specific message reporting classes
*/

class ReporterImp
{
    public:

        /*
            .MethodNotes
        
                The reporter config is not used at the moment. It is made available to the
                ReporterImp for future configurability. 
        */
            
        ReporterImp(ReporterCfg& Cfg);

        virtual ~ReporterImp(void);

        /*
            .MethodDesc

                Initializes the ReporterImp
        */


        virtual void    Init(Rprtr::Registry& Registry, AppInfo& anAppInfo) = 0;

        /*
            .MethodDesc

                Sends Msg to the output
        */

        
        virtual void    Emit(Rprtr::MsgType MsgType, std::string& Msg) = 0;
};
#endif // REPORTERIMP_HPP

