//
//  Contains the ANTLR 2.4+ grammar for GauntletScriptParser and GauntletScriptLexer
//
//  Copyright : This code is in the public domain.
//
//  This code was last modified on $Date: 1998/12/31 16:20:09 $
//
//  $author: Jim Jackl-Mochel $
//  $Revision: 1.15 $
//

header
{
    package jacl.testing;


    import java.util.Vector;
    import java.util.Hashtable;    

    import java.lang.Character;

    import java.lang.reflect.Constructor;    
    import java.lang.reflect.Method;    
    import java.lang.reflect.Array;    
    
    import java.io.*;
    
    import java.util.Enumeration;
    
    import com.objectspace.jgl.*;

    import jacl.util.hash.*;

}

/**
    A parser for Gauntlet Script files

    <p>It takes gauntlet script files and populates the Gauntlet Cfg Block.

    <p><B>ToDo</B>
    <ul>
        <li>Check API Naming
        <li>Check API Types
        <li>Check API Returns
        <li>Check API Exceptions
        <li>Check API Exception Specifications
        <li>Check Comments
        <li>Remove unused Comment tags
    </ul>

    <p><B>Copyright : This code is in the public domain.</B>

    <p>This code was last modified on $Date: 1998/12/31 16:20:09 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.15 $

    @see Gauntlet
    @since JDK 1.1
*/

