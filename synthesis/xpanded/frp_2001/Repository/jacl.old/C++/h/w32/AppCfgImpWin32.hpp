

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

