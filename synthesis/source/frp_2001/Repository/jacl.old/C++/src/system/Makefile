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
