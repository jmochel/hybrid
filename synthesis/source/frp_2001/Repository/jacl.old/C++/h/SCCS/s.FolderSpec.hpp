h15436
s 00081/00000/00000
d D 1.1 99/11/17 08:08:48 jmochel 2 1
cC
cK44559
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:45 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/FolderSpec.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43644
cPC++/h/FolderSpec.hpp
cRb0a1e84f5cb6bb60
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
E 2
I 1
E 1
