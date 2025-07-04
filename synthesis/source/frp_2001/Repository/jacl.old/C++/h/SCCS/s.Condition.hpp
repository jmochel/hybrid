h48421
s 00057/00000/00000
d D 1.1 99/11/17 08:08:20 jmochel 2 1
cC
cK12089
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:17 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Condition.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43642
cPC++/h/Condition.hpp
cR9ff74bcf5cb6bb60
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
#ifndef CONDITION_HPP
#define CONDITION_HPP

/*
    .Contains Condition handling defines and exceptions

    .Author Jim Jackl-Mochel

    .Date 11.09.94

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

using namespace std;

//
//	First come the exceptions
//

#define Precondition(Condition) \
    try \
    { \
    	if (!(Condition )) \
    	{ \
    		throw (PreconditionError(std::string("$Condition1$ A precondition check has been failed:") + std::string(#Condition))); \
    	} \
    } \
    catch (...) \
    { \
        throw; \
    }
    
#define Postcondition(Condition) \
    try \
    { \
    	if (!(Condition )) \
    	{ \
    		throw (PostconditionError(std::string("$Condition2$ A postcondition check has been failed:") + std::string(#Condition))); \
    	} \
    } \
    catch (...) \
    { \
        throw; \
    }


#endif // CONDITION_HPP
E 2
I 1
E 1
