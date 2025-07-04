h30530
s 00121/00000/00000
d D 1.1 99/11/17 12:53:05 jmochel 2 1
cC
cK56085
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:01 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/CtorUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45350
cPjava/src/metamodel/CtorUnitTest.java
cR2f93d71c5cb6ba86
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
E 2
I 1
E 1
