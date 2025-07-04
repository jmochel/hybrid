h17642
s 00335/00000/00000
d D 1.1 99/11/17 12:47:31 jmochel 2 1
cC
cK45273
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:47:28 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/io/stream/StdHdr.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45317
cPC++/src/io/stream/StdHdr.cpp
cR2f93d7b95cb6ba86
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
/*
    $Author: jmochel $

    $Revision: 1.1.1.1 $

    $Date: 1998/06/12 16:36:32 $

    .Contains

    .Copyright 12.07.96 Jim Jackl-Mochel, All rights reserved
*/

#include "StdHdr.hpp"

// Constructors

StdHdr::StdHdr()
{
	SetDefaults();
}

StdHdr::StdHdr(const StdHdr& aStdHdr)
{

    _Id = aStdHdr._Id;
    _Revision = aStdHdr._Revision;
    _FileUniqueID = aStdHdr._FileUniqueID;
    _CreationTimeStamp = aStdHdr._CreationTimeStamp;
    _ModificationTimeStamp = aStdHdr._ModificationTimeStamp;
    _Checksum = aStdHdr._Checksum;
    _RecCnt = aStdHdr._RecCnt;
    _MaxRecSize = aStdHdr._MaxRecSize;
    _MinRecSize = aStdHdr._MinRecSize;
    _PhysFmtID = aStdHdr._PhysFmtID;
    _PhysFmtRev = aStdHdr._PhysFmtRev;
    _PhysFmtDataLen = aStdHdr._PhysFmtDataLen;
    _PhysFmtData = aStdHdr._PhysFmtData;
    _ContentModID = aStdHdr._ContentModID;
    _ContentModRev = aStdHdr._ContentModRev;
    _ContentModDataLen = aStdHdr._ContentModDataLen;
    _ContentModData = aStdHdr._ContentModData;

}

// Set Defaults

void StdHdr::SetDefaults(void)
{
    _Id = "";
    _Revision = 0;
    _FileUniqueID.resize(0);
    _CreationTimeStamp.Reset();
    _ModificationTimeStamp.Reset();
    _Checksum = 0;
    _RecCnt = 0;
    _MaxRecSize = 0;
    _MinRecSize = 0;
    _PhysFmtID.resize(0);
    _PhysFmtRev = 0;
    _PhysFmtDataLen = 0;
    _PhysFmtData.resize(0);
    _ContentModID.resize(0);
    _ContentModRev = 0;
    _ContentModDataLen = 0;
    _ContentModData.resize(0);
}

// Destructors

StdHdr::~StdHdr()
{

}

// Accessors


const std::string StdHdr::GetId(void) const
{
    return(_Id);
}


const Revision StdHdr::GetRevision(void) const
{
    return(_Revision);
}


const std::string StdHdr::GetFileUniqueID(void) const
{
    return(_FileUniqueID);
}


const DateTime StdHdr::GetCreationTimeStamp(void) const
{
    return(_CreationTimeStamp);
}


const DateTime StdHdr::GetModificationTimeStamp(void) const
{
    return(_ModificationTimeStamp);
}


const Checksum StdHdr::GetChecksum(void) const
{
    return(_Checksum);
}


const RecRange StdHdr::GetRecCnt(void) const
{
    return(_RecCnt);
}


const RecSize StdHdr::GetMaxRecSize(void) const
{
    return(_MaxRecSize);
}


const RecSize StdHdr::GetMinRecSize(void) const
{
    return(_MinRecSize);
}


const std::string StdHdr::GetPhysFmtID(void) const
{
    return(_PhysFmtID);
}


const Revision StdHdr::GetPhysFmtRev(void) const
{
    return(_PhysFmtRev);
}


const ULong StdHdr::GetPhysFmtDataLen(void) const
{
    return(_PhysFmtDataLen);
}


const ByteBfr StdHdr::GetPhysFmtData(void) const
{
    return(_PhysFmtData);
}


const std::string StdHdr::GetContentModID(void) const
{
    return(_ContentModID);
}


const Revision StdHdr::GetContentModRev(void) const
{
    return(_ContentModRev);
}


const ULong StdHdr::GetContentModDataLen(void) const
{
    return(_ContentModDataLen);
}


const ByteBfr StdHdr::GetContentModData(void) const
{
    return(_ContentModData);
}


