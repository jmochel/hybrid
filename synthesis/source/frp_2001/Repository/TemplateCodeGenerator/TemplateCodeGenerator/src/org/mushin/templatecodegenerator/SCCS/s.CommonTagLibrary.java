H18296
s 00111/00037/02556
d D 1.8 01/10/03 15:59:53 jmochel 9 8
c Adding attribute name expansion
cC
cK39502
e
s 00134/00048/02459
d D 1.7 01/09/14 15:19:29 jmochel 8 7
c Added the redirect feature and extended exception handling
cC
cK51891
e
s 00000/00005/02507
d D 1.6 01/08/31 15:27:29 jmochel 7 6
c Diagnostics changes.
cC
cK64806
e
s 00001/00001/02511
d D 1.5 01/08/16 09:49:48 jmochel 6 5
c More adaptations to XML DOM
cC
cK16040
e
s 02090/00246/00422
d D 1.4 01/08/14 17:12:54 jmochel 5 4
c It compiles but no longer runs. I merged a lot of code from the other branch and 
cC
cK15231
e
s 00083/00082/00585
d D 1.3 01/06/22 13:00:50 jmochel 4 3
c Refactoring for XML namespaces and using XML APIs internally
cC
cK24634
e
s 00000/00000/00667
d D 1.2 01/05/29 12:33:55 jmochel 3 2
c mvdir
cC
cHdevilmountain.corp.foliage.com
cK33430
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/CommonTagLibrary.java
cZ-04:00
e
s 00667/00000/00000
d D 1.1 01/03/14 22:44:45 jmochel 2 1
cC
cF1
cK13420
cO-rw-rw-r--
e
s 00000/00000/00000
d D 1.0 01/03/14 22:44:45 jmochel 1 0
c BitKeeper file i:/Repository/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/CommonTagLibrary.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHmordanith.ne.mediaone.net
cK15973
cPsrc/org/mushin/templatecodegenerator/CommonTagLibrary.java
cR899f9c13
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
D 5
 * 
E 5
I 5
 *
E 5
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
D 5
 * 
E 5
I 5
 *
E 5
 * The Original Code is "Template Code Generator".
D 5
 * 
 * The Initial Developer of the Original Code is Jim Jackl-Mochel.  
E 5
I 5
 *
 * The Initial Developer of the Original Code is Jim Jackl-Mochel.
E 5
 * Portions created by Jim Jackl-Mochel are
 * Copyright (C) 1998, 1999, 2000 Jim Jackl-Mochel.  All
 * Rights Reserved.
D 5
 * 
 * Contributor(s): 
E 5
I 5
 *
 * Contributor(s):
E 5
 */
D 5
 
E 5
I 5

E 5
package org.mushin.templatecodegenerator;

D 9
import java.util.Stack;
import java.util.Hashtable;
import java.util.Date;
E 9
import java.text.DateFormat;
I 5
D 9
import java.util.Enumeration;
E 9
I 9
import java.util.*;
E 9
I 8
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
E 8
E 5

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.Type;
I 4
import org.w3c.dom.Node;
I 8
import org.w3c.dom.DOMException;
E 8
E 4

/**
D 5
 * 
E 5
I 5
 *
E 5
 * @author Jim Jackl-Mochel
D 5
 * @version $version:$ 
E 5
I 5
 * @version $version:$
E 5
 */
D 5
 
E 5
I 5

E 5
public class CommonTagLibrary implements ITagLibrary
{
	public CommonTagLibrary()
	{
D 4
    	this.rootDoc = null;
		this.engine = null;
E 4
I 4
    	this.m_RootDoc = null;
		this.m_Engine = null;
E 4
D 5
	
E 5
I 5

E 5
D 4
		this.contextStack = new Stack();	
		this.globalData = new Hashtable();
E 4
I 4
		this.m_ContextStack = new Stack();
		this.m_GlobalData = new Hashtable();
E 4
	}
D 5
	
    public void init(RootDoc rootDoc, CodeGeneratorEngine engine) 
E 5
I 5

I 9
    /**
     *  Initializes the Tag Library
     *  @see ITagLibrary#init
     */
E 9
    public void init(RootDoc rootDoc, CodeGeneratorEngine engine)
E 5
D 8
		throws InvalidArgumentException
E 8
I 8
		throws ExpansionException
E 8
    {
	    //
	    // Preconditions
	    //

	    if (rootDoc == null)
	    {
D 4
	    	throw new InvalidArgumentException("Common Tag Library Init was given a null rootDoc",null);
E 4
I 4
D 8
	    	throw new InvalidArgumentException("Common Tag Library Init was given a null m_RootDoc",null);
E 8
I 8
	    	throw new InvalidTagException("Common Tag Library Init was given a null m_RootDoc",null);
E 8
E 4
	    }

	    if (engine == null)
	    {
D 4
	    	throw new InvalidArgumentException("Common Tag Library Init was given a null engine",null);
E 4
I 4
D 8
	    	throw new InvalidArgumentException("Common Tag Library Init was given a null m_Engine",null);
E 8
I 8
	    	throw new InvalidTagException("Common Tag Library Init was given a null m_Engine",null);
E 8
E 4
	    }

D 5
		
E 5
I 5

E 5
		//
D 5
		// Init some data members 
E 5
I 5
		// Init some data members
E 5
		//
D 5
		
E 5
I 5

E 5
D 4
		this.rootDoc = rootDoc;
		this.engine = engine;
E 4
I 4
		this.m_RootDoc = rootDoc;
		this.m_Engine = engine;
E 4
D 5
		
E 5
I 5

E 5
		//
		// Add some global data tags
		//
D 5
		
E 5
I 5

E 5
		// Add the current date
D 5
		
E 5
I 5

E 5
		Date currDate = new Date();

		String dateAsString = DateFormat.getDateInstance().format(currDate);
		String timeAsString = DateFormat.getTimeInstance().format(currDate);
D 5
		
E 5
I 5

E 5
D 4
		this.globalData.put("date", dateAsString);
E 4
I 4
		this.m_GlobalData.put("date", dateAsString);
E 4
D 5
		
		// Add the current time 
		
E 5
I 5

		// Add the current time

E 5
D 4
		this.globalData.put("time", timeAsString);
E 4
I 4
		this.m_GlobalData.put("time", timeAsString);
E 4
    }
D 5
	
	public String invokeDataTag(DataTag tag) 
E 5
I 5

D 9
	public String invokeDataTag(DataTag tag)
E 9
I 9
	public String expandDataNode(Node node)
E 9
E 5
D 8
		throws InvalidArgumentException
E 8
I 8
		throws ExpansionException
E 8
	{
		String expandedTag = null;
D 5
				
E 5
I 5

E 5
D 7
		System.err.println("CommonTagLibrary::invokeDataTag called");

E 7
		//
		// Preconditions
		//

D 9
		if (tag == null)
E 9
I 9
		if (node == null)
E 9
		{
D 7
			System.err.println("CommonTagLibrary Tag is null");
E 7
D 8
			throw new InvalidArgumentException("tag was null",null);
E 8
I 8
D 9
			throw new InvalidTagException("tag was null",null);
E 9
I 9
			throw new InvalidTagException("node was null",null);
E 9
E 8
		}

D 9
		//
		// First check in the global variable table.
		//
E 9
I 9
        // String data to be expanded.
E 9
D 5
		
E 5
I 5

E 5
D 4
		expandedTag = (String) this.globalData.get(tag.getName());
E 4
I 4
D 9
		expandedTag = (String) this.m_GlobalData.get(tag.getName());
E 9
I 9
        String name = node.getNodeName();
E 9
E 4
D 5
		
E 5
I 5
D 7

E 5
		System.err.println("CommonTagLibrary expandedTag = " + expandedTag);
E 7

D 9
		if ( expandedTag != null )
		{
			return (expandedTag);
		}
E 9
I 9
        return expandStringFromContext(name);
    }
E 9
D 5
		
E 5
I 5

E 5
D 9
		//
D 5
		// walk "down" the stack until we find a parameter of appropriate name 
E 5
I 5
		// walk "down" the stack until we find a parameter of appropriate name
E 5
		// 	or until we run out of parm sets
		//
E 9
I 9
    /**
     *  Walks through the existing contexts and expands the string when it finds a match...
     */
E 9

D 4
		int parmSetCount = this.contextStack.size();
E 4
I 4
D 9
		int parmSetCount = this.m_ContextStack.size();
E 9
I 9
    private String expandStringFromContext(String name) throws InvalidTagException {
        String expandedTag;
        //
        // First check in the global variable table.
        //
E 9
E 4
D 5
		
E 5
I 5

E 5
D 9
		for (int i = (parmSetCount-1); i >= 0; i-- )
		{
D 4
			Hashtable parmSet = (Hashtable) this.contextStack.elementAt(i);
E 4
I 4
			Hashtable parmSet = (Hashtable) this.m_ContextStack.elementAt(i);
E 9
I 9
        expandedTag = (String) this.m_GlobalData.get(name);
E 9
E 4
D 5
			
E 5
I 5

E 5
D 9
			expandedTag = (String) parmSet.get(tag.getName());
E 9
I 9
        if ( expandedTag != null )
        {
            return (expandedTag);
        }
E 9
D 5
			
E 5
I 5

E 5
D 9
			if ( expandedTag != null )
D 5
			{ 
E 5
I 5
			{
E 5
				break;
			}
		}
E 9
I 9
        //
        // walk "down" the stack until we find a parameter of appropriate name
        // 	or until we run out of parm sets
        //
E 9
D 5
		
E 5
I 5

E 5
D 9
		if (expandedTag == null )
		{
D 8
			throw new InvalidArgumentException("Unable to find the data tag for " + tag.getName(),null);
E 8
I 8
			throw new InvalidTagException("Unable to find the data tag for " + tag.getName(),null);
E 8
		}
E 9
I 9
        int parmSetCount = this.m_ContextStack.size();
E 9
D 5
		
		return (expandedTag);		
E 5
I 5

D 9
		return (expandedTag);
E 5
	}
E 9
I 9
        for (int i = (parmSetCount-1); i >= 0; i-- )
        {
            Hashtable parmSet = (Hashtable) this.m_ContextStack.elementAt(i);

            expandedTag = (String) parmSet.get(name);

            if ( expandedTag != null )
            {
                break;
            }
        }

        if (expandedTag == null )
        {
            throw new InvalidTagException("Unable to find the data tag for " + name,null);
        }
E 9
D 5
	
E 5
I 5

I 9
        return (expandedTag);
    }
E 9
E 5
D 4
	public void axn_class(String templateBfr) throws InvalidArgumentException, IncorrectNestingException
E 4
I 4
D 8
	public void axn_class(Node node) throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8

D 9
    public void invokeActionTag(Node node) throws ExpansionException
E 9
I 9
    private String expandAttributeValueFromContext(String value)
    {
            // If value has tokens to be expanded.

            StringTokenizer tokenizer = new StringTokenizer(value, "{}");

            boolean hasTokens = ( tokenizer.countTokens() > 1)  ?  true : false;

            if (hasTokens)
            {
                // Identify the tokens

                while (tokenizer.hasMoreTokens())
                {
                    System.out.println("TOKEN: " + tokenizer.nextToken("{}"));
                }
                // Make a list of the unique ones.
                // For each unique token
                    // Find the expansion of that token in the context.
                    // Expand that tokens

            }


        return(value);
    }

