H38627
s 00078/00000/00000
d D 1.1 01/07/13 18:14:21 jmochel 2 1
cC
cF1
cK08491
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:21 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/temp/cdbbtree.h
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK06482
cPC++/temp/cdbbtree.h
cR475c97a3
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
#ifndef CDB_BTREE_H
#define CDB_BTREE_H
     
#include <db.h>
#include <utility.h>
#include "input_iterator.h"
#include <fcntl.h>
#include "date.h"
     
class const_db_btree {
public:
  typedef Date Key;
  typedef int Data;
  typedef pair<Key, Data> value_type;
  typedef pair<Key const, Data> cvalue_type;
     
  struct datagen {
    typedef const_db_btree::value_type value_type; 
    typedef value_type const & deref_type;
     
    void set(Key * startk, Key * endk);
    void set(Key & k, Data & d); // used only by db_btree 
    bool next();
    deref_type value() const
      { return value_; }
     
    value_type value_;
    DB * dbp;
    DBT key;
    DBT data;
    DBT endkey;
    Key ek;
    unsigned int flag;
  };
     
  typedef input_iter_adapter<datagen *> iterator; 
  typedef iterator const_iterator;
  typedef iterator::difference_type difference_type;
     
  // INTERFACE STARTS HERE
     
  const_db_btree(char const * fname)
    { ctor(fname, true, O_RDONLY, 0); }
     
  ~const_db_btree()
    { if (body.dbp) body.dbp->close(body.dbp); }
     
  iterator begin() const
    { body.set((Key *)0, (Key *)0); return iterator(&body); }
  iterator end() const
    { return iterator(); }
     
  iterator find_from(Key k) const
    {
      body.set(&k, 0);
      return iterator(&body);
    }
  iterator find_to(Key k) const
    {
      body.set(0, &k);
      return iterator(&body);
    }
  iterator find_rng(Key k1, Key k2) const
    {
      body.set(&k1, &k2);
      return iterator(&body);
    }
     
  bool get(Key k, Data & x) const;
  // Returns false and leaves data unchanged if k not in database
     
protected:
  datagen mutable body;
  const_db_btree() { }
  void ctor(char const * fname, bool multi, int flags, int mode);
};
     
#endif
E 2
I 1
E 1
