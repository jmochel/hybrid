H34936
s 00027/00006/00297
d D 1.11 01/11/02 11:01:32 jmochel 12 11
c Added comments
cC
cK21402
cZ-05:00
e
s 00006/00016/00297
d D 1.10 01/10/03 15:59:53 jmochel 11 10
c Adding attribute name expansion
cC
cK46504
e
s 00022/00039/00291
d D 1.9 01/09/14 15:19:29 jmochel 10 9
c Added the redirect feature and extended exception handling
cC
cK59984
e
s 00014/00011/00316
d D 1.8 01/08/31 16:31:44 jmochel 9 8
c Now all works for a simple class tag and cntents
cC
cK40844
e
s 00007/00006/00320
d D 1.7 01/08/31 15:27:28 jmochel 8 7
c Diagnostics changes.
cC
cK38927
e
s 00071/00044/00255
d D 1.6 01/08/16 09:49:48 jmochel 7 6
c More adaptations to XML DOM
cC
cK34807
e
s 00000/00005/00299
d D 1.5 01/08/14 17:12:54 jmochel 6 5
c It compiles but no longer runs. I merged a lot of code from the other branch and 
cC
cK01125
e
s 00014/00005/00290
d D 1.4 01/08/03 14:21:14 jmochel 5 4
c Converting the whole shebang over to JAXP basis w/Namespaces
cC
cK20824
e
s 00073/00210/00222
d D 1.3 01/06/22 13:00:50 jmochel 4 3
c Refactoring for XML namespaces and using XML APIs internally
cC
cK09326
e
s 00000/00000/00432
d D 1.2 01/05/29 12:33:55 jmochel 3 2
c mvdir
cC
cHdevilmountain.corp.foliage.com
cK02145
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/CodeGeneratorEngine.java
cZ-04:00
e
s 00432/00000/00000
d D 1.1 01/03/14 22:44:27 jmochel 2 1
cC
cF1
cK43201
cO-rw-rw-r--
e
s 00000/00000/00000
d D 1.0 01/03/14 22:44:27 jmochel 1 0
c BitKeeper file i:/Repository/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/CodeGeneratorEngine.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHmordanith.ne.mediaone.net
cK24033
cPsrc/org/mushin/templatecodegenerator/CodeGeneratorEngine.java
cR8830c4a4
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

D 10
import java.io.PrintWriter;
D 4
import java.io.InputStream;
E 4
import java.io.OutputStream;
import java.io.IOException;
E 10
I 10
import java.io.*;
E 10

D 4
import java.util.StringTokenizer;

E 4
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
import java.util.Hashtable;
import java.util.Collection;
import java.util.Iterator;
I 8
import java.util.Stack;
E 8

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.FieldDoc;

I 4
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

