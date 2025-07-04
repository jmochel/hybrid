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

// Package Declaration

package jacl.test;

// Imports

import java.util.Date;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.reflect.Constructor;

import jacl.test.ClassProxyCfg;
import jacl.test.GauntletScriptLexer;
import jacl.test.GauntletScriptParser;

/**
    A self contained Unit Test for Parser

    <p>This class forms a single executable class for exercising
    Parser. Another class ParserUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

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
    <p>This code was last modified on $Date: 1999/02/12 18:50:27 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.8 $

    @see Parser
    @see ParserUnitTestMain
    @since JDK 1.1
*/

public class GauntletScriptParserUnitTest
{
    /**
        Void constructor that just initialises
    */

    public GauntletScriptParserUnitTest()
    {
    }

    
       
    /**
        Give GauntletScriptParser a vigorous shakedown.

        @see ParmRange
        @see ParmRangeUnitTest
        @since JDK 1.1

    */

    public static void main(String[] args)
    {
        Date    currDateTime = new Date();
        
        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("GauntletScriptParserUnitTest : $Revision: 1.8 $");
            System.out.println("GauntletScriptParserUnitTest  : Starting - " + currDateTime.toString());

        	            
            //
            //
            // Set up the default ranges for the standard types 
            //
            //

            // Instantiate the standard ranges config file parser
            
            StandardRangesConfigLexer    rangesConfigLexer = new StandardRangesConfigLexer(new FileInputStream("\\jacl\\java\\src\\testtools\\gauntlet\\GauntletStandardRanges.cfg"));
            StandardRangesConfigParser   rangesConfigParser = new StandardRangesConfigParser(rangesConfigLexer);

            Hashtable standardRanges = rangesConfigParser.configUnit();

            // Instantiate the config file pareser
            
            GauntletScriptLexer    cfgLexer = new GauntletScriptLexer(new FileInputStream("\\jacl\\java\\src\\testtools\\gauntlet\\TestClass.glt"));
            
            GauntletScriptParser    cfgParser = new GauntletScriptParser(cfgLexer);

        	GauntletScriptParserCfg    inCfg = new GauntletScriptParserCfg();
        	
            ClassProxyCfg cfgBlock = cfgParser.compilationUnit(standardRanges, inCfg);
        	
        }
        catch(java.io.IOException IOError)
        {
            System.out.println("GauntletScriptParserUnitTest could not open the config file");

            System.out.println(IOError);
        }
        catch(antlr.ParserException parserError)
        {
            System.out.println("GauntletScriptParserUnitTest could not parse the config file");

            System.out.println(parserError);
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            currDateTime = new Date();
            
            System.out.println("GauntletScriptParserUnitTest : $Revision: 1.8 $");
            System.out.println("GauntletScriptParserUnitTest : Ending Unit Test - " + currDateTime.toString());
 
            
        }
    }
}
