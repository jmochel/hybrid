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