class GauntletScriptParser extends Parser;
options 
{
    k = 3;                           // 3 token lookahead
    tokenVocabulary=AlmostJava;      // Call its vocabulary "AlmostJava"
    defaultErrorHandler = true;      // Generate parser error handlers
}
{
    /**
        Parameter Declaration and Assignment

        <p>ParmDecl serves to carry data gathered from either a parameter declaration or
        assignment operation. 

        <p><B>ToDo</B>
        <ul>
            <li>Check API Naming
            <li>Check API Types
            <li>Check API Returns
            <li>Check API Exceptions
            <li>Check API Exception Specifications
            <li>Check Comments
            <li>Remove unused Comment tags
        </ul>

        <p><B>Copyright : This code is in the public domain.</B>

        <p>This code was last modified on $Date: 1998/12/31 16:20:09 $

        @author Jim Jackl-Mochel
        @author $Author: jmochel $
        @version $Revision: 1.15 $

        @since JDK 1.1
    */


    class ParmDecl
    {
        /**
            Constructor

            @param  type  i.e. "java.lang.byte"
            @param  id    Name of the variable
            @param  value Contents of the variable

            @throws java.lang.ClassNotFoundException        
            @throws java.lang.NoSuchMethodException
            @throws java.lang.InstantiationException
            @throws java.lang.reflect.InvocationTargetException
            @throws java.lang.IllegalAccessException

            @since  JDK 1.1
        */

        public ParmDecl(String type, String id, String value)
            throws java.lang.ClassNotFoundException,
                java.lang.NoSuchMethodException,
                java.lang.InstantiationException,
                java.lang.reflect.InvocationTargetException,
                java.lang.IllegalAccessException
        {
            _Type = type;
            _ID = id;
            _Value = value;

            // If we are supplied with a value, we can instantiate 
            // an object of this type and value.
            
            
            // The primitive datatypes are constructed via a roundabout scheme 
            // involving their full class counterparts

            if ( _Type.equals("boolean") )
            {
            	Boolean B;
            	
                if ( _Value != null )
                {
                    B = new Boolean(_Value);
                }
                else {
                    B = new Boolean("0");
                }
        
                // Create the new object in the array
        
                Object objs = Array.newInstance(Boolean.TYPE, 1);
                Array.setBoolean(objs, 0, B.booleanValue());
        
                // Transfer the object (of type boolean) to the instantiated 
    
                _Class = Boolean.TYPE;
                _InstantiatedObject = Array.get(objs, 0);
            }
            else if ( _Type.equals("byte") )
            {
            	Byte B;
            	
                if ( _Value != null )
                {
                    B = new Byte(_Value);
                }
                else 
                {
                    B = new Byte("0");
                }

                // Create the new object in the array
        
                Object objs = Array.newInstance(Byte.TYPE, 1);
                Array.setByte(objs, 0, B.byteValue());
        
                // Transfer the object (of type byte) to the instantiated 
        
                _Class = Byte.TYPE;
                _InstantiatedObject = Array.get(objs, 0);
            }
            else if ( _Type.equals("int") )
            {
            	Integer I;
            	
                if ( _Value != null )
                {
                    I = new Integer(_Value);
                }
                else 
                {
                    I = new Integer("0");
                }

                // Create the new object in the array
        
                Object objs = Array.newInstance(Integer.TYPE, 1);
                Array.setInt(objs, 0, I.intValue());
        
                // Transfer the object to the instantiated 
                
                _Class = Integer.TYPE;
                _InstantiatedObject = Array.get(objs, 0);
            }
            else if ( _Type.equals("char") )
            {
            	Character C;
            	
                if ( _Value != null )
                {
                    C = new Character(_Value.charAt(0));
                }
                else 
                {
                    C = new Character('0');
                }

        
                Object objs = Array.newInstance(Character.TYPE, 1);
                Array.setChar(objs, 0, C.charValue());
    
                // Transfer the object to the objs array

                _Class = Character.TYPE;
                _InstantiatedObject = Array.get(objs, 0);
            }
            else if ( _Type.equals("java.lang.Character") )
            {
            	Character C;
            	
                if ( _Value != null )
                {
                    C = new Character(_Value.charAt(0));
                }
                else 
                {
                    C = new Character('0');
                }


                _Class = Class.forName("java.lang.Character");
                _InstantiatedObject = C;
            }
            else if ( _Type.equals("short") )
            {
            	Short o;
            	
                if ( _Value != null )
                {
                    o = new Short(_Value);
                }
                else 
                {
                    o = new Short("0");
                }

                // Create a new object 
    
                Object objs = Array.newInstance(Short.TYPE, 1);
                Array.setShort(objs, 0, o.shortValue());
    
                // Transfer the object 
                _Class = Short.TYPE;
                _InstantiatedObject = Array.get(objs, 0 );
            }
            else if ( _Type.equals("int") )
            {
            	Integer o;
            	
                if ( _Value != null )
                {
                    o = new Integer(_Value);
                }
                else 
                {
                    o = new Integer("0");
                }

                // Create a new object 
    
                Object objs = Array.newInstance(Integer.TYPE, 1);
                Array.setInt(objs, 0, o.intValue());
    
                // Transfer the object 
                
                _Class = Integer.TYPE;
                _InstantiatedObject = Array.get(objs, 0 );
            }
            else if ( _Type.equals("float") )
            {
            	Float o;
            	
                if ( _Value != null )
                {
                    o = new Float(_Value);
                }
                else 
                {
                    o = new Float("0");
                }

                // Create a new object 
    
                Object objs = Array.newInstance(Float.TYPE, 1);
                Array.setFloat(objs, 0, o.floatValue());
    
                // Transfer the object 
                
                _Class = Float.TYPE;
                _InstantiatedObject = Array.get(objs, 0 );
            }
            else if ( _Type.equals("long") )
            {
            	Long o;
            	
                if ( _Value != null )
                {
                    o = new Long(_Value);
                }
                else 
                {
                    o = new Long("0");
                }

                // Create a new object 
    
                Object objs = Array.newInstance(Long.TYPE, 1);
                Array.setLong(objs, 0, o.longValue());
    
                // Transfer the object 
                
                _Class = Long.TYPE;
                _InstantiatedObject = Array.get(objs, 0 );
            }
            else if ( _Type.equals("double") )
            {
            	Double o;
            	
                if ( _Value != null )
                {
                    o = new Double(_Value);
                }
                else 
                {
                    o = new Double("0");
                }

                // Create a new object 
    
                Object objs = Array.newInstance(Double.TYPE, 1);
                Array.setDouble(objs, 0, o.doubleValue());
    
                // Transfer the object 
                
                _Class = Double.TYPE;
                _InstantiatedObject = Array.get(objs, 0 );
            }
            else 
            {
                Class[]        parmClassList;    // List of classes of arguments
                Object[]       parmList;         // Actual parameter list                    
                Constructor     ctor = null;
                // Get the class 
                
                Class    cl =  Class.forName(_Type);
                _Class = cl;                                    

                // Set up the ParmClassList
                
                parmClassList = new Class[1];

                // If the object is of type java.lang.Object 
                // Don't construct a thing. . We will have to 
                // depend on the fact that someone else will be 
                // providing the needed object, properly constructed.

                if ( _Type.equals("java.lang.Object") == true )
                {

                }
                else
                {
                    parmClassList[0] = Class.forName("java.lang.String");

                    // Get the constructor for the class

                    ctor = cl.getConstructor(parmClassList);
                
                    // Set up the parmList
        
                    parmList = new Object[1];

                    if ( parmClassList[0] == null )
                    {
                        // We have a null constructor
                        parmList[0] = null;
                    }
                    else if ( _Value != null )
                    {
                        parmList[0] = _Value;
                    }
                    else 
                    {
                        parmList[0] = new String("");
                    }
                
                    // Instantiate the object

                    _Class = cl;                
                    _InstantiatedObject = ctor.newInstance(parmList);
                }

            }
        }
        




           
        /**
            Generates a hash value for the object 

            @since  JDK 1.1
        */
    
        public int hashCode()
        {
            return(PearsonsHash.generateIntRangeHash(toString()));
        }

        /**
            Dumps the object to a string

            @since  JDK 1.1
        */

        public String toString()
        {
            StringBuffer bfr = new StringBuffer("ParmDecl [\n");

            
            bfr.append("\tType = " + _Type );
            bfr.append(" ID = " + _ID);
            bfr.append(" ID.hash = " + _ID.hashCode());
            bfr.append(" Value = " + _Value);
            bfr.append(" Class = " + _Class);

            return(bfr.toString());
        }


        /**
            The type of the variable or parameter (i.e. "java.lang.Byte")
        */

        public String    _Type;

        /**
            The type of the variable or parameter (i.e. "counter" or "i")
        */

        public String    _ID;

        /**
            The contens of the variable or parameter (i.e. "1" or "Sample STring")
        */

        public String    _Value;

        /**
            The Class associated with the variable or parameter
        */

        public Class     _Class;

        /**
            An instance of the of the variable or parameter as an object
        */

        public Object    _InstantiatedObject;
    };

/*
    
    private void populateParmLists(Method method, ClassProxyCfg cfg) throws antlr.ParserException
    {
        int          parmCount;        // Number of parameters used by the method.
        String       methodName;       // The name of the method
        Object[]     currParmTypes;    // The list of types to be passed into the method.
        Vector       listOfParmLists;  // 
        Object[]     parmList;             // Array of parmument values
        Vector       standardRangesForType;// 
        String       currParmTypeName;     // Name of the type of the current parameter under scrutiny
            
        // Generate the parmlists
        // The number of parm lists is equal to the number of possible values each 
        // parameters can take on , raised to the power of the number of parmuments.

        try 
        {
            methodName = method.toString();
            currParmTypes = method.getParameterTypes();

            if ( currParmTypes == null )
            {
                throw (new ParserException("Cannot find the parameter types for method: " + methodName));
            }
            
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
                
                _Cfg._MethodParmLists.put(method.toString(), listOfParmLists);

            }
            else if ( parmCount ==  1)
            {
                // Generate the list of parm lists
                
                listOfParmLists = new Vector();
                
                currParmTypeName = ((Class) currParmTypes[0]).getName();

                standardRangesForType = ((Vector) cfg._StdRanges.get(currParmTypeName));

                if ( standardRangesForType == null )
                {
                    throw (new ParserException("Cannot find a range for standard class " + currParmTypeName ));
                }

                // Generate an parm list for each value that is to be used 
                
                for ( int j = 0; j < standardRangesForType.size(); j++ )
                {
                    parmList = new Object[parmCount];
                    parmList[0] = standardRangesForType.elementAt(j);
                    listOfParmLists.addElement(parmList);
                }
                
                // Put the list of lists in the collection
                
                _Cfg._MethodParmLists.put(method.toString(), listOfParmLists);
            }
            else 
            {
                int            currParmList;    // Which parmList we are currently populating
                
                // Create the list of lists.

                listOfParmLists = new Vector();
                
                // Loop through and generate the possible 
                // combinations of parameters
                
                int[]    loopCounters;
                int[]    loopCounterMaxValues;

                loopCounters = new int[parmCount];
                loopCounterMaxValues = new int[parmCount];

                // determine the max values 

                for ( int i = 0; i < parmCount; i++ )
                {
                    currParmTypeName = ((Class) currParmTypes[i]).getName();
                    standardRangesForType = ((Vector) cfg._StdRanges.get(currParmTypeName));

                    if ( standardRangesForType == null )
                    {
                        throw (new ParserException("Cannot find a range for standard class " + currParmTypeName ));
                    }
                    
                    loopCounterMaxValues[i] = standardRangesForType.size();
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
                    // Treat loopCounters as a string of interegrs that dictates which 
                    // value goes into the parmument list
                    // So [0, 0, 1] = [safe, safe, low] as parmuments.
                            
                    parmList = new Object[parmCount];

                    for ( int l = 0; l < parmCount; l++ )
                    {
                        currParmTypeName = ((Class) currParmTypes[l]).getName();
                        standardRangesForType = ((Vector) cfg._StdRanges.get(currParmTypeName));

                        parmList[l] = standardRangesForType.elementAt(loopCounters[l]);
                    }

                    listOfParmLists.addElement(parmList);
                }

                _Cfg._MethodParmLists.put(method.toString(), listOfParmLists);
            }
        }
        finally 
        {
        }
    }
*/    
    /**
        Configuration Block built by running the parser
    */
    
    private ClassProxyCfg     _Cfg;


    private Hashtable                         _CurrentMethodsParmDecls;
}

