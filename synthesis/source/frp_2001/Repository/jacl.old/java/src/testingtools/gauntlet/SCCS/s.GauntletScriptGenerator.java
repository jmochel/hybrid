h57000
s 00380/00000/00000
d D 1.1 99/11/17 12:52:24 jmochel 2 1
cC
cK12114
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:52:20 jmochel 1 0
c BitKeeper file e:/jacl/java/src/testingtools/gauntlet/GauntletScriptGenerator.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45346
cPjava/src/testingtools/gauntlet/GauntletScriptGenerator.java
cR2f93d7e75cb6ba86
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
    $author: Jim Jackl-Mochel $
    $Revision: 1.7 $

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

package jacl.test;

// Standard Imports

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.Serializable;

import java.lang.Class;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;


// JGL Imports

import com.objectspace.jgl.*;

// JACL Imports

import jacl.util.hash.PearsonsHash;
import jacl.metamodel.*;


/**
    A Auxillary Class to generate Gauntlet Script Files from the ClassProxyCfg

    <p>This code was originally generated using TM version 36
    <p>This code was last modified on $Date: 1999/02/12 18:50:26 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.7 $

    @see ClassProxyUnitTest
    @since JDK 1.1
*/

public class GauntletScriptGenerator 
    implements Serializable,Cloneable
{

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

    public GauntletScriptGenerator()
    {
    }    
    

    public static void main(String[] args)
    {
        Date        currDateTime = new Date();

        try
        {
            Hashtable     stdRanges = null; 
            PrintStream   originalStdOut = null;
            
            //
            // Parse the command line
            //
            
            GauntletScriptGenCommandLineParser    commandLine = new GauntletScriptGenCommandLineParser();

            commandLine.parse(args);

            //
            // Redirect Output 
            //
            
            File OutputFile = commandLine.getOutputFile();

            if ( OutputFile != null )
            {
                originalStdOut = System.out;
                
                try 
                {
                    PrintStream stdout = new PrintStream(new FileOutputStream(OutputFile));
                    System.setOut(stdout);
                }
                catch(Exception e)
                {
                    // Couldn't open the file.
                    
                    System.out.println("Unable to open output file");
                    System.exit(1);
                }
                
            }

            //
            // Print out start up info
            //
            
            System.out.println("Gauntlet : $Revision: 1.7 $");
            System.out.println("Gauntlet  : Starting - " + currDateTime.toString());

            System.err.println("Gauntlet : $Revision: 1.7 $");
            System.err.println("Gauntlet  : Starting - " + currDateTime.toString());

            if ( OutputFile != null )
            {
                System.err.println("Gauntlet  : Output has been redirected to " + OutputFile.toString());
            }
            
            //
            // Set up configuration defaults
            //

            // Verbosity flag
            
            boolean    verbose;
            
            if (commandLine.getVerbose() == null )
            {
                verbose = false;
            }
            else 
            {
                verbose = commandLine.getVerbose().booleanValue();
            }

            // Script file
            
            File    testingScriptFile = commandLine.getTestingScript();

        	// Class name
        	
        	String    className = commandLine.getClassName();
        	
            //
            // Set up the default ranges for the standard types 
            //

            // Instantiate the standard ranges config file parser
            
            StandardRangesConfigLexer    configLexer = new StandardRangesConfigLexer(new FileInputStream("\\jacl\\java\\src\\testingtools\\gauntlet\\GauntletStandardRanges.cfg"));
            StandardRangesConfigParser    configParser = new StandardRangesConfigParser(configLexer);

            stdRanges = configParser.configUnit();
        
            // Report the standard ranges
            
            if ( verbose == true ) 
            {
                reportStandardRanges(stdRanges);
            }
            
            //
            // Set up the testing configuration
            //
            
            // If the user has not specified a script file, create one for the requested class
            
            // Create a model form the classname
                
            Model model = new Model(className);

            // 
            // Create a temporary file
            	
            FileOutputStream out = new FileOutputStream(testingScriptFile);	


//            out.write(bfr.toString().getBytes());
            	
            out.close();
        	
        	try 
        	{
        	    cfg.populateInstantiatedObjects();
        	}
        	catch (java.lang.RuntimeException runtimeError)
        	{
                System.err.println("The class to be tested threw an exception from its constructor");
                System.err.println(runtimeError);
        	}
        	
            //
            //
            // Create and use the class proxy
            //
            //

            ClassProxy    proxy = new ClassProxy(cfg);
            
            // Report 
                 
            if ( verbose == true ) 
            {
                System.out.println("Gauntlet  : Method and Parameters" );
                System.out.println("===================================================================" );
                // proxy.reportMethodsAndParms();
                System.out.println("===================================================================" );
            }
            
            //
            //
            // Run the short tour
            //
            //
            
            proxy.executeAllShortTours(tour.intValue());
        }
        catch(jacl.util.commandline.LexerCreationException  lexerCreationError)
        {
            System.err.println("Gauntlet could not create the command line lexer");
            System.err.println(lexerCreationError);
        }
        catch(jacl.util.commandline.ParsingException parsingError)
        {
            System.err.println("Gauntlet did not like your command line");
            System.err.println(parsingError);
        }
        catch(java.lang.IllegalAccessException illegalAccessError)
        {
            System.err.println("Gauntlet does not have access to the appropriate constructor");
            System.err.println("If you didn't supply a string via the \"value\" argument ");
            System.err.println("than Gauntlet couldn't access the void constructor. ");
            System.err.println("If you did supply a string, then it failed to access a ");
            System.err.println("string constructor.");
            System.err.println(illegalAccessError);
        }
        catch(java.lang.reflect.InvocationTargetException invocationTargetError)
        {
            System.err.println("Gauntlet the class to be tested threw an exception from its constructor");
            System.err.println(invocationTargetError);
        }
        catch(java.lang.InstantiationException instantiationError)
        {
            System.err.println("Gauntlet the class to be tested threw an exception from its constructor");
            System.err.println(instantiationError);
        }
        catch(java.io.IOException IOError)
        {
            System.err.println("Gauntlet could not open the Standard Ranges Config file");
            System.err.println(IOError);
        }
        catch(antlr.ParserException parserError)
        {
            System.err.println("Gauntlet could not parse the Standard Ranges Config file");
            System.err.println(parserError);
        }
        catch (java.lang.ClassNotFoundException classNotFoundError)
        {
            System.err.println("Gauntlet could not find the class to be tested");
            System.err.println(classNotFoundError);
        }
        catch (java.lang.NoSuchMethodException noSuchMethodError)
        {
            System.err.println("The constructor for the class to be tested was not found");
            System.err.println("If you didn't supply a string via the \"/value=\" switch ");
            System.err.println("than Gauntlet couldn't find the void constructor. ");
            System.err.println("If you did supply a string, then it failed to find a ");
            System.err.println("string constructor.");
            System.err.println(noSuchMethodError);
        }
        finally  
        {
            // Add detailed ending information
            // Who, what, when, why, and how.

            currDateTime = new Date();
            
            System.out.println("Gauntlet : $Revision: 1.7 $");
            System.out.println("Gauntlet : Ending - " + currDateTime.toString());
            
            System.err.println("Gauntlet : $Revision: 1.7 $");
            System.err.println("Gauntlet : Ending - " + currDateTime.toString());
             
        }
    }
    
    private static void reportStandardRanges(Hashtable standardRanges)
    {
        System.out.println("Gauntlet : Standard Ranges for parameters ");
        
        Enumeration values = standardRanges.elements();
        for (Enumeration keys = standardRanges.keys() ; keys.hasMoreElements(); ) 
        {
            System.out.println("\t" + (String) keys.nextElement());
            System.out.println("\t\t" + (Vector) values.nextElement());
            System.out.println("");
        }             
    }
   
    
    
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
        
        GauntletScriptGenerator castObject = (GauntletScriptGenerator) object;
       
        return(object);
    }
}
E 2
I 1
E 1
