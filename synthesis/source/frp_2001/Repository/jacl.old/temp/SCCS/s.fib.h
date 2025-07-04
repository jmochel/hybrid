h19813
s 00029/00000/00000
d D 1.1 99/11/17 12:57:42 jmochel 2 1
cC
cK50541
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:57:38 jmochel 1 0
c BitKeeper file e:/jacl/temp/fib.h
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45378
cPtemp/fib.h
cR2f93d74f5cb6ba86
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
