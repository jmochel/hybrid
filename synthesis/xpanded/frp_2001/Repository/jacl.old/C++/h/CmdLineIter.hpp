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
