/*
    $author: Jim Jackl-Mochel $
    $Revision: 1.4 $

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

import java.lang.Exception;

// Application Imports

/**
    ProxyInitializationException is a runtime exception that is thrown when a Proxy is unable to initialize
    
    <p>This code was originally generated using TM version 34
    <p>This code was last modified on $Date: 1999/02/12 18:50:27 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.4 $

    @see Lexer
    @since JDK 1.1
*/

public class ProxyInitializationException extends RuntimeException
{
    
    /**
        Constructor 

        @since  JDK 1.1
    */

    public ProxyInitializationException()
    {
    }
    
    /**
        Constructor 

        @since  JDK 1.1
    */

    public ProxyInitializationException(String msg)
    {
        super(msg);
    }
    
    /**
        Constructor 

        @since  JDK 1.1
    */

    public ProxyInitializationException(String msg, Throwable thrownObject)
    {
        super(msg);
                
        _ThrownObject = thrownObject;
    }
 
    public String toString()
    {
        String    fullMessage;
            
        if ( _ThrownObject == null )
        {
            fullMessage = this.getMessage();
        }
        else {
            fullMessage = new String(this.getMessage() + "\n" + _ThrownObject.getMessage());
        }
        
        return(fullMessage);
    }
    
    /**
        A contained thrown object
    */

    private Throwable    _ThrownObject;
    
}
