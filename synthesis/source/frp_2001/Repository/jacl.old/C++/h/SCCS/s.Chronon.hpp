h08161
s 00123/00000/00000
d D 1.1 99/11/17 08:08:01 jmochel 2 1
cC
cK37917
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:07:57 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Chronon.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43640
cPC++/h/Chronon.hpp
cR9476d59f5cb6bb60
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
#ifndef CHRONON_HPP
#define CHRONON_HPP


/*
    @doc

    .Contains Declaration of Chronon

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/


#pragma message("Chronon.hpp : Check API Naming")
#pragma message("Chronon.hpp : Check API Types")
#pragma message("Chronon.hpp : Check API Returns")
#pragma message("Chronon.hpp : Check API Exceptions")
#pragma message("Chronon.hpp : Check API Exception Specifications")
#pragma message("Chronon.hpp : Check Comments")



// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif


/*
	@ClassDesc

        Time and Date representation

	@ClassNotes

        Based on notes by ...

*/

class Chronon
{
    // @Access  Public

    public:

        // Typedefs, enums



        // @MemberDesc    Void Constructor

        Chronon();

        // @MemberDesc    Copy Constructor

        Chronon(const Chronon& chronon);

        // @MemberDesc    Constructor

        Chronon(const std::std::string& dataBlock);

        // @MemberDesc    Destructor

        virtual ~Chronon();

        // @MemberDesc    Gets DataBlock

        const std::string& GetDataBlock(void) const;

        // Mutators


        // @MemberDesc    Sets DataBlock

        void SetDataBlock(const std::string& dataBlock);

        // @MemberDesc    Assignment Operator

        Chronon& operator =(const Chronon& aChronon);

        // Logic operators

        // @MemberDesc    Generic compare for Chronon

        signed int  CompareTo(const Chronon& aChronon);
        #pragma message(__FILE__ "(1) :"  "Chronon::CompareTo needs to be implemented or removed")

        // @MemberDesc    Equality operator

        bool operator ==(const Chronon& aChronon);

    // @Access Protected

    protected:

        // @MemberDesc Sets the Default values for the objects data members

	    void SetDefaults(void);
        
    // @Access Protected

    protected:

        // @MemberDesc The binary stored version of the date.

        std::string  _DataBlock;	
};
#endif
E 2
I 1
E 1
