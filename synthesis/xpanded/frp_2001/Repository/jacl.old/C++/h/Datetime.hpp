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
