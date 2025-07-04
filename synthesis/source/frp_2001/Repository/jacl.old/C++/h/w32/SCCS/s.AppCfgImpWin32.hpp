h09747
s 00079/00000/00000
d D 1.1 99/11/17 12:45:41 jmochel 2 1
cC
cK37902
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:45:37 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/w32/AppCfgImpWin32.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45306
cPC++/h/w32/AppCfgImpWin32.hpp
cR2f93d7985cb6ba86
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


#ifndef APPCFGIMPWIN32_HPP
#define APPCFGIMPWIN32_HPP


/*
    @doc

    .Contains Win32AppCfgImp

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#pragma message("AppCfgImpWin32.hpp : Check API Naming")
#pragma message("AppCfgImpWin32.hpp : Check API Types")
#pragma message("AppCfgImpWin32.hpp : Check API Returns")
#pragma message("AppCfgImpWin32.hpp : Check API Exceptions")
#pragma message("AppCfgImpWin32.hpp : Check API Exception Specifications")
#pragma message("AppCfgImpWin32.hpp : Check Comments")

// Standard Library Headers

#include <cstdlib>

#include <windows.h>

// Local Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

// Local Headers

#include "AppCfgImp.hpp"

/*
    @ClassDesc

        The Win32 implementation of a AppCfg class.

    @ClassNotes
*/

class Win32AppCfgImp : public AppCfgImp
{
    // @Access Public

    public:

        // Constructors

        Win32AppCfgImp(AppCfgCfg& cfg);

        // Destructor

        ~Win32AppCfgImp(void);

    // @Access Private

    private:

        // Assignment 

        Win32AppCfgImp& operator = (const Win32AppCfgImp& win32AppCfgImp);
};
#endif  // APPCFGIMPWIN32_HPP

E 2
I 1
E 1
