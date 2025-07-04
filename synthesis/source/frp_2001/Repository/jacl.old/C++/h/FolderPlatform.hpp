

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