/**
    Compilation Unit: In Java, this is a single file.  This is the start rule for this parser

    @since  JDK 1.1
*/

compilationUnit[Hashtable stdRanges, GauntletScriptParserCfg inCfg] returns [ClassProxyCfg configBlock] 
    {
        configBlock = null;
    } 
    :
    {
    }

    //
	// Wrapping things up with a class definition
    //

	( classDefinition[inCfg, stdRanges] ) 
    {
        configBlock = _Cfg;
    }

	EOF!
;


//
// A type definition in Gauntlet Script file is basically a class definition.
//

classDefinition[GauntletScriptParserCfg inCfg, Hashtable stdRanges]
    { 
    	id = null;
    	Vector    mods = null;
    }
	:	
    mods = modifiers "class" id:IDENT 
    { 
        // Create a temp class proxy
    
        _Cfg = new ClassProxyCfg(id.getText(), stdRanges);

        _Cfg.populateMethods();
    }

    //
	// now parse the body of the class
    //

	cb:classBlock[inCfg]
	{
    }
    
    // 
    //  Trailing semicolon
    // 

    SEMI
;


//
// This is the body of a class.  You can have methods and 
// That's about it.
//

classBlock[GauntletScriptParserCfg inCfg]
	:	LCURLY!
        ( method[inCfg] ) *
		RCURLY!
	;


