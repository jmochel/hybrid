#ifndef RPRTR_HPP
#define RPRTR_HPP

/*
    @doc

    .Contains Reporter Constants

    .Author Jim Jackl-Mochel

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// Standard Headers

#include <string>
#include <map>

// Local Library Headers

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef TYPE_HPP
#include "Types.hpp"
#endif

/*
    .ClassDesc

        Describes and contains the Reporter constants
*/

class Rprtr
{
    public:

        // Definition of the places a message can be sent

		enum Service {
				Log = 0x01,
				Console = 0x02
		};

        // The index of a message type

        enum MsgType
        {
            Error = 0,
            Warning = 1,
            Status = 2,
            Banner = 3
        };

        // A masked collection of services

        typedef unsigned int ServiceMask;

        typedef map<std::string,std::string>  Registry;   
};

#endif  // RPRTR_HPP
