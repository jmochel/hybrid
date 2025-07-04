h63032
s 00193/00000/00000
d D 1.1 99/11/17 12:51:16 jmochel 2 1
cC
cK23085
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:51:13 jmochel 1 0
c BitKeeper file e:/jacl/java/src/util/hash/PearsonsHash.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45339
cPjava/src/util/hash/PearsonsHash.java
cR2f93d7fa5cb6ba86
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
    Contains Pearsons Hash Function

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

    This code was last modified on $Date: 1999/10/13 16:06:51 $

    $author: Jim Jackl-Mochel $
    $Revision: 1.4 $
*/

package jacl.util.hash;

// Standard imports

import java.util.Random;

// Library specific imports

import jacl.util.hash.*;

/**
    A high speed string hash method

    <p>The basic hash design is taken from
    <it><b>Pearson, P. K. 

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

    <p>This code was last modified on $Date: 1999/10/13 16:06:51 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.4 $

    @see <???>
    @see <???>
    @see <???>
    @since JDK 1.1
*/


public final class PearsonsHash
{
    /**
        <???>
    */            

    static final private byte[] _IntermediateResultsTable = new byte[256];


    private PearsonsHash()
    {
    }

    /*
        Static initializer for the static table.

        Yes, I am aware that hardcoded numbers are a bad thing (tm).
        I am also aware that there is no symbolic signifigance to
        the number used for the random seed. It is merely important
        that the same seed be used by all the instantiated versions
        of Pearson' Hash. And that is only to cover the very
        unlikely event that two different machines are checking
        values across the net !
    */

    static
    {
        byte    i;
        int     randInt;
        byte    randByte;
        Random  rng = new Random(10000);      // rng = Random Number Generator
        
        byte    exchangeValue;   // Value to be exchanged

        // Populate the intermediate results table

        try 
        {
            for ( i = 0; i < 256; i++ )
            {
                _IntermediateResultsTable[i] = i;
            }
    
            // Then randomly permute the table.
    
            for ( i = 0; i < 256; i++ )
            {
                randInt = rng.nextInt();
                randByte = (byte)(randInt & 0xFF);
                
                exchangeValue = _IntermediateResultsTable[i];
    
                _IntermediateResultsTable[i] = _IntermediateResultsTable[randByte];
                _IntermediateResultsTable[randByte] = exchangeValue;
            }
        }
        catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsError)
        {
            
        }
    }

    public static byte generateByteRangeHash(String string)
    {
        int     i;
        int     length; 
        byte    hashAccumulator;

        // Initialize

        length = string.length();
        hashAccumulator = 0;

        // Walk through the table, XORing all the way.
        
        for ( i = 0; i < length; i++ )
        {
            hashAccumulator = _IntermediateResultsTable[hashAccumulator ^ string.charAt(i)];
        }
        
        return(hashAccumulator);
    }

    public static byte generateByteRangeHash(Object object)
    {
        return(generateByteRangeHash(object.toString()));
    }
    
    public static int generateIntRangeHash(String string)
    {
        int     i;
        int     length; 
        byte    oddHashAccumulator;
        byte    evenHashAccumulator;
        int    hashAccumulator;
        
        // Initialize

        length = string.length();
        oddHashAccumulator = 0;
        evenHashAccumulator = 0;
        hashAccumulator = 0;

        // Walk through the table, XORing all the way.
        
        for ( i = 0; i < length; i++ )
        {
            oddHashAccumulator = _IntermediateResultsTable[oddHashAccumulator ^ string.charAt(i)];
            evenHashAccumulator = _IntermediateResultsTable[evenHashAccumulator ^ string.charAt(i)];
        }
        
        hashAccumulator = (int)((evenHashAccumulator<<8) | oddHashAccumulator);

        return(hashAccumulator);
    }
    
    public static int generateIntRangeHash(Object object)
    {
        return(generateIntRangeHash(object.toString()));
    }
};
E 2
I 1
E 1
