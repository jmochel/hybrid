#ifndef FOLDERITER_HPP
#define FOLDERITER_HPP

/*
    @doc

    .Contains Declaration of FolderIter

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is a derivative work. 
    The copyright notice of the original code is reproduced below.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $

    @Overview FolderIter | 

    This code is a derivative work.
    The copyright notice of the original code is reproduced below.


    # --------------------------------------------------------------------------
    #  Copyright (C) 1997 Dietmar Kueh
    #    Universitaet Konstanz, Germany, Lehrstuhl fuer praktische Informatik I 
    # 
    #  Permission to use, copy, modify, distribute and sell this 
    #  software for any purpose is hereby granted without fee, provided 
    #  that the above copyright notice appears in all copies and that 
    #  both that copyright notice and this permission notice appear in 
    #  supporting documentation. The Universitaet Konstanz and the 
    #  Lehrstuhl fuer praktische Informatik I make no representations 
    #  about the suitability of this software for any purpose. It is 
    #  provided "as is" without express or implied warranty. 
    # --------------------------------------------------------------------------

    The class FolderIter is an Draft C++ Standard Library conformant
    input iterator which iterates over the entries of a directory.
    It is modelled very much along the standard istream_iterator class:
    To iterate over the complete directory, a begin iterator is created with the directory's
    name and a "past the end" iterator is created with the default
    constructor of class FolderIter. Then, the begin iterator yields a
    string with the name of the current directory entry. The begin
    iterator (or a copy thereof) can be advanced until either the whole
    directory was scanned or an error occured: In both cases the iterator
    object used to advance compares equal to the "past the end"
    iterator. Note that the initial begin iterator will also compare equal
    to the "past the end" iterator if opening the directory failed for
    some reason.

    Like the istream_iterator, the class FolderIter is a one
    pass iterator over the directory: Copying an iterator is legal but
    advancing both resulting iterators will bring them out of sync. Also
    the original and the copy will iterate over different elements of the
    directory. The same holds for assignment.  However, both the copy and
    the left hand side of the assignment store the same value of the right
    hand side and may be used to compare against other FolderIters: Two
    FolderIters are considered to be equal if they are either equal to
    the "past the end" iterator (effectively indicating that either the
    whole directory was travered or an error occured) or if both iterators
    yield the same name with the dereference operator. Note that in the
    latter case the iterators may actually be positioned on different
    files if they are obtained from iterators created for different
    directories.

    @ToDo

    Add a wrapper to skip certain names like the \".\" and \"..\" entries.
    Such a class would be rather straight forward to implement and could be
    useful for other input iterators, too, if it takes a predicate as argument
    to determine which items should be skipped.

    A more interesting extension would be a
    wrapper which provides access to the FolderInfo properties of the current
    name.
*/

// Author: Dietmar Kühl dietmar.kuehl@uni-konstanz.de www.informatik.uni-konstanz.de/~kuehl
// Title:  Declaration for an iterator traversing the entries of a directory
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

#include <string>
#include <iterator>

using namespace std;

class FolderIterRep;

#include "FolderSpec.hpp"

/*
    @ClassDesc

        An input iterator used to iterate over
        the entries of a directory.

    @ClassNotes

        This iterator only provides access to
        the names of the entries which are returned from code(operator*)()
        as code(string)s.
*/

class FolderIter :  public iterator<input_iterator_tag, string, int>
{
    // @Access Public

    public:

        // @MemberDesc Constructor

        FolderIter();

        // @MemberDesc Constructor

        FolderIter(FolderSpec& spec);

        // @MemberDesc Constructor

        FolderIter(FolderIter const &it);

        // @MemberDesc Destructor

        ~FolderIter();

        // @MemberDesc Assignment Operator

        FolderIter &operator= (FolderIter const &it);

        // @MemberDesc Dereference operator

        CommonSpec operator* () const
        {
            return(_CurrValue);
        }

        // @MemberDesc Increment operator

        FolderIter &operator++ ();

        // @MemberDesc Increment operator

        FolderIter operator++ (int)
        {
            FolderIter rc(*this);

            operator++();

            return(rc);
        }

        // @MemberDesc Equality operator

        bool operator== (FolderIter const &it) const;

        // @MemberDesc Inequality operator

        bool operator!= (FolderIter const &it) const
        {
            return(!operator== (it));
        }

    // @Access Private

    private:

        // @MemberDesc      Representation for the next value

        FolderIterRep*          _Rep;    

        // @MemberDesc      The current value

        CommonSpec              _CurrValue;
};

#endif // FOLDERITER_HPP

