/*
    $copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/CtorUnitTest.java,v 1.2 1999/07/28 03:46:39 jmochel Exp $
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import jacl.util.UnitTestException;
import jacl.metamodel.Ctor;
import java.lang.reflect.Constructor;

/**
    A self contained Unit Test for Ctor

    <p>This class forms a single executable class for exercising
    Ctor. Another class CtorUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see Ctor
    @see CtorUnitTestMain
    @see <???>
*/

public class CtorUnitTest
{
    /**
        Void constructor that just initialises
    */

    public CtorUnitTest()
    {
    }

    /**
        Give Ctor a vigorous shakedown.

        @see Ctor
        @see CtorUnitTestMain
        @since JDK 1.1
    */

    public boolean Exercise()
    {

        boolean testWasSuccessful = true;
        Date    currDateTime = new Date();

        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("CtorUnitTest : $Revision: 1.2 $");
            System.out.println("CtorUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Create the class using void constructor

            Ctor classToBeTested = new Ctor();

            // Exercise the mutators

            try 
            {
                Class tempClass = Class.forName("java.lang.Byte");
                
                Constructor[] sampleConstructors = tempClass.getConstructors();
                Constructor aSampleConstructor = sampleConstructors[0];

                classToBeTested.setConstructor(aSampleConstructor);
    		
                // Exercise the accessors

                Constructor aPossibleConstructor;

                aPossibleConstructor = classToBeTested.getConstructor();

                if ( aPossibleConstructor != aSampleConstructor)
                {
                    throw(new jacl.util.UnitTestException("CtorUnitTest : Accessor/Mutator test failed for get/setConstructor"));
                }
            }
            catch (java.lang.ClassNotFoundException classNotFoundError )
            {
                throw new jacl.util.UnitTestException("CtorUnitTest : Unable to find the class to be tested");
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("CtorUnitTest : $Revision: 1.2 $");
            System.out.println("CtorUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("CtorUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("CtorUnitTest : $Revision: 1.2 $");
            System.out.println("CtorUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
