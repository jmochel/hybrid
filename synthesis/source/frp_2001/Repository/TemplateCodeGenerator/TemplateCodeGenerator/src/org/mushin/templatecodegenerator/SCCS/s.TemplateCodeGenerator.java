H21502
s 00054/00020/00205
d D 1.10 01/11/02 11:01:32 jmochel 11 10
c Added comments
cC
cK37923
cZ-05:00
e
s 00004/00014/00221
d D 1.9 01/09/14 15:19:29 jmochel 10 9
c Added the redirect feature and extended exception handling
cC
cK46295
e
s 00022/00008/00213
d D 1.8 01/08/31 16:31:44 jmochel 9 8
c Now all works for a simple class tag and cntents
cC
cK60656
e
s 00003/00002/00218
d D 1.7 01/08/31 15:27:29 jmochel 8 7
c Diagnostics changes.
cC
cK43307
e
s 00002/00002/00218
d D 1.6 01/08/15 15:35:37 jmochel 7 6
c Gradually building the standard test up...
cC
cK44232
e
s 00029/00006/00191
d D 1.5 01/08/14 17:12:55 jmochel 6 5
c It compiles but no longer runs. I merged a lot of code from the other branch and 
cC
cK34384
e
s 00019/00016/00178
d D 1.4 01/08/03 14:21:14 jmochel 5 4
c Converting the whole shebang over to JAXP basis w/Namespaces
cC
cK63836
e
s 00085/00059/00109
d D 1.3 01/06/22 13:00:50 jmochel 4 3
c Refactoring for XML namespaces and using XML APIs internally
cC
cK37313
e
s 00000/00000/00168
d D 1.2 01/05/29 12:33:56 jmochel 3 2
c mvdir
cC
cHdevilmountain.corp.foliage.com
cK29497
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/TemplateCodeGenerator.java
cZ-04:00
e
s 00168/00000/00000
d D 1.1 01/03/14 22:46:30 jmochel 2 1
cC
cF1
cK46819
cO-rw-rw-r--
e
s 00000/00000/00000
d D 1.0 01/03/14 22:46:30 jmochel 1 0
c BitKeeper file i:/Repository/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/TemplateCodeGenerator.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHmordanith.ne.mediaone.net
cK05710
cPsrc/org/mushin/templatecodegenerator/TemplateCodeGenerator.java
cR9104b6c8
cV4
cX0xa1
cZ-05:00
e
u
U
f e 0
f x 0xa1
t
T
I 2
/*
 * The contents of this file are subject to the Artistic
 * License (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.opensource.org/licenses/artistic-license.html
D 4
 * 
E 4
I 4
 *
E 4
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
D 4
 * 
E 4
I 4
 *
E 4
 * The Original Code is "Template Code Generator".
D 4
 * 
 * The Initial Developer of the Original Code is Jim Jackl-Mochel.  
E 4
I 4
 *
 * The Initial Developer of the Original Code is Jim Jackl-Mochel.
E 4
 * Portions created by Jim Jackl-Mochel are
 * Copyright (C) 1998, 1999, 2000 Jim Jackl-Mochel.  All
 * Rights Reserved.
D 4
 * 
 * Contributor(s): 
E 4
I 4
 *
 * Contributor(s):
E 4
 */

package org.mushin.templatecodegenerator;

D 9
import java.io.PrintWriter;
import java.io.FileInputStream;
I 4
import java.io.File;
import java.io.IOException;
E 9
I 9
import java.io.*;
E 9
E 4

import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;

import com.sun.javadoc.*;

I 4
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;

E 4
D 5
/**
 * The <code>TemplateCodeGenerator</code> class acts as a Doclet
 * and can be called from the javadoc invocation line.
E 5
I 5
/** The <code>TemplateCodeGenerator</code> class is a Doclet
 * called from the javadoc invocation line.
E 5
D 4
 * This passes in the parsed document as a RootDoc along with any other 
E 4
I 4
 * This passes in the parsed document as a RootDoc along with any other
E 4
 * special command line parameters
 *
D 4
 * <p>The doclet is called with some additional parameters 
E 4
I 4
 * <p>The doclet is called with some additional parameters
E 4
 * 	The command line is :
D 4
 * 		doclet -f templatefilename 
 *		javadoc -doclet org.mushin.templatecodegenerator.TemplateCodeGenerator ClassToExamine.java 
E 4
I 4
 * 		doclet -f templatefilename
D 5
 *		javadoc -doclet org.mushin.templatecodegenerator.TemplateCodeGenerator ClassToExamine.java
E 5
I 5
 * 		javadoc -doclet org.mushin.templatecodegenerator.TemplateCodeGenerator ClassToExamine.java
E 5
E 4
 */

