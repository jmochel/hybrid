H39133
s 02682/00000/00000
d D 1.1 01/11/02 11:01:32 jmochel 2 1
cC
cF1
cK62521
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/11/02 11:01:32 jmochel 1 0
c BitKeeper file g:/TemplateCodeGenerator/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/TagLibraryBase.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHdevilmountain.corp.foliage.com
cK26154
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/TagLibraryBase.java
cR937e279a
cV4
cX0xb1
cZ-05:00
e
u
U
f e 0
f x 0xb1
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

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.Type;

import org.w3c.dom.Node;
import org.w3c.dom.DOMException;

/**
 *
 * @author Jim Jackl-Mochel
 * @version $version:$
 */

public class TagLibraryBase implements ITagLibrary
{
	public TagLibraryBase()
	{
    	this.m_RootDoc = null;
		this.m_Engine = null;

		this.m_ContextStack = new Stack();
		this.m_GlobalData = new Hashtable();
	}

    /**
     *  Initializes the Tag Library
     *  @see ITagLibrary#init
     */
    public void init(RootDoc rootDoc, CodeGeneratorEngine engine)
		throws ExpansionException
    {
	    //
	    // Preconditions
	    //

	    if (rootDoc == null)
	    {
	    	throw new InvalidTagException("Common Tag Library Init was given a null m_RootDoc",null);
	    }

	    if (engine == null)
	    {
	    	throw new InvalidTagException("Common Tag Library Init was given a null m_Engine",null);
	    }


		//
		// Init some data members
		//

		this.m_RootDoc = rootDoc;
		this.m_Engine = engine;

		//
		// Add some global data tags
		//

		// Add the current date

		Date currDate = new Date();

		String dateAsString = DateFormat.getDateInstance().format(currDate);
		String timeAsString = DateFormat.getTimeInstance().format(currDate);

		this.m_GlobalData.put("date", dateAsString);

		// Add the current time

		this.m_GlobalData.put("time", timeAsString);
    }

	public String expandDataElement(Node node)
		throws ExpansionException
	{
		String expandedTag = null;

		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("node was null",null);
		}

        // String data to be expanded.

        String name = node.getNodeName();

