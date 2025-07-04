/*
    $author: Jim Jackl-Mochel $
    $Revision: 1.20 $

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
import java.util.Hashtable;
import java.io.*;
import jacl.test.ClassProxy;

/**
    A self contained Unit Test for ClassProxy

    <p>This class forms a single executable class for exercising
    ClassProxy. Another class ClassProxyUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/02/12 18:50:25 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.20 $

    @see ClassProxy
    @see ClassProxyUnitTestMain
    @see <???>
    @since JDK 1.1
*/

public class ClassProxyUnitTest
{
    /**
        Void constructor that just initialises
    */

    public ClassProxyUnitTest()
    {
    }

    /**
        Give ClassProxy a vigorous shakedown.

        @see ClassProxy
        @see ClassProxyUnitTestMain
        @since JDK 1.1
    */

    public boolean Exercise()
    {

        boolean testWasSuccessful = true;
        Date    currDateTime = new Date();
        ClassProxy classToBeTested;
        
        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("ClassProxyUnitTest : $Revision: 1.20 $");
            System.out.println("ClassProxyUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data

            // Create a new ClassProxy and use it to excercise its class
            
            try 
            {
                // Instantiate the standard ranges config file parser
                
                StandardRangesConfigLexer    standardRangesConfigLexer = new StandardRangesConfigLexer(new FileInputStream("\\jacl\\java\\src\\testtools\\gauntlet\\GauntletStandardRanges.cfg"));
                StandardRangesConfigParser   standardRangesConfigParser = new StandardRangesConfigParser(standardRangesConfigLexer);
                
         	    Hashtable standardRanges = standardRangesConfigParser.configUnit();

                // Instantiate the config file pareser
            
                GauntletScriptLexer    configLexer = new GauntletScriptLexer(new FileInputStream("\\jacl\\java\\src\\testtools\\gauntlet\\TestClass.glt"));
                
                GauntletScriptParser    configParser = new GauntletScriptParser(configLexer);
    
            	GauntletScriptParserCfg inCfg = new GauntletScriptParserCfg();
            	
                ClassProxyCfg configBlock = configParser.compilationUnit(standardRanges, inCfg);

                ClassProxy  testClass = new ClassProxy(configBlock);
                
                testClass.executeAllShortTours(200);
            }
            catch(antlr.ParserException parserError)
            {
                System.err.println("Gauntlet could not parse the Standard Ranges Config file");
                System.err.println(parserError);
            }
            catch(java.io.IOException IOError)
            {
                System.err.println("Gauntlet could not open the Standard Ranges Config file");
                System.err.println(IOError);
            }
            catch(java.lang.IllegalAccessException illegalAccessError)
            {
                System.err.println("Gauntlet does not have access to the appropriate constructor");
                System.err.println("If you didn't supply a string via the \"/value=\" switch ");
                System.err.println("than Gauntlet couldn't access the void constructor. ");
                System.err.println("If you did supply a string, then it failed to access a ");
                System.err.println("string constructor.");
                System.err.println(illegalAccessError);
                throw ( new UnitTestException());
            }
            catch(java.lang.reflect.InvocationTargetException invocationTargetError)
            {
                System.err.println("Gauntlet the class to be tested threw an exception from its constructor");
                System.err.println(invocationTargetError);
                throw ( new UnitTestException());
            }
            catch(java.lang.InstantiationException instantiationError)
            {
                System.err.println("Gauntlet the class to be tested threw an exception from its constructor");
                System.err.println(instantiationError);
                throw ( new UnitTestException());
            }
            catch (java.lang.ClassNotFoundException classNotFoundError)
            {
                System.err.println("Gauntlet could not find the class to be tested");
                System.err.println(classNotFoundError);
                throw ( new UnitTestException());
            }
            catch (java.lang.NoSuchMethodException noSuchMethodError)
            {
                System.err.println("The constructor for the class to be tested was not found");
                System.err.println("If you didn't supply a string via the \"/value=\" switch ");
                System.err.println("than Gauntlet couldn't find the void constructor. ");
                System.err.println("If you did supply a string, then it failed to find a ");
                System.err.println("string constructor.");
                System.err.println(noSuchMethodError);
                
                throw ( new UnitTestException());
            }
            finally 
            {
            }
        }
        catch (jacl.test.UnitTestException unitTestError)
        {
            System.out.println("ClassProxyUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.
            System.out.println("ClassProxyUnitTest : $Revision: 1.20 $");
            System.out.println("ClassProxyUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }

}
