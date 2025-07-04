#ifndef TYPES_HPP
#define TYPES_HPP

/*
    .Contains General Data Types

    .Author Jim Jackl-Mochel

    .Date 03/02/93

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#include <cstdio>
#include <string>

using namespace std;

#include "Portable.hpp"

/*
        Range portable types
*/

// Type                               Portable Range

typedef signed char    SChar;       // 0 .. 127
typedef signed short   SShort;      // -32,767 .. 32,767
typedef signed int     SInt;        // -32,767 .. 32,767
typedef signed long    SLong;             // -2,147,483,647 .. 2,147,483,647 

typedef unsigned char  UChar;       // 0 .. 255
typedef unsigned short UShort;      // 0 .. 65,535
typedef unsigned int   UInt;        // 0 .. 65,535
typedef unsigned long  ULong;             // 0 .. 4,294,967,295

/*
        Boolean
*/

// Commented out for use in the BC5 compiler
// #include "bool.h"

/*
        StreamOffset is a relative position in a file
*/


// typedef basic_string<Byte>			ByteBfr; //  A byte buffer as opposed to a char buffer.

typedef std::basic_string<Byte,std::char_traits<Byte>,std::allocator<Byte> > ByteBfr; //  A byte buffer as opposed to a char buffer.

typedef SLong StreamOffset;         // General Stream Offset type
typedef ULong AbsStreamOffset;      // Absolute Stream Offset type, range = 0 .. 4,294,967,295

typedef ULong RecRange;             // Record Count Values, range = 0 .. 4,294,967,295
typedef ULong RecSize;              // Record Size Values, range = 0 .. 4,294,967,295

typedef ULong Checksum;             // Checksum values, range = 0 .. 4,294,967,295

#endif /* TYPES_HPP */
