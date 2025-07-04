h20033
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
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 2
/*
    Copyright (C) 1998-1999 Jim Jackl-Mochel

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
