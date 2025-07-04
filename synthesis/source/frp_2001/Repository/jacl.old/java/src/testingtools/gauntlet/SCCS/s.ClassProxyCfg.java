h65179
s 01252/00000/00000
d D 1.1 99/11/17 12:52:06 jmochel 2 1
cC
cK22406
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:52:03 jmochel 1 0
c BitKeeper file e:/jacl/java/src/testingtools/gauntlet/ClassProxyCfg.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45344
cPjava/src/testingtools/gauntlet/ClassProxyCfg.java
cR2f93d7ec5cb6ba86
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
    $Revision: 1.7 $
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


/**
        A simple rough encapsulation so that class proxy configuration information
        can be passed around as one unit.

        @author Jim Jackl-Mochel
        @author $Author: jmochel $
        @version $Revision: 1.7 $
    
        @see ClassProxy
        @since JDK 1.1
*/

class ClassProxyCfg
{
    
    /**
       Void constructor.
      
       @since JDK 1.1
       @deprecated
    */

    public ClassProxyCfg()
    {
        _MethodNames = new Vector();
        _Methods = new Hashtable();
        _MethodParmRanges = new Hashtable();
        _MethodParmLists = new Hashtable();
        
        _Ctors = new Vector();
        _CtorParmRanges = new Hashtable();
        _CtorParmLists = new Hashtable();

        _InstantiatedObjects = new Vector();
        
        _Class = null;
        _Instances = new Vector();
    	_ClassName = null;
    	_StdRanges = null;
    }

    /**
        Constructor

        @param className The name of the class to be tested.
        @param stdRanges A hashTable mapping a class or primitive type name to default possible ranges.
        
        @throws jacl.util.CfgException When ClassProxyCfg fails to configure.
        
        @since  JDK 1.1
    */

    public ClassProxyCfg(String className, Hashtable stdRanges)
    {
    	// For memory management
    	
    	this();
    		
    	_StdRanges = stdRanges;
    	
        //
    	//    Set the class name
        //
    		
        _ClassName = className;
    		
        //
        // Get the Class object
        //

        try 
        {
            _Class = Class.forName(_ClassName);
        }
        catch (java.lang.ClassNotFoundException classNotFoundError)
        {
            throw( new RuntimeException("Unable to contruct class: " + _ClassName) );
        }
    }

	//
	//
    //    Accessors 
	//
	//
	
	/**
	    Gets the name of the class to be tested.
	*/
		
    public String GetClassName()
    {
        return _ClassName;
    }

	/**
	    FGets the class object for the class to be tested.
	*/

    public Class  GetClass()
    {
        return _Class;
    }

	/**
	    Gets the table of default possible ranges.
	*/

    public Hashtable GetStdRanges()
    {
        return _StdRanges;
    }

	/**
	    Gets the set of (pre-selected) contructors for the class to be tested.
	*/

    public Vector GetConstructors()
    {
    	return _Ctors;
    }

	/**
	    Gets the set of instantiated objects to be tested.
	*/

    public Vector GetInstantiatedObjects()
    {
    	return _InstantiatedObjects;
    }

	/**
	    Gets the count of methods to be used in testing.
	*/

    public int getMethodCount()
    {
    	return _Methods.size();
    }

	/**
	    Gets the count of the number of parameter lists available for the given method.
	*/

    public int getParmListCount(Method method)
    {
        Vector currListOfParmLists = (Vector) _MethodParmLists.get(method);
            
        if ( currListOfParmLists == null )
        {
            throw (new RuntimeException(new String("Unable to find parm lists for method")));
        }

        return currListOfParmLists.size();
    }


	/**
	    Gets the method for the given index.
	*/

    public Method getMethod(int methodNdx)
    {
        String currMethodName = (String) _MethodNames.elementAt(methodNdx);

        if ( currMethodName == null )
        {
            throw (new RuntimeException(new String("Unable to find method name ")));
        }
        
        Method method = (Method) _Methods.get(currMethodName);
        
        if ( method == null )
        {
            throw (new RuntimeException(new String("Unable to find method ")));
        }
        
        return method;
    }
    