E 4
public class CodeGeneratorEngine
{
I 12
    /**
     *  Basic public constructor
     *
     *  by default the <code>CodeGeneratorEngine</code> sets up system.out
     *  as its default output location.
     */

E 12
	public CodeGeneratorEngine()
	{
D 4
	    this.rootDoc = null;
E 4
I 4
	    this.m_RootDoc = null;
E 4
		
D 4
	    this.out = null;
	    this.err = null;	
E 4
I 4
D 9
	    this.m_Out = null;
E 9
	    this.m_Err = null;
I 9
        this.m_Out = new Stack();
E 9
E 4

D 4
	    this.tagLibraries = new Hashtable();
E 4
I 4
	    this.m_TagLibraries = new Hashtable();
I 9

        //
        // Set up the output streams
        //

        addOutput(System.out);
        setErr(System.err);
D 12

E 12
E 9
E 4
	}
	
D 4

E 4
	/**
D 10
	 * 
E 10
I 10
	 *
E 10
D 12
	 *
E 12
	 * @param   root  		The root document that carries the package/class information
D 4
	 * @param   templateStream  The stream containing the input template
E 4
	 * @param   outputStream  	The stream to which the output will be delivered
	 *
D 10
	 * @exception   InvalidArgumentException  
	 * @exception   IOException  
E 10
I 10
	 * @exception   InvalidTagException
	 * @exception   IOException
E 10
	 */
	 
D 4
	public void start(RootDoc root, InputStream	templateStream, OutputStream outputStream, OutputStream errStream)
		throws InvalidArgumentException, IOException
E 4
I 4
D 9
	public void start(RootDoc root, Document templateDoc, OutputStream outputStream, OutputStream errStream)
E 9
I 9
	public void start(RootDoc root, Document templateDoc)
E 9
D 10
		throws InvalidArgumentException, IOException, NoSuchMethodException
E 10
I 10
		throws ExpansionException
E 10
E 4
	{
D 6
		System.out.println("CodeGeneratorEngine::start called");
E 6
		String templateBuffer = null;
		
		//
		// Initialize members
		//
		
D 4
		this.rootDoc = root;
E 4
I 4
		this.m_RootDoc = root;
E 4
		
		try
		{
			//
			// Initialize the Tag Libraries
			//

D 4
			Iterator iter = this.tagLibraries.values().iterator();
E 4
I 4
			Iterator iter = this.m_TagLibraries.values().iterator();
E 4

			while (iter.hasNext())
			{
D 4
				((ITagLibrary)iter.next()).init(root, this);
E 4
I 4
				((ITagLibrary) iter.next()).init(root, this);
E 4
			}

D 9
			//
D 4
			// Read in a template buffer from the stream
			//
			
			byte[] bfr = new byte[templateStream.available()];
			templateStream.read(bfr);
			templateBuffer = new String(bfr);

			//
E 4
			// Set up the output streams
			//
E 9

D 8
			setOutput(outputStream);
E 8
I 8
D 9
			addOutput(outputStream);
E 8
			setErr(errStream);
			
E 9
			//
			// Start processing the template
			//
					
D 4
			expandTemplate(templateBuffer);
E 4
I 4
			expandTemplate(templateDoc);
E 4
		} 
		finally
		{
		}
	}
D 12
	
    public void addLibrary(String libraryNameSpace, String libraryClassName) 
E 12
I 12

    /**
     * Adds a TagLibrary to the set of usable TagLibraries
     */
    public void addLibrary(String libraryNameSpace, String libraryClassName)
E 12
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class libraryClass;
		
		// Locate the class of the library and instantiate it.
		
		libraryClass = Class.forName(libraryClassName);
		
		ITagLibrary tagLibrary = (ITagLibrary) libraryClass.newInstance();
		
		// Add it to the tag libraries
		
D 4
		this.tagLibraries.put(libraryNameSpace, tagLibrary);
E 4
I 4
		this.m_TagLibraries.put(libraryNameSpace, tagLibrary);
E 4
    }

I 7
    /**
     *   This is the main generating code. It looks through a given template
     *	and attempts to find XML-tags
     *   (e.g. "Header goes here <method>Generate this</method> and here's the footer") and calls the
     *   methods which corresponds to the tagnames.
     *
     *	@param   template  the "XML"-template
     */

     public void expandTemplateChildren(Node node)
D 10
         throws NoSuchMethodException
E 10
I 10
         throws ExpansionException
E 10
     {
            // No matter what the type of node, if there are child nodes, recurse down them

            NodeList children = null;

            children = node.getChildNodes();

            for (int k = 0 ; k < children.getLength(); k++)
            {
                expandTemplate(children.item(k));
            }
    }

E 7
   /**
    *   This is the main generating code. It looks through a given template 
    *	and attempts to find XML-tags 
    *   (e.g. "Header goes here <method>Generate this</method> and here's the footer") and calls the 
    *   methods which corresponds to the tagnames.
    *
    *	@param   template  the "XML"-template
    */

