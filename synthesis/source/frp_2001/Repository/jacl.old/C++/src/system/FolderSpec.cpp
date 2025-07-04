/*
    .Contains: FolderSpec Implementation

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef REPORTER_HPP
#include "Reporter.hpp"
#endif

#include "FolderSpec.hpp"

// Constructors

FolderSpec::FolderSpec() : CommonSpec()
{
	SetDefaults();
}

FolderSpec::FolderSpec(const FolderSpec& spec) : CommonSpec((CommonSpec&) spec)
{
}

FolderSpec::FolderSpec(const CommonSpec& spec) : CommonSpec(spec)
{
}

FolderSpec::FolderSpec(const std::string& name) : CommonSpec(CommonSpec::Folder, name)
{
}

// Set Defaults

void FolderSpec::SetDefaults(void)
{
}

// Destructors

FolderSpec::~FolderSpec()
{
}

// Logic operators

/*
    .MethodDesc Compares two FolderSpec objects
    
    .MethodReturn
    
        -1 if *this falls into sequence before aFolderSpec
        0 if the two objects are equal.
        +1 if *this falls into sequence after aFolderSpec
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  FolderSpec::CompareTo(const FolderSpec& spec) const
{
    return( ((CommonSpec&) *this).CompareTo((CommonSpec&) spec));
}

/*
    .MethodDesc Compares two FolderSpec objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool FolderSpec::operator ==(const FolderSpec& spec) const
{
    return(CompareTo(spec) == 0);
}

/*
    .MethodDesc 

         Less than
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool FolderSpec::operator <(const FolderSpec& spec) const
{
    return(CompareTo(spec) == -1);
}

// Assignment

FolderSpec& FolderSpec::operator =(const FolderSpec& spec)
{
    if ( this == &spec )
    {
        return(*this);
    }

    (CommonSpec&) *this = (CommonSpec&) spec;
    
    return(*this);
}
