h33266
s 00217/00000/00000
d D 1.1 99/11/17 08:08:29 jmochel 2 1
cC
cK62280
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:25 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Exceptions.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43642
cPC++/h/Exceptions.hpp
cR84e51aaf5cb6bb60
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
E 2
I 1
E 1
