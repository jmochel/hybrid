#ifndef APPCFG_HPP
#define APPCFG_HPP


/*
    @doc

    .Contains Declaration of <???>, and Envelope class

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/


#pragma message("AppCfg.hpp : Check API Naming")
#pragma message("AppCfg.hpp : Check API Types")
#pragma message("AppCfg.hpp : Check API Returns")
#pragma message("AppCfg.hpp : Check API Exceptions")
#pragma message("AppCfg.hpp : Check API Exception Specifications")
#pragma message("AppCfg.hpp : Check Comments")


// JACL Library Headers

#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef DATETIME_HPP
#include "Datetime.hpp"
#endif

#ifndef APPCFGIMP_HPP
#include "AppCfgImp.hpp"
#endif



// Declarations

typedef AppCfgImp* (*AppCfgInitFxn)(AppInfo& info);

#ifndef APPCFGPLATFORM_HPP
#include "AppCfgPlatform.hpp"
#endif

/*
	@ClassDesc

        AppCfg <???>

	@ClassNotes

        <???>

*/

class AppCfg
{
    // @Access Public

    public:

        // Typedefs, enums


        // @MemberDesc    Void Constructor

        AppCfg();

        // @MemberDesc    Copy Constructor

        AppCfg(const AppCfg& appCfg);

        // @MemberDesc    Constructor

        AppCfg(AppInfo& info, AppCfgInitFxn initFxn = DefAppCfgInitFxn);

        // @MemberDesc    Destructor

        virtual ~AppCfg();


        // @MemberDesc    Gets Info

        const AppInfo& GetInfo(void) const;


        // @MemberDesc    Gets KeyAndValue

        const std::string& GetKeyAndValue(void) const;


        // @MemberDesc    Gets Value

        const std::string& GetValue(void) const;

        // @MemberDesc    Sets Info

        void SetInfo(const AppInfo& Info);


        // @MemberDesc    Sets KeyAndValue

        void SetKeyAndValue(const std::string& KeyAndValue);


        // @MemberDesc    Sets Value

        void SetValue(const std::string& Value);


        // @MemberDesc    Assignment Operator

        AppCfg& operator =(const AppCfg& appCfg);

        // Logic operators

        // @MemberDesc    Generic compare for AppCfg

        signed int  CompareTo(const AppCfg& appCfg);
        #pragma message(__FILE__ "(1) :"  "AppCfg::CompareTo needs to be implemented or removed")

        // @MemberDesc    Equality operator

        bool operator ==(const AppCfg& appCfg);
        

    // @Access Protected

    protected:

        // @MemberDesc Sets the Default values for the objects data members

	    void SetDefaults(void);

    // @Access Protected

    protected:

        // @MemberDesc <???>

        AppInfo  _Info;


        // @MemberDesc <???>

        std::string  _KeyAndValue;


        // @MemberDesc <???>

        std::string  _Value;


        // @MemberDesc <???>

        std::map<std::string,std::string>  _KeyAndValueMap;

        // @MemberDesc
        //      Platform specific implementation of AppCfg

        AppCfgImp*    _Imp;

        // @MemberDesc
        //      Platform specific initialization function.

        AppCfgInitFxn _InitFxn;
};

#endif  // APPCFG_HPP
