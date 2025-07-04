h39301
s 00045/00000/00000
d D 1.1 99/11/17 12:48:26 jmochel 2 1
cC
cK64249
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:48:22 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/w32/SubstreamWin32Ini.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45322
cPC++/src/io/stream/w32/SubstreamWin32Ini.cpp
cR2f93d7aa5cb6ba86
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
/*
    .Contains Win32 Stream Initialization Function

    .Author Jim Jackl-Mochel

    .Date 02.07.96

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

#include "w32/SubStreamWriteWin32.hpp"
#include "w32/SubStreamReadWin32.hpp"
#include "w32/SubStreamReadMemMapWin32.hpp"
#include "w32/SubStreamWriteMemMapWin32.hpp"

SubStream* Win32IOStreamInitFxn(const StreamSpec& Spec, StreamInfo& Info)
{
    SubStream*  SubStream;

    if ( Info.GetMode() == Strm::Write )
    {
        SubStream  = new Win32IOWriteSubStream();
    }
    else if ( Info.GetMode() == Strm::Read )
    {
        SubStream  = new Win32MMFReadSubStream();
    }

    if ( SubStream == 0 )
    {
        return(0);
    }

    return(SubStream);
}




E 2
I 1
E 1
