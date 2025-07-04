h18486
s 00119/00000/00000
d D 1.1 99/11/17 08:08:09 jmochel 2 1
cC
cK47206
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:05 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/CmdLineLexer.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43640
cPC++/h/CmdLineLexer.hpp
cR990dceaf5cb6bb60
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
#ifndef CMDLINELEXER_HPP
#define CMDLINELEXER_HPP

#pragma warning(disable : 4786) // Brain-Dead Debugger Format Warning

/*
    @doc

    .Author Jim Jackl-Mochel

    .Date 01.12.93

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/

#include <stdexcept>
#include <string>
#include <map>

using namespace std;

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

/*
    @ClassDesc

        Lexically analyses command lines and breaks them down into tag,value pairs

    @ClassNotes

        At the moment the default implementation uses the following lexically significant characters in command line
    
        '/','='

    
        The command line syntax is : ProgramName /tag1=value1 /tag2=value2


    @ToDo

        Change this to an input iterator so that multiple syntaxes can be supported using the same command line perser.
*/

class CmdLineLexer
{
    // @Access Public

    public:

        // @MemberDesc Constructor

        CmdLineLexer( void );

        // @MemberDesc Destructor

        virtual ~CmdLineLexer( void );

        // @MemberDesc Lex the entire command line

        void Lex(int ArgCnt, char* ArgList[]);

    // @Access Public

    public:

        class CommandlineSyntaxError : public DomainError
        {
            public:

                CommandlineSyntaxError(std::string& desc) : DomainError(desc)
                {
                }
        };

    // @Access Private

    private:

        // @MemberDesc Lex a single entire command line tag value pair

        bool Lex(std::string& Arg);

    // @Access Private

    private:

        CmdLineLexer& operator = (const CmdLineLexer& Lexer);
        CmdLineLexer(const CmdLineLexer& Lexer);

    // @Access Protected

    protected:

        // @MemberDesc The character indicating the start of a tag.

        const char ArgTagStart;

        // @MemberDesc The character that separates a tag and a value

        const char ArgTagSep;

        // @MemberDesc Map of the tags and value pairs.

        std::map<std::string, std::string, std::less<std::string>, std::allocator<std::string> >	    _TagAndValues;
};

#endif // CMDLINELEXER_HPP
E 2
I 1
E 1