    public void expandActionNode(Node node) throws ExpansionException
E 9
    {
        Class[] strArr = { Node.class };

        // Check if the tag library has the tag.

        Method mthd = null;

        try
        {
            mthd = this.getClass().getMethod("axn_" + node.getNodeName(), strArr);
        }
        catch (NoSuchMethodException e)
        {
            throw new InvalidTagException("Tag " + node.getNodeName() + " is unsupported",e);
        }
        catch (SecurityException e)
        {
            throw new InvalidTagException("Tag " + node.getNodeName() + " is supported by a private method. It is probably misspelled.",e);
        }

        beginAction(node);

        Object[] args = new Object[] {node};

        try
        {
            mthd.invoke(this, args);
        }
        catch (IllegalAccessException e)
        {
            throw new InvalidTagException("Invalid access for action tag", e);
        }
        catch (IllegalArgumentException e)
        {
            throw new InvalidTagException("Invalid argument for for action tag", e);
        }
        catch (InvocationTargetException e)
        {
            throw new InvalidTagException("Action tag doesn't appear to be supported ", e);
        }

        endAction(node);
    }

I 9
    /**
     * Basic code that is called before each action code that is called. It implements the standard attributes of the tags
     * Currently it only implements the redirect attribute.
     *
     */

E 9
    public void beginAction(Node node) throws ExpansionException
    {
        try
        {
            // Redirect the output

            Node attr;

            if ((attr = node.getAttributes().getNamedItem("redirect")) != null )
            {
D 9
                this.m_Engine.addOutput(attr.getNodeValue());
E 9
I 9
                // Preprocess the contents of the attribute so that any data items get expanded.
                // So "{name}TestCase.java" gets expanded to "StdClassTestCase.java" before getting passed
                // on for the redirection.

                String expandedAttrValue;

                expandedAttrValue = expandAttributeValueFromContext(attr.getNodeValue());

                // Then pass on the expanded attribute to the output stack.

                this.m_Engine.addOutput(expandedAttrValue);
E 9
            }
        }
        catch (FileNotFoundException e)
        {
            throw new ExpansionException("Tag redirect attribute had a invalid output file name", e);
        }
        catch (DOMException e)
        {
            throw new ExpansionException("Tag redirect attribute is missing an output file name", e);
        }
    }

I 9
    /**
     * Basic code that is called after each action that is called. It implements the standard attributes of the tags
     * Currently it only implements the redirect attribute.
     *
     */

E 9
    public void endAction(Node node) throws ExpansionException
    {
        // Remove the redirect

        Node attr;

        if ((attr = node.getAttributes().getNamedItem("redirect")) != null )
        {
            this.m_Engine.removeOutput();
        }
    }
I 9

    /**
     * Implements a null or shill action. Used as a wrapper for getting access to attribute expansion from within
     * a action tag that is not yet evaluated...
     */

    public void axn_shill(Node node) throws ExpansionException
	{
        // Expand the current template

        this.m_Engine.expandTemplateChildren(node);
	}
E 9

