h04089
s 00384/00000/00000
d D 1.1 99/11/17 08:46:52 jmochel 2 1
cC
cK31206
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:46:49 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/FolderIter.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43873
cPC++/src/system/FolderIter.cpp
cRe1f1ccef5cb6bb65
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
/*
    @doc

    .Contains: FolderIter, an iterator for walking through and down folder hierarchies

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is derived from an earlier piece of software by Dietmar Kuehl
                See notice below

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

// Author: Dietmar Kühl dietmar.kuehl@uni-konstanz.de www.informatik.uni-konstanz.de/~kuehl
// Title:  Implementation of an iterator traversing the entries of a directory
// <!!-------------------------------------------------------------------------->
// <!! Copyright (C) 1997 Dietmar Kuehl >
// <!!   Universitaet Konstanz, Germany, Lehrstuhl fuer praktische Informatik I >
// <!!>
// <!! Permission to use, copy, modify, distribute and sell this >
// <!! software for any purpose is hereby granted without fee, provided >
// <!! that the above copyright notice appears in all copies and that >
// <!! both that copyright notice and this permission notice appear in >
// <!! supporting documentation. The Universitaet Konstanz and the >
// <!! Lehrstuhl fuer praktische Informatik I make no representations >
// <!! about the suitability of this software for any purpose. It is >
// <!! provided "as is" without express or implied warranty. >
// <!!-------------------------------------------------------------------------->


extern "C"
{
#include <dirent.h>
}

#include <cassert>

#include "FolderIter.hpp"


/*
    @ClassDesc

        A reference counted internal representation of a FolderIter.


    @ClassNotes

        Objects of class FolderIter use a FolderIterRep as internal
        representation (or 0 if they are invalid): A FolderIterRep
        is basically a reference counted pair of a code(DIR*) and a
        code(dirent*). Note that FolderIter is strictly a single pass
        iterator: Copying FolderIters does saves the current position
        only until one of the copies is advanced. Also, copies may get out
        of sync: Only the FolderIter used to advance and copies of this
        iterator after the advance operation return the current directory
        entry.
*/

class FolderIterRep
{
    // @Access Public

    public:

        // @MemberDesc Constructor

        FolderIterRep(FolderSpec& spec);  // normal allocation

        // @MemberDesc Destructor

        ~FolderIterRep();

        // @MemberDesc Accessor for Spec

        const CommonSpec& GetSpec(void) const
        { 
            return( CommonSpec(CommonSpec::Stream, (_DirEnt ? _DirEnt->d_name : ""))); 
        }

        // @MemberDesc Add a reference count
        
        void AddRef(void)
        {
            ++_RefCnt;
        }

        // @MemberDesc Release a reference count

        bool Release(void)
        {
            return(--_RefCnt == 0);
        }

        bool GetNext(void);

    // @Access Private

    private:

        FolderIterRep(FolderIterRep const &);   // no copying
        operator= (FolderIterRep const &);      // no assigning

    // @Access Private

    private:

        size_t      _RefCnt;
        DIR*        _Dir;
        dirent*     _DirEnt;
};

/*
    @MethodDesc
        
          Constructor

    @MethodNotes
        
        Open the directory (if possible). The representation is not
        put onto the initial entry of the directory: This is done by the
        constructor of FolderIter.

    @Parm        Name of the folder to be opened

*/

FolderIterRep::FolderIterRep(FolderSpec& spec):
  _RefCnt(1),
  _Dir(opendir(spec.GetName().c_str())),
  _DirEnt(0)
{
}

/*
    @MethodDesc
        
          Destructor

    @MethodNotes
        
        If the directory was opened successfully, close it here and
        Release the memory set aside for the code(dirent).

*/


FolderIterRep::~FolderIterRep()
{
    if (_Dir)
    {
        closedir(_Dir);
    }
}

/*
    @MethodDesc
        
          <???>

    @MethodNotes
        
        The workhorse of the FolderIter: The function to get the next
        entry from a directory. This function simply calls readdir()
        and stores the result in _DirEnt. 

    @ReturnDesc

        If the call fails, it
        returns false, otherwise, i.e. on success, it returns
        true.
*/


bool FolderIterRep::GetNext()
{
    if (_Dir != 0)
    {
        _DirEnt = readdir(_Dir);
   		return(_DirEnt != 0);
    }
    else {
        return(false);
    }
}

/*
    @MethodDesc
        
          Default constructor

    @MethodNotes
        
        Creates an iterator suitable as past the end indicator

*/




/*
    @MethodDesc
        
          Default Constructor

    @MethodNotes
        
        creates an iterator suitable as past the end indicator
*/

FolderIter::FolderIter():
  _Rep(0)
{
}

/*
    @MethodDesc
        
          Constructor

    @MethodNotes
        
        Creates an iterator for the named directory. If
        it opening the directory fails, the constructed iterator compares
        equal to the past the end iterator immediately.


    @Parm        NAme of the directory to be opened
*/



FolderIter::FolderIter(FolderSpec& spec):
  _Rep(new FolderIterRep(spec))
{
  if (!_Rep->GetNext())
  {
    delete _Rep;
    _Rep = 0;
  }
  else {
    _CurrValue = _Rep->GetSpec();
  }
}

/*
    @MethodDesc
        
          Copy Constructor

    @MethodNotes
        
        copy the pointer to the representation and increase the
        reference count. Also copy the current name.


    @Parm        The FolderIter to be copied

*/


FolderIter::FolderIter(FolderIter const &iter):
  _Rep(iter._Rep),
  _CurrValue(iter._CurrValue)
{
    if (_Rep != 0)
    {
        _Rep->AddRef();
    }
}

/*
    @MethodDesc
        
          Destructor

    @MethodNotes
        
        Release the representation if the reference count drop to zero.

*/



FolderIter::~FolderIter()
{
    if (_Rep != 0 && _Rep->Release())
    {
        delete _Rep;
    }
}

/*
    @MethodDesc
        
          Assignment operator

    @MethodNotes
        
        Release the own representation and reference
        the representation of the RHS. 

    @Parm        The FolderIter to be assigned from

*/

FolderIter &FolderIter::operator= (FolderIter const &it)
{
    if (it._Rep != 0)
    {
        it._Rep->AddRef();
    }

    if (_Rep != 0 && _Rep->Release())
    {
        delete _Rep;
    }

    _Rep   = it._Rep;
    _CurrValue = it._CurrValue;

    return(*this);
}

/*
    @MethodDesc
        
          Preincrement operator

    @MethodNotes
        
        Fetch the next directory entry.
*/


FolderIter &FolderIter::operator++ ()
{
    assert(_Rep != 0);

    if (_Rep->GetNext())
    {
        _CurrValue = _Rep->GetSpec();
    }
    else {

        if (_Rep->Release())
        {
            delete _Rep;
        }
        _Rep = 0;
    }

  return(*this);
}

/*
    @MethodDesc
        
          Equality Operator

    @MethodNotes
        
        Two iterators are considered equal if they are either both invalid
        (i.e. iterator where the representation is code(0)) or if they are
        both valid and point to the same name.
*/


bool FolderIter::operator== (FolderIter const &it) const
{
    if (_Rep == 0)
    {
        return(it._Rep == 0? true: false);
    }
    else {
        return(it._Rep == 0? false: _CurrValue == it._CurrValue);
    }
}
E 2
I 1
E 1
