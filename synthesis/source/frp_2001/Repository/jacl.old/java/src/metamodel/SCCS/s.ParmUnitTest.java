h52955
s 00136/00000/00000
d D 1.1 99/11/17 12:53:41 jmochel 2 1
cC
cK13029
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:37 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/ParmUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45354
cPjava/src/metamodel/ParmUnitTest.java
cR2f93d7125cb6ba86
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
    
    $header$
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import jacl.util.UnitTestException;
import jacl.metamodel.Parm;

/**
    A self contained Unit Test for Parm

    <p>This class forms a single executable class for exercising
    Parm. Another class ParmUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see Parm
    @see ParmUnitTestMain
*/

public class ParmUnitTest
{
    /**
        Void constructor that just initialises
    */

    public ParmUnitTest()
    {
    }

    /**
        Give Parm a vigorous shakedown.

        @see Parm
        @see ParmUnitTestMain
    */

    public boolean Exercise()
    {

        boolean testWasSuccessful = true;
        Date    currDateTime = new Date();

        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("ParmUnitTest : $Revision: 1.2 $");
            System.out.println("ParmUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data



            // Create the class using void constructor

            Parm classToBeTested = new Parm();

            // Exercise the mutators

            String aSampleName = new String("parm1");
            Type aSampleType = new Type("java.lang.Byte");
            
            Range aSampleRange = new Range();
                aSampleRange.addPossibleValue(new Byte("0"));
                aSampleRange.addPossibleValue(new Byte("1"));
                
            Object aSampleValue = new Byte("0");

            classToBeTested.setName(aSampleName);
            classToBeTested.setType(aSampleType);
            classToBeTested.setRange(aSampleRange);
		
            // Exercise the accessors

            String aPossibleName;
            Type aPossibleType;
            Range aPossibleRange;
            Object aPossibleValue;

            aPossibleName = classToBeTested.getName();

            if ( aPossibleName != aSampleName)
            {
                throw(new jacl.util.UnitTestException("ParmUnitTest : Accessor/Mutator test failed for get/setName"));
            }
            aPossibleType = classToBeTested.getType();

            if ( aPossibleType != aSampleType)
            {
                throw(new jacl.util.UnitTestException("ParmUnitTest : Accessor/Mutator test failed for get/setType"));
            }
            aPossibleRange = classToBeTested.getRange();

            if ( aPossibleRange != aSampleRange)
            {
                throw(new jacl.util.UnitTestException("ParmUnitTest : Accessor/Mutator test failed for get/setRange"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ParmUnitTest : $Revision: 1.2 $");
            System.out.println("ParmUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("ParmUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ParmUnitTest : $Revision: 1.2 $");
            System.out.println("ParmUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
E 2
I 1
E 1