        return expandStringFromContext(name);
    }

    /**
     *  Walks through the existing contexts and expands the string when it finds a match...
     */

    private String expandStringFromContext(String name) throws InvalidTagException
    {
        String expandedTag;
        //
        // First check in the global variable table.
        //

        expandedTag = (String) this.m_GlobalData.get(name);

        if ( expandedTag != null )
        {
            return (expandedTag);
        }

        //
        // walk "down" the stack until we find a parameter of appropriate name
        // 	or until we run out of parm sets
        //

        int parmSetCount = this.m_ContextStack.size();

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

        return (expandedTag);
    }

    private String expandAttributeValueFromContext(String value) throws ExpansionException
    {
        // Create a pattern to match "{id}"

        Pattern ptrn = Pattern.compile("\\{[A-Za-z]+\\}");

        // Create a matcher with an input string

        Matcher m = ptrn.matcher(value);

        boolean matchesFound =  m.find();

        StringBuffer bfr = new StringBuffer();

        //
        // If there are any strings to be expanded ,
        // locate them and expand them from the context stack.
        //

        while (matchesFound)
        {
            String tmp = m.group();
            String symbol = tmp.substring(1,tmp.length()-1);

            // Find the symbol in the context stack

            m.appendReplacement(bfr, this.expandStringFromContext(symbol));

            // Go to the next match

            matchesFound = m.find();
        }

        m.appendTail(bfr);

        return(bfr.toString());
    }

    public void expandActionElement(Node node) throws ExpansionException
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

    /**
     * Basic code that is called before each action code that is called. It implements the standard attributes of the tags
     * Currently it only implements the redirect attribute.
     *
     */

    public void beginAction(Node node) throws ExpansionException
    {
        try
        {
            // Redirect the output

            Node attr;

            if ((attr = node.getAttributes().getNamedItem("redirect")) != null )
            {
                // Preprocess the contents of the attribute so that any data items get expanded.
                // So "{name}TestCase.java" gets expanded to "StdClassTestCase.java" before getting passed
                // on for the redirection.

                String expandedAttrValue;

                expandedAttrValue = expandAttributeValueFromContext(attr.getNodeValue());

                // Then pass on the expanded attribute to the output stack.

                this.m_Engine.addOutput(expandedAttrValue);
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

    /**
     * Basic code that is called after each action that is called. It implements the standard attributes of the tags
     * Currently it only implements the redirect attribute.
     *
     */

    public void endAction(Node node) throws ExpansionException
    {
        // Remove the redirect

        Node attr;

        if ((attr = node.getAttributes().getNamedItem("redirect")) != null )
        {
            this.m_Engine.removeOutput();
        }
    }

    /**
     * Implements a null or shill action. Used as a wrapper for getting access to attribute expansion from within
     * a action tag that is not yet evaluated...
     */

    public void axn_shill(Node node) throws ExpansionException
	{
        // Expand the current template

        this.m_Engine.expandTemplateChildren(node);
	}

	public void axn_class(Node node) throws ExpansionException
	{

		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<class> tag was given a null node",null);
		}

		ClassDoc[] classes = this.m_RootDoc.classes();

		//
		// Expand as necessary
		//

		if (classes == null)
		{
			return;
		}

		//
		// For each class in the root
		//

		for (int i = 0; i < classes.length; i++)
		{
			//
			// Set the current class
			//

			this.m_CurrentClass = classes[i];

			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Put the approriate data elements into the context
			//

			parmSet.put("name", classes[i].name());
			parmSet.put("qualifiedName", classes[i].qualifiedName());
			parmSet.put("qualifiedTypeName", classes[i].qualifiedTypeName());
			parmSet.put("package", classes[i].containingPackage().name());
			parmSet.put("superclass", classes[i].superclass().name());

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplateChildren(node);

			// Pop the stack.

			this.m_ContextStack.pop();

		}

		this.m_CurrentClass = null;
	}


	public void axn_field(Node node)
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<field> tag was given a null template buffer",null);
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<field> tag was invoked out of context",null);
		}

		FieldDoc[] fields = this.m_CurrentClass.fields();

		//
		// Expand as necessary
		//

		if (fields == null)
		{
			return;
		}


		//
		// For each field in the current class
		//

		for (int i = 0; i < fields.length; i++)
		{
			//
			// Set the current field			//

			this.m_CurrentField = fields[i];

			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Put the approriate data elements into the context
			//

			Integer ndx = new Integer(i);

			parmSet.put("ndx", ndx.toString());

			parmSet.put("name", fields[i].name());
			parmSet.put("typeName", fields[i].type().typeName());
			parmSet.put("qualifiedTypeName", fields[i].type().qualifiedTypeName());


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


			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentField = null;
	}



	public void axn_method(Node node)
		throws ExpansionException
	{

		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<method> tag was given a null template bufer",null);
		}


		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<method> tag was invoked out of context",null);
		}

		MethodDoc[] methods = this.m_CurrentClass.methods();

		//
		// Expand as necessary
		//

		if (methods == null)
		{
			return;
		}

		//
		// For each method in the current class
		//

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Set the current method			//

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

		this.m_CurrentMethod = null;
	}


	public void axn_accessor(Node node)
		throws ExpansionException
	{

		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<accessor> tag was given a null template buffer",null);
		}


		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<accessor> tag was invoked out of context",null);
		}

		MethodDoc[] methods = this.m_CurrentClass.methods();

		//
		// Expand as necessary
		//

		if (methods == null)
		{
			return;
		}

		//
		// For each public method (that is an accessor) in the current class
		//

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Is it an accessor ?
			//

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
		}

		this.m_CurrentMethod = null;
	}

	public void axn_mutator(Node node)
		throws ExpansionException
	{

		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<mutator> tag was given a null template buffer",null);
		}


		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<mutator> tag was invoked out of context",null);
		}

		MethodDoc[] methods = this.m_CurrentClass.methods();

		//
		// Expand as necessary
		//

		if (methods == null)
		{
			return;
		}

		//
		// For each method (that is an accessor) in the current class
		//

		for (int i = 0; i < methods.length; i++)
		{
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
	 * @exception   InvalidTagException
	 * @exception   IncorrectNestingException
	 */

	public void axn_keybehavior(Node node)
		throws ExpansionException
	{

		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<keybehavior> tag was given a null template buffer",null);
		}


		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<keybehavior> tag was invoked out of context",null);
		}

		MethodDoc[] methods = this.m_CurrentClass.methods();

		//
		// Expand as necessary
		//

		if (methods == null)
		{
			return;
		}

		//
		// For each method (that is an keybehavior) in the current class
		//

		for (int i = 0; i < methods.length; i++)
		{
			//
			// Is it an accessor ?
			//

			if ( (methods[i].name().startsWith("get") == false) && (methods[i].name().startsWith("set") == false) && (methods[i].isPublic()))
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

		this.m_CurrentMethod = null;
	}
    }


	public void axn_constructor(Node node)
		throws ExpansionException
	{

		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<constructor> tag was given a null template bufer",null);
		}


		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<constructor> tag was invoked out of context",null);
		}

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

   			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.m_CurrentConstructor = null;
	}


	public void axn_parameter(Node node)
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<parameter> tag was given a null template bufer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<exception> tag was given a null template bufer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<uniqueConstructorException> tag was given a null template buffer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<return> tag was given a null template bufer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<isSerializable> tag was given a null template buffer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<isSerializable> tag was given a null template buffer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<hasUniquePropertyException> tag was given a null template buffer",null);
		}

		if (this.m_CurrentClass == null)
		{
			throw new IncorrectNestingException("<hasUniquePropertyException> tag was invoked out of context",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<hasUniqueKeyBehaviorException> tag was given a null template buffer",null);
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

		// Expand the current template

		this.m_Engine.expandTemplate(node);
	}



	public void axn_hasUniqueConstructorException(Node node)
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<hasUniqueException> tag was given a null template buffer",null);
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
		throws ExpansionException
	{
		//
		// Preconditions
		//

		if (node == null)
		{
			throw new InvalidTagException("<hasException> tag was given a null template buffer",null);
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

		this.m_Engine.expandTemplate(node);
	}

/*
	public void axn_xxxx(Node node)
	{
		XxxxDoc[] xxxxs = this.CurrentYyyy.xxxxs();

		//
		// For each xxxx in the current class
		//

		for (int i = 0; i < xxxxs.length; i++)
		{
			//
			// Set the current xxxx			//

			this.CurrentXxxx = xxxxs[i];

			//
			// Create the context for the calls
			//

			Hashtable parmSet = new Hashtable();

			//
			// Put the approriate data elements into the context
			//

			parmSet.put("name", xxxxs[i].name());

			// Add the context to the stack

			this.m_ContextStack.push(parmSet);

			// Expand the current template

			this.m_Engine.expandTemplate(node);

			// Pop the stack.

			this.m_ContextStack.pop();
		}

		this.CurrentYyyy = null;
	}

*/

    private RootDoc 				m_RootDoc;

	private CodeGeneratorEngine		m_Engine;

	private ClassDoc 			m_CurrentClass; 		// Current  class
    private MethodDoc 			m_CurrentMethod; 		// Current method of the template class
    private ConstructorDoc		m_CurrentConstructor;   // Current constructor of the template class
    private FieldDoc 			m_CurrentField; 		// Current field of the template class.
    private Parameter 			m_CurrentParameter; 	// Current Parameter of the template method or constructor
    private ClassDoc			m_CurrentException; 	// Current Parameter of the template method or constructor
	private ClassDoc			m_CurrentUniqueException; 	// Current Unique Exception

	private Hashtable			m_GlobalData;

	/**
	 *	Used for storing the current data associated with a
	 *  given Class, method, constructor, etc.
	 */

	private Stack			 	m_ContextStack;
}
E 2
I 1
E 1
