/*
    Contains Parser

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

*/

package jacl.util.commandline;

// Standard Imports

import java.util.*;
import java.lang.reflect.*;

// Other Imports

// JACL Imports

import jacl.util.commandline.*;

/**
    Configurable Validating Command Line Parser 

    <p>Parser is a parser that can be configured to expect a specified set 
    of arguments. In addition it is run-time configurable for the syntax to be used.
    
    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.8 $

    @see Lexer
*/

public class Parser
{
    
    /**
        ArgSemantic is a description of an argument expected on a commandline.
    
        <p>The ArgSemantic contains information about each argument expected by the 
         command line parser. 
    
        @author Jim Jackl-Mochel
        @author $Author: jmochel $
        @version $Revision: 1.8 $
    
        @see Lexer
        @see Parser
     */
    
    public class ArgSemantic
    {
        /**
            Empty Constructor
    
            @see Parser
        */
    
        
         public ArgSemantic(String key, String abbrev, String desc, String type, boolean required)
         {
            _Key = key;
            _Desc = desc;
            _Type = type;
            _Required = required;
            _Abbrev = abbrev;
         }
         
        /**
            Gets the ArgSemantic Key 
        */
        
        public String getKey()
        {
            return(_Key);
        }
         
        /**
            Gets the ArgSemantic Key abbreviation
        */
        
        public String getAbbrev()
        {
            return(_Abbrev);
        }

        /**
            Gets the ArgSemantic Type
        */
        
        public String getType()
        {
            return(_Type);
        }

        /**
            Gets the ArgSemantic Required flag
        */
        
        
        public boolean getRequired()
        {
            return(_Required);
        }


        /**
            Override for the toString method
        */
        
        
        public String toString()
        {
            String str;
            
            if (_Required == true ) 
            {
                str = new String("Mandatory: " + _Key + " // " + _Desc + "\n");
            }
            else {
                str = new String("Optional: " + _Key + " // " + _Desc + "\n");
            }                
            
            return(str);
        }

         /**
             The key (input tag) used for this argument on the command line.
         */
         
         private String    _Key;

         /**
             The key abbreviation (input tag) used for this argument on the command line.
         */
         
         private String    _Abbrev;
        
         /**
             The Description of the argument being asked for.
         */
         
         private String    _Desc;

         /**
             The name of the class type of the argument being asked for.
         */
         
         private String    _Type;
         
         /**
             Flag to indicate that the argument is required.
         */
         
         private boolean  _Required;
    };
 
   
    /**
        Constructor

        <p>The Parser instantiates the given lexer. If the lexer is ""
        than it takes a look at the property jacl.util.commandline.lexer and uses that 
        to determine what Lexer to put in place for commandline parsing. If it is not found,
        it uses the default lexer (SlashEqualsLexer).
        
        <p>The exitOnFailure governs on a parsing failure and displaying the usage information.
    
        @exception jacl.util.commandline.LexerCreationException Thrown when the parser cannot create 
        an instance of the syntax lexer for whatever reason.
        
        @see Lexer
   */

    public Parser(String lexerClassName, boolean exitOnFailure) throws LexerCreationException
    {
        try 
        {
            // If the user of the class does not specify a lexer, use the system property
            
            if ( lexerClassName.equals(new String("")) )
            {
                // Get the classname for the Lexer
            
                lexerClassName = System.getProperty("jacl.util.commandline.lexer");
             
                // If one does not exist , use the default (SlashEqualsLexer)
                 
                if (lexerClassName == null )
                {
                    lexerClassName = new String("jacl.util.commandline.SlashEqualsLexer");
                }
            }
        
            // Initialize the data members
            
            _ExitOnFailure = exitOnFailure;

            _ArgSemantics = new Hashtable();
            _ParsedArguments = new Hashtable();
            _Abbrevs = new Hashtable();

            // Now instantiate the lexer
                         
            _Lexer = (Lexer) (Class.forName(lexerClassName).newInstance());
        }
        catch (ClassNotFoundException lexerNotFoundError)
        {
            throw new LexerCreationException("Unable to find the command line syntax lexer");
        }
        catch (InstantiationException lexerNotInstantiatableError)
        {
            throw new LexerCreationException("Unable to instantiate the command line syntax lexer");
        }
        catch (IllegalAccessException hmmError)
        {
            throw new LexerCreationException("Command line syntax lexer access violation");
        }
    }