	/**
	    Gets a set of parameters for the given method and index.
	*/
    
    public Object[] getParmList(Method method, int parmListNdx)
    {
        Vector currListOfParmLists = (Vector) _MethodParmLists.get(method);
            
        if ( currListOfParmLists == null )
        {
            throw (new RuntimeException(new String("Unable to find parm lists for method")));
        }

        Object[] currParmList = (Object[]) currListOfParmLists.elementAt(parmListNdx);
            
        if ( currParmList == null )
        {
            throw (new RuntimeException(new String("Unable to find parm list for method")));
        }
        
        return currParmList;
    }
    
	/**
	    Get all the possible Methods and load them into the Cfg object
    */
    
	public void populateMethods()
    {
        Method[] classMethods = _Class.getDeclaredMethods();

        int methodCount = classMethods.length;
        
        for ( int i = 0; i < methodCount; i++ )
        {
            _MethodNames.addElement(classMethods[i].toString());
            _Methods.put(classMethods[i].toString(), classMethods[i]);
        }
    }

	/**
	    For each constructor to be used, populate the sets of parameters.
	    
	    <p>
	    For each constructor to be used in constructing test objects walk 
	    through all the possible permutations of sets of parameters and 
	    populate a list with all of those sets.
	    </p>
	*/
    
    public void populateAllCtorParmLists()
    {
        Constructor ctor = null;
        
        for ( int i = 0; i < _Ctors.size(); i++ )
        {
            ctor = (Constructor) _Ctors.elementAt(i);
            
            populateCtorParmLists(ctor);
        }        
    }


	/**
	    For each method to be used, populate the sets of parameters.
	    
	    <p>
	    For each method to be used in testing the objects walk 
	    through all the possible permutations of sets of parameters and 
	    populate a list with all of those sets.
	    </p>
	*/
    
    public void populateAllMethodParmLists()
    {
        Method method = null;
        
        for ( int i = 0; i < _Methods.size(); i++ )
        {
            String methodName = (String) _MethodNames.elementAt(i);
            
            if ( methodName == null )
            {
                throw (new RuntimeException(new String("Method unavailable")));
            }
            
            method = (Method) _Methods.get(methodName);
            
            populateMethodParmLists(method);
        }        
    }

	/**
	    For all constructors, remove all those that we can't use.
	    
	    <p>
	    For each constructor to be used in constructing test objects walk 
	    through and determine its usability. If we can't use it, discard it.
	    </p>
	*/
    
    public void removeUnsuitableCtors()
    {
        Constructor ctor = null;
        Vector  listOfCtorsToBeRemoved = new Vector();

        // Determine which ctors are to be removed.
        // For each cosntructor
        
        for ( int i = 0; i < _Ctors.size(); i++ )
        {
            // Get the constructor 
            
            ctor = (Constructor) _Ctors.elementAt(i);

            if ( ctor == null )
            {
                throw (new RuntimeException(new String("There are no constructors listed")));
            }

            // If the cosntructor takes parameters, ensure that we have vlues 
            // that we can plug into them.
            
            if ( ctor.getParameterTypes().length != 0 )
            {
                Vector listOfParmRanges = (Vector) _CtorParmRanges.get(ctor);
            
                // If there are no parm ranges declared then the 
                // Constructor needs to be removed.
            
                if ( listOfParmRanges == null )
                {
                    listOfCtorsToBeRemoved.addElement(ctor);
                }
            }                
        }        

        // Remove them 
        
        for ( int i = 0; i < listOfCtorsToBeRemoved.size(); i++ )
        {
            _Ctors.removeElement((Constructor) listOfCtorsToBeRemoved.elementAt(i));
        }        
    }
    
	/**
	    For the given constructor, populate the sets of parameters.
	    
	    <p>
	    For the given constructor walk through all the possible 
	    permutations of sets of parameters and populate a list 
	    with all of those sets.
	    </p>
	    <p>
	    The number of sets of parameters is equal to the number 
	    of possible values each parameter can take on, raised to 
	    the power of the number of parms.
	    </p>
	    
	*/
    
