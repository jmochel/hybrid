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
    FILE*    InFile;
    FILE*    OutFile;
    char     Bfr[1024];
    size_t   BytesRead;
    size_t   BytesWritten;

    char*    FileInBfr;
    char*    FileOutBfr;

    StreamSpec  InSpec;
    StreamSpec  OutSpec;

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
    catch (CmdLineParser::InvalidArgumentError* Error) {
        cout << Error->what();
        return(EXIT_FAILURE);
    }
    catch (CmdLineParser::TooFewArgumentsError* Error) {
        cout << Error->what();
        return(EXIT_FAILURE);
    }
    catch (CmdLineParser::TooManyArgumentsError* Error) {
        cout << Error->what();
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
    OutSpec = CmdLine.GetOutputSpec();

	// Allocate the buffers    

    FileInBfr = (char*)  malloc(BfrMultiplier * BlockSize);
    FileOutBfr = (char*) malloc(BfrMultiplier * BlockSize);

    // Open the output stream - we are creating the Input file

    OutFile = fopen(InSpec.c_str(), "wb");
    setvbuf(OutFile, FileOutBfr, _IOFBF, BfrMultiplier * BlockSize);

    for ( i = 0; i < Iterations; i++ )
    {
		memset(Bfr,'\x01', BlockSize );

        BytesWritten = fwrite(Bfr, BlockSize, 1, OutFile);
        if ( BytesWritten != 1 )
        {
            throw range_error("Failed write");
        }
    }

    // Close it

    fclose(OutFile);

	// Open the input file

    InFile = fopen(InSpec.c_str(), "rb");
    setvbuf(InFile, FileInBfr, _IOFBF, BfrMultiplier * BlockSize);

    OutFile = fopen(OutSpec.c_str(), "wb");
    setvbuf(OutFile, FileOutBfr, _IOFBF, BfrMultiplier * BlockSize);

    cout << "Copy using ANSI Standard IO" << "\n";
    cout << "Begin: " << Time;

    for ( i = 0; i < Iterations; i++ )
    {
        BytesRead = fread(Bfr, BlockSize, 1, InFile);
        if ( BytesRead != 1 )
        {
            throw range_error("Failed read");
        }

        BytesWritten = fwrite(Bfr, BlockSize, 1, OutFile);
        if ( BytesWritten != 1 )
        {
            throw range_error("Failed write");
        }
    }

    cout << "End: " << Time;

    fclose(InFile);
    fclose(OutFile);

    free(FileInBfr);
    free(FileOutBfr);
    
    return(EXIT_SUCCESS);
}



    // Now to test seeking and writing

/*
    Spec = "stream.to3";
    Info = Strm::Write;
    Info = Strm::File;
    Info = BfrMultiplier * 1024;

    StreamOpWorked = OutStream.Open(Spec, Info);

    cout << "Testing Write with Seek" << "\n";
    cout << "Begin: " << Time;

    for ( i = 0; i < Iterations; i++ )
    {
        OutBfr = OutStream.Alloc(1024, Strm::Begin, 0);
        memset(OutBfr, '\x01', 1024);
        OutStream.Free(OutBfr);
    }

    StreamOpWorked = OutStream.Close();

    cout << "End: " << Time;

    // Now to test seeking and reading

    Spec = "stream.to3";
    Info = Strm::Read;
    Info = Strm::File;
    Info = BfrMultiplier * 1024;

    StreamOpWorked = InStream.Open(Spec, Info);

    cout << "Testing Read with Seek" << "\n";
    cout << "Begin: " << Time;

    for ( i = 0; i < Iterations; i++ )
    {
        InBfr = InStream.Alloc(1024, Strm::Begin, 0);
        InStream.Free(OutBfr);
    }

    cout << "End: " << Time;

    StreamOpWorked = InStream.Close();


    // Now to test reading and reallocing.

    Spec = "stream.to1";
    Info = Strm::Read;
    Info = Strm::File;
    Info = BfrMultiplier * 1024;

    StreamOpWorked = InStream.Open(Spec, Info);

    cout << "Testing Read with Realloc" << "\n";
    cout << "Begin: " << Time;

    InBfr = InStream.Alloc(1024);

    for ( i = 0; i < Iterations; i++ )
    {
        InBfr = InStream.Alloc((1024 + (1024 * i)), InBfr);
    }

    InStream.Free(InBfr);

    cout << "End: " << Time;

    StreamOpWorked = InStream.Close();


    // Now to test delimited reads

    Spec = "stream.to4";
    Info = Strm::Write;
    Info = Strm::File;
    Info = BfrMultiplier * 1024;

    StreamOpWorked = OutStream.Open(Spec, Info);

    cout << "Generating Delimited file for testing" << "\n";
    cout << "Begin: " << Time;

    for ( i = 0; i < Iterations; i++ )
    {
        OutBfr = OutStream.Alloc(1024);
        memset(OutBfr, '\x01', 1024);
        *(OutBfr + 1023) = '\x02';
        OutStream.Free(OutBfr);
    }

    StreamOpWorked = OutStream.Close();

    cout << "End: " << Time;



    Spec = "stream.to4";
    Info = Strm::Read;
    Info = Strm::File;
    Info = BfrMultiplier * 1024;

    StreamOpWorked = InStream.Open(Spec, Info);

    cout << "Testing Delimited Read" << "\n";
    cout << "Begin: " << Time;

    for ( i = 0; i < Iterations; i++ )
    {
        InBfr = InStream.VarAlloc('\x02');

                if ( InBfr == 0 )
                {
                    throw range_error("Failed alloc");
                }

                if ( InStream.GetBytesFound() != 1024 )
                {
                    throw range_error("Failed");
                }

        InStream.Free(OutBfr);
    }

    cout << "End: " << Time;

    StreamOpWorked = InStream.Close();
*/
  






