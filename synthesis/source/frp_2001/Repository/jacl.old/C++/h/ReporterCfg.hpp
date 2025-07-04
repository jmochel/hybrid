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