    public void populateCtorParmLists(Constructor ctor)
    {
        int          parmCount;        // Number of parameters used by the ctor.
        String       ctorName;          // The name of the constructor
        Object[]     currParmTypes;    // The list of types to be passed into the ctor.
        Vector       listOfParmLists;  // list Of sets of parameters
        Object[]     parmList;         // Array of parameter values
        Vector       listOfParmRanges; // a vector of objects that represent the possible values a parm can take on.
        Vector       listOfParmRangeLists; // A list of lists Of ParmRanges
        String       currParmTypeName;     // Name of the type of the current parameter under scrutiny
            
        // Generate the parmlists
        // The number of parm lists is equal to the number of possible values each 
        // parameters can take on, raised to the power of the number of parmuments.

        try 
        {
            ctorName = ctor.toString();
            currParmTypes = ctor.getParameterTypes();
            
            parmCount = currParmTypes.length;
            
            System.out.println("\tPopulating Parm Lists for " + ctorName);
            System.out.println("\tParameter Count : " + parmCount);
            
            // If we have no parameters, generate an empty array of Objects
            // This is the legitimate parameter list for a method that takes no parameters 
            
            if ( parmCount == 0 )
            {
                // Generate the list of parm lists
                
                listOfParmLists = new Vector();
                
                // Generate the parm list.

                parmList = new Object[0];
                
                listOfParmLists.addElement(parmList);
                
                // Put the list of lists in the collection
                
                _CtorParmLists.put(ctor.toString(), listOfParmLists);
            }
            else if ( parmCount ==  1)
            {
                // Generate the list of parm lists
                
                listOfParmLists = new Vector();
                
                currParmTypeName = ((Class) currParmTypes[0]).getName();

                // We should already have the parm ranges available to us.
                
                listOfParmRangeLists = (Vector) _CtorParmRanges.get(ctor);

                if ( listOfParmRangeLists == null )
                {
                    throw (new RuntimeException("Cannot find a set of parm ranges for constructor " + ctorName ));
                }
                
                listOfParmRanges = (Vector) listOfParmRangeLists.elementAt(0);

                if ( listOfParmRanges  == null )
                {
                    throw (new RuntimeException("Cannot find a range for standard class " + currParmTypeName ));
                }

                // Generate an parm list for each value that is to be used 
                
                for ( int j = 0; j < listOfParmRanges.size(); j++ )
                {
                    parmList = new Object[parmCount];
                    parmList[0] = listOfParmRanges.elementAt(j);
                    listOfParmLists.addElement(parmList);
                }
                
                // Put the list of lists in the collection
                
                _CtorParmLists.put(ctor, listOfParmLists);
            }
            else 
            {
                int            currParmList;    // Which parmList we are currently populating
                
                // Create the list of lists.

                listOfParmLists = new Vector();

                // We should already have the parm ranges available to us.
                
                listOfParmRangeLists = (Vector) _CtorParmRanges.get(ctor);

                if ( listOfParmRangeLists == null )
                {
                    throw (new RuntimeException("Cannot find a set of parm ranges for constructor " + ctor.toString()));
                }
                
                // Loop through and generate the possible combinations of parameters
                
                int[]    loopCounters;
                int[]    loopCounterMaxValues;

                loopCounters = new int[parmCount];
                loopCounterMaxValues = new int[parmCount];

                // determine the max values 

                for ( int i = 0; i < parmCount; i++ )
                {
                    listOfParmRanges = (Vector) listOfParmRangeLists.elementAt(0);

                    if ( listOfParmRanges  == null )
                    {
                        throw (new RuntimeException("Cannot find a range for constructor " + ctor.toString() + " parameter " + i ));
                    }

                    loopCounterMaxValues[i] = listOfParmRanges.size();
                }
                
                // Treat loopCounters as a string of integers that dictates which 
                // value goes into the parameter list
                // Given [n, m, p] we increment p, if necessary, carrying over to 
                // m and then n. 
                // We then use the values in loopCounters as indices to dictate which parameter goes 
                // into the parameter list.
                
                boolean done = false;
                
                int     farthestLoopCounter = parmCount - 1;

                while (!done)
                {
                    // Increment the farthest most counter 
                    
                    loopCounters[farthestLoopCounter]++;
                   
                    if ( loopCounters[farthestLoopCounter] == loopCounterMaxValues[farthestLoopCounter])
                    {
                        // loop down adding the carry
                        
                        int currNdx = farthestLoopCounter;
                            
                        while (loopCounters[currNdx] == loopCounterMaxValues[currNdx] && currNdx > 0)
                        {
                            loopCounters[currNdx] = 0; // Carry this over to the next.
                            
                            currNdx--;                  // Move over to the left
                            loopCounters[currNdx]++;    // Add the carry
                            
                            if ( currNdx == 0 && loopCounters[0] == loopCounterMaxValues[0] )
                            {
                                done = true;
                                loopCounters[currNdx]--;     // Bring this back down to a value within range.
                            }

                        }
                    }

                    // Populate the list of parameters
                    // Treat loopCounters as a string of indices that dictates which 
                    // value goes into the parameter list
                    // So [0, 0, 1] = [1st value, 1st value, 2nd value] as the parameter list.
                            
                    parmList = new Object[parmCount];

                    for ( int l = 0; l < parmCount; l++ )
                    {
                        listOfParmRanges = (Vector) listOfParmRangeLists.elementAt(0);

                        if ( listOfParmRanges  == null )
                        {
                            throw (new RuntimeException("Cannot find a range for parameter " + l + " of construtor " + ctor.toString()));
                        }

                        parmList[l] = listOfParmRanges.elementAt(loopCounters[l]);
                    }

                    listOfParmLists.addElement(parmList);
                }

                _CtorParmLists.put(ctor, listOfParmLists);
            }
        }
        finally 
        {
        }
    }