public class TemplateCodeGenerator extends Doclet
{
I 11
    /**
     * Public constructor
     */

    public TemplateCodeGenerator()
    {
    }

    /**
     * Main that allows me to invoke the appropriate test process from within the class.
     * this shouuld probably be replaced with an JUnit Test case.
     *
     */
E 11
D 4
	
E 4
I 4

I 6
    public static void main(String[] argv)
    {

        String[] args = { "-private",
                          "-doclet",
                            "org.mushin.templatecodegenerator.TemplateCodeGenerator",
D 7
                            "PearsonsHash.java",
E 7
I 7
                            "G:/TemplateCodeGenerator/TemplateCodeGenerator/scm/PearsonsHash.java",
E 7
                            "-f",
D 7
                             "stdtest.jt"
E 7
I 7
D 9
                             "G:/TemplateCodeGenerator/TemplateCodeGenerator/scm/stdtest.jt"
E 9
I 9
                             "G:/TemplateCodeGenerator/TemplateCodeGenerator/scm/stdtest.jt",
                            "-o",
                            "G:/TemplateCodeGenerator/TemplateCodeGenerator/scm/stdtest.output"
E 9
E 7
        };

        com.sun.tools.javadoc.Main.main(args);
    }

E 6
I 5
D 11
/** Doclet equivalent to main
 * @param root Main Root document
D 6
 * @return 
E 6
I 6
 * @return
E 6
 */
E 11
I 11
    /**
     * Doclet equivalent to main
     * @param root Main Root document
     * @return
     */

E 11
E 5
E 4
	public static boolean start(RootDoc root)
    {
D 4
		byte[] bfr = null;
		FileInputStream in = null;
		
		//
		// Instantiate the code generator 
		//
		
		codeGenerator = new CodeGeneratorEngine();
	
E 4
I 4
		//
		// Instantiate the code generator
		//

I 5
D 6
        System.out.println("Start has been called");
E 6
E 5
		m_CodeGenerator = new CodeGeneratorEngine();

D 5
        try
        {

        }
E 5
E 4
		//
		// Add the libraries
D 4
		// 			
		
E 4
I 4
		//

E 4
		try
		{
			// Add the common library
D 4
			
			codeGenerator.addLibrary("tcg", "org.mushin.templatecodegenerator.CommonTagLibrary");
			codeGenerator.addLibrary("", "org.mushin.templatecodegenerator.CommonTagLibrary");
		
E 4
I 4

D 11
			m_CodeGenerator.addLibrary("tcg", "org.mushin.templatecodegenerator.CommonTagLibrary");
			m_CodeGenerator.addLibrary("", "org.mushin.templatecodegenerator.CommonTagLibrary");
E 11
I 11
			m_CodeGenerator.addLibrary("tcg", "org.mushin.templatecodegenerator.TagLibraryBase");
			m_CodeGenerator.addLibrary("", "org.mushin.templatecodegenerator.TagLibraryBase");
E 11
D 5

E 5
E 4
		}
		catch (ClassNotFoundException error)
		{
			System.out.println("Unable to find the class for one of the tag libraries");
		}
		catch (InstantiationException error)
		{
D 4
			System.out.println("Unable to find the class for one of the tag libraries");		
E 4
I 4
			System.out.println("Unable to find the class for one of the tag libraries");
E 4
		}
		catch (IllegalAccessException error)
		{
D 4
			System.out.println("Unable to find the class for one of the tag libraries");		
E 4
I 4
			System.out.println("Unable to find the class for one of the tag libraries");
E 4
		}
D 4
		
	
E 4
I 4

D 5

E 5
E 4
		//
D 4
		// Open and read in the template
E 4
I 4
		// Open and read in the template to a Document
E 4
		//

D 4
		if (templateFileName == null )
E 4
I 4
		if (m_TemplateFileName == null )
E 4
		{
			// We should have already gotten a file name by now.

			return(false);
		}
D 4
	
E 4
I 4

E 4
		try
		{
D 4
			in = new FileInputStream(templateFileName);
E 4
I 4
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            m_TemplateDoc = builder.parse(new File(m_TemplateFileName));
E 4
		}
D 4
		catch (java.io.FileNotFoundException error)
E 4
I 4
		catch (ParserConfigurationException error)
E 4
		{
D 4
			System.err.println("Unable to find the Template File");
E 4
I 4
            System.err.println(error);
E 4
			return(false);
D 4
		}
		catch (java.io.IOException error)
E 4
I 4
        }
        catch (SAXException error)
E 4
		{
D 4
			System.err.println("Failed to read the Template File");
E 4
I 4
            System.err.println(error);
E 4
			return(false);
D 4
		}
		
E 4
I 4
        }
        catch (IOException error)
        {
            System.err.println(error);
			return(false);
        }

