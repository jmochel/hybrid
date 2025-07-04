h04919
s 00069/00000/00000
d D 1.1 99/11/17 08:09:19 jmochel 2 1
cC
cK33800
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:16 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/ReporterImp.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43647
cPC++/h/ReporterImp.hpp
cRa32447df5cb6bb60
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

E 2
I 1
E 1
