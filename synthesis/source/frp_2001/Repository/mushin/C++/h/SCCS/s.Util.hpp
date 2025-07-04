H09911
s 00041/00000/00000
d D 1.1 01/07/13 18:14:20 jmochel 2 1
cC
cF1
cK46375
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:20 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/h/Util.hpp
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK04063
cPC++/h/Util.hpp
cR4752dd65
cV4
cX0xb1
cZ-04:00
e
u
U
f e 0
f x 0xb1
t
T
I 2
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

E 2
I 1
E 1
