h08499
s 00171/00000/00000
d D 1.1 99/11/17 08:08:52 jmochel 2 1
cC
cK35348
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:49 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/FolderSpecManipulator.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43645
cPC++/h/FolderSpecManipulator.hpp
cRb31163cf5cb6bb60
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
#ifndef FOLDERSPECMANIPULATOR_HPP
#define FOLDERSPECMANIPULATOR_HPP


/*
    @doc

    .Contains Declaration of FolderSpecManipulator

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


#pragma message("FolderSpecManipulator.hpp : Check API Naming")
#pragma message("FolderSpecManipulator.hpp : Check API Types")
#pragma message("FolderSpecManipulator.hpp : Check API Returns")
#pragma message("FolderSpecManipulator.hpp : Check API Exceptions")
#pragma message("FolderSpecManipulator.hpp : Check API Exception Specifications")
#pragma message("FolderSpecManipulator.hpp : Check Comments")



// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

#ifndef VOLUMESPEC_HPP
#include "VolumeSpec.hpp"
#endif

#ifndef SYSTEMSPEC_HPP
#include "SystemSpec.hpp"
#endif

#ifndef FOLDERSPEC_HPP
#include "FolderSpec.hpp"
#endif

/*
	@ClassDesc

        Manipulator for accessing component portions of a FolderSpec

*/

class FolderSpecManipulator
{
    // @Access  Public

    public:

        // @MemberDesc    Void Constructor

        FolderSpecManipulator();

        // @MemberDesc    Copy Constructor

        FolderSpecManipulator(const FolderSpecManipulator& folderSpecManipulator);

        // @MemberDesc    Constructor

        FolderSpecManipulator(const FolderSpec& spec);

        // @MemberDesc    Destructor

        virtual ~FolderSpecManipulator();

        // @MemberDesc    Gets System

        const SystemSpec& GetSystem(void) const;

        // @MemberDesc    Gets Volume

        const VolumeSpec& GetVolume(void) const;

        // @MemberDesc    Gets Canonical

        const FolderSpec& GetCanonical(void) const;

        // @MemberDesc    Gets Path

        const std::string& GetPath(void) const;

        // @MemberDesc    Gets Name

        const std::string& GetName(void) const;

        // @MemberDesc    Sets System

        void SetSystem(const SystemSpec& system);

        // @MemberDesc    Sets Volume

        void SetVolume(const VolumeSpec& volume);

        // @MemberDesc    Sets Canonical

        void SetCanonical(const FolderSpec& canonical);

        // @MemberDesc    Sets Path

        void SetPath(const std::string& path);

        // @MemberDesc    Sets Name

        void SetName(const std::string& name);

        // @MemberDesc    Assignment Operator

        FolderSpecManipulator& operator =(const FolderSpecManipulator& aFolderSpecManipulator);

        // Logic operators

        // @MemberDesc    Generic compare for FolderSpecManipulator

        signed int  CompareTo(const FolderSpecManipulator& aFolderSpecManipulator);
        #pragma message(__FILE__ "(1) :"  "FolderSpecManipulator::CompareTo needs to be implemented or removed")

        // @MemberDesc    Equality operator

        bool operator ==(const FolderSpecManipulator& aFolderSpecManipulator);

    // @Access Protected

    protected:

        // @MemberDesc Sets the Default values for the objects data members

	    void SetDefaults(void);
        
    // @Access Protected

    protected:

        // @MemberDesc      System Specifier associated with the FolderSpec

        SystemSpec  _System;	

        // @MemberDesc      Volume Specifier associated with the FolderSpec

        VolumeSpec  _Volume;	

        // @MemberDesc      Fully expanded specifier associated with the original FolderSpec

        FolderSpec  _Canonical;	

        // @MemberDesc      Path associated with the FolderSpec

        std::string  _Path;	

        // @MemberDesc      Name of the Folder

        std::string  _Name;	
};
#endif
E 2
I 1
E 1
