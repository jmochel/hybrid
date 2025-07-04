h37485
s 00048/00000/00000
d D 1.1 99/11/17 08:09:08 jmochel 2 1
cC
cK01434
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:04 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Portable.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43646
cPC++/h/Portable.hpp
cRbc3f352f5cb6bb60
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
