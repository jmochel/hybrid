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
 * The Original Code is "JACL - Just Another Class Library".
 * 
 * The Initial Developer of the Original Code is Jim Jackl-Mochel.  
 * Portions created by Jim Jackl-Mochel are
 * Copyright (C) 1998, 1999, 2000 Jim Jackl-Mochel.  All
 * Rights Reserved.
 * 
 * Contributor(s): 
 */

package org.mushin.jacl.util;

// Standard Imports

import java.lang.Exception;

// Application Imports

/**
    UnitTestException is a runtime exception that is thrown when a Unit Test Module fails.
    
    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/02/12 18:50:29 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.5 $

    @see Lexer
    @since JDK 1.1
*/

public class UnitTestException extends Exception
{
    
    /**
        Constructor 

        @since  JDK 1.1
    */

    public UnitTestException()
    {
    }
    
    /**
        Constructor 

        @since  JDK 1.1
    */

    public UnitTestException(String msg)
    {
        super(msg);
    }
}