D 4
   public void expandTemplate(String templateBfr)
   {
   		//
		// Generate a tokenized stream, with "<" as tokens also.
		//
		
		StringTokenizer tmp = new StringTokenizer(templateBfr,"<",true);
	  
		Object[] params = new Object[1];
		
		Object[] paramsSimple = new Object[0];
		
		Class[] strArr = { java.lang.String.class };
		
		Class[] nullArr = { };
E 4
I 4
	public void expandTemplate(Node node)
D 10
	    throws NoSuchMethodException
E 10
I 10
	    throws ExpansionException
E 10
	{
		// Get basic information about the node.
E 4

D 4
		//
		// For each token
		//   
		
		while (tmp.hasMoreTokens())
E 4
I 4
		short type = node.getNodeType();
		String name = node.getNodeName();
		String value = node.getNodeValue();
		NamedNodeMap attributes = node.getAttributes();

D 7
		// If this is an element, do appropriate data things...

		if (type == Node.ELEMENT_NODE)
E 4
		{
D 4
			String token = tmp.nextToken("<");
			
			// Data or Action element
			
			if (token.equals("<")) // Start of command
E 4
I 4
			if (node.hasChildNodes() == true)
E 4
			{
D 4
				String cmd = tmp.nextToken("</>");
				
				// 
				// Another '<' means that we have an escaped '<<'
				// 
				
				if (cmd.equals("<")) // Escaped <
				{
					// So simply print the '<' and go on.
									
					this.out.print("<");
					continue;
				}

				//
				// Check if it is a block (an action element such as <class>...</class>
				// 	block or single data element such as <name/>
				//
				// If the next token is not one of the following delimiters 
				// throw an error
				
				if ("/>".indexOf(token = tmp.nextToken("/>")) == -1)
				{
					throw new IllegalStateException("Expected '>' or '/', got:"+token);
				}

				//
				// If the next token is not a "/" 
				//	we have an action element
				//
				
				if (!token.equals("/"))
				{
					//
					// Get template and place it in a temp buffer
					//
					
					StringBuffer blockTmp = new StringBuffer();
					
					// Walk through the tokens, accumulating 
					// everything until we hit the next '<'
					
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
									{
										throw new IllegalStateException("Expected '>', got:"+token);
									}

									// Block method exists?
									
									try
									{
										invokeMethodForActionTag(cmd, blockTmp);
										
										break; // Continue with next command
									} 
									catch (NoSuchMethodException e)
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
					{
						throw new IllegalStateException("Expected '>', got:"+token);
					}
			
					try
					{
					
						// Method command
						
						invokeMethodForDataTag(cmd);
						
					} 
					catch (NoSuchMethodException e)
					{
						/*
						try
						{
							// What is this for : printField(cmd);
						} 
						catch (NoSuchFieldException ee)
						{
							System.err.println("Unknown command:"+cmd);
							continue;
						}
						*/
					}
				}
E 4
I 4
				invokeMethodForActionTag(node);
			}
E 4
		}
D 4
		else
E 4
I 4

		// If there are child nodes recurse down them
E 7
I 7
		// If this is an element, and it has children, it is a action element (like <class>...</class>
        // Set up the context for that tag...
E 7

D 7
		NodeList children = null;

		if (node.hasChildNodes() == true)
E 4
		{
D 4
			this.out.print(token);
		}
      }
      
      this.out.flush();
   }
E 4
I 4
			children = node.getChildNodes();
E 7
I 7
		if ( node.hasChildNodes() == true)
        {
            // If it is a element node, invoke the action method to set the context
E 7
E 4

I 4
D 7
			for (int k = 0 ; k < children.getLength(); k++)
			{
				expandTemplate(children.item(k));
			}
		}
		else
		{
			if (type == Node.ELEMENT_NODE)
			{
				// dump out the expanded data tags
			}
E 7
I 7
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
E 7
E 4

I 4
D 7
			System.out.print(node.getNodeValue());
		}
		
E 7
I 7
            if (type == Node.ELEMENT_NODE )
            {
                invokeMethodForDataTag(node);
            }
            else
            {
D 9
                System.out.print(node.getNodeValue());
E 9
I 9
                ((PrintWriter) m_Out.peek()).print(node.getNodeValue());
E 9
            }
        }
E 7
	}
E 4

D 4
	public void invokeMethodForActionTag(String cmd, StringBuffer block)
E 4
I 4
	public void invokeMethodForActionTag(Node node)
