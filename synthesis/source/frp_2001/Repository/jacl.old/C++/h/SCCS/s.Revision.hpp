h21647
s 00089/00000/00000
d D 1.1 99/11/17 08:09:27 jmochel 2 1
cC
cK51030
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:24 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Revision.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43648
cPC++/h/Revision.hpp
cRa7bcb93f5cb6bb60
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
#ifndef REVISION_HPP
#define REVISION_HPP

/*
    .Contains Revision Class Definition

    .Author Jim Jackl-Mochel

    .Date 01.12.93

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

// Forward declarations

class Stream;

// JACL Library Headers

#ifndef UTIL_HPP
#include "Util.hpp"
#endif


/*
	.ClassDesc

	.ClassNotes

*/

class Revision
{
    public:

        // Constructors

        Revision();
        Revision(const Revision& Revision);

	    // Set the Defaults

	    void SetDefaults(void);

        // Destructors

        virtual ~Revision();

        // Accessors

        const UInt GetMajor(void) const;
        const UInt GetMinor(void) const;

        // Mutators

        void SetMajor(const UInt& Major);
        void SetMinor(const UInt& Minor);


        // Assignment

        Revision& operator =(const Revision& aRevision);
        Revision& operator =(const UInt Revision);

        // Logic operators

        signed int  CompareTo(const Revision& aRevision);
        bool operator ==(const Revision& aRevision);

	    // Raw I/O

        friend ostream& operator << (ostream& os, const Revision& aRevision);

        friend Stream& operator << (Stream& os, const Revision& aRevision);
        friend Stream& operator >> (Stream& is, Revision& aRevision);

    protected:

        UInt  _Major;	// Major Revision Number 
        UInt  _Minor;	// Minor Revision Number 
};

#endif
E 2
I 1
E 1
