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


