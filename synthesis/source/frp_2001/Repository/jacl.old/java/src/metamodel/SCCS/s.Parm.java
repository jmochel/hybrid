h28370
s 00516/00000/00000
d D 1.1 99/11/17 12:53:30 jmochel 2 1
cC
cK55635
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:26 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/Parm.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45353
cPjava/src/metamodel/Parm.java
cR2f93d7155cb6ba86
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
    
    $Header: h:\\Repository/jacl/java/src/metamodel/Parm.java,v 1.3 1999/07/28 03:50:45 jmochel Exp $
*/

package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;
import java.util.Enumeration;

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    A class wrapper for a single parameter.

    <p>This class is an abstract wrapper for several possible attributes
    of a method's parameters.   Please note the difference between the range of possible 
    values a parameter can take on (as given by the range of a parm) versus the actual
    instances of values (as given by the value of a parm)it has currently taken on.
    
    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see ParmUnitTest
*/

public class Parm 
    implements Serializable,Cloneable
{
    /**
        Empty Constructor
    */

    public Parm()
    {
        // Assumes that array will be managed in accessor/mutator 

        _Values = new Vector();
        _Range = new Range();
    }

    /**
        Full Parameter Constructor

        @param  name   The name of the parameter
        @param  type   The type of the parameter
        @param  range   The range of possible values the parameter can take on.
        @param  values   The actual values the parameter takes on.
    */

    public Parm(String name, Type type, Range range, Object[] values)
    {
        // initialize to defaults.
        
        this();

        // Assign the 
        
        _Name = name;
        _Type = type;
        _Range = range;
        _Values.copyInto(values);
    }

    /**
        Constructor

        @param  name   The name of the parameter
        @param  cl     The class of the parameter
    */

    public Parm(String name, Class cl)
    {
        this();

        _Name = name;
        _Type = new Type(cl);
    }

    /**
        Full Parameter Constructor

        @param  cl     The class of the parameter
    */

    public Parm(Class cl)
    {
        this();

        _Type = new Type(cl);
    }

    /*
        =========================
                Accessors
        =========================
    */

    /**
        Gets the Name of the parameter

        @see    setName(String name)
    */

    String getName()
    {
        return _Name;
    }

    /**
        Gets the Type of the parameter

        @see    setType(Type type)
    */

    Type getType()
    {
        return _Type;
    }

    /**
        Gets the Range of possible values the parameter can take on

        @see    setRange(Range range)
    */

    Range getRange()
    {
        return _Range;
    }


    /**
        Gets an enumeration of the range of values the parameter is taking

        @see    setValues(Object[] values)
    */

    Enumeration getPossibleValues()
    {
        return _Range.getPossibleValues();
    }

    /**
        Gets the Value at the given index 
 
        @see    setValueAt(int ndx, Object value)
    */

    Object getPossibleValueAt(int ndx)
    {
        return (Object) _Range.getPossibleValueAt(ndx);
    }

    /**
        Gets the count of Values

        @return int count of the number of Values
    */

    int getPossibleValueCnt()
    {
        return _Range.getPossibleValueCnt();
    }

    /**
        Gets the range of values the parameter has taken on.

        @see    setValues(Object[] values)
    */

    Object[] getValues()
    {

        // Create the temporary array
        
        Object[] tempArray = new Object[_Values.size()];
        
        // Populate it.
        
        for (int i = 0; i < tempArray.length; i++ )
        {
            tempArray[i] = (Object) _Values.elementAt(i);
        }
        
        // return it
        
        return tempArray;
    }

    /**
        Gets the nth Value 

        @see    setValueAt(int ndx, Object value)
    */

    Object getValueAt(int ndx)
    {
        return (Object) _Values.elementAt(ndx);
    }

    /**
        Gets the count of Values

        @return int count of the number of Values
    */

    int getValueCnt()
    {
        return _Values.size();
    }

    /**
        Gets the name of the type of the parameter

        @see    setValueAt(int ndx, Object value)
    */

    String getTypeName()
    {
        return _Type.getName();
    }

    /**
        Gets the type associated with the parameter

        @see    setValueAt(int ndx, Object value)
    */

    Class getTypeClass()
    {
        return _Type.getType();
    }

    /*
        =========================
                Mutators
        =========================
    */
    
    /**
        Sets the Name to be name.

        @see    getName()
    */

    public void setName(String name)
    {
        _Name = name;
    }

    /**
        Sets the Type of the parameter

        @see    getType()
    */

    public void setType(Type type)
    {
        _Type = type;
    }

    /**
        Sets the possible Range of values to range.

        @see    getRange()
    */

    public void setRange(Range range)
    {
        _Range = range;
    }

    /**
        Sets the range of possible values to <code> values</code>

        @see    getValues()
    */

    public void setPossibleValues(Object[] values)
    {
        _Range.setPossibleValues(values);
    }


    /**
        Sets the nth Value to be value.

        @param  value   The <code>Object</code> to be <???>
        @param  ndx             The index or position of the Value to be set

        @see    getValueAt(int ndx)
    */

    public void setPossibleValueAt(int ndx, Object value)
    {
        _Range.setPossibleValueAt(ndx,value);
    }

    /**
        Adds a possible value to the range of possible Values.

        @param  value   The <code>Object</code> to be <???>

        @see    getValueAt(int ndx)
    */

    public void addPossibleValue(Object value)
    {
        _Range.addPossibleValue(value);
    }

    /**
        Sets the the actual Values to <code>values</code>.

        @param  values   The <code>Object[]</code> to be <???>

        @see    getValues()
    */

    public void setValues(Object[] values)
    {
        _Values.removeAllElements();
        _Values.copyInto(values);
    }

    /**
        Sets the nth Value to value

        @param  value   The <code>Object</code> to be <???>
        @param  ndx             The index or position of the Value to be set

        @see    getValueAt(int ndx)
    */
    public void setValueAt(int ndx, Object value)
    {
        _Values.insertElementAt(value, ndx);
    }


    /**
        Adds a value to the set of  Values.

        @param  value   The <code>Object</code> to be <???>
        @see    getValueAt(int ndx)
    */

    public void addValue(Object value)
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
            Parm    castObject = (Parm) object;

            if ( _Name.equals(castObject._Name) == false )
            {
                return(false);
            }

            if ( _Type.equals(castObject._Type) == false )
            {
                return(false);
            }

            if ( _Range.equals(castObject._Range) == false )
            {
                return(false);
            }

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
    */

    public String toString()
    {
        StringBuffer    bfr = new StringBuffer();

        bfr.append("(");

        bfr.append(" parm.name=");
        bfr.append("\"");
        bfr.append(_Name);
        bfr.append("\"");

        bfr.append(" parm.type=");
        bfr.append("\"");
        bfr.append(_Type);
        bfr.append("\"");

        bfr.append(" parm.range=");
        bfr.append("\"");        
        bfr.append(_Range);
        bfr.append("\"");

        bfr.append(" parm.values=");
        bfr.append("\"");        
        bfr.append(_Values);
        bfr.append("\"");

        
        bfr.append(")");
        
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
        
        Parm castObject = (Parm) object;

        castObject._Name = (String) _Name;
        castObject._Type = (Type) _Type.clone();
        castObject._Type = (Type) _Type;
        castObject._Range = (Range) _Range.clone();
        castObject._Range = (Range) _Range;
        castObject._Values = (Vector) _Values.clone();
        castObject._Values = (Vector) _Values;
       
       return(object);
    }


    /**
        Name of the parameter
    */            

    private String  _Name;
    

    /**
        Type asscoiated with the Parameter
    */            

    private Type  _Type;
    

    /**
        Range of possible values this parameter can take on.
    */            

    private Range  _Range;
    

    /**
        The set of actuals values this Parm will take on.
    */            

    private Vector  _Values;
}
E 2
I 1
E 1