//
// This is the complete method.
//


method[GauntletScriptParserCfg inCfg]
    { 
        Vector parms = null;
        HashMap assignments = null;
        String t = null;
        Vector    mods = null;
        _CurrentMethodsParmDecls = new Hashtable();   
    }
    : mods = modifiers (t = type)? name:IDENT LPAREN parms = parameterDeclarationList RPAREN LCURLY assignments = assignmentList RCURLY
    {
        Class[]        parmClassList;    // List of classes of arguments
        Object[]       parmList;         // Actual parameter list


        //
        // First check and exclude methods as indicated.
        //

        int ndx = mods.indexOf("exclude");
    
        if ( ndx != -1 )    // Method is to be excluded
        {
            ParmDecl    parmDecl = null;

            try 
            {
                parmClassList = new Class[parms.size()];
                
                for ( int i = 0; i < parmClassList.length; i++ )
                {
                    parmDecl = (ParmDecl) parms.elementAt(i);
                    
                    parmClassList[i] = parmDecl._Class;
                }

                // Get the method for the class
                
                _Cfg.removeMethod(name.getText(), parmClassList);
            }
            catch (java.lang.SecurityException securityError)
            {
                throw (new ParserException("Not allowed access to information on string constructor" + _Cfg.GetClassName()));
            }
        
            return;     
        }
            
        
        // 
    	//    Check for and process constructors separately
    	//
    	
    	if ( name.getText().equals(_Cfg.GetClassName()) == true) 
        {
        	//
        	//    Construct the constructor
        	//
        	
            Constructor    constructor = null;
           	ParmDecl        parmDecl = null;
            	
            try 
            {
                // Get the constructor for the class

                parmClassList = new Class[parms.size()];
                
                for ( int i = 0; i < parmClassList.length; i++ )
                {
                    parmDecl = (ParmDecl) parms.elementAt(i);
                    
                    parmClassList[i] = parmDecl._Class;
                }
                
                _Cfg.addCtor(parmClassList);
                
                // Now store the Parameter ranges for the constructor
                // These are derived from some assignment statements inside the 
                // body of the constructor.

                // We have as many lists of possible ranges as we have parameters 
      
                for ( int i = 0; i < parms.size(); i++ )
                {
                	// For each parm create a new vector containing all the 
                    // possible ranges for that parameter

                	parmDecl = (ParmDecl) parms.elementAt(i);
                     
                    Pair pair = null;
                    Range range = assignments.equalRange(parmDecl._ID); 

                    // Check that we actually have found some assignments  
                    // for any existing parameter. If we have not there is a mistake.

        			if ( range.begin.atEnd() == true && (((ParmDecl)parms.elementAt(0))._Value != null))
                    {
                        throw(new ParserException("No assignments were found for parameter " + parmDecl._ID + " in constructor " + _Cfg.GetClassName()));
                    }

                    while (range.begin.atEnd() == false)
                    {
                     	pair = (Pair) range.begin.get();
              	     	ParmDecl assignDecl = (ParmDecl) pair.second;
              	     	
              	     	 if ( parmDecl._ID.equals(assignDecl._ID) == true )
              	         {
              	         	_Cfg.addCtorParmValue(parmClassList, i,assignDecl._InstantiatedObject );
              	         }
              	         
              	         range.begin.advance();
                     }
                }
            }
            catch (java.lang.SecurityException securityError)
            {
                throw (new ParserException("Not allowed access to information on string constructor" + _Cfg.GetClassName()));
            }
        }
        else 
        {
                //
                //    Construct the method
                //
                
                Method    method;

                //
                // Make an instance of the class.
                //
                
                ParmDecl    parmDecl = null;
                    
                try 
                {
                    parmClassList = new Class[parms.size()];
                    
                    for ( int i = 0; i < parmClassList.length; i++ )
                    {
                        parmDecl = (ParmDecl) parms.elementAt(i);
                        
                        parmClassList[i] = parmDecl._Class;
                    }
                    
                    
                    // store the Parameter ranges for the method
                   
                    Vector    rangeLists = new Vector(parms.size());
                                        
                    for ( int i = 0; i < parms.size(); i++ )
                    {
                        // For each parm create a new vector containing all the 
                        // possible ranges for that parameter

                        Vector parmRanges = new Vector();
                        parmDecl = (ParmDecl) parms.elementAt(i);
                        
                         Pair pair = null;
                         Range range = assignments.equalRange(parmDecl._ID); 
                         
                        while (range.begin.atEnd() == false)
                        {
                             pair = (Pair) range.begin.get();
                               ParmDecl assignDecl = (ParmDecl) pair.second;
                               
                                if ( parmDecl._ID.equals(assignDecl._ID) == true )
                               {
                                    _Cfg.addMethodParmValue(name.getText(), parmClassList, i,assignDecl._InstantiatedObject );
                               }
                               
                               range.begin.advance();
                         }
                    }
                }
                catch (java.lang.SecurityException securityError)
                {
                    throw (new ParserException("Not allowed access to information on string constructor" + _Cfg.GetClassName()));
                }
            }
        }
    ;



