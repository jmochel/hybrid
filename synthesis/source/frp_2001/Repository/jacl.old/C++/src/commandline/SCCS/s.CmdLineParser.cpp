h64276
s 00198/00000/00000
d D 1.1 99/11/17 12:47:03 jmochel 2 1
cC
cK24519
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:59 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/commandline/CmdLineParser.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45314
cPC++/src/commandline/CmdLineParser.cpp
cR2f93d7815cb6ba86
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
    @doc

    $Author: jmochel $

    $Revision: 1.1.1.1 $

    $Date: 1998/06/12 16:36:30 $

    .ClassDesc 'CmdLineParser' parses application command lines 

    02.12.96 Jim JM

    This code is in the public domain
*/

#include <iostream>

#include "Condition.hpp"
#include "BasicStringUtil.hpp"
#include "CmdLineParser.hpp"

/*
    @MethodDesc Constructor
*/

CmdLineParser::CmdLineParser( int ArgCnt, char *ArgList[]) : CmdLineLexer()
{
    Precondition(ArgCnt != 0);
    Precondition(ArgList != 0);

    _RequiredArgCnt = 0;
    _ActualArgCnt = (unsigned int) ArgCnt;

	try 
	{
		Lex(ArgCnt, ArgList);
	}
	catch (...)
	{
		throw;
	}
}


/*
    @MethodDesc Destructor
*/

CmdLineParser::~CmdLineParser( void )
{
}


/*
    @MethodDesc Validates command line arguments
*/

void CmdLineParser::Validate(void)
{
    try 
    {
        map<std::string, std::string>::iterator i;

        // Check that we have at least as many arguments as are required

        if (_TagAndValues.size() < _RequiredArgCnt )
        {
            throw (TooFewArgumentsError(std::string("$CommandLineParser1$ Not all mandatory arguments have been given" )));
        }

        // For each argument execute the appropriate validation.

        for ( i = _TagAndValues.begin(); i != _TagAndValues.end(); i++ )
        {
            _Validations.InvokeAll((*i).first, (*i).second);
        }
    }
    catch (...)
    {
        throw;
    }
}

/*
    @MethodDesc Validates an argument value based on given criteria
*/

bool StdValidation(CmdLineParser* user, CmdLineParser::ArgCriteria& Criteria, std::string& Value)
{
    Precondition(user != 0);

    size_t i;

	try 
	{
		switch ( Criteria.GetType() )
		{
			case CmdLineParser::Any :

				// As it says, anything passes

				break;

			case CmdLineParser::Boolean :

				// This test should be pretty obvious

				if ( (Value[0] != '1') && (Value[0] != '0') )
				{
					throw (CmdLineParser::InvalidArgumentError(std::string("$CommandLineParser2$ Incorrectly formed boolean argument") + Value ) );
				}
				break;

			case CmdLineParser::Alpha :

				for (i = 0; i < Value.length(); i++ )
				{
					if ( !isalpha(Value[i]))
					{
						throw (CmdLineParser::InvalidArgumentError(std::string("$CommandLineParser3$ Incorrectly formed alpha argument") + Value) );
					}
				}

				break;

			case CmdLineParser::AlphaNumeric :

				for (i = 0; i < Value.length(); i++ )
				{
					if (!isalnum(Value[i]))
					{
						throw (CmdLineParser::InvalidArgumentError(std::string("$CommandLineParser4$ Incorrectly formed alphanumeric argument") + Value) );
					}
				}

				break;

			case CmdLineParser::Numeric :

				for (i = 0; i < Value.length(); i++ )
				{
					if (!isdigit(Value[i]))
					{
						throw (CmdLineParser::InvalidArgumentError(std::string("$CommandLineParser5$ Incorrectly formed numeric argument") + Value) );
					}
				}

				break;

			case CmdLineParser::Ascii :

				for (i = 0; i < Value.length(); i++ )
				{
					if (isascii(Value[i]) == false)
					{
						throw (CmdLineParser::InvalidArgumentError(std::string("$CommandLineParser6$ Incorrectly formed ACSII argument") + Value) );
					}
				}

				break;

			case CmdLineParser::Date :

				break;

			case CmdLineParser::Time :

				break;

			case CmdLineParser::Filespec :

				// For now we will assume a range of valid characters, with prejudice towards order and structure :-)

				for (i = 0; i < Value.length(); i++ )
				{
					if ( !isalnum(Value[i]) && ( Value[i] != '.') && ( Value[i] != ':') && ( Value[i] != '\\') && ( Value[i] != '/') && ( Value[i] != '_') )
					{
						throw (CmdLineParser::InvalidArgumentError(std::string("$CommandLineParser7$ Incorrectly formed filespec") + Value) );
					}
				}

				break;

			default :
				break;
		}
	}
	catch (...)
	{
		throw;
	}

    return(true);
}



E 2
I 1
E 1
