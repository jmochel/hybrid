h16207
s 00086/00000/00000
d D 1.1 99/11/17 12:48:58 jmochel 2 1
cC
cK42824
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:48:54 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/reporter/ReporterCfg.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45325
cPC++/src/reporter/ReporterCfg.cpp
cR2f93d7a15cb6ba86
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
    .Author: Jim JM

    .Date: 12.11.94 

    .Contains   ReporterCfg implementation

	.Copyright

		This code is in the public domain

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $

*/

#include <iostream>

#include <cstdarg>
#include <cstdlib>

#ifndef BASICSTRINGUTIL_HPP
#include "BasicStringUtil.hpp"
#endif

#ifndef REPORTERCFG_HPP
#include "ReporterCfg.hpp"
#endif


// Constructors

ReporterCfg::ReporterCfg()
{
    _ServiceMask = Rprtr::Console | Rprtr::Log;
}

ReporterCfg::ReporterCfg(const ReporterCfg& aReporterCfg)
{
    _ServiceMask = aReporterCfg._ServiceMask;
}

ReporterCfg::ReporterCfg(Rprtr::ServiceMask aMask)
{
    _ServiceMask = aMask;
}

// Destructors

ReporterCfg::~ReporterCfg(void)
{
}

// Accessors

const Rprtr::ServiceMask ReporterCfg::GetServiceMask(void) const
{
    return(_ServiceMask);
}

// Mutators

void ReporterCfg::SetServiceMask(const Rprtr::ServiceMask aMask)
{
    _ServiceMask = aMask;
}

// Assignment

ReporterCfg& ReporterCfg::operator = (const ReporterCfg& aReporterCfg)
{
    _ServiceMask = aReporterCfg._ServiceMask;

    return(*this);
}

ReporterCfg& ReporterCfg::operator = (const Rprtr::ServiceMask aServiceMask)
{
    _ServiceMask = aServiceMask;

    return(*this);
}

E 2
I 1
E 1
