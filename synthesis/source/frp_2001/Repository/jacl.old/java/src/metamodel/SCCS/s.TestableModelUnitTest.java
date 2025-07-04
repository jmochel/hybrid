h58258
s 00112/00000/00000
d D 1.1 99/11/17 12:54:20 jmochel 2 1
cC
cK16492
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:17 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/TestableModelUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45358
cPjava/src/metamodel/TestableModelUnitTest.java
cR2f93d7075cb6ba86
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
E 2
I 1
E 1
