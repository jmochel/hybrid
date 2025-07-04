h52693
s 00073/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK13339
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/com/dreambean/codegen/tags/CommonTags.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK36398
cPsrc/com/dreambean/codegen/tags/CommonTags.java
cR87cb8676
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
package com.dreambean.codegen.tags;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import com.dreambean.codegen.CodeGenerator;
import com.dreambean.codegen.TagLibrary;
import com.dreambean.codegen.meta.ClassMetaData;

/**
 *   This is a source code generator. It's primary use is to generate classes derived from other classes such as RMI-stubs, object-proxies etc.
 *   This is done by writing a code-template using a XML-like language and then subclass this class and implement the tags that have been used in the template file.
 *		
 *   @author Rickard Öberg
 *   @version 1.3
 */
public class CommonTags
	implements TagLibrary
{
   // Attributes ----------------------------------------------------
   protected CodeGenerator gen;
   
   // Public --------------------------------------------------------
   /**
    *   Include a template that is a resource (i.e. a file accessible from the classpath)
    *
    * @param   inFilename  the name of the template
    */
   public void includeResource(String name)
      throws IOException
   {
      InputStream in;
      in = getClass().getResourceAsStream("/"+name);
         
      if (in == null)
      {
         throw new IOException("Template '"+name+"' does not exist");
      }
   
      gen.generate(in);
   }
   
   /**
    *   Return todays date. Useful for code headers
    *
    * @return     todays date as a string
    */
   public String now()
   {
      return new java.util.Date().toString();
   }

   /**
    *   Template comment. Do nothing
    *
    */
   public void comment(String template)
   {
   }
   
   // TagLibrary implementation -------------------------------------
   public void setGenerator(CodeGenerator gen)
	{
		this.gen = gen;
	}
	
	// Protected -----------------------------------------------------
}
E 2N
I 1
E 1
