h04461
s 00247/00000/00000
d D 1.1 99/11/17 12:54:24 jmochel 2 1
cC
cK31690
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:20 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/Type.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45358
cPjava/src/metamodel/Type.java
cR2f93d7065cb6ba86
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

    $Header: h:\\Repository/jacl/java/src/metamodel/Type.java,v 1.3 1999/07/28 03:59:44 jmochel Exp $
*/

package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;
import java.lang.Class;

// JACL Imports

import jacl.util.hash.PearsonsHash;

/**
    A wrapper for the Name and Class of a parameter's type.

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see TypeUnitTest
*/

public class Type
    implements Serializable,Cloneable
{
    /**
        Empty Constructor
    */

    public Type()
    {
        _Name = null;
        _Class = null;
    }

    /**
        Full Parameter Constructor

        @param  name   The name of the type (i.e. "Integer", "Byte" and so on.)
    */

    public Type(String name)
    {
        this();
        
        _Name = name;
        
        try 
        {
            _Class = Class.forName(name);
        }
        catch (java.lang.ClassNotFoundException classNotFoundError)
        {
            throw new IllegalArgumentException("Unable to find class: " + name);
        }
        
    }

    /**
        Full Parameter Constructor

        @param  class   The Class of the type
    */

    public Type(Class cl)
    {
        this();

        _Class = cl;
        _Name = cl.getName();
    }

    /*
        =========================
                Accessors
        =========================
    */

    /**
        Gets the Name of the type.

        @return String
        @see    setName(String name)
    */

    String getName()
    {
        return _Name;
    }

    /**
        Gets the Class asspciated with the type.

        @see    setClass(Class class)
    */

    Class getType()
    {
        return _Class;
    }

    /*
        =========================
                Mutators
        =========================
    */
    
    /**
        Sets the Name of the Type
        
        @see    getName()
    */

    public void setName(String name)
    {
        _Name = name;
    }

    /**
        Sets the Class to be class.

        @see    getType()
    */

    public void setClass(Class cl)
    {
        _Class = cl;
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
    */

    public int hashCode()
    {
        return(PearsonsHash.generateIntRangeHash(toString()));
    }

    /**
        Checks for equality

        @see java.lang.Object#equals(Object)
    */

    public boolean equals(Object object)
    {
        if ( super.equals(object) == true )
        {
            Type    castObject = (Type) object;

            if ( _Name.equals(castObject._Name) == false )
            {
                return(false);
            }

            if ( _Class.equals(castObject._Class) == false )
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
    */

    public String toString()
    {
        StringBuffer    bfr = new StringBuffer();

        bfr.append("(");
        bfr.append("type.name=");
        bfr.append("\"");
        bfr.append(_Name);
        bfr.append("\"");
        
        bfr.append(" type.class=");
        bfr.append("\"");
        bfr.append(_Class);
        bfr.append("\"");
        bfr.append(")");
        
        return(bfr.toString());
    }

    /**
        Clones this object

        <p><B>Caveat Emptor: This clone currently does a shallow copy</B>

        @see java.lang.Object#clone()
    */

    public Object clone() throws CloneNotSupportedException
    {
        Object object = null;

        object = super.clone();
        
        Type castObject = (Type) object;

        castObject._Name = (String) _Name;
        castObject._Class = (Class) _Class;
       
       return(object);
    }


    /**
        Name of the Type
    */            

    private String  _Name;
    
    /**
        Class for this Type
    */            

    private Class  _Class;
    

}
E 2
I 1
E 1