	public void axn_class(Node node) throws ExpansionException
E 8
E 4
	{

		//
		// Preconditions
		//

D 4
		if (templateBfr == null)
E 4
I 4
		if (node == null)
E 4
		{
D 4
			throw new InvalidArgumentException("<class> tag was given a null template bufer",null);
E 4
I 4
D 8
			throw new InvalidArgumentException("<class> tag was given a null node",null);
E 8
I 8
			throw new InvalidTagException("<class> tag was given a null node",null);
E 8
E 4
		}

D 5
		System.err.println("CommonTagLibrary::axn_class called");

E 5
D 4
		ClassDoc[] classes = this.rootDoc.classes();
E 4
I 4
		ClassDoc[] classes = this.m_RootDoc.classes();
E 4

		//
		// Expand as necessary
		//
D 5
		
E 5
I 5

E 5
		if (classes == null)
		{
			return;
		}

		//
		// For each class in the root
		//
D 5
		
E 5
I 5

E 5
		for (int i = 0; i < classes.length; i++)
		{
			//
			// Set the current class
D 5
			// 
			
E 5
I 5
			//

E 5
D 4
			this.currentClass = classes[i];
E 4
I 4
			this.m_CurrentClass = classes[i];
E 4
D 5
			
E 5
I 5

E 5
			//
			// Create the context for the calls
			//
D 5
			
E 5
I 5

E 5
			Hashtable parmSet = new Hashtable();
D 5
			
E 5
I 5

E 5
			//
			// Put the approriate data elements into the context
			//
D 5
			
E 5
I 5

E 5
			parmSet.put("name", classes[i].name());
			parmSet.put("qualifiedName", classes[i].qualifiedName());
D 5
			parmSet.put("qualifiedTypeName", classes[i].qualifiedTypeName());			
E 5
I 5
			parmSet.put("qualifiedTypeName", classes[i].qualifiedTypeName());
E 5
			parmSet.put("package", classes[i].containingPackage().name());
			parmSet.put("superclass", classes[i].superclass().name());
D 5
						
E 5
I 5

E 5
			// Add the context to the stack
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.push(parmSet);
E 4
I 4
			this.m_ContextStack.push(parmSet);
E 4
D 5
			
E 5
I 5

E 5
			// Expand the current template
D 5
			
E 5
I 5

E 5
D 4
			this.engine.expandTemplate(templateBfr);
E 4
I 4
D 6
			this.m_Engine.expandTemplate(node);
E 6
I 6
			this.m_Engine.expandTemplateChildren(node);
E 6
E 4
D 5
			
E 5
I 5

E 5
			// Pop the stack.
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.pop();
E 4
I 4
			this.m_ContextStack.pop();
I 8

E 8
E 4
		}
D 5
		
E 5
I 5

E 5
D 4
		this.currentClass = null;
E 4
I 4
		this.m_CurrentClass = null;
E 4
	}
D 5
	
	
E 5
I 5


E 5
D 4
	public void axn_field(String templateBfr) 
		throws InvalidArgumentException, IncorrectNestingException
E 4
I 4
	public void axn_field(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
E 4
	{
		//
		// Preconditions
		//

D 4
		if (templateBfr == null)
E 4
I 4
		if (node == null)
E 4
		{
D 8
			throw new InvalidArgumentException("<field> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<field> tag was given a null template buffer",null);
E 8
		}

D 5
		System.err.println("CommonTagLibrary::axn_field called");

		
E 5
D 4
		if (this.currentClass == null)
E 4
I 4
		if (this.m_CurrentClass == null)
E 4
		{
			throw new IncorrectNestingException("<field> tag was invoked out of context",null);
		}
D 5
	
E 5
I 5

E 5
D 4
		FieldDoc[] fields = this.currentClass.fields();
E 4
I 4
		FieldDoc[] fields = this.m_CurrentClass.fields();
E 4

		//
		// Expand as necessary
		//
D 5
		
E 5
I 5

E 5
		if (fields == null)
		{
			return;
		}


		//
		// For each field in the current class
		//
D 5
		
E 5
I 5

E 5
		for (int i = 0; i < fields.length; i++)
		{
			//
D 5
			// Set the current field			// 
			
E 5
I 5
			// Set the current field			//

E 5
D 4
			this.currentField = fields[i];
E 4
I 4
			this.m_CurrentField = fields[i];
E 4
D 5
			
E 5
I 5

E 5
			//
			// Create the context for the calls
			//
D 5
			
E 5
I 5

E 5
			Hashtable parmSet = new Hashtable();
D 5
			
E 5
I 5

E 5
			//
			// Put the approriate data elements into the context
			//
D 5
			
E 5
I 5

			Integer ndx = new Integer(i);

			parmSet.put("ndx", ndx.toString());

E 5
			parmSet.put("name", fields[i].name());
			parmSet.put("typeName", fields[i].type().typeName());
			parmSet.put("qualifiedTypeName", fields[i].type().qualifiedTypeName());
D 5
						
E 5
I 5


			// Add basic values for data types to the context

			if ("String".equals(fields[i].type().typeName()))
			{
				parmSet.put("minValue", "MINVALUE");
				parmSet.put("maxValue", "MAXVALUE");
				parmSet.put("safeValue", "SAFEVALUE");
				parmSet.put("assignableMinValue", " new String(\"MINVALUE\")");
				parmSet.put("assignableMaxValue", " new String(\"MAXVALUE\")");
				parmSet.put("assignableSafeValue", " new String(\"SAFEVALUE\")");
				parmSet.put("assignableLowBoundaryValue", " new String(\"LOWBOUNDARYVALUE\")");
				parmSet.put("assignableHighBoundaryValue", " new String(\"HIGHBOUNDARYVALUE\")");
				parmSet.put("assignableLowBeyondBoundaryValue", " new String(\"LOWBEYONDBOUNDARYVALUE\")");
				parmSet.put("assignableHighBeyondBoundaryValue", " new String(\"HIGHBEYONDBOUNDARYVALUE\")");
				parmSet.put("assignableDestructiveValue", "null");

			}
			        else if ("boolean".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "true");
			            parmSet.put("safeValue", "true");
			            parmSet.put("maxValue", "false");
			            parmSet.put("assignableMinValue", "true");
			            parmSet.put("assignableSafeValue","true");
			            parmSet.put("assignableMaxValue","false");
			            parmSet.put("assignableLowBoundaryValue", "true");
			            parmSet.put("assignableHighBoundaryValue","false");
			            parmSet.put("assignableLowBeyondBoundaryValue", "true");
			            parmSet.put("assignableHighBeyondBoundaryValue","false");
			            parmSet.put("assignableDestructiveValue","false");

			        }
			        else if ("Boolean".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "true");
			            parmSet.put("safeValue", "true");
			            parmSet.put("maxValue", "false");
			            parmSet.put("assignableMinValue", "new Boolean(true)");
			            parmSet.put("assignableSafeValue","new Boolean(true)");
			            parmSet.put("assignableMaxValue","new Boolean(false)");
			            parmSet.put("assignableLowBoundaryValue", "new Boolean(true)");
			            parmSet.put("assignableHighBoundaryValue","new Boolean(false)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Boolean(true)");
			            parmSet.put("assignableHighBeyondBoundaryValue","new Boolean(false)");
			            parmSet.put("assignableDestructiveValue","null");

			        }
			        else if ("byte".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Byte.MIN_VALUE");
			            parmSet.put("safeValue", "0");
			            parmSet.put("maxValue", "Byte.MAX_VALUE");
			            parmSet.put("assignableMinValue", "Byte.MIN_VALUE");
			            parmSet.put("assignableSafeValue","0");
			            parmSet.put("assignableMaxValue","Byte.MAX_VALUE");
			            parmSet.put("assignableLowBoundaryValue", "Byte.MIN_VALUE");
			            parmSet.put("assignableHighBoundaryValue","Byte.MAX_VALUE");
			            parmSet.put("assignableLowBeyondBoundaryValue", "Byte.MIN_VALUE");
			            parmSet.put("assignableHighBeyondBoundaryValue","Byte.MAX_VALUE");
			            parmSet.put("assignableDestructiveValue","Byte.MAX_VALUE");

			        }
			        else if ("Byte".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Byte.MIN_VALUE");
			            parmSet.put("safeValue", "0");
			            parmSet.put("maxValue", "Byte.MAX_VALUE");
			            parmSet.put("assignableMinValue", "new Byte(Byte.MIN_VALUE)");
			            parmSet.put("assignableSafeValue","new Byte(0)");
			            parmSet.put("assignableMaxValue", "new Byte(Byte.MAX_VALUE)");
			            parmSet.put("assignableLowBoundaryValue", "new Byte(Byte.MIN_VALUE)");
			            parmSet.put("assignableHighBoundaryValue", "new Byte(Byte.MAX_VALUE)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Byte(Byte.MIN_VALUE)");
			            parmSet.put("assignableHighBeyondBoundaryValue", "new Byte(Byte.MAX_VALUE)");
			            parmSet.put("assignableDestructiveValue", "null");

			        }
			        else if ("short".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Short.MIN_VALUE");
			            parmSet.put("safeValue", "1");
			            parmSet.put("maxValue", "Short.MAX_VALUE");
			            parmSet.put("assignableMinValue", "Short");
			            parmSet.put("assignableSafeValue","1");
			            parmSet.put("assignableMaxValue","Short.MAX_VALUE");
			            parmSet.put("assignableLowBoundaryValue", "Short.MIN_VALUE");
			            parmSet.put("assignableHighBoundaryValue","Short.MAX_VALUE");
			            parmSet.put("assignableLowBeyondBoundaryValue", "Short");
			            parmSet.put("assignableHighBeyondBoundaryValue","Short.MAX_VALUE");
			            parmSet.put("assignableDestructiveValue","Short.MAX_VALUE");

			        }
			        else if ("Short".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Short.MIN_VALUE");
			            parmSet.put("safeValue", "1");
			            parmSet.put("maxValue", "Short.MAX_VALUE");
			            parmSet.put("assignableMinValue", "new Short(Short.MIN_VALUE)");
			            parmSet.put("assignableSafeValue","new Short(1)");
			            parmSet.put("assignableMaxValue", "new Short(Short.MAX_VALUE)");
			            parmSet.put("assignableLowBoundaryValue", "new Short(Short.MIN_VALUE)");
			            parmSet.put("assignableHighBoundaryValue", "new Short(Short.MAX_VALUE)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Short(Short.MIN_VALUE)");
			            parmSet.put("assignableHighBeyondBoundaryValue", "new Short(Short.MAX_VALUE)");
			            parmSet.put("assignableDestructiveValue", "null");

			        }
			        else if ("int".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Integer.MIN_VALUE");
			            parmSet.put("safeValue", "1");
			            parmSet.put("maxValue", "Integer.MAX_VALUE");
			            parmSet.put("assignableMinValue", "Integer");
			            parmSet.put("assignableSafeValue","1");
			            parmSet.put("assignableMaxValue","Integer.MAX_VALUE");
			            parmSet.put("assignableLowBoundaryValue", "Integer.MIN_VALUE");
			            parmSet.put("assignableHighBoundaryValue","Integer.MAX_VALUE");
			            parmSet.put("assignableLowBeyondBoundaryValue", "Integer.MIN_VALUE");
			            parmSet.put("assignableHighBeyondBoundaryValue","Integer.MAX_VALUE");
			            parmSet.put("assignableDestructiveValue","Integer.MAX_VALUE");

			        }
			        else if ("Integer".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Integer.MIN_VALUE");
			            parmSet.put("safeValue", "1");
			            parmSet.put("maxValue", "Integer.MAX_VALUE");
			            parmSet.put("assignableMinValue", "new Integer(Integer.MIN_VALUE)");
			            parmSet.put("assignableSafeValue","new Integer(1)");
			            parmSet.put("assignableMaxValue", "new Integer(Integer.MAX_VALUE)");
			            parmSet.put("assignableLowBoundaryValue", "new Integer(Integer.MIN_VALUE)");
			            parmSet.put("assignableHighBoundaryValue", "new Integer(Integer.MAX_VALUE)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Integer(Integer.MIN_VALUE)");
			            parmSet.put("assignableHighBeyondBoundaryValue", "new Integer(Integer.MAX_VALUE)");
			            parmSet.put("assignableDestructiveValue", "null");

			        }
			        else if ("long".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Long.MIN_VALUE");
			            parmSet.put("safeValue", "1");
			            parmSet.put("maxValue", "Long.MAX_VALUE");
			            parmSet.put("assignableMinValue", "Long");
			            parmSet.put("assignableSafeValue","0");
			            parmSet.put("assignableMaxValue","Long.MAX_VALUE");
			            parmSet.put("assignableLowBoundaryValue", "Long.MIN_VALUE");
			            parmSet.put("assignableHighBoundaryValue","Long.MAX_VALUE");
			            parmSet.put("assignableLowBeyondBoundaryValue", "Long.MIN_VALUE");
			            parmSet.put("assignableHighBeyondBoundaryValue","Long.MAX_VALUE");
			            parmSet.put("assignableDestructiveValue","Long.MAX_VALUE");

			        }
			        else if ("Long".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Long.MIN_VALUE");
			            parmSet.put("safeValue", "1");
			            parmSet.put("maxValue", "Long.MAX_VALUE");
			            parmSet.put("assignableMinValue", "new Long(Long.MIN_VALUE)");
			            parmSet.put("assignableSafeValue","new Long(0)");
			            parmSet.put("assignableMaxValue", "new Long(Long.MAX_VALUE)");
			            parmSet.put("assignableLowBoundaryValue", "new Long(Long.MIN_VALUE)");
			            parmSet.put("assignableHighBoundaryValue", "new Long(Long.MAX_VALUE)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Long(Long.MIN_VALUE)");
			            parmSet.put("assignableHighBeyondBoundaryValue", "new Long(Long.MAX_VALUE)");
			            parmSet.put("assignableDestructiveValue", "null");

			        }
			        else if ("float".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Float.MIN_VALUE");
			            parmSet.put("safeValue", "1");
			            parmSet.put("maxValue", "Float.MAX_VALUE");
			            parmSet.put("assignableMinValue", "Float.MIN_VALUE");
			            parmSet.put("assignableSafeValue","0");
			            parmSet.put("assignableMaxValue","Float.MAX_VALUE");
			            parmSet.put("assignableLowBoundaryValue", "Float.MIN_VALUE");
			            parmSet.put("assignableHighBoundaryValue","Float.MAX_VALUE");
			            parmSet.put("assignableLowBeyondBoundaryValue", "Float.MIN_VALUE");
			            parmSet.put("assignableHighBeyondBoundaryValue","Float.MAX_VALUE");
			            parmSet.put("assignableDestructiveValue","Float.MAX_VALUE");

			        }
			        else if ("Float".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Float.MIN_VALUE");
			            parmSet.put("safeValue", "0");
			            parmSet.put("maxValue", "Float.MAX_VALUE");
			            parmSet.put("assignableMinValue", "new Float(Float.MIN_VALUE)");
			            parmSet.put("assignableSafeValue","new Float(0)");
			            parmSet.put("assignableMaxValue", "new Float(Float.MAX_VALUE)");
			            parmSet.put("assignableLowBoundaryValue", "new Float(Float.MIN_VALUE)");
			            parmSet.put("assignableHighBoundaryValue", "new Float(Float.MAX_VALUE)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Float(Float.MIN_VALUE)");
			            parmSet.put("assignableHighBeyondBoundaryValue", "new Float(Float.MAX_VALUE)");
			            parmSet.put("assignableDestructiveValue", "null");

			        }
			        else if ("double".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Double.MIN_VALUE");
			            parmSet.put("safeValue", "0");
			            parmSet.put("maxValue", "Double.MAX_VALUE");
			            parmSet.put("assignableMinValue", "Double.MIN_VALUE");
			            parmSet.put("assignableSafeValue","0");
			            parmSet.put("assignableMaxValue","Double.MAX_VALUE");
			            parmSet.put("assignableLowBoundaryValue", "Double.MIN_VALUE");
			            parmSet.put("assignableHighBoundaryValue","Double.MAX_VALUE");
			            parmSet.put("assignableLowBeyondBoundaryValue", "Double.MIN_VALUE");
			            parmSet.put("assignableHighBeyondBoundaryValue","Double.MAX_VALUE");
			            parmSet.put("assignableDestructiveValue","Double.MAX_VALUE");

			        }
			        else if ("Double".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Double.MIN_VALUE");
			            parmSet.put("safeValue", "0");
			            parmSet.put("maxValue", "Double.MAX_VALUE");
			            parmSet.put("assignableMinValue", "new Double(Double.MIN_VALUE)");
			            parmSet.put("assignableSafeValue","new Double(0)");
			            parmSet.put("assignableMaxValue", "new Double(Double.MAX_VALUE)");
			            parmSet.put("assignableLowBoundaryValue", "new Double(Double.MIN_VALUE)");
			            parmSet.put("assignableHighBoundaryValue", "new Double(Double.MAX_VALUE)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Double(Double.MIN_VALUE)");
			            parmSet.put("assignableHighBeyondBoundaryValue", "new Double(Double.MAX_VALUE)");
			            parmSet.put("assignableDestructiveValue", "null");

			        }
			        else if ("char".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Character.MIN_VALUE");
			            parmSet.put("safeValue", "32");
			            parmSet.put("maxValue", "Character.MAX_VALUE");
			            parmSet.put("assignableMinValue", "Character");
			            parmSet.put("assignableSafeValue","32");
			            parmSet.put("assignableMaxValue","Character.MAX_VALUE");
			            parmSet.put("assignableLowBoundaryValue", "Character.MIN_VALUE");
			            parmSet.put("assignableHighBoundaryValue","Character.MAX_VALUE");
			            parmSet.put("assignableLowBeyondBoundaryValue", "Character.MIN_VALUE");
			            parmSet.put("assignableHighBeyondBoundaryValue","Character.MAX_VALUE");
			            parmSet.put("assignableDestructiveValue","Character.MAX_VALUE");

			        }
			        else if ("Character".equals(fields[i].type().typeName()))
			        {
			            parmSet.put("minValue", "Character.MIN_VALUE");
			            parmSet.put("safeValue", "32");
			            parmSet.put("maxValue", "Character.MAX_VALUE");
			            parmSet.put("assignableMinValue", "new Character(Character.MIN_VALUE)");
			            parmSet.put("assignableSafeValue","new Character(32)");
			            parmSet.put("assignableMaxValue", "new Character(Character.MAX_VALUE)");
			            parmSet.put("assignableLowBoundaryValue", "new Character(Character.MIN_VALUE)");
			            parmSet.put("assignableHighBoundaryValue", "new Character(Character.MAX_VALUE)");
			            parmSet.put("assignableLowBeyondBoundaryValue", "new Character(Character.MIN_VALUE)");
			            parmSet.put("assignableHighBeyondBoundaryValue", "new Character(Character.MAX_VALUE)");
			            parmSet.put("assignableDestructiveValue", "null");

			        }
			else
			{
			        parmSet.put("minValue", "");
			        parmSet.put("maxValue", "");
			        parmSet.put("safeValue", "");
			        parmSet.put("assignableMinValue", "new " + fields[i].type().qualifiedTypeName() + "()" );
			        parmSet.put("assignableMaxValue", "new " + fields[i].type().qualifiedTypeName() + "()" );
			        parmSet.put("assignableSafeValue", "new " + fields[i].type().qualifiedTypeName() + "()" );
			        parmSet.put("assignableLowBoundaryValue", "new " + fields[i].type().qualifiedTypeName() + "()" );
			        parmSet.put("assignableHighBoundaryValue", "new " + fields[i].type().qualifiedTypeName() + "()" );
			        parmSet.put("assignableLowBeyondBoundaryValue", "new " + fields[i].type().qualifiedTypeName() + "()" );
			        parmSet.put("assignableHighBeyondBoundaryValue", "new " + fields[i].type().qualifiedTypeName() + "()" );
			        parmSet.put("assignableDestructiveValue", "null" );

			}


E 5
			// Add the context to the stack
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.push(parmSet);
E 4
I 4
			this.m_ContextStack.push(parmSet);
E 4
D 5
			
E 5
I 5

E 5
			// Expand the current template
D 5
			
E 5
I 5

E 5
D 4
			this.engine.expandTemplate(templateBfr);
E 4
I 4
			this.m_Engine.expandTemplate(node);
E 4
D 5
			
E 5
I 5

E 5
			// Pop the stack.
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.pop();
E 4
I 4
			this.m_ContextStack.pop();
E 4
		}
D 5
		
E 5
I 5

E 5
D 4
		this.currentField = null;
E 4
I 4
		this.m_CurrentField = null;
E 4
	}


