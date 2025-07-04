H06609
s 00118/00000/00000
d D 1.1 01/08/14 17:12:55 jmochel 2 1
cC
cF1
cK38743
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/08/14 17:12:55 jmochel 1 0
c BitKeeper file g:/TemplateCodeGenerator/TemplateCodeGenerator/scm/Hash.java
cBjmochel@mordanith.ne.mediaone.net|ChangeSet|20010315024621|04493|9033c8cd
cHdevilmountain.corp.foliage.com
cK07670
cPTemplateCodeGenerator/scm/Hash.java
cR993392b6
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
    Contains Hash Interface

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

    This code was last modified on $Date: 1999/01/07 22:14:07 $

    $author: Jim Jackl-Mochel $
    $Revision: 1.3 $
*/

package mushin.util.hash;

/**
    Basic Hashing Interface

    <p>Presents a basic interface for accessing hash methods of
    different levels of granularity.

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

    <p>This code was last modified on $Date: 1999/01/07 22:14:07 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $

    @see <???>
    @see <???>
    @see <???>
    @since JDK 1.1
*/

public interface Hash
{
    /**
        Generates a byte range hash of a String

        @param  string - String to be hashed.

        @return byte

        @see    <???>
        @since  JDK 1.1
    */

    public byte generateByteRangeHash(String string);

    /**
        Generates a byte range hash of an Object

        @param  object -  Object to be hashed.

        @return byte

        @see    <???>
        @since  JDK 1.1
    */

    public byte generateByteRangeHash(Object object);


    /**
        Generates an int range hash of a String

        @param  string - String to be hashed.

        @return 

        @see    <???>
        @since  JDK 1.1
    */

    public int generateIntRangeHash(String string);

    /**
        Generates a byte range hash of an Object

        @param  object -  Object to be hashed.

        @return int

        @see    <???>
        @since  JDK 1.1
    */

    public int generateIntRangeHash(Object object);
};
E 2
I 1
E 1
