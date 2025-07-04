H45592
s 00004/00004/00058
d D 1.5 01/11/02 11:01:32 jmochel 6 5
c Cleaned up some comments
cC
cK38116
cZ-05:00
e
s 00018/00004/00044
d D 1.4 01/10/03 15:59:53 jmochel 5 4
c Adding attribute name expansion
cC
cK36608
e
s 00006/00002/00042
d D 1.3 01/09/14 15:19:29 jmochel 4 3
c Added the redirect feature and extended exception handling
cC
cK57546
e
s 00000/00000/00044
d D 1.2 01/05/29 12:33:56 jmochel 3 2
c mvdir
cC
cHdevilmountain.corp.foliage.com
cK40615
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/ITagLibrary.java
cZ-04:00
e
s 00044/00000/00000
d D 1.1 01/03/14 22:45:57 jmochel 2 1
cC
cF1
cK47054
cO-rw-rw-r--
e
s 00000/00000/00000
d D 1.0 01/03/14 22:45:57 jmochel 1 0
c BitKeeper file i:/Repository/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/ITagLibrary.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHmordanith.ne.mediaone.net
cK24759
cPsrc/org/mushin/templatecodegenerator/ITagLibrary.java
cR8eaf3a8d
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

import java.util.Stack;
import java.util.Hashtable;
I 4
import java.io.FileNotFoundException;
E 4

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;
I 4
import org.w3c.dom.Node;
E 4

/**
D 5
 * 
E 5
I 5
 * The TagLibrary interface is given a series of nodes (XML DOM nodes) which it recursively expands
 * against the metainformation about the packages/classes being dealt (Doclet API rootDoc)
 *
E 5
 * @author Jim Jackl-Mochel
 * @version $version:$ 
 */
 
public interface ITagLibrary
{
I 5
    /**
     *  Initializes the tag Library with the appropriate root document and <code>CodeGeneratorEngine</code>
     */

E 5
D 4
    void init(RootDoc rootDoc, CodeGeneratorEngine engine) throws InvalidArgumentException;
E 4
I 4
    void init(RootDoc rootDoc, CodeGeneratorEngine engine) throws ExpansionException;
E 4
D 5
	
D 4
	String invokeDataTag(DataTag tag) throws InvalidArgumentException;
E 4
I 4
	String invokeDataTag(DataTag tag) throws ExpansionException;
E 5

D 5
    void   invokeActionTag(Node node) throws ExpansionException;
E 5
I 5
    /**
D 6
     *  Expands data tags of the type "<name/>" into their appropriate text
E 6
I 6
     *  Expands data elements of the type "<name/>" into their appropriate text
E 6
     */

D 6
	String expandDataNode(Node node) throws ExpansionException;
E 6
I 6
	String expandDataElement(Node node) throws ExpansionException;
E 6

    /**
D 6
     *  Expands action tags of the type "<class></class>" into their appropriate text and further expansions
E 6
I 6
     *  Expands action elements of the type "<class></class>" into their appropriate text and further expansions
E 6
     */

D 6
    void   expandActionNode(Node node) throws ExpansionException;
E 6
I 6
    void   expandActionElement(Node node) throws ExpansionException;
E 6
E 5
E 4
}
E 2
I 1
E 1