//
// A list of formal parameters
//

parameterDeclarationList returns [Vector parmDeclList]
    { 
      parmDeclList = new Vector(); 
      ParmDecl    pd = null;
    }
    	:	( pd = parameterDeclaration { parmDeclList.addElement(pd); } ( COMMA pd = parameterDeclaration { parmDeclList.addElement(pd); } )*  )? 
	;

//
// A formal parameter.
//

parameterDeclaration returns [ParmDecl decl]
    { 
    	decl = null; 
        String spec = null;
    }
	:	pm:parameterModifier spec = typeSpec id:IDENT 
		{
			try 
		    {
                decl = new ParmDecl(spec, id.getText(), null);
                
                _CurrentMethodsParmDecls.put(id.getText(), decl);
		    }
		    catch (java.lang.ClassNotFoundException classNotFoundError)
		    {
		    	throw(new ParserException("Unable to find the class " + spec));
		    }
            catch (java.lang.NoSuchMethodException  noSuchMethodError)
            {
                throw(new ParserException("Unable to find the String constructor for class " + spec));
            }
            catch (java.lang.InstantiationException instantiationError)
            {
                throw(new ParserException("Unable to invoke the String constructor for class " + spec));
            }
            catch (java.lang.reflect.InvocationTargetException invocationTargetError)
            {
                throw(new ParserException("String constructor failed for class " + spec));
            }
            catch (java.lang.IllegalAccessException illegalAccessError)
            {
                throw(new ParserException("Unable to access the String constructor for class " + spec));
            }
        }
        
	;

