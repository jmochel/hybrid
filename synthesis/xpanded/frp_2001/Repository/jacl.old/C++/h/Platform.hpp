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