	/**
	    For the given method, populate the sets of parameters.
	    
	    <p>
	    For the given method walk through all the possible 
	    permutations of sets of parameters and populate a list 
	    with all of those sets.
	    </p>
	    <p>
	    The number of sets of parameters is equal to the number 
	    of possible values each parameter can take on, raised to 
	    the power of the number of parms.
	    </p>
	    
	*/
    
    public void populateMethodParmLists(Method method)
    {
        int          parmCount;        // Number of parameters used by the method.
        String       methodName;          // The name of the construmethod
        Object[]     currParmTypes;    // The list of types to be passed into the method.
        Vector       listOfParmLists;  // 
        Object[]     parmList;         // Array of parameter values
        Vector       listOfParmRanges; // 
        Vector       listOfParmRangeLists; //         
        String       currParmTypeName;     // Name of the type of the current parameter under scrutiny
            
        // Generate the parmlists
        // The number of parm lists is equal to the number of possible values each 
        // parameters can take on, raised to the power of the number of parmuments.

        try 
        {
            methodName = method.toString();
            currParmTypes = method.getParameterTypes();
            
            parmCount = currParmTypes.length;
            
            System.out.println("\tPopulating Parm Lists for " + methodName);
            System.out.println("\tParameter Count : " + parmCount);
                
            if ( parmCount == 0 )
            {
                // Generate the list of parm lists
                
                listOfParmLists = new Vector();
                
                // Generate the parm list.

                parmList = new Object[0];
                
                listOfParmLists.addElement(parmList);
                
                // Put the list of lists in the collection
                
                System.out.println("\tAdding an empty parmlist for method " + method.toString() );
                
                _MethodParmLists.put(method, listOfParmLists);
            }
            else if ( parmCount ==  1)
            {
                // Generate the list of parm lists
                
                listOfParmLists = new Vector();
                
                currParmTypeName = ((Class) currParmTypes[0]).getName();

                // We should already have the parm ranges available to us.
                
                listOfParmRangeLists = (Vector) _MethodParmRanges.get(method);

                if ( listOfParmRangeLists == null )
                {
                    throw (new RuntimeException("Cannot find a set of parm ranges for construmethod " + methodName ));
                }
                              
                listOfParmRanges = (Vector) listOfParmRangeLists.elementAt(0);

                if ( listOfParmRanges  == null )
                {
                    throw (new RuntimeException("Cannot find a range for standard class " + currParmTypeName ));
                }

                // Generate an parm list for each value that is to be used 
                
                for ( int j = 0; j < listOfParmRanges.size(); j++ )
                {
                    parmList = new Object[parmCount];
                    parmList[0] = listOfParmRanges.elementAt(j);
                    listOfParmLists.addElement(parmList);
                }
                
                // Put the list of lists in the collection
                
                _MethodParmLists.put(method, listOfParmLists);
            }
            else 
            {
                int            currParmList;    // Which parmList we are currently populating
                
                // Create the list of lists.

                listOfParmLists = new Vector();

                // We should already have the parm ranges available to us.
                
                listOfParmRangeLists = (Vector) _MethodParmRanges.get(method);

                if ( listOfParmRangeLists == null )
                {
                    throw (new RuntimeException("Cannot find a set of parm ranges for construmethod " + method.toString()));
                }
                
                // Loop through and generate the possible 
                // combinations of parameters
                
                int[]    loopCounters;
                int[]    loopCounterMaxValues;

                loopCounters = new int[parmCount];
                loopCounterMaxValues = new int[parmCount];

                // determine the max values 

                for ( int i = 0; i < parmCount; i++ )
                {
                    listOfParmRanges = (Vector) listOfParmRangeLists.elementAt(i);

                    if ( listOfParmRanges  == null )
                    {
                        throw (new RuntimeException("Cannot find a range for method " + method.toString() + " parameter " + i ));
                    }

                    loopCounterMaxValues[i] = listOfParmRanges.size();
                }
                
                // Treat loopCounters as a string of integers that dictates which 
                // value goes into the parmument list
                // Given [n, m, p] we increment p, if necessary, carrying over to 
                // m and then n. 
                // We then use the values in loopCounters to dictate which parameter goes 
                // into the parameter list.
                
                boolean done = false;
                
                int     farthestLoopCounter = parmCount - 1;

                while (!done)
                {
                    // Increment the farthest most counter 
                    
                    loopCounters[farthestLoopCounter]++;
                   
                    if ( loopCounters[farthestLoopCounter] == loopCounterMaxValues[farthestLoopCounter])
                    {
                        // loop down adding the carry
                        
                        int currNdx = farthestLoopCounter;
                            
                        while (loopCounters[currNdx] == loopCounterMaxValues[currNdx] && currNdx > 0)
                        {
                            loopCounters[currNdx] = 0; // Carry this over to the next.
                            
                            currNdx--;                  // Move over to the left
                            loopCounters[currNdx]++;    // Add the carry
                            
                            if ( currNdx == 0 && loopCounters[0] == loopCounterMaxValues[0] )
                            {
                                done = true;
                                loopCounters[currNdx]--;     // Bring this back down to a value within range.
                            }

                        }
                    }

                    // Populate the list of parameters
                    // Treat loopCounters as a string of indices that dictates which 
                    // value goes into the parameter list
                    // So [0, 0, 1] = [1st value, 1st value, 2nd value] as the parameter list.
                            
                    parmList = new Object[parmCount];

                    for ( int l = 0; l < parmCount; l++ )
                    {
                        listOfParmRanges = (Vector) listOfParmRangeLists.elementAt(l);

                        if ( listOfParmRanges  == null )
                        {
                            throw (new RuntimeException("Cannot find a range for parameter " + l + " of constrcutor " + method.toString()));
                        }

                        parmList[l] = listOfParmRanges.elementAt(loopCounters[l]);
                    }

                    listOfParmLists.addElement(parmList);
                }

                _MethodParmLists.put(method, listOfParmLists);
            }
        }
        finally 
        {
        }
    }



