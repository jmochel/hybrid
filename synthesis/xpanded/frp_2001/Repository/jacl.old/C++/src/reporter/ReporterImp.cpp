/*
    .Author: Jim JM

    .Date: 12.11.94 

    .Contains   Reporter is a message reporting class

	.Copyright

		This code is in the public domain

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $

*/

#pragma warning(disable:4786)  // remove warning about brain dead debug symbol limit 

#include <iostream>

#include <cstdarg>
#include <cstdlib>

#ifndef BASICSTRINGUTIL_HPP
#include "BasicStringUtil.hpp"
#endif

#ifndef REPORTERIMP_HPP
#include "ReporterImp.hpp"
#endif

// Constructors

ReporterImp::ReporterImp(ReporterCfg& Cfg)
{
    
}


// Destructors

ReporterImp::~ReporterImp(void)
{
}
