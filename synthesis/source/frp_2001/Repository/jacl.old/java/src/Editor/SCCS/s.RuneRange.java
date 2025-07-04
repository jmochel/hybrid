h32071
s 00324/00000/00000
d D 1.1 99/11/17 12:55:29 jmochel 2 1
cC
cK59001
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:55:26 jmochel 1 0
c BitKeeper file e:/jacl/java/src/Editor/RuneRange.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45364
cPjava/src/Editor/RuneRange.java
cR2f93d7345cb6ba86
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
    Contains RuneRange

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

package jacl.editor;

// Standard Imports

import java.io.Serializable;

// Other Imports

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    A simple encapsulation of the concept of a range (begin->end ) of runes.

    <p>The methods are public here to ensure ease of access. 

    <p><B>ToDo</B>
    <ul>
        <li>Check API Naming
        <li>Check API Types
        <li>Check API Returns
        <li>Check API Exceptions
        <li>Check Comments
        <li>Remove unused Comment tags
        <li>Make sure that there is no way to create an object with an invalid state
        <li>If an instance variable doen't have a natural default, have one passed into the constructor
        <li>Throw exceptions on bad input.
        <li>Constructoirs and initializers should only be about initialization
        <li>Minimize coupling, take as input only data needed by the method.
    </ul>

    <p><B>Reminders</B>
    <ul>
        <li>All arg passing is done by handles:Modify the contents and it effects everbody
        <li>Aliasing happens automatically during argument passing
        <li>There are no local objects, only local handles.
        <li>Handles have scope, obects do not
        <li>Object lifetime is not an issue in java
        <li>There is no language suppport to prevent objects from being modified (i.e. const)
    </ul>

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/01/07 22:13:51 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see RuneRangeUnitTest
    @since JDK 1.1
*/

public 
class RuneRange 
implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public RuneRange()
    {
    }


    /**
        Full Parameter Constructor

        @param  begin   The long to be <???>
        @param  end   The long to be <???>

        @see <???>
        @see <???>
        @since JDK 1.1
    */

    public RuneRange
    (
         long begin,
         long end
    )
    {
        this();

        _Begin = begin;
        _End = end;
    }

    /*
        =========================
                Accessors
        =========================
    */


    /**
        Gets the Begin to be <???>
        <p>The Begin is (<???> - describe its meaning , when it is used).

        @return long

        @see    setBegin(long begin)
        @since  JDK 1.1
    */

    long getBegin()
    {
        return _Begin;
    }




    /**
        Gets the End to be <???>
        <p>The End is (<???> - describe its meaning , when it is used).

        @return long

        @see    setEnd(long end)
        @since  JDK 1.1
    */

    long getEnd()
    {
        return _End;
    }




    /*
        =========================
                Mutators
        =========================
    */
    

    /**
        Sets the Begin to be begin.
        <p>The Begin is (<???> - describe its meaning , when it is used).

        @param  begin   The <code>long</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getBegin()
        @since  JDK 1.1
    */

    public void setBegin(long begin)
    {
        _Begin = begin;
    }




    /**
        Sets the End to be end.
        <p>The End is (<???> - describe its meaning , when it is used).

        @param  end   The <code>long</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getEnd()
        @since  JDK 1.1
    */

    public void setEnd(long end)
    {
        _End = end;
    }




    /*
        =====================================
                java.lang.Object  Overrides
        =====================================
    */
    

    /**
        Generates hash codes

        @see jacl.util.hash.PearsonsHash
        @see jacl.lang.Object#hashCode()
        @since JDK 1.1
    */

    public int hashCode()
    {
        return(PearsonsHash.generateIntRangeHash(toString()));
    }

    /**
        Checks for equality

        @see java.lang.Object#equals(Object)
        @since JDK 1.1
    */

    public boolean equals(Object object)
    {
        if ( super.equals(object) == true )
        {
            RuneRange    castObject = (RuneRange) object;

            if ( _Begin != castObject._Begin)
            {
                return(false);
            }

            if ( _End != castObject._End)
            {
                return(false);
            }

         }
        else {
            return(false);
        }

        return(true);
    }

    /**
        Generates a string representation of the class

        @see java.lang.Object#toString()
        @since JDK 1.1
    */

    public String toString()
    {
        StringBuffer    bfr = new StringBuffer(this.getClass().getName());

        bfr.append("Begin = " + _Begin + "\n" );
        bfr.append("End = " + _End + "\n" );

        return(bfr.toString());
    }

    /**
        Clones this object

        <p><B>Caveat Emptor: This clone currently does a shallow copy</B>

        @see java.lang.Object#clone()
        @since JDK 1.1
    */

    public Object clone() throws CloneNotSupportedException
    {
        Object object = null;

        object = super.clone();
        
        RuneRange castObject = (RuneRange) object;

        castObject._Begin = (long) _Begin;
        castObject._End = (long) _End;
       
       return(object);
    }


    /**
        <???>
    */            

    private long  _Begin;
    

    /**
        <???>
    */            

    private long  _End;
    

}
E 2
I 1
E 1
