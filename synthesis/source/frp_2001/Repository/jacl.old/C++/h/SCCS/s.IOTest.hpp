h24970
s 00059/00000/00000
d D 1.1 99/11/17 08:08:56 jmochel 2 1
cC
cK55012
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:53 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/IOTest.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43645
cPC++/h/IOTest.hpp
cRb55d141f5cb6bb60
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
    
E 2
I 1
E 1
