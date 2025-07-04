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
