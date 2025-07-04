h23158
s 00033/00000/00000
d D 1.1 99/11/17 12:57:38 jmochel 2 1
cC
cK53717
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:57:35 jmochel 1 0
c BitKeeper file e:/jacl/temp/date.h
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45377
cPtemp/date.h
cR2f93d7505cb6ba86
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
#ifndef DATE_H
#define DATE_H
#include <iostream.h>
#include <utility.h>
     
struct Date {
  Date(unsigned m, unsigned d, unsigned y)
    : num(d + 100 * m + 10000 * y) { }
  Date()
    : num(0) { }
  bool operator<(Date const & x) const
    { return num < x.num; }
  bool operator==(Date const & x) const
    { return num == x.num; }
  friend ostream& operator<<(ostream& os, Date const & x) 
  { 
    unsigned d = x.num % 100;
    unsigned n = x.num / 100;
    return os << n % 100 << "/" << d << "/" << n / 100;
  }
  friend istream& operator>>(istream& is, Date & x) 
  {
    unsigned m, d, y;
    char c;
    is >> m >> c >> d >> c >> y;
    x = Date(m,d,y);
    return is;
  }
private:
  unsigned long num;
};
     
#endif
E 2
I 1
E 1
