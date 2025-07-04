/*
    $copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/TestableModelUnitTest.java,v 1.2 1999/07/28 03:58:14 jmochel Exp $
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import jacl.util.UnitTestException;


/**
    A self contained Unit Test for TestableModel

    <p>This class forms a single executable class for exercising
    TestableModel. Another class TestableModelUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see TestableModel
    @see TestableModelUnitTestMain
*/

public class TestableModelUnitTest
{
    /**
        Void constructor that just initialises
    */

    public TestableModelUnitTest()
    {
    }

    /**
        Give TestableModel a vigorous shakedown.

        @see TestableModel
        @see TestableModelUnitTestMain
    */

    public boolean Exercise()
    {

        boolean testWasSuccessful = true;
        Date    currDateTime = new Date();

        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("TestableModelUnitTest : $Revision: 1.2 $");
            System.out.println("TestableModelUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data

         

            // Create the class using void constructor

            try 
            {
                TestableModel classToBeTested = new TestableModel("java.lang.Byte");

            }
            catch(ClassNotFoundException classNotFoundError)
            {
                throw new jacl.util.UnitTestException("Unable to find the class to be tested");
            }
            
            // Exercise the mutators



		
            // Exercise the accessors



            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("TestableModelUnitTest : $Revision: 1.2 $");
            System.out.println("TestableModelUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("TestableModelUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ParmProxyUnitTest : $Revision: 1.2 $");
            System.out.println("ParmProxyUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
