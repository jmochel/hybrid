/*
    @Copyright
*/

// Package Declaration

package jacl.util.commandline;

// Imports

import java.util.*;
import java.io.*;
import junit.framework.*;

/**
    Unit Test Case for Parser

    <p>This class forms a detailed test case for Parser designed to be 
    run standalone or by JUnit's TestRunner

    @author Jim Jackl-Mochel
    @author $Author: jmochel $

    @see Parser
*/

public class ParserUnitTest extends TestCase
{
    //#################################################################
    //#
    //#     Standard Unit Test Case support code.
    //#
    //#################################################################

    /**
        Constructor for the test case.
    */
    
    public ParserUnitTest(String name)
    {
        super(name);
    }

    /**
        Main

        <p>To be used for running the unit test without TestRunner
    */

   	public static void main(String[] args) 
    {
		junit.textui.TestRunner.run(suite());
	}

    /**
        The test suite

        <p>Returns a test suite that contains all the tests to be run by 
        the unit test.
    */
    
    public static Test suite()
    {
        // Tells the test framework to exercise all methods that
        // take a void and start with "test"

        return new TestSuite(ParserUnitTest.class);
    }

    
    /**
        Initializes any test specific code that has to persist from test call to test call.
    */
    
    protected void setUp()
    {
        //
        // Initialise the test data
        //

        _TestSet = new String[4][4];
        
        // argument set is well formed and valid.
        
        _TestSet[0][0] = new String("/in=infile.ext");
        _TestSet[0][1] = new String("/out=outfile.ext");
        _TestSet[0][2] = new String("/repeat=5");
        _TestSet[0][3] = new String("/flog=true");

        // argument set is well formed and valid.
        
        _TestSet[1][0] = new String("/i=infile.ext");
        _TestSet[1][1] = new String("/o=outfile.ext");
        _TestSet[1][2] = new String("/repeat=5");
        _TestSet[1][3] = new String("/flog=true");
        
        // argument set is Malformed with missing '/'            
        
        _TestSet[2][0] = new String("/in=infile.ext");
        _TestSet[2][1] = new String("out=outfile.ext");
        _TestSet[2][2] = new String("/repeat=5");
        _TestSet[2][3] = new String("/flog=true");
        
        // argument set is Malformed with incorrect type
        
        _TestSet[3][0] = new String("/in=infile.ext");
        _TestSet[3][1] = new String("/out=outfile.ext");
        _TestSet[3][2] = new String("/repeat=a");
        _TestSet[3][3] = new String("/flog=true");
    }

    /**
        Uninitializes any test specific code that has to persist from test call to test call.
    */
    
    protected void tearDown()
    {
    }

    //#################################################################
    //#
    //#     Tests for Proof of Existence.
    //#
    //#     Proof of existence demonstrates that a feature has been 
    //#     minimally implemented
    //#
    //#################################################################
    

    /**
        Prove that the classes can be constructed

        <p>Proof of existence demonstrates that a feature has been minimally implemented
    */

