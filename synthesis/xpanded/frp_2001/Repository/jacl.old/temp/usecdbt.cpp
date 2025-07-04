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
