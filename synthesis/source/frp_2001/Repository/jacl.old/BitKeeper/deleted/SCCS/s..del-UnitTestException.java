h41218
s 00020/00030/00047
d D 1.2 00/09/12 08:51:15 jmochel 3 2
c Delete: java/src/testingtools/gauntlet/UnitTestException.java
cC
cHdevilmountain
cK57954
cPBitKeeper/deleted/.del-UnitTestException.java
cZ-04:00
e
s 00077/00000/00000
d D 1.1 99/11/17 12:52:58 jmochel 2 1
cC
cK41898
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:52:54 jmochel 1 0
c BitKeeper file e:/jacl/java/src/testingtools/gauntlet/UnitTestException.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45349
cPjava/src/testingtools/gauntlet/UnitTestException.java
cR2f93d71e5cb6ba86
cV3
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 0x21
t
T
I 2
/*
D 3
    Copyright (C) 1998-1999 Jim Jackl-Mochel
E 3
I 3
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
E 3

D 3
    Permission is hereby granted, free of charge, to any person obtaining a copy 
    of this software and associated documentation files (the "Software"), to deal in
    the Software without restriction, including without limitation the rights to use, 
    copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
    Software, and to permit persons to whom the Software is furnished to do so, 
    subject to the following conditions:

    The above copyright notice and this permission notice shall be included in 
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
    IMPLIED, INCLUDING BUT NOT LIMITED TOTHE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHOR BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
    ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
    WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

    Except as contained in this notice, the name of the author shall not be used 
    in advertising or otherwise to promote the sale, use or other dealings in this
    Software without specific prior written authorization.

    This code was last modified on $Date: 1999/02/12 18:50:29 $

    $author: Jim Jackl-Mochel $
    $Revision: 1.5 $
*/


package jacl.util;
E 3
I 3
package org.mushin.jacl.util;
E 3

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
E 2
I 1
E 1
