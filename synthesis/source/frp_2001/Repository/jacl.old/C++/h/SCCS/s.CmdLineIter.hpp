h09569
s 00068/00000/00000
d D 1.1 99/11/17 08:08:05 jmochel 2 1
cC
cK38594
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:01 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/CmdLineIter.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43640
cPC++/h/CmdLineIter.hpp
cR96c11d9f5cb6bb60
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
#ifndef CMDLINEITER_HPP
#define CMDLINEITER_HPP

#pragma warning(disable : 4786) // Brain-Dead Debugger Format Warning

/*
    .Contains CmdLineIter Class Definition

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

class CmdLineIter : public iterator<input_iterator_tag, pair<std::string, std::string> >
{
    public:

        // Constructors

        CmdLineIter(void);
        CmdLineIter(const CmdLineIter& iter);
        CmdLineIter(int argCnt, char* argList[]);

        // Destructors

        virtual ~CmdLineIter(void);

        // Assignment

        CmdLineIter& operator = (const CmdLineIter& iter);

        // Required other operators

        pair<std::string,std::string> operator*();
        pair<std::string,std::string>* operator->();

        CmdLineIter& operator++();
        CmdLineIter& operator++(int);

    private:

        const char ArgTagStart;
        const char ArgTagSep;

        std::map<std::string, std::string, std::less<std::string>, std::allocator<std::string> >	_TagAndValues;

};


#endif
E 2
I 1
E 1
