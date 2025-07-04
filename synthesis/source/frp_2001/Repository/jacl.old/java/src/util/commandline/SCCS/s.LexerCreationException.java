h18858
s 00099/00000/00000
d D 1.1 99/11/17 12:51:23 jmochel 2 1
cC
cK40925
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:51:20 jmochel 1 0
c BitKeeper file e:/jacl/java/src/util/commandline/LexerCreationException.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45340
cPjava/src/util/commandline/LexerCreationException.java
cR2f93d7f85cb6ba86
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
    $author: Jim Jackl-Mochel $
    $Revision: 1.5 $

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
*/

package jacl.util.commandline;

// Standard Imports

import java.lang.Exception;

// Other Imports

// JACL Imports

import jacl.util.commandline.*;

// Application Imports

/**
    LexerCreationExecption is a runtime exception that is thrown when a CommandLineParser 
    is unable to create a Lexer for processing a commad line.
    
    <p><B>ToDo</B>
    <ul>
        <li>Check API Naming
        <li>Check API Types
        <li>Check API Returns
        <li>Check API Exceptions
        <li>Check API Exception Specifications
        <li>Check Comments
        <li>Remove unused Comment tags
        <li>Make sure that there is no way to create an obj with an invalid state
        <li>If an instance variable doen't have a natural default, have one passed into the constructor
        <li>Throw exceptions on bad input.
        <li>Constructors and initializers should only be about initialization
        <li>minimize coupling, take as input only data needed by the method.
    </ul>

    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/01/07 22:14:05 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.5 $

    @see Lexer
    @since JDK 1.1
*/

public class LexerCreationException extends Exception
{
    
    /**
        Constructor 

        @see    Lexer
        @since  JDK 1.1
    */

    public LexerCreationException()
    {
    }
    
    /**
        Constructor 

        @see    Lexer
        @since  JDK 1.1
    */

    public LexerCreationException(String msg)
    {
        super(msg);
    }
}
E 2
I 1
E 1
