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
