/*
    .Contains <???>

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/


#include "StreamWithStdHdr.hpp"

StreamWithStdHdr::StreamWithStdHdr()
    : Stream(), _Hdr()
{

}

StreamWithStdHdr::~StreamWithStdHdr(void)
{
}

bool  StreamWithStdHdr::Open(const StreamSpec& Spec, StreamInfo& Info)
{
    bool        StreamWasOpened;

    // Open the stream

    StreamWasOpened = Stream::Open(Spec, Info);

    if (!StreamWasOpened)
    {
        return(false);
    }

    // If the Stream is in read mode, get the StdHdr

    if ( Info.GetMode() == Strm::Read )
    {
        Read(_Hdr);
    }

    return(true);
}

bool  StreamWithStdHdr::Close(void)
{
    StreamInfo  Info;
    bool        SeekSuceeded;

    // If the Stream is in write mode, write the StdHdr

    Info = _SubStream->GetInfo();

    if ( Info.GetMode() == Strm::Write )
    {
        SeekSuceeded = Stream::Seek(Strm::Begin);

        Write(_Hdr);
    }

    return(Stream::Close());
}


StdHdr StreamWithStdHdr::GetHdr(void)
{
    return(_Hdr);
}

void   StreamWithStdHdr::SetHdr(StdHdr& Hdr)
{
    _Hdr = Hdr;
}

void    StreamWithStdHdr::Read(StdHdr& Hdr)
{

}

void    StreamWithStdHdr::Read(std::string& aString)
{
    RecSize     Length;  
    Byte*       Bfr;

    Read(Length);

    Bfr = Alloc(Length);
    aString.assign( (char*) Bfr, Length);
    Free(Bfr);
}

void    StreamWithStdHdr::Read(DateTime& aDateTime)
{
    UInt16    TempInt;

    Read(TempInt);
    aDateTime.SetYear(TempInt);

    Read(TempInt);
    aDateTime.SetMonth(TempInt);

    Read(TempInt);
    aDateTime.SetDay(TempInt);

    Read(TempInt);
    aDateTime.SetHour(TempInt);

    Read(TempInt);
    aDateTime.SetMinute(TempInt);

    Read(TempInt);
    aDateTime.SetSecond(TempInt);

    Read(TempInt);
    aDateTime.SetDaylightSavingTime(TempInt);
}

void    StreamWithStdHdr::Read(Revision& aRevision)
{
    UInt16 TempInt;

    Read(TempInt);
    aRevision.SetMajor(TempInt);

    Read(TempInt);
    aRevision.SetMinor(TempInt);
}

void    StreamWithStdHdr::Read(UInt16& AnInt)
{
    UInt16* TempInt;

    TempInt = (UInt16*) Alloc(sizeof(UInt16));

    AnInt = *TempInt;

    Free((Byte*) TempInt);
}

void    StreamWithStdHdr::Read(UInt32& AnInt)
{
    UInt32* TempInt;

    TempInt  = (UInt32*) Alloc(sizeof(UInt32));
    AnInt = (ULong) *TempInt;
    Free((Byte*) TempInt);
}


void    StreamWithStdHdr::Write(StdHdr& Hdr)
{
    
}

void    StreamWithStdHdr::Write(UInt16 AnInt)
{
    UInt16* TempInt;

    TempInt  = (UInt16*) Alloc(sizeof(UInt16));
    *TempInt = (UInt16)  AnInt;
    Free((Byte*) TempInt);
}

void    StreamWithStdHdr::Write(UInt32 AnInt)
{
    UInt32* TempInt;

    TempInt  = (UInt32*) Alloc(sizeof(UInt32));
    *TempInt = (UInt32) AnInt;
    Free((Byte*) TempInt);
}

void    StreamWithStdHdr::Write(std::string& AString)
{
    Byte* Bfr;

    Write( (UInt32) AString.length());

    Bfr = Alloc(AString.length());
    memcpy(Bfr, AString.c_str(),AString.length());
    Free(Bfr); 
}

void    StreamWithStdHdr::Write(DateTime& ADateTime)
{
    UInt16    TempInt;

    TempInt = (UInt16) ADateTime.GetYear();
    Write(TempInt);

    TempInt = (UInt16) ADateTime.GetMonth();
    Write(TempInt);

    TempInt = (UInt16) ADateTime.GetDay();
    Write(TempInt);

    TempInt = (UInt16) ADateTime.GetHour();
    Write(TempInt);

    TempInt = (UInt16) ADateTime.GetMinute();
    Write(TempInt);

    TempInt = (UInt16) ADateTime.GetSecond();
    Write(TempInt);

    TempInt = (UInt16) ADateTime.GetDaylightSavingTime();
    Write(TempInt);

}

void    StreamWithStdHdr::Write(Revision& ARevision)
{
    Write((UInt16) ARevision.GetMajor());
    Write((UInt16) ARevision.GetMinor());
}

#ifdef TEST_STDHDR

#include <stdlib.h>

#include "jacl/stdhdr.hpp"

// Unit Test 

void main(int argc, char* argv[])
{
    StdHdr    AStackStdHdr;
    StdHdr*   ADynamicStdHdr;
    
    // Test the dynamic allocation 
    
    ADynamicStdHdr = new StdHdr();
    
    delete(ADynamicStdHdr);
    
    return(EXIT_SUCCESS);
}

#endif

