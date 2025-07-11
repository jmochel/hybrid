h15276
s 00161/00000/00000
d D 1.1 99/11/17 12:53:19 jmochel 2 1
cC
cK40683
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:16 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/ModelUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45351
cPjava/src/metamodel/ModelUnitTest.java
cR2f93d7185cb6ba86
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
    Contains ModelUnitTest

    $author: Jim Jackl-Mochel $
    $Revision: 1.2 $

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
import java.util.Enumeration;

import jacl.util.UnitTestException;
import jacl.metamodel.Model;
import jacl.metamodel.RecursiveClassDump;

/**
    A self contained Unit Test for Model

    <p>This class forms a single executable class for exercising
    Model. Another class ModelUnitTestMain actually
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
    <p>This code was last modified on $Date: 1999/02/16 20:21:51 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see Model
    @see ModelUnitTestMain
    @see <???>
    @since JDK 1.1
*/

public class ModelUnitTest
{
    /**
        Void constructor that just initialises
    */

    public ModelUnitTest()
    {
    }

    /**
        Give Model a vigorous shakedown.

        @see Model
        @see ModelUnitTestMain
        @since JDK 1.1
    */

    public boolean Exercise()
    {

        boolean testWasSuccessful = true;
        Date    currDateTime = new Date();
        Model classToBeTested;
        
        try
        {
            // Add detailed start up information
            // Who, what, when, why, and how.
    
            System.out.println("ModelUnitTest : $Revision: 1.2 $");
            System.out.println("ModelUnitTest : Starting Unit Test - " + currDateTime.toString());

            // Initialise the test data

            // Create the class 

            try 
            {
                classToBeTested = new Model("java.lang.Byte");
                
            }
            catch (ClassNotFoundException classNotFoundError)
            {
                throw new jacl.util.UnitTestException("Unable to find class to be tested");
            }

            // Print the details out
            
            RecursiveClassDump classDump = new RecursiveClassDump(classToBeTested);
            
            System.out.println(classDump);
            
            // Add detailed ending information
            // Who, what, when, why, and how.

            System.out.println("ModelUnitTest : $Revision: 1.2 $");
            System.out.println("ModelUnitTest : Ending Unit Test - " + currDateTime.toString());

        }
        catch (jacl.util.UnitTestException unitTestError)
        {
            System.out.println("ModelUnitTest : Unit Test Failed");
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
