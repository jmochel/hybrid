
/*
    Contains RuneRangeUnitTest

    $author: Jim Jackl-Mochel $
    $Revision: 1.3 $

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

package jacl.editor;

// Imports

import java.util.Date;
import jacl.test.UnitTestException;
import jacl.editor.RuneRange;

/**
    A self contained Unit Test for RuneRange

    <p>This class forms a single executable class for exercising
    RuneRange. Another class RuneRangeUnitTestMain actually
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

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/02/12 18:51:12 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see RuneRange
    @see RuneRangeUnitTestMain
    @see <???>
    @since JDK 1.1
*/

public class RuneRangeUnitTest
{
    /**
        Void constructor that just initialises
    */

    public RuneRangeUnitTest()
    {
    }

    /**
        Give RuneRange a vigorous shakedown.

        @see RuneRange
        @see RuneRangeUnitTestMain
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
    
            System.out.println("RuneRangeUnitTest : $Revision: 1.3 $");
            System.out.println("RuneRangeUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Create the class using void constructor

            RuneRange classToBeTested = new RuneRange();

            // Exercise the mutators

            long aSampleBegin = 0;
            long aSampleEnd = 9;


            classToBeTested.setBegin(aSampleBegin);
            classToBeTested.setEnd(aSampleEnd);
		
            // Exercise the accessors

            long aPossibleBegin;
            long aPossibleEnd;

            aPossibleBegin = classToBeTested.getBegin();

            if ( aPossibleBegin != aSampleBegin)
            {
                throw(new jacl.testing.UnitTestException("RuneRangeUnitTest : Accessor/Mutator test failed for get/setBegin"));
            }
            aPossibleEnd = classToBeTested.getEnd();

            if ( aPossibleEnd != aSampleEnd)
            {
                throw(new jacl.testing.UnitTestException("RuneRangeUnitTest : Accessor/Mutator test failed for get/setEnd"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("RuneRangeUnitTest : $Revision: 1.3 $");
            System.out.println("RuneRangeUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.testing.UnitTestException unitTestError)
        {
            System.out.println("RuneRangeUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ParmProxyUnitTest : $Revision: 1.3 $");
            System.out.println("ParmProxyUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