D 5
	
E 5
I 5

E 5
D 4
	public void axn_method(String templateBfr)
		throws InvalidArgumentException, IncorrectNestingException
E 4
I 4
	public void axn_method(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
E 4
	{

		//
		// Preconditions
		//

D 4
		if (templateBfr == null)
E 4
I 4
		if (node == null)
E 4
		{
D 8
			throw new InvalidArgumentException("<method> tag was given a null template bufer",null);
E 8
I 8
			throw new InvalidTagException("<method> tag was given a null template bufer",null);
E 8
		}

D 5
		
E 5
I 5

E 5
D 4
		if (this.currentClass == null)
E 4
I 4
		if (this.m_CurrentClass == null)
E 4
		{
			throw new IncorrectNestingException("<method> tag was invoked out of context",null);
		}

D 5
		System.err.println("CommonTagLibrary::axn_method called");

E 5
D 4
		MethodDoc[] methods = this.currentClass.methods();
E 4
I 4
		MethodDoc[] methods = this.m_CurrentClass.methods();
E 4

		//
		// Expand as necessary
		//
D 5
		
E 5
I 5

E 5
		if (methods == null)
		{
			return;
		}

		//
		// For each method in the current class
		//
D 5
		
E 5
I 5

E 5
		for (int i = 0; i < methods.length; i++)
		{
			//
D 5
			// Set the current method			// 
			
E 5
I 5
			// Set the current method			//

E 5
D 4
			this.currentMethod = methods[i];
E 4
I 4
			this.m_CurrentMethod = methods[i];
E 4
D 5
			
E 5
I 5

E 5
			//
			// Create the context for the calls
			//
D 5
			
E 5
I 5

E 5
			Hashtable parmSet = new Hashtable();
D 5
			
E 5
I 5

E 5
			//
			// Put the approriate data elements into the context
			//
D 5
			
E 5
I 5

			Integer ndx = new Integer(i);

			parmSet.put("ndx", ndx.toString());
E 5
			parmSet.put("name", methods[i].name());
D 5
									
E 5
I 5

E 5
			// Add the context to the stack
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.push(parmSet);
E 4
I 4
			this.m_ContextStack.push(parmSet);
E 4
D 5
			
E 5
I 5

E 5
			// Expand the current template
D 5
			
E 5
I 5

E 5
D 4
			this.engine.expandTemplate(templateBfr);
E 4
I 4
			this.m_Engine.expandTemplate(node);
E 4
D 5
			
E 5
I 5

E 5
			// Pop the stack.
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.pop();
E 4
I 4
			this.m_ContextStack.pop();
E 4
		}
D 5
		
E 5
I 5

E 5
D 4
		this.currentMethod = null;
E 4
I 4
		this.m_CurrentMethod = null;
E 4
	}


D 4
	public void axn_constructor(String templateBfr)
		throws InvalidArgumentException, IncorrectNestingException
E 4
I 4
D 5
	public void axn_constructor(Node node)
E 5
I 5
	public void axn_accessor(Node node)
E 5
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
E 4
	{

		//
		// Preconditions
		//

D 4
		if (templateBfr == null)
E 4
I 4
		if (node == null)
E 4
		{
D 5
			throw new InvalidArgumentException("<constructor> tag was given a null template bufer",null);
E 5
I 5
D 8
			throw new InvalidArgumentException("<accessor> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<accessor> tag was given a null template buffer",null);
E 8
E 5
		}

D 5
		
E 5
I 5

E 5
D 4
		if (this.currentClass == null)
E 4
I 4
		if (this.m_CurrentClass == null)
E 4
		{
D 5
			throw new IncorrectNestingException("<constructor> tag was invoked out of context",null);
E 5
I 5
			throw new IncorrectNestingException("<accessor> tag was invoked out of context",null);
E 5
		}

D 5
		System.err.println("CommonTagLibrary::axn_constructor called");

D 4
		ConstructorDoc[] constructors = this.currentClass.constructors();
E 4
I 4
		ConstructorDoc[] constructors = this.m_CurrentClass.constructors();
E 5
I 5
		MethodDoc[] methods = this.m_CurrentClass.methods();
E 5
E 4

		//
		// Expand as necessary
		//
D 5
		
		if (constructors == null)
E 5
I 5

		if (methods == null)
E 5
		{
			return;
		}

		//
D 5
		// For each constructor in the current class
E 5
I 5
		// For each public method (that is an accessor) in the current class
E 5
		//
D 5
		
		for (int i = 0; i < constructors.length; i++)
E 5
I 5

		for (int i = 0; i < methods.length; i++)
E 5
		{
			//
D 5
			// Set the current constructor			// 
			
D 4
			this.currentConstructor = constructors[i];
E 4
I 4
			this.m_CurrentConstructor = constructors[i];
E 4
			
			//
			// Create the context for the calls
			//
			
			Hashtable parmSet = new Hashtable();
			
			//
			// Put the approriate data elements into the context
E 5
I 5
			// Is it an accessor ?
E 5
			//
D 5
			
			parmSet.put("name", constructors[i].name());
						
			// Add the context to the stack
			
D 4
			this.contextStack.push(parmSet);
E 4
I 4
			this.m_ContextStack.push(parmSet);
E 4
			
			// Expand the current template
			
D 4
			this.engine.expandTemplate(templateBfr);
E 4
I 4
			this.m_Engine.expandTemplate(node);
E 4
			
			// Pop the stack.
			
D 4
			this.contextStack.pop();
E 4
I 4
			this.m_ContextStack.pop();
E 5
I 5

			if ((methods[i].name().startsWith("get") == true) && (methods[i].isPublic()))
			{
				//
				// Set the current method
				//

				this.m_CurrentMethod = methods[i];

				//
				// Create the context for the calls
				//

				Hashtable parmSet = new Hashtable();

				//
				// Put the approriate data elements into the context
				//

				Integer ndx = new Integer(i);

				parmSet.put("ndx", ndx.toString());
				parmSet.put("name", methods[i].name());

				// Add the context to the stack

				this.m_ContextStack.push(parmSet);

				// Expand the current template

				this.m_Engine.expandTemplate(node);

				// Pop the stack.

				this.m_ContextStack.pop();
			}
E 5
E 4
		}
D 5
		
D 4
		this.currentConstructor = null;
E 4
I 4
		this.m_CurrentConstructor = null;
E 4
	}
E 5

I 5
		this.m_CurrentMethod = null;
	}
E 5

D 4
	public void axn_parameter(String templateBfr) 
		throws InvalidArgumentException, IncorrectNestingException
E 4
I 4
D 5
	public void axn_parameter(Node node)
E 5
I 5
	public void axn_mutator(Node node)
