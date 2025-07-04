h18548
s 00141/00000/00000
d D 1.1 99/11/17 12:47:17 jmochel 2 1
cC
cK46679
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:47:14 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/date/Datetime.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45315
cPC++/src/date/Datetime.cpp
cR2f93d7bd5cb6ba86
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
E 2
I 1
E 1
