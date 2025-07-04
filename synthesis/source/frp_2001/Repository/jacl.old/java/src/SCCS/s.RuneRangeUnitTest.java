h59394
s 00857/00000/00000
d D 1.1 99/11/17 12:50:58 jmochel 2 1
cC
cK20344
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:50:55 jmochel 1 0
c BitKeeper file e:/jacl/java/src/RuneRangeUnitTest.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45337
cPjava/src/RuneRangeUnitTest.java
cR2f93d7ff5cb6ba86
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
    @Copyright
*/

// Package Declaration

package jacl.???.RuneRange;

// Imports

import java.util.*;
import junit.framework.*;

/**
    Unit Test Case for RuneRange

    <p>This class forms a detailed test case for RuneRange designed to be 
    run standalone or by JUnit's TestRunner

    @author Jim Jackl-Mochel
    @author $Author: jmochel $

    @see RuneRange
*/

public class RuneRangeUnitTest extends TestCase
{
    //#################################################################
    //#
    //#     Standard Unit Test Case support code.
    //#
    //#################################################################

    /**
        Constructor for the test case.
    */
    
    public RuneRangeUnitTest(String name)
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

        return new TestSuite(RuneRangeUnitTest.class);
    }

    
    /**
        Initializes any test specific code that has to persist from test call to test call.
    */
    
    protected void setUp()
    {
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
        RuneRange testRuneRange = null;
        
        // Test of the void Constructor
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange() : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange()", testRuneRange);
            testRuneRange = null;
        }

        // Test of the String Constructor
        
        try 
        {    
            String  ctorInput = new String("Test Input");

            testRuneRange = new RuneRange(ctorInput);
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange(String) : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange(String) : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange(String)", testRuneRange);
            testRuneRange = null;
        }

        // Test of the <???> Constructor
        
        try 
        {    
            testRuneRange = new RuneRange(<???>);
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange(<???>) : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange(<???>) : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange(<???>)", testRuneRange);
            testRuneRange = null;
        }

    }
    
    /**
        Prove that the accessors and mutators "exist"

        <p>Proof of existence demonstrates that a feature has been minimally implemented
    */

    public void testProofOfExistenceForAccessorsAndMtors()
    {
        RuneRange testRuneRange = null;
        
        // Create the testRuneRange
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }

                
        // Establish the canonical attributes
        
        int canonRequiredArgCount = new int("");
        assertNotNull("Unable to construct canonical attribute canonRequiredArgCount", canonRequiredArgCount);
        Lexer canonLexer = new Lexer("");
        assertNotNull("Unable to construct canonical attribute canonLexer", canonLexer);
        ArgSemantic canonArgSemantics = new ArgSemantic("");
        assertNotNull("Unable to construct canonical attribute canonArgSemantics", canonArgSemantics);
        boolean canonExitOnFailure = new boolean("");
        assertNotNull("Unable to construct canonical attribute canonExitOnFailure", canonExitOnFailure);
        Object canonParsedArguments = new Object("");
        assertNotNull("Unable to construct canonical attribute canonParsedArguments", canonParsedArguments);
        Validation canonValidation = new Validation("");
        assertNotNull("Unable to construct canonical attribute canonValidation", canonValidation);
        String canonAbbrevs = new String("");
        assertNotNull("Unable to construct canonical attribute canonAbbrevs", canonAbbrevs);

        //
        // Exercise the mutators
        //


        // Verify the existence of the setRequiredArgCount method
        testRuneRange.setRequiredArgCount(canonRequiredArgCount);

        // Verify the existence of the setLexer method
        testRuneRange.setLexer(canonLexer);

        // Verify the existence of the setArgSemantics method
        testRuneRange.setArgSemantics(canonArgSemantics);

        // Verify the existence of the setExitOnFailure method
        testRuneRange.setExitOnFailure(canonExitOnFailure);

        // Verify the existence of the setParsedArguments method
        testRuneRange.setParsedArguments(canonParsedArguments);

        // Verify the existence of the setValidation method
        testRuneRange.setValidation(canonValidation);

        // Verify the existence of the setAbbrevs method
        testRuneRange.setAbbrevs(canonAbbrevs);

        // Exercise the Accessors and verify we get something back.

        
        // Verify the existence of the getRequiredArgCount method

        int testRequiredArgCount = null;
        testRequiredArgCount = testRuneRange.getRequiredArgCount();
        assertNotNull(testRequiredArgCount);
        
        // Verify the existence of the getLexer method

        Lexer testLexer = null;
        testLexer = testRuneRange.getLexer();
        assertNotNull(testLexer);
        
        // Verify the existence of the getArgSemantics method

        ArgSemantic testArgSemantics = null;
        testArgSemantics = testRuneRange.getArgSemantics();
        assertNotNull(testArgSemantics);
        
        // Verify the existence of the getExitOnFailure method

        boolean testExitOnFailure = null;
        testExitOnFailure = testRuneRange.getExitOnFailure();
        assertNotNull(testExitOnFailure);
        
        // Verify the existence of the getParsedArguments method

        Object testParsedArguments = null;
        testParsedArguments = testRuneRange.getParsedArguments();
        assertNotNull(testParsedArguments);
        
        // Verify the existence of the getValidation method

        Validation testValidation = null;
        testValidation = testRuneRange.getValidation();
        assertNotNull(testValidation);
        
        // Verify the existence of the getAbbrevs method

        String testAbbrevs = null;
        testAbbrevs = testRuneRange.getAbbrevs();
        assertNotNull(testAbbrevs);
    }

    /**
        Prove that the core methods "exist"

        <p>Proof of existence demonstrates that a feature has been minimally implemented
    */

    public void testProofOfExistenceForCoreMethods()
    {
        RuneRange testRuneRange = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange() : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange() : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange()", testRuneRange);
            testRuneRange = null;
        }

        // Test toString

        String canonDesc = new String("Test String");

        String desc = testRuneRange.toString();

        assertEqual(canonDesc, desc);

        // Test Hash

        int canonHashCode = 0;

        int hashCode = testRuneRange.hashCode();

        assertEqual(canonHashCode, hashCode);

        // Test Serialization

        try 
        {
            RuneRange loadedRuneRange = null;

            ObjectOutputStream objOut = null;
            ObjectInputStream  objIn = null;
            FileOutputStream fileOut = null;
            FileInputStream  fileIn = null;
            File             tempFile = null;

            // Create the temp file

            tempFile = File.createTempFile("testRuneRange", "serialized",  new File("."))
            assertNotNull(tempFile);

            // Open the streams for writing

            fileOut = new FileOutputStream(tempFile);
            assertNotNull(fileOut);

            objOut = new ObjectOutputStream(fileOut);
            assertNotNull(objOut);

            // Write the testRuneRange

            objOut.writeObject(testRuneRange);
            objOut.close();

            // Reopen the file for read.
            
            filein = new FileInputStream(tempFile);
            assertNotNull(filein);

            objIn = new ObjectInputStream(fileIn);
            assertNotNull(objIn);
          
            // Read in the file to the new instance.

            objIn.readObject(loadedRuneRange);
            objIn.close();

            // Compare the two.

            assertEqual(testRuneRange,loadedRuneRange);

        }
        catch(<specificException> <specificError>)
        {
            fail("threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("", testRuneRange);
            testRuneRange = null;
        }
    }
   

    /**
        Prove that the key behaviors "exist"

        <p>Proof of existence demonstrates that a feature has been minimally implemented
    */

    public void testProofOfExistenceForKeyBehaviors()
    {
        RuneRange testRuneRange = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }

        // Test key behaviour

        <???>
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
        RuneRange testRuneRange = null;
        
        // Test of the <???> Constructor
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }

        // Validate that the object is valid as far as it is concerned

        assert(testRuneRange.isValid());
    }
    
    /**
        Prove that the accessors and mutators work behave properly under non-stressful conditions.
    */

    public void testBasicFunctionalityOfAccessorsAndMtors()
    {
        RuneRange testRuneRange = null;
        
        // Create the testRuneRange
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }
        
        // 
        // Test accessors and mutators
        //

        // Initialize for <AttrN>

        <AttrT> canon<AttrN> = <???>;
        <AttrT> test<AttrN> = <???>;

        // Set <AttrN>

        testRuneRange.set<AttrN>(canon<AttrN>);

        // Get <AttrN>
        
        test<AttrN> = testRuneRange.get<AttrN>();

        // Compare test value to canonical value.

        assertEqual("Testing <AttrN>", canon<AttrN>, test<AttrN>);
    }
    
    /**
        Prove that the core methods behave properly under non-stressful conditions.
    */

    public void testBasicFunctionalityOfCoreMethods()
    {
        RuneRange testRuneRange = null;
        
        // Create the testRuneRange
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }
        
        // Test toString

        String canonDesc = new String("Test String");

        String desc = testRuneRange.toString();

        assertEqual(canonDesc, desc);

        // Test Hash

        <???>

        // Test Serialization

        <???>

    }
    
    /**
        Prove that the key behaviours behave properly under non-stressful conditions.
    */

    public void testBasicFunctionalityOfKeyBehaviors()
    {
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
    }
    
    /**
        Prove that the accessors and mutators work with boundary inputs

        <P>Boundary tests exercise a feature under conditions that are at the 
        edge of legal according to the API. Geared towards finding off-by-one errors, etc.
    */

    public void testBoundaryFunctionalityOfAccessorsAndMtors()
    {
    }
    
    /**
        Prove that the core methods work with boundary inputs

        <P>Boundary tests exercise a feature under conditions that are at the 
        edge of legal according to the API. Geared towards finding off-by-one errors, etc.
    */

    public void testBoundaryFunctionalityOfCoreMethods()
    {
        RuneRange testRuneRange = null;
        
        // Create the testRuneRange
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }
        
        // Test toString

        String canonDesc = new String("Test String");

        String desc = testRuneRange.toString();

        assertEqual(canonDesc, desc);

        // Test Hash

        <???>

        // Test Serialization

        <???>
    }
    
    /**
        Prove that the key behaviors work with boundary inputs

        <P>Boundary tests exercise a feature under conditions that are at the 
        edge of legal according to the API. Geared towards finding off-by-one errors, etc.
    */

    public void testBoundaryFunctionalityOfKeyBehaviors()
    {
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
    }
    
    /**
        Prove that the accessors and mutators work with beyond boundary inputs

        <P>Beyond Boundary tests exercise a feature under conditions that are beyond the 
        edge of legal according to the API. Geared towards excercising recovery from error 
        conditions.
    */
    
    public void testBeyondBoundaryFunctionalityOfAccessorsAndMtors()
    {
    }
    
    /**
        Prove that the core methods work with beyond boundary inputs

        <P>Beyond Boundary tests exercise a feature under conditions that are beyond the 
        edge of legal according to the API. Geared towards excercising recovery from error 
        conditions.
    */
    
    public void testBeyondBoundaryFunctionalityOfCoreMethods()
    {
        RuneRange testRuneRange = null;
        
        // Create the testRuneRange
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }
        
        // Test toString

        String canonDesc = new String("Test String");

        String desc = testRuneRange.toString();

        assertEqual(canonDesc, desc);

        // Test Hash

        <???>

        // Test Serialization

        <???>
    }

    /**
        Prove that the key behaviors work with beyond boundary inputs

        <P>Beyond Boundary tests exercise a feature under conditions that are beyond the 
        edge of legal according to the API. Geared towards exercising recovery from error 
        conditions.
    */
    
    public void testBeyondBoundaryFunctionalityOfKeyBehaviors()
    {
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
    }
    
    /**
        Prove that the accessors and mutators work under destructive conditions
       
        <p>Destructive conditions include passing in null for objects, putting the 
        methods under extreme load, etc.
    */

    public void testDestructiveFunctionalityOfAccessorsAndMtors()
    {
    }
    
    /**
        Prove that the core methods with under destructive conditions
       
        <p>Destructive conditions include passing in null for objects, putting the 
        methods under extreme load, etc.
    */

    public void testDestructiveFunctionalityOfCoreMethods()
    {
        RuneRange testRuneRange = null;
        
        // Create the testRuneRange
        
        try 
        {    
            testRuneRange = new RuneRange();
        }
        catch(<specificException> <specificError>)
        {
            fail("Unable to construct testRuneRange : threw <specificException>" + <specificException>);
        }
        catch(java.lang.RuntimeException runtimeError)
        {
            fail("Unable to construct testRuneRange : threw java.lang.RuntimeException" + runtimeError);
        }
        finally 
        {
            assertNotNull("Unable to construct testRuneRange", testRuneRange);
            testRuneRange = null;
        }
        
        // Test toString

        String canonDesc = new String("Test String");

        String desc = testRuneRange.toString();

        assertEqual(canonDesc, desc);

        // Test Hash

        <???>

        // Test Serialization

        <???>
    }

    /**
        Prove that the key behaviors work under destructive conditions
       
        <p>Destructive conditions include passing in null for objects, putting the 
        methods under extreme load, etc.
    */

    public void testDestructiveFunctionalityOfKeyBehaviors()
    {
    }
}
E 2
I 1
E 1
