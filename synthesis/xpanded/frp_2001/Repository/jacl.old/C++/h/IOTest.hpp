#ifndef IOTEST_HPP
#define IOTEST_HPP

/*
    .Contains Command Line parser and other defines for IO testing apps

    .Author Jim Jackl-Mochel

    .Date 12.07.97

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#include "CmdLineParser.hpp"

class IOTestCmdLineParser : public CmdLineParser
{
	public:

		IOTestCmdLineParser(int ArgC, char* ArgV[]) : CmdLineParser(ArgC, ArgV)
		{
			// register validation info

			Register(std::string("is"), CmdLineParser::Filespec, CmdLineParser::Required, std::string("Input Stream name"), StdValidation);
			Register(std::string("os"), CmdLineParser::Filespec, CmdLineParser::Required, std::string("Output Stream name"), StdValidation); 
			Register(std::string("iter"), CmdLineParser::Numeric, CmdLineParser::Required, std::string("Iteration Count"), StdValidation);
			Register(std::string("block"), CmdLineParser::Numeric, CmdLineParser::Required, std::string("Block Size"), StdValidation);
		}

		std::string  GetInputSpec(void)
		{
			return(_TagAndValues[std::string("is")]);
		}

		std::string  GetOutputSpec(void)
		{
			return(_TagAndValues[std::string("os")]);
		}

		size_t  GetIterationCnt(void)
		{
			return(atoi(_TagAndValues[std::string("iter")].c_str()));
		}

		size_t  GetBlockSize(void)
		{
			return(atoi(_TagAndValues[std::string("block")].c_str()));
		}

};

#endif // IOTEST_HPP
    
