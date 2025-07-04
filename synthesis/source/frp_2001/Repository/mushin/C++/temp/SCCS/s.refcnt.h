H12241
s 00049/00000/00000
d D 1.1 01/07/13 18:14:21 jmochel 2 1
cC
cF1
cK47915
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:21 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/temp/refcnt.h
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK50271
cPC++/temp/refcnt.h
cR4764edff
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
//This is modified from code originally posted to 
//the genstl mailing list by John Maxwell Skaller.
     
template <class T>
class refcnt_ptr {
public:
  typedef T element_type;
  typedef refcnt_ptr<T> self;
     
  explicit refcnt_ptr(T *t = 0) : ptr(t) { inc(); } 
  ~refcnt_ptr()  { dec(); }
  refcnt_ptr(self const &pt) : ptr(pt.ptr) { inc(); }
     
  self& operator=(self const &pt) {
    if (pt.ptr != ptr) { // nothing to do if same pointer
      dec();  ptr = pt.ptr; inc();
    }
    return *this;
  }
     
  T & operator* () const { return * ptr; } 
  T * operator~ () const { return ptr; }
  T * get () const { return ptr; }
  T * operator->() const {  return ptr; } 
  bool operator!() const { return ptr == 0; }
  friend bool operator == (self const &pt1, self const &pt2)
    { return pt1.ptr == pt2.ptr; }
  friend bool operator != (self const &pt1, self const &pt2)
    { return pt1.ptr != pt2.ptr; }
     
private:
     
  T *ptr;
  void inc() { if (ptr) ptr->refcnt++; }
  void dec() { if (ptr && --ptr->refcnt==0) delete ptr; }
};
     
template <class Int>
class refcnt_base {
protected:
  refcnt_base() : refcnt(0) { }
  refcnt_base(refcnt_base<Int> const &) : refcnt(0) { } 
  void operator=(refcnt_base<Int> const &) { } 
  ~refcnt_base() 
    { if (refcnt) throw "Deleted object with nonzero refcnt"; }
private:
  Int refcnt;
  template <class T> friend class refcnt_ptr;
};
E 2
I 1
E 1
