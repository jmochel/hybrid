h37126
s 00046/00000/00000
d D 1.1 99/11/17 12:48:07 jmochel 2 1
cC
cK61478
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:48:04 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/unx/SubstreamUnixIOIni.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45320
cPC++/src/io/stream/unx/SubstreamUnixIOIni.cpp
cR2f93d7af5cb6ba86
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
#ifndef STRMPLAT_HPP
#define STRMPLAT_HPP

/*
    .Contains Unix IO Stream Initialization Function

    .Author Jim Jackl-Mochel

    .Date 02.07.96

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

#include "SubStreamWriteUnixIO.hpp"
#include "SubStreamReadUnixIO.hpp"

SubStream* UnixIOStreamInitFxn(const StreamSpec& Spec, StreamInfo& Info)
{
    SubStream*  SubStream;

    if ( Info.GetMode() == Strm::Write )
    {
        SubStream  = new UnixIOWriteSubStream();
    }
    else if ( Info.GetMode() == Strm::Read )
    {
        SubStream  = new UnixIOReadSubStream();
    }

    if ( SubStream == 0 )
    {
        return(0);
    }

    return(SubStream);
}

#endif // STRMPLAT_HPP


E 2
I 1
E 1
