H18651
s 00038/00000/00000
d D 1.1 01/07/13 18:14:20 jmochel 2 1
cC
cF1
cK54306
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:20 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/h/Platform.hpp
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK41354
cPC++/h/Platform.hpp
cR474c7956
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
#ifndef PLATFORM_HPP
#define PLATFORM_HPP

/*
    .Contains Compile time configuration for Platform and OS

    .Author Jim Jackl-Mochel

    .Date 02.07.96

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#define JACL_WIN32 1
#define JACL_DOS   2
#define JACL_WIN16 3
#define JACL_GENERICUNIX 4
#define JACL_WINNT 5

#if defined(__NT__)
#   define JACL_OS JACL_WINNT
#elif __WIN32__
#   define JACL_OS JACL_WIN32
#elif defined(_WIN32)
#   define JACL_OS JACL_WIN32
#elif defined(_Windows)
#   define JACL_OS JACL_WIN16
#else
#   error "No platform has been detected"
#endif

#endif  // PLATFORM_HPP
E 2
I 1
E 1
