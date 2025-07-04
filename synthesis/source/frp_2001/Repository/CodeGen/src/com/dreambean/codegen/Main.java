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
   
	public Main(Properties c) throws Exception
	{
		// Init config with system properties
		
		cfg = new Properties(System.getProperties());
		
		// Load properties from the jar 
		
		cfg.load(getClass().getResourceAsStream("/codegen.properties"));
		
		// Add given property
		
		Enumeration enum = c.propertyNames();
		
		while(enum.hasMoreElements())
		{
			Object key = enum.nextElement();
			
			cfg.put(key,c.get(key));
		}
		
		// Create generator
		
		codegen = new CodeGenerator(cfg);

		// Setup metadata
		
		setupMetaData();

		// Setup tags
		
		setupTags();
   
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