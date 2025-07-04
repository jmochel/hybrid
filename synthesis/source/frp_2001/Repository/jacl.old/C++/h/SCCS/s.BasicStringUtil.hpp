h62214
s 00056/00000/00000
d D 1.1 99/11/17 08:07:49 jmochel 2 1
cC
cK24682
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:07:46 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/BasicStringUtil.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43638
cPC++/h/BasicStringUtil.hpp
cR6d905adf5cb6bb7f
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
E 2
I 1
E 1
