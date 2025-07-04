/*
    Contains RuneStore

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
    <p>This code was last modified on $Date: 1999/01/07 22:13:51 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see <???>
    @see RuneStoreUnitTest
    @since JDK 1.1
*/

public class RuneStore 
    implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public RuneStore()
    {
    }


    /**
        Full Parameter Constructor

        @param  name   The File to be <???>
        @param  stream   The Stream to be <???>

        @see <???>
        @see <???>
        @since JDK 1.1
    */

    public RuneStore(File name, Stream stream)
    {
        this();

        _Name = name;
        _Stream = stream;
    }

    /*
        =========================
                Accessors
        =========================
    */


    /**
        Gets the Name to be <???>
        <p>The Name is (<???> - describe its meaning , when it is used).

        @return File

        @see    setName(File name)
        @since  JDK 1.1
    */

    File getName()
    {
        return _Name;
    }




    /**
        Gets the Stream to be <???>
        <p>The Stream is (<???> - describe its meaning , when it is used).

        @return Stream

        @see    setStream(Stream stream)
        @since  JDK 1.1
    */

    Stream getStream()
    {
        return _Stream;
    }




    /*
        =========================
                Mutators
        =========================
    */
    

    /**
        Sets the Name to be name.
        <p>The Name is (<???> - describe its meaning , when it is used).

        @param  name   The <code>File</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getName()
        @since  JDK 1.1
    */

    public void setName(File name)
    {
        _Name = name;
    }




    /**
        Sets the Stream to be stream.
        <p>The Stream is (<???> - describe its meaning , when it is used).

        @param  stream   The <code>Stream</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getStream()
        @since  JDK 1.1
    */

    public void setStream(Stream stream)
    {
        _Stream = stream;
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
            RuneStore    castObject = (RuneStore) object;

            if ( _Name.equals(castObject._Name) == false )
            {
                return(false);
            }

            if ( _Stream.equals(castObject._Stream) == false )
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

        bfr.append("Name = " + _Name + "\n" );
        bfr.append("Stream = " + _Stream + "\n" );

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
        
        RuneStore castObject = (RuneStore) object;

        castObject._Name = (File) _Name.clone();
        castObject._Name = (File) _Name;
        castObject._Stream = (Stream) _Stream.clone();
        castObject._Stream = (Stream) _Stream;
       
       return(object);
    }


    /**
        <???>
    */            

    public File  _Name;
    

    /**
        <???>
    */            

    public Stream  _Stream;
    

}
