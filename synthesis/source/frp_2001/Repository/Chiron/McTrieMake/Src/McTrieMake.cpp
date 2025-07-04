/*
    @doc

    .Contains: Tool to convert a sorted length delimited string list to a McTrie File

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

Reporter    GlobalReporter(AppInfo(string("mctriemake"),string("1.00.00")));

class MkMcTrieCmdLineParser : public CmdLineParser
{
	public:

		MkMcTrieCmdLineParser(int ArgC, char* ArgV[]) : CmdLineParser(ArgC, ArgV)
		{
			// register validation info

			Register(string("if"), CmdLineParser::Filespec, CmdLineParser::Required, string("Input File Name"), StdValidation);
			Register(string("of"), CmdLineParser::Filespec, CmdLineParser::Required, string("Output File Name"), StdValidation);
			Register(string("segsize"), CmdLineParser::Numeric, CmdLineParser::Required, string("Segment Size for the McTrie"), StdValidation);
		}

		string  GetInputFileName(void) 
        {
			return(_TagAndValues[string("if")]);
		}

		string  GetOutputFileName(void)
		{
			return(_TagAndValues[string("of")]);
		}

		size_t  GetSegmentSize(void)
		{
			return(atoi(_TagAndValues[string("segsize")].c_str()));
		}
};

int main(int argc, char* argv[])
{
	McTrie							Trie;

    StreamSpec                      spec;
    StreamInfo                      info;	
    Stream							in;
	Stream							out;

	Byte*							bfr;
	size_t							termLen;
	
	list<string>			        InputTerms;
	string							newTerm;
	size_t							SegmentSize;
    bool                            TermWasAdded;

    ULong                           NumberOfBlocksWritten = 0;
    
	string							Usage("/if=<InputFile> /of=<OutputFile> /segsize=<SegmentSize>\n");

    Reporter::Set("w32.logfile","makemctrie");

	// Set up the command line parser

	MkMcTrieCmdLineParser	  CmdLine(argc, argv);

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

	// Get the segment size

	SegmentSize = CmdLine.GetSegmentSize();

	// Open the appropriate files.

    try {

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
        
    try {

        // For each NodeCnt worth of terms 
        // add the terms to the set of nodes.

	    while ( (bfr = in.Alloc(sizeof(UInt))) != 0)
	    {
            // Read in terms

            termLen = (size_t) *((UInt*) bfr);
            in.Free(bfr);

            bfr = in.Alloc(termLen);
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
        // Add the terms to the trie
	    //

        Trie.Init(SegmentSize);

	    size_t TermCnt = InputTerms.size();

	    for ( size_t i = 0; i < TermCnt; i++ )
	    {
            try {
                TermWasAdded = Trie.AddTerm(InputTerms.front(), string(2 + SegmentSize, 'X'));
            }
            catch (const range_error& error)
            {
    		    exit(EXIT_FAILURE);
            }

		    if ( TermWasAdded == false ) // The trie is full
		    {
			    // Finish off the trie

			    Trie.Deinit();

                // Walk and display the results

                Trie.WalkAndPrintTerms();
            
			    // Write it out

                bfr = out.Alloc(Trie.GetBlockSize());
                memcpy(bfr,Trie.GetBlock(), Trie.GetBlockSize());
                out.Free(bfr);

			    NumberOfBlocksWritten++;

			    // Reinit

			    Trie.Init(SegmentSize);
		    }
		    else {
			    InputTerms.pop_front();
		    }
	    }
    }
    catch (...)
    {
        exit(EXIT_FAILURE);
    }

    try {

	    //
	    // Clean up any remainder in the last trie
        //

	    Trie.Deinit();

        // 
        // Write it out
        //

        bfr = out.Alloc(Trie.GetBlockSize());
        memcpy(bfr,Trie.GetBlock(), Trie.GetBlockSize());
        out.Free(bfr);

	    NumberOfBlocksWritten++;
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
