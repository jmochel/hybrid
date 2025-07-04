
/*
    Contains ParmListUnitTest

    $author: Jim Jackl-Mochel $
    $Revision: 1.1 $

    Copyright (c) 1998 Jim Jackl-Mochel
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:

    1. Redistributions of source code must retain the above copyright
       notice, this list of conditions and the following disclaimer.

    2. Redistributions in binary form must reproduce the above copyright
       notice, this list of conditions and the following disclaimer in the
       documentation and/or other materials provided with the distribution.

    3. The name of the author may not be used to endorse or promote products
       derived from this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

// Package Declaration

package jacl.metamodel;

// Imports

import java.util.Date;
import jacl.util.UnitTestException;
import jacl.metamodel.ParmList;

/**
    A self contained Unit Test for ParmList

    <p>This class forms a single executable class for exercising
    ParmList. Another class ParmListUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

    <p><B>ToDo</B>
    <ul>
        <li>Check API Naming
        <li>Check API Types
        <li>Check API Returns
        <li>Check API Exceptions
        <li>Check API Exception Specifications
        <li>Check Comments
        <li>Remove unused Comment tags
    </ul>

    <p><B>Copyright (c) 1998 Jim Jackl-Mochel</B>
    All rights reserved. See source for license agreement

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/02/12 15:38:59 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.1 $

    @see ParmList
    @see ParmListUnitTestMain
    @see <???>
    @since JDK 1.1
*/

public class ParmListUnitTest
{
    /**
        Void constructor that just initialises
    */

    public ParmListUnitTest()
    {
    }

    /**
        Give ParmList a vigorous shakedown.

        @see ParmList
        @see ParmListUnitTestMain
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
    
            System.out.println("ParmListUnitTest : $Revision: 1.1 $");
            System.out.println("ParmListUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data

           

            // Create the class using void constructor

            ParmList classToBeTested = new ParmList();

            // Exercise the mutators

            Object aSampleValue = new Byte("1");


            classToBeTested.addParm(aSampleValue);
		
            // Exercise the accessors

            Object aPossibleValue;

            aPossibleValue = classToBeTested.getParmAt(0);

            if ( aPossibleValue != aSampleValue)
            {
                throw(new jacl.util.UnitTestException("ParmListUnitTest : Accessor/Mutator test failed for get/setValue"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ParmListUnitTest : $Revision: 1.1 $");
            System.out.println("ParmListUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("ParmListUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ParmProxyUnitTest : $Revision: 1.1 $");
            System.out.println("ParmProxyUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
