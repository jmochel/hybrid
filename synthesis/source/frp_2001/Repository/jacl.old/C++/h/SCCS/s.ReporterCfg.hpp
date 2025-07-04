h08754
s 00081/00000/00000
d D 1.1 99/11/17 08:09:15 jmochel 2 1
cC
cK37672
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:12 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/ReporterCfg.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43647
cPC++/h/ReporterCfg.hpp
cRa0d7968f5cb6bb60
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
#ifndef REPORTERCFG_HPP
#define REPORTERCFG_HPP

/*
    .Contains ReporterCfg

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// Standard Library Headers

#include <string>

// Local Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef RPRTR_HPP
#include "Rprtr.hpp"
#endif


/*
    .ClassDesc

            An encapsulation of status information for streams

    .ClassNotes
        


*/

class ReporterCfg
{

    public:

        // Constructors

        ReporterCfg();
        ReporterCfg(const ReporterCfg& aReporterCfg);
        ReporterCfg(Rprtr::ServiceMask aMask);

        // Destructor

        ~ReporterCfg(void);

        // Accessors

        const Rprtr::ServiceMask GetServiceMask(void) const;

        // Mutators

        void SetServiceMask(const Rprtr::ServiceMask aMask);

        // Assignment

        ReporterCfg& operator = (const ReporterCfg& aReporterCfg);
		ReporterCfg& operator = (const Rprtr::ServiceMask aServiceMask);
		ReporterCfg& operator = (std::string& Key);

    protected:

        Rprtr::ServiceMask   _ServiceMask;       // The type of service to be opened.
};

#endif  // REPORTERCONFIGINFO_HPP
E 2
I 1
E 1
