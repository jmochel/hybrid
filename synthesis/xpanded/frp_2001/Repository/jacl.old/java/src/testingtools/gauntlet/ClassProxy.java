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

    This code was last modified on $Date: 1999/02/12 18:50:25 $

    $author: Jim Jackl-Mochel $
    $Revision: 1.33 $
*/

package jacl.test;

// Standard Imports

import java.io.Serializable;
import java.lang.Class;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Vector;

// JGL Imports

import com.objectspace.jgl.*;

// JACL Imports

import jacl.util.hash.PearsonsHash;
import jacl.test.ClassProxyCfg;

// Application Imports

/**
    A Proxy class that hides all the wonderful details of class manipulation

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/02/12 18:50:25 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.33 $

    @see ClassProxyUnitTest
    @since JDK 1.1
*/

public class ClassProxy 
    implements Serializable,Cloneable
{

    
    /**
        Empty Constructor

        @since JDK 1.1
    */

    public ClassProxy()
    {
        _Cfg = new ClassProxyCfg();
    }

   /**
        Full Parameter Constructor

        @param  name               The name of the class
        @param  constructorInput   The string that ges in the class constructor

        @throws java.lang.ClassNotFoundException    If the class to be tested cannot be found
        @throws java.lang.NoSuchMethodException     If the class to be tested does not have an appropriate constructor
        @throws java.lang.InstantiationException    If the class to be tested could not be created
        @throws java.lang.reflect.InvocationTargetException  If the constructor of the class to be tested failed
        @throws jacl.test.ProxyInitializationException    
        @throws java.lang.IllegalAccessException    If you don't have permissions to get to the appropriate constructor
        
        @since JDK 1.1
    */

    public ClassProxy(ClassProxyCfg cfg) 
        throws java.lang.ClassNotFoundException, 
            java.lang.NoSuchMethodException,
            jacl.test.ProxyInitializationException,
            java.lang.InstantiationException,
            java.lang.reflect.InvocationTargetException,
            java.lang.IllegalAccessException
    {
        // Call the void constructor to handle the memory allocation
    
        this();

        _Name = cfg.GetClassName();

    	// Initialize the name and metainfo 
        
        _Class = cfg.GetClass();
        
        _Cfg = cfg;
        
    }    
    
    /*
        =====================================
                Reporting
        =====================================
    */

    private void invokationSuccessProlog(int methodNdx, int parmSetNdx, Object returnValue, Method method, Object[] parmList)
    {
        System.out.println("\t" + method);
        
        for (int i = 0; i < parmList.length; i++ )
        {
            System.out.print("\t\t" + parmList[i].getClass());
            System.out.println("\t\t" + parmList[i]);
        }
        
        System.out.println("\tReturn = " + returnValue);
    }
    
    private void invokationSuccessEpilog(Method method, Object[] parmList)
    {
        System.out.println("==================================================");
    }
    
    private void invokationFailureProlog(int methodNdx, int parmSetNdx, Object returnValue, Method method, Object[] parmList, Throwable error)
    {
        System.out.println("Method was invoked and threw an exception ");
        System.out.println("\t" + method);
        
        for (int i = 0; i < parmList.length; i++ )
        {
            if ( parmList[i] == null )
            {
                System.out.print("\t\t No Parameters");
            }
            else 
            {
                System.out.print("\t\t" + parmList[i].getClass());
                System.out.println("\t\t" + parmList[i]);
            }                
        }
        
        System.out.println("\tReturn = " + returnValue);
        System.out.println(error);
    }
    
    private void invokationFailureEpilog(Method method, Object[] parmList, Throwable  error)
    {
        System.out.println("==================================================");
    }
    
    
    
    /*
        =====================================
                Testing Invocations
        =====================================
    */

    public void executeAllShortTours(int tour)
    {
        Vector objectsToBeTested = _Cfg.GetInstantiatedObjects();
        int objectCount = objectsToBeTested.size();
        
        for ( int i = 0; i < objectCount; i++ )
        {
            _Instance = objectsToBeTested.elementAt(i);

            executeShortTour(tour);
        }
    }
    
    
    private void executeShortTour(int tour)
    {
        int            methodNdx;
        Method         currMethod;
        String         currMethodName;
        Vector         currListOfParmLists;
        Object[]       currParmList;

      
        int numberOfMethods = _Cfg.getMethodCount();
    
        System.out.println("Starting Short Tour");

        while ( tour != numberOfMethods )
        {
            methodNdx = tour % numberOfMethods;

            currMethod = (Method) _Cfg.getMethod(methodNdx);
            
            
            int numberOfParmLists = _Cfg.getParmListCount(currMethod);
            
            int parmListNdx = tour % numberOfParmLists;
            
            Object    returnValue = null;
                
            currParmList = (Object[]) _Cfg.getParmList(currMethod,parmListNdx);
            
            if ( currParmList == null )
            {
                throw (new ProxyInitializationException(new String("Unable to find parm list for method")));
            }

            System.out.println("Method(" + methodNdx + ") Parm Set(" + parmListNdx + ") tour (" + tour + ")");

            try 
            {
                
                returnValue = currMethod.invoke(_Instance, currParmList);
                
                invokationSuccessProlog(methodNdx, parmListNdx, returnValue, currMethod, currParmList);
                invokationSuccessEpilog(currMethod, currParmList);
            }
            catch(java.lang.reflect.InvocationTargetException invocationTargetError)
            {
                invokationFailureProlog(methodNdx, parmListNdx, returnValue, currMethod, currParmList, invocationTargetError.getTargetException());
                invokationFailureEpilog(currMethod, currParmList, invocationTargetError.getTargetException());
            }
            catch(java.lang.IllegalArgumentException illegalArgumentError)
            {
                invokationFailureProlog(methodNdx, parmListNdx, returnValue, currMethod, currParmList, illegalArgumentError);
                invokationFailureEpilog(currMethod, currParmList, illegalArgumentError);
            }
            catch(java.lang.IllegalAccessException illegalAccessError)
            {
                throw (new ProxyInitializationException(new String("4")));
            }
            finally
            {
            }

            tour--;
            
        }
    }
    
    /*
        =====================================
                java.lang.Object  Overrides
        =====================================
    */
    

    /**
        Generates hash codes

        @since JDK 1.1
    */

    public int hashCode()
    {
        return(PearsonsHash.generateIntRangeHash(toString()));
    }


    /**
        Generates a string representation of the class

        @since JDK 1.1
    */

    public String toString()
    {
        StringBuffer    bfr = new StringBuffer(this.getClass().getName());

        bfr.append("Name = " + _Name + "\n" );
        bfr.append("Class = " + _Class + "\n" );

        return(bfr.toString());
    }

    /**
        Clones this object

        <p><B>Caveat Emptor: This clone currently does a shallow copy</B>

        @since JDK 1.1
    */

    public Object clone() throws CloneNotSupportedException
    {
        Object object = null;

        object = super.clone();
        
        ClassProxy castObject = (ClassProxy) object;

        castObject._Name = (String) _Name;
        castObject._Class = (Class) _Class;
        castObject._Cfg = (ClassProxyCfg) _Cfg;
       
        return(object);
    }
    
    /**
        Name of the class
    */            

    private String  _Name;
    
    /**
        Constructor input for the class constructor
    */            

    private String  _ConstructorInput;
    
    /**
        Exemplar Handle of the class
    */            

    private Object  _Instance;

    /**
        Exemplar Handle of the class
    */            

    private Class  _Class;
    
    /**
        Configuration information for Class Proxy
    */            
 
    private ClassProxyCfg    _Cfg;
}
