h56210
s 00111/00000/00000
d D 1.1 99/11/17 12:46:59 jmochel 2 1
cC
cK16675
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:56 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/commandline/CmdLineLexer.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45313
cPC++/src/commandline/CmdLineLexer.cpp
cR2f93d7825cb6ba86
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

	.Contains CmdLineLexer implementation

	.Author Jim JM

	.Date	02.01.96 

	.Copyright 
    
		This code is in the public domain

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $

*/

#include "Condition.hpp"
#include "CmdLineLexer.hpp"

// @MethodDesc Constructors

CmdLineLexer::CmdLineLexer(void) : ArgTagStart('/') , ArgTagSep('=') , _TagAndValues()
{
}

// @MethodDesc Destructor

CmdLineLexer::~CmdLineLexer( void )
{
}

/*
    @MethodDesc Lex the entire command line
*/

void CmdLineLexer::Lex(int ArgCount, char* ArgList[])
{
    Precondition(ArgCount != 0);
    Precondition(ArgList != 0);

    std::string Arg;

	try 
	{
		while( --ArgCount > 0 && *++ArgList != 0)
		{
			if ( (*ArgList)[0] == ArgTagStart)
			{
					Arg = (*ArgList)+1;

					if ( !Lex(Arg) )
					{
						throw CommandlineSyntaxError(std::string("$CommandLineLexer1$ Incorrectly formed commandline argument") + std::string(Arg) );
					}
			}
			else {
				throw CommandlineSyntaxError(std::string("$CommandLineLexer1$ Incorrectly formed commandline argument") + std::string(Arg) );
			}
		}
	}
	catch (...)
	{
		throw;
	}
}

/*
    @MethodDesc - Lex a single command line argument

    @MethodNotes - Should be handed a argument of the form "tag=value".
*/

bool CmdLineLexer::Lex(std::string& Arg)
{
    size_t  TagSepNdx;
    std::string  Tag;
    std::string  Value;

	try 
	{
		// Verify that there is a tag seperator

		if ((TagSepNdx = Arg.find(ArgTagSep)) == std::string::npos)
		{
			return(false);
		}

		// Break the argument into tag and value

		Tag.replace(0, Tag.length(), Arg, 0, TagSepNdx);
		Value.replace(0, Arg.length() - TagSepNdx - 1, Arg.c_str() + TagSepNdx + 1);

		// Place them in the list.

		_TagAndValues.insert( map<std::string, std::string>::value_type(Tag, Value));
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
