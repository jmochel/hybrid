h15083
s 00097/00000/00000
d D 1.1 99/11/17 08:07:46 jmochel 2 1
cC
cK44825
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:07:42 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/AppInfo.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43638
cPC++/h/AppInfo.hpp
cR6b4512df5cb6bb7f
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
#ifndef APPINFO_HPP
#define APPINFO_HPP


/*
    @doc

    .Contains Declaration of AppInfo

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/




// JACL Library Headers

#include <string>

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

using namespace std;

/*
	.ClassDesc

	.ClassNotes

*/

class AppInfo
{
    public:

        // Constructors

        AppInfo();

        AppInfo(const std::string& Name, const std::string& Rev);

        AppInfo(const AppInfo& AppInfo);

	    // Set the Defaults

	    void SetDefaults(void);

        // Destructors

        virtual ~AppInfo();

        // Accessors

        const std::string GetName(void) const;
        const std::string GetRev(void) const;

        // Mutators

        void SetName(const std::string& Name);
        void SetRev(const std::string& Rev);

        // Assignment

        AppInfo& operator =(const AppInfo& aAppInfo);

        // Logic operators
        
        signed int  CompareTo(const AppInfo& aAppInfo);
        bool operator == (const AppInfo& aAppInfo);
        

    protected:

        std::string  _Name;	// Name of the application
        std::string  _Rev;	// Revision, as a string, of the application
};

#endif
E 2
I 1
E 1
