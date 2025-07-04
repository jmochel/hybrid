h39170
s 00100/00000/00000
d D 1.1 99/11/17 08:10:22 jmochel 2 1
cC
cK02802
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:10:19 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/SystemSpec.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43654
cPC++/h/SystemSpec.hpp
cRc897213f5cb6bb60
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
#ifndef SYSTEMSPEC_HPP
#define SYSTEMSPEC_HPP


/*
    .Contains Declaration of SystemSpec

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#pragma message("SystemSpec.hpp : Check API Naming")
#pragma message("SystemSpec.hpp : Check API Types")
#pragma message("SystemSpec.hpp : Check API Returns")
#pragma message("SystemSpec.hpp : Check API Exceptions")
#pragma message("SystemSpec.hpp : Check API Exception Specifications")
#pragma message("SystemSpec.hpp : Check Comments")



// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif


/*
	.ClassDesc

	.ClassNotes

*/

class SystemSpec
{
    public:

        // Typedefs, enums


        // Constructors

        SystemSpec();
        SystemSpec(const SystemSpec& systemSpec);
        SystemSpec(const std::string& name);

        // Destructors

        virtual ~SystemSpec();

        // Accessors

        const std::string& GetName(void) const;

        // Mutators

        void SetName(const std::string& name);

        // Assignment

        SystemSpec& operator =(const SystemSpec& spec);

        // Logic operators

        signed int  CompareTo(const SystemSpec& spec);
        #pragma message(__FILE__ "(1) :"  "SystemSpec::CompareTo needs to be implemented or removed")
        bool operator ==(const SystemSpec& spec);

    protected:

	    /*
            .MethodDesc

                Sets the Default values for the objects data members

        */

	    void SetDefaults(void);
       

    protected:

        std::string  _Name;	
};
#endif
E 2
I 1
E 1
