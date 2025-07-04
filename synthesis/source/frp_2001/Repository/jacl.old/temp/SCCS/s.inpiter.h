h48736
s 00078/00000/00000
d D 1.1 99/11/17 12:57:46 jmochel 2 1
cC
cK13015
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:57:42 jmochel 1 0
c BitKeeper file e:/jacl/temp/inpiter.h
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45378
cPtemp/inpiter.h
cR2f93d74e5cb6ba86
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
#ifndef INPUT_ITERATOR_H
#define INPUT_ITERATOR_H
     
#include <iterator>
#ifndef NO_PARTIAL_SPECIALIZATION
#include "pointer_traits.h"
#endif
#include <stddef.h>
     
#ifndef NO_PARTIAL_SPECIALIZATION
template <class P, class Distance = ptrdiff_t> 
#else
template <class P, class Val, class Der, class Distance> 
#endif
     
class input_iter_adapter :
     
#ifndef NO_PARTIAL_SPECIALIZATION
  public std::iterator<
    std::input_iterator_tag,
    typename pointer_traits<P>::element_type::value_type, 
    Distance
  >
#else
   public input_iterator<Val, Distance>
#endif
     
{
public:
  typedef input_iter_adapter self;
#ifndef NO_PARTIAL_SPECIALIZATION
  typedef
    typename pointer_traits<P>::element_type::deref_type 
    deref_type;
#else
  typedef Der deref_type;
#endif
     
  class PIR {
  public:
    typedef typename input_iter_adapter::value_type value_type; 
    PIR(value_type const & x) : value(x) { }
    // Default ctor, dtor, assign
    value_type operator*() const { return value; }
  private:
    value_type value;
  };
     
  explicit input_iter_adapter(P const & pg)
    : p(pg) { inc(); }
     
  input_iter_adapter() // past-the-end value; non-dereferenceable
    : p(0) { }
     
  // Default copy ctor, assignment, dtor.
     
  bool valid() const  // *this != self()
    { return p != P(0); }
  deref_type operator*() const
    { return p->value(); }
  value_type const * operator->() const
    { return &(p->value()); }
  self& operator++()
    { inc(); return *this; }
  PIR operator++(int)
    { PIR tmp( p->value() ); inc(); return tmp; }
  bool operator==(self const & x) const
    { return p == x.p; }
  bool operator!=(self const & x) const
    { return p != x.p; }
     
private:
     
  P p;
  void inc() { if (!p->next()) p = P(0); }
};
     
#endif
E 2
I 1
E 1
