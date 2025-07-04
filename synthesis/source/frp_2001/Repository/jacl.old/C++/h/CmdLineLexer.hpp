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
