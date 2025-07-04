h17868
s 00049/00000/00000
d D 1.1 99/11/17 12:57:53 jmochel 2 1
cC
cK47915
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:57:50 jmochel 1 0
c BitKeeper file e:/jacl/temp/refcnt.h
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45379
cPtemp/refcnt.h
cR2f93d74c5cb6ba86
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