    public void testProofOfExistenceForCtors()
    {
        Parser testParser = null;
        
        // Test of the void Constructor
        
        try 
        {    
            testParser = new Parser("SlashEqualsLexer", false);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);

        }

    }
    
    /**
        Prove that the accessors and mutators "exist"

        <p>Proof of existence demonstrates that a feature has been minimally implemented
    */

    public void testProofOfExistenceForAccessorsAndMtors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);

        }
        
        // Populate it.
        
        testParser.register(testParser.new ArgSemantic("in","i", "Input filename","java.io.File", true));
        testParser.register(testParser.new ArgSemantic("out","o", "Output filename","java.io.File", true));
        testParser.register(testParser.new ArgSemantic("repeat","", "Number of times to repeat","java.lang.Integer", false));
        testParser.register(testParser.new ArgSemantic("flog", "", "Flag to flog the output","java.lang.Boolean", false));
       
        // Parse one of the test strings.

        try
        {
            testParser.parse(_TestSet[0]);
        }
        catch(ParsingException parsingError)
        {
            fail("Unable to parse test argument set : parse threw a ParsingException");
            return;
        }

        // Test the accessors
        
        File in = (File) testParser.getParsedArgument("in");
        assertNotNull(in);
        
        File out = (File) testParser.getParsedArgument("out");
        assertNotNull(out);
        
        Integer repeat = (Integer) testParser.getParsedArgument("repeat");
        assertNotNull(repeat);
        
        Boolean flog = (Boolean) testParser.getParsedArgument("flog");
        assertNotNull(flog);
        
        String usage = testParser.getUsage();
        assertNotNull(usage);
    }

    /**
        Prove that the core methods "exist"

        <p>Proof of existence demonstrates that a feature has been minimally implemented
    */

    public void testProofOfExistenceForCoreMethods()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);

        }
        
        // Test toString

        String desc = testParser.toString();
        assertNotNull(desc);

        // Test Hash

        int hashCode = testParser.hashCode();

        // Test Serialization

        try 
        {
            Parser loadedParser = null;

            ObjectOutputStream objOut = null;
            ObjectInputStream  objIn = null;
            FileOutputStream fileOut = null;
            FileInputStream  fileIn = null;
            File             tempFile = null;

            // Create the temp file

            tempFile = File.createTempFile("testParser", "serialized",  new File("."));;
            assertNotNull(tempFile);

            // Open the streams for writing

            fileOut = new FileOutputStream(tempFile);
            assertNotNull(fileOut);

            objOut = new ObjectOutputStream(fileOut);
            assertNotNull(objOut);

            // Write the testParser

            objOut.writeObject(testParser);
            objOut.close();

            // Reopen the file for read.
            
            fileIn = new FileInputStream(tempFile);
            assertNotNull(fileIn);

            objIn = new ObjectInputStream(fileIn);
            assertNotNull(objIn);
          
            // Read in the file to the new instance.

            loadedParser = (Parser) objIn.readObject();
            objIn.close();

            // Compare the two.

            assertEquals(testParser,loadedParser);

        }
        catch(IOException IOError)
        {
            fail("threw java.io.IOException" + IOError);
        }
        catch(java.lang.ClassNotFoundException classNotFoundError)
        {
            fail("threw java.lang.ClassNotFoundException" + classNotFoundError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("", testParser);

        }
    }
   

    /**
        Prove that the key behaviors "exist"

        <p>Proof of existence demonstrates that a feature has been minimally implemented
	At the moment this is amply proven via the Accessors/mutators methods
    */

    public void testProofOfExistenceForKeyBehaviors()
    {
    }

    //#################################################################
    //#
    //#     Tests for Basic Functionality
    //#
    //#     Proves that the classes behave as they should under 
    //#     non-stressful conditions.
    //#
    //#################################################################

    /**
        Prove that the classes construct as they should under non-stressful conditions.
    */

    public void testBasicFunctionalityOfCtors()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", false);
	    assert(testParser.isValid());

            testParser = new Parser("", true);
	    assert(testParser.isValid());

            testParser = new Parser("SlashEqualsLexer", false);
	    assert(testParser.isValid());
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);

        }
    }
    
    /**
        Prove that the accessors and mutators work behave properly under non-stressful conditions.
    */

    public void testBasicFunctionalityOfAccessorsAndMtors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }

        // Populate it.
        
        testParser.register(testParser.new ArgSemantic("in","i", "Input filename","java.io.File", true));
        testParser.register(testParser.new ArgSemantic("out","o", "Output filename","java.io.File", true));
        testParser.register(testParser.new ArgSemantic("repeat","", "Number of times to repeat","java.lang.Integer", false));
        testParser.register(testParser.new ArgSemantic("flog", "", "Flag to flog the output","java.lang.Boolean", false));
       
        // Parse one of the test strings.

        try
        {
            testParser.parse(_TestSet[0]);
        }
        catch(ParsingException parsingError)
        {
            fail("Unable to parse test argument set : parse threw a ParsingException");
            return;
        }

        // Establish the canonical attributes (variables)
        
        int canonRequiredArgCount = 2;



/*
        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        ArgSemantic canonValueSemantic = new ArgSemantic("");
        assertNotNull("Unable to construct canonical value attribute canonValueSemantic", canonValueSemantic);
        boolean canonExitOnFailure = new boolean("");
        assertNotNull("Unable to construct canonical attribute canonExitOnFailure", canonExitOnFailure);

        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        Object canonValueArgAsAnObject = new Object("");
        assertNotNull("Unable to construct canonical value attribute canonValueArgAsAnObject", canonValueArgAsAnObject);
        Validation canonValidation = new Validation("");
        assertNotNull("Unable to construct canonical attribute canonValidation", canonValidation);

        String canonKeyAbbrev = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyAbbrev", canonKeyAbbrev);

        String canonValueKey = new String("");
        assertNotNull("Unable to construct canonical value attribute canonValueKey", canonValueKey);
*/

        // Establish the test attributes (variables)

