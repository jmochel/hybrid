h24813
s 00038/00000/00000
d D 1.1 99/11/17 08:09:04 jmochel 2 1
cC
cK54306
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:00 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Platform.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43646
cPC++/h/Platform.hpp
cRb9f6767f5cb6bb60
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
