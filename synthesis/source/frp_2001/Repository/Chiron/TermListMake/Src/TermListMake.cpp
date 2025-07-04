/*
    @doc

    .Contains: Tool to convert a basic list of strings to a sorted length delimited string file.

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: $
    $Revision: $
    $Date: $
*/

#include <string>
#include <list>
#include <vector>
#include <deque>
#include <algorithm>

#include "Reporter.hpp"
#include "CmdLineParser.hpp"
#include "Stream.hpp"
#include "McTrie.hpp"

Reporter    GlobalReporter(AppInfo(string("termlistmake"),string("1.00.00")));

class TermListMakeCmdLineParser : public CmdLineParser
{
	public:

		TermListMakeCmdLineParser(int ArgC, char* ArgV[]) : CmdLineParser(ArgC, ArgV)
		{
			// register validation info

			Register(string("if"), CmdLineParser::Filespec, CmdLineParser::Required, string("Input File Name"), StdValidation);
			Register(string("of"), CmdLineParser::Filespec, CmdLineParser::Required, string("Output File Name"), StdValidation);
		}

		string  GetInputFileName(void) 
        {
			return(_TagAndValues[string("if")]);
		}

		string  GetOutputFileName(void)
		{
			return(_TagAndValues[string("of")]);
		}
};

int main(int argc, char* argv[])
{
    StreamSpec                      spec;
    StreamInfo                      info;	
    Stream							in;
	Stream							out;

	Byte*							bfr;
	size_t							termLen;

    ULong                           NumberOfTermsWritten = 0;
    
	string							Usage("/if=<InputFile> /of=<OutputFile>\n");

    Reporter::Set("w32.logfile","termlistmake");

	// Set up the command line parser

	TermListMakeCmdLineParser	  CmdLine(argc, argv);

	// Validate command line

	try {
		CmdLine.Validate();
	}
	catch (CmdLineParser::InvalidArgumentError& error) 
    {
        Report(error);
        Report(Rprtr::Error, Usage);
		exit(EXIT_FAILURE);
	}
	catch (CmdLineParser::TooFewArgumentsError& error) {
        Report(error);
        Report(Rprtr::Error, Usage);
		exit(EXIT_FAILURE);
	}
	catch (CmdLineParser::TooManyArgumentsError& error) {
        Report(error);
        Report(Rprtr::Error, Usage);
		exit(EXIT_FAILURE);
	}

	// Open the appropriate files.

    try
    {
        spec = CmdLine.GetInputFileName();
        info = Strm::Read;

	    in.Open(spec,info);

        spec = CmdLine.GetOutputFileName();
        info = Strm::Write;

        out.Open(spec,info);
    }
    catch (Exception& error)
    {
        Report(Rprtr::Error, "Error opening the input and output files");
        Report(Rprtr::Error, error.what());
        exit(EXIT_FAILURE);
    }
    catch(...)
    {
        Report(Rprtr::Error, "Error opening the input and output files");
        exit(EXIT_FAILURE);
    }
        
    try
    {
        // While we have terms, pump them into the term list.
    
	    while (bfr = in.VarAlloc(?) != 0)
	    {
            // Read in terms

            termLen = in.GetVarBlock();
		    newTerm = string((char*) bfr, (size_t) termLen);
            in.Free(bfr);

		    if ( !InputTerms.empty() && newTerm == InputTerms.back() )
		    {
			    // Remove duplicates
		    }
		    else {
			    InputTerms.push_back(newTerm);
		    }
	    }

        //
        // Write the terms out to the file 
	    //

	    for ( size_t i = 0; i < TermCnt; i++ )
	    {
    		    // Write it out

                bfr = out.Alloc(Trie.GetBlockSize());
                memcpy(bfr,Trie.GetBlock(), Trie.GetBlockSize());
                out.Free(bfr);

			    InputTerms.pop_front();
	    }

    }
    catch (...)
    {
        exit(EXIT_FAILURE);
    }

	// Close down the files.

	in.Close();
    out.Close();

	// Return

	return(EXIT_SUCCESS);
}
