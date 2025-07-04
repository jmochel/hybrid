H13175
s 00126/00000/00000
d D 1.1 01/07/13 18:14:24 jmochel 2 1
cC
cK42870
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:23 jmochel 1 0
c BitKeeper file f:/Repository/mushin/src/mushin/util/commandline/IOFilterParser.java
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK13386
cPsrc/mushin/util/commandline/IOFilterParser.java
cR480238fc
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
    Contains IOFilterParser

    $author: Jim Jackl-Mochel $
    $Revision: 1.8 $

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

package mushin.util.commandline;

// Standard Imports

import java.util.*;
import java.io.File;
import java.lang.reflect.*;

// Application Imports

/**
    IOFilterParser for Input/Output Filters

    <p>IOFilterParser is a parser for files that simply take an 
    input stream and output stream
    
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
    <p>This code was last modified on $Date: 1999/07/28 04:01:53 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.8 $


    @see IOFilterParser
    @since JDK 1.1
*/

public class IOFilterParser extends Parser
{
    
    /**
        Constructor

        <p>IOFilterParser has the following expectEd keys.

        @see Parser
        @since JDK 1.1
   */

    public IOFilterParser(String lexerClassName) throws IllegalArgumentException, LexerCreationException
    {
        super(lexerClassName, true);

        this.register(new Parser.ArgSemantic(new String("in"), new String("i"), new String("Input file name"), new String("java.io.File"), true));
        this.register(new Parser.ArgSemantic(new String("out"), new String("o"), new String("Output file name"), new String("java.io.File"), true));
    }

    /**
        Constructor

        <p>IOFilterParser has the following expected keys.

        @see Parser
        @since JDK 1.1
   */

    public IOFilterParser() throws IllegalArgumentException, LexerCreationException
    {
        this(new String(""));
    }
    
    /**
        Gets the input File object
    */

    public File getInFile()
    {
        return( (File) getParsedArgument("in"));
    }
    
    /**
        Gets the output File object
    */

    public File getOutFile()
    {
        return((File) getParsedArgument("out"));
    }
};
E 2
I 1
E 1