E 4
D 10
	  	throws NoSuchMethodException
E 10
I 10
	  	throws ExpansionException
E 10
	{
D 6
		System.err.println("CodeGeneratorEngine::invokeMethodForActionTag called");
I 4
D 5
/*
E 5
I 5

E 6
E 5
E 4
D 7
		Class[] strArr = { java.lang.String.class };
E 7
I 7
D 10
		Class[] strArr = { Node.class };
E 7
 
E 10
D 11
		// Create the action tag.
		
D 4
		ActionTag axnTag = new ActionTag(cmd);
E 4
I 4
		ActionTag axnTag = new ActionTag(node);
E 4
		
E 11
		// Locate the appropriate tag library
I 5

		ITagLibrary library = null;

D 11
        if (axnTag.getNamespace() == null )
E 11
I 11
        if (node.getNamespaceURI() == null )
E 11
        {
            library = (ITagLibrary) this.m_TagLibraries.get("");
        }
        else
        {
D 11
            library = (ITagLibrary) this.m_TagLibraries.get(axnTag.getNamespace());
E 11
I 11
            library = (ITagLibrary) this.m_TagLibraries.get(node.getNamespaceURI());
E 11
        }
E 5
		
D 4
		ITagLibrary library = (ITagLibrary) this.tagLibraries.get(axnTag.getNamespace());
E 4
I 4
D 5
		ITagLibrary library = (ITagLibrary) this.m_TagLibraries.get(axnTag.getNamespace());
E 4
		
		// Check if the common tag library has the tag.
E 5
I 5
D 10
		// Check if the tag library has the tag.
E 5

		Method mthd = library.getClass().getMethod("axn_" + axnTag.getName(), strArr);

		if ( mthd == null )
		{
			throw new NoSuchMethodException();
		}
E 10
I 10
D 11
        library.invokeActionTag(node);
E 11
I 11
D 12
        library.expandActionNode(node);
E 12
I 12
        library.expandActionElement(node);
E 12
E 11
E 10
D 5
								
E 5
I 5
D 7
/*								
E 7
I 7

E 7
E 5
D 10
		try
		{
D 7
			Object[] args = new Object[] {block.toString()};
E 7
I 7
			Object[] args = new Object[] {node};
E 7
			
			mthd.invoke(library, args);
		} 
		catch (InvocationTargetException e)
		{
D 4
			this.err.println("Could not call "+cmd+":"+e+" "+e.getTargetException());
E 4
I 4
D 7
			this.m_Err.println("Could not call "+cmd+":"+e+" "+e.getTargetException());
E 7
I 7
			this.m_Err.println("Could not call a method for " + axnTag.getName() + ":"+e+" "+e.getTargetException());
E 7
E 4
			e.getTargetException().printStackTrace();
		} 
		catch (IllegalAccessException e)
		{
D 4
			this.err.println("Could not call "+cmd+":"+e);
E 4
I 4
D 7
			this.m_Err.println("Could not call "+cmd+":"+e);
E 7
I 7
			this.m_Err.println("Did not have the accss to call a method for " + axnTag.getName() + ":"+e);
E 7
E 4
			e.printStackTrace();
		}
E 10
D 4

E 4
I 4
D 7
  */
E 4
		return;
E 7
I 7
 		return;
E 7
	}
	
D 7
	public void invokeMethodForDataTag(String cmd)
E 7
I 7
	public void invokeMethodForDataTag(Node node)
E 7
D 10
	   	throws NoSuchMethodException
E 10
I 10
	   	throws ExpansionException
