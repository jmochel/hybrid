#ifndef VOLUMESPEC_HPP
#define VOLUMESPEC_HPP


/*
    .Contains Declaration of VolumeSpec

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#pragma message("VolumeSpec.hpp : Check API Naming")
#pragma message("VolumeSpec.hpp : Check API Types")
#pragma message("VolumeSpec.hpp : Check API Returns")
#pragma message("VolumeSpec.hpp : Check API Exceptions")
#pragma message("VolumeSpec.hpp : Check API Exception Specifications")
#pragma message("VolumeSpec.hpp : Check Comments")



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

class VolumeSpec
{
    public:

        // Typedefs, enums


        // Constructors

        VolumeSpec();
        VolumeSpec(const VolumeSpec& spec);
        VolumeSpec(const std::string& name);

        // Destructors

        virtual ~VolumeSpec();

        // Accessors

        const std::string& GetName(void) const;

        // Mutators

        void SetName(const std::string& name);

        // Assignment

        VolumeSpec& operator =(const VolumeSpec& spec);

        // Logic operators

        signed int  CompareTo(const VolumeSpec& spec);
        #pragma message(__FILE__ "(1) :"  "VolumeSpec::CompareTo needs to be implemented or removed")
        bool operator ==(const VolumeSpec& spec);

    protected:

	    /*
            .MethodDesc

                Sets the Default values for the objects data members

        */

	    void SetDefaults(void);
        

    protected:

        std::string  _Name;	//
};
#endif
