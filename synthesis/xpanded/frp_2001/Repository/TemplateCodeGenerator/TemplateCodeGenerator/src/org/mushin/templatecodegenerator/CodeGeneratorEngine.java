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

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
import java.util.Hashtable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.FieldDoc;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class CodeGeneratorEngine
{
    /**
     *  Basic public constructor
     *
     *  by default the <code>CodeGeneratorEngine</code> sets up system.out
     *  as its default output location.
     */

	public CodeGeneratorEngine()
	{
	    this.m_RootDoc = null;
		
	    this.m_Err = null;
        this.m_Out = new Stack();

	    this.m_TagLibraries = new Hashtable();

        //
        // Set up the output streams
        //

        addOutput(System.out);
        setErr(System.err);
	}
	
	/**
	 *
	 * @param   root  		The root document that carries the package/class information
	 * @param   outputStream  	The stream to which the output will be delivered
	 *
	 * @exception   InvalidTagException
	 * @exception   IOException
	 */
	 
	public void start(RootDoc root, Document templateDoc)
		throws ExpansionException
	{
		String templateBuffer = null;
		
		//
		// Initialize members
		//
		
		this.m_RootDoc = root;
		
		try
		{
			//
			// Initialize the Tag Libraries
			//

			Iterator iter = this.m_TagLibraries.values().iterator();

			while (iter.hasNext())
			{
				((ITagLibrary) iter.next()).init(root, this);
			}


			//
			// Start processing the template
			//
					
			expandTemplate(templateDoc);
		} 
		finally
		{
		}
	}

    /**
     * Adds a TagLibrary to the set of usable TagLibraries
     */
    public void addLibrary(String libraryNameSpace, String libraryClassName)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class libraryClass;
		
		// Locate the class of the library and instantiate it.
		
		libraryClass = Class.forName(libraryClassName);
		
		ITagLibrary tagLibrary = (ITagLibrary) libraryClass.newInstance();
		
		// Add it to the tag libraries
		
		this.m_TagLibraries.put(libraryNameSpace, tagLibrary);
    }

    /**
     *   This is the main generating code. It looks through a given template
     *	and attempts to find XML-tags
     *   (e.g. "Header goes here <method>Generate this</method> and here's the footer") and calls the
     *   methods which corresponds to the tagnames.
     *
     *	@param   template  the "XML"-template
     */

     public void expandTemplateChildren(Node node)
         throws ExpansionException
     {
            // No matter what the type of node, if there are child nodes, recurse down them

            NodeList children = null;

            children = node.getChildNodes();

            for (int k = 0 ; k < children.getLength(); k++)
            {
                expandTemplate(children.item(k));
            }
    }

   /**
    *   This is the main generating code. It looks through a given template 
    *	and attempts to find XML-tags 
    *   (e.g. "Header goes here <method>Generate this</method> and here's the footer") and calls the 
    *   methods which corresponds to the tagnames.
    *
    *	@param   template  the "XML"-template
    */

	public void expandTemplate(Node node)
	    throws ExpansionException
	{
		// Get basic information about the node.

		short type = node.getNodeType();
		String name = node.getNodeName();
		String value = node.getNodeValue();
		NamedNodeMap attributes = node.getAttributes();

		// If this is an element, and it has children, it is a action element (like <class>...</class>
        // Set up the context for that tag...

		if ( node.hasChildNodes() == true)
        {
            // If it is a element node, invoke the action method to set the context

            if (type == Node.ELEMENT_NODE)
            {
                invokeMethodForActionTag(node);
            }
            else
            {
                // No matter what the type of node, if there are child nodes, recurse down them

                NodeList children = null;

                children = node.getChildNodes();

                for (int k = 0 ; k < children.getLength(); k++)
                {
                    expandTemplate(children.item(k));
                }
            }
        }
        else
        {
            // If it is an element , and it has no children, it must be a data element

            if (type == Node.ELEMENT_NODE )
            {
                invokeMethodForDataTag(node);
            }
            else
            {
                ((PrintWriter) m_Out.peek()).print(node.getNodeValue());
            }
        }
	}

	public void invokeMethodForActionTag(Node node)
	  	throws ExpansionException
	{
		// Locate the appropriate tag library

		ITagLibrary library = null;

        if (node.getNamespaceURI() == null )
        {
            library = (ITagLibrary) this.m_TagLibraries.get("");
        }
        else
        {
            library = (ITagLibrary) this.m_TagLibraries.get(node.getNamespaceURI());
        }
		
        library.expandActionElement(node);

 		return;
	}
	
	public void invokeMethodForDataTag(Node node)
	   	throws ExpansionException
	{
		try
		{
			// Locate the appropriate tag library
			
			ITagLibrary library = (ITagLibrary) this.m_TagLibraries.get( (node.getNamespaceURI() == null) ? "" : node.getNamespaceURI() );

			// Get the expansion of the data tag 
			
			try
			{
				String res = library.expandDataElement(node);

				((PrintWriter) this.m_Out.peek()).print(res);
                ((PrintWriter) this.m_Out.peek()).flush();
			}
			catch (Exception e)
			{
				this.m_Err.println("Could not call invoke class library support for " + node.getLocalName() +":"+e);
			} 

			return;
		} 
		finally 
		{
		}
    }

	
	/**
	 *
	 *
	 * @param   root  
	 */
	 
	private void setRoot(RootDoc root)
	{
		this.m_RootDoc = root;
	}

    /**
     * Adds a given output file name as a output location on the stack of output streams
     */

    public void addOutput(String outputFileName) throws FileNotFoundException
	{
		addOutput(new FileOutputStream(outputFileName));
	}

    /**
     * Adds the given output stream on the stack of output streams.
     */

	public void addOutput(OutputStream output)
	{
		this.m_Out.push(new PrintWriter(output));
	}

    /**
     * Pop the current output stream off the output stream stack.
     */

    public void removeOutput()
	{
		this.m_Out.pop();
	}


	private void setErr(OutputStream err)
	{
		this.m_Err = new PrintWriter(err);
	}
		
    private Hashtable 			m_TagLibraries;
	
    private RootDoc 			m_RootDoc;
	
    private Stack               m_Out;

    private PrintWriter 		m_Err;
}
