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
