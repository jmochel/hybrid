h29572
s 00135/00000/00000
d D 1.1 99/11/17 12:51:34 jmochel 2 1
cC
cK53056
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:51:30 jmochel 1 0
c BitKeeper file e:/jacl/java/src/util/commandline/PosixStyleLexer.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45341
cPjava/src/util/commandline/PosixStyleLexer.java
cR2f93d7f55cb6ba86
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
    Contains Posix Style Command Line Lexer class.

    Copyright : This code is in the public domain.

    This code was last modified on $Date: 1999/01/07 22:14:07 $

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

// System imports

import java.util.*;

/**
    A Posix Style command line lexical analyser

    <p>Takes in command line arguments of the form "-key value" or "-key" and lexes them into keys/values pair.
    The key-value pairs are passed on via the Enumeration interface.

    <p><B>ToDo</B>
    <ul>
        <li>Check API Naming
        <li>Check API Types
        <li>Check API Returns
        <li>Check API Exceptions
        <li>Check API Exception Specifications
        <li>Check Comments
        <li>Remove unused Comment tags
    </ul>

    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/01/07 22:14:07 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.5 $

    @see Parser
    @see LexerUnitTest
    @see LexerUnitTestMain
    @since JDK 1.1
*/

public class PosixStyleLexer extends Lexer
{
    /**
        Empty Constructor

        @see Lexer
        @since JDK 1.1
    */

    public PosixStyleLexer()
    {
       super(new String("Syntax: -key value or -key "));
    }
    
    /**
        Lexes the command line down into its component Key and Value pairs
    
        @exception java.lang.IllegalArgumentException        
        
        @see Lexer$Lex
        @since JDK 1.1
    */
     
    public void lex(String[] argValues) throws java.lang.IllegalArgumentException
    {
        int argCnt = argValues.length;
        
        String        arg;
        String        key;
        String        value;

        for ( int i = 0; i < argCnt; i++ )
        {
            arg = argValues[i];
            
            // If the argument doesn't starts with a '-'
            
            if ( arg.charAt(0) != '-' )
            {
                throw new IllegalArgumentException("This argument should start with '-' : ( " + arg + " )");
            }
            
            // Break it into a key and a value
            
            key = arg.substring(1,arg.length());
            
            // if there are no more arguments or the next argument has a  '-' in front of it
            // then we have to assume that this is a boolean type flag
            // we will enter a 1 as true and let the Parser worry about interpretation
            
            if ( ((i + 1) == argCnt) || ( ((argValues[i]).charAt(0) != '-')))
            {
                value = "1";
            }
            else { // We snatch the next argument as a value and advance the index.
            
                value = argValues[i+1];
                i++;
            }

            this.addKeyAndValue(key, value);
       }
    }
};
E 2
I 1
E 1
