h28804
s 00098/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK54554
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/com/dreambean/codegen/meta/ClassMetaData.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK17370
cPsrc/com/dreambean/codegen/meta/ClassMetaData.java
cR87c21336
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
package com.dreambean.codegen.meta;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import com.dreambean.codegen.CodeGenerator;
import com.dreambean.codegen.MetaData;
import com.dreambean.codegen.tags.ClassTags;

/**
 *   This is a source code generator. It's primary use is to generate classes derived from other classes such as RMI-stubs, object-proxies etc.
 *   This is done by writing a code-template using a XML-like language and then subclass this class and implement the tags that have been used in the template file.
 *		
 *   @author Rickard Öberg
 *   @version 1.3
 */
public class ClassMetaData
	implements MetaData
{
   // Attributes ----------------------------------------------------
   Class clazz;
   
   // Static --------------------------------------------------------
   /**
    *   Removes the package name from a full classname
    *
    * @param   className  the full classname
    * @return     the classname
    */
   static String removePackage(String className)
   {
      if (className.lastIndexOf('.') != -1)
         className = className.substring(className.lastIndexOf('.')+1);
         
      return className;
   }

   // Public --------------------------------------------------------
   public void setGenerator(CodeGenerator codegen)
	{
		String clazz = codegen.getConfiguration().getProperty("class");
		if (clazz != null)
		{
			try
			{
				setClass(Class.forName(clazz));
				codegen.addTags(new ClassTags());
			} catch (Exception e)
			{
				throw new IllegalArgumentException("Could not load class "+clazz);
			}
		}
	}
   
   /**
    *   Set the class to extract metadata from
    *
    * @param   class the class
    */
   public void setClass(Class clazz)
   {
   	this.clazz = clazz;
   }

   public Method[] getPublicMethods()
   {
   	return clazz.getMethods();
   }

	public String getFullClassName()
	{
		return clazz.getName();
	}
	
	public String getClassName()
	{
		return removePackage(getFullClassName());
	}
	
	public String getPackage()
	{
      String packageName = clazz.getName();
      if (packageName.lastIndexOf('.') == -1)
         return "";
      else
         return packageName.substring(0,packageName.lastIndexOf('.'));
	}

	public Constructor[] getConstructors()
	{
		return clazz.getConstructors();
	}
}
E 2N
I 1
E 1