/*
        boolean testExitOnFailure = null;
        Object testValueArgAsAnObject = null;
        Validation testValidation = null;
        String testValueKey = null;
*/

        // Test the accessors
        
        File in = (File) testParser.getParsedArgument("in");
        assertEquals(new File("infile.ext"), in);
        
        File out = (File) testParser.getParsedArgument("out");
        assertEquals(new File("outfile.ext"), out);
        
        Integer repeat = (Integer) testParser.getParsedArgument("repeat");
	    assertEquals(new Integer(5), repeat);
        
        Boolean flog = (Boolean) testParser.getParsedArgument("flog");
	    assertEquals(new Boolean(true), flog);
       

        //
        // Exercise the mutators
        //


/*        testParser.addArgSemantics(Key,Semantic);

        testParser.setExitOnFailure(canonExitOnFailure);

        testParser.addParsedArguments(Key,ArgAsAnObject);

        testParser.setValidation(canonValidation);

        testParser.addAbbrevs(Abbrev,Key);

*/

        //
        // Exercise the Accessors and verify we get something back.
        //


/*        testValueSemantic = testParser.getSemantic(canonKeyKey);
        assertEquals(canonValueSemantic, testValueSemantic);

        testExitOnFailure = testParser.getExitOnFailure();
        assertEquals(canonExitOnFailure, testExitOnFailure);

        testValueArgAsAnObject = testParser.getArgAsAnObject(canonKeyKey);
        assertEquals(canonValueArgAsAnObject, testValueArgAsAnObject);

        testValidation = testParser.getValidation();
        assertEquals(canonValidation, testValidation);

        testValueKey = testParser.getKey(canonKeyAbbrev);
        assertEquals(canonValueKey, testValueKey);
*/
    }
    
    /**
        Prove that the core methods behave properly under non-stressful conditions.
    */

    public void testBasicFunctionalityOfCoreMethods()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test toString

        String desc = testParser.toString();

        assertNotNull(desc);
        
        // Test Hash

        int canonHashCode = 0;

        int hashCode = testParser.hashCode();

        assertEquals(canonHashCode, hashCode);

        // Test Serialization

        try 
        {
            Parser loadedParser = null;

            ObjectOutputStream objOut = null;
            ObjectInputStream  objIn = null;
            FileOutputStream fileOut = null;
            FileInputStream  fileIn = null;
            File             tempFile = null;

            // Create the temp file

            tempFile = File.createTempFile("testParser", "serialized",  new File("."));
            assertNotNull(tempFile);

            // Open the streams for writing

            fileOut = new FileOutputStream(tempFile);
            assertNotNull(fileOut);

            objOut = new ObjectOutputStream(fileOut);
            assertNotNull(objOut);

            // Write the testParser

            objOut.writeObject(testParser);
            objOut.close();

            // Reopen the file for read.
            
            fileIn = new FileInputStream(tempFile);
            assertNotNull(fileIn);

            objIn = new ObjectInputStream(fileIn);
            assertNotNull(objIn);
          
            // Read in the file to the new instance.

            loadedParser = (Parser) objIn.readObject();
            objIn.close();

            // Compare the two.

            assertEquals(testParser,loadedParser);

        }
        catch(IOException IOError)
        {
            fail("threw java.io.IOException" + IOError);
        }
        catch(java.lang.ClassNotFoundException classNotFoundError)
        {
            fail("threw java.lang.ClassNotFoundException" + classNotFoundError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("", testParser);
        }

    }
    
    /**
        Prove that the key behaviours behave properly under non-stressful conditions.
    */

    public void testBasicFunctionalityOfKeyBehaviors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test the basic functionality 
        
        // <???>

    }

    //#################################################################
    //#
    //#     Tests for Boundary Functionality
    //#
    //#     Boundary tests exercise a feature under conditions that 
    //#     are at the edge of legal according to the API. Geared 
    //#     towards finding off-by-one errors, etc.
    //#
    //#################################################################


    /**
        Prove that the classes construct as they should with boundary inputs

        <P>Boundary tests exercise a feature under conditions that are at the 
        edge of legal according to the API. Geared towards finding off-by-one errors, etc.
    */

    public void testBoundaryFunctionalityOfCtors()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Validate that the object is valid as far as it is concerned

        assert(testParser.isValid());
    }
    
    /**
        Prove that the accessors and mutators work with boundary inputs

        <P>Boundary tests exercise a feature under conditions that are at the 
        edge of legal according to the API. Geared towards finding off-by-one errors, etc.
    */

    public void testBoundaryFunctionalityOfAccessorsAndMtors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Establish the canonical attributes (variables)
        
        int canonRequiredArgCount;

