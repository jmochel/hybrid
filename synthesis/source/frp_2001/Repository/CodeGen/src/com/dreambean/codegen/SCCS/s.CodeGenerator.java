h49777
s 00459/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK10797
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/com/dreambean/codegen/CodeGenerator.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK10621
cPsrc/com/dreambean/codegen/CodeGenerator.java
cR87ba4863
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
 *   @author Rickard Öberg
 *   @version 1.3
 */
public final class CodeGenerator
{
   // Attributes ----------------------------------------------------
   private PrintWriter out;
   
   private Hashtable meta = new Hashtable();
   private Vector tags = new Vector();
   
   private Vector cglList = new Vector();
   
   private Properties cfg;
   
   // Static --------------------------------------------------------

	// Constructors --------------------------------------------------
	public CodeGenerator()
	{
		this((Properties)System.getProperties().clone());
	}

	public CodeGenerator(Properties cfg)
	{
		this.cfg = cfg;
	}

   // Public --------------------------------------------------------
   public Properties getConfiguration()
	{
		return cfg;
	}
   
	public Object getMetaData(Class cl)
	{
		return meta.get(cl);
	}

	public void addMetaData(Object metadata)
	{
		meta.put(metadata.getClass(),metadata);
	}		
	
	public void addTags(TagLibrary t)
	{
		t.setGenerator(this);
		tags.addElement(t);
	}
	
	public void addCodeGeneratorListener(CodeGeneratorListener cgl)
	{
		cglList.addElement(cgl);
	}
	
	public void fireBeforeGeneration()
	{
		Enumeration enum = cglList.elements();
		CodeGeneratorEvent evt = new CodeGeneratorEvent(this);
		while(enum.hasMoreElements())
		{
			((CodeGeneratorListener)enum.nextElement()).beforeGeneration(evt);
		}
	}

	public void fireAfterGeneration()
	{
		Enumeration enum = cglList.elements();
		CodeGeneratorEvent evt = new CodeGeneratorEvent(this);
		while(enum.hasMoreElements())
		{
			((CodeGeneratorListener)enum.nextElement()).afterGeneration(evt);
		}
	}
	
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
	                           invokeBlockMethod(cmd, blockTmp);
                              break; // Continue with next command
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
                  invokeMethod(cmd);
               } catch (NoSuchMethodException e)
               {
                  try
                  {
                     printField(cmd);
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
      
      out.flush();
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
    *   Include and generate a template from a stream
    *
    * @param   in  the stream with the template
    * @exception   IOException  thrown if the generation failed
    */
   public void generate(File f)
      throws IOException
   {
   	fireBeforeGeneration();
   	
   	FileInputStream in = new FileInputStream(f);
      byte[] bytes = new byte[in.available()];
      in.read(bytes);
      
      generate(new String(bytes));
      
   	fireAfterGeneration();
   }
   
	/**
    *   Include and generate a template from a stream
    *
    * @param   in  the stream
    * @exception   IOException  thrown if the generation failed
    */
   public void generate(InputStream in)
      throws IOException
   {
   	fireBeforeGeneration();
   	
      byte[] bytes = new byte[in.available()];
      in.read(bytes);
      in.close();
      
      generate(new String(bytes));
      
   	fireAfterGeneration();
   }
   
   public void close()
	{
		out.close();
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
//               new com.sun.tools.javac.Main().compile(args2);
            } catch (NoClassDefFoundError e)
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
   
  // Private --------------------------------------------------------
  private void invokeBlockMethod(String cmd, StringBuffer block)
   	throws NoSuchMethodException
	{
      Class[] strArr = { java.lang.String.class };
      
		for (int i = 0; i < tags.size(); i++)
		{
			try
			{
	      	Method m = tags.elementAt(i).getClass().getMethod(cmd,strArr);
	      	try
				{
	      		m.invoke(tags.elementAt(i),new Object[] {block.toString()});
			   } catch (InvocationTargetException e)
			   {
			      System.err.println("Could not call "+cmd+":"+e+" "+e.getTargetException());
			      e.getTargetException().printStackTrace();
			   } catch (IllegalAccessException e)
			   {
			      System.err.println("Could not call "+cmd+":"+e);
			      e.printStackTrace();
			   }
			   return;
			} catch (NoSuchMethodException e)
			{
				// Try next one
			}
		}
		throw new NoSuchMethodException();
	}
	
   private void invokeMethod(String cmd)
   	throws NoSuchMethodException
	{
      Class[] nullArr = new Class[0];
      
		for (int i = 0; i < tags.size(); i++)
		{
			try
			{
	      	Method m = tags.elementAt(i).getClass().getMethod(cmd,nullArr);
	      	try
				{
	            Object res = m.invoke(tags.elementAt(i),new Object[0]);
	            out.print(res);
			   } catch (InvocationTargetException e)
			   {
			      System.err.println("Could not call "+cmd+":"+e+" "+e.getTargetException());
			      e.getTargetException().printStackTrace();
			   } catch (IllegalAccessException e)
			   {
			      System.err.println("Could not call "+cmd+":"+e);
			      e.printStackTrace();
			   }
			   return;
			} catch (NoSuchMethodException e)
			{
				// Try next one
			}
		}
		throw new NoSuchMethodException();
	}
	
   private void printField(String cmd)
   	throws NoSuchFieldException
	{
		for (int i = 0; i < tags.size(); i++)
		{
			try
			{
	      	Field f = tags.elementAt(i).getClass().getField(cmd);
            // Print field value
            try
            {
					out.print(f.get(tags.elementAt(i)));
            } catch (Exception ee)
            {
               System.err.println("Could not get "+cmd+":"+ee+" "+ee.getMessage());
            }
            return;
			} catch (NoSuchFieldException e)
			{
				// Try next one
			}
		}
		throw new NoSuchFieldException();
	}
}
E 2N
I 1
E 1
