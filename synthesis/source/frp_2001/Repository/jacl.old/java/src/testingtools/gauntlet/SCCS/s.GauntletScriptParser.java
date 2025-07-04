h51166
s 01724/00000/00000
d D 1.1 99/11/17 12:52:36 jmochel 2 1
cC
cK06884
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:52:32 jmochel 1 0
c BitKeeper file e:/jacl/java/src/testingtools/gauntlet/GauntletScriptParser.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45347
cPjava/src/testingtools/gauntlet/GauntletScriptParser.java
cR2f93d7e45cb6ba86
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
 * ANTLR-generated file resulting from grammar GauntletScriptParser.g
 * 
 * Terence Parr, MageLang Institute
 * with John Lilley, Empathy Software
 * ANTLR Version 2.4.0; 1996,1997
 */

    package jacl.test;


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


import java.io.IOException;
import antlr.Tokenizer;
import antlr.TokenBuffer;
import antlr.LLkParser;
import antlr.Token;
import antlr.ParserException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
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

    <p>This code was last modified on $Date: 1999/02/12 18:50:27 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.16 $

    @see Gauntlet
    @since JDK 1.1
*/
public class GauntletScriptParser extends antlr.LLkParser
       implements AlmostJavaTokenTypes
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

        <p>This code was last modified on $Date: 1999/02/12 18:50:27 $

        @author Jim Jackl-Mochel
        @author $Author: jmochel $
        @version $Revision: 1.16 $

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

