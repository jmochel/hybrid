h07187
s 00270/00000/00000
d D 1.1 99/11/17 12:48:44 jmochel 2 1
cC
cK33716
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:48:40 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/mctrie/McTrieUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45324
cPC++/src/mctrie/McTrieUnitTest.cpp
cR2f93d7a55cb6ba86
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
    @doc

    .Contains: Unit Test for McTrie

    .Author: Jim Jackl-Mochel

    .Date: 10.16.96

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:32 $
*/

#include <string>
#include <list>
#include <vector>
#include <deque>
#include <algorithm>

#include "Reporter.hpp"
#include "McTrie.hpp"

Reporter    GlobalReporter(AppInfo(std::string("mctrieunittest"),std::string("1.00.00")));

/*
    @ProcDesc

        Main for the McTrie Unit Test


*/

int main(int argc, char* argv[])
{
	McTrie							trie;
	list<std::string>			        terms;
    bool                            termWasAdded;
    size_t                          segmentSize = 3;    // A comfortable default
    std::string                          addendumData;
    bool                            termWasFound;

    // Populate the list;

    terms.push_back(std::string("caas"));
    terms.push_back(std::string("cab"));
    terms.push_back(std::string("cabaalistically"));
    terms.push_back(std::string("cabal"));
    terms.push_back(std::string("cabala"));
    terms.push_back(std::string("cabalism"));
    terms.push_back(std::string("cabalist"));
    terms.push_back(std::string("cabalistical"));
    terms.push_back(std::string("cabalize"));
    terms.push_back(std::string("caballer"));
    terms.push_back(std::string("caballine"));
    terms.push_back(std::string("cabaret"));
    terms.push_back(std::string("cabbage"));
    terms.push_back(std::string("cabbler"));
    terms.push_back(std::string("cabbling"));
    terms.push_back(std::string("cabezon"));
    terms.push_back(std::string("cabiai"));
    terms.push_back(std::string("cabin"));
    terms.push_back(std::string("cabinet"));
    terms.push_back(std::string("cabinetmaker"));
    terms.push_back(std::string("cabinetmaking"));
    terms.push_back(std::string("cabinetwork"));
    terms.push_back(std::string("cabirean"));
    terms.push_back(std::string("cabirian"));
    terms.push_back(std::string("cabiric"));
    terms.push_back(std::string("cable"));
    terms.push_back(std::string("cabled"));
    terms.push_back(std::string("cablegram"));
    terms.push_back(std::string("cablelaid"));
    terms.push_back(std::string("cablet"));
    terms.push_back(std::string("cabling"));
    terms.push_back(std::string("cabman"));
    terms.push_back(std::string("cabob"));
    terms.push_back(std::string("caboched"));
    terms.push_back(std::string("caboodle"));
    terms.push_back(std::string("caboose"));
    terms.push_back(std::string("cabotage"));
    terms.push_back(std::string("cabrerite"));
    terms.push_back(std::string("cabriole"));
    terms.push_back(std::string("cabriolet"));
    terms.push_back(std::string("cabrit"));
    terms.push_back(std::string("caburn"));
    terms.push_back(std::string("cacaine"));
    terms.push_back(std::string("cacao"));
    terms.push_back(std::string("cachalot"));
    terms.push_back(std::string("cachinnation"));
    terms.push_back(std::string("cachinnatory"));
    terms.push_back(std::string("cacholong"));
    terms.push_back(std::string("cachou"));
    terms.push_back(std::string("cack"));
    terms.push_back(std::string("cackerel"));
    terms.push_back(std::string("cackle"));
    terms.push_back(std::string("cackler"));
    terms.push_back(std::string("cackling"));
    terms.push_back(std::string("cacochymical"));
    terms.push_back(std::string("cacochymy"));
    terms.push_back(std::string("cacodemon"));
    terms.push_back(std::string("cacodoxical"));
    terms.push_back(std::string("cacodoxy"));
    terms.push_back(std::string("cacodyl"));
    terms.push_back(std::string("cacodylic"));
    terms.push_back(std::string("cacogastric"));
    terms.push_back(std::string("cacographic"));
    terms.push_back(std::string("cacography"));
    terms.push_back(std::string("cacology"));
    terms.push_back(std::string("cacomixtle"));
    terms.push_back(std::string("cacoon"));
    terms.push_back(std::string("cacophonical"));
    terms.push_back(std::string("cacophony"));
    terms.push_back(std::string("cacotechny"));
    terms.push_back(std::string("cactaceous"));
    terms.push_back(std::string("cactus"));
    terms.push_back(std::string("cacuminal"));
    terms.push_back(std::string("cacuminate"));
    terms.push_back(std::string("cad"));
    terms.push_back(std::string("cadastral"));
    terms.push_back(std::string("cadaveric"));
    terms.push_back(std::string("cadaverous"));
    terms.push_back(std::string("cadbait"));
    terms.push_back(std::string("caddis"));
    terms.push_back(std::string("caddish"));
    terms.push_back(std::string("caddow"));
    terms.push_back(std::string("caddy"));
    terms.push_back(std::string("cade"));
    terms.push_back(std::string("cadence"));
    terms.push_back(std::string("cadency"));
    terms.push_back(std::string("cadene"));
    terms.push_back(std::string("cadent"));
    terms.push_back(std::string("cadenza"));
    terms.push_back(std::string("cader"));
    terms.push_back(std::string("cadet"));
    terms.push_back(std::string("cadetship"));
    terms.push_back(std::string("cadge"));
    terms.push_back(std::string("cadger"));
    terms.push_back(std::string("cadgy"));
    terms.push_back(std::string("cadi"));
    terms.push_back(std::string("cadilesker"));
    terms.push_back(std::string("cadillac"));
    terms.push_back(std::string("cadis"));
    terms.push_back(std::string("cadmean"));
    terms.push_back(std::string("cadmia"));
    terms.push_back(std::string("cadmian"));
    terms.push_back(std::string("cadmic"));
    terms.push_back(std::string("cadmium"));
    terms.push_back(std::string("cadrans"));
    terms.push_back(std::string("caducary"));
    terms.push_back(std::string("caducean"));
    terms.push_back(std::string("caduceus"));
    terms.push_back(std::string("caducibranchiate"));
    terms.push_back(std::string("caducity"));
    terms.push_back(std::string("caducous"));
    terms.push_back(std::string("caduke"));
    terms.push_back(std::string("cady"));
    terms.push_back(std::string("caen"));
    terms.push_back(std::string("caffeic"));
    terms.push_back(std::string("caffeine"));
    terms.push_back(std::string("caffetannic"));
    terms.push_back(std::string("caffre"));
    terms.push_back(std::string("caftan"));
    terms.push_back(std::string("cag"));
    terms.push_back(std::string("cage"));
    terms.push_back(std::string("caged"));
    terms.push_back(std::string("cageling"));
    terms.push_back(std::string("cagmag"));
    terms.push_back(std::string("cahincic"));
    terms.push_back(std::string("cahoot"));
    terms.push_back(std::string("caiman"));
    terms.push_back(std::string("cainozoic"));
    terms.push_back(std::string("caird"));
    terms.push_back(std::string("cairn"));

    // Sort the terms

    terms.sort();

    // Build the McTrie
    
    try {

        //
        // Add the terms to the trie
	    //

        trie.BeginAdd(segmentSize);

	    size_t termCnt = terms.size();

	    for ( size_t i = 0; i < termCnt; i++ )
	    {
            try {
                termWasAdded = trie.Add(terms.front(), std::string(2 + segmentSize, 'X'));
            }
            catch (const range_error& error)
            {
    		    exit(EXIT_FAILURE);
            }

		    if ( termWasAdded == false ) // The trie is full
		    {
			    // Finish off the trie

			    trie.EndAdd();

                // Walk and display the Raw nodes

                trie.WalkAndPrintRawNodes();

                // Walk and display the results

                trie.WalkAndPrintTerms();

                // Check the ability to search

                trie.BeginLookup();

                termWasFound = trie.Lookup(std::string("cabalize"), addendumData);
                termWasFound = trie.Lookup(std::string("cad"), addendumData);
                termWasFound = trie.Lookup(std::string("cage"), addendumData);
                termWasFound = trie.Lookup(std::string("caffeine"), addendumData);

                trie.EndLookup();
            
			    // Reinit

			    trie.BeginAdd(segmentSize);
		    }
		    else {
			    terms.pop_front();
		    }
	    }
    }
    catch (...)
    {
        exit(EXIT_FAILURE);
    }

    try {

	    //
	    // Clean up any remainder in the last trie
        //

	    trie.EndAdd();

        // Walk and display the results

        trie.WalkAndPrintRawNodes();

        // Walk and display the results

        trie.WalkAndPrintTerms();
    }
    catch (...)
    {
        exit(EXIT_FAILURE);
    }


	// Return

	return(EXIT_SUCCESS);
}
E 2
I 1
E 1
