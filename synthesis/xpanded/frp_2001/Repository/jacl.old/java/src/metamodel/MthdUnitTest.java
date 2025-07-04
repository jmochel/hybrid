/*
    $copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/MthdUnitTest.java,v 1.2 1999/07/28 03:49:35 jmochel Exp $
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import jacl.util.UnitTestException;
import jacl.metamodel.Mthd;

/**
    A self contained Unit Test for Mthd

    <p>This class forms a single executable class for exercising
    Mthd. Another class MthdUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see Mthd
    @see MthdUnitTestMain
*/

public class MthdUnitTest
{
    /**
        Void constructor that just initialises
    */

    public MthdUnitTest()
    {
    }

    /**
        Give Mthd a vigorous shakedown.

        @see Mthd
        @see MthdUnitTestMain
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
    
            System.out.println("MthdUnitTest : $Revision: 1.2 $");
            System.out.println("MthdUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data

            

            // Create the class using void constructor

            Mthd classToBeTested = new Mthd();

            if ( classToBeTested == null)
            {
                throw(new jacl.util.UnitTestException("MthdUnitTest : Accessor/Mutator test failed for get/setMethod"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("MthdUnitTest : $Revision: 1.2 $");
            System.out.println("MthdUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("MthdUnitTest : Unit Test Failed");
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
