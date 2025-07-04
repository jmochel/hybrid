h53924
s 00109/00000/00000
d D 1.1 99/11/17 12:53:48 jmochel 2 1
cC
cK13810
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:45 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/RangeUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45354
cPjava/src/metamodel/RangeUnitTest.java
cR2f93d7105cb6ba86
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
    $Copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/RangeUnitTest.java,v 1.2 1999/07/28 03:54:28 jmochel Exp $
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import jacl.util.UnitTestException;
import jacl.metamodel.Range;

/**
    A self contained Unit Test for Range

    <p>This class forms a single executable class for exercising
    Range. Another class RangeUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see Range
    @see RangeUnitTestMain
*/

public class RangeUnitTest
{
    /**
        Void constructor that just initialises
    */

    public RangeUnitTest()
    {
    }

    /**
        Give Range a vigorous shakedown.

        @see Range
        @see RangeUnitTestMain
    */

    public boolean Exercise()
    {

        boolean testWasSuccessful = true;
        Date    currDateTime = new Date();

        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("RangeUnitTest : $Revision: 1.2 $");
            System.out.println("RangeUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Create the class using void constructor

            Range classToBeTested = new Range();

            // Exercise the mutators

            Object aSampleValue = new Byte("1");


            classToBeTested.addPossibleValue(aSampleValue);
		
            // Exercise the accessors

            Object aPossibleValue;

            aPossibleValue = classToBeTested.getPossibleValueAt(0);

            if ( aPossibleValue != aSampleValue)
            {
                throw(new jacl.util.UnitTestException("RangeUnitTest : Accessor/Mutator test failed for get/setValue"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("RangeUnitTest : $Revision: 1.2 $");
            System.out.println("RangeUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("RangeUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("RangeUnitTest : $Revision: 1.2 $");
            System.out.println("RangeUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
E 2
I 1
E 1
