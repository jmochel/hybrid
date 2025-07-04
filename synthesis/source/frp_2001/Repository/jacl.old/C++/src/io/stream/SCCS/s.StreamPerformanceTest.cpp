h03357
s 00147/00000/00000
d D 1.1 99/11/17 12:47:42 jmochel 2 1
cC
cK27797
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:47:38 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/StreamPerformanceTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45318
cPC++/src/io/stream/StreamPerformanceTest.cpp
cR2f93d7b65cb6ba86
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
    .Contains A Speed Test of the Stream API

    .Author Jim Jackl-Mochel

    .Date 02.13.96

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

// Standard Library Headers

#include <cstdlib>
#include <cstdio>

// Local Library Headers

#include "Reporter.hpp"
#include "Stream.hpp"
#include "Datetime.hpp"

// Application Specific Header

#include "IOTest.hpp"

int main(int ArgCnt, char* ArgValues[])
{
    Stream      OutStream;
    Stream      InStream;
    StreamSpec  InSpec;
    StreamSpec  OutSpec;
    StreamInfo  Info;

    bool        StreamOpWorked;
    Byte*       OutBfr;
    Byte*       InBfr;
    size_t      i;
    DateTime    Time;

    size_t      Iterations = 20480;
    size_t      BfrMultiplier = 32;
    size_t      BlockSize = 1024;
    
    IOTestCmdLineParser     CmdLine(ArgCnt, ArgValues);

    // Validate command line

    try 
    {
        CmdLine.Validate();
    }
    catch (const CmdLineParser::InvalidArgumentError& Error) {
        cout << Error.what();
        return(EXIT_FAILURE);
    }
    catch (const CmdLineParser::TooFewArgumentsError& Error) {
        cout << Error.what();
        return(EXIT_FAILURE);
    }
    catch (const CmdLineParser::TooManyArgumentsError& Error) {
        cout << Error.what();
        return(EXIT_FAILURE);
    }
    catch(...)
    {
        return(EXIT_FAILURE);
    }

    // Get the command line arguments 

    Iterations = CmdLine.GetIterationCnt();
    BlockSize = CmdLine.GetBlockSize();
    InSpec = CmdLine.GetInputSpec();
    OutSpec = CmdLine.GetInputSpec();
    
    // Open the output stream - creating the test input

    Info = Strm::Write;
    Info = Strm::File;
    Info = BfrMultiplier * BlockSize;

    StreamOpWorked = OutStream.Open(InSpec, Info);

    for ( i = 0; i < Iterations; i++ )
    {
        OutBfr = OutStream.Alloc(BlockSize);
        memset(OutBfr, '\x01', BlockSize);
        OutStream.Free(OutBfr);
    }

    // Close it

    StreamOpWorked = OutStream.Close();

    // Open the Input stream

    Info = Strm::Read;
    Info = Strm::File;
    Info = BfrMultiplier * BlockSize;

    StreamOpWorked = InStream.Open(InSpec, Info);

    // Open the other Output stream

    OutSpec = "stream.to1";
    Info = Strm::Write;
    Info = Strm::File;
    Info = BfrMultiplier * BlockSize;

    StreamOpWorked = OutStream.Open(OutSpec, Info);

    cout << "Copy using Streams" << "\n";
    cout << "Begin: " << Time;

    // Copy from one file to another

    for ( i = 0; i < Iterations; i++ )
    {
        InBfr = InStream.Alloc(BlockSize);
        OutBfr = OutStream.Alloc(BlockSize);
        memcpy(OutBfr, InBfr, BlockSize);
        InStream.Free(InBfr);
        OutStream.Free(OutBfr);
    }

    cout << "End: " << Time;

    // Close the streams

    StreamOpWorked = OutStream.Close();
    StreamOpWorked = InStream.Close();

    return(EXIT_SUCCESS);
}







E 2
I 1
E 1
