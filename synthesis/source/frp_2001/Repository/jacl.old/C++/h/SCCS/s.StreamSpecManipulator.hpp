h06097
s 00166/00000/00000
d D 1.1 99/11/17 08:10:02 jmochel 2 1
cC
cK32777
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:58 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/StreamSpecManipulator.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43652
cPC++/h/StreamSpecManipulator.hpp
cRdcaea40f5cb6bb60
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
#ifndef STREAMSPECMANIPULATOR_HPP
#define STREAMSPECMANIPULATOR_HPP


/*
    @doc

    .Contains Declaration of StreamSpecManipulator

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#pragma message("StreamSpecManipulator.hpp : Check API Naming")
#pragma message("StreamSpecManipulator.hpp : Check API Types")
#pragma message("StreamSpecManipulator.hpp : Check API Returns")
#pragma message("StreamSpecManipulator.hpp : Check API Exceptions")
#pragma message("StreamSpecManipulator.hpp : Check API Exception Specifications")
#pragma message("StreamSpecManipulator.hpp : Check Comments")



// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

#ifndef FOLDERSPEC_HPP
#include "FolderSpec.hpp"
#endif

#ifndef STREAMSPEC_HPP
#include "StreamSpec.hpp"
#endif

/*
	@ClassDesc

        General manipulator for breaking down and reconstructing StreamSpecs.        
*/

class StreamSpecManipulator
{
    // @Access  Public

    public:

        // @MemberDesc    Void Constructor

        StreamSpecManipulator();

        // @MemberDesc    Copy Constructor

        StreamSpecManipulator(const StreamSpecManipulator& streamSpecManipulator);

        // @MemberDesc    Constructor

        StreamSpecManipulator(const StreamSpec& spec);

        // @MemberDesc    Destructor

        virtual ~StreamSpecManipulator();

        // @MemberDesc    Gets FolderSpec

        const FolderSpec& GetFolderSpec(void) const;

        // @MemberDesc    Gets Canonical

        const StreamSpec& GetCanonical(void) const;

        // @MemberDesc    Gets Root

        const std::string& GetRoot(void) const;

        // @MemberDesc    Gets Extension

        const std::string& GetExtension(void) const;

        // @MemberDesc    Gets Name

        const std::string& GetName(void) const;

        // @MemberDesc    Sets FolderSpec

        void SetFolderSpec(const FolderSpec& folderSpec);

        // @MemberDesc    Sets Canonical

        void SetCanonical(const StreamSpec& canonical);

        // @MemberDesc    Sets Root

        void SetRoot(const std::string& root);

        // @MemberDesc    Sets Extension

        void SetExtension(const std::string& extension);

        // @MemberDesc    Sets Name

        void SetName(const std::string& name);

        // @MemberDesc    Assignment Operator

        StreamSpecManipulator& operator =(const StreamSpecManipulator& manipulator);

        // Logic operators

        // @MemberDesc    Generic compare for StreamSpecManipulator

        signed int  CompareTo(const StreamSpecManipulator& manipulator);
        #pragma message(__FILE__ "(1) :"  "StreamSpecManipulator::CompareTo needs to be implemented or removed")

        // @MemberDesc    Equality operator

        bool operator ==(const StreamSpecManipulator& aStreamSpecManipulator);

    // @Access Protected

    protected:

        // @MemberDesc Sets the Default values for the objects data members

	    void SetDefaults(void);
        
    // @Access Protected

    protected:

        // @MemberDesc      Folder Specification associated with the StreamSpec

        FolderSpec  _FolderSpec;	

        // @MemberDesc      Full Stream Specification derived from original StreamSpec

        StreamSpec  _Canonical;	

        // @MemberDesc      Root name of the StreamSpec

        std::string  _Root;	

        // @MemberDesc      Extension name of the StreamSpec

        std::string  _Extension;	

        // @MemberDesc      Full name of the StreamSpec

        std::string  _Name;	
};
#endif
E 2
I 1
E 1