   /**
        Constructor

        <p>The Parser instantiates the given lexer. If the lexer is ""
        than it takes a look at the property jacl.util.commandline.lexer and uses that 
        to determine what Lexer to put in place for commandline parsing. If it is not found,
        it uses the default lexer (SlashEqualsLexer).
        
        <p>The Parserxiting on a parsing failure and displaying the usage information.
    
        @exception jacl.util.commandline.LexerCreationException Thrown when the parser cannot create 
        an instance of the syntax lexer for whatever reason.
        
        @see Lexer
   */

    public Parser(String lexerClassName, boolean exitOnFailure, Validation validation) throws LexerCreationException
    {
        this(lexerClassName,exitOnFailure);
        
        _Validation = validation;
    } 
            
    /**
        Gets the Usage of the Parser and its current registered arguments

        @see    Parser#ArgSemantic
    */


    public String getUsage()
    {
        StringBuffer bfr = new StringBuffer("Usage: \n");
        
        Enumeration iter = _ArgSemantics.elements();
        
        while ( iter.hasMoreElements())
        {
            bfr.append(iter.nextElement().toString());
        }        
        
        return(bfr.toString());
    }
    
    /**
        Registers an description of the expected command line argument.

        @param argSemantic A description of the expected command line argument.
        
        @exception java.lang.IllegalArgumentException Indicates that the argSemantic passed in 
        was invalid.
        
        @see    Parser#ArgSemantic
    */

    public void register(ArgSemantic argSemantic) 
    {
        String emptyString = new String("");
        
        // Validate the ArgSemantic.
        
        if (argSemantic.getKey().equals(emptyString))
        {
            throw (new IllegalArgumentException("Parser is being configured with a null argument name"));
        }
        
        if (argSemantic.getType().equals(emptyString))
        {
            throw (new IllegalArgumentException("Parser is being configured with a null argument type\n" + argSemantic ));
        }

        // Verify that the argument's class exists 
        
        try 
        {
            Class.forName(argSemantic.getType());
        }
        catch (ClassNotFoundException classNotFoundError)
        {
            throw (new IllegalArgumentException("Parser is being configured with a argument type that could not be found\n" + argSemantic ));
        }

        // Add the Argument to the list of argument semantics
        
        _ArgSemantics.put(argSemantic.getKey(), argSemantic);
        
        // Up the reuired argument count as necessary
        
        if ( argSemantic.getRequired() == true )
        {            
            _RequiredArgCount++;
        }
        
        // Add to the Abbrev table as necessary
        
        if ( argSemantic.getAbbrev() != null )
        {
            _Abbrevs.put(argSemantic.getAbbrev(), argSemantic.getKey());

            // Check that none of the abbreviations conflict with any of 
            // the normal keys for argument semantics.
            
            Enumeration abbrevIter = _Abbrevs.keys();
            
            while ( abbrevIter.hasMoreElements() )
            {
                if ( _ArgSemantics.get((java.lang.String) abbrevIter.nextElement()) != null )
                {
                    throw (new IllegalArgumentException("Parser is being configured with an abbreviation that matches thename of another parameter" ));
                }
            }
                
            // We now set the Abbrevs table into the Lexer.
            // I know that this generates some redundant calls to 
            // the lexer but this way it gets set when its contents are non-zero
            // rather than just when it is created. This way, if the lexer 
            // has a non-null Abbrevs table it knows that it is populated.
            
            _Lexer.setAbbrevs(_Abbrevs);
        }
    }
    

    /**
        Parses and validates the command line arguments

        @exception java.lang.IllegalArgumentException 
    */
    
