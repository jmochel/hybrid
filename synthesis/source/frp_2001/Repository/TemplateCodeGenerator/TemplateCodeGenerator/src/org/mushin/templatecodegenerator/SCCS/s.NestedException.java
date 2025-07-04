H06152
s 00000/00000/00122
d D 1.2 01/05/29 12:33:56 jmochel 3 2
c mvdir
cC
cHdevilmountain.corp.foliage.com
cK03948
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/NestedException.java
cZ-04:00
e
s 00122/00000/00000
d D 1.1 01/03/14 22:46:14 jmochel 2 1
cC
cF1
cK16407
cO-rw-rw-r--
e
s 00000/00000/00000
d D 1.0 01/03/14 22:46:14 jmochel 1 0
c BitKeeper file i:/Repository/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/NestedException.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHmordanith.ne.mediaone.net
cK02393
cPsrc/org/mushin/templatecodegenerator/NestedException.java
cR8fcfdae9
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

import java.io.*;

/**
	Nested Exception Base class
	
 	<p>Allows user to 'nest' or 'chain' exception
 	within it. This allows the bean to forward 
	details of a failure to the user.

	@author Jim Jackl-Mochel
	@author $Author: Jmochel $
*/

public class NestedException extends Exception
{
    /** 
		Chained exception
    */
     
    private Exception nestedException = null;
    
    /** 
     	Description
    */
     
    private String 	desc = null;

    /**
    	Constructor 
    	
     	@param desc				Description of the cause of the exception
     	@param nestedException 	The exception to be nested
    */

    public NestedException(String desc, Exception nestedException)
    {
        super(desc);
		
        this.desc = desc;
        this.nestedException = nestedException;
    }


    /**
		Override of toString method 
		
		<p>Includes stacktrace of the nested exception
    */
	 
    public String toString()
    {
        StringBuffer desc = new StringBuffer();

        desc.append(super.toString());
        desc.append("\n");
        
        if (this.nestedException != null)
        {
            try
            {
                StringWriter sW = new StringWriter();
                PrintWriter trace = new PrintWriter(sW);
                this.nestedException.printStackTrace(trace);
                trace.flush();
                desc.append(sW.toString());
                trace.close();
            }
            catch (Exception error)
            {
                desc.append("No stack trace available");
            }
        }

	    return desc.toString();
	}

    /**
    	Return description
     
	 	@return Description of the cause of the exception
    */

    public String getDescription()
    {
        return this.desc;
    }

    /**
    	Get nested exception
		
		@return The nested exception (maybe null)
    */

    public Exception getNestedException()
    {
        return this.nestedException;
    }
}

E 2
I 1
E 1
