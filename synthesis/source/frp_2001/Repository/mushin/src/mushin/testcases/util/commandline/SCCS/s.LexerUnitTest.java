H64947
s 00253/00000/00000
d D 1.1 01/07/13 18:14:23 jmochel 2 1
cC
cF1
cK26914
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:23 jmochel 1 0
c BitKeeper file f:/Repository/mushin/src/mushin/testcases/util/commandline/LexerUnitTest.java
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK56691
cPsrc/mushin/testcases/util/commandline/LexerUnitTest.java
cR47f0226e
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

package mushin.testcases.util.commandline;

// Java Imports

import java.util.*;

// Jacl Imports

import mushin.util.commandline.*;
import mushin.util.commandline.Lexer;

/**
 *     A self contained Unit Test for Lexer
 * 
 *     <p>This class forms a single executable class for exercising
 *     Lexer. Another class LexerUnitTestMain actually
 *     invokes UnitTest. This simplifies creating automated unit test executors.
 * 
 *     <p><B>ToDo</B>
 *     <ul>
 *         <li>Check API Naming
 *         <li>Check API Types
 *         <li>Check API Returns
 *         <li>Check API Exceptions
 *         <li>Check API Exception Specifications
 *         <li>Check Comments
 *         <li>Remove unused Comment tags
 *     </ul>
 * 
 *     <p>This code was originally generated using TM version 34
 *     <p>This code was last modified on $Date: 1999/01/07 22:14:05 $
 * 
 *     @author Jim Jackl-Mochel
 *     @author $Author: jmochel $
 *     @version $Revision: 1.5 $
 * 
 *     @see <{Lexer}>
 *     @see LexerUnitTestMain
 *     @see <???>
 *     @since JDK 1.1
*/

public class LexerUnitTest
{
    /**
 *         Void constructor that just initialises
    */

    public LexerUnitTest()
    {
    }

    /**
 *         Give Lexer a vigorous shakedown.
 * 
 *         @see <{Lexer}>
 *         @see LexerUnitTestMain
 *         @since JDK 1.1
    */

    public boolean Exercise()
    {

        boolean     testWasSuccessful = true;
        Date        currDateTime = new Date();
        Hashtable   testDataSets = new Hashtable();

        Lexer         testLexer;
        String[][]    testDataSet;
        int           i;
        int           j;

        int           lexerCount;
        int           testDataSetCount;

        // Add detailed start up information
        // Who, what, when, why, and how.

        System.out.println("LexerUnitTest : $Revision: 1.5 $");
        System.out.println("LexerUnitTest : Starting Unit Test - " + currDateTime.toString());
        
        //
        // Initialise the test data
        //


        // Populate the name of the tests
        
        String[] lexerNames = 
        { 
            "mushin.util.commandline.SlashEqualsLexer", 
            "mushin.util.commandline.PosixStyleLexer"
        };

        // Populate the arrays of test command lines.
                    
        String[][] slashEqualsTestSet =
        {
            { "/in=infile.ext", "/out=outfile.ext", "/repeat=5", "/flog=true" },    // Good Test Strings
            { "/in=infile.ext", "out=outfile.ext", "/repeat=5", "/flog=true" },    // Malformed with missing '/'            
            { "/in=infile.ext", "/out=outfile.ext", "/repeat", "/flog=true" },     // Malformed with missing '='
            { "/i=infile.ext", "/o=outfile.ext", "/repeat=5", "/flog=true" }    // Good Test Strings
        };
            

        String[][] posixStyleTestSet =
        {   
            { "-in", "infile.ext", "-out", "outfile.ext", "-repeat","5","-flog" },    // Good Test Strings
            { "in", "infile.ext", "-out", "outfile.ext", "-repeat","5","-flog" },    // missing '-'
            { "-in", "infile.ext", "out", "outfile.ext", "-repeat","5","-flog" },    // missing '-'
            { "-i", "infile.ext", "-o", "outfile.ext", "-repeat","5","-flog" }    // Good Test Strings
        };    
        
        try
        {
            // Populate the testdataSets 
            
            testDataSets.put(lexerNames[0], slashEqualsTestSet);
            testDataSets.put(lexerNames[1], posixStyleTestSet);
        }
        finally {
        }

        // Populate the abbreviations table
        
        Hashtable abbrevs = new Hashtable(2);
        
        abbrevs.put("i", "in");
        abbrevs.put("o", "out");

        
        //
        // Run the tests in a loop.
        //
         
        lexerCount = lexerNames.length;
        testDataSetCount = testDataSets.size();
        
        // Verify we have the correct amount of data sets for the number of lexers

        if ( lexerCount != testDataSetCount )
        {
            System.out.println("LexerUnitTest : Unit Test has been misconfigured, there is a mismatch between ");
            System.out.println("LexerUnitTest : the number of lexers and the number of data sets to test them with");
            System.exit(1);
        }
        
        // Loop through the tests 

               
        for (i = 0; i < lexerCount; i++)
        {
            try 
            {
                // Now instantiate the lexer
                             
                testLexer = (Lexer) (Class.forName(lexerNames[i]).newInstance());

                testLexer.setAbbrevs(abbrevs);
                
                // Get the test data 

                System.out.println("");
                System.out.println("LexerUnitTest : Getting test data for lexer : " + lexerNames[i]);
                
                testDataSet = (String[][]) testDataSets.get(lexerNames[i]);
                            
                // Test the test lexer 
    
                System.out.println("LexerUnitTest : Testing lexer : " + lexerNames[i]);
    
                for (j = 0; j < testDataSet.length; j++ )
                {
                    System.out.println("LexerUnitTest : Lexer test Data Set : " + j );
                    
                    try 
                    {
                        testLexer.lex(testDataSet[j]);
                    }
                    catch (IllegalArgumentException argError)
                    {
                        System.out.println("LexerUnitTest : Lexer threw an exception");
                        System.out.println(argError);
                    }
                    finally 
                    {
                        System.out.println("LexerUnitTest : Printing out the first key value pairs");
                        
                        Enumeration    iter = testLexer.getIter();
            
                        while ( iter.hasMoreElements() == true )
                        {
                            System.out.println(iter.nextElement());
                        }
                    }
                }                    
            }
            catch(ClassNotFoundException classNotFoundError)
            {
                System.out.println("LexerUnitTest : Unable to find lexer class " + lexerNames[i]);
                System.exit(1);
            }
            catch (IllegalAccessException illegalAccessError)
            {
                System.out.println("LexerUnitTest : Found the test lexer class, but I don't have permission to load it " + lexerNames[i]);
                System.exit(1);
            }
            catch (InstantiationException istantiationError)
            {
                System.out.println("LexerUnitTest : Found the test lexer class, but I can't instantiate it " + lexerNames[i]);
                System.exit(1);
            }
            
        }

        // Add detailed ending information
        // Who, what, when, why, and how.

        System.out.println("LexerUnitTest : $Revision: 1.5 $");
        System.out.println("LexerUnitTest : Ending Unit Test - " + currDateTime.toString());
        
        return(testWasSuccessful);
    }

}
E 2
I 1
E 1
