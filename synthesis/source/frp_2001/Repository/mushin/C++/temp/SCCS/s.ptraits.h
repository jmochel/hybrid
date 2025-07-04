H52155
s 00014/00000/00000
d D 1.1 01/07/13 18:14:21 jmochel 2 1
cC
cF1
cK22102
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:21 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/temp/ptraits.h
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK48312
cPC++/temp/ptraits.h
cR47635cef
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
