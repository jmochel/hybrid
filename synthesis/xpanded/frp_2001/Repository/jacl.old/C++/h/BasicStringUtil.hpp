#ifndef BASICSTRINGUTIL_HPP
#define BASICSTRINGUTIL_HPP

/*
    .Contains utility functions for manipulating basic_string

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/

#include <string>

using namespace std;

const size_t BasicStringUtilBufferSize = 2048;

/*
    .ProcDesc

        Converts a std::string to a long

    .ProcNotes

        Should generate an exception when unable to convert. Instead it returns it as a 0.
*/

long    ToLong(const std::string& str);

/*
    .ProcDesc

        Converts a "sprintf" style format std::string with arguments to a single std::string.
*/

std::string  Format(const std::string& Format, ...);

/*
    .ProcDesc

        Converts a "sprintf" style format std::string with arguments to a single std::string.
*/

std::string  Format(const char* Format, ...);
std::string  Format(const char* Format, va_list);
std::string  Format(const std::string& Format, va_list);

#endif // BASICSTRINGTUTIL_HPP