/*        
        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        ArgSemantic canonValueSemantic = new ArgSemantic("");
        assertNotNull("Unable to construct canonical value attribute canonValueSemantic", canonValueSemantic);
        boolean canonExitOnFailure = new boolean("");
        assertNotNull("Unable to construct canonical attribute canonExitOnFailure", canonExitOnFailure);

        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        Object canonValueArgAsAnObject = new Object("");
        assertNotNull("Unable to construct canonical value attribute canonValueArgAsAnObject", canonValueArgAsAnObject);
        Validation canonValidation = new Validation("");
        assertNotNull("Unable to construct canonical attribute canonValidation", canonValidation);

        String canonKeyAbbrev = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyAbbrev", canonKeyAbbrev);

        String canonValueKey = new String("");
        assertNotNull("Unable to construct canonical value attribute canonValueKey", canonValueKey);

        // Establish the test attributes (variables)

        int testRequiredArgCount = null;
        Lexer testLexer = null;
        ArgSemantic testValueSemantic = null;
        boolean testExitOnFailure = null;
        Object testValueArgAsAnObject = null;
        Validation testValidation = null;
        String testValueKey = null;

        //
        // Exercise the mutators
        //


        testParser.addArgSemantics(Key,Semantic);

        testParser.setExitOnFailure(canonExitOnFailure);

        testParser.addParsedArguments(Key,ArgAsAnObject);

        testParser.setValidation(canonValidation);

        testParser.addAbbrevs(Abbrev,Key);


        //
        // Exercise the Accessors and verify we get something back.
        //


        testValueSemantic = testParser.getSemantic(canonKeyKey);
        assertEquals(canonValueSemantic, testValueSemantic);

        testExitOnFailure = testParser.getExitOnFailure();
        assertEquals(canonExitOnFailure, testExitOnFailure);

        testValueArgAsAnObject = testParser.getArgAsAnObject(canonKeyKey);
        assertEquals(canonValueArgAsAnObject, testValueArgAsAnObject);

        testValidation = testParser.getValidation();
        assertEquals(canonValidation, testValidation);

        testValueKey = testParser.getKey(canonKeyAbbrev);
        assertEquals(canonValueKey, testValueKey);
*/        
    }
    
    /**
        Prove that the core methods work with boundary inputs

        <P>Boundary tests exercise a feature under conditions that are at the 
        edge of legal according to the API. Geared towards finding off-by-one errors, etc.
    */

    public void testBoundaryFunctionalityOfCoreMethods()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test toString

        String canonDesc = new String("Test String");

        String desc = testParser.toString();

        assertEquals(canonDesc, desc);

        // Test Hash

        int canonHashCode = 0;

        int hashCode = testParser.hashCode();

        assertEquals(canonHashCode, hashCode);

        // Test Serialization

        try 
        {
            Parser loadedParser = null;

            ObjectOutputStream objOut = null;
            ObjectInputStream  objIn = null;
            FileOutputStream fileOut = null;
            FileInputStream  fileIn = null;
            File             tempFile = null;

            // Create the temp file

            tempFile = File.createTempFile("testParser", "serialized",  new File("."));
            assertNotNull(tempFile);

            // Open the streams for writing

            fileOut = new FileOutputStream(tempFile);
            assertNotNull(fileOut);

            objOut = new ObjectOutputStream(fileOut);
            assertNotNull(objOut);

            // Write the testParser

            objOut.writeObject(testParser);
            objOut.close();

            // Reopen the file for read.
            
            fileIn = new FileInputStream(tempFile);
            assertNotNull(fileIn);

            objIn = new ObjectInputStream(fileIn);
            assertNotNull(objIn);
          
            // Read in the file to the new instance.

            loadedParser = (Parser) objIn.readObject();
            objIn.close();

            // Compare the two.

            assertEquals(testParser,loadedParser);

        }
        catch(IOException IOError)
        {
            fail("threw java.io.IOException" + IOError);
        }
        catch(java.lang.ClassNotFoundException classNotFoundError)
        {
            fail("threw java.lang.ClassNotFoundException" + classNotFoundError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("", testParser);
        }
    }
    
    /**
        Prove that the key behaviors work with boundary inputs

        <P>Boundary tests exercise a feature under conditions that are at the 
        edge of legal according to the API. Geared towards finding off-by-one errors, etc.
    */

    public void testBoundaryFunctionalityOfKeyBehaviors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test key behaviors

