h31053
s 00151/00000/00000
d D 1.1 99/11/17 12:54:17 jmochel 2 1
cC
cK56476
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:13 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/TestableModel.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45357
cPjava/src/metamodel/TestableModel.java
cR2f93d7085cb6ba86
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
    $copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/TestableModel.java,v 1.2 1999/07/28 03:57:41 jmochel Exp $
*/

package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;

// Other Imports

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    <???> // A Short summary

    <p><???> // Notes

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see TestableModelUnitTest
*/

public class TestableModel 
	extends Model 
	implements Serializable,Cloneable
{
    /**
        Empty Constructor
    */

    public TestableModel()
    {
	super();

    }


    /**
        Full Parameter Constructor

        @param  methods   The array of Mthd to be <???>
        @param  ctors   The array of Ctor to be <???>
    */

    public TestableModel(String className)
        throws java.lang.ClassNotFoundException
    {
        super(className);
    }

    /*
        =========================
                Accessors
        =========================
    */


    /*
        =========================
                Mutators
        =========================
    */
    

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
    */

    public boolean equals(Object object)
    {
        if ( super.equals(object) == true )
        {
            TestableModel    castObject = (TestableModel) object;

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
        StringBuffer    bfr = new StringBuffer(this.getClass().getName());


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
        
        TestableModel castObject = (TestableModel) object;

       
       return(object);
    }


}
E 2
I 1
E 1
