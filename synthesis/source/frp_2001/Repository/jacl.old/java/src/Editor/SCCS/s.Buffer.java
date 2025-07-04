h36994
s 00437/00000/00000
d D 1.1 99/11/17 12:55:15 jmochel 2 1
cC
cK64519
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:55:11 jmochel 1 0
c BitKeeper file e:/jacl/java/src/Editor/Buffer.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45363
cPjava/src/Editor/Buffer.java
cR2f93d7385cb6ba86
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
    Contains Buffer

    $author: Jim Jackl-Mochel $
    $Revision: 1.5 $

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

package jacl.<???>;

// Standard Imports

import java.io.Serializable;

// Other Imports

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    <???> // A Short summary

    <p><???> // Notes

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
    <p>This code was last modified on $Date: 1999/01/07 22:13:49 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.5 $

    @see <???>
    @see BufferUnitTest
    @since JDK 1.1
*/

public 
class Buffer 
implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public Buffer()
    {
    }


    /**
        Full Parameter Constructor

        @param  transcript   The RuneStore to be <???>
        @param  contents   The RuneStore to be <???>
        @param  dot   The RuneRange to be <???>
        @param  ismodified   The boolean to be <???>

        @see <???>
        @see <???>
        @since JDK 1.1
    */

    public Buffer
    (
         RuneStore transcript,
         RuneStore contents,
         RuneRange dot,
         boolean ismodified
    )
    {
        this();

        _Transcript = transcript;
        _Contents = contents;
        _Dot = dot;
        _IsModified = ismodified;
    }

    /*
        =========================
                Accessors
        =========================
    */


    /**
        Gets the Transcript to be <???>
        <p>The Transcript is (<???> - describe its meaning , when it is used).

        @return RuneStore

        @see    setTranscript(RuneStore transcript)
        @since  JDK 1.1
    */

    RuneStore getTranscript()
    {
        return _Transcript;
    }




    /**
        Gets the Contents to be <???>
        <p>The Contents is (<???> - describe its meaning , when it is used).

        @return RuneStore

        @see    setContents(RuneStore contents)
        @since  JDK 1.1
    */

    RuneStore getContents()
    {
        return _Contents;
    }




    /**
        Gets the Dot to be <???>
        <p>The Dot is (<???> - describe its meaning , when it is used).

        @return RuneRange

        @see    setDot(RuneRange dot)
        @since  JDK 1.1
    */

    RuneRange getDot()
    {
        return _Dot;
    }




    /**
        Gets the IsModified to be <???>
        <p>The IsModified is (<???> - describe its meaning , when it is used).

        @return boolean

        @see    setIsModified(boolean ismodified)
        @since  JDK 1.1
    */

    boolean getIsModified()
    {
        return _IsModified;
    }




    /*
        =========================
                Mutators
        =========================
    */
    

    /**
        Sets the Transcript to be transcript.
        <p>The Transcript is (<???> - describe its meaning , when it is used).

        @param  transcript   The <code>RuneStore</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getTranscript()
        @since  JDK 1.1
    */

    public void setTranscript(RuneStore transcript)
    {
        _Transcript = transcript;
    }




    /**
        Sets the Contents to be contents.
        <p>The Contents is (<???> - describe its meaning , when it is used).

        @param  contents   The <code>RuneStore</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getContents()
        @since  JDK 1.1
    */

    public void setContents(RuneStore contents)
    {
        _Contents = contents;
    }




    /**
        Sets the Dot to be dot.
        <p>The Dot is (<???> - describe its meaning , when it is used).

        @param  dot   The <code>RuneRange</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getDot()
        @since  JDK 1.1
    */

    public void setDot(RuneRange dot)
    {
        _Dot = dot;
    }




    /**
        Sets the IsModified to be ismodified.
        <p>The IsModified is (<???> - describe its meaning , when it is used).

        @param  ismodified   The <code>boolean</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getIsModified()
        @since  JDK 1.1
    */

    public void setIsModified(boolean ismodified)
    {
        _IsModified = ismodified;
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
            Buffer    castObject = (Buffer) object;

            if ( _Transcript.equals(castObject._Transcript) == false )
            {
                return(false);
            }

            if ( _Contents.equals(castObject._Contents) == false )
            {
                return(false);
            }

            if ( _Dot.equals(castObject._Dot) == false )
            {
                return(false);
            }

            if ( _IsModified.equals(castObject._IsModified) == false )
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

        bfr.append("Transcript = " + _Transcript + "\n" );
        bfr.append("Contents = " + _Contents + "\n" );
        bfr.append("Dot = " + _Dot + "\n" );
        bfr.append("IsModified = " + _IsModified + "\n" );

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
        
        Buffer castObject = (Buffer) object;

        castObject._Transcript = (RuneStore) _Transcript.clone();
        castObject._Transcript = (RuneStore) _Transcript;
        castObject._Contents = (RuneStore) _Contents.clone();
        castObject._Contents = (RuneStore) _Contents;
        castObject._Dot = (RuneRange) _Dot.clone();
        castObject._Dot = (RuneRange) _Dot;
        castObject._IsModified = (boolean) _IsModified.clone();
        castObject._IsModified = (boolean) _IsModified;
       
       return(object);
    }


    /**
        <???>
    */            

    private RuneStore  _Transcript;
    

    /**
        <???>
    */            

    private RuneStore  _Contents;
    

    /**
        <???>
    */            

    private RuneRange  _Dot;
    

    /**
        <???>
    */            

    private boolean  _IsModified;
    

}
E 2
I 1
E 1