//        <???>
    }

    //#################################################################
    //#
    //#     Tests for Beyond Boundary Functionality
    //#
    //#     Beyond Boundary tests exercise a feature under conditions 
    //#     that are beyond the edge of legal according to the API. 
    //#     Geared towards exercising recovery from error conditions.
    //#
    //#################################################################

    /**
        Prove that the classes construct as they should with beyond boundary inputs

        <P>Beyond Boundary tests exercise a feature under conditions that are beyond the 
        edge of legal according to the API. Geared towards excercising recovery from error 
        conditions.
    */

    public void testBeyondBoundaryFunctionalityOfCtors()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Validate that the object is valid as far as it is concerned

        assert(testParser.isValid());
    }
    
    /**
        Prove that the accessors and mutators work with beyond boundary inputs

        <P>Beyond Boundary tests exercise a feature under conditions that are beyond the 
        edge of legal according to the API. Geared towards excercising recovery from error 
        conditions.
    */
    
    public void testBeyondBoundaryFunctionalityOfAccessorsAndMtors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Establish the canonical attributes (variables)
        
        int canonRequiredArgCount;
/*
        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        ArgSemantic canonValueSemantic = new ArgSemantic("");
        assertNotNull("Unable to construct canonical value attribute canonValueSemantic", canonValueSemantic);
        boolean canonExitOnFailure = new boolean("");
        assertNotNull("Unable to construct canonical attribute canonExitOnFailure", canonExitOnFailure);

        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        Object canonValueArgAsAnObject = new Object("");
        assertNotNull("Unable to construct canonical value attribute canonValueArgAsAnObject", canonValueArgAsAnObject);
        Validation canonValidation = new Validation("");
        assertNotNull("Unable to construct canonical attribute canonValidation", canonValidation);

        String canonKeyAbbrev = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyAbbrev", canonKeyAbbrev);

        String canonValueKey = new String("");
        assertNotNull("Unable to construct canonical value attribute canonValueKey", canonValueKey);

        // Establish the test attributes (variables)

        int testRequiredArgCount = null;
        Lexer testLexer = null;
        ArgSemantic testValueSemantic = null;
        boolean testExitOnFailure = null;
        Object testValueArgAsAnObject = null;
        Validation testValidation = null;
        String testValueKey = null;

        //
        // Exercise the mutators
        //


        testParser.setRequiredArgCount(canonRequiredArgCount);
        testParser.setLexer(canonLexer);

        testParser.addArgSemantics(Key,Semantic);

        testParser.setExitOnFailure(canonExitOnFailure);

        testParser.addParsedArguments(Key,ArgAsAnObject);

        testParser.setValidation(canonValidation);

        testParser.addAbbrevs(Abbrev,Key);


        //
        // Exercise the Accessors and verify we get something back.
        //


        testRequiredArgCount = testParser.getRequiredArgCount();
        assertEquals(canonRequiredArgCount, testRequiredArgCount);

        testLexer = testParser.getLexer();
        assertEquals(canonLexer, testLexer);

        testValueSemantic = testParser.getSemantic(canonKeyKey);
        assertEquals(canonValueSemantic, testValueSemantic);

        testExitOnFailure = testParser.getExitOnFailure();
        assertEquals(canonExitOnFailure, testExitOnFailure);

        testValueArgAsAnObject = testParser.getArgAsAnObject(canonKeyKey);
        assertEquals(canonValueArgAsAnObject, testValueArgAsAnObject);

        testValidation = testParser.getValidation();
        assertEquals(canonValidation, testValidation);

        testValueKey = testParser.getKey(canonKeyAbbrev);
        assertEquals(canonValueKey, testValueKey);
*/        
    }
    
    /**
        Prove that the core methods work with beyond boundary inputs

        <P>Beyond Boundary tests exercise a feature under conditions that are beyond the 
        edge of legal according to the API. Geared towards excercising recovery from error 
        conditions.
    */
    
    public void testBeyondBoundaryFunctionalityOfCoreMethods()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test toString

        String canonDesc = new String("Test String");

        String desc = testParser.toString();

        assertEquals(canonDesc, desc);

        // Test Hash

        int canonHashCode = 0;

        int hashCode = testParser.hashCode();

        assertEquals(canonHashCode, hashCode);

        // Test Serialization

        try 
        {
            Parser loadedParser = null;

            ObjectOutputStream objOut = null;
            ObjectInputStream  objIn = null;
            FileOutputStream fileOut = null;
            FileInputStream  fileIn = null;
            File             tempFile = null;

            // Create the temp file

            tempFile = File.createTempFile("testParser", "serialized",  new File("."));
            assertNotNull(tempFile);

            // Open the streams for writing

            fileOut = new FileOutputStream(tempFile);
            assertNotNull(fileOut);

            objOut = new ObjectOutputStream(fileOut);
            assertNotNull(objOut);

            // Write the testParser

            objOut.writeObject(testParser);
            objOut.close();

            // Reopen the file for read.
            
            fileIn = new FileInputStream(tempFile);
            assertNotNull(fileIn);

            objIn = new ObjectInputStream(fileIn);
            assertNotNull(objIn);
          
            // Read in the file to the new instance.

            loadedParser = (Parser) objIn.readObject();
            objIn.close();

            // Compare the two.

            assertEquals(testParser,loadedParser);

        }
        catch(IOException IOError)
        {
            fail("threw java.io.IOException" + IOError);
        }
        catch(java.lang.ClassNotFoundException classNotFoundError)
        {
            fail("threw java.lang.ClassNotFoundException" + classNotFoundError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("", testParser);
        }
    }

    /**
        Prove that the key behaviors work with beyond boundary inputs

        <P>Beyond Boundary tests exercise a feature under conditions that are beyond the 
        edge of legal according to the API. Geared towards exercising recovery from error 
        conditions.
    */
    
    public void testBeyondBoundaryFunctionalityOfKeyBehaviors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test key behaviors

        //<???>
    }

    //#################################################################
    //#
    //#     Tests for Destructive Functionality
    //#
    //#     Destructive conditions include passing in null for objects, 
    //#     putting the methods under extreme load, etc.
    //#
    //#################################################################

    /**
        Prove that the classes construct as they should under destructive conditions
        
        <p>Destructive conditions include passing in null for objects, putting the 
        methods under extreme load, etc.
    */

    public void testDestructiveFunctionalityOfCtors()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Validate that the object is valid as far as it is concerned

        assert(testParser.isValid());
    }
    
    /**
        Prove that the accessors and mutators work under destructive conditions
       
        <p>Destructive conditions include passing in null for objects, putting the 
        methods under extreme load, etc.
    */

    public void testDestructiveFunctionalityOfAccessorsAndMtors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Establish the canonical attributes (variables)
        
        int canonRequiredArgCount;

