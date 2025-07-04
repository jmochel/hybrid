h27602
s 00043/00030/00646
d D 1.2 00/08/15 07:55:24 jmochel 3 2
c Modified the command line processing to return usage() when the command lkine was not waht was expected. 
cC
cK06822
e
s 00676/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK05256
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/codegen/CodeGenerator.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK03888
cPsrc/codegen/CodeGenerator.java
cR87ad59a4
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
I 3
 
E 3
package codegen;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 *   This is a source code generator. It's primary use is to generate classes derived from other classes such as RMI-stubs, object-proxies etc.
 *   This is done by writing a code-template using a XML-like language and then subclass this class and implement the tags that have been used in the template file.
 *		
 *   @author Rickard Öberg
 *   @version 1.3
 */
public class CodeGenerator
{
   // Attributes ----------------------------------------------------
   protected PrintWriter out;
   protected Method currentMethod; // Current method of the template class
   protected Constructor currentConstructor; // Current constructor of the template class
   protected Class currentClass;   // Template class

   // Common template variables
   public String packageName;    // Package of the generated class
   public String fullClassName;  // Classname (with package) of the generated class
   public String className;      // Classname (without package) of the generated class

   public String type;
   public String name;
   public String comma;
   public String returnType;
   public String returnResult;
   public String methodName;

   // Static --------------------------------------------------------

