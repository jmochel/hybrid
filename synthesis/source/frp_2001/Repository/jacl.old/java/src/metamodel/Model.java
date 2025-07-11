/*
    Copyright (C) 1998-1999 Jim Jackl-Mochel

    Permission is hereby granted, free of charge, to any person obtaining a copy 
    of this software and associated documentation files (the "Software"), to deal in
    the Software without restriction, including without limitation the rights to use, 
    copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
    Software, and to permit persons to whom the Software is furnished to do so, 
    subject to the following conditions:

    The above copyright notice and this permission notice shall be included in 
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
    IMPLIED, INCLUDING BUT NOT LIMITED TOTHE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHOR BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
    ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
    WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

    Except as contained in this notice, the name of the author shall not be used 
    in advertising or otherwise to promote the sale, use or other dealings in this
    Software without specific prior written authorization.

    This code was last modified on $Date: 1999/02/19 02:21:11 $

    $author: Jim Jackl-Mochel $
    $Revision: 1.3 $
*/

package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;

// Other Imports

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

import java.util.Hashtable;
import java.util.Enumeration;

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    Model of a Java class

    This is a model of a Java class that goes beyond java.lang.reflect.Class.
    This model includes the names of parameters as well allowable ranges for 
    those parameters.
   
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

    <p><B>Copyright (c) 1998 Jim Jackl-Mochel</B>
    All rights reserved. See source for license agreement

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/02/19 02:21:11 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see ModelUnitTest
    @since JDK 1.1
*/

