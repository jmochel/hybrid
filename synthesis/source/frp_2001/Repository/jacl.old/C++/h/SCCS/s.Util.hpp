h16147
s 00041/00000/00000
d D 1.1 99/11/17 08:10:30 jmochel 2 1
cC
cK46375
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:10:26 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Util.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43655
cPC++/h/Util.hpp
cRcd3ad0cf5cb6bb60
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
