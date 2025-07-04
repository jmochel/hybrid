
/*
    .Author: Jim JM

    .Date: 07.12.93 

    .Contains   DateTime a date and time wrapper

	.Copyright

		This code is in the public domain

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $

*/

#include <cstdio>

#include "Datetime.hpp"

DateTime::DateTime(void)
{
    time(&_CalendarTime);

    UpdateDateTime();
}

void DateTime::UpdateDateTime(void)
{
    struct tm* TempDateTime;

    TempDateTime =  gmtime(&_CalendarTime);

    _DateTime.tm_sec = TempDateTime->tm_sec ;
    _DateTime.tm_min = TempDateTime->tm_min ;
    _DateTime.tm_mday = TempDateTime->tm_mday ;
    _DateTime.tm_mon = TempDateTime->tm_mon ;
    _DateTime.tm_year = TempDateTime->tm_year ;
    _DateTime.tm_isdst = TempDateTime->tm_isdst ;
}

DateTime::DateTime(const DateTime& DateTime)
{
        _DateTime = DateTime._DateTime;
}

DateTime::~DateTime(void)
{
}


// Accessors

UInt DateTime::GetDaylightSavingTime(void)
{
    return((UInt) _DateTime.tm_isdst);
}

UInt DateTime::GetYear(void)
{
    return((UInt) _DateTime.tm_year);
}

UInt DateTime::GetMonth(void)
{
    return((UInt) _DateTime.tm_mon);
}

UInt DateTime::GetDay(void)
{
    return((UInt) _DateTime.tm_mday);
}

UInt DateTime::GetHour(void)
{
    return((UInt) _DateTime.tm_hour);
}

UInt DateTime::GetMinute(void)
{
    return((UInt) _DateTime.tm_min);
}

UInt DateTime::GetSecond(void)
{
    return((UInt) _DateTime.tm_sec);
}

        // Accessors

void DateTime::SetDaylightSavingTime(UInt DST)
{
    _DateTime.tm_isdst = (int) DST;
}

void DateTime::SetYear(UInt Year)
{
    _DateTime.tm_year = (int) Year;
}

void DateTime::SetMonth(UInt Month)
{
    _DateTime.tm_mon = (int) Month;
}

void DateTime::SetDay(UInt Day)
{
    _DateTime.tm_mday = (int) Day;
}

void DateTime::SetHour(UInt Hour)
{
    _DateTime.tm_hour = (int) Hour;
}

void DateTime::SetMinute(UInt Minute)
{
    _DateTime.tm_min = (int) Minute;
}

void DateTime::SetSecond(UInt Second)
{
    _DateTime.tm_sec = (int) Second;
}

void DateTime::Reset(void)
{
        time(&_CalendarTime);
}

ostream& operator << (ostream& os, DateTime& dt)
{
    dt.Reset();
        os << ctime(&(dt._CalendarTime));

    return(os);
}
