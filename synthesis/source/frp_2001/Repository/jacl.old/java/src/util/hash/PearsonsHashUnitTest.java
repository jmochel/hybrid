
// Package Declaration

package jacl.util.hash;

// Imports

import java.util.Date;

import jacl.util.hash.*;

/**
    A self contained Unit Test for PearsonsHash

    <p>This class forms a single executable class for exercising
    PearsonsHash. Another class PearsonsHashUnitTestMain actually
    invokes UnitTest. This simplifies creating automated unit test executors.

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

    <p>This code was last modified on $Date: 1999/01/07 22:14:08 $

    @author Jim Jackl-Mochel
    @author $Author: jmochel $
    @version $Revision: 1.3 $


    @see PearsonsHash
    @see PearsonsHashUnitTestMain
    @since JDK 1.1
*/

public class PearsonsHashUnitTest
{
    public PearsonsHashUnitTest()
    {
    }

    /**
        Give PearsonsHash a vigorous shakedown.

        @see PearsonsHash
        @see PearsonsHashUnitTestMain
        @since JDK 1.1
    */

    public boolean Exercise()
    {
        // Create and populate the test data
        
        String[]    keys = { "caas", "cab", "cabaalistically", "cabal", "cabala", "cabalism", "cabalist", "cabalistical", "cabalize", "caballer",
                                    "caballine", "cabaret", "cabbage", "cabbler", "cabbling", "cabezon", "cabiai", "cabin", "cabinet", "cabinetmaker",
                                    "cabinetmaking", "cabinetwork", "cabirean", "cabirian", "cabiric", "cable", "cabled", "cablegram", "cablelaid",
                                    "cablet", "cabling", "cabman", "cabob", "caboched", "caboodle", "caboose", "cabotage", "cabrerite", "cabriole",
                                    "cabriolet", "cabrit", "caburn", "cacaine", "cacao", "cachalot", "cachinnation", "cachinnatory", "cacholong",
                                    "cachou", "cack", "cackerel", "cackle", "cackler", "cackling", "cacochymical", "cacochymy", "cacodemon", "cacodoxical",
                                    "cacodoxy", "cacodyl", "cacodylic", "cacogastric", "cacographic", "cacography", "cacology", "cacomixtle",
                                    "cacoon", "cacophonical", "cacophony", "cacotechny", "cactaceous", "cactus", "cacuminal", "cacuminate",
                                    "cad", "cadastral", "cadaveric", "cadaverous", "cadbait", "caddis", "caddish", "caddow", "caddy",
                                    "cade", "cadence", "cadency", "cadene", "cadent", "cadenza", "cader", "cadet", "cadetship", "cadge", "cadger",
                                    "cadgy", "cadi", "cadilesker", "cadillac", "cadis", "cadmean", "cadmia", "cadmian", "cadmic", "cadmium",
                                    "cadrans", "caducary", "caducean", "caduceus", "caducibranchiate", "caducity", "caducous", "caduke", "cady",
                                    "caen", "caffeic", "caffeine", "caffetannic", "caffre", "caftan", "cag", "cage", "caged", "cageling",
                                    "cagmag", "cahincic", "cahoot", "caiman", "cainozoic", "caird", "cairn"
                            };


        // Test the byte range hashing for strings

        System.out.println("PearsonsHashUnitTest: Begin generating byte range hash values");
                
        for ( int i = 0; i < keys.length; i++ )
        {
            System.out.println(PearsonsHash.generateByteRangeHash(keys[i]));
        }

        System.out.println("PearsonsHashUnitTest: End generating byte range hash values");
        
        // Test the int range hashing for strings

        System.out.println("PearsonsHashUnitTest: Begin generating int range hash values");
                
        for ( int i = 0; i < keys.length; i++ )
        {
            System.out.println(PearsonsHash.generateIntRangeHash(keys[i]));
        }
        
        System.out.println("PearsonsHashUnitTest: End generating int range hash values");
        
        return(true);
    }
}
