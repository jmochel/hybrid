h57015
s 00131/00000/00000
d D 1.1 99/11/17 08:09:39 jmochel 2 1
cC
cK21297
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:09:35 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/StdHdr.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43649
cPC++/h/StdHdr.hpp
cRaea1cbaf5cb6bb60
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
#ifndef STDHDR_HPP
#define STDHDR_HPP


/*
    $Author: jmochel $

    $Revision: 1.1.1.1 $

    $Date: 1998/06/12 16:36:30 $

    .Contains

    .Copyright DateHere Jim Jackl-Mochel, All rights reserved
*/

// JACL Library Headers

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef DATETIME_HPP
#include "Datetime.hpp"
#endif

#ifndef REVISION_HPP
#include "Revision.hpp"
#endif

/*
	.ClassDesc

	.ClassNotes

*/

class StdHdr
{
    public:

        // Constructors

        StdHdr();
        StdHdr(const StdHdr& StdHdr);

	    // Set the Defaults

	    void SetDefaults(void);

        // Destructors

        virtual ~StdHdr();

        // Accessors

        const std::string GetId(void) const;
        const Revision GetRevision(void) const;
        const std::string GetFileUniqueID(void) const;
        const DateTime GetCreationTimeStamp(void) const;
        const DateTime GetModificationTimeStamp(void) const;
        const Checksum GetChecksum(void) const;
        const RecRange GetRecCnt(void) const;
        const RecSize GetMaxRecSize(void) const;
        const RecSize GetMinRecSize(void) const;
        const std::string GetPhysFmtID(void) const;
        const Revision GetPhysFmtRev(void) const;
        const ULong GetPhysFmtDataLen(void) const;
        const ByteBfr GetPhysFmtData(void) const;
        const std::string GetContentModID(void) const;
        const Revision GetContentModRev(void) const;
        const ULong GetContentModDataLen(void) const;
        const ByteBfr GetContentModData(void) const;

        // Mutators

        void SetId(const std::string& Id);
        void SetRevision(const Revision& Revision);
        void SetFileUniqueID(const std::string& FileUniqueID);
        void SetCreationTimeStamp(const DateTime& CreationTimeStamp);
        void SetModificationTimeStamp(const DateTime& ModificationTimeStamp);
        void SetChecksum(const Checksum& Checksum);
        void SetRecCnt(const RecRange& RecCnt);
        void SetMaxRecSize(const RecSize& MaxRecSize);
        void SetMinRecSize(const RecSize& MinRecSize);
        void SetPhysFmtID(const std::string& PhysFmtID);
        void SetPhysFmtRev(const Revision& PhysFmtRev);
        void SetPhysFmtDataLen(const ULong& PhysFmtDataLen);
        void SetPhysFmtData(const ByteBfr& PhysFmtData);
        void SetContentModID(const std::string& ContentModID);
        void SetContentModRev(const Revision& ContentModRev);
        void SetContentModDataLen(const ULong& ContentModDataLen);
        void SetContentModData(const ByteBfr& ContentModData);

        // Assignment

        StdHdr& operator =(const StdHdr& aStdHdr);

        // Logic operators
        
        SInt  CompareTo(const StdHdr& aStdHdr);
        bool operator ==(const StdHdr& aStdHdr);
        
    protected:

        std::string      _Id;	        // The ID for the Header Format (6 Bytes long)
        std::string      _Endian;        // The Endian-ness of the file. (4 bytes long)
        ULong       _Len;           // Length of the header in bytes (4 Bytes long)
        Revision    _Revision;	    // The revision of the Header Format 
        std::string      _FileUniqueID;	// The unique ID for this file
        DateTime    _CreationTimeStamp;	// When the file was created
        DateTime    _ModificationTimeStamp;	// When the file was last modified
        Checksum    _Checksum;	    // Checksum for the file. Excluding the header.
        RecRange    _RecCnt;	    // Number of records stored in the file.
        RecSize     _MaxRecSize;	// Size in bytes of the largest record.
        RecSize     _MinRecSize;	// Size in bytes of the smallest record.
        std::string      _PhysFmtID;	    // ID for the physical format of the file (8 Bytes long). 
        Revision    _PhysFmtRev;	// Revision for the physical format of the file. 
        ULong       _PhysFmtDataLen;// Length of the physical format data for the file. (in bytes) 
        ByteBfr     _PhysFmtData;	// The physical format data for the file. (in bytes) 
        std::string      _ContentModID;	// ID for the content model of the file (8 Bytes long). 
        Revision    _ContentModRev;	// Revision for the content model of the file. 
        ULong       _ContentModDataLen;	// Length of the content model data for the file. (in bytes) 
        ByteBfr     _ContentModData;	// The content model data for the file. 
};

#endif
E 2
I 1
E 1
