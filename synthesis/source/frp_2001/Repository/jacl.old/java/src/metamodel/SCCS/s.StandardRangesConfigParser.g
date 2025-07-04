h54236
s 00413/00000/00000
d D 1.1 99/11/17 12:53:59 jmochel 2 1
cC
cK12058
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:53:55 jmochel 1 0
c BitKeeper file e:/jacl/java/src/metamodel/StandardRangesConfigParser.g
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45355
cPjava/src/metamodel/StandardRangesConfigParser.g
cR2f93d70d5cb6ba86
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
header
{
    package jacl.metamodel;

    import java.util.Vector;
    import java.util.Hashtable;    

    import java.lang.Character;

    import java.lang.reflect.Constructor;    
    import java.lang.reflect.Array;    
}

class StandardRangesConfigParser extends Parser;

/**
    Top level unit of translation
    i.e. the file.

    This returns a Hashtable (keyed on the name of the data type being tracked) of vectors
    of objects that are valid constructed parameters of the given type.
*/

configUnit returns [Hashtable parmValueLists]
{
    // Init

    Object[] entry = null;

    parmValueLists = new Hashtable();

    Vector parmValues = null;

    String oldParmType = new String("");

}
    : ( entry=configEntry 
        {
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
      )+ 
      EOF
      {
            // Plug in the last parsed data

            parmValueLists.put(oldParmType, parmValues);
      }
	;

/**
    A single line entry in the config file

    @returns objs

    objs[0] contains the key or alias associated with the parameter
    objs[1] contains the constructed class
*/

configEntry returns [Object[] keyAndParm]
{
    // Init Action

    keyAndParm = new Object[2];

    String      name = null;   // Name of the class to be instantiated.
    String      key = null;     // The non-canonical name of the class to be instantiated.
    String      input = null;     // The input string for the constructor of the class to be instantiated.
}
	: ( key=className name=canonicalClassName input=ctorInput )
 	{ 

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
	;

/**
    The key used to locate the entry. 
    A data type in and of itself.
*/

className returns [String s]
    {
        // Init
    
        s = null;
    }
	: id:IDENT  ( DOT^ IDENT )* { s = id.getText(); }
	;

/**
    The canonical name of the class to be instantiated using 
    the provided constructor parameter.
*/

canonicalClassName returns [String s]
    {
        // Init
    
        s = null;
    }
	: id:IDENT  ( DOT^ IDENT )* { s = id.getText(); }
	;

/**
    The combined constructor parameter type and 
    input.
*/

ctorInput returns [String input]
    {
        // Init
	
		input = null;
    }
    :   inp:INPUT { 
                    input = inp.getText(); 

                    // Trim the '(' off the front, and the ')' off the back.
                
                    input = inp.getText().substring(1, inp.getText().length()-1);
        } 
    ;


//----------------------------------------------------------------------------
// The Standard Range Config Scanner
//----------------------------------------------------------------------------

class StandardRangesConfigLexer extends Lexer;


// LEFTPAREN 
//     options
//     {
//         paraphrase = "left parenthesis";
//     }
// 	:  '('
// 	;
// 
// RIGHTPAREN 
//     options
//     {
//         paraphrase = "right parenthesis";
//     }
// 	:  ')'
// 	;
// 
// Single-line comments

SLASH_COMMENT
    options
    {
        paraphrase = "a comment";
    }
	:	"//"
		(
            ~('\n'|'\r'))* ('\n'|'\r'('\n')?
        ) 
        { $setType(Token.SKIP);}
	;

// Identifiers

IDENT
    options
    {
        paraphrase = "an identifier";
    }
    :	('a'..'z'|'A'..'Z'|'_'|'$') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$'|'.')*
	;


INPUT
    options
    {
        paraphrase = "constructor input";
    }
	:	( '(' ) ('"' | 'a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$'|'.'|'+'|'-') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$'|'.'|'-'|'+'|'/' | '"' )* ( ')' )
	;


// Whitespace - Ignored 

WS	:	(	' '
		|	'\t'
		|	'\f'
		// handle newlines
		|	(	"\r\n"  // DOS
			)
		)
		{ $setType(Token.SKIP); newline(); }
	;
E 2
I 1
E 1
