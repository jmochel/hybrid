#ifndef CMDLINEPRS_HPP
#define CMDLINEPRS_HPP

#pragma warning(disable : 4786) // Brain-Dead Debugger Format Warning

/*
    .Contains CmdLineParser 

    .Author Jim Jackl-Mochel

    .Date 01.14.93

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/
    
#include <stdexcept>
#include <string>

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef CMDLINELEXER_HPP
#include "CmdLineLexer.hpp"
#endif

#ifndef SIGCBMGR_HPP
#include "SigCBMgr.hpp"
#endif

using namespace std;

/*
    .ClassDesc

            Command line parsing class with validation

    .ClassNotes

            The CmdLineLexer class breaks down command lines of the form 
            
            /tagname=value into mapped pairs of text. The CmdLineParser validates 
            the semantics of the value in as general or specific a manner as necessary.
*/


class CmdLineParser : public CmdLineLexer
{
    public:

        enum ArgType  
        {
            Any              = 0x0000,
            Boolean          = 0x0001,
            Alpha            = 0x0002,
            AlphaNumeric     = 0x0003,
            Numeric          = 0x0004,
            Ascii            = 0x0005,
            Date             = 0x0006,
            Time             = 0x0007,
            Filespec         = 0x0008
        };

        enum  ArgNature 
        {
            Optional         = 0x0000,
            Required         = 0x0001
        };


        class ArgCriteria
        {
            public:
            
                // Constructors

                ArgCriteria( void )
                {
                    SetDefaults();
                }

                ArgCriteria(std::string& Name, ArgType Type, ArgNature Nature, std::string& Desc)
                {
                    _Name = Name;
                    _Type = Type;
                    _Nature = Nature;
                    _Desc = Desc;
                }

                ArgCriteria(const ArgCriteria& Criteria)
                {
                    _Name = Criteria._Name;
                }

                void SetDefaults(void)
                {
                    _Name = "";
                    _Type = Any;
                    _Nature = Optional;
                    _Desc = "";
                }

                // Destructors

                virtual ~ArgCriteria( void )
                {
                }

                // Accessors

                const std::string& GetName(void) const
                {
                    return(_Name);
                }

                const ArgType GetType (void) const
                {
                    return(_Type);
                }

                const ArgNature GetNature(void) const
                {
                    return(_Nature);
                }

                const std::string& GetDesc(void) const
                {
                    return(_Desc);
                }

                // Assignment Operator

                ArgCriteria& ArgCriteria::operator = (const ArgCriteria& Criteria)
                {
                    if ( this == &Criteria )
                    {
                        return(*this);
                    }

                    _Name = Criteria._Name;
                    _Type = Criteria._Type;
                    _Nature = Criteria._Nature;
                    _Desc = Criteria._Desc;

                    return(*this);
                }

                // Comparison Operator

                bool operator == (const ArgCriteria& Criteria) const
                {
                    return ( bool) ((_Name==Criteria._Name)&&(_Type==Criteria._Type)&&(_Nature == Criteria._Nature));
                }
                
                // I/O Stream overloads

                friend ostream& operator << (ostream& os, const ArgCriteria& Criteria)
                {
                    if ( Criteria._Nature == Required )
                    {
                        os << "Required: "; 
                    }

                    os << Criteria._Name << " - " << Criteria._Desc << "\n";
                    
                    return(os);
                }

        private:

            std::string      _Name;          // argument tag
            ArgType     _Type;          // argument's expected datatype
            ArgNature   _Nature;        // Required or optional
            std::string      _Desc;          // Description of the argument
        };

        typedef SignatureCallbackMgr<CmdLineParser, ArgCriteria, std::string, std::string>::FxnType   FxnType;

    protected:

        SignatureCallbackMgr<CmdLineParser, ArgCriteria, std::string, std::string>  _Validations;

        size_t   _RequiredArgCnt;
        size_t   _ActualArgCnt;
        std::string   _ProgramName;
        std::string   _ProgramDesc;
        std::string   _Usage;

    public:

        // Constructors

        CmdLineParser(int ArgCnt, char *ArgValues[]);

        // Destructor

        virtual ~CmdLineParser( void );

        // Other methods

        void SetDesc(std::string& ProgramName, std::string&  ProgramDesc, std::string& Usage);

        /*
            .MethodDesc Registers a command line argument for processing

            .MethodDesc Registers a command line argument of the form
                        /<tag>=<value> with semantic information for the value as well 
                        a callback for validating the semantics of the value.

            .MethodParm.Name - The tag that should be encountered on the command line

            .MethodParm.Type - The data type of the arguments
        */


        void Register(std::string& Tag, CmdLineParser::ArgType Type, CmdLineParser::ArgNature Nature, std::string& Desc, FxnType CallbackFxn)
        {
            ArgCriteria Criteria(Tag, Type, Nature, Desc);
    
            _Validations.Register(this, Criteria, CallbackFxn, Tag);

            if ( Nature == Required )
            {
                _RequiredArgCnt++;
            }
        }

        virtual void CmdLineParser::Validate(void);

    public:

        /*
            .ClassDesc

                Command line parsing class exception

            .ClassNotes

                Indicates that to many arguments were supplied.
        */


        class TooManyArgumentsError : public StartupInputError
        {
            public:

                TooManyArgumentsError(std::string& Desc) : StartupInputError(Desc)
                {
                }
        };

        /*
            .ClassDesc

                Command line parsing class exception

            .ClassNotes

                Indicates that two few mandatory arguments were supplied.
        */

        class TooFewArgumentsError : public StartupInputError
        {
            public:

                TooFewArgumentsError(std::string& Desc) : StartupInputError(Desc)
                {
                }
        };

        /*
            .ClassDesc

                Command line parsing class exception

            .ClassNotes

                Indicates that an invalid argument was supplied
        */

        class InvalidArgumentError : public StartupInputError
        {
            public:

                InvalidArgumentError(std::string& Desc) : StartupInputError(Desc)
                {
                }
        };
};

// non-member callback function

bool StdValidation(CmdLineParser* Owner, CmdLineParser::ArgCriteria& Criteria, std::string& Value);

#endif // CMDLINEPRS_HPP