// Mutators

void StdHdr::SetId(const std::string& Id)
{
	_Id = Id;
}

void StdHdr::SetRevision(const Revision& Revision)
{
	_Revision = Revision;
}

void StdHdr::SetFileUniqueID(const std::string& FileUniqueID)
{
	_FileUniqueID = FileUniqueID;
}

void StdHdr::SetCreationTimeStamp(const DateTime& CreationTimeStamp)
{
	_CreationTimeStamp = CreationTimeStamp;
}

void StdHdr::SetModificationTimeStamp(const DateTime& ModificationTimeStamp)
{
	_ModificationTimeStamp = ModificationTimeStamp;
}

void StdHdr::SetChecksum(const Checksum& Checksum)
{
	_Checksum = Checksum;
}

void StdHdr::SetRecCnt(const RecRange& RecCnt)
{
	_RecCnt = RecCnt;
}

void StdHdr::SetMaxRecSize(const RecSize& MaxRecSize)
{
	_MaxRecSize = MaxRecSize;
}

void StdHdr::SetMinRecSize(const RecSize& MinRecSize)
{
	_MinRecSize = MinRecSize;
}

void StdHdr::SetPhysFmtID(const std::string& PhysFmtID)
{
	_PhysFmtID = PhysFmtID;
}

void StdHdr::SetPhysFmtRev(const Revision& PhysFmtRev)
{
	_PhysFmtRev = PhysFmtRev;
}

void StdHdr::SetPhysFmtDataLen(const ULong& PhysFmtDataLen)
{
	_PhysFmtDataLen = PhysFmtDataLen;
}

void StdHdr::SetPhysFmtData(const ByteBfr& PhysFmtData)
{
	_PhysFmtData = PhysFmtData;
}

void StdHdr::SetContentModID(const std::string& ContentModID)
{
	_ContentModID = ContentModID;
}

void StdHdr::SetContentModRev(const Revision& ContentModRev)
{
	_ContentModRev = ContentModRev;
}

void StdHdr::SetContentModDataLen(const ULong& ContentModDataLen)
{
	_ContentModDataLen = ContentModDataLen;
}

void StdHdr::SetContentModData(const ByteBfr& ContentModData)
{
	_ContentModData = ContentModData;
}


// Logic operators

/*
    .MethodDesc Compares two StdHdr objects
    
    .MethodReturn
    
        -1 if aStdHdr is less than *this
        0 if the two objects are equal.
        +1 if aStdHdr is greater than *this
    
    .MethodNotes
    
        Explain the collating sequence. 
*/    
    
signed int  StdHdr::CompareTo(const StdHdr& aStdHdr)
{
      return(0);  
}

/*
    .MethodDesc Compares two StdHdr objects
    
    .MethodReturn
    
        true if the two objects are equal false if they are not
    
    .MethodNotes
    
        Explain the equality criteria.
*/    

bool StdHdr::operator ==(const StdHdr& aStdHdr)
{
    return(CompareTo(aStdHdr) == 0);
}

// Assignment

StdHdr& StdHdr::operator =(const StdHdr& aStdHdr)
{
    if ( this == &aStdHdr )
    {
        return(*this);
    }

    _Id = aStdHdr._Id;
    _Revision = aStdHdr._Revision;
    _FileUniqueID = aStdHdr._FileUniqueID;
    _CreationTimeStamp = aStdHdr._CreationTimeStamp;
    _ModificationTimeStamp = aStdHdr._ModificationTimeStamp;
    _Checksum = aStdHdr._Checksum;
    _RecCnt = aStdHdr._RecCnt;
    _MaxRecSize = aStdHdr._MaxRecSize;
    _MinRecSize = aStdHdr._MinRecSize;
    _PhysFmtID = aStdHdr._PhysFmtID;
    _PhysFmtRev = aStdHdr._PhysFmtRev;
    _PhysFmtDataLen = aStdHdr._PhysFmtDataLen;
    _PhysFmtData = aStdHdr._PhysFmtData;
    _ContentModID = aStdHdr._ContentModID;
    _ContentModRev = aStdHdr._ContentModRev;
    _ContentModDataLen = aStdHdr._ContentModDataLen;
    _ContentModData = aStdHdr._ContentModData;

    return(*this);
}

E 2
I 1
E 1
