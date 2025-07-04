/*
    $copyright$
    
    $Header: h:\\Repository/jacl/java/src/metamodel/Ctor.java,v 1.2 1999/07/28 03:45:04 jmochel Exp $
*/

package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;

import java.lang.reflect.Constructor;

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    A Proxy Constructor

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see CtorUnitTest
*/

public class Ctor 
    extends Function
    implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public Ctor()
    {
    	super();
    }

    /**
        Full Parameter Constructor

        @param  signature   The String to be <???>
        @param  parms   The array of Parm to be <???>
        @param  parmlists   The array of ParmList to be <???>
        @param  constructor   The Constructor to be <???>
    */

    public Ctor(Constructor constructor)
    {
        super();

        _Constructor = constructor;
        
        setSignature(_Constructor.toString());
        
        Class[] parmTypes = _Constructor.getParameterTypes();
        
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
        Gets the Constructor to be <???>

        <p>The Constructor is (<???> - describe its meaning , when it is used).

        @return Constructor

        @see    setConstructor(Constructor constructor)
    */

    Constructor getConstructor()
    {
        return _Constructor;
    }


    /*
        =========================
                Mutators
        =========================
    */
    
    /**
        Sets the Constructor to be constructor.
        <p>The Constructor is (<???> - describe its meaning , when it is used).

        @param  constructor   The <code>Constructor</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getConstructor()
        @since  JDK 1.1
    */

    public void setConstructor(Constructor constructor)
    {
        _Constructor = constructor;
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
            Ctor    castObject = (Ctor) object;

            if ( _Constructor.equals(castObject._Constructor) == false )
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

        bfr.append(super.toString());

        bfr.append(" ctor.constructor=");
        bfr.append("\"");
        bfr.append(_Constructor);
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
        
        Ctor castObject = (Ctor) object;

        castObject._Constructor = (Constructor) _Constructor;
       
       return(object);
    }


    /**
        Cosntructor represented by the object
    */            

    private Constructor  _Constructor;
}
