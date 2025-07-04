h06124
s 00021/00014/00119
d D 1.2 00/08/15 07:55:41 jmochel 3 2
c Modified the Main to correctly add the loaded command line arguments to the properties listing.
cC
cK58499
e
s 00133/00000/00000
d D 1.1 00/08/14 16:15:54 jmochel 2 1
cC
cF1
cK57221
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/08/14 16:15:54 jmochel 1 0
c BitKeeper file F:/Repository/CodeGen/src/com/dreambean/codegen/Main.java
cBjmochel@devilmountain|ChangeSet|20000814201452|24445|7a8511c8
cHdevilmountain
cK20467
cPsrc/com/dreambean/codegen/Main.java
cR87c0045c
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

import java.beans.*;
import java.io.*;
import java.util.*;

import com.dreambean.codegen.tags.*;
import com.dreambean.codegen.meta.*;

/**
 *   This is a source code generator. It's primary use is to generate classes derived from other classes such as RMI-stubs, object-proxies etc.
 *   This is done by writing a code-template using a XML-like language and then subclass this class and implement the tags that have been used in the template file.
 *		
 *   @author Rickard Öberg
 *   @version 1.3
 */
public class Main
{
   // Attributes ----------------------------------------------------
	CodeGenerator codegen;
	Properties cfg;
	
	// Static --------------------------------------------------------

   /**
    *   Standalone application invocation
    *
    * @param   args  
    */
   public static void main(String[] args)
   	throws Exception
   {
   	Properties cfg = new Properties();
   	
		// Add parameters to configuration
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].startsWith("-"))
			{
				cfg.put(args[i].substring(1),args[++i]);
			}
		}
		
     	new Main(cfg);
   }
   
D 3
	public Main(Properties c)
		throws Exception
E 3
I 3
	public Main(Properties c) throws Exception
E 3
	{
D 3
		// Init config to system properties
E 3
I 3
		// Init config with system properties
		
E 3
		cfg = new Properties(System.getProperties());
		
D 3
		// Load configuration
E 3
I 3
		// Load properties from the jar 
		
E 3
		cfg.load(getClass().getResourceAsStream("/codegen.properties"));
		
D 3
		// Add given cfg
		Enumeration enum = cfg.keys();
E 3
I 3
		// Add given property
		
		Enumeration enum = c.propertyNames();
		
E 3
		while(enum.hasMoreElements())
		{
			Object key = enum.nextElement();
I 3
			
E 3
			cfg.put(key,c.get(key));
		}
		
D 3
      // Create generator
      codegen = new CodeGenerator(cfg);
      
      // Setup metadata
      setupMetaData();
      
      // Setup tags
      setupTags();
E 3
I 3
		// Create generator
		
		codegen = new CodeGenerator(cfg);

		// Setup metadata
		
		setupMetaData();

		// Setup tags
		
		setupTags();
E 3
   
   	// Setup listener
   	codegen.addCodeGeneratorListener(new CodeOutput());
   
   	// Generate source
      codegen.generate(new File(cfg.getProperty("codegen.template")));
	}
	
   // Public --------------------------------------------------------
   public void setupMetaData()
   	throws Exception
	{
		StringTokenizer tkn = new StringTokenizer(cfg.getProperty("codegen.metadata"),",");
		while(tkn.hasMoreTokens())
		{
			Object md = Beans.instantiate(getClass().getClassLoader(),tkn.nextToken());
			if (md instanceof ClassMetaData)
			{
				((ClassMetaData)md).setGenerator(codegen);
			}
			codegen.addMetaData(md);
		}
	}
	
	public void setupTags()
		throws Exception
	{
		StringTokenizer tkn = new StringTokenizer(cfg.getProperty("codegen.tags"),",");
		while(tkn.hasMoreTokens())
		{
			Object tags = Beans.instantiate(getClass().getClassLoader(),tkn.nextToken());
			codegen.addTags((TagLibrary)tags);
		}
	}
	
	static class CodeOutput
		implements CodeGeneratorListener
	{
	   public void beforeGeneration(CodeGeneratorEvent evt)
		{
			try
			{
				CodeGenerator codegen = (CodeGenerator)evt.getSource();
		      codegen.setOutput(new File(codegen.getConfiguration().getProperty("output")));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	   public void afterGeneration(CodeGeneratorEvent evt)
		{
			CodeGenerator codegen = (CodeGenerator)evt.getSource();
	      codegen.close();
//			codegen.compile();
		}
	}
}
E 2N
I 1
E 1
