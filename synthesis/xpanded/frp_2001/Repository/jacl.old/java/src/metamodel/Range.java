/*
    $Copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/Range.java,v 1.3 1999/07/28 03:53:33 jmochel Exp $
*/

package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;
import java.util.Enumeration;

// Other Imports

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    A collection of instances of possible values for a single parameter

    <p>This is really nothing more than an array of elements
    all of the same type. 

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see RangeUnitTest
*/

public class Range 
    implements Serializable,Cloneable
{
    /**
        Empty Constructor
    */

    public Range()
    {
        // Assumes that array will be managed in accessor/mutator 

        _Values = new Vector();
    }

    /**
        Full Parameter Constructor

        @param  values   An array of Object[] to be put into the range
    */

    public Range(Object[] values)
    {
        this();

        _Values.copyInto(values);
    }

    /*
        =========================
                Accessors
        =========================
    */

    /**
        Gets an enumeration of the possible values in the ranges.

        @see    setValues(Object[] values)
    */

    Enumeration getPossibleValues()
    {
        return _Values.elements();
    }

    /**
        Gets the Value at the given index

        @see    setValueAt(int ndx, Object value)
    */

    Object getPossibleValueAt(int ndx)
    {
        return (Object) _Values.elementAt(ndx);
    }

    /**
        Gets the count of Values

        @return int count of the number of Values
    */

    int getPossibleValueCnt()
    {
        return _Values.size();
    }


    /*
        =========================
                Mutators
        =========================
    */
    
    /**
        Replaces any existing values with <code>values.</code>

        @see    getValues()
    */

    public void setPossibleValues(Object[] values)
    {
        _Values.removeAllElements();
        _Values.copyInto(values);
    }


    /**
        Sets the Value at the given index to be value.
    
        @param  ndx             The index or position of the Value to be set

        @see    getValueAt(int ndx)
    */

    public void setPossibleValueAt(int ndx, Object value)
    {
        _Values.insertElementAt(value, ndx);
    }


    /**
        Adds value to the set of  Values.

        @see    getValueAt(int ndx)
    */

    public void addPossibleValue(Object value)
    {
        _Values.addElement(value);
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
            Range    castObject = (Range) object;

            if ( _Values.equals(castObject._Values) == false )
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

        bfr.append("Values = " + _Values + "\n" );

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
        
        Range castObject = (Range) object;

        castObject._Values = (Vector) _Values.clone();
        castObject._Values = (Vector) _Values;
       
       return(object);
    }


    /**
        The set of values that make up the range.
    */            

    private Vector  _Values;
}
