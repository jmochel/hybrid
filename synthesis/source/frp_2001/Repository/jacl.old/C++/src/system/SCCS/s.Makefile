h10154
s 00037/00000/00000
d D 1.1 99/11/17 08:47:19 jmochel 2 1
cC
cK38656
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:47:16 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/Makefile
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43875
cPC++/src/system/Makefile
cR1211677f5cb6bb65
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
# <!!-*-C++-*- file: Makefile --->
# <!!-------------------------------------------------------------------------->
# <!! Copyright (C) 1997 Dietmar Kuehl >
# <!!   Universitaet Konstanz, Germany, Lehrstuhl fuer praktische Informatik I >
# <!!>
# <!! Permission to use, copy, modify, distribute and sell this >
# <!! software for any purpose is hereby granted without fee, provided >
# <!! that the above copyright notice appears in all copies and that >
# <!! both that copyright notice and this permission notice appear in >
# <!! supporting documentation. The Universitaet Konstanz and the >
# <!! Lehrstuhl fuer praktische Informatik I make no representations >
# <!! about the suitability of this software for any purpose. It is >
# <!! provided "as is" without express or implied warranty. >
# <!!-------------------------------------------------------------------------->

# Author: Dietmar Kühl dietmar.kuehl@uni-konstanz.de http://www.informatik.uni-konstanz.de/~kuehl
# Title:  Makefile for a simple demonstration of the directory iterator

CXX		= g++
CC		= $(CXX)
CXXFLAGS	= -g -O

default:	dir
dir:		dir.o directory.o
		$(CXX) -o $@ dir.o directory.o

directory.o:	directory.h
dir.o:		directory.h

clean:
		$(RM) dir.o directory.o
		$(RM) core
		$(RM) mkerr olderr
		$(RM) *~

distclean:	clean
		$(RM) dir
E 2
I 1
E 1