E 5
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
E 4
	{
I 5

E 5
		//
		// Preconditions
		//

D 4
		if (templateBfr == null)
E 4
I 4
		if (node == null)
E 4
		{
D 5
			throw new InvalidArgumentException("<parameter> tag was given a null template bufer",null);
E 5
I 5
D 8
			throw new InvalidArgumentException("<mutator> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<mutator> tag was given a null template buffer",null);
E 8
E 5
		}

D 5
		
D 4
		if ((this.currentConstructor == null) && (this.currentMethod == null))
E 4
I 4
		if ((this.m_CurrentConstructor == null) && (this.m_CurrentMethod == null))
E 5
I 5

		if (this.m_CurrentClass == null)
E 5
E 4
		{
D 5
			throw new IncorrectNestingException("<parameter> tag was invoked out of context",null);
E 5
I 5
			throw new IncorrectNestingException("<mutator> tag was invoked out of context",null);
E 5
		}
D 5
		
E 5
I 5

		MethodDoc[] methods = this.m_CurrentClass.methods();

E 5
		//
D 5
		// Get the parameters
E 5
I 5
		// Expand as necessary
E 5
		//
D 5
		
		Parameter[] parameters = null;
		
D 4
		if (this.currentMethod != null)
E 4
I 4
		if (this.m_CurrentMethod != null)
E 5
I 5

		if (methods == null)
E 5
E 4
		{
D 4
			parameters = this.currentMethod.parameters();
E 4
I 4
D 5
			parameters = this.m_CurrentMethod.parameters();
E 5
I 5
			return;
E 5
E 4
		}
D 4
		else if (this.currentConstructor != null)
E 4
I 4
D 5
		else if (this.m_CurrentConstructor != null)
E 5
I 5

		//
		// For each method (that is an accessor) in the current class
		//

		for (int i = 0; i < methods.length; i++)
E 5
E 4
		{
D 4
			parameters = this.currentConstructor.parameters();
E 4
I 4
D 5
			parameters = this.m_CurrentConstructor.parameters();
E 5
I 5
			//
			// Is it an accessor ?
			//

			if ((methods[i].name().startsWith("set") == true) && (methods[i].isPublic()))
			{
				//
				// Set the current method
				//

				this.m_CurrentMethod = methods[i];

				//
				// Create the context for the calls
				//

				Hashtable parmSet = new Hashtable();

				//
				// Put the approriate data elements into the context
				//

				Integer ndx = new Integer(i);

				parmSet.put("ndx", ndx.toString());
				parmSet.put("name", methods[i].name());

				// Add the context to the stack

				this.m_ContextStack.push(parmSet);

				// Expand the current template

				this.m_Engine.expandTemplate(node);

				// Pop the stack.

				this.m_ContextStack.pop();
			}
		}

		this.m_CurrentMethod = null;
	}


	/**
	 *	A keybehavior is any method that is not an accessor or a mutator
	 *
	 * @param   node
D 8
	 * @exception   InvalidArgumentException
E 8
I 8
	 * @exception   InvalidTagException
E 8
	 * @exception   IncorrectNestingException
	 */

	public void axn_keybehavior(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{

		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<keybehavior> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<keybehavior> tag was given a null template buffer",null);
E 8
		}


		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<keybehavior> tag was invoked out of context",null);
E 5
E 4
		}
D 5
		
E 5
I 5

		MethodDoc[] methods = this.m_CurrentClass.methods();

E 5
		//
		// Expand as necessary
		//
D 5
		
		if (parameters == null)
E 5
I 5

		if (methods == null)
E 5
		{
			return;
		}
D 5
		
E 5
I 5

E 5
		//
D 5
		// For each parameter in the current class
E 5
I 5
		// For each method (that is an keybehavior) in the current class
E 5
		//
D 5
		
		for (int i = 0; i < parameters.length; i++)
E 5
I 5

		for (int i = 0; i < methods.length; i++)
E 5
		{
			//
D 5
			// Set the current parameter			// 
			
D 4
			this.currentParameter = parameters[i];
E 4
I 4
			this.m_CurrentParameter = parameters[i];
E 4
			
E 5
I 5
			// Is it an accessor ?
			//

			if ( (methods[i].name().startsWith("get") == false) && (methods[i].name().startsWith("set") == false) && (methods[i].isPublic()))
			{
				//
				// Set the current method
				//

				this.m_CurrentMethod = methods[i];

E 5
			//
			// Create the context for the calls
			//
D 5
			
E 5
I 5

E 5
			Hashtable parmSet = new Hashtable();
D 5
			
E 5
I 5

E 5
			//
			// Put the approriate data elements into the context
			//
D 5
			
			parmSet.put("name", parameters[i].name());
			parmSet.put("typeName", parameters[i].type().typeName());
			parmSet.put("qualifiedTypeName", parameters[i].type().qualifiedTypeName());
			parmSet.put("fullTypeName", parameters[i].type().toString());
			parmSet.put("dimension", parameters[i].type().dimension());
						
			if (i != (parameters.length - 1))
			{
				parmSet.put("comma", ",");
			}
			else
			{
				parmSet.put("comma", "");
			}
			
E 5
I 5

				Integer ndx = new Integer(i);

				parmSet.put("ndx", ndx.toString());
			parmSet.put("name", methods[i].name());

E 5
			// Add the context to the stack
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.push(parmSet);
E 4
I 4
			this.m_ContextStack.push(parmSet);
E 4
D 5
			
E 5
I 5

E 5
			// Expand the current template
D 5
			
E 5
I 5

E 5
D 4
			this.engine.expandTemplate(templateBfr);
E 4
I 4
			this.m_Engine.expandTemplate(node);
E 4
D 5
			
E 5
I 5

E 5
			// Pop the stack.
D 5
			
E 5
I 5

E 5
D 4
			this.contextStack.pop();
E 4
I 4
			this.m_ContextStack.pop();
E 4
		}
D 5
		
D 4
		this.currentParameter = null;		
E 4
I 4
		this.m_CurrentParameter = null;
E 5
I 5

		this.m_CurrentMethod = null;
E 5
E 4
	}
I 5
    }
E 5


D 4
	public void axn_return(String templateBfr)
		throws InvalidArgumentException, IncorrectNestingException
E 4
I 4
D 5
	public void axn_return(Node node)
E 5
I 5
	public void axn_constructor(Node node)
