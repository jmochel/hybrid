
/*
    .Contains: FolderInfo Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/


#pragma message("FolderInfo.cpp : Check that all Exceptions are thrown.")
#pragma message("FolderInfo.cpp : Check that all Exceptions are caught and/or rethrown.")
#pragma message("FolderInfo.cpp : Check that all caught exceptions return us to a known state.")
#pragma message("FolderInfo.cpp : Check local variable and parameter naming")
#pragma message("FolderInfo.cpp : Check Pre and Postconditions")
#pragma message("FolderInfo.cpp : Lint")
#pragma message("FolderInfo.cpp : PRofile")


#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef CONDITION_HPP
#include "Condition.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "FolderInfo.hpp"

// Constructors

FolderInfo::FolderInfo()
{
	SetDefaults();
}

FolderInfo::FolderInfo(const FolderInfo& aFolderInfo)
{
}

// Set Defaults

void FolderInfo::SetDefaults(void)
{
}

// Destructors

FolderInfo::~FolderInfo()
{
}

// Logic operators

/*
    .MethodDesc Compares two FolderInfo objects
    
    .MethodReturn
    
        -1 if *this falls into sequence before aFolderInfo
        0 if the two objects are equal.
        +1 if *this falls into sequence after aFolderInfo
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  FolderInfo::CompareTo(const FolderInfo& aFolderInfo) const
{
    return(1);
}

/*
    .MethodDesc Compares two FolderInfo objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool FolderInfo::operator ==(const FolderInfo& aFolderInfo) const
{
    return(CompareTo(aFolderInfo) == 0);
}

/*
    .MethodDesc Compares two FolderInfo objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool FolderInfo::operator <(const FolderInfo& aFolderInfo) const
{
    return(CompareTo(aFolderInfo) == -1);
}

// Assignment

FolderInfo& FolderInfo::operator =(const FolderInfo& aFolderInfo)
{
    if ( this == &aFolderInfo )
    {
        return(*this);
    }

    return(*this);
}
