/*
 * The contents of this file are subject to the Artistic
 * License (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.opensource.org/licenses/artistic-license.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * The Original Code is "Template Code Generator".
 *
 * The Initial Developer of the Original Code is Jim Jackl-Mochel.
 * Portions created by Jim Jackl-Mochel are
 * Copyright (C) 1998, 1999, 2000 Jim Jackl-Mochel.  All
 * Rights Reserved.
 *
 * Contributor(s):
 */

package org.mushin.templatecodegenerator;

import java.io.*;

import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;

import com.sun.javadoc.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;

/** The <code>TemplateCodeGenerator</code> class is a Doclet
 * called from the javadoc invocation line.
 * This passes in the parsed document as a RootDoc along with any other
 * special command line parameters
 *
 * <p>The doclet is called with some additional parameters
 * 	The command line is :
 * 		doclet -f templatefilename
 * 		javadoc -doclet org.mushin.templatecodegenerator.TemplateCodeGenerator ClassToExamine.java
 */

public class TemplateCodeGenerator extends Doclet
{
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

    public static void main(String[] argv)
    {

        String[] args = { "-private",
                          "-doclet",
                            "org.mushin.templatecodegenerator.TemplateCodeGenerator",
                            "G:/TemplateCodeGenerator/TemplateCodeGenerator/scm/PearsonsHash.java",
                            "-f",
                             "G:/TemplateCodeGenerator/TemplateCodeGenerator/scm/stdtest.jt",
                            "-o",
                            "G:/TemplateCodeGenerator/TemplateCodeGenerator/scm/stdtest.output"
        };

        com.sun.tools.javadoc.Main.main(args);
    }

    /**
     * Doclet equivalent to main
     * @param root Main Root document
     * @return
     */

	public static boolean start(RootDoc root)
    {
		//
		// Instantiate the code generator
		//

		m_CodeGenerator = new CodeGeneratorEngine();

		//
		// Add the libraries
		//

		try
		{
			// Add the common library

			m_CodeGenerator.addLibrary("tcg", "org.mushin.templatecodegenerator.TagLibraryBase");
			m_CodeGenerator.addLibrary("", "org.mushin.templatecodegenerator.TagLibraryBase");
		}
		catch (ClassNotFoundException error)
		{
			System.out.println("Unable to find the class for one of the tag libraries");
		}
		catch (InstantiationException error)
		{
			System.out.println("Unable to find the class for one of the tag libraries");
		}
		catch (IllegalAccessException error)
		{
			System.out.println("Unable to find the class for one of the tag libraries");
		}

		//
		// Open and read in the template to a Document
		//

		if (m_TemplateFileName == null )
		{
			// We should have already gotten a file name by now.

			return(false);
		}

		try
		{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            m_TemplateDoc = builder.parse(new File(m_TemplateFileName));
		}
		catch (ParserConfigurationException error)
		{
            System.err.println(error);
			return(false);
        }
        catch (SAXException error)
		{
            System.err.println(error);
			return(false);
        }
        catch (IOException error)
        {
            System.err.println(error);
			return(false);
        }

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
		{
			//
			// Start the doclet
			//

			m_CodeGenerator.start(root, m_TemplateDoc);
		}
        catch (ExpansionException error)
        {
            System.err.println(error);
            return(false);
        }

		return true;
    }

	/** Used by javadoc to determine the size of additional parameters to the doclet
     *
     *
     * @param
     * @return
     */

	public static int optionLength(String option)
	{
		if ( option.equals("-f") == true)
		{
			return(2);
		}
		else if ( option.equals("-o") == true)
		{
			return(2);
		}

		return 0;
	}

    /**
     * Used by javadoc to determine if the current option is legitimnate for the doclet.
     * @param options
     * @param reporter
     * @return
     */

	public static boolean validOptions(String[][] options, DocErrorReporter reporter)
	{
		for( int i = 0; i < options.length; ++i )
		{


			if( options[i][0].equals( "-f" ) )          // Checks for the template foile name switch
			{
				m_TemplateFileName = options[i][1];
			}
			else if ( options[i][0].equals( "-o" ) )    // Checks for output directory switch.
			{
				m_OutputFileName = options[i][1];
			}
		}

		return true;
	}

    /**
     *  The javadoc Document class used to pass in the predigested information garnered from reading the java
     *  source classes.
     */

	private static Document                 m_TemplateDoc;

    /**
     * The name of the Template file.
     */

	private static String 					m_TemplateFileName;

    /**
     * Name of the specified output file.
     */

    private static String 					m_OutputFileName;

    /**
     * An instantiated CodeGenerator object.
     * The code generator encapsulates the libraries of tags to be used.
     */
	private static CodeGeneratorEngine 		m_CodeGenerator;

}
