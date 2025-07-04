#ifndef FOLDERSPEC_HPP
#define FOLDERSPEC_HPP


/*
    .Contains Declaration of FolderSpec

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


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

        Fully identifies the folder.

    .ClassLongDesc

        A Folder is an abstraction of a directory. The FolderSpec is the identifier needed to fully  
        specify the Folder. 

	.ClassNotes

        FolderInfo is needed to fully describe the attributes of the given Folder
*/

class FolderSpec : public CommonSpec
{
    public:

        // Constructors

        FolderSpec();
        FolderSpec(const FolderSpec& spec);
        FolderSpec(const CommonSpec& spec);
        FolderSpec(const std::string& name);

        // Destructors

        virtual ~FolderSpec();

        // Assignment

        FolderSpec& operator =(const FolderSpec& spec);

        // Logic operators

        signed int CompareTo(const FolderSpec& spec) const;
        bool operator == (const FolderSpec& spec) const;
        bool operator < (const FolderSpec& spec) const;

    protected:

	    void SetDefaults(void);
};

#endif