//
// A modifier for a parameter
//

parameterModifier
	:	(f:"final")?
		{
        }
	;


assignmentList returns [HashMap assignments]
    { 
        assignments = new HashMap(true);
        ParmDecl    a = null;
    }
	:	( a = assignment { 
            System.out.println(a);
		    assignments.add(a._ID, a); } )* 
	;


assignment returns [ParmDecl decl]
    { 
    	decl = null; 
    	String value = null;
        String ty = null;
    }

    : (ty = type)? id:IDENT ASSIGN value = constant SEMI 
        { 
        	ParmDecl tempDecl = null;
        
	        //
        	// Get the type of the parameter being assigned to
        	// In theory, the parameter has already been declared in the method 
            // parameter list. We should be able to match up the type of the declaration 
            // with the value of the assignment via the name of the parameter.
            //
              
        	tempDecl = (ParmDecl) _CurrentMethodsParmDecls.get(id.getText());
        	
        	if (tempDecl == null )
            {
            	throw(new ParserException("Parameter name in assignment does not match name in method declaration" + id.getText())); 
            }

            //  
            // If the parameter is of type "java.lang.Object" someone has hopefully 
            // provided the correct type in the assignment statement itself.
            //

            if ( tempDecl._Type.equals("java.lang.Object") == true)
            {
                // If the script does not have a corrrect type for this then 
                // kick out an error.

                if ( ty == null )
                {
                    throw(new ParserException("Parameter " + id.getText() + " needs a declared type other than java.lang.Object")); 
                }

                tempDecl._Type = ty;
            }

            try 
            {
           	    decl = new ParmDecl(tempDecl._Type, id.getText(), value);
        	}
            catch (java.lang.ClassNotFoundException classNotFoundError)
            {
                throw(new ParserException("Unable to find the class " + tempDecl._Type));
            }
            catch (java.lang.NoSuchMethodException  noSuchMethodError)
            {
                throw(new ParserException("Unable to find the String constructor for class " + tempDecl._Type));
            }
            catch (java.lang.InstantiationException instantiationError)
            {
                throw(new ParserException("Unable to invoke the String constructor for class " + tempDecl._Type));
            }
            catch (java.lang.reflect.InvocationTargetException invocationTargetError)
            {
                throw(new ParserException("String constructor failed for class " + tempDecl._Type));
            }
            catch (java.lang.IllegalAccessException illegalAccessError)
            {
                throw(new ParserException("Unable to access the String constructor for class " + tempDecl._Type));
            }

        }
    ;

