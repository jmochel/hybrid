H27283
s 00035/00000/00000
d D 1.1 01/07/13 18:14:21 jmochel 2 1
cC
cF1
cK62503
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:21 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/temp/usecdbt.cpp
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK54897
cPC++/temp/usecdbt.cpp
cR47668341
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
#include <iterator.h>
#include <iostream.h>
#include <algorithm.h>
#include "const_db_btree.h"
     
typedef pair<Date,int> record;
     
struct comp_sales {
  bool operator()(record const & x,
                  record const & y)
    { return x.second > y.second; }
};
     
ostream& operator<<(ostream& os, record const& x) 
{
  return os << x.first << ": " << x.second;
}
     
void print_five_best(Date d1, Date d2) 
{
  // code to gain access to database
  const_db_btree db("sales");
  // code to find the five best sales days 
  // between dates d1 and d2 inclusive. 
  record A[5];
  partial_sort_copy(
    db.find_rng(d1, d2),
    db.end(),
    A, A+5,
    comp_sales()
  );
  //print out the five best sales days 
  copy(A, A+5,
       ostream_iterator<record, char>(cout, "\n"));
}
E 2
I 1
E 1
