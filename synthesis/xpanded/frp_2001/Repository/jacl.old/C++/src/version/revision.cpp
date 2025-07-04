
/*
    $Author: jmochel $

    $Revision: 1.1.1.1 $

    $Date: 1998/06/12 16:36:31 $

    .Contains

    .Copyright DateHere Jim Jackl-Mochel, All rights reserved
*/

#include <iostream>


#include "Stream.hpp"
#include "Revision.hpp"

// Constructors

Revision::Revision()
{
	SetDefaults();
}

Revision::Revision(const Revision& aRevision)
{
    _Major = aRevision._Major;
    _Minor = aRevision._Minor;
}

// Set Defaults

void Revision::SetDefaults(void)
{
    _Major = 0;
    _Minor = 0;
}

// Destructors

Revision::~Revision()
{

}

// Accessors


const UInt Revision::GetMajor(void) const
{
    return(_Major);
}

const UInt Revision::GetMinor(void) const
{
    return(_Minor);
}

// Mutators

void Revision::SetMajor(const UInt& Major)
{
	_Major = Major;
}

void Revision::SetMinor(const UInt& Minor)
{
	_Minor = Minor;
}


// Logic operators

/*
    .MethodDesc Compares two Revision objects
    
    .MethodReturn
    
        -1 if aRevision is less than *this
        0 if the two objects are equal.
        +1 if aRevision is greater than *this
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  Revision::CompareTo(const Revision& aRevision)
{
    if ( _Major == aRevision._Major )
    {
        if ( _Minor == aRevision._Minor )
        {
            return(0);
        }
        else if ( _Minor > aRevision._Minor )
        {
            return(1);
        }
        else {
            return(-1);
        }
    }
    else if ( _Major > aRevision._Major)
    {
        return(1);
    }
    else {
       return(-1);
    }

}

/*
    .MethodDesc Compares two Revision objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool Revision::operator ==(const Revision& aRevision)
{
    return(CompareTo(aRevision) == 0);
}

// Assignment

Revision& Revision::operator =(const Revision& aRevision)
{
    if ( this == &aRevision )
    {
        return(*this);
    }

    _Major = aRevision._Major;
    _Minor = aRevision._Minor;

    return(*this);
}

Revision& Revision::operator =(const UInt Revision)
{
    _Major = Revision;
    _Minor = Revision;

    return(*this);
}

// I/O

ostream& operator <<(ostream& os, const Revision& aRevision)
{
    // Put out the name of the class

    os << "Revision:\n";

    // Now the data members of the class
    os << "\t" << "Major=" << aRevision._Major << "\n";
    os << "\t" << "Minor=" << aRevision._Minor << "\n";

    return(os);
}

#ifdef TEST_REVISION

#include <cstdlib>

#include "Revision.hpp"

// Unit Test 

void main(int argc, char* argv[])
{
    Revision    AStackRevision;
    Revision*   ADynamicRevision;
    
    // Test the dynamic allocation 
    
    ADynamicRevision = new Revision();

    delete(ADynamicRevision);
    
    return(EXIT_SUCCESS);
}

#endif