public class Model 
    implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public Model()
    {
        // Assumes that array will be managed in accessor/mutator 

        _Methods = new Vector();
        
        // Assumes that array will be managed in accessor/mutator 

        _Ctors = new Vector();
    }

    /**
        Full Parameter Constructor

        @param  className   The name of the class to be modeled.

        @see <???>
        @since JDK 1.1
    */

    public Model(String className)
        throws java.lang.ClassNotFoundException
    {
    	// For memory management
    	
    	this();
    		
        //
    	//    Set the class name
        //
    		
        _ClassName = className;
    		
        //
        // Get the Class object
        //

        _Class = Class.forName(_ClassName);
        
        //
        // Populate Constructors and Methods
        //
        
        populateConstructors();
        populateMethods();
    }


    /*
        =========================
                Accessors
        =========================
    */

    public Enumeration getMethods()
    {
        return _Methods.elements();
    }
    
    public Enumeration getConstructors()
    {
        return _Ctors.elements();
    }

    /**
        Gets the Method at the given index to be <???>
        <p>The Method is (<???> - describe its meaning , when it is used).

        @return Mthd

        @see    setMethodAt(int ndx, Mthd method)
        @since  JDK 1.1
    */

    Mthd getMethodAt(int ndx)
    {
        return (Mthd) _Methods.elementAt(ndx);
    }

    /**
        Gets the count of Methods
        <p>The Methods is (<???> - describe its meaning , when it is used).

        @return int count of the number of Methods

        @since  JDK 1.1
    */

    int getMethodCnt()
    {
        return _Methods.size();
    }

    /**
        Gets the Ctor at the given index to be <???>
        <p>The Ctor is (<???> - describe its meaning , when it is used).

        @return Ctor

        @see    setCtorAt(int ndx, Ctor ctor)
        @since  JDK 1.1
    */

    Ctor getCtorAt(int ndx)
    {
        return (Ctor) _Ctors.elementAt(ndx);
    }

    /**
        Gets the count of Ctors
        <p>The Ctors is (<???> - describe its meaning , when it is used).

        @return int count of the number of Ctors

        @since  JDK 1.1
    */

    int getCtorCnt()
    {
        return _Ctors.size();
    }


    /*
        =========================
                Mutators
        =========================
    */
    
    /**
        Sets the Methods to be methods.
        <p>The Methods is (<???> - describe its meaning , when it is used).

        @param  methods   The <code>Mthd[]</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getMethods()
        @since  JDK 1.1
    */

    public void setMethods(Mthd[] methods)
    {
        _Methods.removeAllElements();
        _Methods.copyInto(methods);
    }


    /**
        Sets the Method at the given index to be method.
        <p>The Method is (<???> - describe its meaning , when it is used).

        @param  method   The <code>Mthd</code> to be <???>
        @param  ndx             The index or position of the Method to be set
        @exception  <???>       (Describe when the exception is thrown).

        @see    getMethodAt(int ndx)
        @since  JDK 1.1
    */

    public void setMethodAt(int ndx, Mthd method)
    {
        _Methods.insertElementAt(method, ndx);
    }


    /**
        Adds method to the set of  Methods.
        <p>The Method is (<???> - describe its meaning , when it is used).

        @param  method   The <code>Mthd</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getMethodAt(int ndx)
        @since  JDK 1.1
    */

    public void addMethod(Mthd method)
    {
        _Methods.addElement(method);
    }



    /**
        Sets the Ctors to be ctors.
        <p>The Ctors is (<???> - describe its meaning , when it is used).

        @param  ctors   The <code>Ctor[]</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getCtors()
        @since  JDK 1.1
    */

    public void setCtors(Ctor[] ctors)
    {
        _Ctors.removeAllElements();
        _Ctors.copyInto(ctors);
    }


    /**
        Sets the Ctor at the given index to be ctor.
        <p>The Ctor is (<???> - describe its meaning , when it is used).

        @param  ctor   The <code>Ctor</code> to be <???>
        @param  ndx             The index or position of the Ctor to be set
        @exception  <???>       (Describe when the exception is thrown).

        @see    getCtorAt(int ndx)
        @since  JDK 1.1
    */

    public void setConstructorAt(int ndx, Ctor ctor)
    {
        _Ctors.insertElementAt(ctor, ndx);
    }


    /**
        Adds ctor to the set of  Ctors.
        <p>The Ctor is (<???> - describe its meaning , when it is used).

        @param  ctor   The <code>Ctor</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getCtorAt(int ndx)
        @since  JDK 1.1
    */

    public void addConstructor(Ctor ctor)
    {
        _Ctors.addElement(ctor);
    }

    /*
        =====================================
                Other Functionality
        =====================================
    */
    
    public void populateConstructors()
    {
        Constructor[] constructors = _Class.getDeclaredConstructors();

        int constructorCount = constructors.length;
        
        for ( int i = 0; i < constructorCount; i++ )
        {
            addConstructor(new Ctor(constructors[i]));
        }
        
    }

	/**
	    Get all the possible Methods and load them
    */
    
	public void populateMethods()
    {
        Method[] methods = _Class.getDeclaredMethods();

        int methodCount = methods.length;
        
        for ( int i = 0; i < methodCount; i++ )
        {
            addMethod(new Mthd(methods[i]));
        }
    }

    public void populateConstructorRanges(Hashtable stdRanges)
    {
        Ctor ctor = null;
        int constructorCount = _Ctors.size();
        
        for ( int i = 0; i < constructorCount; i++ )
        {
            ctor = (Ctor) _Ctors.elementAt(i);

            ctor.populateRanges(stdRanges);
        }
    }


	public void populateMethodRanges(Hashtable stdRanges)
    {
        Mthd mthd = null;
        int mthdCount = _Methods.size();
        
        for ( int i = 0; i < mthdCount; i++ )
        {
            mthd = (Mthd) _Methods.elementAt(i);

            mthd.populateRanges(stdRanges);
        }
    }

    public void populateConstructorParmLists()
    {
        Ctor ctor = null;
        int constructorCount = _Ctors.size();
        
        for ( int i = 0; i < constructorCount; i++ )
        {
            ctor = (Ctor) _Ctors.elementAt(i);

            ctor.populateParmLists();
        }
    }


	public void populateMethodParmLists()
    {
        Mthd mthd = null;
        int mthdCount = _Methods.size();
        
        for ( int i = 0; i < mthdCount; i++ )
        {
            mthd = (Mthd) _Methods.elementAt(i);

            mthd.populateParmLists();
        }
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
            Model    castObject = (Model) object;

            if ( _Methods.equals(castObject._Methods) == false )
            {
                return(false);
            }

            if ( _Ctors.equals(castObject._Ctors) == false )
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
        StringBuffer    bfr = new StringBuffer("\n<class name= ");
        bfr.append(this.getClass().getName());
        bfr.append(">\n");

        // Class name
        
        if ( _ClassName != null )
        {
            bfr.append("\n<classname>\n");
            bfr.append(_ClassName);
            bfr.append("\n</classname>\n");
        }
        
        // Constructors
        
        Enumeration ctorIter = _Ctors.elements();

        bfr.append("\n<ctors>\n" );
        
        while (ctorIter.hasMoreElements() == true )
        {
            bfr.append((Ctor) ctorIter.nextElement());
        }
        
        bfr.append("\n</ctors>\n" );

        // Methods
        
        Enumeration mthdIter = _Methods.elements();

        bfr.append("\n<methods>\n" );
        
        while (mthdIter.hasMoreElements() == true )
        {
            bfr.append((Mthd) mthdIter.nextElement());
        }

        bfr.append("\n</methods>\n" );
        
        bfr.append("</class>");
        
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
        
        Model castObject = (Model) object;

        castObject._Methods = (Vector) _Methods.clone();
        castObject._Methods = (Vector) _Methods;
        castObject._Ctors = (Vector) _Ctors.clone();
        castObject._Ctors = (Vector) _Ctors;
       
       return(object);
    }


    /**
        <???>
    */            

    private Class  _Class;
    

    /**
        <???>
    */            

    private String  _ClassName;

    /**
        <???>
    */            

    private Vector  _Methods;
    

    /**
        <???>
    */            

    private Vector  _Ctors;
    

}
