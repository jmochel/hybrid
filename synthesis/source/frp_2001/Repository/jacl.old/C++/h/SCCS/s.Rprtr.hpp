h46307
s 00070/00000/00000
d D 1.1 99/11/17 08:09:31 jmochel 2 1
cC
cK10778
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:27 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Rprtr.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43649
cPC++/h/Rprtr.hpp
cRaa0ad29f5cb6bb60
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
E 2
I 1
E 1
