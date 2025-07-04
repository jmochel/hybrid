h12163
s 00229/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK38543
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/com/dreambean/codegen/tags/ClassTags.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK11009
cPsrc/com/dreambean/codegen/tags/ClassTags.java
cR87c99cb1
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
public class ClassTags
	implements TagLibrary
{
   // Attributes ----------------------------------------------------
   protected CodeGenerator gen;
   
   protected Method currentMethod; // Current method of the template class
   protected Constructor currentConstructor; // Current constructor of the template class

   public String type;
   public String name;
   public String comma;
   public String returnType;
   public String returnResult;
   public String methodName;

   // Static --------------------------------------------------------
   /**
    *   Get the type of the given class. This method reports arraytypes correctly. 
    *
    * @param   cl  the class
    * @return     the type of the class (e.g. "java.lang.String")
    */
   static String getTypeName(Class cl)
   {
      if (cl.isArray())
         return getTypeName(cl.getComponentType())+"[]";
      else
         return cl.getName();
   }

   // Public --------------------------------------------------------
   /**
    *   Generate the 'parameter' tag. This tag signifies the parameters of a method declaration or method call.
    *
    * @param   template  
    */
   public void parameter(String template)
   {
      Class[] paramTypes = null;
      if (currentMethod != null)
         paramTypes = currentMethod.getParameterTypes();
      if (currentConstructor != null)
         paramTypes = currentConstructor.getParameterTypes();
      
      for (int i = 0; i < paramTypes.length; i++)
      {
         type = getTypeName(paramTypes[i]);
         name = "arg"+i;
         comma = (i == paramTypes.length-1 ? "" : ", ");
         
         gen.generate(template);
      }
   }
   

   /**
    *   Generate the 'exception' tag. This tag is mostly used in the 'throws'-clause
    *
    * @param   template  
    */
   public void exception(String template)
   {
      Class[] exceptionTypes = null;
      if (currentMethod != null)
         exceptionTypes = currentMethod.getExceptionTypes();
      if (currentConstructor != null)
         exceptionTypes = currentConstructor.getExceptionTypes();
      
      for (int i = 0; i < exceptionTypes.length; i++)
      {
         type = exceptionTypes[i].getName();
         comma = (i == exceptionTypes.length-1 ? "" : ", ");
         
         gen.generate(template);
      }
   }
   

   /**
    *   Find out whether the output class has a package other than the default package in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasPackage(String template)
   {
      if (!getClassMetaData().getPackage().equals(""))
         gen.generate(template);
   }

   /**
    *   Find out whether the current method has a return value in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasResult(String template)
   {
      if (!returnType.equals("void"))
         gen.generate(template);
   }

   /**
    *   Find out whether the current method has no return value in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasNoResult(String template)
   {
      if (returnType.equals("void"))
         gen.generate(template);
   }

   /**
    *   Find out whether the current method/constructor has any exceptions in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasExceptions(String template)
   {
      if (currentMethod != null && currentMethod.getExceptionTypes().length != 0)
         gen.generate(template);
      else if (currentConstructor != null && currentConstructor.getExceptionTypes().length != 0)
         gen.generate(template);
   }

   /**
    *   Generate code for all public methods
    *
    * @param   template  
    */
   public void method(String template)
   {
      Method[] methods = getClassMetaData().getPublicMethods();
   
      for (int i = 0; i < methods.length; i++)
      {
         // Set current method
         currentMethod = methods[i];
         
         methodName = currentMethod.getName();
         returnType = getTypeName(currentMethod.getReturnType());
         returnResult = returnType.equals("void") ? "" : "return";
        
         gen.generate(template);
      }
      currentMethod = null;
   }

   /**
    *   Generate code for all constructors
    *
    * @param   template  
    */
   public void constructor(String template)
   {
      Constructor[] constructors = getClassMetaData().getConstructors();
   
      for (int i = 0; i < constructors.length; i++)
      {
         // Set current constructors
         currentConstructor = constructors[i];
         
         methodName = currentMethod.getName();
         returnType = getTypeName(currentMethod.getReturnType());
         returnResult = returnType.equals("void") ? "" : "return";
         
         gen.generate(template);
      }
      currentConstructor = null;
   }

   /**
    *   Check if current method is not final
    *
    * @param   template  
    */
   public void isNotFinal(String template)
   {
      if (!Modifier.isFinal(currentMethod.getModifiers()))
         gen.generate(template);
   }
   
   public String packageName()
	{
		return getClassMetaData().getPackage();
	}
	
	public String className()
	{
		return getClassMetaData().getClassName();
	}

	public String fullClassName()
	{
		return getClassMetaData().getFullClassName();
	}

   // TagLibrary implementation -------------------------------------
   public void setGenerator(CodeGenerator gen)
	{
		this.gen = gen;
	}
	
	protected ClassMetaData getClassMetaData()
	{
		return ((ClassMetaData)gen.getMetaData(ClassMetaData.class));
	}
}
E 2N
I 1
E 1
