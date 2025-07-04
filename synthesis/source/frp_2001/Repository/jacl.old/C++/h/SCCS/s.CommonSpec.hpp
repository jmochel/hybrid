h52085
s 00146/00000/00000
d D 1.1 99/11/17 08:08:16 jmochel 2 1
cC
cK15695
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:13 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/CommonSpec.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43641
cPC++/h/CommonSpec.hpp
cR9da9126f5cb6bb60
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
#ifndef COMMONSPEC_HPP
#define COMMONSPEC_HPP


/*
    @doc

    .Contains Declaration of CommonSpec

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/


#pragma message("CommonSpec.hpp : Check API Naming")
#pragma message("CommonSpec.hpp : Check API Types")
#pragma message("CommonSpec.hpp : Check API Returns")
#pragma message("CommonSpec.hpp : Check API Exceptions")
#pragma message("CommonSpec.hpp : Check API Exception Specifications")
#pragma message("CommonSpec.hpp : Check Comments")



// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif


/*
	@ClassDesc

        Common specification for Folders and Streams. 


*/

class CommonSpec
{
    // @Access  Public

    public:

        // Typedefs, enums


        // @MemberDesc Type for the CommonSpec.

        enum Type
        {
           Folder,
           Stream,
        };

        // @MemberDesc    Void Constructor

        CommonSpec();

        // @MemberDesc    Copy Constructor

        CommonSpec(const CommonSpec& commonSpec);

        // @MemberDesc    Constructor

        CommonSpec(Type type,const std::string& name);

        // @MemberDesc    Destructor

        virtual ~CommonSpec();

        // @MemberDesc    Gets Type

        const CommonSpec::Type GetType(void) const;

        // @MemberDesc    Gets Name

        const std::string& GetName(void) const;

        // Mutators

        // @MemberDesc    Sets Type

        void SetType(const CommonSpec::Type& type);

        // @MemberDesc    Sets Name

        void SetName(const std::string& name);

        // @MemberDesc    Assignment Operator

        CommonSpec& operator =(const CommonSpec& spec);

        // Logic operators

        // @MemberDesc    Generic compare for CommonSpec

        signed int  CompareTo(const CommonSpec& spec) const;

        // @MemberDesc    Equality operator

        bool operator ==(const CommonSpec& spec) const;

        // @MemberDesc    Less than  operator

        bool operator <(const CommonSpec& spec) const;

        // @MemberDesc    Inequality operator

        bool operator !=(const CommonSpec& spec) const;
        bool operator !=(const char* spec) const;

    // @Access Protected

    protected:

        // @MemberDesc Sets the Default values for the objects data members

	    void SetDefaults(void);
        
    // @Access Protected

    protected:

        // @MemberDesc      The type of the CommonSpec (is it Folder or Stream)

        Type  _Type;	

        // @MemberDesc      The std::string containing the identifying text 

        std::string  _Name;	
};
#endif
E 2
I 1
E 1
