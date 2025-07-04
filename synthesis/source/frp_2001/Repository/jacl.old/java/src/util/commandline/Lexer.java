/*
    $author: Jim Jackl-Mochel $
    $Revision: 1.6 $

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

// System imports

import java.util.*;

/**
    A command line lexical analyser

    <p>Takes in command line arguments and lexes them into pairs of keys and values.
    Its should only check for syntax and report on syntax violations. Semantics checking 
    is left to the user of CommandLineLexer. The key-value pairs are
    passed on via the Enumeration interface.

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

    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/11/09 15:40:08 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.6 $

    @see Parser
    @see Lexer$KeyAndValue
    @see LexerUnitTest
    @see LexerUnitTestMain
    @since JDK 1.1
*/

public abstract class Lexer 
{
    // Constants
    
    public    int    DefaultKeyAndValueCnt = 3;

    /**
        A command line lexical key and vaue pair class
    
        <p>Acts as a contanter for a key and a value pair
    
        <p><B>Copyright : This code is in the public domain.</B>
    
        <p>This code was originally generated using TM version 34
        <p>This code was last modified on $Date: 1999/11/09 15:40:08 $
    
        @author Jim Jackl-Mochel
        @author $Author: jmochel $
        @version $Revision: 1.6 $
    
        @see Lexer
        @see LexerUnitTest
        @see LexerUnitTestMain
        @since JDK 1.1
    */
    
    public class KeyAndValue
    {

        /**
            Basic Constructor
            
            @since JDK 1.1
        */
        
        KeyAndValue(String key, String value)
        {
            _Key = key;
            _Value = value;
        }
        
        String getKey()
        {
            return(_Key);
        }
        
        String getValue()
        {
            return(_Value);
        }
        
        public String toString()
        {
            String newString = new String("KeyAndValue { Key = " + _Key + " Value = " + _Value + "}" );
            
            return(newString);
        }
        
        private String _Key;
        private String _Value;
    }

   /**
        Empty Constructor
        
        @since JDK 1.1
    */
    
    public Lexer()
    {
        _KeyAndValuePairs = new Hashtable(DefaultKeyAndValueCnt);
    }
         
    /**
        Constructor

        @see <???>
        @see <???>
        @since JDK 1.1
    */

    public Lexer(String usage)
    {
        this();
        _Usage = usage;
    }
    
    /**
        @since JDK 1.1
    */
    
    public void setAbbrevs(Hashtable abbrevs)
    {
        _Abbrevs = abbrevs;
    }

    public void addKeyAndValue(String key, String value)
    {
        // If there are supplied abbreviations, expand the keys upon insertion

        String finalKey = key;
        
        if ( _Abbrevs != null  )
        {
            String tempKey = (java.lang.String) _Abbrevs.get(key);
            
            // If there is no expansion to be done, use the original key

            if ( tempKey != null )
            {
                finalKey = tempKey;                
            }
        }

        // Add the kay and the value
        
        _KeyAndValuePairs.put( finalKey, new KeyAndValue(finalKey, value));
    }        
    
    /**
        Lexes the command line down into its component Key and Value pairs
    
        @exception java.lang.IllegalArgumentException
        @since JDK 1.1
    */

    public abstract void lex(String[] argValues) throws IllegalArgumentException;
    
    /**
        getUsage returns a string describing the syntax of the particular Lexer
    
        @since JDK 1.1
    
    */

    public String getUsage()
    {
        return(_Usage);
    }
        
    /**
        Returns an Enumeration of KeyAndValue Objects 
    
        @since JDK 1.1
    
    */
    
    public Enumeration getIter()
    {
        return(_KeyAndValuePairs.elements());
    }

    public boolean isValid()
    {
        if ( _Usage == null )
        {
            return false;
        }
    
        if ( _Abbrevs == null )
        {
            return false;
        }
        
        return true;
    }
        
    // Data Members
   
    protected    Hashtable    _KeyAndValuePairs;
    protected    String       _Usage;
    protected    Hashtable    _Abbrevs;
};
