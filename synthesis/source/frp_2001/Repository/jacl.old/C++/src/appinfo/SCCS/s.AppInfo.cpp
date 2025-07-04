h03653
s 00151/00000/00000
d D 1.1 99/11/17 12:46:20 jmochel 2 1
cC
cK31436
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:46:17 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/appinfo/AppInfo.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45310
cPC++/src/appinfo/AppInfo.cpp
cR2f93d78d5cb6ba86
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

E 2
I 1
E 1
