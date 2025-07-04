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