E 5
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
E 4
	{
I 5

E 5
		//
		// Preconditions
		//

D 4
		if (templateBfr == null)
E 4
I 4
		if (node == null)
E 4
		{
D 5
			throw new InvalidArgumentException("<return> tag was given a null template bufer",null);
E 5
I 5
D 8
			throw new InvalidArgumentException("<constructor> tag was given a null template bufer",null);
E 8
I 8
			throw new InvalidTagException("<constructor> tag was given a null template bufer",null);
E 8
E 5
		}

D 5
		
D 4
		if (this.currentMethod == null)
E 4
I 4
		if (this.m_CurrentMethod == null)
E 5
I 5

		if (this.m_CurrentClass == null)
E 5
E 4
		{
D 5
			throw new IncorrectNestingException("<return> tag was invoked out of context",null);
E 5
I 5
			throw new IncorrectNestingException("<constructor> tag was invoked out of context",null);
E 5
		}

D 5
	
E 5
I 5
		ConstructorDoc[] constructors = this.m_CurrentClass.constructors();

		//
		// Expand as necessary
		//

		if (constructors == null)
		{
			return;
		}

		//
		// For each constructor in the current class
		//

		for (int i = 0; i < constructors.length; i++)
		{
			//
			// Set the current constructor			//

			this.m_CurrentConstructor = constructors[i];

			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Put the approriate data elements into the context
			//

			Integer ndx = new Integer(i);

			parmSet.put("ndx", ndx.toString());
			parmSet.put("name", constructors[i].name());

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

D 8
			// Expand the current template
E 8
I 8
   			// Expand the current template
E 8

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentConstructor = null;
	}


	public void axn_parameter(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<parameter> tag was given a null template bufer",null);
E 8
I 8
			throw new InvalidTagException("<parameter> tag was given a null template bufer",null);
E 8
		}


		if ((this.m_CurrentConstructor == null) && (this.m_CurrentMethod == null))
		{
			throw new IncorrectNestingException("<parameter> tag was invoked out of context",null);
		}

		//
		// Get the parameters
		//

		Parameter[] parameters = null;

		if (this.m_CurrentMethod != null)
		{
			parameters = this.m_CurrentMethod.parameters();
		}
		else if (this.m_CurrentConstructor != null)
		{
			parameters = this.m_CurrentConstructor.parameters();
		}

		//
		// Expand as necessary
		//

		if (parameters == null)
		{
			return;
		}

		//
		// For each parameter in the current class
		//

		for (int i = 0; i < parameters.length; i++)
		{
			//
			// Set the current parameter			//

			this.m_CurrentParameter = parameters[i];

			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Put the approriate data elements into the context
			//

			Integer ndx = new Integer(i);

			parmSet.put("ndx", ndx.toString());
			parmSet.put("name", parameters[i].name());
			parmSet.put("typeName", parameters[i].type().typeName());
			parmSet.put("qualifiedTypeName", parameters[i].type().qualifiedTypeName());
			parmSet.put("fullTypeName", parameters[i].type().toString());
			parmSet.put("dimension", parameters[i].type().dimension());

			// Populate the comma as necessary

			if (i != (parameters.length - 1))
			{
				parmSet.put("comma", ",");
			}
			else
			{
				parmSet.put("comma", "");
			}

			// Add basic values for data types to the context

			if ("String".equals(parameters[i].type().typeName()))
			{
				parmSet.put("minValue", "MINVALUE");
				parmSet.put("maxValue", "MAXVALUE");
				parmSet.put("safeValue", "SAFEVALUE");
				parmSet.put("assignableMinValue", " new String(\"MINVALUE\")");
				parmSet.put("assignableMaxValue", " new String(\"MAXVALUE\")");
				parmSet.put("assignableSafeValue", " new String(\"SAFEVALUE\")");
				parmSet.put("assignableLowBoundaryValue", " new String(\"LOWBOUNDARYVALUE\")");
				parmSet.put("assignableHighBoundaryValue", " new String(\"HIGHBOUNDARYVALUE\")");
				parmSet.put("assignableLowBeyondBoundaryValue", " new String(\"LOWBEYONDBOUNDARYVALUE\")");
				parmSet.put("assignableHighBeyondBoundaryValue", " new String(\"HIGHBEYONDBOUNDARYVALUE\")");
				parmSet.put("assignableDestructiveValue", "null");

			}
                    else if ("boolean".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "true");
                        parmSet.put("safeValue", "true");
                        parmSet.put("maxValue", "false");
                        parmSet.put("assignableMinValue", "true");
                        parmSet.put("assignableSafeValue","true");
                        parmSet.put("assignableMaxValue","false");
                        parmSet.put("assignableLowBoundaryValue", "true");
                        parmSet.put("assignableHighBoundaryValue","false");
                        parmSet.put("assignableLowBeyondBoundaryValue", "true");
                        parmSet.put("assignableHighBeyondBoundaryValue","false");
                        parmSet.put("assignableDestructiveValue","false");

                    }
                    else if ("Boolean".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "true");
                        parmSet.put("safeValue", "true");
                        parmSet.put("maxValue", "false");
                        parmSet.put("assignableMinValue", "new Boolean(true)");
                        parmSet.put("assignableSafeValue","new Boolean(true)");
                        parmSet.put("assignableMaxValue","new Boolean(false)");
                        parmSet.put("assignableLowBoundaryValue", "new Boolean(true)");
                        parmSet.put("assignableHighBoundaryValue","new Boolean(false)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Boolean(true)");
                        parmSet.put("assignableHighBeyondBoundaryValue","new Boolean(false)");
                        parmSet.put("assignableDestructiveValue","null");

                    }
                    else if ("byte".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Byte.MIN_VALUE");
                        parmSet.put("safeValue", "0");
                        parmSet.put("maxValue", "Byte.MAX_VALUE");
                        parmSet.put("assignableMinValue", "Byte.MIN_VALUE");
                        parmSet.put("assignableSafeValue","0");
                        parmSet.put("assignableMaxValue","Byte.MAX_VALUE");
                        parmSet.put("assignableLowBoundaryValue", "Byte.MIN_VALUE");
                        parmSet.put("assignableHighBoundaryValue","Byte.MAX_VALUE");
                        parmSet.put("assignableLowBeyondBoundaryValue", "Byte.MIN_VALUE");
                        parmSet.put("assignableHighBeyondBoundaryValue","Byte.MAX_VALUE");
                        parmSet.put("assignableDestructiveValue","Byte.MAX_VALUE");

                    }
                    else if ("Byte".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Byte.MIN_VALUE");
                        parmSet.put("safeValue", "0");
                        parmSet.put("maxValue", "Byte.MAX_VALUE");
                        parmSet.put("assignableMinValue", "new Byte(Byte.MIN_VALUE)");
                        parmSet.put("assignableSafeValue","new Byte(0)");
                        parmSet.put("assignableMaxValue", "new Byte(Byte.MAX_VALUE)");
                        parmSet.put("assignableLowBoundaryValue", "new Byte(Byte.MIN_VALUE)");
                        parmSet.put("assignableHighBoundaryValue", "new Byte(Byte.MAX_VALUE)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Byte(Byte.MIN_VALUE)");
                        parmSet.put("assignableHighBeyondBoundaryValue", "new Byte(Byte.MAX_VALUE)");
                        parmSet.put("assignableDestructiveValue", "null");

                    }
                    else if ("short".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Short.MIN_VALUE");
                        parmSet.put("safeValue", "1");
                        parmSet.put("maxValue", "Short.MAX_VALUE");
                        parmSet.put("assignableMinValue", "Short");
                        parmSet.put("assignableSafeValue","1");
                        parmSet.put("assignableMaxValue","Short.MAX_VALUE");
                        parmSet.put("assignableLowBoundaryValue", "Short.MIN_VALUE");
                        parmSet.put("assignableHighBoundaryValue","Short.MAX_VALUE");
                        parmSet.put("assignableLowBeyondBoundaryValue", "Short");
                        parmSet.put("assignableHighBeyondBoundaryValue","Short.MAX_VALUE");
                        parmSet.put("assignableDestructiveValue","Short.MAX_VALUE");

                    }
                    else if ("Short".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Short.MIN_VALUE");
                        parmSet.put("safeValue", "1");
                        parmSet.put("maxValue", "Short.MAX_VALUE");
                        parmSet.put("assignableMinValue", "new Short(Short.MIN_VALUE)");
                        parmSet.put("assignableSafeValue","new Short(1)");
                        parmSet.put("assignableMaxValue", "new Short(Short.MAX_VALUE)");
                        parmSet.put("assignableLowBoundaryValue", "new Short(Short.MIN_VALUE)");
                        parmSet.put("assignableHighBoundaryValue", "new Short(Short.MAX_VALUE)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Short(Short.MIN_VALUE)");
                        parmSet.put("assignableHighBeyondBoundaryValue", "new Short(Short.MAX_VALUE)");
                        parmSet.put("assignableDestructiveValue", "null");

                    }
                    else if ("int".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Integer.MIN_VALUE");
                        parmSet.put("safeValue", "1");
                        parmSet.put("maxValue", "Integer.MAX_VALUE");
                        parmSet.put("assignableMinValue", "Integer");
                        parmSet.put("assignableSafeValue","1");
                        parmSet.put("assignableMaxValue","Integer.MAX_VALUE");
                        parmSet.put("assignableLowBoundaryValue", "Integer.MIN_VALUE");
                        parmSet.put("assignableHighBoundaryValue","Integer.MAX_VALUE");
                        parmSet.put("assignableLowBeyondBoundaryValue", "Integer.MIN_VALUE");
                        parmSet.put("assignableHighBeyondBoundaryValue","Integer.MAX_VALUE");
                        parmSet.put("assignableDestructiveValue","Integer.MAX_VALUE");

                    }
                    else if ("Integer".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Integer.MIN_VALUE");
                        parmSet.put("safeValue", "1");
                        parmSet.put("maxValue", "Integer.MAX_VALUE");
                        parmSet.put("assignableMinValue", "new Integer(Integer.MIN_VALUE)");
                        parmSet.put("assignableSafeValue","new Integer(1)");
                        parmSet.put("assignableMaxValue", "new Integer(Integer.MAX_VALUE)");
                        parmSet.put("assignableLowBoundaryValue", "new Integer(Integer.MIN_VALUE)");
                        parmSet.put("assignableHighBoundaryValue", "new Integer(Integer.MAX_VALUE)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Integer(Integer.MIN_VALUE)");
                        parmSet.put("assignableHighBeyondBoundaryValue", "new Integer(Integer.MAX_VALUE)");
                        parmSet.put("assignableDestructiveValue", "null");

                    }
                    else if ("long".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Long.MIN_VALUE");
                        parmSet.put("safeValue", "1");
                        parmSet.put("maxValue", "Long.MAX_VALUE");
                        parmSet.put("assignableMinValue", "Long");
                        parmSet.put("assignableSafeValue","0");
                        parmSet.put("assignableMaxValue","Long.MAX_VALUE");
                        parmSet.put("assignableLowBoundaryValue", "Long.MIN_VALUE");
                        parmSet.put("assignableHighBoundaryValue","Long.MAX_VALUE");
                        parmSet.put("assignableLowBeyondBoundaryValue", "Long.MIN_VALUE");
                        parmSet.put("assignableHighBeyondBoundaryValue","Long.MAX_VALUE");
                        parmSet.put("assignableDestructiveValue","Long.MAX_VALUE");

                    }
                    else if ("Long".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Long.MIN_VALUE");
                        parmSet.put("safeValue", "1");
                        parmSet.put("maxValue", "Long.MAX_VALUE");
                        parmSet.put("assignableMinValue", "new Long(Long.MIN_VALUE)");
                        parmSet.put("assignableSafeValue","new Long(0)");
                        parmSet.put("assignableMaxValue", "new Long(Long.MAX_VALUE)");
                        parmSet.put("assignableLowBoundaryValue", "new Long(Long.MIN_VALUE)");
                        parmSet.put("assignableHighBoundaryValue", "new Long(Long.MAX_VALUE)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Long(Long.MIN_VALUE)");
                        parmSet.put("assignableHighBeyondBoundaryValue", "new Long(Long.MAX_VALUE)");
                        parmSet.put("assignableDestructiveValue", "null");

                    }
                    else if ("float".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Float.MIN_VALUE");
                        parmSet.put("safeValue", "1");
                        parmSet.put("maxValue", "Float.MAX_VALUE");
                        parmSet.put("assignableMinValue", "Float.MIN_VALUE");
                        parmSet.put("assignableSafeValue","0");
                        parmSet.put("assignableMaxValue","Float.MAX_VALUE");
                        parmSet.put("assignableLowBoundaryValue", "Float.MIN_VALUE");
                        parmSet.put("assignableHighBoundaryValue","Float.MAX_VALUE");
                        parmSet.put("assignableLowBeyondBoundaryValue", "Float.MIN_VALUE");
                        parmSet.put("assignableHighBeyondBoundaryValue","Float.MAX_VALUE");
                        parmSet.put("assignableDestructiveValue","Float.MAX_VALUE");

                    }
                    else if ("Float".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Float.MIN_VALUE");
                        parmSet.put("safeValue", "0");
                        parmSet.put("maxValue", "Float.MAX_VALUE");
                        parmSet.put("assignableMinValue", "new Float(Float.MIN_VALUE)");
                        parmSet.put("assignableSafeValue","new Float(0)");
                        parmSet.put("assignableMaxValue", "new Float(Float.MAX_VALUE)");
                        parmSet.put("assignableLowBoundaryValue", "new Float(Float.MIN_VALUE)");
                        parmSet.put("assignableHighBoundaryValue", "new Float(Float.MAX_VALUE)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Float(Float.MIN_VALUE)");
                        parmSet.put("assignableHighBeyondBoundaryValue", "new Float(Float.MAX_VALUE)");
                        parmSet.put("assignableDestructiveValue", "null");

                    }
                    else if ("double".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Double.MIN_VALUE");
                        parmSet.put("safeValue", "0");
                        parmSet.put("maxValue", "Double.MAX_VALUE");
                        parmSet.put("assignableMinValue", "Double.MIN_VALUE");
                        parmSet.put("assignableSafeValue","0");
                        parmSet.put("assignableMaxValue","Double.MAX_VALUE");
                        parmSet.put("assignableLowBoundaryValue", "Double.MIN_VALUE");
                        parmSet.put("assignableHighBoundaryValue","Double.MAX_VALUE");
                        parmSet.put("assignableLowBeyondBoundaryValue", "Double.MIN_VALUE");
                        parmSet.put("assignableHighBeyondBoundaryValue","Double.MAX_VALUE");
                        parmSet.put("assignableDestructiveValue","Double.MAX_VALUE");

                    }
                    else if ("Double".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Double.MIN_VALUE");
                        parmSet.put("safeValue", "0");
                        parmSet.put("maxValue", "Double.MAX_VALUE");
                        parmSet.put("assignableMinValue", "new Double(Double.MIN_VALUE)");
                        parmSet.put("assignableSafeValue","new Double(0)");
                        parmSet.put("assignableMaxValue", "new Double(Double.MAX_VALUE)");
                        parmSet.put("assignableLowBoundaryValue", "new Double(Double.MIN_VALUE)");
                        parmSet.put("assignableHighBoundaryValue", "new Double(Double.MAX_VALUE)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Double(Double.MIN_VALUE)");
                        parmSet.put("assignableHighBeyondBoundaryValue", "new Double(Double.MAX_VALUE)");
                        parmSet.put("assignableDestructiveValue", "null");

                    }
                    else if ("char".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Character.MIN_VALUE");
                        parmSet.put("safeValue", "32");
                        parmSet.put("maxValue", "Character.MAX_VALUE");
                        parmSet.put("assignableMinValue", "Character");
                        parmSet.put("assignableSafeValue","32");
                        parmSet.put("assignableMaxValue","Character.MAX_VALUE");
                        parmSet.put("assignableLowBoundaryValue", "Character.MIN_VALUE");
                        parmSet.put("assignableHighBoundaryValue","Character.MAX_VALUE");
                        parmSet.put("assignableLowBeyondBoundaryValue", "Character.MIN_VALUE");
                        parmSet.put("assignableHighBeyondBoundaryValue","Character.MAX_VALUE");
                        parmSet.put("assignableDestructiveValue","Character.MAX_VALUE");

                    }
                    else if ("Character".equals(parameters[i].type().typeName()))
                    {
                        parmSet.put("minValue", "Character.MIN_VALUE");
                        parmSet.put("safeValue", "32");
                        parmSet.put("maxValue", "Character.MAX_VALUE");
                        parmSet.put("assignableMinValue", "new Character(Character.MIN_VALUE)");
                        parmSet.put("assignableSafeValue","new Character(32)");
                        parmSet.put("assignableMaxValue", "new Character(Character.MAX_VALUE)");
                        parmSet.put("assignableLowBoundaryValue", "new Character(Character.MIN_VALUE)");
                        parmSet.put("assignableHighBoundaryValue", "new Character(Character.MAX_VALUE)");
                        parmSet.put("assignableLowBeyondBoundaryValue", "new Character(Character.MIN_VALUE)");
                        parmSet.put("assignableHighBeyondBoundaryValue", "new Character(Character.MAX_VALUE)");
                        parmSet.put("assignableDestructiveValue", "null");

                    }
            else
            {
                    parmSet.put("minValue", "");
                    parmSet.put("maxValue", "");
                    parmSet.put("safeValue", "");
                    parmSet.put("assignableMinValue", "new " + parameters[i].type().qualifiedTypeName() + "()" );
                    parmSet.put("assignableMaxValue", "new " + parameters[i].type().qualifiedTypeName() + "()" );
                    parmSet.put("assignableSafeValue", "new " + parameters[i].type().qualifiedTypeName() + "()" );
                    parmSet.put("assignableLowBoundaryValue", "new " + parameters[i].type().qualifiedTypeName() + "()" );
                    parmSet.put("assignableHighBoundaryValue", "new " + parameters[i].type().qualifiedTypeName() + "()" );
                    parmSet.put("assignableLowBeyondBoundaryValue", "new " + parameters[i].type().qualifiedTypeName() + "()" );
                    parmSet.put("assignableHighBeyondBoundaryValue", "new " + parameters[i].type().qualifiedTypeName() + "()" );
                    parmSet.put("assignableDestructiveValue", "null" );

            }

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentParameter = null;
	}

	public void axn_exception(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<exception> tag was given a null template bufer",null);
E 8
I 8
			throw new InvalidTagException("<exception> tag was given a null template bufer",null);
E 8
		}


		if ((this.m_CurrentConstructor == null) && (this.m_CurrentMethod == null))
		{
			throw new IncorrectNestingException("<exception> tag was invoked out of context",null);
		}

		//
		// Get the exceptions
		//

		ClassDoc[] exceptions = null;

		if (this.m_CurrentMethod != null)
		{
			exceptions = this.m_CurrentMethod.thrownExceptions();
		}
		else if (this.m_CurrentConstructor != null)
		{
			exceptions = this.m_CurrentConstructor.thrownExceptions();
		}

		//
		// Expand as necessary
		//

		if (exceptions == null)
		{
			return;
		}

		//
		// For each parameter in the current class
		//

		for (int i = 0; i < exceptions.length; i++)
		{
			//
			// Set the current parameter			//

			this.m_CurrentException = exceptions[i];

			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Put the approriate data elements into the context
			//

			Integer ndx = new Integer(i);

			parmSet.put("ndx", ndx.toString());
			parmSet.put("name", this.m_CurrentException.name());
			parmSet.put("typeName", this.m_CurrentException.typeName());
			parmSet.put("qualifiedTypeName", this.m_CurrentException.qualifiedTypeName());

			// Populate the comma as necessary

			if (i != (exceptions.length - 1))
			{
				parmSet.put("comma", ",");
			}
			else
			{
				parmSet.put("comma", "");
			}

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentException = null;
	}



	public void axn_uniqueException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<hasUniqueException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasUniqueException> tag was invoked out of context",null);
		}

		//
		// Get the exceptions
		//

		MethodDoc[] methods = this.m_CurrentClass.methods();
		ConstructorDoc[] constructors = this.m_CurrentClass.constructors();

		//
		// Expand as necessary
		//

		if ((methods == null) && (constructors == null))
		{
			System.err.println("methods = null && constructors == null");
			return;
		}

		Hashtable	uniqueExceptions = new Hashtable();

		//
		// For each method in the current class
		//

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			if ((methods[i].isPublic()) && (methods[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = methods[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}


		//
		// For each constructor in the current class
		//

		for (int i = 0; i < constructors.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			System.err.println("method = " + constructors[i].name());
			System.err.println("isPublic = " + constructors[i].isPublic());
			System.err.println("thrownExceptions = " + constructors[i].thrownExceptions());

			if ((constructors[i].isPublic()) && (constructors[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = constructors[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}


		// There are no exceptions

		if (uniqueExceptions.size() == 0)
		{
			return;
		}

		// Now invoke for each unique exception

		Enumeration exceptionEnumerator = uniqueExceptions.elements();

		while (exceptionEnumerator.hasMoreElements())
		{
			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Set the current parameter
			//

			this.m_CurrentUniqueException = (ClassDoc) exceptionEnumerator.nextElement();

			//
			// Put the approriate data elements into the context
			//

			parmSet.put("name", this.m_CurrentUniqueException.name());
			parmSet.put("typeName", this.m_CurrentUniqueException.typeName());
			parmSet.put("qualifiedTypeName", this.m_CurrentUniqueException.qualifiedTypeName());

			// Populate the comma as necessary

			if (exceptionEnumerator.hasMoreElements())
			{
				parmSet.put("comma", ",");
			}
			else
			{
				parmSet.put("comma", "");
			}

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentUniqueException = null;
	}

	public void axn_uniquePropertyException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<hasUniqueException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasUniqueException> tag was invoked out of context",null);
		}

		//
		// Get the exceptions
		//

		MethodDoc[] methods = this.m_CurrentClass.methods();

		//
		// Expand as necessary
		//

		if (methods == null)
		{
			System.err.println("methods = null");
			return;
		}

		Hashtable	uniqueExceptions = new Hashtable();

		//
		// For each method in the current class
		//

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			System.err.println("method = " + methods[i].name());
			System.err.println("isPublic = " + methods[i].isPublic());
			System.err.println("thrownExceptions = " + methods[i].thrownExceptions());

			if ((methods[i].isPublic()) &&  ((methods[i].name().startsWith("set") == true) || (methods[i].name().startsWith("get") == true)) && (methods[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = methods[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}


		// There are no exceptions

		if (uniqueExceptions.size() == 0)
		{
			return;
		}

		// Now invoke for each unique exception

		Enumeration exceptionEnumerator = uniqueExceptions.elements();

		while (exceptionEnumerator.hasMoreElements())
		{
			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Set the current parameter
			//

			this.m_CurrentUniqueException = (ClassDoc) exceptionEnumerator.nextElement();

			//
			// Put the approriate data elements into the context
			//

			parmSet.put("name", this.m_CurrentUniqueException.name());
			parmSet.put("typeName", this.m_CurrentUniqueException.typeName());
			parmSet.put("qualifiedTypeName", this.m_CurrentUniqueException.qualifiedTypeName());

			// Populate the comma as necessary

			if (exceptionEnumerator.hasMoreElements())
			{
				parmSet.put("comma", ",");
			}
			else
			{
				parmSet.put("comma", "");
			}

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentParameter = null;
	}



	public void axn_uniqueConstructorException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<uniqueConstructorException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<uniqueConstructorException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<uniqueConstructorException> tag was invoked out of context",null);
		}

		//
		// Get the exceptions
		//

		ConstructorDoc[] constructors = this.m_CurrentClass.constructors();

		//
		// Expand as necessary
		//

		if (constructors == null)
		{
			System.err.println("constructors == null");
			return;
		}

		Hashtable	uniqueExceptions = new Hashtable();


		//
		// For each constructor in the current class
		//

		for (int i = 0; i < constructors.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			System.err.println("method = " + constructors[i].name());
			System.err.println("isPublic = " + constructors[i].isPublic());
			System.err.println("thrownExceptions = " + constructors[i].thrownExceptions());

			if ((constructors[i].isPublic()) && (constructors[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = constructors[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}


		// There are no exceptions

		if (uniqueExceptions.size() == 0)
		{
			return;
		}

		// Now invoke for each unique exception

		Enumeration exceptionEnumerator = uniqueExceptions.elements();

		while (exceptionEnumerator.hasMoreElements())
		{
			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Set the current parameter
			//

			this.m_CurrentUniqueException = (ClassDoc) exceptionEnumerator.nextElement();

			//
			// Put the approriate data elements into the context
			//

			parmSet.put("name", this.m_CurrentUniqueException.name());
			parmSet.put("typeName", this.m_CurrentUniqueException.typeName());
			parmSet.put("qualifiedTypeName", this.m_CurrentUniqueException.qualifiedTypeName());

			// Populate the comma as necessary

			if (exceptionEnumerator.hasMoreElements())
			{
				parmSet.put("comma", ",");
			}
			else
			{
				parmSet.put("comma", "");
			}

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentUniqueException = null;
	}



	public void axn_return(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<return> tag was given a null template bufer",null);
E 8
I 8
			throw new InvalidTagException("<return> tag was given a null template bufer",null);
E 8
		}


		if (this.m_CurrentMethod == null)
		{
			throw new IncorrectNestingException("<return> tag was invoked out of context",null);
		}


		//
		// Get the returns
		//

		Type returnType = null;

		returnType = this.m_CurrentMethod.returnType();

		//
		// Verify that we have something to expand
		//

		if (returnType == null)
		{
			return;
		}

		//
		// Create the context for the calls
		//

		Hashtable parmSet = new Hashtable();

		//
		// Put the approriate data elements into the context
		//

		parmSet.put("name", returnType.typeName());
		parmSet.put("typeName", returnType.typeName());
		parmSet.put("qualifiedTypeName", returnType.qualifiedTypeName());
		parmSet.put("fullTypeName", returnType.toString());
		parmSet.put("dimension", returnType.dimension());

		// Add the context to the stack

		this.m_ContextStack.push(parmSet);

		// Expand the current template

		this.m_Engine.expandTemplate(node);

		// Pop the stack.

		this.m_ContextStack.pop();
	}



	public void axn_isSerializable(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<isSerializable> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<isSerializable> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<isSerializable> tag was invoked out of context",null);
		}

		//
		// Expand as necessary
		//

		if (this.m_CurrentClass.isSerializable() == false)
		{
			return;
		}

		// Expand the current template

		this.m_Engine.expandTemplate(node);
	}


	public void axn_hasReturn(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<isSerializable> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<isSerializable> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<isSerializable> tag was invoked out of context",null);
		}

		//
		// Expand as necessary
		//

		if (this.m_CurrentMethod.returnType() == null)
		{
			return;
		}

		if (this.m_CurrentMethod.returnType().typeName().equals("void"))
		{
			return;
		}

		// Expand the current template

		this.m_Engine.expandTemplate(node);
	}

	public void axn_hasUniqueException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<hasUniqueException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasUniqueException> tag was invoked out of context",null);
		}

		//
		// Expand as necessary
		//

		MethodDoc[] methods = this.m_CurrentClass.methods();
		ConstructorDoc[] constructors = this.m_CurrentClass.constructors();

		//
		// Expand as necessary
		//

		if ((methods == null) && (constructors == null))
		{
			System.err.println("methods = null && constructors == null");
			return;
		}

		Hashtable	uniqueExceptions = new Hashtable();

		//
		// For each method in the current class
		//

		System.err.println("methods length = " + methods.length);

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			System.err.println("method = " + methods[i].name());
			System.err.println("isPublic = " + methods[i].isPublic());
			System.err.println("thrownExceptions = " + methods[i].thrownExceptions());

			if ((methods[i].isPublic()) && (methods[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = methods[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}

		//
		// For each constructor in the current class
		//

		for (int i = 0; i < constructors.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			System.err.println("method = " + constructors[i].name());
			System.err.println("isPublic = " + constructors[i].isPublic());
			System.err.println("thrownExceptions = " + constructors[i].thrownExceptions());

			if ((constructors[i].isPublic()) && (constructors[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = constructors[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}

		// There are no exceptions

		if (uniqueExceptions.size() == 0)
		{
			System.err.println("uniqueExceptions.size() == 0");
			return;
		}

		// Expand the current template

		this.m_Engine.expandTemplate(node);
	}


	public void axn_hasUniquePropertyException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
E 5
		//
D 5
		// Get the returns
E 5
I 5
		// Preconditions
E 5
		//
D 5
		
		Type returnType = null;
		
D 4
		returnType = this.currentMethod.returnType();
E 4
I 4
		returnType = this.m_CurrentMethod.returnType();
E 5
I 5

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<hasUniquePropertyException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<hasUniquePropertyException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasUniquePropertyException> tag was invoked out of context",null);
		}
E 5
E 4

		//
D 5
		// Verify that we have something to expand
E 5
I 5
		// Expand as necessary
E 5
		//
D 5
	
		if (returnType == null)
E 5
I 5

		MethodDoc[] methods = this.m_CurrentClass.methods();

		//
		// Expand as necessary
		//

		if (methods == null)
E 5
		{
I 5
			System.err.println("methods = null");
E 5
			return;
		}
D 5
		
E 5
I 5

		Hashtable	uniqueExceptions = new Hashtable();

E 5
		//
D 5
		// Create the context for the calls
E 5
I 5
		// For each method in the current class
E 5
		//
D 5
		
		Hashtable parmSet = new Hashtable();
			
E 5
I 5

		System.err.println("methods length = " + methods.length);

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			System.err.println("method = " + methods[i].name());
			System.err.println("isPublic = " + methods[i].isPublic());
			System.err.println("thrownExceptions = " + methods[i].thrownExceptions());

			if ((methods[i].isPublic()) &&  ((methods[i].name().startsWith("set") == true) || (methods[i].name().startsWith("get") == true)) && (methods[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = methods[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}

		// There are no exceptions

		if (uniqueExceptions.size() == 0)
		{
			System.err.println("uniqueExceptions.size() == 0");
			return;
		}

		// Expand the current template

		this.m_Engine.expandTemplate(node);
	}

	public void axn_hasUniqueKeyBehaviorException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
E 5
		//
D 5
		// Put the approriate data elements into the context
E 5
I 5
		// Preconditions
E 5
		//
D 5
		
		parmSet.put("name", returnType.typeName());		
		parmSet.put("typeName", returnType.typeName());
		parmSet.put("qualifiedTypeName", returnType.qualifiedTypeName());
		parmSet.put("fullTypeName", returnType.toString());
		parmSet.put("dimension", returnType.dimension());
						
		// Add the context to the stack
		
D 4
		this.contextStack.push(parmSet);
E 4
I 4
		this.m_ContextStack.push(parmSet);
E 4
		
E 5
I 5

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<hasUniqueKeyBehaviorException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<hasUniqueKeyBehaviorException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasUniqueKeyBehaviorException> tag was invoked out of context",null);
		}

		//
		// Expand as necessary
		//

		MethodDoc[] methods = this.m_CurrentClass.methods();

		//
		// Expand as necessary
		//

		if (methods == null)
		{
			System.err.println("methods = null");
			return;
		}

		Hashtable	uniqueExceptions = new Hashtable();

		//
		// For each method in the current class
		//

		System.err.println("methods length = " + methods.length);

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			System.err.println("method = " + methods[i].name());
			System.err.println("isPublic = " + methods[i].isPublic());
			System.err.println("thrownExceptions = " + methods[i].thrownExceptions());

			if ((methods[i].isPublic()) &&  (methods[i].name().startsWith("set") == false) && (methods[i].name().startsWith("get") == true) && (methods[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = methods[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}

		// There are no exceptions

		if (uniqueExceptions.size() == 0)
		{
			System.err.println("uniqueExceptions.size() == 0");
			return;
		}

E 5
		// Expand the current template
D 5
		
E 5
I 5

		this.m_Engine.expandTemplate(node);
	}



	public void axn_hasUniqueConstructorException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<hasUniqueException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasUniqueException> tag was invoked out of context",null);
		}

		//
		// Expand as necessary
		//

		ConstructorDoc[] constructors = this.m_CurrentClass.constructors();

		//
		// Expand as necessary
		//

		if (constructors == null)
		{
			System.err.println("constructors == null");
			return;
		}

		Hashtable	uniqueExceptions = new Hashtable();

		//
		// For each constructor  in the current class
		//


		for (int i = 0; i < constructors.length; i++)
		{
			//
			// Put the thrown exceptions into a table
			//

			// For each method

			if ((constructors[i].isPublic()) && (constructors[i].thrownExceptions() != null ) )
			{
				ClassDoc[] exceptions = constructors[i].thrownExceptions();

				// for each exception

				for (int j = 0; j < exceptions.length; j++)
				{
					System.err.println("	exception = " + exceptions[j].name());

					// Add it to the set of exceptions

					if (uniqueExceptions.containsKey(exceptions[j].qualifiedTypeName()) == false)
					{
						uniqueExceptions.put(exceptions[j].qualifiedTypeName(), exceptions[j]);
					}
				}

			}
		}

		// There are no exceptions

		if (uniqueExceptions.size() == 0)
		{
			System.err.println("uniqueExceptions.size() == 0");
			return;
		}

		// Expand the current template

		this.m_Engine.expandTemplate(node);
	}


	public void axn_hasException(Node node)
D 8
		throws InvalidArgumentException, IncorrectNestingException, NoSuchMethodException
E 8
I 8
		throws ExpansionException
E 8
	{
		//
		// Preconditions
		//

		if (node == null)
		{
D 8
			throw new InvalidArgumentException("<hasException> tag was given a null template buffer",null);
E 8
I 8
			throw new InvalidTagException("<hasException> tag was given a null template buffer",null);
E 8
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasException> tag was invoked out of context",null);
		}

		if (this.m_CurrentMethod == null && this.m_CurrentConstructor == null )
		{
			throw new IncorrectNestingException("<hasException> tag was invoked out of context",null);
		}

		//
		// Expand as necessary
		//

		if (this.m_CurrentMethod != null )
		{
			if (this.m_CurrentMethod.thrownExceptions() == null)
			{
				return;
			}
		}

		if (this.m_CurrentConstructor != null )
		{
			if (this.m_CurrentConstructor.thrownExceptions() == null)
			{
				return;
			}
		}

		// Expand the current template

E 5
D 4
		this.engine.expandTemplate(templateBfr);
E 4
I 4
		this.m_Engine.expandTemplate(node);
E 4
D 5
		
		// Pop the stack.
		
D 4
		this.contextStack.pop();
E 4
I 4
		this.m_ContextStack.pop();
E 5
E 4
	}

/*
D 5
	public void axn_xxxx(String templateBfr)
E 5
I 5
	public void axn_xxxx(Node node)
E 5
	{
		XxxxDoc[] xxxxs = this.CurrentYyyy.xxxxs();

		//
		// For each xxxx in the current class
		//
D 5
		
E 5
I 5

E 5
		for (int i = 0; i < xxxxs.length; i++)
		{
			//
D 5
			// Set the current xxxx			// 
			
E 5
I 5
			// Set the current xxxx			//

E 5
			this.CurrentXxxx = xxxxs[i];
D 5
			
E 5
I 5

E 5
			//
			// Create the context for the calls
			//
D 5
			
E 5
I 5

E 5
			Hashtable parmSet = new Hashtable();
D 5
			
E 5
I 5

E 5
			//
			// Put the approriate data elements into the context
			//
D 5
			
E 5
I 5

E 5
			parmSet.put("name", xxxxs[i].name());
D 5
						
E 5
I 5

E 5
			// Add the context to the stack
D 5
			
			this.ContextStack.push(parmSet);
			
E 5
I 5

			this.m_ContextStack.push(parmSet);

E 5
			// Expand the current template
D 5
			
			this.Engine.expandTemplate(templateBfr);
			
E 5
I 5

			this.m_Engine.expandTemplate(node);

E 5
			// Pop the stack.
D 5
			
			this.ContextStack.pop();
E 5
I 5

			this.m_ContextStack.pop();
E 5
		}
D 5
		
		this.CurrentYyyy = null;		
E 5
I 5

		this.CurrentYyyy = null;
E 5
	}

*/

D 4
    private RootDoc 				rootDoc;
E 4
I 4
    private RootDoc 				m_RootDoc;
E 4
D 5
	
E 5
I 5

E 5
D 4
	private CodeGeneratorEngine		engine;
E 4
I 4
	private CodeGeneratorEngine		m_Engine;
E 4
D 5
	
E 5
I 5

E 5
D 4
	private ClassDoc 			currentClass; 		// Current  class
    private MethodDoc 			currentMethod; 		// Current method of the template class
    private ConstructorDoc		currentConstructor;   // Current constructor of the template class
    private FieldDoc 			currentField; 		// Current field of the template class.
    private Parameter 			currentParameter; 	// Current Parameter of the template method or constructor
E 4
I 4
	private ClassDoc 			m_CurrentClass; 		// Current  class
    private MethodDoc 			m_CurrentMethod; 		// Current method of the template class
    private ConstructorDoc		m_CurrentConstructor;   // Current constructor of the template class
    private FieldDoc 			m_CurrentField; 		// Current field of the template class.
    private Parameter 			m_CurrentParameter; 	// Current Parameter of the template method or constructor
I 5
    private ClassDoc			m_CurrentException; 	// Current Parameter of the template method or constructor
	private ClassDoc			m_CurrentUniqueException; 	// Current Unique Exception
E 5
E 4

D 4
	private Hashtable			globalData;
E 4
I 4
	private Hashtable			m_GlobalData;
E 4
D 5
	
E 5
I 5

E 5
	/**
D 5
	 *	Used for storing the current data associated with a 
E 5
I 5
	 *	Used for storing the current data associated with a
E 5
	 *  given Class, method, constructor, etc.
	 */

D 4
	private Stack			 	contextStack;
E 4
I 4
	private Stack			 	m_ContextStack;
E 4
}
E 2
I 1
E 1
