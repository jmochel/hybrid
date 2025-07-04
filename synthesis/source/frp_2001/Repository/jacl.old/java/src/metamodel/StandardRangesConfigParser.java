/*
 * ANTLR-generated file resulting from grammar StandardRangesConfigParser.g
 * 
 * Terence Parr, MageLang Institute
 * with John Lilley, Empathy Software
 * ANTLR Version 2.4.0; 1996,1997
 */

    package jacl.metamodel;

    import java.util.Vector;
    import java.util.Hashtable;    

    import java.lang.Character;

    import java.lang.reflect.Constructor;    
    import java.lang.reflect.Array;    

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
public class StandardRangesConfigParser extends antlr.LLkParser
       implements StandardRangesConfigParserTokenTypes
 {

protected StandardRangesConfigParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public StandardRangesConfigParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected StandardRangesConfigParser(Tokenizer lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public StandardRangesConfigParser(Tokenizer lexer) {
  this(lexer,1);
}

/**
    Top level unit of translation
    i.e. the file.

    This returns a Hashtable (keyed on the name of the data type being tracked) of vectors
    of objects that are valid constructed parameters of the given type.
*/
	public final Hashtable  configUnit() throws ParserException, IOException {
		Hashtable parmValueLists;
		
		
		// Init
		
		Object[] entry = null;
		
		parmValueLists = new Hashtable();
		
		Vector parmValues = null;
		
		String oldParmType = new String("");
		
		
		
		try {      // for error handling
			{
			int _cnt3=0;
			_loop3:
			do {
				if ((LA(1)==IDENT)) {
					entry=configEntry();
					
					if ( entry[0].equals(oldParmType) )
					{
					// Just add the vaue to the current list.
					
					parmValues.addElement(entry[1]);
					}
					else {
					
					if ( !oldParmType.equals("") )
					{
					parmValueLists.put(oldParmType, parmValues);
					}
					
					parmValues = new Vector();
					
					parmValues.addElement(entry[1]);
					
					oldParmType = (String) entry[0];
					}
					
				}
				else {
					if ( _cnt3>=1 ) { break _loop3; } else {throw new NoViableAltException(LT(1));}
				}
				
				_cnt3++;
			} while (true);
			}
			match(Token.EOF_TYPE);
			
			// Plug in the last parsed data
			
			parmValueLists.put(oldParmType, parmValues);
			
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
		return parmValueLists;
	}
	
/**
    A single line entry in the config file

    @returns objs

    objs[0] contains the key or alias associated with the parameter
    objs[1] contains the constructed class
*/
	public final Object[]  configEntry() throws ParserException, IOException {
		Object[] keyAndParm;
		
		
		// Init Action
		
		keyAndParm = new Object[2];
		
		String      name = null;   // Name of the class to be instantiated.
		String      key = null;     // The non-canonical name of the class to be instantiated.
		String      input = null;     // The input string for the constructor of the class to be instantiated.
		
		
		try {      // for error handling
			{
			key=className();
			name=canonicalClassName();
			input=ctorInput();
			}
			
			
			/*
			First put the non-canonical classname into the keyAndParm 
			*/
			
			keyAndParm[0] = key;
			
			/* 
			I am using an array here because it seems to be the only usable 
			way to assign primitive data types to objects.
			*/
			
			if ( name.equals("boolean") )
			{
			// Put a primitive boolean into the parms Array
			
			Boolean B = new Boolean(input);
			
			// Create the new object (of type boolean) in the array
			
							Object objs = Array.newInstance(Boolean.TYPE, 1);
			Array.setBoolean(objs, 0, B.booleanValue());
			
			// Transfer the object (of type boolean) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else if ( name.equals("byte") )
			{
			// Put a primitive byte into the parms Array
			
			Byte B = new Byte(input);
			
			// Create the new object (of type byte) in the array
			
							Object objs = Array.newInstance(Byte.TYPE, 1);
						    Array.setByte(objs, 0, B.byteValue());
			
			// Transfer the object (of type byte) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else if ( name.equals("char") )
			{
			// Put a primitive boolean into the parms Array
			
			Character C = new Character(input.charAt(0));
			
			// Create the new object (of type char) in the array
			
							Object objs = Array.newInstance(Character.TYPE, 1);
						    Array.setChar(objs, 0, C.charValue());
			
			// Transfer the object (of type char) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else if ( name.equals("java.lang.Character") )
			{
			// Put a primitive character into the parms Array
			
			Character C = new Character(input.charAt(0));
			
			// Transfer the object (of type char) to the objs array
			
					        keyAndParm[1] = C;
			}
			else if ( name.equals("short") )
			{
			// Put a primitive boolean into the parms Array
			
			Short S = new Short(input);
			
			// Create the new object (of type short) in the array
			
							Object objs = Array.newInstance(Short.TYPE, 1);
						    Array.setShort(objs, 0, S.shortValue());
			
			// Transfer the object (of type short) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else if ( name.equals("int") )
			{
			// Put a primitive boolean into the parms Array
			
			Integer I = new Integer(input);
			
			// Create the new object (of type int) in the array
			
							Object objs = Array.newInstance(Integer.TYPE, 1);
						    Array.setInt(objs, 0, I.intValue());
			
			// Transfer the object (of type int) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else if ( name.equals("float") )
			{
			// Get the parameter for the constructor
			
			Float F = new Float(input);
			
			// Create the new object (of type float) in the array
			
							Object objs = Array.newInstance(Float.TYPE, 1);
						    Array.setFloat(objs, 0, F.floatValue());
			
			// Transfer the object (of type float) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else if ( name.equals("long") )
			{
			// Get the parameter for the constructor
			
			Long L = new Long(input);
			
			// Create the new object (of type long) in the array
			
							Object objs = Array.newInstance(Long.TYPE, 1);
						    Array.setLong(objs, 0, L.longValue());
			
			// Transfer the object (of type float) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else if ( name.equals("double") )
			{
			// Get the parameter for the constructor
			
			Double D = new Double(input);
			
			// Create the new object (of type double) in the array
			
							Object objs = Array.newInstance(Double.TYPE, 1);
						    Array.setDouble(objs, 0, D.doubleValue());
			
			// Transfer the object (of type double) to the objs array
			
					        keyAndParm[1] = Array.get(objs, 0);
			}
			else {
			
			
			try 
			{
			//
			// Get the Class for the class to be instantiated.
			//
			
			Class currClass = Class.forName(name);
			
						//
						// Get the string constructor for the class to be instantiated
						//
						
						Constructor    ctor;
						        Class[]        ctorParmTypes = new Class[1];
						
			ctorParmTypes[0] = Class.forName("java.lang.String");
						
						ctor = currClass.getConstructor(ctorParmTypes);
						
						//
						// Use the constructor to create the new instance of the class
						// 
						
						Object[]    ctorParms = new Object[1];
						
			ctorParms[0] = input;
						    
					    keyAndParm[1] = ctor.newInstance(ctorParms);
			
			if ( keyAndParm[1] == null )
			{
			throw (new ParserException(new String("Standard Ranges Config File Error : Unable create a low value for the the standard class ") + name));
			}
			
			}
			catch (java.lang.ClassNotFoundException classNotFoundError)
			{
			throw (new ParserException(new String("Standard Ranges Config File Error : Unable to find the standard class ") + name));
			}
			catch (java.lang.NoSuchMethodException noSuchMethodError) 
			{
			throw (new ParserException(new String("Standard Ranges Config File Error : Unable to find a string constructor for standard class ") + name));
			}
			catch (java.lang.IllegalAccessException ilegalAccessError)
			{
			throw (new ParserException(new String("Standard Ranges Config File Error : Unable to get access to standard class ") + name));
			}
			catch (java.lang.InstantiationException instantiationError)
			{
			throw (new ParserException(new String("ClassProxy Initialization Error : Unable to create the standard class ") + name));
			}
			catch (java.lang.IllegalArgumentException instantiationError)
			{
			throw (new ParserException(new String("ClassProxy Initialization Error : Bad parmuments where passed to the constrcutor for standard class ") + name));
			}
			catch (java.lang.reflect.InvocationTargetException invocationTargetError)
			{
			throw (new ParserException(new String("ClassProxy Initialization Error : String constructor has thrown an exception for standard class ") + name));
			}
			}
			
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_1);
		}
		return keyAndParm;
	}
	
/**
    The key used to locate the entry. 
    A data type in and of itself.
*/
	public final String  className() throws ParserException, IOException {
		String s;
		
		Token  id = null;
		
		// Init
		
		s = null;
		
		
		try {      // for error handling
			id = LT(1);
			match(IDENT);
			{
			_loop8:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					match(IDENT);
				}
				else {
					break _loop8;
				}
				
			} while (true);
			}
			s = id.getText();
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_2);
		}
		return s;
	}
	
/**
    The canonical name of the class to be instantiated using 
    the provided constructor parameter.
*/
	public final String  canonicalClassName() throws ParserException, IOException {
		String s;
		
		Token  id = null;
		
		// Init
		
		s = null;
		
		
		try {      // for error handling
			id = LT(1);
			match(IDENT);
			{
			_loop11:
			do {
				if ((LA(1)==DOT)) {
					match(DOT);
					match(IDENT);
				}
				else {
					break _loop11;
				}
				
			} while (true);
			}
			s = id.getText();
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_3);
		}
		return s;
	}
	
/**
    The combined constructor parameter type and 
    input.
*/
	public final String  ctorInput() throws ParserException, IOException {
		String input;
		
		Token  inp = null;
		
		// Init
			
				input = null;
		
		
		try {      // for error handling
			inp = LT(1);
			match(INPUT);
			
			input = inp.getText(); 
			
			// Trim the '(' off the front, and the ')' off the back.
			
			input = inp.getText().substring(1, inp.getText().length()-1);
			
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_1);
		}
		return input;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"an identifier",
		"DOT",
		"constructor input",
		"a comment",
		"WS"
	};
	
	private static final long _tokenSet_0_data_[] = { 2L, 0L };
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	private static final long _tokenSet_1_data_[] = { 18L, 0L };
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
	private static final long _tokenSet_2_data_[] = { 16L, 0L };
	public static final BitSet _tokenSet_2 = new BitSet(_tokenSet_2_data_);
	private static final long _tokenSet_3_data_[] = { 64L, 0L };
	public static final BitSet _tokenSet_3 = new BitSet(_tokenSet_3_data_);
	
	}
