/*
    .Contains Various utility routines for dealing with basic_string

    .Author Jim Jackl-Mochel

    .Date 01.07.97 

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#include <cstdarg>

#include "Condition.hpp"
#include "BasicStringUtil.hpp"

/*
    .ProcDesc

        Converts a std::string to a long

    .ProcNotes

*/

long ToLong(const std::string& str)
{
    long    ResultingLong;

    // Test preconditions
    
    Precondition(str.empty() == false);

    // Convert the std::string 
    
    ResultingLong = atol(str.c_str());


    return(ResultingLong);
}

/*
    .ProcDesc

        Takes a variable argument std::string and generates a

    .ProcNotes

        Not the fastest implementation but speed has not been a critical factor yet.

        It has yet to implement any of the real tough formatting.
*/

std::string Format(const std::string& FormatStr, ...)
{
    unsigned char   Buffer[BasicStringUtilBufferSize+1];
    std::string          Result;
    int             CharsWritten;

    Precondition(FormatStr.empty() == false);

    try
    {
        va_list         ArgList;

        va_start(ArgList, FormatStr);

        CharsWritten = vsprintf( (char*) &Buffer[0], FormatStr.c_str(), ArgList);

        if ( CharsWritten > BasicStringUtilBufferSize) 
        {
            throw( DomainError(std::string("$BasicStringUtil1$ A formatted message overran the temporary buffer")));
        }

        if ( CharsWritten == -1) 
        {
            throw( DomainError(std::string("$BasicStringUtil2$ Unable to format a message as requested")));
        }

        Result = (char*) &Buffer[0];

        va_end(ArgList);
    }
    catch (...)
    {
        throw;
    }

    return(Result);
}

std::string Format(const char* FormatStr, ...)
{
    unsigned char   Buffer[BasicStringUtilBufferSize+1];
    std::string          Result;
    int             CharsWritten;

    Precondition(FormatStr != 0);

    try
    {
        va_list         ArgList;

        va_start(ArgList, FormatStr);

        CharsWritten = vsprintf( (char*) &Buffer[0], FormatStr, ArgList);

        if ( CharsWritten > BasicStringUtilBufferSize) 
        {
            throw( DomainError(std::string("$BasicStringUtil1$ A formatted message overran the temporary buffer")));
        }

        if ( CharsWritten == -1) 
        {
            throw( DomainError(std::string("$BasicStringUtil2$ Unable to format a message as requested")));
        }

        Result = (char*) &Buffer[0];

        va_end(ArgList);
    }
    catch (...)
    {
        throw;
    }

    return(Result);
}

std::string Format(const std::string& FormatStr, va_list ArgList)
{
    unsigned char   Buffer[BasicStringUtilBufferSize+1];
    std::string          Result;
    int             CharsWritten;

    Precondition(FormatStr.empty() == false);

    try
    {
        CharsWritten = vsprintf( (char*) &Buffer[0], FormatStr.c_str(), ArgList);

        if ( CharsWritten > BasicStringUtilBufferSize) 
        {
            throw( DomainError(std::string("$BasicStringUtil1$ A formatted message overran the temporary buffer")));
        }

        if ( CharsWritten == -1) 
        {
            throw( DomainError(std::string("$BasicStringUtil2$ Unable to format a message as requested")));
        }

        Result = (char*) &Buffer[0];
    }
    catch (...)
    {
        throw;
    }

    return(Result);
}

std::string Format(const char* FormatStr, va_list ArgList)
{
    unsigned char   Buffer[BasicStringUtilBufferSize+1];
    std::string          Result;
    int             CharsWritten;

    Precondition(FormatStr != 0);

    try
    {
        CharsWritten = vsprintf( (char*) &Buffer[0], FormatStr, ArgList);

        if ( CharsWritten > BasicStringUtilBufferSize) 
        {
            throw( DomainError(std::string("$BasicStringUtil1$ A formatted message overran the temporary buffer")));
        }

        if ( CharsWritten == -1) 
        {
            throw( DomainError(std::string("$BasicStringUtil2$ Unable to format a message as requested")));
        }

        Result = (char*) &Buffer[0];
    }
    catch (...)
    {
        throw;
    }

    return(Result);
}