E 4
D 9
		try
E 9
I 9
        try
        {
            //
            // Config the doclet
            //

            if (m_OutputFileName != null)
            {
                m_CodeGenerator.addOutput(new FileOutputStream(m_OutputFileName));
            }
        }
        catch (FileNotFoundException e)
        {
        }

        try
E 9
		{
			//
D 9
			// Config the doclet
E 9
I 9
			// Start the doclet
E 9
			//
D 4
			
			codeGenerator.start(root, in, System.out, System.err);
E 4
I 4

D 9
			m_CodeGenerator.start(root, m_TemplateDoc, System.out, System.err);
E 9
I 9
			m_CodeGenerator.start(root, m_TemplateDoc);
E 9
E 4
		}
D 4
		catch (InvalidArgumentException err)
E 4
I 4
D 10
		catch (NoSuchMethodException error)
E 10
I 10
        catch (ExpansionException error)
E 10
        {
D 10
			System.err.println(error);
			return(false);
		}
		catch (InvalidArgumentException error)
E 4
		{
D 4
			System.err.println(err);
E 4
I 4
			System.err.println(error);
E 4
			return(false);
D 4
		}		
		catch (java.io.IOException 	err)
E 4
I 4
		}
		catch (java.io.IOException 	error)
E 4
		{
D 4
			System.err.println(err);
E 4
I 4
			System.err.println(error);
E 4
			return(false);
		}
E 10
I 10
            System.err.println(error);
            return(false);
        }
E 10
D 4
		
E 4
I 4

E 4
		return true;
    }

D 5
	/**
	 *
	 *
D 4
	 * @param   option  
	 * @return     
E 4
I 4
	 * @param   option
	 * @return
E 4
	 */
E 5
I 5
	/** Used by javadoc to determine the size of additional parameters to the doclet
D 11
 *
 *
D 6
 * @param  
 * @return 
E 6
I 6
 * @param
 * @return
E 6
 */
E 11
I 11
     *
     *
     * @param
     * @return
     */
E 11
E 5
D 4
	 
E 4
I 4

E 4
	public static int optionLength(String option)
	{
		if ( option.equals("-f") == true)
		{
D 6
			return 2;
E 6
I 6
			return(2);
		}
		else if ( option.equals("-o") == true)
		{
			return(2);
E 6
		}
D 4
		
E 4
I 4

E 4
D 8
		System.out.println("optionLength was called");

E 8
		return 0;
	}

I 5
D 11
/** Used by javadoc to determine if the current option is legitimnate for the doclet.
 * @param options
 * @param reporter
D 6
 * @return 
E 6
I 6
 * @return
E 6
 */
E 11
I 11
    /**
     * Used by javadoc to determine if the current option is legitimnate for the doclet.
     * @param options
     * @param reporter
     * @return
     */

E 11
E 5
	public static boolean validOptions(String[][] options, DocErrorReporter reporter)
	{
		for( int i = 0; i < options.length; ++i )
		{
D 4
			if( options[i][0].equals( "-f" ) ) 
E 4
I 4
D 11
			if( options[i][0].equals( "-f" ) )
E 11
I 11


			if( options[i][0].equals( "-f" ) )          // Checks for the template foile name switch
E 11
E 4
			{
D 4
				templateFileName = options[i][1];
E 4
I 4
				m_TemplateFileName = options[i][1];
E 4
			}
I 6
D 11
			else if ( options[i][0].equals( "-o" ) )
E 11
I 11
			else if ( options[i][0].equals( "-o" ) )    // Checks for output directory switch.
E 11
			{
				m_OutputFileName = options[i][1];
			}
E 6
		}

		return true;
	}
D 4
	
	private static String 					templateFileName;
	
	private static CodeGeneratorEngine 		codeGenerator;
E 4
I 4

I 11
    /**
     *  The javadoc Document class used to pass in the predigested information garnered from reading the java
     *  source classes.
     */

E 11
	private static Document                 m_TemplateDoc;

I 11
    /**
     * The name of the Template file.
     */

E 11
	private static String 					m_TemplateFileName;
I 6

I 11
    /**
     * Name of the specified output file.
     */

E 11
    private static String 					m_OutputFileName;
E 6

I 11
    /**
     * An instantiated CodeGenerator object.
     * The code generator encapsulates the libraries of tags to be used.
     */
E 11
	private static CodeGeneratorEngine 		m_CodeGenerator;
I 8

D 11
    public TemplateCodeGenerator() {
    }
E 11
E 8
E 4
}
E 2
I 1
E 1