    public void parse(String[] args) throws java.lang.IllegalArgumentException, jacl.util.commandline.ParsingException 
    {
        try 
        {
            Enumeration argIter;    // Lexed argument iterator

            // If we have no arguments and there is at least one required argument
            // print the usage out.
            
            if ( (args.length == 0) && (_RequiredArgCount > 0 ) )
            {
                System.out.println(this.getUsage());
            }
            
            // Lex the arguments
            
            _Lexer.lex(args);
            
            // Next we compare the lexed arguments against the 
            // requested argument semantics
                        
            argIter = _Lexer.getIter();
            
            while (argIter.hasMoreElements())
            {
                Lexer.KeyAndValue        keyAndValue = (Lexer.KeyAndValue) argIter.nextElement();
                
                String                   key = keyAndValue.getKey();
                String                   value = keyAndValue.getValue();
                
                Parser.ArgSemantic    argSemantic;
                
                Object                    valueAsAnObject;
                
                // Get the arg semantic that corresponds to the input argument
                
                argSemantic = (Parser.ArgSemantic) _ArgSemantics.get(key);
                
                if ( argSemantic == null ) 
                {
                    throw(new IllegalArgumentException("Unrecognised argument : " + key ));
                }    
                
                try 
                {
                    // Using the String value that came with the argument,
                    // create an object of the expected type.
                    
                    // Get the constructor (for argument type) that takes a String.
                    
                    Class[] parmClass = new Class[1];
                    parmClass[0] = (new String("")).getClass();
                    
                    Constructor constructorTakingAString = Class.forName(argSemantic.getType()).getConstructor(parmClass);

                    // Invoke that constructor with teh value from the argument list.
                    
                    Object[] parmList = new Object[1];
                    parmList[0] = value;
                    
                    valueAsAnObject = constructorTakingAString.newInstance(parmList);
                    
                    // Populate the list of parsed arguments
                    
                    _ParsedArguments.put(argSemantic.getKey(), valueAsAnObject);
                }        
                catch (ClassNotFoundException classNotFoundError)
                {
                    throw(new IllegalArgumentException("Unfindable type " + argSemantic.getType() + " for argument " + argSemantic.getKey() ));
                }
                catch (NoSuchMethodException noSuchMethodError)
                {
                    throw(new IllegalArgumentException("Unable to find a constructor that takes astring for  " + argSemantic.getType() + " for argument " + argSemantic.getKey() ));
                }
                catch (SecurityException securityError)
                {
                    throw(new IllegalArgumentException("Unable get permission to use a constructor that takes astring for  " + argSemantic.getType() + " for argument " + argSemantic.getKey() ));
                }
                catch (InstantiationException argumentTypeNotInstantiatableError)
                {
                    throw(new IllegalArgumentException("Unable to instantiate " + argSemantic.getType() + " for argument " + argSemantic.getKey() ));
                }
                catch (IllegalAccessException accessError)
                {
                    throw(new IllegalArgumentException("Unable to access class " + argSemantic.getType() + " for argument " + argSemantic.getKey() ));
                }
                catch (InvocationTargetException invocationError)
                {
                    throw(new IllegalArgumentException("Unable to invoke constructor for class " + argSemantic.getType() + " for argument " + argSemantic.getKey() ));
                }
            }

            // Validates the parsed arguments
            
            if ( _Validation != null )
            {
                _Validation.IsSemanticallyValid(_ParsedArguments);
            }
        }
        catch (IllegalArgumentException illegalArgumentError)
        {
            System.out.println(illegalArgumentError);
            System.out.println("\n");
            System.out.println(getUsage());
            
            if (_ExitOnFailure == true )
            {
                System.exit(1);
            }
        }
    }
    
    /**
        Gets the named parameter if it exists

        @returns null if no argument available
    */
    
    public Object getParsedArgument(String key)
    {
        return(_ParsedArguments.get(key));
    }    

    /*
        Java Standard Auxillary methods
    */
    
    public String toString()
    {
        StringBuffer bfr = new StringBuffer(super.toString());

        return bfr.toString();
    }
    
    /*
        Jacl Standard Auxillary methods
    */
    
    public boolean isValid()
    {
        if ( _Lexer.isValid() == false )
        {
            return false;
        }
        
        return true;
    }
     
    /**
        The number of required arguments
    */            

    protected int  _RequiredArgCount;
    
    /**
        A Command Line lexer for lexing the actual command line.
    */            

    protected Lexer _Lexer;
    
    /**
        Collection of ArgSemantics for the parser
    */            
    
    protected Hashtable    _ArgSemantics;
    
    /**
        Flag to indicate that the parser should exit if the command line fails to parse
    */            
    
    protected boolean    _ExitOnFailure;

    /**
        Collection of Parsed 
    */            
    
    protected Hashtable    _ParsedArguments;   
    
    /**
        A validation functor for validating the parsed arguments.
    */            

    protected Validation _Validation;

    /**
        Collection of Abbreviations for the keys in the arg semantics
    */            
    
    protected Hashtable    _Abbrevs;   
};
