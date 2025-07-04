/*
	mkmctrie.cpp

	$ Header $

	This program dumps a McTrie (MCT) file
*/

#include <string>
#include <list>
#include <vector>
#include <deque>
#include <algorithm>

#include "jacl/types.hpp"
#include "jacl/rprtr.hpp"
#include "jacl/cmdlnprs.hpp"
#include "jacl/mctrie.hpp"

Rprtr	GlobalRprtr(__FILE__);

class DumpMcTrieCmdLineParser : public CmdLineParser
{
	public:

		DumpMcTrieCmdLineParser(int ArgC, char* ArgV[]) : CmdLineParser(ArgC, ArgV)
		{
			// register validation info

			RegisterArgForValidation(ArgCriteria( "if", Arg::Filespec, Arg::Required));
			RegisterArgForValidation(ArgCriteria( "of", Arg::Filespec, Arg::Required));
		}

		string  GetInputFileName(void)
		{
			map<string, string, less<string>, allocator>::iterator i;

			return(_TagAndValues[string("if")]);
		}

		string  GetOutputFileName(void)
		{
			map<string, string, less<string>, allocator>::iterator i;

			return(_TagAndValues[string("of")]);
		}
};

int main(int argc, char* argv[])
{
	McTrie							Trie;
	McTrie							OptimalTrie;

	FILE*							In;
	FILE*							Out;

	Byte							InputBfr[1024];
	size_t							RecLen;
	size_t							NumberOfBlocksWritten = 0;

	list<string, allocator>			InputTerms;
	string							NewTerm;
	size_t							SegmentSize;

	string							Usage("mctdmp /if=<InputFile> /of=<OutputFile>\n");

	// Set up the command line parser

	DumpMcTrieCmdLineParser	  CmdLine(argc, argv);

	// Validate command line

	try {
		CmdLine.Validate();
	}
	catch (const CmdLineParser::InvalidArgumentError& Error) {
		cout << Error.what() << "\n";
		cout << Usage;
		exit(EXIT_FAILURE);
	}
	catch (const CmdLineParser::TooFewArgumentsError& Error) {
		cout << Error.what() << "\n";
		cout << Usage;
		exit(EXIT_FAILURE);
	}
	catch (const CmdLineParser::TooManyArgumentsError& Error) {
		cout << Error.what() << "\n";
		cout << Usage;
		exit(EXIT_FAILURE);
	}


	// Open the appropriate files.

	In = fopen(CmdLine.GetInputFileName().c_str(), "rb");
	Out = fopen(CmdLine.GetOutputFileName().c_str(), "wb");

	// For each NodeCnt worth of terms, add the terms to the set of nodes.

	while ( !feof(In) )
	{

		//
		// Read in the McTrie Block
		//

		if (fread(InputBfr, 1, 1, In) == 0 )
		{
			break;
		}

		RecLen = *InputBfr & 0x00FF;
		BreakIf ( fread ( InputBfr, 1, RecLen, In) < RecLen );
	}

    // Read in each block and dump it out.

    // !!!!!!!!!!!!!!!!!!!!!!!!!

	// Close down the files.

	fclose(In);
	fclose(Out);

	// Return

	return(EXIT_SUCCESS);
}
