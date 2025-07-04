h07159
s 00101/00000/00000
d D 1.1 99/11/17 12:54:13 jmochel 2 1
cC
cK28273
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:09 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/StandardRangesConfigParserUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45357
cPjava/src/metamodel/StandardRangesConfigParserUnitTest.java
cR2f93d7095cb6ba86
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
    $copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/StandardRangesConfigParserUnitTest.java,v 1.2 1999/07/28 03:57:05 jmochel Exp $
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.reflect.Constructor;

import jacl.metamodel.StandardRangesConfigLexer;
import jacl.metamodel.StandardRangesConfigParser;

/**
    A self contained Unit Test for Parser

    <p>This class forms a single executable class for exercising
    Parser. Another class ParserUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see Parser
    @see ParserUnitTestMain
*/

public class StandardRangesConfigParserUnitTest
{
    /**
        Void constructor that just initialises
    */

    public StandardRangesConfigParserUnitTest()
    {
    }

    
       
    /**
        Give StandardRangesConfigParser a vigorous shakedown.

        @see ParmRange
        @see ParmRangeUnitTest
    */

    public static void main(String[] args)
    {
        Date    currDateTime = new Date();
        
        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("StandardRangesConfigParserUnitTest : $Revision: 1.2 $");
            System.out.println("StandardRangesConfigParserUnitTest  : Starting - " + currDateTime.toString());

            // Instantiate the config file pareser
            
            StandardRangesConfigLexer    configLexer = new StandardRangesConfigLexer(new FileInputStream("\\jacl\\java\\src\\testingtools\\gauntlet\\GauntletStandardRanges.cfg"));
            
            StandardRangesConfigParser    configParser = new StandardRangesConfigParser(configLexer);

            configParser.configUnit();
        }
        catch(java.io.IOException IOError)
        {
            System.out.println("StandardRangesConfigParserUnitTest could not open the config file");

            System.out.println(IOError);
        }
        catch(antlr.ParserException parserError)
        {
            System.out.println("StandardRangesConfigParserUnitTest could not parse the config file");

            System.out.println(parserError);
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            currDateTime = new Date();
            
            System.out.println("StandardRangesConfigParserUnitTest : $Revision: 1.2 $");
            System.out.println("StandardRangesConfigParserUnitTest : Ending Unit Test - " + currDateTime.toString());
 
            
        }
    }
}
E 2
I 1
E 1
