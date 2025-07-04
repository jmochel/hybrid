h03103
s 00078/00000/00000
d D 1.1 99/11/17 08:08:24 jmochel 2 1
cC
cK32645
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:21 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Datetime.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43642
cPC++/h/Datetime.hpp
cR8246ee7f5cb6bb60
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
#ifndef DATETIME_HPP
#define DATETIME_HPP

/*
    .Contains DateTime Class Definition

    .Author Jim Jackl-Mochel

    .Date 12.04.94

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/

/*
	.ClassDesc	
		
		A wrapper for the ANSI time_t data type
*/

#include <ctime>

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

class DateTime 
{
    public:

        // Accessors

        UInt GetDaylightSavingTime(void);
        UInt GetYear(void);
        UInt GetMonth(void);
        UInt GetDay(void);
        UInt GetHour(void);
        UInt GetMinute(void);
        UInt GetSecond(void);        

        // Accessors

        void SetDaylightSavingTime(UInt);
        void SetYear(UInt);
        void SetMonth(UInt);
        void SetDay(UInt);
        void SetHour(UInt);
        void SetMinute(UInt);
        void SetSecond(UInt);        

		// Contructors

        DateTime(void);
		DateTime(const DateTime& aDateTime);

		// Destructors

        ~DateTime(void);

		// Mutators

		void Reset(void);
        void UpdateDateTime(void);
        
		friend std::ostream& operator << (std::ostream& os, DateTime& DateTime);

	protected:

    	time_t	    _CalendarTime;
    	struct tm	_DateTime;
};

#endif // DATETIME_HPP 
E 2
I 1
E 1
