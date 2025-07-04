h62554
s 00070/00000/00000
d D 1.1 99/11/17 08:08:36 jmochel 2 1
cC
cK26093
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:33 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/FolderInfo.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43643
cPC++/h/FolderInfo.hpp
cR898bbbdf5cb6bb60
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
#ifndef FOLDERINFO_HPP
#define FOLDERINFO_HPP


/*
    .Contains Declaration of FolderInfo

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#pragma message("FolderInfo.hpp : Check API Exceptions")
#pragma message("FolderInfo.hpp : Check API Exception Specifications")
#pragma message("FolderInfo.hpp : Check Comments")

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

class FolderInfo
{
    public:

        // Constructors

        FolderInfo();
        FolderInfo(const FolderInfo& info);

        // Destructors

        virtual ~FolderInfo();

        // Assignment

        FolderInfo& operator =(const FolderInfo& info);

        // Logic operators

        signed int  CompareTo(const FolderInfo& folderInfo) const;
        bool operator ==(const FolderInfo& folderInfo) const;
        bool operator <(const FolderInfo& folderInfo) const;

    protected:

	    void SetDefaults(void);
};

#endif
E 2
I 1
E 1
