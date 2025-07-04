/*
    @doc

    .Contains: Unit Test for the Stream class

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

#include <cstdlib>

#include <std::string>
#include <vector>
#include <algorithm>

#include "Reporter.hpp"

#include "Stream.hpp"



Reporter    GlobalReporter(AppInfo(std::string("streamunittest"),std::string("1.00.00")));

struct WriteStr
{
    Stream* _OutStream;

    WriteStr(Stream* OutStream)
    {
        _OutStream = OutStream;
    }

    bool operator()(std::string& Str)
    {
        UInt* LenBfr;
        Byte* Bfr;

        // Output the length

        LenBfr = (UInt*) _OutStream->Alloc(sizeof(UInt));
        *LenBfr = Str.length();
        _OutStream->Free((Byte*) LenBfr);

        // Output the std::string itself

        Bfr = _OutStream->Alloc(Str.length());
        memcpy(Bfr, Str.data(), Str.length());
        _OutStream->Free(Bfr);

        return(true);
    }
};


struct ReadStr
{
    Stream* _InStream;

    ReadStr(Stream* inStream)
    {
        _InStream = inStream;
    }

    bool operator()(std::string& str)
    {
        UInt* LenBfr;
        Byte* Bfr;

        // Get the length

        LenBfr = (UInt*) _InStream->Alloc(sizeof(UInt));

        // Get the String bytes

        Bfr = _InStream->Alloc(*LenBfr);

        str = std::string((const char*) Bfr, (size_t) *LenBfr);

        _InStream->Free((Byte*) LenBfr);
        _InStream->Free(Bfr);

        return(true);
    }
};

int main(int argc, char* argv[])
{
    Stream      OutStream;
    Stream      InStream;
    StreamSpec  Spec;
    StreamInfo  Info;
    bool        StreamOpWorked;

    vector<std::string>    WordList;

    try {
        Reporter::Set("w32.logfile","streamunittest");
    }
    catch (...)
    {
        exit(EXIT_FAILURE);
    }


    try 
    {
        // Populate the list;

        WordList.push_back(std::string("Talking"));
        WordList.push_back(std::string("Drums"));
        WordList.push_back(std::string("Of"));
        WordList.push_back(std::string("Africa"));
        WordList.push_back(std::string("Have"));
        WordList.push_back(std::string("Gone"));
        WordList.push_back(std::string("Berserk"));
    }
    catch (...)
    {
        exit(EXIT_FAILURE);
    }

    // Open the output stream

    Spec = "StreamUnitTest.to0";
    Info.SetMode(Strm::Write);
    Info.SetService(Strm::File);

    StreamOpWorked = OutStream.Open(Spec, Info);

    // Write out tons of data

    for_each(WordList.begin(), WordList.end(), WriteStr(&OutStream));

    // Close it

    StreamOpWorked = OutStream.Close();

    // Open the Input stream

    Spec = "StreamUnitTest.to0";
    Info.SetMode(Strm::Read);
    Info.SetService(Strm::File);

    StreamOpWorked = InStream.Open(Spec, Info);

    // Open the other Output stream

    Spec = "StreamUnitTest.to1";
    Info.SetMode(Strm::Write);
    Info.SetService(Strm::File);

    StreamOpWorked = OutStream.Open(Spec, Info);

    // Close the streams

    StreamOpWorked = OutStream.Close();
    StreamOpWorked = InStream.Close();

    return(EXIT_SUCCESS);
}
