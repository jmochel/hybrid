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

    This code was last modified on $Date: 1999/02/19 02:21:10 $

    $author: Jim Jackl-Mochel $
    $Revision: 1.3 $
*/


package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

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

    <p><B>Copyright (c) 1998 Jim Jackl-Mochel</B>
    All rights reserved. See source for license agreement

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/02/19 02:21:10 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see <???>
    @see FunctionUnitTest
    @since JDK 1.1
*/

public 
class Function 
implements Serializable,Cloneable
{
    /**
        Empty Constructor

        @since JDK 1.1

    */

    public Function()
    {
        // Assumes that array will be managed in accessor/mutator 

        _Parms = new Vector();
        // Assumes that array will be managed in accessor/mutator 

        _ParmLists = new Vector();
    }

    /**
        Full Parameter Constructor

        @param  signature   The String to be <???>
        @param  parms   The Parm[] to be <???>
        @param  parmlists   The ParmList[] to be <???>

        @see <???>
        @see <???>
        @since JDK 1.1
    */

    public Function(String signature, Parm[] parms, ParmList[] parmlists)
    {
        this();

        _Signature = signature;
        _Parms.copyInto(parms);
        _ParmLists.copyInto(parmlists);
    }


    /*
        =========================
                Accessors
        =========================
    */

    /**
        Gets the Signature to be <???>

        <p>The Signature is (<???> - describe its meaning , when it is used).

        @return String

        @see    setSignature(String signature)
        @since  JDK 1.1
    */

    String getSignature()
    {
        return _Signature;
    }

    /**
        Gets the Parms to be <???>

        <p>The Parms is (<???> - describe its meaning , when it is used).

        @return Parm[]

        @see    setParms(Parm[] parms)
        @since  JDK 1.1
    */

    Parm[] getParms()
    {

        // Create the temporary array
        
        Parm[] tempArray = new Parm[_Parms.size()];
        
        // Populate it.
        
        for (int i = 0; i < tempArray.length; i++ )
        {
            tempArray[i] = (Parm) _Parms.elementAt(i);
        }
        
        // return it
        
        return tempArray;
    }

    /**
        Gets the Parm at the given index to be <???>
        <p>The Parm is (<???> - describe its meaning , when it is used).

        @return Parm

        @see    setParmAt(int ndx, Parm parm)
        @since  JDK 1.1
    */

    Parm getParmAt(int ndx)
    {
        return (Parm) _Parms.elementAt(ndx);
    }

    /**
        Gets the count of Parms
        <p>The Parms is (<???> - describe its meaning , when it is used).

        @return int count of the number of Parms

        @since  JDK 1.1
    */

    int getParmCnt()
    {
        return _Parms.size();
    }

    /**
        Gets the ParmLists to be <???>

        <p>The ParmLists is (<???> - describe its meaning , when it is used).

        @return ParmList[]

        @see    setParmLists(ParmList[] parmlists)
        @since  JDK 1.1
    */

    ParmList[] getParmLists()
    {

        // Create the temporary array
        
        ParmList[] tempArray = new ParmList[_ParmLists.size()];
        
        // Populate it.
        
        for (int i = 0; i < tempArray.length; i++ )
        {
            tempArray[i] = (ParmList) _ParmLists.elementAt(i);
        }
        
        // return it
        
        return tempArray;
    }

    /**
        Gets the ParmList at the given index to be <???>
        <p>The ParmList is (<???> - describe its meaning , when it is used).

        @return ParmList

        @see    setParmListAt(int ndx, ParmList parmlist)
        @since  JDK 1.1
    */

    ParmList getParmListAt(int ndx)
    {
        return (ParmList) _ParmLists.elementAt(ndx);
    }

    /**
        Gets the count of ParmLists
        <p>The ParmLists is (<???> - describe its meaning , when it is used).

        @return int count of the number of ParmLists

        @since  JDK 1.1
    */

    public int getParmListCnt()
    {
        return _ParmLists.size();
    }



    public int getPossibleValueCnt(int parmNdx)
    {
        Parm    parm = getParmAt(parmNdx);
     
        if ( parm == null )
        {
            throw new IllegalArgumentException("Incorrect parm index requested " + parmNdx);
        }
        
        return parm.getPossibleValueCnt();
    }        


    public Object getPossibleValueForParm(int parmNdx, int valueNdx)
    {
        Parm    parm = getParmAt(parmNdx);
     
        if ( parm == null )
        {
            throw new IllegalArgumentException("Incorrect parm index requested " + parmNdx);
        }
        
        Object obj = parm.getPossibleValueAt(valueNdx);
        
        return obj;
    }        

    /*
        =========================
                Mutators
        =========================
    */
    
    /**
        Sets the Signature to be signature.
        <p>The Signature is (<???> - describe its meaning , when it is used).

        @param  signature   The <code>String</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getSignature()
        @since  JDK 1.1
    */

    public void setSignature(String signature)
    {
        _Signature = signature;
    }



    /**
        Sets the Parms to be parms.
        <p>The Parms is (<???> - describe its meaning , when it is used).

        @param  parms   The <code>Parm[]</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getParms()
        @since  JDK 1.1
    */

    public void setParms(Parm[] parms)
    {
        _Parms.removeAllElements();
        _Parms.copyInto(parms);
    }


    /**
        Sets the Parm at the given index to be parm.
        <p>The Parm is (<???> - describe its meaning , when it is used).

        @param  parm   The <code>Parm</code> to be <???>
        @param  ndx             The index or position of the Parm to be set
        @exception  <???>       (Describe when the exception is thrown).

        @see    getParmAt(int ndx)
        @since  JDK 1.1
    */

    public void setParmAt(int ndx, Parm parm)
    {
        _Parms.insertElementAt(parm, ndx);
    }


    /**
        Adds parm to the set of  Parms.
        <p>The Parm is (<???> - describe its meaning , when it is used).

        @param  parm   The <code>Parm</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getParmAt(int ndx)
        @since  JDK 1.1
    */

    public void addParm(Parm parm)
    {
        _Parms.addElement(parm);
    }


    /**
        Adds parm to the set of  Parms.
        <p>The Parm is (<???> - describe its meaning , when it is used).

        @param  parm   The <code>Parm</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getParmAt(int ndx)
        @since  JDK 1.1
    */

    public void addParm(String name, Class type)
    {
        Parm parm = new Parm(name, type);
        
        _Parms.addElement(parm);
    }

    /**
        Sets the ParmLists to be parmlists.
        <p>The ParmLists is (<???> - describe its meaning , when it is used).

        @param  parmlists   The <code>ParmList[]</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getParmLists()
        @since  JDK 1.1
    */

    public void setParmLists(ParmList[] parmlists)
    {
        _ParmLists.removeAllElements();
        _ParmLists.copyInto(parmlists);
    }


    /**
        Sets the ParmList at the given index to be parmlist.
        <p>The ParmList is (<???> - describe its meaning , when it is used).

        @param  parmlist   The <code>ParmList</code> to be <???>
        @param  ndx             The index or position of the ParmList to be set
        @exception  <???>       (Describe when the exception is thrown).

        @see    getParmListAt(int ndx)
        @since  JDK 1.1
    */

    public void setParmListAt(int ndx, ParmList parmlist)
    {
        _ParmLists.insertElementAt(parmlist, ndx);
    }


    /**
        Adds parmlist to the set of  ParmLists.
        <p>The ParmList is (<???> - describe its meaning , when it is used).

        @param  parmlist   The <code>ParmList</code> to be <???>
        @exception  <???>       (Describe when the exception is thrown).

        @see    getParmListAt(int ndx)
        @since  JDK 1.1
    */

    public void addParmList(ParmList parmlist)
    {
        _ParmLists.addElement(parmlist);
    }



    /*
        =========================
           Other Functionality
        =========================
    */


    public void populateRanges(Hashtable stdRanges)
    {
        // For each parm

        Enumeration parmIter = _Parms.elements();

        while (parmIter.hasMoreElements() == true )
        {
            Parm parm = (Parm) parmIter.nextElement();

            // Get the range for the type 
        
            Vector possibleValues = (Vector) stdRanges.get(parm.getTypeName());
        
            if ( possibleValues == null )
            {
                throw new IllegalArgumentException("Unable to find a range for Parm" + this.toString() );
            }

            // for each possible value.
            
            Enumeration possibleValueIter = possibleValues.elements();
            
            while (possibleValueIter.hasMoreElements() == true )
            {
                parm.addPossibleValue(possibleValueIter.nextElement());
            }
            
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
    
    public void populateParmLists()
    {
        int          parmCount;        // Number of parameters used by the method.
        Object[]     currParmTypes;    // The list of types to be passed into the method.
        ParmList     parmList;         // Array of parameter values
        Vector       listOfParmRanges; // 
        Vector       listOfParmRangeLists; //         
        String       currParmTypeName;     // Name of the type of the current parameter under scrutiny
        Enumeration  possibleValueIter;
        
        // Initialize

        parmCount = _Parms.size();
            
        // Generate the parmlists
        
        // The number of parm lists is equal to the number of possible values each 
        // parameters can take on, raised to the power of the number of arguments.

        try 
        {
            System.out.println("\tPopulating Parm Lists for " + _Signature);
            System.out.println("\tParameter Count : " + _Parms.size());

            if ( parmCount == 0 )
            {
                // Generate the parm list.

                parmList = new ParmList();
                
                // Add an empty parameter 
                
                parmList.addParm(new Object[0]);
                    
                // Put the ParmList in the collection of parm lists.
                    
                _ParmLists.addElement(parmList);

            }
            else if ( parmCount ==  1)
            {
                // Generate the parm list.

                parmList = new ParmList();
                
                // For each possible value of the single parameter create a parm list.
                
                Parm parm = (Parm) _Parms.elementAt(0);
                
                possibleValueIter = parm.getPossibleValues();

                while ( possibleValueIter.hasMoreElements() == true )
                {
                    // Create a new list.
                    
                    parmList = new ParmList();
                    
                    // Put the current value in it.
                    
                    parmList.addParm(possibleValueIter.nextElement());    
                    
                    // Add the Parm List to the set of parm Lists.
                    
                    _ParmLists.addElement(parmList);
                }
            }
            else 
            {
                // Loop through and generate the possible 
                // combinations of parameters
                
                int[]    loopCounters;
                int[]    loopCounterMaxValues;

                loopCounters = new int[parmCount];
                loopCounterMaxValues = new int[parmCount];

                // determine the sum of the possible value counts 

                for ( int i = 0; i < parmCount; i++ )
                {
                    loopCounterMaxValues[i] = getPossibleValueCnt(i);
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

                    // Now populate the list of parameters
                    
                    // Treat loopCounters as a string of indices that dictates which 
                    // value goes into the parameter list
                    // So [0, 0, 1] = [1st value, 1st value, 2nd value] as the parameter list.
                            
                    parmList = new ParmList(parmCount);

                    for ( int l = 0; l < parmCount; l++ )
                    {
                        parmList.addParm(getPossibleValueForParm(l, loopCounters[l]));
                    }

                    _ParmLists.addElement(parmList);
                }
            }
        }
        finally 
        {
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
            Function    castObject = (Function) object;

            if ( _Signature.equals(castObject._Signature) == false )
            {
                return(false);
            }

            if ( _Parms.equals(castObject._Parms) == false )
            {
                return(false);
            }

            if ( _ParmLists.equals(castObject._ParmLists) == false )
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
        StringBuffer    bfr = new StringBuffer();

        bfr.append("(");

        bfr.append(" function.signature=");
        bfr.append("\"");
        bfr.append(_Signature);
        bfr.append("\"");

        // Parms
        
        Enumeration parmIter = _Parms.elements();

        bfr.append(" function.parms=" );
        
        while (parmIter.hasMoreElements() == true )
        {
            bfr.append((Parm) parmIter.nextElement());
        }

        // Parms Lists
        
        Enumeration parmListIter = _ParmLists.elements();

        bfr.append(" function.parmlists=" );
        
        while (parmListIter.hasMoreElements() == true )
        {
            bfr.append((ParmList) parmListIter.nextElement());
        }

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
        
        Function castObject = (Function) object;

        castObject._Signature = (String) _Signature;
        castObject._Parms = (Vector) _Parms.clone();
        castObject._Parms = (Vector) _Parms;
        castObject._ParmLists = (Vector) _ParmLists.clone();
        castObject._ParmLists = (Vector) _ParmLists;
       
       return(object);
    }


    /**
        <???>
    */            

    private String  _Signature;
    

    /**
        <???>
    */            

    private Vector  _Parms;
    

    /**
        <???>
    */            

    private Vector  _ParmLists;
    

}
