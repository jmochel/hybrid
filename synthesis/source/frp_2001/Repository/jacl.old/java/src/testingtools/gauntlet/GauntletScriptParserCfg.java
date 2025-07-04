/*
    $author: Jim Jackl-Mochel $
    $Revision: 1.3 $

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

import java.io.Serializable;

// JACL Imports

import jacl.util.hash.PearsonsHash;

/**
        ReportAndControlCfg carries configuration information for the Script Parser

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
        @version $Revision: 1.3 $

        @since JDK 1.1
    */


class GauntletScriptParserCfg
        implements Serializable,Cloneable
{
    public final static int NotVerbose  =  1;
    public final static int TotallyVerbose  =  64;
    
    /**
        Constructor
    
        @since  JDK 1.1
    */
    
    public GauntletScriptParserCfg()
    {
        _ReportingLevel = NotVerbose;
    }
    
    /**
        Accessor
    */
    
    public int getReportingLevel()
    {
        return(_ReportingLevel);
    }

	
	
	
    private int _ReportingLevel;
}   
