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