constant returns [String s]
    {
    	s = null;
    }
	:	i:NUM_INT         { s = new String(i.getText()); }
	|	c:CHAR_LITERAL    { s = new String(c.getText()); }
	|	l:STRING_LITERAL   { s = new String(l.getText()); }
	|	f:NUM_FLOAT        { s = new String(f.getText()); }
	;

typeSpec returns [String spec]
    {
    	spec = null;
	}
	:	spec = type (lb:LBRACK^ RBRACK!)* { spec.concat("[]"); }
		{
		}
	;

type returns [String type]
    {
    	type = null;
    }
    : type = builtInType
    | id:IDENT    {type = new String(id.getText()); }
    ;
    
builtInType returns [String type]
    {
    	type = null;
    }
	:	"void"       { type = new String("void"); }
	|	"boolean"    { type = new String("boolean"); }
	|	"byte"       { type = new String("byte"); }
	|	"char"       { type = new String("char"); }
	|	"short"      { type = new String("short"); }
	|	"int"        { type = new String("int"); }
	|	"float"      { type = new String("float"); }
	|	"long"       { type = new String("long"); }
	|	"double"     { type = new String("double"); }
	;


//
// A list of zero or more modifiers.  We could have used (modifier)* in
// place of a call to modifiers, but I thought it was a good idea to keep
// this rule separate so they can easily be collected in a Vector if
// someone so desires
//

modifiers returns [Vector vect]
    {
    	vect = new Vector();
    	String    mod = null;
    }
	:	( mod = modifier { vect.addElement(mod);} )*
	;

//
// modifiers for Java classes, interfaces, class/instance vars and methods
//

    
modifier returns [String mod]
    {
        mod = null;
    }
    : "private"         { mod = new String("private"); }
    | "public"          { mod = new String("public"); }
    | "protected"       { mod = new String("protected"); }
    | "static"          { mod = new String("static"); }
    | "transient"       { mod = new String("transient"); }
    | "final"           { mod = new String("final"); }
    | "abstract"        { mod = new String("abstract"); }
    | "native"          { mod = new String("native"); }
    | "threadsafe"      { mod = new String("threadsafe"); }
    | "synchronized"    { mod = new String("synchronized"); }
    | "const"       { mod = new String("const"); }
    | "exclude"     { mod = new String("exclude"); }
    ;

//----------------------------------------------------------------------------
// The scanner
//----------------------------------------------------------------------------

class GauntletScriptLexer extends Lexer;

options 
{
	tokenVocabulary=AlmostJava; // call the vocabulary "Java"
	testLiterals=false;         // don't automatically test for literals
	k=6;                        // four characters of lookahead
}

//
// OPERATORS
//

LPAREN			:	'('		;
RPAREN			:	')'		;
LBRACK			:	'['		;
RBRACK			:	']'		;
LCURLY			:	'{'		;
RCURLY			:	'}'		;
COLON			:	':'		;
COMMA			:	','		;
DOT 			:	'.'		;
ASSIGN			:	'='		;
EQUAL			:	"=="	;
LNOT			:	'!'		;
BNOT			:	'~'		;
NOT_EQUAL		:	"!="	;
DIV				:	'/'		;
DIV_ASSIGN		:	"/="	;
PLUS			:	'+'		;
PLUS_ASSIGN		:	"+="	;
INC				:	"++"	;
STAR			:	'*'		;
STAR_ASSIGN		:	"*="	;
MOD				:	'%'		;
MOD_ASSIGN		:	"%="	;
SR				:	">>"	;
SR_ASSIGN		:	">>="	;
BSR				:	">>>"	;
BSR_ASSIGN		:	">>>="	;
GE				:	">="	;
GT				:	">"		;
SL				:	"<<"	;
SL_ASSIGN		:	"<<="	;
LE				:	"<="	;
LT				:	'<'		;
BXOR			:	'^'		;
BXOR_ASSIGN		:	"^="	;
BOR				:	'|'		;
BOR_ASSIGN		:	"|="	;
LOR				:	"||"	;
BAND			:	'&'		;
BAND_ASSIGN		:	"&="	;
LAND			:	"&&"	;
SEMI			:	';'		;

