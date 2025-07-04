h38328
s 00044/00000/00000
d D 1.1 99/11/17 08:09:50 jmochel 2 1
cC
cK00949
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:47 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/StreamPlatform.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43651
cPC++/h/StreamPlatform.hpp
cRd583ecff5cb6bb60
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
#ifndef STRMPLATFORM_HPP
#define STRMPLATFORM_HPP

/*
    .Contains Stream Platform Specific Initializations

    .Author Jim Jackl-Mochel

    .Date 02.07.96

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

#if ( JACL_OS == JACL_WINNT )
#pragma message("Info: JACL Streams are being configured for use on WinNT platforms")
extern SubStream* Win32IOStreamInitFxn(const StreamSpec& Spec, StreamInfo& Info);
const StreamInitFxn DefStreamInitFxn = Win32IOStreamInitFxn;
#elif ( JACL_OS == JACL_WIN32 )
#pragma message("Info: JACL Streams are being configured for use on Win32 platforms")
extern SubStream* Win32IOStreamInitFxn(const StreamSpec& Spec, StreamInfo& Info);
const StreamInitFxn DefStreamInitFxn = Win32IOStreamInitFxn;
#elif ( JACL_OS == JACL_WIN16 )
#pragma message("Info: JACL Streams are being configured for use on Win16 platforms")
extern SubStream* UnixIOStreamInitFxn(const StreamSpec& Spec, StreamInfo& Info);
const StreamInitFxn DefStreamInitFxn = UnixIOStreamInitFxn;
#elif ( JACL_OS == JACL_GENERICUNIX )
#pragma message("Info: JACL Streams are being configured for use on Generic UNIX platforms")
extern SubStream* UnixIOStreamInitFxn(const StreamSpec& Spec, StreamInfo& Info);
const StreamInitFxn DefStreamInitFxn = UnixIOStreamInitFxn;
#else
#	error "There is no DefStreamInitFxn defined for the streams classes on this platform"
#endif

#endif  // STRMPLATFORM_HPP
E 2
I 1
E 1
