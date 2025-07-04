h43069
s 00023/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK02382
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/com/dreambean/codegen/CodeGeneratorListener.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK03845
cPsrc/com/dreambean/codegen/CodeGeneratorListener.java
cR87be3d50
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

import java.util.*;

/**
 *   This is a source code generator. It's primary use is to generate classes derived from other classes such as RMI-stubs, object-proxies etc.
 *   This is done by writing a code-template using a XML-like language and then subclass this class and implement the tags that have been used in the template file.
 *		
 *   @author Rickard Öberg
 *   @version 1.3
 */
public interface CodeGeneratorListener
	extends EventListener
{
   // Public --------------------------------------------------------
   public void beforeGeneration(CodeGeneratorEvent evt);
   public void afterGeneration(CodeGeneratorEvent evt);
//   public void generationError(CodeGenerationErrorEvent evt);
}
E 2N
I 1
E 1
