/*
    $author: Jim Jackl-Mochel $
    $Revision: 1.28 $

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

// Package Declaration

package jacl.test;

// Imports

import java.util.Date;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import jacl.util.commandline.ParsingException;

import jacl.test.StandardRangesConfigLexer;
import jacl.test.StandardRangesConfigParser;

import jacl.test.GauntletCommandLineParser;
import jacl.test.GauntletScriptGenerator;

/**
    A Java Class Abuser

    <p><B>ToDo</B>
    <ul>
        <li>Add parameter copying
    </ul>

    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/02/12 18:50:26 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.28 $

    @since JDK 1.1
*/

public class Gauntlet
{
    /**
        Void constructor 
    */

    public Gauntlet()
    {
    }

    /**
        Give the named class a vigorous shakedown.

        @see ClassProxy
        @since JDK 1.1
    */

    public static void main(String[] args)
    {
        Date        currDateTime = new Date();

        try
        {
            Hashtable     stdRanges = null; 
            PrintStream   originalStdOut = null;
            
            //
            //
            // Parse the command line
            //
            //
            
            GauntletCommandLineParser    commandLine = new GauntletCommandLineParser();

            commandLine.parse(args);

            // Redirect Output 
            
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

            // Print out start up info
            
            System.out.println("Gauntlet : $Revision: 1.28 $");
            System.out.println("Gauntlet  : Starting - " + currDateTime.toString());

            System.err.println("Gauntlet : $Revision: 1.28 $");
            System.err.println("Gauntlet  : Starting - " + currDateTime.toString());

            if ( OutputFile != null )
            {
                System.err.println("Gauntlet  : Output has been redirected to " + OutputFile.toString());
            }
            
            //
            //
            // Set up configuration defaults
            //
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
            
            File    testScriptFile = commandLine.getTestingScript();

            // Tour number 
            
            Integer tour = commandLine.getTour();
            
            if (tour == null ) 
            {
                tour = new Integer(500);
            }
            
        	// Class name
        	
        	String    className = commandLine.getClassName();
        	
            //
            //
            // Set up the default ranges for the standard types 
            //
            //

            // Instantiate the standard ranges config file parser
            
            StandardRangesConfigLexer    configLexer = new StandardRangesConfigLexer(new FileInputStream("\\jacl\\java\\src\\testtools\\gauntlet\\GauntletStandardRanges.cfg"));
            StandardRangesConfigParser    configParser = new StandardRangesConfigParser(configLexer);

            stdRanges = configParser.configUnit();
        
            // Report the standard ranges
            
            if ( verbose == true ) 
            {
                reportStandardRanges(stdRanges);
            }
            
            //
            //
            // Set up the test configuration
            //
            //
            
            // If the user has not specified a script file, create one for 
            // the requested class
            
            if ( testScriptFile == null )
            {
                //
                // Create a test script using the className and value from the command line.
                //
                
            	ClassProxyCfg cfg = new ClassProxyCfg(className, stdRanges);

            	GauntletScriptGenerator gtor = new GauntletScriptGenerator(cfg);
            		
                StringBuffer bfr = gtor.generateScript(commandLine.getValue(),true);
            	System.out.print(bfr);
            	
            	//
            	// Create a temporary file
            	//
            	
                FileOutputStream out = new FileOutputStream("Bogus.glt");	

            	out.write(bfr.toString().getBytes());
            	
            	out.close();
            	
                testScriptFile = new File("Bogus.glt");	
            }            	
        	
        	// Instantiate the script file parser
            
            GauntletScriptLexer    scriptLexer = new GauntletScriptLexer(new FileInputStream(testScriptFile));
            GauntletScriptParser    scriptParser = new GauntletScriptParser(scriptLexer);
            GauntletScriptParserCfg inCfg = new GauntletScriptParserCfg();
            
            ClassProxyCfg cfg = scriptParser.compilationUnit(stdRanges, inCfg);
        	
        	// Temp dump of configBlock 
        	
        	cfg.Dump();
        	
        	//
        	// Finalize the config block
        	//

        	cfg.removeUnsuitableCtors();
        	cfg.populateAllCtorParmLists();
        	cfg.populateAllMethodParmLists();        	
        	
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
            
            System.out.println("Gauntlet : $Revision: 1.28 $");
            System.out.println("Gauntlet : Ending - " + currDateTime.toString());
            
            System.err.println("Gauntlet : $Revision: 1.28 $");
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

}