	/**
	    For the given class, create the set of objects to be tested.
	    
	    <p>
	    For the class wlak through all the constructors and all of their 
	    parameter lists and try and create all the objects to be tested.
	    </p>
	    
	*/
    


    public void populateInstantiatedObjects()
    {
        Constructor ctor = null;
        
        for ( int i = 0; i < _Ctors.size(); i++ )
        {
            ctor = (Constructor) _Ctors.elementAt(i);
            
            Vector parmLists = (Vector) _CtorParmLists.get(ctor);
            
            if ( parmLists == null ) 
            {
                throw (new RuntimeException("Unable to find the parm lists for constructor:\n " + ctor.toString()));
            }

            // For each parameter list.
            
            for ( int j = 0; j < parmLists.size(); j++ )
            {
                Object[] parmList = (Object[]) parmLists.elementAt(j);
                
                if ( parmList == null ) 
                {
                    throw (new RuntimeException("Unable to find the " + j + "th parm list for constructor:\n " + ctor.toString()));
                }
                
                instantiateObjectsForCtor(ctor, parmList);
            }
        }        
        
    }

	/**
	    For the given constructor, create the set of objects to be tested.
	    
	*/
    
    public void instantiateObjectsForCtor(Constructor ctor, Object[] parmList)
    {
        Object obj = null; 
        
        
        // Instantiate the object

        try 
        {
            obj = ctor.newInstance(parmList);
        }
        catch(java.lang.reflect.InvocationTargetException invocationTargetError)
        {
            throw (new RuntimeException("Unable to instantiate the test object: Class did not like the input\nConstructor:\n" + ctor.toString() + "\nParameters:\n" + parmList.toString() + "\n" + invocationTargetError.toString()));
        }
        catch(java.lang.InstantiationException instantiationError)
        {
            throw (new RuntimeException("Unable to instantiate the test object: Object is abstract or an interface\nConstructor:\n" + ctor.toString() + "\nParameters:\n" + parmList.toString() + "\n"));
        }
        catch(java.lang.IllegalAccessException illegalAccessError)
        {
            throw (new RuntimeException("Unable to instantiate the test object: Gauntlet doesn't have the access rights\nConstructor:\n" + ctor.toString() + "\nParameters:\n" + parmList.toString() + "\n"));
        }
        
        _InstantiatedObjects.addElement(obj);
    }
    
