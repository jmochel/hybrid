/*
    @doc

    .Contains: AppInfo Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "AppInfo.hpp"

// Constructors

AppInfo::AppInfo()
{
	SetDefaults();
}

AppInfo::AppInfo(const AppInfo& aAppInfo)
{

    _Name = aAppInfo._Name;
    _Rev = aAppInfo._Rev;

}

AppInfo::AppInfo(const std::string& Name, const std::string& Rev)
{

    _Name = Name;
    _Rev = Rev;
}

// Set Defaults

void AppInfo::SetDefaults(void)
{
    _Name = "";
    _Rev = "";
}

// Destructors

AppInfo::~AppInfo()
{
}

// Accessors


const std::string AppInfo::GetName(void) const
{
    return(_Name);
}


const std::string AppInfo::GetRev(void) const
{
    return(_Rev);
}


// Mutators

void AppInfo::SetName(const std::string& Name)
{
	_Name = Name;
}

void AppInfo::SetRev(const std::string& Rev)
{
	_Rev = Rev;
}


// Logic operators

/*
    .MethodDesc Compares two AppInfo objects
    
    .MethodReturn
    
        -1 if aAppInfo is less than *this
        0 if the two objects are equal.
        +1 if aAppInfo is greater than *this
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  AppInfo::CompareTo(const AppInfo& aAppInfo)
{
    int CurrCompare;

    CurrCompare = _Name.compare(aAppInfo._Name);

    if (CurrCompare == 0)
    {
        return(_Rev.compare(aAppInfo._Rev));
    }

    return(CurrCompare);
}

/*
    .MethodDesc Compares two AppInfo objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool AppInfo::operator ==(const AppInfo& aAppInfo)
{
    return(CompareTo(aAppInfo) == 0);
}

// Assignment

AppInfo& AppInfo::operator =(const AppInfo& aAppInfo)
{
    if ( this == &aAppInfo )
    {
        return(*this);
    }

    _Name = aAppInfo._Name;
    _Rev = aAppInfo._Rev;

    return(*this);
}

