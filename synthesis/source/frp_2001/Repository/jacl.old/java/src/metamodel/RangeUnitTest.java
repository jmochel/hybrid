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
