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
