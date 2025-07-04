h48712
s 00136/00000/00000
d D 1.1 99/11/17 12:53:52 jmochel 2 1
cC
cK07466
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:48 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/RecursiveClassDump.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45355
cPjava/src/metamodel/RecursiveClassDump.java
cR2f93d70f5cb6ba86
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
    $Header: h:\\Repository/jacl/java/src/metamodel/RecursiveClassDump.java,v 1.2 1999/07/28 03:56:01 jmochel Exp $

    $Copyright:$
*/


package jacl.metamodel;

// Standard Imports

import java.io.Serializable;
import java.lang.StringBuffer;
import java.lang.Class;
import java.lang.reflect.*;
// Other Imports

// JACL Imports

import jacl.util.hash.PearsonsHash;

// Application Imports

/**
    

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.2 $

    @see TypeUnitTest
*/

public class RecursiveClassDump
    implements Serializable,Cloneable
{
    /**
        Empty Constructor
    */

    public RecursiveClassDump()
    {
        _Class = null;
    }

    /**
        Full Parameter Constructor

        @param  name   The String[] to be <???>

        @see <???>
    */

    public RecursiveClassDump(Object obj)
    {
        this();
        
        _Object = obj;
        
        _Class = _Object.getClass();
    }
    
    
    public String toString()
    {
        return toString(_Object, 0);
    }
    
    public String toString(Object obj, int lvl)
    {
        StringBuffer   bfr = new StringBuffer();
        StringBuffer   indentBfr = new StringBuffer();
        Class           currClass = null;
        Field[]         fields = null;
        Object          currObject = null;

        // Set up
        
        currClass = obj.getClass();
        
        fields = currClass.getDeclaredFields();       
        currObject = obj;
        
        
        // Populate the indent buffer
        
        for ( int i = 0; i < lvl; i++ )
        {
            indentBfr.append("\t");
        }
        
        // Inspect the current object for its fields
        
        for ( int j = 0; j < fields.length; j++ )
        {
            Object memberObj = null;
            Class memberClass = null;
            Field[] memberClassFields = null;
            
            // Get the value of this field into the current object
            
            try 
            {
                memberObj = fields[j].get(currObject);
            }
            catch (IllegalAccessException illegalAccessError)
            {
                throw new RuntimeException("Unable to get the value for field " + fields[j].getName());
            }
        
            // Look at the object if it has fields, pass it on down.
            
            memberClass = memberObj.getClass();
            
            memberClassFields = memberClass.getDeclaredFields();
            
            if (memberClassFields.length != 0 )
            {
                toString(memberObj, lvl+1);
            }
            else 
            {
                bfr.append("\n");
                bfr.append(indentBfr);
                
                bfr.append(memberObj);
            }
        }
   
        return(bfr.toString());
    }
   

    private Class  _Class;
    private Object  _Object;
}
E 2
I 1
E 1
