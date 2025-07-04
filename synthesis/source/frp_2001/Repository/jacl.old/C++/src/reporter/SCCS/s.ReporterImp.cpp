h27088
s 00047/00000/00000
d D 1.1 99/11/17 12:49:01 jmochel 2 1
cC
cK53670
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:48:58 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/reporter/ReporterImp.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45326
cPC++/src/reporter/ReporterImp.cpp
cR2f93d7a05cb6ba86
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
E 2
I 1
E 1
