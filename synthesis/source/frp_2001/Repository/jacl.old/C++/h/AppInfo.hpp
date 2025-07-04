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
