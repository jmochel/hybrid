h34890
s 00101/00000/00000
d D 1.1 99/11/17 12:53:26 jmochel 2 1
cC
cK60509
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:23 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/MthdUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45352
cPjava/src/metamodel/MthdUnitTest.java
cR2f93d7165cb6ba86
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
E 2
I 1
E 1