/*        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        ArgSemantic canonValueSemantic = new ArgSemantic("");
        assertNotNull("Unable to construct canonical value attribute canonValueSemantic", canonValueSemantic);
        boolean canonExitOnFailure = new boolean("");
        assertNotNull("Unable to construct canonical attribute canonExitOnFailure", canonExitOnFailure);

        String canonKeyKey = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyKey", canonKeyKey);

        Object canonValueArgAsAnObject = new Object("");
        assertNotNull("Unable to construct canonical value attribute canonValueArgAsAnObject", canonValueArgAsAnObject);
        Validation canonValidation = new Validation("");
        assertNotNull("Unable to construct canonical attribute canonValidation", canonValidation);

        String canonKeyAbbrev = new String("");
        assertNotNull("Unable to construct canonical key attribute canonKeyAbbrev", canonKeyAbbrev);

        String canonValueKey = new String("");
        assertNotNull("Unable to construct canonical value attribute canonValueKey", canonValueKey);

        // Establish the test attributes (variables)

        int testRequiredArgCount = null;
        Lexer testLexer = null;
        ArgSemantic testValueSemantic = null;
        boolean testExitOnFailure = null;
        Object testValueArgAsAnObject = null;
        Validation testValidation = null;
        String testValueKey = null;

        //
        // Exercise the mutators
        //


        testParser.setRequiredArgCount(canonRequiredArgCount);
        testParser.setLexer(canonLexer);

        testParser.addArgSemantics(Key,Semantic);

        testParser.setExitOnFailure(canonExitOnFailure);

        testParser.addParsedArguments(Key,ArgAsAnObject);

        testParser.setValidation(canonValidation);

        testParser.addAbbrevs(Abbrev,Key);


        //
        // Exercise the Accessors and verify we get something back.
        //


        testRequiredArgCount = testParser.getRequiredArgCount();
        assertEquals(canonRequiredArgCount, testRequiredArgCount);

        testLexer = testParser.getLexer();
        assertEquals(canonLexer, testLexer);

        testValueSemantic = testParser.getSemantic(canonKeyKey);
        assertEquals(canonValueSemantic, testValueSemantic);

        testExitOnFailure = testParser.getExitOnFailure();
        assertEquals(canonExitOnFailure, testExitOnFailure);

        testValueArgAsAnObject = testParser.getArgAsAnObject(canonKeyKey);
        assertEquals(canonValueArgAsAnObject, testValueArgAsAnObject);

        testValidation = testParser.getValidation();
        assertEquals(canonValidation, testValidation);

        testValueKey = testParser.getKey(canonKeyAbbrev);
        assertEquals(canonValueKey, testValueKey);
*/        
    }

    
    /**
        Prove that the core methods with under destructive conditions
       
        <p>Destructive conditions include passing in null for objects, putting the 
        methods under extreme load, etc.
    */

    public void testDestructiveFunctionalityOfCoreMethods()
    {
        Parser testParser = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test toString

        String canonDesc = new String("Test String");

        String desc = testParser.toString();

        assertEquals(canonDesc, desc);

        // Test Hash

        int canonHashCode = 0;

        int hashCode = testParser.hashCode();

        assertEquals(canonHashCode, hashCode);

        // Test Serialization

        try 
        {
            Parser loadedParser = null;

            ObjectOutputStream objOut = null;
            ObjectInputStream  objIn = null;
            FileOutputStream fileOut = null;
            FileInputStream  fileIn = null;
            File             tempFile = null;

            // Create the temp file

            tempFile = File.createTempFile("testParser", "serialized",  new File("."));
            assertNotNull(tempFile);

            // Open the streams for writing

            fileOut = new FileOutputStream(tempFile);
            assertNotNull(fileOut);

            objOut = new ObjectOutputStream(fileOut);
            assertNotNull(objOut);

            // Write the testParser

            objOut.writeObject(testParser);
            objOut.close();

            // Reopen the file for read.
            
            fileIn = new FileInputStream(tempFile);
            assertNotNull(fileIn);

            objIn = new ObjectInputStream(fileIn);
            assertNotNull(objIn);
          
            // Read in the file to the new instance.

            loadedParser = (Parser) objIn.readObject();
            objIn.close();

            // Compare the two.

            assertEquals(testParser,loadedParser);

        }
        catch(IOException IOError)
        {
            fail("threw java.io.IOException" + IOError);
        }
        catch(java.lang.ClassNotFoundException classNotFoundError)
        {
            fail("threw java.lang.ClassNotFoundException" + classNotFoundError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("", testParser);
        }
    }

    /**
        Prove that the key behaviors work under destructive conditions
       
        <p>Destructive conditions include passing in null for objects, putting the 
        methods under extreme load, etc.
    */

    public void testDestructiveFunctionalityOfKeyBehaviors()
    {
        Parser testParser = null;
        
        // Create the testParser
        
        try 
        {    
            testParser = new Parser("", true);
        }
        catch(LexerCreationException lexerCreationError)
        {
            fail("Unable to construct testParser() : threw LexerCreationException" + lexerCreationError);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testParser() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testParser()", testParser);
        }
        
        // Test key behaviors

        // <???>
    }




    /**
        Holder for some test argument sets.
    */
    
    private     String[][]     _TestSet;


}
