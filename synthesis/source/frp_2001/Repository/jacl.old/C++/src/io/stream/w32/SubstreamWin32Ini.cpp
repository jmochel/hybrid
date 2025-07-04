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




