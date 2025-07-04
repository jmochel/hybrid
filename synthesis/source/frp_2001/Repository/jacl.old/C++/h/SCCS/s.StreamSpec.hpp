h36311
s 00088/00000/00000
d D 1.1 99/11/17 08:09:58 jmochel 2 1
cC
cK65386
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:54 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/StreamSpec.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43651
cPC++/h/StreamSpec.hpp
cRda3935cf5cb6bb60
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
E 2
I 1
E 1
