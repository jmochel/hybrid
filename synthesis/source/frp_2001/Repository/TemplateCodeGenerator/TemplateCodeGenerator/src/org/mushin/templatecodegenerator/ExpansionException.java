/*
 * Created by IntelliJ IDEA.
 * User: jmochel
 * Date: Sep 14, 2001
 * Time: 1:57:57 PM
 * To change template for new class use 
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.mushin.templatecodegenerator;

public class ExpansionException extends NestedException
{
    /**
    	Constructor

     	@param desc				Description of the cause of the exception
     	@param nestedException 	The exception to be nested
    */

    public ExpansionException(String desc, Exception nestedException)
    {
        super(desc, nestedException);
    }
}
