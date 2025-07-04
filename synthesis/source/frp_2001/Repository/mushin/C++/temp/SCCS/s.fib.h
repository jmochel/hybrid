H14047
s 00029/00000/00000
d D 1.1 01/07/13 18:14:21 jmochel 2 1
cC
cF1
cK50541
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:21 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/temp/fib.h
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK43469
cPC++/temp/fib.h
cR4760388f
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
#include "refcnt_ptr.h"
#include "input_iterator.h"
     
class fib :
  public refcnt_base<unsigned long> {
public:
  typedef unsigned long value_type;
  typedef value_type deref_type;
  fib(long n)
    : x1(0), x2(1), nleft(n), refcnt(0) { }
  deref_type value() const
    { return x1; }
  bool next() {
    if (nleft-- <= 0)
      return false;
    value_type x = x1 + x2;
    x1 = x2;
    x2 = x;
    return true;
  }
private:
  value_type x1, x2;
  long nleft;
};
     
typedef input_iter_adapter<refcnt_ptr<fib>, long> fib_iter;
     
inline fib_iter fibonacci(long n) 
{ return fib_iter(refcnt_ptr<fib>(new fib(n))); }
E 2
I 1
E 1
