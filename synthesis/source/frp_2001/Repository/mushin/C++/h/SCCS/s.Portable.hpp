H31282
s 00048/00000/00000
d D 1.1 01/07/13 18:14:20 jmochel 2 1
cC
cF1
cK01434
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:20 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/h/Portable.hpp
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK50473
cPC++/h/Portable.hpp
cR474e0883
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
#ifndef PORTABLE_HPP
#define PORTABLE_HPP

/*
    .Contains Portable Data Type Definitions

    .Author Jim Jackl-Mochel

    .Date 03.20.93

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#ifndef PLATFORM_HPP
#include "Platform.hpp"
#endif

/*
	Data Types defined for their size
*/

#if ( JACL_OS == JACL_WIN32 )
typedef unsigned char		UInt8;
typedef unsigned short int  UInt16;
typedef unsigned long		UInt32;
#elif __DOS386__
typedef unsigned char		UInt8;
typedef unsigned int		UInt16;
typedef unsigned long		UInt32;
#else // Plain old DOS ?
typedef unsigned char		UInt8;
typedef unsigned short int  UInt16;
typedef unsigned long		UInt32;
#endif

/*
	The almighty Byte
*/

typedef UInt8  Byte;

#endif /* PORTABLE_HPP */
E 2
I 1
E 1