   /**
    *   Standalone application invocation
    *
    * @param   args  
    */
I 3

E 3
   public static void main(String[] args)
   {
D 3
      if (args.length == 0)
E 3
I 3
      if (args.length != 3)
	  {
E 3
         usage();
I 3
	  }
E 3
      else
I 3
	  {
E 3
         try
         {
D 3
            if (args.length == 3)
            {
               // Generate code
               CodeGenerator codegen = new CodeGenerator();
               codegen.setOutput(new File(args[1]));
            
               try
               {
                  // 1.2
                  codegen.setGeneratorClass(Class.forName(args[2],true,Thread.currentThread().getContextClassLoader()));
               } catch (NoSuchMethodError e)
               {
                  // 1.1
                  codegen.setGeneratorClass(Class.forName(args[2]));
               }
               codegen.setClass(args[2]);
               codegen.include(args[0]);
               codegen.getOutput().close();
               codegen.compile(new File(args[1]));
            }
         
         } catch (Throwable e)
E 3
I 3
			// Generate code

			CodeGenerator codegen = new CodeGenerator();
			codegen.setOutput(new File(args[1]));

			try
			{
			  // 1.2
			  codegen.setGeneratorClass(Class.forName(args[2],true,Thread.currentThread().getContextClassLoader()));
			} catch (NoSuchMethodError e)
			{
			  // 1.1
			  codegen.setGeneratorClass(Class.forName(args[2]));
			}
			codegen.setClass(args[2]);
			codegen.include(args[0]);
			codegen.getOutput().close();
			codegen.compile(new File(args[1]));
        
         } 
		 catch (Throwable e)
E 3
         {
            e.printStackTrace(System.out);
         }
I 3
	  }
E 3
   }
   

   /**
    *   Usage help
    *
    */
   public static void usage()
   {
D 3
      System.out.println("CodeGenerator v"+Package.getPackage("codegen").getSpecificationVersion()+" ("+Package.getPackage("codegen").getImplementationVersion()+")");
      System.out.println("Usage:");
      System.out.println("java codegen.CodeGenerator {template-file} {output-file} {input-class}");
      System.out.println();
      System.out.println("Copyright 1999 dreamBean Software. All rights reserved");
E 3
I 3
   		String	specVersion;
		String  implementationVersion;
		
		specVersion 	= Package.getPackage("codegen").getSpecificationVersion();
		
		implementationVersion = Package.getPackage("codegen").getImplementationVersion();
		
		System.out.println("CodeGenerator v" + specVersion + " (" + implementationVersion + ")");
		System.out.println("Usage:");
		System.out.println("java codegen.CodeGenerator {template-file} {output-file} {input-class}");
		System.out.println();
		System.out.println("Copyright 1999 dreamBean Software. All rights reserved");
E 3
   }

   // Public --------------------------------------------------------
   /**
    *   This is the main generating code. It looks through a given template and attempts to find XML-tags (e.g. "Header goes here <method>Generate this</method> and here's the footer") and calls the methods which corresponds to the tagnames.
    *
    * @param   template  the "XML"-template
    */
   public void generate(String template)
   {
      StringTokenizer tmp = new StringTokenizer(template,"<",true);
      Object[] params = new Object[1];
      Object[] paramsSimple = new Object[0];
      Class[] strArr = { java.lang.String.class };
      Class[] nullArr = { };
   
      while (tmp.hasMoreTokens())
      {
         // Get next command
         String token = tmp.nextToken("<");
         if (token.equals("<")) // Start of command
         {
            String cmd = tmp.nextToken("</>");
            if (cmd.equals("<")) // Escaped <
            {
               out.print("<");
               continue;
            }
         
            // Check if block or single command
            if ("/>".indexOf(token = tmp.nextToken("/>")) == -1)
               throw new IllegalStateException("Expected '>' or '/', got:"+token);
            
            // Got block, read >
            if (!token.equals("/"))
            {
            
               // Get template
               StringBuffer blockTmp = new StringBuffer();
               while (true)
               {
                  if (!(token = tmp.nextToken("<")).equals("<"))
                  {
                     blockTmp.append(token);
                     continue;
                  }
                  else // Found <
                  {
                     String slash = tmp.nextToken("</");
                     if (slash.equals("<")) // Escaped <
                     {
                        blockTmp.append("<");
                        continue;
                     }
                  
                     // Check if this is end of command
                     if (slash.equals("/"))
                     {
                        // Right command?
                        String cmd2;
                        if ((cmd2 = tmp.nextToken(">")).equals(cmd))
                        {
                           // Read away ending >
                           if (!(token = tmp.nextToken()).equals(">"))
                              throw new IllegalStateException("Expected '>', got:"+token);
                        
                           // Block method exists?
                           try
                           {
                              Method m = getClass().getMethod(cmd,strArr);
                        
                              // Invoke block method
                              params[0] = blockTmp.toString();
                              try
                              {
                                 m.invoke(this,params);
                                 break; // Continue with next command
                              } catch (InvocationTargetException e)
                              {
                                 System.err.println("Could not call "+cmd+":"+e+" "+e.getTargetException());
                                 e.getTargetException().printStackTrace();
                              } catch (Exception e)
                              {
                                 System.err.println("Could not call "+cmd+":"+e+" "+e.getMessage());
                                 e.printStackTrace();
                              }
                           } catch (NoSuchMethodException e)
                           {
                              System.err.println("Unknown command:"+cmd);
                              break;
                           }
                           
                        }
                        else
                        {
                           // Wrong command
                           blockTmp.append("</"+cmd2);
                        }
                     }
                     else // Not end of command
                     {
                           blockTmp.append("<"+slash);
                     }
                  }
               }
            }
            else // Not block command
            {
               if (!(token = tmp.nextToken(">")).equals(">"))
                  throw new IllegalStateException("Expected '>', got:"+token);
            
               try
               {
                  // Method command
                  Method m = getClass().getMethod(cmd,nullArr);
                  try
                  {
                     Object res = m.invoke(this,paramsSimple);
                     out.print(res);
                  } catch (InvocationTargetException e)
                  {
                     System.err.println("Could not call "+cmd+":"+e+" "+e.getTargetException());
                     e.getTargetException().printStackTrace();
                  } catch (Exception e)
                  {
                     System.err.println("Could not call "+cmd+":"+e+" "+e.getMessage());
                     e.printStackTrace();
                  }
               } catch (NoSuchMethodException e)
               {
                  try
                  {
                     Field f = getClass().getField(cmd);
                     
                     // Get field value
                     try
                     {
                        out.print(f.get(this));
                     } catch (Exception ee)
                     {
                        System.err.println("Could not get "+cmd+":"+ee+" "+ee.getMessage());
                     }
                  } catch (NoSuchFieldException ee)
                  {
                     System.err.println("Unknown command:"+cmd);
                     continue;
                  }
               }
            }
         }
         else
         {
            out.print(token);
         }
      }
   }


   /**
    *   Set the class to be used as template-class. The tags defined here ('publicMethod' etc.) all use the given class for code generation
    *
    * @param   genClass  the template-class
    */
   public void setGeneratorClass(Class genClass)
   {
      currentClass = genClass;
   }


   /**
    *   Set the output of the code generation
    *
    * @param   file  The output file
    * @exception   IOException  
    */
   public void setOutput(File file)
      throws IOException
   {
      setOutput(new PrintWriter(new BufferedOutputStream(new FileOutputStream(file))));
   }


   /**
    *   Set the output-stream of the code generation
    *
    * @param   out  
    */
   public void setOutput(PrintWriter out)
   {
      this.out = out;
   }


   /**
    *   Get the output stream
    *
    * @return     the output stream
    */
   public PrintWriter getOutput()
   {
      return out;
   }

   /**
    *   Include and generate a template from a stream
    *
    * @param   in  the stream with the template
    * @exception   IOException  thrown if the generation failed
    */
   public void include(InputStream in)
      throws IOException
   {
      byte[] bytes = new byte[in.available()];
      in.read(bytes);
      
      generate(new String(bytes));
      out.flush();
   }

   /**
    *   Get the type of the given class. This method reports arraytypes correctly. 
    *
    * @param   cl  the class
    * @return     the type of the class (e.g. "java.lang.String")
    */
   public String getTypeName(Class cl)
   {
      if (cl.isArray())
         return getTypeName(cl.getComponentType())+"[]";
      else
         return cl.getName();
   }

   /**
    *   Removes the package name from a full classname
    *
    * @param   className  the full classname
    * @return     the classname
    */
   public String removePackage(String className)
   {
      if (className.lastIndexOf('.') != -1)
         className = className.substring(className.lastIndexOf('.')+1);
         
      return className;
   }
   

   /**
    *   Set the package of the generated class
    *
    * @param   pkg  The full classname from which the package-name is extracted
    */
   public void setClass(String className)
   {
      // Package
      packageName = className;
      if (packageName.lastIndexOf('.') == -1)
         packageName = "";
      else
         packageName = packageName.substring(0,packageName.lastIndexOf('.'));
   
      // Classname /wo package
      this.className = removePackage(className);
   
      // Full classname
      fullClassName = className;
   }


   /**
    *   Compile the given source using the JDK-compiler. Remove source afterwards unless keepgenerated option is used
    *
    * @param   f  the sourcefile to compile
    * @exception   IOException  
    */
   public void compile(File f)
      throws IOException
   {
      if (System.getProperty("codegen.compiler") != null)
      {
      	 // Use javac, invoke using exec
      	 if (System.getProperty("codegen.compiler").equals("javac"))
         {
            String cmd = "javac";
            if (System.getProperty("codegen.classpath") != null)
               cmd += " -classpath "+System.getProperty("codegen.classpath");      
            if (System.getProperty("codegen.output") != null)
               cmd += " -d "+System.getProperty("codegen.output");
            
            cmd += " "+f.toString();
            try
            {
               Process javac = Runtime.getRuntime().exec(cmd);
					 BufferedReader in = new BufferedReader(new InputStreamReader(javac.getInputStream()));               
               String msgs;
               while ((msgs = in.readLine()) != null)
               		System.err.println(msgs);
            } catch (Exception e)
            {
               throw new IOException(e.getMessage());
            }
         
      	 // Use jikes, invoke using exec
         } else if (System.getProperty("codegen.compiler").equals("jikes"))
         {
            String cmd = "jikes";
            if (System.getProperty("codegen.classpath") != null)
               cmd += " -classpath "+System.getProperty("codegen.classpath");      
            if (System.getProperty("codegen.output") != null)
               cmd += " -d "+System.getProperty("codegen.output");
         
            cmd += " "+f.toString();
            try
            {
               Process jikes = Runtime.getRuntime().exec(cmd);
					 BufferedReader in = new BufferedReader(new InputStreamReader(jikes.getInputStream()));               
               String msgs;
               while ((msgs = in.readLine()) != null)
               		System.err.println(msgs);
            } catch (Exception e)
            {
               throw new IOException(e.getMessage());
            }
         }
      }
      else
      {
         try
         {
            Vector v = new Vector();
            if (System.getProperty("codegen.classpath") != null)
            {
               v.addElement("-classpath");
               v.addElement(System.getProperty("codegen.classpath"));
            }
            if (System.getProperty("codegen.output") != null)
            {
               v.addElement("-d");
               v.addElement(System.getProperty("codegen.output"));
            }
            v.addElement(f.toString());
            String[] args2 = new String[v.size()];
            v.copyInto(args2);
            
            try
            {
D 3
               new com.sun.tools.javac.Main().compile(args2);
            } catch (NoClassDefFoundError e)
E 3
I 3
               //new sun.tools.javac.Main().compile(args2);
            } 
			catch (NoClassDefFoundError e)
E 3
            {
               new sun.tools.javac.Main(System.err,null).compile(args2);
            }
         } catch (Throwable e)
         {
            System.err.println("Could not compile:"+e);
            throw new IOException(e.getMessage());
         }
      }
      
      // Keep generated?
      if (!Boolean.getBoolean("codegen.keepgenerated"))
         f.delete();
   }

   // Common template generator tags --------------------------------

   /**
    *   Include a templatefile
    *
    * @param   inFilename  the name of the template
    */
   public void include(String inFilename)
      throws IOException
   {
      File inFile = new File(inFilename);
      if (!inFile.exists())
      {
         throw new IOException("File '"+inFilename+"' does not exist");
      }
   
      InputStream in = new FileInputStream(inFile);
   
      include(new FileInputStream(inFile));
   }
   /**
    *   Include a template that is a resource (i.e. a file accessible from the classpath)
    *
    * @param   inFilename  the name of the template
    */
   public void includeResource(String inFilename)
      throws IOException
   {
      File inFile = new File(inFilename);
      
      InputStream in;
      if (getClass().getClassLoader() == null) // JDK1.1 fix
         in = getClass().getResourceAsStream("/"+inFilename);
      else
         in = getClass().getClassLoader().getResourceAsStream("/"+inFilename);
         
      if (in == null)
      {
         throw new IOException("Template '"+inFilename+"' does not exist");
      }
   
      include(in);
   }
   

   /**
    *   Generate the 'method' tag. This tag signifies a Java method/constructor
    *
    * @param   template  
    */
   public void method(String template)
   {
      if (currentMethod != null)
      {
         methodName = currentMethod.getName();
         returnType = getTypeName(currentMethod.getReturnType());
         returnResult = returnType.equals("void") ? "" : "return";
      }
         
      generate(template);
   }


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
         
         generate(template);
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
         
         generate(template);
      }
   }
   

   /**
    *   Find out whether the output class has a package other than the default package in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasPackage(String template)
   {
      if (!packageName.equals(""))
         generate(template);
   }

   /**
    *   Find out whether the current method has a return value in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasResult(String template)
   {
      if (!returnType.equals("void"))
         generate(template);
   }

   /**
    *   Find out whether the current method has no return value in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasNoResult(String template)
   {
      if (returnType.equals("void"))
         generate(template);
   }

   /**
    *   Find out whether the current method/constructor has any exceptions in which case it should generate the given template
    *
    * @param   template  
    */
   public void hasExceptions(String template)
   {
      if (currentMethod != null && currentMethod.getExceptionTypes().length != 0)
         generate(template);
   
      if (currentConstructor != null && currentConstructor.getExceptionTypes().length != 0)
         generate(template);
   }

   /**
    *   Generate code for all public methods
    *
    * @param   template  
    */
   public void publicMethod(String template)
   {
      Method[] methods = currentClass.getMethods();
   
      for (int i = 0; i < methods.length; i++)
      {
         // Set current method
         currentMethod = methods[i];
         generate("<method>"+template+"</method>");
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
      Constructor[] constructors = currentClass.getDeclaredConstructors();
   
      for (int i = 0; i < constructors.length; i++)
      {
         // Set current constructors
         currentConstructor = constructors[i];
         generate("<method>"+template+"</method>");
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
         generate(template);
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
}
E 2
I 1
E 1
