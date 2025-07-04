h48035
s 00093/00000/00000
d D 1.1 99/11/17 12:57:31 jmochel 2 1
cC
cK11789
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:57:27 jmochel 1 0
c BitKeeper file e:/jacl/temp/cdbbtree.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45377
cPtemp/cdbbtree.cpp
cR2f93d7525cb6ba86
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
#include <errno.h>
#include <string.h>
#include "except_util.h"
#include "const_db_btree.h"
     
namespace {
     
  template <class T>
  DBT pod2dbt(T & x)
  {
    DBT t;
    t.size = sizeof(T);
    t.data = (void *)&x;
    return t;
  }
     
  template <class T>
  void dbt2pod(DBT dbt, T & x)
  { memcpy((void *)&x, dbt.data, dbt.size); }
     
  int compare(DBT const * px, DBT const * py) 
  {
    Date d1, d2;
    dbt2pod(*px, d1);
    dbt2pod(*py, d2);
    return d1 < d2 ? -1 : d1 > d2 ? +1 : 0;
  }
     
}
     
void const_db_btree::datagen::set(Key * startk, Key * endk) 
{ 
  if (startk) {
    key = pod2dbt(*startk);
    flag = R_CURSOR;
  }
  else
    flag = R_FIRST;
  if (endk) {
    ek = *endk;
    endkey = pod2dbt(ek);
  }
  else
    endkey.data = 0;
}
     
bool const_db_btree::datagen::next() 
{
  if (flag == R_SETCURSOR) { // used only in db_btree
    int code = dbp->put(dbp, &key, &data, flag); 
    if (code < 0)
      throw unix_error(errno);
  }
  else {
    int code = dbp->seq(dbp, &key, &data, flag); 
    if (code < 0)
      throw unix_error(errno); 
    else if (code > 0 || endkey.data && compare(&key, &endkey) > 0)
      return false;
    dbt2pod(key, value_.first);
    dbt2pod(data, value_.second);
  }
  flag = R_NEXT;
  return true;
}
     
void const_db_btree::ctor(
  char const * fname, bool multi, int flags, int mode
)
{
  BTREEINFO info;
  bzero(&info, sizeof(info));
  info.prefix = 0;  // Null pointer
  info.compare = &compare;
  if (multi)
    info.flags = R_DUP;
  body.dbp = dbopen(fname, flags, mode, DB_BTREE, (void *)&info); 
  if (!body.dbp)
    throw unix_error(errno);
}
     
bool const_db_btree::get(Key k, Data & x) const 
{
  DBT key = pod2dbt(k), data;
  int code = body.dbp->get(body.dbp, &key, &data, 0); 
  int e = errno;
  if (code < 0)
    throw unix_error(e);
  else if (code > 0)
    return false;
  dbt2pod(data, x);
  return true;
}
E 2
I 1
E 1
