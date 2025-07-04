/*
    @doc Stream

    .Author Jim Jackl-Mochel

    .Date 02.06.96

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/


// Local Library Headers

#include "Util.hpp"
#include "Condition.hpp"

// Local Headers

#include "Stream.hpp"
#include "SubStream.hpp"

/*
    @MethodDesc Constructor
*/

Stream::Stream(StreamInitFxn InitFxn)
{
    SetDefaults();

    _InitFxn = InitFxn;
}

/*
    @MethodDesc Destructor
*/

Stream::~Stream(void)
{
    if (_SubStream != 0 )
    {
        delete(_SubStream);
    }
}

/*
    @MethodDesc <c SetDefaults> Sets the class wide defaults
*/

void Stream::SetDefaults(void)
{
    _SubStream = 0;
    _CurrRegion = 0;
    _InitFxn = 0;
}

/*
    @MethodDesc Open the stream

    @MethodNotes

        This does a bit more than open the stream. It actually uses the
        Initialization Function to determine what type of substream to create.

*/

bool  Stream::Open(const StreamSpec& Spec, StreamInfo& Info)
{
    StreamInitFxn TempInitFxn;
    bool          StreamWasOpened;

    try 
    {
        // Free up any existing substream

        if ( _SubStream != 0 )
        {
            delete(_SubStream);
        }

        // Set the values into a new state

        TempInitFxn = _InitFxn;
        SetDefaults();

        _InitFxn = TempInitFxn;

        // Get the appropriate new substream

        _SubStream = _InitFxn(Spec, Info);

        if ( _SubStream == 0 )
        {
            throw (Strm::PlatformInitializeError(std::string("$Stream1$ Unable to initialize the stream's platform specific layer")));
        }

        // Open the file

        StreamWasOpened = _SubStream->Open(Spec, Info);
        if (!StreamWasOpened)
        {
            throw (Strm::OpenError(std::string("$Stream1$ Unable to initialize the stream's platform specific layer")));
        }

        // Get the CurrRegion

        _CurrRegion = (StreamRegion*) _SubStream->GetCurrRegion();
    }
    catch (...)
    {
        throw;
    }

    return(true);
}


/*
    @MethodDesc Allocate a block of memory from the current offset
*/

Byte* Stream::Alloc(size_t Len)
{
    Precondition(Len != 0);

    Byte* TempBfr;

    // Check to see if we need to call the sub stream alloc.

    if ( (_CurrRegion == 0) || (_CurrRegion->GetBfrAvail() < Len) )
    {
        TempBfr = _SubStream->Alloc(Len);
        _CurrRegion = _SubStream->GetCurrRegion();

        return(TempBfr);
    }

    // If not just return the Update.

    return(_CurrRegion->Update(Len));
}


/*
    @MethodDesc Allocate a block of memory from the specified offset
*/

Byte* Stream::Alloc(size_t Len, Strm::Whence Whence, StreamOffset Offset)
{
    Precondition(Len != 0);

    Byte* TempBfr;

    // Seek, as necessary, to the appropriate location.

    if ((Whence != Strm::Curr) || (Offset != 0))
    {
        _SubStream->Seek(Whence, Offset);
        _CurrRegion = _SubStream->GetCurrRegion();
    }

    // Check to see if we need to call the sub stream alloc.

    if ( (_CurrRegion == 0) || (_CurrRegion->GetBfrAvail() < Len) )
    {
        TempBfr = _SubStream->Alloc(Len);
        _CurrRegion = _SubStream->GetCurrRegion();

        return(TempBfr);
    }

    // If not just return the Update.

    return(_CurrRegion->Update(Len));
}

/*
    @MethodDesc Reallocate a block of memory from the offset associated with <p Bfr>
*/

Byte* Stream::Alloc(size_t NewLen, Byte* Bfr)
{
    Precondition(NewLen != 0);
    Precondition(Bfr != 0);

    try 
    {
        // First see if the current region can answer the needs

        if ( _CurrRegion->GetBytesForwardFromBfr(Bfr) >= NewLen ) 
        {
                _CurrRegion->ResetTo(Bfr);
                return(_CurrRegion->Update(NewLen));
        }

        return(_SubStream->Alloc(NewLen, Bfr));
    }
    catch (...)
    {
        throw;
    }
}


/*
    @MethodDesc

        Allocate a block of variable length block of memory ending with the deimeter <p Delim>

*/

Byte* Stream::VarAlloc(Byte Delim)
{
        return(_SubStream->VarAlloc(Delim));
}

// General Status and such

/*
    @MethodDesc

       Sets the current offset to the given offset
*/

bool  Stream::Seek(Strm::Whence Whence, StreamOffset Offset)
{
    return(_SubStream->Seek(Whence, Offset));
}

/*
    @MethodDesc

       Gets the current offset in absolute form.
*/
    
AbsStreamOffset Stream::Tell(void)
{
    return(_SubStream->Tell());
}

/*
    @MethodDesc Checks if Stream is currently at the end.
*/

bool  Stream::IsEOS(void)
{
    return(_SubStream->IsEOS());
}

/*
    @MethodDesc Returns the number of bytes found in a <mf VarAlloc> call.
*/

size_t Stream::GetBytesFound(void) const
{
        return(_SubStream->GetBytesFound());
}

/*
    @MethodDesc Close the stream

*/

bool  Stream::Close(void)
{
    // Close up the region list and substream

    return(_SubStream->Close());
}
