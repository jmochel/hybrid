H17578
s 00033/00000/00000
d D 1.1 01/07/13 18:14:21 jmochel 2 1
cC
cF1
cK53717
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:21 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/temp/date.h
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK43514
cPC++/temp/date.h
cR475eaf8e
cV4
cX0xb1
cZ-04:00
e
u
U
f e 0
f x 0xb1
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
