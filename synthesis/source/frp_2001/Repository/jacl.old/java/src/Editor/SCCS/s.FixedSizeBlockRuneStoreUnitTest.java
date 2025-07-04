h54010
s 00178/00000/00000
d D 1.1 99/11/17 12:55:26 jmochel 2 1
cC
cK10874
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:55:22 jmochel 1 0
c BitKeeper file e:/jacl/java/src/Editor/FixedSizeBlockRuneStoreUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45364
cPjava/src/Editor/FixedSizeBlockRuneStoreUnitTest.java
cR2f93d7355cb6ba86
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
    Contains FixedSizeBlockRuneStoreUnitTest

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

package jacl.util.<???>;

// Imports

import java.util.Date;
import jacl.test.UnitTestException;
import jacl.util.FixedSizeBlockRuneStore;

/**
    A self contained Unit Test for FixedSizeBlockRuneStore

    <p>This class forms a single executable class for exercising
    FixedSizeBlockRuneStore. Another class FixedSizeBlockRuneStoreUnitTestMain actually
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

    @see FixedSizeBlockRuneStore
    @see FixedSizeBlockRuneStoreUnitTestMain
    @see <???>
    @since JDK 1.1
*/

public class FixedSizeBlockRuneStoreUnitTest
{
    /**
        Void constructor that just initialises
    */

    public FixedSizeBlockRuneStoreUnitTest()
    {
    }

    /**
        Give FixedSizeBlockRuneStore a vigorous shakedown.

        @see FixedSizeBlockRuneStore
        @see FixedSizeBlockRuneStoreUnitTestMain
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
    
            System.out.println("FixedSizeBlockRuneStoreUnitTest : $Revision: 1.3 $");
            System.out.println("FixedSizeBlockRuneStoreUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data

            <???>

            // Create the class using void constructor

            FixedSizeBlockRuneStore classToBeTested = new FixedSizeBlockRuneStore();

            // Exercise the mutators

            boolean aSampleCacheIsDirty = new boolean("");
            RuneRange aSampleCacheRange = new RuneRange("");
            StringBuffer aSampleCache = new StringBuffer("");


            classToBeTested.setCacheIsDirty(aSampleCacheIsDirty);
            classToBeTested.setCacheRange(aSampleCacheRange);
            classToBeTested.setCache(aSampleCache);
		
            // Exercise the accessors

            boolean aPossibleCacheIsDirty;
            RuneRange aPossibleCacheRange;
            StringBuffer aPossibleCache;

            aPossibleCacheIsDirty = classToBeTested.getCacheIsDirty();

            if ( aPossibleCacheIsDirty != aSampleCacheIsDirty)
            {
                throw(new jacl.testing.UnitTestException("FixedSizeBlockRuneStoreUnitTest : Accessor/Mutator test failed for get/setCacheIsDirty"));
            }
            aPossibleCacheRange = classToBeTested.getCacheRange();

            if ( aPossibleCacheRange != aSampleCacheRange)
            {
                throw(new jacl.testing.UnitTestException("FixedSizeBlockRuneStoreUnitTest : Accessor/Mutator test failed for get/setCacheRange"));
            }
            aPossibleCache = classToBeTested.getCache();

            if ( aPossibleCache != aSampleCache)
            {
                throw(new jacl.testing.UnitTestException("FixedSizeBlockRuneStoreUnitTest : Accessor/Mutator test failed for get/setCache"));
            }

            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("FixedSizeBlockRuneStoreUnitTest : $Revision: 1.3 $");
            System.out.println("FixedSizeBlockRuneStoreUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.testing.UnitTestException unitTestError)
        {
            System.out.println("FixedSizeBlockRuneStoreUnitTest : Unit Test Failed");
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
E 2
I 1
E 1
