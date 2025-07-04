h29143
s 00418/00000/00000
d D 1.1 99/11/17 12:55:22 jmochel 2 1
cC
cK53209
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:55:18 jmochel 1 0
c BitKeeper file e:/jacl/java/src/Editor/FixedSizeBlockRuneStore.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45364
cPjava/src/Editor/FixedSizeBlockRuneStore.java
cR2f93d7365cb6ba86
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
    Contains FixedSizeBlockRuneStore

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
    <p>This code was last modified on $Date: 1999/01/07 22:13:50 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see <???>
    @see FixedSizeBlockRuneStoreUnitTest
    @since JDK 1.1
*/

public 
class FixedSizeBlockRuneStore 
extends RuneStore
implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public FixedSizeBlockRuneStore()
    {
    }

    /**
        Full Parameter Constructor

        @param  name   The File to be <???>
        @param  stream   The Stream to be <???>
        @param  cacheisdirty   The boolean to be <???>
        @param  cacherange   The RuneRange to be <???>
        @param  cache   The StringBuffer to be <???>

        @since JDK 1.1
    */

    public FixedSizeBlockRuneStore
    (
         File name,
         Stream stream,
         boolean cacheisdirty,
         RuneRange cacherange,
         StringBuffer cache
    )
    {
        super(
         name,
         stream
    );

        this();


        _CacheIsDirty = cacheisdirty;

        _CacheRange = cacherange;

        _Cache = cache;
    }

    /**
        Full Parameter Constructor

        @param  cacheisdirty   The boolean to be <???>
        @param  cacherange   The RuneRange to be <???>
        @param  cache   The StringBuffer to be <???>

        @see <???>
        @see <???>
        @since JDK 1.1
    */

    public FixedSizeBlockRuneStore
    (
         boolean cacheisdirty,
         RuneRange cacherange,
         StringBuffer cache
    )
    {
        this();

        _CacheIsDirty = cacheisdirty;
        _CacheRange = cacherange;
        _Cache = cache;
    }

    /*
        =========================
                Accessors
        =========================
    */


    /**
        Gets the CacheIsDirty to be <???>
        <p>The CacheIsDirty is (<???> - describe its meaning , when it is used).

        @return boolean

        @see    setCacheIsDirty(boolean cacheisdirty)
        @since  JDK 1.1
    */

    boolean getCacheIsDirty()
    {
        return _CacheIsDirty;
    }




    /**
        Gets the CacheRange to be <???>
        <p>The CacheRange is (<???> - describe its meaning , when it is used).

        @return RuneRange

        @see    setCacheRange(RuneRange cacherange)
        @since  JDK 1.1
    */

    RuneRange getCacheRange()
    {
        return _CacheRange;
    }




    /**
        Gets the Cache to be <???>
        <p>The Cache is (<???> - describe its meaning , when it is used).

        @return StringBuffer

        @see    setCache(StringBuffer cache)
        @since  JDK 1.1
    */

    StringBuffer getCache()
    {
        return _Cache;
    }




    /*
        =========================
                Mutators
        =========================
    */
    

    /**
        Sets the CacheIsDirty to be cacheisdirty.
        <p>The CacheIsDirty is (<???> - describe its meaning , when it is used).

        @param  cacheisdirty   The <code>boolean</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getCacheIsDirty()
        @since  JDK 1.1
    */

    public void setCacheIsDirty(boolean cacheisdirty)
    {
        _CacheIsDirty = cacheisdirty;
    }




    /**
        Sets the CacheRange to be cacherange.
        <p>The CacheRange is (<???> - describe its meaning , when it is used).

        @param  cacherange   The <code>RuneRange</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getCacheRange()
        @since  JDK 1.1
    */

    public void setCacheRange(RuneRange cacherange)
    {
        _CacheRange = cacherange;
    }




    /**
        Sets the Cache to be cache.
        <p>The Cache is (<???> - describe its meaning , when it is used).

        @param  cache   The <code>StringBuffer</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getCache()
        @since  JDK 1.1
    */

    public void setCache(StringBuffer cache)
    {
        _Cache = cache;
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
            FixedSizeBlockRuneStore    castObject = (FixedSizeBlockRuneStore) object;

            if ( _CacheIsDirty.equals(castObject._CacheIsDirty) == false )
            {
                return(false);
            }

            if ( _CacheRange.equals(castObject._CacheRange) == false )
            {
                return(false);
            }

            if ( _Cache.equals(castObject._Cache) == false )
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

        bfr.append("CacheIsDirty = " + _CacheIsDirty + "\n" );
        bfr.append("CacheRange = " + _CacheRange + "\n" );
        bfr.append("Cache = " + _Cache + "\n" );

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
        
        FixedSizeBlockRuneStore castObject = (FixedSizeBlockRuneStore) object;

        castObject._CacheIsDirty = (boolean) _CacheIsDirty.clone();
        castObject._CacheIsDirty = (boolean) _CacheIsDirty;
        castObject._CacheRange = (RuneRange) _CacheRange.clone();
        castObject._CacheRange = (RuneRange) _CacheRange;
        castObject._Cache = (StringBuffer) _Cache.clone();
        castObject._Cache = (StringBuffer) _Cache;
       
       return(object);
    }


    /**
        <???>
    */            

    private boolean  _CacheIsDirty;
    

    /**
        <???>
    */            

    private RuneRange  _CacheRange;
    

    /**
        <???>
    */            

    private StringBuffer  _Cache;
    

}
E 2
I 1
E 1
