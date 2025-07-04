h02767
s 00123/00000/00000
d D 1.1 99/11/17 08:47:00 jmochel 2 1
cC
cK30005
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:46:56 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/FolderSpec.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43874
cPC++/src/system/FolderSpec.cpp
cRe6873cef5cb6bb65
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
E 2
I 1
E 1
