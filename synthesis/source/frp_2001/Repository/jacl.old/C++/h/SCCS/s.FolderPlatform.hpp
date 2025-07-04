h24903
s 00044/00000/00000
d D 1.1 99/11/17 08:08:44 jmochel 2 1
cC
cK53153
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:41 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/FolderPlatform.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43644
cPC++/h/FolderPlatform.hpp
cR8e325ccf5cb6bb60
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


#ifndef FOLDERPLATFORM_HPP
#define FOLDERPLATFORM_HPP

/*
    .Contains: Folder Platform Specific Initialisation Function Definitions

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit 

#ifndef PLATFORM_HPP
#include "Platform.hpp"
#endif

#if ( JACL_OS == JACL_WINNT )
#pragma message("Info: JACL Folder is being configured for use on WinNT platform")
extern FolderImp* Win32FolderInitFxn(FolderSpec& Spec, Fldr::RetrievalMask mask);
    const FolderInitFxn DefFolderInitFxn = Win32FolderInitFxn;
#elif ( JACL_OS == JACL_WIN32 )
#pragma message("Info: JACL Folder is being configured for use on Win32 platform")
    extern FolderImp* Win32FolderInitFxn(FolderSpec& spec, Fldr::RetrievalMask mask);
    const FolderInitFxn DefFolderInitFxn = Win32FolderInitFxn;
#elif ( JACL_OS == JACL_GENERICUNIX )
#pragma message("Info: JACL Folder is being configured for use on Generic UNIX platforms")
    extern FolderImp* UnixFolderInitFxn(FolderSpec& spec,Fldr::RetrievalMask mask);
    const FolderInitFxn DefFolderInitFxn = UnixFolderInitFxn;
#else
#	error "There is no DefFolderInitFxn defined for the Folders classes on this platform"
#endif

#endif  // FOLDERPLATFORM_HPP
E 2
I 1
E 1