E 10
	{
D 11
		Class[] nullArr = new Class[0];
D 6
      
		System.err.println("CodeGeneratorEngine::invokeMethodForDataTag called");
E 6
D 4

E 4
I 4
D 7
/*
E 7
I 7

E 11
E 7
E 4
		try
		{
D 11
			// Create the data tag.
			
D 4
			DataTag dataTag = new DataTag(cmd);
E 4
I 4
D 7
			DataTag dataTag = new DataTag(cmd, "", null);
E 7
I 7
			DataTag dataTag = new DataTag(node);
E 7
E 4
			
E 11
			// Locate the appropriate tag library
			
D 4
			ITagLibrary library = (ITagLibrary) this.tagLibraries.get(dataTag.getNamespace());
E 4
I 4
D 7
			ITagLibrary library = (ITagLibrary) this.m_TagLibraries.get(dataTag.getNamespace());
E 7
I 7
D 11
			ITagLibrary library = (ITagLibrary) this.m_TagLibraries.get( (dataTag.getNamespace() == null) ? "" : dataTag.getNamespace() );
E 11
I 11
			ITagLibrary library = (ITagLibrary) this.m_TagLibraries.get( (node.getNamespaceURI() == null) ? "" : node.getNamespaceURI() );
E 11
E 7
E 4

			// Get the expansion of the data tag 
			
			try
			{
D 11
				String res = library.invokeDataTag(dataTag);
E 11
I 11
D 12
				String res = library.expandDataNode(node);
E 12
I 12
				String res = library.expandDataElement(node);
E 12
E 11

D 4
				this.out.print(res);
E 4
I 4
D 8
				this.m_Out.print(res);
E 8
I 8
				((PrintWriter) this.m_Out.peek()).print(res);
E 8
E 4
D 9
			} 
E 9
I 9
                ((PrintWriter) this.m_Out.peek()).flush();
			}
E 9
			catch (Exception e)
			{
D 4
				this.err.println("Could not call "+cmd+":"+e);
E 4
I 4
D 7
				this.m_Err.println("Could not call "+cmd+":"+e);
E 7
I 7
D 11
				this.m_Err.println("Could not call invoke class library support for " + dataTag.getName() +":"+e);
E 11
I 11
				this.m_Err.println("Could not call invoke class library support for " + node.getLocalName() +":"+e);
E 11
E 7
E 4
			} 

			return;
		} 
		finally 
		{
		}
I 4
D 7
*/
E 4
	}
E 7
I 7
    }
E 7

	
	/**
	 *
	 *
	 * @param   root  
	 */
	 
	private void setRoot(RootDoc root)
	{
D 4
		this.rootDoc = root;
E 4
I 4
		this.m_RootDoc = root;
E 4
	}
D 10
	
E 10
I 10

I 12
    /**
     * Adds a given output file name as a output location on the stack of output streams
     */

E 12
    public void addOutput(String outputFileName) throws FileNotFoundException
	{
		addOutput(new FileOutputStream(outputFileName));
	}

I 12
    /**
     * Adds the given output stream on the stack of output streams.
     */

E 12
E 10
D 8
	private void setOutput(OutputStream output)
E 8
I 8
D 9
	private void addOutput(OutputStream output)
E 9
I 9
	public void addOutput(OutputStream output)
E 9
E 8
	{
D 4
		this.out = new PrintWriter(output);
E 4
I 4
D 8
		this.m_Out = new PrintWriter(output);
E 8
I 8
D 10
		this.m_Out.add(new PrintWriter(output));
E 10
I 10
		this.m_Out.push(new PrintWriter(output));
	}

I 12
    /**
     * Pop the current output stream off the output stream stack.
     */

E 12
    public void removeOutput()
	{
		this.m_Out.pop();
E 10
E 8
E 4
	}
I 12

E 12

	private void setErr(OutputStream err)
	{
D 4
		this.err = new PrintWriter(err);
E 4
I 4
		this.m_Err = new PrintWriter(err);
E 4
	}
		
D 4
    private Hashtable 			tagLibraries;
	
    private RootDoc 			rootDoc;
	
    private PrintWriter 		out;
E 4
I 4
    private Hashtable 			m_TagLibraries;
E 4
	
D 4
    private PrintWriter 		err;	
E 4
I 4
    private RootDoc 			m_RootDoc;
E 4
	
I 4
D 8
    private PrintWriter 		m_Out;
E 4
	
E 8
I 8
    private Stack               m_Out;

E 8
I 4
    private PrintWriter 		m_Err;
E 4
}
E 2
I 1
E 1
