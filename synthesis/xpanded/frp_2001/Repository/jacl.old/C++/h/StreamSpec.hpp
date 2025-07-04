#ifndef STREAMSPEC_HPP
#define STREAMSPEC_HPP


/*
    .Contains Declaration of StreamSpec

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#pragma message("StreamSpec.hpp : Check API Naming")
#pragma message("StreamSpec.hpp : Check API Types")
#pragma message("StreamSpec.hpp : Check API Returns")
#pragma message("StreamSpec.hpp : Check API Exceptions")
#pragma message("StreamSpec.hpp : Check API Exception Specifications")
#pragma message("StreamSpec.hpp : Check Comments")



// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

#ifndef COMMONSPEC_HPP
#include "CommonSpec.hpp"
#endif

/*
	.ClassDesc

        Fully identifies the stream to the file system

	.ClassNotes

        StreamInfo is needed for a full description of the attributes of the stream
*/

class StreamSpec : public CommonSpec
{
    public:

        // Typedefs, enums


        // Constructors

        StreamSpec();
        StreamSpec(const StreamSpec& spec);
        StreamSpec(std::string& name);

        // Destructors

        virtual ~StreamSpec();

        // Assignment

        StreamSpec& operator =(const StreamSpec& spec);
        StreamSpec& operator =(const char* spec);


        // Logic operators

        signed int  CompareTo(const StreamSpec& spec) const;
        bool operator ==(const StreamSpec& spec) const;
        bool operator <(const StreamSpec& spec) const;

    protected:

	    void SetDefaults(void);
};
#endif
