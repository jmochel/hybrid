/*
    $author: Jim Jackl-Mochel $
    $Revision: 1.3 $

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

/**
    Validation functor for Gauntlet Commandline parser

    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/02/12 18:50:26 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see Lexer
    @since JDK 1.1
*/

package jacl.test;

// Standard Imports 

import java.util.Hashtable;

import jacl.util.commandline.Validation;
import jacl.util.commandline.ParsingException;

public class GauntletCommandLineValidation implements Validation
{
    public void IsSemanticallyValid(Hashtable parsedArguments) throws ParsingException
    {
        try 
        {
            // Verify that we have a class name OR a testing script to work with.
            
            if ( (parsedArguments.get("class") == null ) && (parsedArguments.get("script") == null ) )
            {
                throw( new ParsingException(new String("Gauntlet requires a classname or a testing script in order to run")));
            }

            
        }
        finally 
        {
        }
    }
}