	/**
	    Remove the given method and attendant information from the Cfg object
	*/
	
    public void removeMethod(String methodName, Class[] parmClassList)
    {
    	try
        {
            // Get the method for the class
                
            Method method = _Class.getMethod(methodName, parmClassList);
                
            // Remove all occurrences of the method 

            int ndx = _MethodNames.indexOf(method.toString());
                
            // If the method is not currently in the list of methods, something is wrong.
            
            _MethodNames.removeElementAt(ndx);
            _Methods.remove(method.toString());
            _MethodParmRanges.remove(method);
        }
        catch (java.lang.SecurityException securityError)
        {
            throw (new RuntimeException("Not allowed access to information on string constructor" + _ClassName));
        }
        catch (java.lang.NoSuchMethodException NoSuchMethodException )
        {
             throw (new RuntimeException("Class does not have string constructor: " + _ClassName));
        }
    }

	/**
	    Add a constructor to the Cfg object
	*/
	
	public void addCtor(Class[] parmClassList)
    {
        try 
        {
            Constructor ctor = _Class.getConstructor(parmClassList);
    
            // Put the constructor into the Constructor list.
    
            _Ctors.addElement(ctor);
        }
        catch (java.lang.SecurityException securityError)
        {
            throw (new RuntimeException("Not allowed access to information on string constructor" + _ClassName));
        }
        catch (java.lang.NoSuchMethodException NoSuchMethodException )
        {
             throw (new RuntimeException("Class does not have string constructor: " + _ClassName));
        }
    }	
    	
    /**
        Add a value to the list of possible values for a given parameter 
        for a given constructor
    */

