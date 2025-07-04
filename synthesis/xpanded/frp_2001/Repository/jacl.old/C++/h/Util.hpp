#ifndef UTIL_HPP
#define UTIL_HPP

/*
    .Contains Conditional Return Macros,

    .Author Jim Jackl-Mochel

    .Date 03.02.93

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#define ReturnTrueIf(Condition) \
    if ((Condition) == true ) \
    { \
        return(true); \
    }

#define ReturnFalseIf(Condition) \
    if ((Condition) == true ) \
    { \
        return(false); \
    }
    
/*
	Max and min macros
*/

#define Max(A,B) (((A) > (B)) ? (A) : (B)) 
#define Min(A,B) (((A) < (B)) ? (A) : (B)) 

#endif /* UTIL_HPP */

