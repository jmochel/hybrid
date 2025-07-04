H24865
s 00024/00000/00000
d D 1.1 01/10/03 15:59:53 jmochel 2 1
cC
cF1
cK47143
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/10/03 15:59:53 jmochel 1 0
c BitKeeper file g:/TemplateCodeGenerator/TemplateCodeGenerator/src/org/mushin/templatecodegenerator/ExpansionException.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHdevilmountain.corp.foliage.com
cK21411
cPTemplateCodeGenerator/src/org/mushin/templatecodegenerator/ExpansionException.java
cRb67bdd30
cV4
cX0xb1
cZ-04:00
e
u
U
f e 0
f x 0xb1
t
T
I 2
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
E 2
I 1
E 1