    public void addCtorParmValue(Class[] parmClassList, int parmNdx, Object parmValue)
    {
    	// Preconditions
    	
    	if ( parmNdx > (parmClassList.length - 1 ) )
        {
        	throw new IndexOutOfBoundsException("parmNdx is greater than the number of parameters");
        }
    	
        try 
        {
            Vector rangeList = null;
            
        	// Get the constructor 
        	
            Constructor ctor = _Class.getConstructor(parmClassList);
         
        	// See if we have a list of lists of poossible ranges
        	
            Vector rangeLists = (Vector) _CtorParmRanges.get(ctor);

        	if ( rangeLists == null )
            {
            	rangeLists = new Vector();
            
                rangeList = new Vector();
                
                rangeLists.addElement(rangeList);
                
            	_CtorParmRanges.put(ctor, rangeLists);
            }

        	// Get the list of possible values for the given parameter

            if (rangeLists.size() < (parmNdx+1)) 
            {
                rangeList = new Vector();
                rangeLists.addElement(rangeList);
            }

            rangeList = (Vector) rangeLists.elementAt(parmNdx);
        		
        	// Now add the parm onto the list
        	
        	rangeList.addElement(parmValue);
        }
        catch (java.lang.SecurityException securityError)
        {
            throw (new RuntimeException("Not allowed access to information on string constructor" + _ClassName));
        }
        catch (java.lang.NoSuchMethodException NoSuchMethodException )
        {
             throw (new RuntimeException("Class does not have string constructor: " + _ClassName));
        }
    	
    }

   /**
        Add a value to the list of possible values for a given parameter 
        for a given constructor
    */

    public void addMethodParmValue(String methodName, Class[] parmClassList, int parmNdx, Object parmValue)
    {
        // Preconditions
        
        if ( parmNdx > (parmClassList.length - 1 ) )
        {
            throw new IndexOutOfBoundsException("parmNdx is greater than the number of parameters");
        }

        System.out.println("\taddMethodParmValue parm# " + parmNdx  + " of method " + methodName);
        
        try 
        {
        	Vector rangeList = null;
        	
            // Get the method for the class
            
            Method method = _Class.getMethod(methodName, parmClassList);
         
            // See if we have a list of lists of possible ranges
            
            Vector rangeLists = (Vector) _MethodParmRanges.get(method);

            if ( rangeLists == null )
            {
                System.out.println("\t\tCreating a list of lists of parm ranges for parameter " + parmNdx  + " of method " + methodName);
                rangeLists = new Vector(parmClassList.length);
                
                rangeList = new Vector();
                
                rangeLists.addElement(rangeList);
            
                System.out.println("\t\tAdding a list of lists of parm ranges for parameter " + parmNdx  + " of method " + methodName);
                _MethodParmRanges.put(method, rangeLists);
            }

            // Get the list of possible values for the given parameter
            
            if (rangeLists.size() < (parmNdx+1)) 
            {
                System.out.println("\t\t\tCreating list of parm ranges for parameter " + parmNdx  + " of method " + methodName );
                rangeList = new Vector();

                System.out.println("\t\t\tAdding list of parm ranges for parameter " + parmNdx  + " of method " + methodName );                
                rangeLists.addElement(rangeList);
            }

            rangeList = (Vector) rangeLists.elementAt(parmNdx);

            // Now add the parm onto the list
            
            System.out.println("\t\t\t\tAdding parm value " + parmValue + " to list of parm ranges for parameter " + parmNdx  + " of method " + methodName );
            
            rangeList.addElement(parmValue);
        }
        catch (java.lang.SecurityException securityError)
        {
            throw (new RuntimeException("Not allowed access to information on string constructor" + _ClassName));
        }
        catch (java.lang.NoSuchMethodException NoSuchMethodException )
        {
             throw (new RuntimeException("Class does not have string constructor: " + _ClassName));
        }
        
    }

    /**
        Dumps the current description of the ClassProxyCfg to System.out

        @since  JDK 1.1
    */
    
