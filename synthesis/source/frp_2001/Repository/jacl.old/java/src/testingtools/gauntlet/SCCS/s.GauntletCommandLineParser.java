h38338
s 00172/00000/00000
d D 1.1 99/11/17 12:52:17 jmochel 2 1
cC
cK58662
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:52:13 jmochel 1 0
c BitKeeper file e:/jacl/java/src/testingtools/gauntlet/GauntletCommandLineParser.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45345
cPjava/src/testingtools/gauntlet/GauntletCommandLineParser.java
cR2f93d7e95cb6ba86
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

package jacl.test;

// Standard Imports

import java.util.*;
import java.io.File;
import java.lang.reflect.*;

// JACL Imports

import jacl.util.commandline.*;

// Application Imports

import jacl.test.GauntletCommandLineValidation;

/**
    GauntletCommandLineParser for a testing tool called Gauntlet

    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/02/12 18:50:26 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.8 $


    @see Parser    @since JDK 1.1
*/

public class GauntletCommandLineParser extends jacl.util.commandline.Parser
{
    
    /**
        Constructor

        <p>GauntletCommandLineParser has the following expectEd keys.

        @see Parser
        @since JDK 1.1
   */

    public GauntletCommandLineParser(String lexerClassName) throws IllegalArgumentException, LexerCreationException
    {
        super(lexerClassName, true, new GauntletCommandLineValidation());

        this.register(new Parser.ArgSemantic("class", "cl", "Class to be tested", "java.lang.String", false));
        this.register(new Parser.ArgSemantic("script", "sc", "Testing script file (typically .glt)", "java.io.File", false));
        this.register(new Parser.ArgSemantic("config", "cfg", "Configuration file for Standard Parameter Ranges", "java.io.File", false));
        this.register(new Parser.ArgSemantic("tour", "t", "Short tour number to be executed", "java.lang.Integer", false));
        this.register(new Parser.ArgSemantic("value", "val", "Constructor input for the class to be tested", "java.lang.String", false));
        this.register(new Parser.ArgSemantic("verbose", "v", "Verbose reporting", "java.lang.Boolean", false));
        this.register(new Parser.ArgSemantic("out", "o", "Output File for results", "java.io.File", false));
        this.register(new Parser.ArgSemantic("summary", "sum", "Summary output file", "java.io.File", false));
    }

    /**
        Constructor

        <p>IOFilterParser has the following expected keys.

        @see Parser
        @since JDK 1.1
   */

    public GauntletCommandLineParser() throws IllegalArgumentException, LexerCreationException
    {
        this(new String(""));
    }
    
    /**
        Gets the Testing Script File object
    */

    public File getTestingScript()
    {
        return( (File) getParsedArgument("script"));
    }
    

    /**
        Gets the Testing Script File object
    */

    public File getOutputFile()
    {
        return( (File) getParsedArgument("out"));
    }

    /**
        Gets the Summary Output File object
    */

    public File getSummaryFile()
    {
        return( (File) getParsedArgument("sumary"));
    }

    /**
        Gets the name of the class to be tested.
    */

    public String getClassName()
    {
        return((String) getParsedArgument("class"));
    }

    /**
        Gets the name of the class to be tested.
    */

    public String getValue()
    {
        return((String) getParsedArgument("value"));
    }

    /**
        Gets the tour number
    */

    public Integer getTour()
    {
        return((Integer) getParsedArgument("tour"));
    }

    /**
        Gets the name of the configuration file
    */

    public File getConfig()
    {
        return( (File) getParsedArgument("config"));
    }
    

    /**
        Gets the verbose flag
    */

    public Boolean getVerbose()
    {
        return((Boolean) getParsedArgument("verbose"));
    }
};
E 2
I 1
E 1
