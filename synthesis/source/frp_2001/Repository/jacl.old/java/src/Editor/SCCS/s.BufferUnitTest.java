h43275
s 00187/00000/00000
d D 1.1 99/11/17 12:55:18 jmochel 2 1
cC
cK03595
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:55:15 jmochel 1 0
c BitKeeper file e:/jacl/java/src/Editor/BufferUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45363
cPjava/src/Editor/BufferUnitTest.java
cR2f93d7375cb6ba86
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
    Contains BufferUnitTest

    $author: Jim Jackl-Mochel $
    $Revision: 1.6 $

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

package jacl.util.<???>;

// Imports

import java.util.Date;
import jacl.test.UnitTestException;
import jacl.util.Buffer;

/**
    A self contained Unit Test for Buffer

    <p>This class forms a single executable class for exercising
    Buffer. Another class BufferUnitTestMain actually
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
    <p>This code was last modified on $Date: 1999/02/12 18:51:11 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.6 $

    @see Buffer
    @see BufferUnitTestMain
    @see <???>
    @since JDK 1.1
*/

public class BufferUnitTest
{
    /**
        Void constructor that just initialises
    */

    public BufferUnitTest()
    {
    }

    /**
        Give Buffer a vigorous shakedown.

        @see Buffer
        @see BufferUnitTestMain
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
    
            System.out.println("BufferUnitTest : $Revision: 1.6 $");
            System.out.println("BufferUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data

            <???>

            // Create the class using void constructor

            Buffer classToBeTested = new Buffer();

            // Exercise the mutators

            RuneStore aSampleTranscript = new RuneStore("");
            RuneStore aSampleContents = new RuneStore("");
            RuneRange aSampleDot = new RuneRange("");
            boolean aSampleIsModified = new boolean("");


            classToBeTested.setTranscript(aSampleTranscript);
            classToBeTested.setContents(aSampleContents);
            classToBeTested.setDot(aSampleDot);
            classToBeTested.setIsModified(aSampleIsModified);
		
            // Exercise the accessors

            RuneStore aPossibleTranscript;
            RuneStore aPossibleContents;
            RuneRange aPossibleDot;
            boolean aPossibleIsModified;

            aPossibleTranscript = classToBeTested.getTranscript();

            if ( aPossibleTranscript != aSampleTranscript)
            {
                throw(new jacl.testing.UnitTestException("BufferUnitTest : Accessor/Mutator test failed for get/setTranscript"));
            }
            aPossibleContents = classToBeTested.getContents();

            if ( aPossibleContents != aSampleContents)
            {
                throw(new jacl.testing.UnitTestException("BufferUnitTest : Accessor/Mutator test failed for get/setContents"));
            }
            aPossibleDot = classToBeTested.getDot();

            if ( aPossibleDot != aSampleDot)
            {
                throw(new jacl.testing.UnitTestException("BufferUnitTest : Accessor/Mutator test failed for get/setDot"));
            }
            aPossibleIsModified = classToBeTested.getIsModified();

            if ( aPossibleIsModified != aSampleIsModified)
            {
                throw(new jacl.testing.UnitTestException("BufferUnitTest : Accessor/Mutator test failed for get/setIsModified"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("BufferUnitTest : $Revision: 1.6 $");
            System.out.println("BufferUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.testing.UnitTestException unitTestError)
        {
            System.out.println("BufferUnitTest : Unit Test Failed");
            System.out.println(unitTestError);
            testWasSuccessful = false;
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ParmProxyUnitTest : $Revision: 1.6 $");
            System.out.println("ParmProxyUnitTest : Ending Unit Test - " + currDateTime.toString());
        }

        return(testWasSuccessful);
    }
}
E 2
I 1
E 1
