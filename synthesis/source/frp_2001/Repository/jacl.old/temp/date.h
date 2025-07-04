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
