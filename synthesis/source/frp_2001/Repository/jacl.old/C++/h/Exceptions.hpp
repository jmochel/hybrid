#ifndef EXCEPTIONS_HPP
#define EXCEPTIONS_HPP

/*

    $Header: h:\\Repository/jacl/c++/h/Exceptions.hpp,v 1.1.1.1 1998/06/12 16:36:29 jmochel Exp $

    .Contains

        Definitions for basic JACL Exceptions

    .OriginalAuthor

        jsjm

    .Copyright

        This code is in the public domain
*/

#include <string>
#include <stdexcept>

using namespace std;

/*
    .ClassDesc

        Basic JACL Exception class 

    .ClassLongDesc

        The basic exception class with a stored description of the problem.

    .ClassNotes

        Implements the virtual what() method of the exception base class.

        This should never be thrown directly. Only derived calsses should be thrown.
*/

class Exception : public std::exception
{
	public:

		Exception(const std::string& What) : _What(What)
		{
		}

		virtual ~Exception()
		{
		}

		virtual const char *what()
		{
			return((const char* ) _What.c_str());
		}

	protected:

		std::string	_What;
};


/*
    .ClassDesc

        Base exception for internal logic errors


    .ClassNotes

        If the programmer could be responsible it is probably a InternalError.

*/

class InternalError : public Exception
{
	public:

		InternalError(const std::string& Desc) : Exception(Desc)
		{
		}

		virtual ~InternalError()
		{
		}
};


/*
    .ClassDesc

        Base exception for external errors

    .ClassNotes

        If it is a runtime occurrence most likely caused by external input or operating conditions
        it is probably a ExternalError

*/

class ExternalError : public Exception
{
	public:

		ExternalError(const std::string& Desc) : Exception(Desc)
		{
		}

		virtual ~ExternalError()
		{
		}
};

/*
    .ClassDesc

        Base Domain specific exception class


    .ClassNotes

        If it is an error specific to a given problem domain that it is probably a DomainError

*/

class DomainError : public ExternalError
{
	public:

		DomainError(const std::string& Desc) : ExternalError(Desc)
		{
		}

		virtual ~DomainError()
		{
		}
};

/*
    .ClassDesc

        An exception for violated preconditions

    .ClassNotes

        If a precondition for a procedure or methods call is violated, 
		it is a PreconditionError. This is usually thrown by the Precondition()
		macro.

*/

class PreconditionError : public InternalError
{
    public:

        PreconditionError(const std::string& Desc) : InternalError(Desc)
        {
        }

        virtual ~PreconditionError()
        {
        }

};

/*
    .ClassDesc

        An exception for violated postconditions

    .ClassNotes

        If a postcondition for a procedure or methods call is violated, 
		it is a PostconditionError. This is usually thrown by the Postcondition()
		macro.

*/


class PostconditionError : public InternalError
{
    public:

        PostconditionError(const std::string& Desc) : InternalError(Desc)
        {
        }

        virtual ~PostconditionError()
        {
        }
};

/*
    .ClassDesc

        An exception for invalid external input 
       
*/


class StartupInputError : public ExternalError
{
    public:

        StartupInputError(const std::string& Desc) : ExternalError(Desc)
        {
        }

        virtual ~StartupInputError()
        {
        }
};


#endif // EXCEPTIONS_HPP
