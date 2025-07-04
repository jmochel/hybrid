/*
    $copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/Mthd.java,v 1.2 1999/07/28 03:49:12 jmochel Exp $
*/

package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;

import java.lang.reflect.Method;

// Other Imports

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    A proxy for a method of a class

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see MthdUnitTest
*/

public class Mthd 
    extends Function
    implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public Mthd()
    {
	    super();
    }


    /**
        Full Parameter Constructor

        @param  method   The Method to be <???>

        @since JDK 1.1
    */

    public Mthd(Method method)
    {
        super();

        _Method = method;
        
        setSignature(_Method.toString());
        
        Class[] parmTypes = _Method.getParameterTypes();
        
        if ( parmTypes.length != 0 )
        {
            for( int i =0; i < parmTypes.length; i++ )
            {
                Integer I = new Integer(i);
                
                String name = new String("Parm" + I.toString());
                
                addParm(name, parmTypes[i]);
            }
        }
    }

    /*
        =========================
                Accessors
        =========================
    */

    /**
        Gets the Method to be <???>

        <p>The Method is (<???> - describe its meaning , when it is used).

        @return Method

        @see    setMethod(Method method)
        @since  JDK 1.1
    */

    Method getMethod()
    {
        return _Method;
    }


    /*
        =========================
                Mutators
        =========================
    */
    
    /**
        Sets the Method to be method.
        <p>The Method is (<???> - describe its meaning , when it is used).

        @param  method   The <code>Method</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getMethod()
        @since  JDK 1.1
    */

    public void setMethod(Method method)
    {
        _Method = method;
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
            Mthd    castObject = (Mthd) object;

            if ( _Method.equals(castObject._Method) == false )
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

        bfr.append("Method = " + _Method + "\n" );

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
        
        Mthd castObject = (Mthd) object;

        castObject._Method = (Method) _Method;
       
       return(object);
    }


    /**
        The method represented by this object
    */            

    private Method  _Method;
    

}
