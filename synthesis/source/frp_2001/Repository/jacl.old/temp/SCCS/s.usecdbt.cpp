h33093
s 00035/00000/00000
d D 1.1 99/11/17 12:57:57 jmochel 2 1
cC
cK62503
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:57:53 jmochel 1 0
c BitKeeper file e:/jacl/temp/usecdbt.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45379
cPtemp/usecdbt.cpp
cR2f93d74b5cb6ba86
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
