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

import java.util.Stack;
import java.util.Hashtable;
import java.io.FileNotFoundException;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;
import org.w3c.dom.Node;

/**
 * The TagLibrary interface is given a series of nodes (XML DOM nodes) which it recursively expands
 * against the metainformation about the packages/classes being dealt (Doclet API rootDoc)
 *
 * @author Jim Jackl-Mochel
 * @version $version:$ 
 */
 
public interface ITagLibrary
{
    /**
     *  Initializes the tag Library with the appropriate root document and <code>CodeGeneratorEngine</code>
     */

    void init(RootDoc rootDoc, CodeGeneratorEngine engine) throws ExpansionException;

    /**
     *  Expands data elements of the type "<name/>" into their appropriate text
     */

	String expandDataElement(Node node) throws ExpansionException;

    /**
     *  Expands action elements of the type "<class></class>" into their appropriate text and further expansions
     */

    void   expandActionElement(Node node) throws ExpansionException;
}