//
// An identifier.  Note that testLiterals is set to true!  This means
// that after we match the rule, we look in the literals table to see
// if it's a literal or really an identifer
//

IDENT
	options 
    {
        testLiterals=true;
    }
	:	('a'..'z'|'A'..'Z'|'_'|'$') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$'|'.')*
	;

//
// Whitespace -- ignored
//

WS	:	(	' '
		|	'\t'
		|	'\f'
		// handle newlines
		|	(	"\r\n"  // Evil DOS
			|	'\r'    // Macintosh
			|	'\n'    // Unix (the right way)
			)
			{ newline(); }
		)
		{ _ttype = Token.SKIP; }
	;

//
// Single-line comments
//

SL_COMMENT
	:	"//"
		(~('\n'|'\r'))* ('\n'|'\r'('\n')?)
		{$setType(Token.SKIP); newline();}
	;

//
// multiple-line comments
//

ML_COMMENT
	:	"/*"
		(
			{ LA(2)!='/' }? '*'
		|	'\r' '\n'		{newline();}
		|	'\r'			{newline();}
		|	'\n'			{newline();}
		|	~('*'|'\n'|'\r')
		)*
		"*/"
		{$setType(Token.SKIP);}
	;


// character literals
CHAR_LITERAL
	:	'\'' ( ESC | ~'\'' ) '\''
	;

// string literals
STRING_LITERAL
	:	'"' (ESC|~('"'|'\\'))* '"'
	;


// escape sequence -- note that this is protected; it can only be called
//   from another lexer rule -- it will not ever directly return a token to
//   the parser
// There are various ambiguities hushed in this rule.  The optional
// '0'...'9' digit matches should be matched here rather than letting
// them go back to STRING_LITERAL to be matched.  ANTLR does the
// right thing by matching immediately; hence, it's ok to shut off
// the FOLLOW ambig warnings.
protected
ESC
	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	('u')+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT 
		|	('0'..'3')
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	('0'..'9')
				(	
					options {
						warnWhenFollowAmbig = false;
					}
				:	'0'..'9'
				)?
			)?
		|	('4'..'7')
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	('0'..'9')
			)?
		)
	;


// hexadecimal digit (again, note it's protected!)
protected
HEX_DIGIT
	:	('0'..'9'|'A'..'F'|'a'..'f')
	;


// a dummy rule to force vocabulary to be all characters (except special
//   ones that ANTLR uses internally (0 to 2)
protected
VOCAB
	:	'\3'..'\377'
	;


// a numeric literal
NUM_INT
	{boolean isDecimal=false;}
	:	'.' {_ttype = DOT;}
			(('0'..'9')+ (EXPONENT)? (FLOAT_SUFFIX)? { _ttype = NUM_FLOAT; })?
	|	(	'0' {isDecimal = true;} // special case for just '0'
			(	('x'|'X')
				(											// hex
					// the 'e'|'E' and float suffix stuff look
					// like hex digits, hence the (...)+ doesn't
					// know when to stop: ambig.  ANTLR resolves
					// it correctly by matching immediately.  It
					// is therefor ok to hush warning.
					options {
						warnWhenFollowAmbig=false;
					}
				:	HEX_DIGIT
				)+
			|	('0'..'7')+									// octal
			)?
		|	('-')* ('1'..'9') ('0'..'9')*  {isDecimal=true;}		// non-zero decimal
		)
		(	('l'|'L')
		
		// only check to see if it's a float if looks like decimal so far
		|	{isDecimal}?
			(	('-')* '.' ('0'..'9')* (EXPONENT)? (FLOAT_SUFFIX)?
			|	EXPONENT (FLOAT_SUFFIX)?
			|	FLOAT_SUFFIX
			)
			{ _ttype = NUM_FLOAT; }
		)?
	;


// a couple protected methods to assist in matching floating point numbers
protected
EXPONENT
	:	('e'|'E') ('+'|'-')? ('0'..'9')+
	;


protected
FLOAT_SUFFIX
	:	'f'|'F'|'d'|'D'
	;
