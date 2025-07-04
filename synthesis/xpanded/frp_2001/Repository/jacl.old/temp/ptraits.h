#ifndef POINTER_TRAITS_H
#define POINTER_TRAITS_H
     
template <class Pointer>
struct pointer_traits {
  typedef typename Pointer::element_type element_type;
};
     
template <class T>
struct pointer_traits<T *> {
  typedef T element_type;
};
     
#endif
