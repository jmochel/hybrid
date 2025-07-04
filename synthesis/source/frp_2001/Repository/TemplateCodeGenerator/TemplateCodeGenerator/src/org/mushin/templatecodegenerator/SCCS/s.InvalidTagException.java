H11333
s 00042/00000/00000
d D 1.1 01/10/03 15:59:53 jmochel 2 1
cC
cF1
cK33452
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/10/03 15:59:53 jmochel 1 0
c BitKeeper file g:/TemplateCodeGenerator/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/InvalidTagException.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHdevilmountain.corp.foliage.com
cK37058
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/InvalidTagException.java
cRb67f2aef
cV4
cX0xb1
cZ-04:00
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

import java.io.*;

public class InvalidTagException extends ExpansionException
{
    /**
    	Constructor 
    	
     	@param desc				Description of the cause of the exception
     	@param nestedException 	The exception to be nested
    */

    public InvalidTagException(String desc, Exception nestedException)
    {
        super(desc, nestedException);
    }


}

E 2
I 1
E 1