protected GauntletScriptParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public GauntletScriptParser(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected GauntletScriptParser(Tokenizer lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public GauntletScriptParser(Tokenizer lexer) {
  this(lexer,3);
}

/**
    Compilation Unit: In Java, this is a single file.  This is the start rule for this parser

    @since  JDK 1.1
*/
	public final ClassProxyCfg  compilationUnit(
		Hashtable stdRanges, GauntletScriptParserCfg inCfg
	) throws ParserException, IOException {
		ClassProxyCfg configBlock;
		
		
		configBlock = null;
		
		
		try {      // for error handling
			
			
			{
			classDefinition(inCfg, stdRanges);
			}
			
			configBlock = _Cfg;
			
			match(Token.EOF_TYPE);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
		return configBlock;
	}
	
	public final void classDefinition(
		GauntletScriptParserCfg inCfg, Hashtable stdRanges
	) throws ParserException, IOException {
		
		Token  id = null;
		
			id = null;
			Vector    mods = null;
		
		
		try {      // for error handling
			mods=modifiers();
			match(LITERAL_class);
			id = LT(1);
			match(IDENT);
			
			// Create a temp class proxy
			
			_Cfg = new ClassProxyCfg(id.getText(), stdRanges);
			
			_Cfg.populateMethods();
			
			classBlock(inCfg);
			
			
			match(SEMI);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
	}
	
	public final Vector  modifiers() throws ParserException, IOException {
		Vector vect;
		
		
			vect = new Vector();
			String    mod = null;
		
		
		try {      // for error handling
			{
			_loop29:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					mod=modifier();
					vect.addElement(mod);
				}
				else {
					break _loop29;
				}
				
			} while (true);
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_2);
		}
		return vect;
	}
	
	public final void classBlock(
		GauntletScriptParserCfg inCfg
	) throws ParserException, IOException {
		
		
		try {      // for error handling
			match(LCURLY);
			{
			_loop6:
			do {
				if ((_tokenSet_3.member(LA(1)))) {
					method(inCfg);
				}
				else {
					break _loop6;
				}
				
			} while (true);
			}
			match(RCURLY);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_4);
		}
	}
	
	public final void method(
		GauntletScriptParserCfg inCfg
	) throws ParserException, IOException {
		
		Token  name = null;
		
		Vector parms = null;
		HashMap assignments = null;
		String t = null;
		Vector    mods = null;
		_CurrentMethodsParmDecls = new Hashtable();   
		
		
		try {      // for error handling
			mods=modifiers();
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==IDENT)) {
				t=type();
			}
			else if ((LA(1)==IDENT) && (LA(2)==LPAREN)) {
			}
			else {
				throw new NoViableAltException(LT(1));
			}
			
			}
			name = LT(1);
			match(IDENT);
			match(LPAREN);
			parms=parameterDeclarationList();
			match(RPAREN);
			match(LCURLY);
			assignments=assignmentList();
			match(RCURLY);
			
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
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_6);
		}
	}
	
	public final String  type() throws ParserException, IOException {
		String type;
		
		Token  id = null;
		
			type = null;
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_void:
			case LITERAL_boolean:
			case LITERAL_byte:
			case LITERAL_char:
			case LITERAL_short:
			case LITERAL_int:
			case LITERAL_float:
			case LITERAL_long:
			case LITERAL_double:
			{
				type=builtInType();
				break;
			}
			case IDENT:
			{
				id = LT(1);
				match(IDENT);
				type = new String(id.getText());
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_7);
		}
		return type;
	}
	
	public final Vector  parameterDeclarationList() throws ParserException, IOException {
		Vector parmDeclList;
		
		
		parmDeclList = new Vector(); 
		ParmDecl    pd = null;
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case IDENT:
			case LITERAL_final:
			case LITERAL_void:
			case LITERAL_boolean:
			case LITERAL_byte:
			case LITERAL_char:
			case LITERAL_short:
			case LITERAL_int:
			case LITERAL_float:
			case LITERAL_long:
			case LITERAL_double:
			{
				pd=parameterDeclaration();
				parmDeclList.addElement(pd);
				{
				_loop12:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						pd=parameterDeclaration();
						parmDeclList.addElement(pd);
					}
					else {
						break _loop12;
					}
					
				} while (true);
				}
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_8);
		}
		return parmDeclList;
	}
	
	public final HashMap  assignmentList() throws ParserException, IOException {
		HashMap assignments;
		
		
		assignments = new HashMap(true);
		ParmDecl    a = null;
		
		
		try {      // for error handling
			{
			_loop18:
			do {
				if ((_tokenSet_5.member(LA(1)))) {
					a=assignment();
					
					System.out.println(a);
							    assignments.add(a._ID, a);
				}
				else {
					break _loop18;
				}
				
			} while (true);
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_9);
		}
		return assignments;
	}
	
	public final ParmDecl  parameterDeclaration() throws ParserException, IOException {
		ParmDecl decl;
		
		Token  id = null;
		
			decl = null; 
		String spec = null;
		
		
		try {      // for error handling
			parameterModifier();
			spec=typeSpec();
			id = LT(1);
			match(IDENT);
			
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
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_10);
		}
		return decl;
	}
	
	public final void parameterModifier() throws ParserException, IOException {
		
		Token  f = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_final:
			{
				f = LT(1);
				match(LITERAL_final);
				break;
			}
			case IDENT:
			case LITERAL_void:
			case LITERAL_boolean:
			case LITERAL_byte:
			case LITERAL_char:
			case LITERAL_short:
			case LITERAL_int:
			case LITERAL_float:
			case LITERAL_long:
			case LITERAL_double:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
			}
			
			
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_5);
		}
	}
	
	public final String  typeSpec() throws ParserException, IOException {
		String spec;
		
		Token  lb = null;
		
			spec = null;
			
		
		try {      // for error handling
			spec=type();
			{
			_loop24:
			do {
				if ((LA(1)==LBRACK)) {
					lb = LT(1);
					match(LBRACK);
					match(RBRACK);
				}
				else {
					break _loop24;
				}
				
			} while (true);
			}
			spec.concat("[]");
			
					
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_11);
		}
		return spec;
	}
	
	public final ParmDecl  assignment() throws ParserException, IOException {
		ParmDecl decl;
		
		Token  id = null;
		
			decl = null; 
			String value = null;
		String ty = null;
		
		
		try {      // for error handling
			{
			if ((_tokenSet_5.member(LA(1))) && (LA(2)==IDENT)) {
				ty=type();
			}
			else if ((LA(1)==IDENT) && (LA(2)==ASSIGN)) {
			}
			else {
				throw new NoViableAltException(LT(1));
			}
			
			}
			id = LT(1);
			match(IDENT);
			match(ASSIGN);
			value=constant();
			match(SEMI);
			
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
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_12);
		}
		return decl;
	}
	
	public final String  constant() throws ParserException, IOException {
		String s;
		
		Token  i = null;
		Token  c = null;
		Token  l = null;
		Token  f = null;
		
			s = null;
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case NUM_INT:
			{
				i = LT(1);
				match(NUM_INT);
				s = new String(i.getText());
				break;
			}
			case CHAR_LITERAL:
			{
				c = LT(1);
				match(CHAR_LITERAL);
				s = new String(c.getText());
				break;
			}
			case STRING_LITERAL:
			{
				l = LT(1);
				match(STRING_LITERAL);
				s = new String(l.getText());
				break;
			}
			case NUM_FLOAT:
			{
				f = LT(1);
				match(NUM_FLOAT);
				s = new String(f.getText());
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_4);
		}
		return s;
	}
	
	public final String  builtInType() throws ParserException, IOException {
		String type;
		
		
			type = null;
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_void:
			{
				match(LITERAL_void);
				type = new String("void");
				break;
			}
			case LITERAL_boolean:
			{
				match(LITERAL_boolean);
				type = new String("boolean");
				break;
			}
			case LITERAL_byte:
			{
				match(LITERAL_byte);
				type = new String("byte");
				break;
			}
			case LITERAL_char:
			{
				match(LITERAL_char);
				type = new String("char");
				break;
			}
			case LITERAL_short:
			{
				match(LITERAL_short);
				type = new String("short");
				break;
			}
			case LITERAL_int:
			{
				match(LITERAL_int);
				type = new String("int");
				break;
			}
			case LITERAL_float:
			{
				match(LITERAL_float);
				type = new String("float");
				break;
			}
			case LITERAL_long:
			{
				match(LITERAL_long);
				type = new String("long");
				break;
			}
			case LITERAL_double:
			{
				match(LITERAL_double);
				type = new String("double");
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_7);
		}
		return type;
	}
	
	public final String  modifier() throws ParserException, IOException {
		String mod;
		
		
		mod = null;
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_private:
			{
				match(LITERAL_private);
				mod = new String("private");
				break;
			}
			case LITERAL_public:
			{
				match(LITERAL_public);
				mod = new String("public");
				break;
			}
			case LITERAL_protected:
			{
				match(LITERAL_protected);
				mod = new String("protected");
				break;
			}
			case LITERAL_static:
			{
				match(LITERAL_static);
				mod = new String("static");
				break;
			}
			case LITERAL_transient:
			{
				match(LITERAL_transient);
				mod = new String("transient");
				break;
			}
			case LITERAL_final:
			{
				match(LITERAL_final);
				mod = new String("final");
				break;
			}
			case LITERAL_abstract:
			{
				match(LITERAL_abstract);
				mod = new String("abstract");
				break;
			}
			case LITERAL_native:
			{
				match(LITERAL_native);
				mod = new String("native");
				break;
			}
			case LITERAL_threadsafe:
			{
				match(LITERAL_threadsafe);
				mod = new String("threadsafe");
				break;
			}
			case LITERAL_synchronized:
			{
				match(LITERAL_synchronized);
				mod = new String("synchronized");
				break;
			}
			case LITERAL_const:
			{
				match(LITERAL_const);
				mod = new String("const");
				break;
			}
			case LITERAL_exclude:
			{
				match(LITERAL_exclude);
				mod = new String("exclude");
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_13);
		}
		return mod;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"class\"",
		"IDENT",
		"SEMI",
		"LCURLY",
		"RCURLY",
		"LPAREN",
		"RPAREN",
		"COMMA",
		"\"final\"",
		"ASSIGN",
		"NUM_INT",
		"CHAR_LITERAL",
		"STRING_LITERAL",
		"NUM_FLOAT",
		"LBRACK",
		"RBRACK",
		"\"void\"",
		"\"boolean\"",
		"\"byte\"",
		"\"char\"",
		"\"short\"",
		"\"int\"",
		"\"float\"",
		"\"long\"",
		"\"double\"",
		"\"private\"",
		"\"public\"",
		"\"protected\"",
		"\"static\"",
		"\"transient\"",
		"\"abstract\"",
		"\"native\"",
		"\"threadsafe\"",
		"\"synchronized\"",
		"\"const\"",
		"\"exclude\"",
		"COLON",
		"DOT",
		"EQUAL",
		"LNOT",
		"BNOT",
		"NOT_EQUAL",
		"DIV",
		"DIV_ASSIGN",
		"PLUS",
		"PLUS_ASSIGN",
		"INC",
		"STAR",
		"STAR_ASSIGN",
		"MOD",
		"MOD_ASSIGN",
		"SR",
		"SR_ASSIGN",
		"BSR",
		"BSR_ASSIGN",
		"GE",
		"GT",
		"SL",
		"SL_ASSIGN",
		"LE",
		"LT",
		"BXOR",
		"BXOR_ASSIGN",
		"BOR",
		"BOR_ASSIGN",
		"LOR",
		"BAND",
		"BAND_ASSIGN",
		"LAND",
		"WS",
		"SL_COMMENT",
		"ML_COMMENT",
		"ESC",
		"HEX_DIGIT",
		"VOCAB",
		"EXPONENT",
		"FLOAT_SUFFIX"
	};
	
	private static final long _tokenSet_0_data_[] = { 2L, 0L };
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	private static final long _tokenSet_1_data_[] = { 1098974760960L, 0L };
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
	private static final long _tokenSet_2_data_[] = { 535822384L, 0L };
	public static final BitSet _tokenSet_2 = new BitSet(_tokenSet_2_data_);
	private static final long _tokenSet_3_data_[] = { 1099510583328L, 0L };
	public static final BitSet _tokenSet_3 = new BitSet(_tokenSet_3_data_);
	private static final long _tokenSet_4_data_[] = { 64L, 0L };
	public static final BitSet _tokenSet_4 = new BitSet(_tokenSet_4_data_);
	private static final long _tokenSet_5_data_[] = { 535822368L, 0L };
	public static final BitSet _tokenSet_5 = new BitSet(_tokenSet_5_data_);
	private static final long _tokenSet_6_data_[] = { 1099510583584L, 0L };
	public static final BitSet _tokenSet_6 = new BitSet(_tokenSet_6_data_);
	private static final long _tokenSet_7_data_[] = { 262176L, 0L };
	public static final BitSet _tokenSet_7 = new BitSet(_tokenSet_7_data_);
	private static final long _tokenSet_8_data_[] = { 1024L, 0L };
	public static final BitSet _tokenSet_8 = new BitSet(_tokenSet_8_data_);
	private static final long _tokenSet_9_data_[] = { 256L, 0L };
	public static final BitSet _tokenSet_9 = new BitSet(_tokenSet_9_data_);
	private static final long _tokenSet_10_data_[] = { 3072L, 0L };
	public static final BitSet _tokenSet_10 = new BitSet(_tokenSet_10_data_);
	private static final long _tokenSet_11_data_[] = { 32L, 0L };
	public static final BitSet _tokenSet_11 = new BitSet(_tokenSet_11_data_);
	private static final long _tokenSet_12_data_[] = { 535822624L, 0L };
	public static final BitSet _tokenSet_12 = new BitSet(_tokenSet_12_data_);
	private static final long _tokenSet_13_data_[] = { 1099510583344L, 0L };
	public static final BitSet _tokenSet_13 = new BitSet(_tokenSet_13_data_);
	
	}
E 2
I 1
E 1