    public void Dump()
    {
        //
        //    Name of the class
        //
        
        System.out.println("Class Name: " + _ClassName);

        //
        //    Class constructors
        //

        String ctorName = null;
        Constructor ctor = null;
        
        for ( int i = 0; i < _Ctors.size(); i++ )
        {
            // Constructor name 
        
            ctor = (Constructor) _Ctors.elementAt(i);
            ctorName = ctor.toString();
            
            System.out.println("Constructor : " + ctorName);
            
            // Possible inputs

            Vector listOfParmRangeLists = (Vector) _CtorParmRanges.get(ctor);

            if (listOfParmRangeLists != null)                 
            {
                for ( int j = 0; j < listOfParmRangeLists.size(); j++ )
                {
                    // For each parameter
                    
                    System.out.println("\tParameter #" + j);
                    
                    Vector parmRange = (Vector) listOfParmRangeLists.elementAt(j);
        
                    for (int k = 0; k < parmRange.size(); k++ )
                    {
                        System.out.println("\t\tValue = " + parmRange.elementAt(k).toString());
                    }
                }
            }
            else 
            {
                System.out.println("\t\tThere are no parameter ranges assigned to parameters for this constructor\n");
            }
            
        }

         //
        //    Class methods
        //

        String methodName = null;
        Method method = null;
        
        for ( int i = 0; i < _MethodNames.size(); i++ )
        {
            methodName = (String) _MethodNames.elementAt(i);
            
            method = (Method)  _Methods.get(methodName);
            
            System.out.println("Method: " + methodName);

            // Possible inputs

            Vector listOfParmRangeLists = (Vector) _MethodParmRanges.get(method);
                
            if ( listOfParmRangeLists != null )
            {
                for ( int j = 0; j < listOfParmRangeLists.size(); j++ )
                {
                    // For each parameter
                    
                    System.out.println("\tParameter #" + j);
                    
                    Vector parmRange = (Vector) listOfParmRangeLists.elementAt(j);
        
                    for (int k = 0; k < parmRange.size(); k++ )
                    {
                        System.out.println("\t\tValue = " + parmRange.elementAt(k).toString());
                    }
                }
            }
        }
    }

   /**
        Collection of Instantiated Objects to be tested
        
        maps object#(index of the vector)->object
   */            

   private  Vector   _InstantiatedObjects;
    
   /**
        Collection of Method Names associated with the class.
        
        maps method#(index of the vector)->methodname
    */            

    private  Vector   _MethodNames;
    
    /**
        Collection of Methods associated with the class.
        
        maps methodname->method 
    */            

    private Hashtable    _Methods;

    
    /**
        Collection of List of valid parameters associated with each parameter of each Method.
        
        maps Method->parameter#->Object[]
        
        Hashtable(Method, Vector(Vector(Object)))
        
    */            
    
    private Hashtable    _MethodParmRanges;

    /**
        Collection of Lists of Argument Sets associated with each Method.
        
        maps method->Object[] 
        
        Hashtable(Method, Vector(Object[]))
    */            
    
    private Hashtable    _MethodParmLists;
    
    
    /**
        Collection of Constructors associated with the class.
        
        maps constructor#->constructor
    */            

    private  Vector   _Ctors;

    /**
        Collection of List of valid parameters associated with 
        each parameter of each Constructor.
        
        maps Constructor->parameter#->Object[] 

        Hashtable(Constructor, Vector(Vector(Object)))
    */            
    
    
    private Hashtable    _CtorParmRanges;
    

    /**
        Collection of Lists of Argument Sets associated with each Constructor.
        
        maps Constructor->Object[][] 
        
        Hashtable(Constructor, Vector(Vector(Object)))

    */            
    
    private Hashtable    _CtorParmLists;
    
    /**
        Name of the class
        
    */            
    
    private String    _ClassName;
    
    /**
        Instantiated classes
    */
        
    private  Class        _Class;
    
    /**
        Instantiated classes to be tested.
    */
        
    private  Vector     _Instances;
	
	/**
        Standard Ranges for some of the parameters
    */
    
    private Hashtable   _StdRanges;
}
E 2
I 1
E 1
