#ifndef APPCFGIMP_HPP
#define APPCFGIMP_HPP

/*
    @doc

    .Contains: AppCfgImp Definition, a letter class for AppCfg

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/


#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit 


#pragma message("AppCfg.hpp : Check API Naming")
#pragma message("AppCfg.hpp : Check API Types")
#pragma message("AppCfg.hpp : Check API Returns")
#pragma message("AppCfg.hpp : Check API Exceptions")
#pragma message("AppCfg.hpp : Check API Exception Specifications")
#pragma message("AppCfg.hpp : Check Comments")

#include <map>

#ifndef APPINFO_HPP
#include "AppInfo.hpp"
#endif

// Declarations


/*
	@ClassDesc

		AppCfg is a base class for platform specific implemenation 

	@ClassNotes

        <???>

*/


class AppCfgImp
{
    // @Access  Public

    public:
               
        // @MemberDesc    Void Constructor

        AppCfgImp();

        // @MemberDesc    Copy Constructor

        AppCfgImp(const AppCfgImp& appCfg);

        // @MemberDesc    Constructor

        AppCfgImp(AppInfo& cfg);

        // @MemberDesc    Sets the default state

	    void SetDefaults(void);

        // @MemberDesc    Destructor

        virtual ~AppCfgImp();

        // Accessors

        // @MemberDesc    Gets Info
        const AppInfo GetInfo(void) const;
        // @MemberDesc    Gets KeyAndValue
        const std::string GetKeyAndValue(void) const;
        // @MemberDesc    Gets Value
        const std::string GetValue(void) const;

        // Mutators

        // @MemberDesc    Sets Info
        void SetInfo(const AppInfo& Info);
        // @MemberDesc    Sets KeyAndValue
        void SetKeyAndValue(const std::string& KeyAndValue);
        // @MemberDesc    Sets Value
        void SetValue(const std::string& Value);

        // @MemberDesc    Assignment operator

        AppCfgImp& operator =(const AppCfgImp& appCfgImp);

        // @MemberDesc    Generic compare for AppCfg
        
        signed int  CompareTo(const AppCfgImp& appCfgImp);
        #pragma message(__FILE__ "(1) :"  "AppCfgimp::CompareTo needs to be implemented or removed")

        // @MemberDesc    Equality operator

        bool operator ==(const AppCfgImp& appCfgImp);

    // @Access Protected

    protected:

        // @MemberDesc
        //      <???>

        AppInfo  _Info;

        // @MemberDesc
        //      <???>

        std::string  _KeyAndValue;
        // @MemberDesc
        //      <???>

        std::string  _Value;
        // @MemberDesc
        //      <???>

        std::map<std::string, std::string>  _KeyAndValueMap;
};
#endif  // APPCFGIMP_HPP
