h42447
s 00030/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK02394
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/com/dreambean/codegen/CodeGeneratorEvent.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK37954
cPsrc/com/dreambean/codegen/CodeGeneratorEvent.java
cR87bc756a
cV3
cX0x1a1
cZ-04:00
c______________________________________________________________________
e
u
U
f e 0
f x 0x1a1
t
T
I 2
/*
 * Copyright 1999 by dreamBean Software,
 * All rights reserved.
 */
package com.dreambean.codegen;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 *   This is a source code generator. It's primary use is to generate classes derived from other classes such as RMI-stubs, object-proxies etc.
 *   This is done by writing a code-template using a XML-like language and then subclass this class and implement the tags that have been used in the template file.
 *		
 *   @author Rickard �berg
 *   @version 1.3
 */
public final class CodeGeneratorEvent
	extends EventObject
{
   // Attributes ----------------------------------------------------
   
   // Static --------------------------------------------------------

   // Constructors --------------------------------------------------
   CodeGeneratorEvent(CodeGenerator src)
	{
		super(src);
	}
}
E 2N
I 1
E 1
