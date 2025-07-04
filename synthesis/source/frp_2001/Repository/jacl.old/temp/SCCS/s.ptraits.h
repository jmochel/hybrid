h57840
s 00014/00000/00000
d D 1.1 99/11/17 12:57:49 jmochel 2 1
cC
cK22102
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:57:46 jmochel 1 0
c BitKeeper file e:/jacl/temp/ptraits.h
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45378
cPtemp/ptraits.h
cR2f93d74d5cb6ba86
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
E 2
I 1
E 1
