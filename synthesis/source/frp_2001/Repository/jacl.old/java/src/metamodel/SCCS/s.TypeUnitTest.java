h36633
s 00127/00000/00000
d D 1.1 99/11/17 12:54:28 jmochel 2 1
cC
cK62188
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:24 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/TypeUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45358
cPjava/src/metamodel/TypeUnitTest.java
cR2f93d7055cb6ba86
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
    
    $Header: h:\\Repository/jacl/java/src/metamodel/TypeUnitTest.java,v 1.2 1999/07/28 04:00:32 jmochel Exp $
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import jacl.util.UnitTestException;
import jacl.metamodel.Type;

/**
    A self contained Unit Test for Type

    <p>This class forms a single executable class for exercising
    Type. Another class TypeUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see Type
    @see TypeUnitTestMain
*/

public class TypeUnitTest
{
    /**
        Void constructor that just initialises
    */

    public TypeUnitTest()
    {
    }

    /**
        Give Type a vigorous shakedown.

        @see Type
        @see TypeUnitTestMain
    */

    public boolean Exercise()
    {

        boolean testWasSuccessful = true;
        Date    currDateTime = new Date();

        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("TypeUnitTest : $Revision: 1.2 $");
            System.out.println("TypeUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Create the Type

            Type classToBeTested = new Type();

            // Exercise the mutators

            Class aSampleClass = null;
            
            try
            {
                aSampleClass = Class.forName("java.lang.Integer");
            }
            catch ( java.lang.ClassNotFoundException classNotFoundError)
            {
                throw(new jacl.util.UnitTestException("TypeUnitTest : Unable to create class from the name for the type"));
            }
            
            String aSampleName = new String("java.lang.Integer");

            classToBeTested.setName(aSampleName);
            classToBeTested.setClass(aSampleClass);
		
            // Exercise the accessors

            String aPossibleName;
            Class aPossibleClass;

            aPossibleName = classToBeTested.getName();

            if ( aPossibleName != aSampleName)
            {
                throw(new jacl.util.UnitTestException("TypeUnitTest : Accessor/Mutator test failed for get/setName"));
            }
            aPossibleClass = classToBeTested.getType();

            if ( aPossibleClass != aSampleClass)
            {
                throw(new jacl.util.UnitTestException("TypeUnitTest : Accessor/Mutator test failed for get/setClass"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("TypeUnitTest : $Revision: 1.2 $");
            System.out.println("TypeUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("TypeUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("TypeUnitTest : $Revision: 1.2 $");
            System.out.println("TypeUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
E 2
I 1
E 1
