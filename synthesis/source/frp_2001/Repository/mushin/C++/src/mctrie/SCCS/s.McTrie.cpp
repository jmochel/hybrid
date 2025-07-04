H52044
s 00609/00000/00000
d D 1.1 01/07/13 18:14:20 jmochel 2 1
cC
cF1
cK20911
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:20 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/src/mctrie/McTrie.cpp
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK30297
cPC++/src/mctrie/McTrie.cpp
cR47554560
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
    @doc

    .Author: Jim Jackl-Mochel

    .Date: 06.13.96

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#include <string>
#include <list>
#include <vector>
#include <deque>
#include <queue>
#include <algorithm>

#include "Types.hpp"
#include "Condition.hpp"
#include "Mctrie.hpp"

/*
    @MethodDesc
        
          Constructor
*/

McTrie::McTrie(void) 
{
	_Block = 0;
	_RefLvlNdxs = 0;
    _NewLvlNdxs = 0;

	SetDefs();
}


/*
    @MethodDesc
        
          Destructor

*/

McTrie::~McTrie(void)
{
	if ( _Block != 0 )
	{
		delete[] _Block;
	}

	if ( _RefLvlNdxs != 0 )
	{
		delete[] _RefLvlNdxs;
	}

	if ( _NewLvlNdxs != 0 )
	{
		delete[] _NewLvlNdxs;
	}
}
/*
    @MethodDesc
        
          Initializes the McTrie to a segment size of segSize

*/

void McTrie::BeginAdd(size_t segSize)
{
    Precondition(segSize >= McTrie_DefSegSize);     // Test to verify that segment size is large enough.

   	SetDefs(segSize);

	// Block size stuff

	_NodeCnt = McTrie_DefNodeCnt;
	_BlockSize = _NodeSize * _NodeCnt;

	if ( _Block == 0 )
	{
    	_Block = new Byte[_BlockSize];
	}

    memset(_Block, 0, _BlockSize);

	if ( _RefLvlNdxs == 0 )
	{
    	_RefLvlNdxs = new Byte[_NodeCnt];
	}

    memset(_RefLvlNdxs, 0, sizeof(_RefLvlNdxs));

	if ( _NewLvlNdxs == 0 )
	{
    	_NewLvlNdxs = new Byte[_NodeCnt];
	}

    memset(_NewLvlNdxs, 0, sizeof(_NewLvlNdxs));

	_OldTerm = "";

	// State and other info

	_NodesAvail = _NodeCnt;

	_CurrNodeNdx = 0;
	_RefLvl = 0;				// Level of the deepest node in the reference (old) term (1 indexed)
}

/*
    @MethodDesc
        
          Adds a term and some addendum data to a McTrie
*/

bool McTrie::Add(std::string& NewTerm, std::string& AddendumData)
{
	char*	MismatchPos;
	size_t	 MismatchNdx;
	size_t	 MismatchLen;
	size_t	 SegDiffBegin; // The first segment that is different in the two terms
	size_t	 SegDiffCnt;   // The number of segments that are different in the two terms
	size_t	 MaxLvl;
	size_t	 MinLvl;
	size_t	 NewLvl;				// Level of the deepest node in the new term (1 indexed)
    size_t   i;             // A random counter

	Byte	 NewTermBfr[McTrie_DefTermBfrSize];

    // Parameter Checks

    if ( AddendumData.length() != _NodeSize )
    {
        throw range_error("McTrie:Addendum Data length needs the same as the Node Size");
    }

    // Init the locals

	memset(NewTermBfr, '\0', McTrie_DefTermBfrSize);

	// Get the difference between the old and new terms.

	MismatchPos = first_mismatch(_OldTerm.begin(), _OldTerm.end(), NewTerm.begin(), NewTerm.end());

	// If there is no mismatch, we must already have added this term.

	if ( MismatchPos == NewTerm.end() )
	{
		return(true);
	}

	// Fill in the numbers for the difference
	// Now, if the mismatch starts in the middle of a segment, this could throw some of the numbers off
	// So we reset the mismatch pos to the beginning of the segment.

	MismatchNdx = MismatchPos - NewTerm.begin();
	MismatchLen = distance(MismatchPos, NewTerm.end());

	// True up the segment indexes appropriately

	MismatchLen = MismatchLen + (MismatchNdx % _SegSize);
	MismatchNdx = MismatchNdx - (MismatchNdx % _SegSize);

	SegDiffBegin = MismatchNdx/_SegSize;
	SegDiffCnt   = MismatchLen/_SegSize + ((MismatchLen % _SegSize) ? 1 : 0);

	// Check that we have this many empty segments. If not, we fail.
    // We add one to the SegDiffCnt to allow for the AddendumData

	if ( (SegDiffCnt + 1) > _NodesAvail )
	{
		return(false);
	}

	// Init the new level.

	NewLvl = SegDiffBegin + SegDiffCnt -1;

	//
	//	Add the new nodes to the Block
	//

	// Get the range of new nodes to be added

	MaxLvl = _MAX(_RefLvl, NewLvl);
	MinLvl = _MIN(_RefLvl, NewLvl);

	// Truncate that range if the new term is shorter than the old term

	if ( MaxLvl > (SegDiffBegin + SegDiffCnt -1) )
	{
		MaxLvl = SegDiffBegin + SegDiffCnt -1;
	}

	// Place the segments of the new nodes into the block

	memcpy(NewTermBfr, NewTerm.c_str(), NewTerm.length());

	for ( i = SegDiffBegin; i <= MaxLvl; i++ )
	{
		Node_SetSegment( &(_Block[_CurrNodeNdx * _NodeSize]), (NewTermBfr + (i * _SegSize) ) );
		_NewLvlNdxs[i] = _CurrNodeNdx;
		_CurrNodeNdx++;
		_NodesAvail--;
	}

	// Set the terminator

	Node_SetTerminated(&(_Block[(_CurrNodeNdx-1) * _NodeSize]), true);

	// Set the Addendum Data

    Node_SetAddendumData(&(_Block[(_CurrNodeNdx) * _NodeSize]), AddendumData.c_str());
    _CurrNodeNdx++;
    _NodesAvail--;

	// Set the Right attributes

	for ( i = SegDiffBegin; i <= MaxLvl; i++ )
	{
		if ( (i <= MinLvl) && (i == SegDiffBegin) )
		{
			Node_SetRight( &(_Block[(_RefLvlNdxs[i]*_NodeSize)]), (_NewLvlNdxs[i] - _RefLvlNdxs[i]) );
		}

		_RefLvlNdxs[i] = _NewLvlNdxs[i];
	}

	// Clean up any leftover RefLvl values

	if ( _RefLvl > NewLvl )
	{
		for ( i = NewLvl+1; i <= _RefLvl; i++ )
		{
			_RefLvlNdxs[i] = 0;
		}
	}

	// Update working variables

	_RefLvl = NewLvl;
	_OldTerm = NewTerm;

    return(true);
}

/*
    @MethodDesc
        
        Deinitializes the McTrie.

    @MethodNotes

        This cleans up and finalizes the McTrie currently in memory.
*/

void McTrie::EndAdd(void)
{
	for ( size_t i = 0; i <= _RefLvl; i++ )
	{
		Node_SetRight( &(_Block[(_RefLvlNdxs[i]*_NodeSize)]), _NodeCnt - _RefLvlNdxs[i]-1);
	}

	_NodesAvail = _NodeCnt;
	_CurrNodeNdx = 0;
}

/*
    @MethodDesc
        
          Signals the start of looking up terms
*/

void McTrie::BeginLookup(void)
{
	SetDefs();
}

/*
    @MethodDesc
        
          End the looking up of terms

*/

void McTrie::EndLookup(void)
{
}

/*
    @MethodDesc
        
          Sets the defaults for a segment size of segSize
*/

void McTrie::SetDefs(size_t segSize)
{
    Precondition(segSize >= McTrie_DefSegSize);     // Test to verify that segment size is large enough.

	// Init Node attribute sizes

	_SegSize    = segSize;
	_OffsetSize = McTrie_DefOffsetSize;
	_TerminatorSize = McTrie_DefTerminatorSize;
	_RightSize  = McTrie_DefRightSize;

	_NodeSize = _SegSize + _TerminatorSize + _RightSize;
}


/*
    @MethodDesc
        
          Walks and prints all the raw nodes in the McTrie

    @ToDo

        Change this to an iterator that walks the McTrie
*/

void McTrie::WalkAndPrintRawNodes(void)
{
	Byte*	 CurrNode;		 // Pointer to the current node.

	// Initialize

	for ( _CurrNodeNdx = 0; _CurrNodeNdx < _NodeCnt; _CurrNodeNdx++ )
	{
		// Where we are

		CurrNode = _Block + (_CurrNodeNdx * _NodeSize);

		if (Node_GetSegment(CurrNode)[0] == 0)
		{
			break;
		}

		// Dump the Node Contents

		fprintf(stdout, "[%i] [", _CurrNodeNdx);

          for ( size_t i = 0; i < _SegSize; i++ )
          {
			    if (Node_GetSegment(CurrNode)[i] != 0)
			    {
				    fprintf(stdout, "%c", Node_GetSegment(CurrNode)[i]);
			    }
             else {
				    fprintf(stdout, " ");
             }
          }

		fprintf(stdout, "] ");

		// print out the other node attributes

		fprintf(stdout, " [%i] ", (unsigned int) Node_GetTerminated(CurrNode));
		fprintf(stdout, " [%i] ", (unsigned int) Node_GetRight(CurrNode));
		fprintf(stdout, "\n");
	}
}

/*
    @MethodDesc
        
          Walks and prints all the terms in the McTrie

    @ToDo

        Change this to an iterator that walks the McTrie
*/

void McTrie::WalkAndPrintTerms(void)
{
	Byte*	currNode;		 // Pointer to the current node.
	Byte    currLvl;		 // Level of the current node.

    Byte    termBfr[256 * 3];


    typedef short int  Level;
    typedef pair<Byte, Level>   NdxAndLvl;

    // The NdxAndLvls queue is a sorted queue of the next index at 
    // which the level is expected to change and what level it will change to.

	priority_queue<NdxAndLvl, vector<NdxAndLvl>,greater<NdxAndLvl> > 	NdxAndLvls;

	// Initialize

	currLvl = 0;

    NdxAndLvls.push(NdxAndLvl(_NodeCnt-1,0));    // This should not be removed by the walking process
	NdxAndLvls.push(NdxAndLvl(Node_GetRight(_Block), 0));

    // Walk through the nodes. 

	for ( _CurrNodeNdx = 0; _CurrNodeNdx < _NodeCnt; _CurrNodeNdx++ )
	{
        // Determine whether or not we should jump down to a different level 

        printf("Top of Loop: _CurrNodeNdx = %d\n", _CurrNodeNdx );
        printf("Top of Loop: NdxAndLvls.top().first = %d\n", NdxAndLvls.top().first );
        printf("Top of Loop: NdxAndLvls.top().second = %d\n", NdxAndLvls.top().second );

        while ( (NdxAndLvls.size() > 0) && ( _CurrNodeNdx >= NdxAndLvls.top().first) )
        {
            currLvl = NdxAndLvls.top().second;
            NdxAndLvls.pop();

            printf("Popping off: NdxAndLvls.top().first = %d\n", NdxAndLvls.top().first );
            printf("Popping off: NdxAndLvls.top().second = %d\n", NdxAndLvls.top().second );
        }

		// Where we are

		currNode = _Block + (_CurrNodeNdx * _NodeSize);

		//
		// Now compose the term
		//

		// Copy the segment into the appropriate level of the term buffer

		memcpy( termBfr + (currLvl * _SegSize), Node_GetSegment(currNode), _SegSize);

		// If this node comprises a part of a whole term, print it.

		if ( Node_GetTerminated(currNode) == true )
		{
            // Print out the term.

			for ( size_t i = 0; i < ((currLvl+1) * _SegSize); i++ )
			{
				if ( termBfr[i] != 0 )
				{
					fprintf(stdout, "%c", termBfr[i]);
				}
			}

			fprintf(stdout, " => ");
		}

		// Update the position indicators

		if ( Node_GetRight(currNode) >= 1 )
		{
			NdxAndLvls.push(NdxAndLvl(_CurrNodeNdx + Node_GetRight(currNode), currLvl));
            printf("Adding On: _CurrNodeNdx = %d\n", _CurrNodeNdx );
            printf("Adding On: NdxAndLvl.first = %d\n", _CurrNodeNdx + Node_GetRight(currNode) );
            printf("Adding On: NdxAndLvl.second = %d\n", currLvl );

            printf("Adding On: NdxAndLvls.top().first = %d\n", NdxAndLvls.top().first );
            printf("Adding On: NdxAndLvls.top().second = %d\n", NdxAndLvls.top().second );

            currLvl++;
    	}
		else // Right == 0
		{
            currLvl++;
		}

        // After all this , now print out the addendum data and advance the state

		if ( Node_GetTerminated(currNode) == true )
		{
            // Print out the Addendum data

            _CurrNodeNdx++;
            currNode += _NodeSize;

			for ( size_t i = 0; i < _NodeSize; i++ )
			{
				fprintf(stdout, "%c", currNode[i]);
			}

			fprintf(stdout, "\n");
        }
	}
}


/*
    @MethodDesc
        
          Find the exact term specified

    @MethodNotes
        
        <???>

    @Parm        Term to be located
    @Parm        Addendum Data found


*/

bool McTrie::Lookup(const std::string& term, std::string& addendumData)
{
	Byte*   currNode;		 // Pointer to the current node.
	Byte    currLvl;		 // Level of the current node.

	Byte	lvlAtNdx[256+1];	// Array of Levels associated with a given node (ndx).
	Byte	nextNdx[256+1];	// Array of indexes associated with a given level.

    Byte    termBfr[McTrie_DefTermBfrSize];
    int     order;
    bool    searchIsDone = false;
    size_t  termNodeCnt;

	deque<Byte>	jumpDownLvls;

	// Initialize

	memset(lvlAtNdx, '\0', sizeof(lvlAtNdx));
	memset(nextNdx, '\0', sizeof(nextNdx));
    memcpy(termBfr, term.c_str(),  term.size());

    _CurrNodeNdx = 0;
    currLvl = 0;

    termNodeCnt = GetNodeCnt(term.size());

	while ( searchIsDone == false )
	{
		// Where we are

		currNode = _Block + (_CurrNodeNdx * _NodeSize);

		//
		// Now compare the term
		//

		// Compare the segment

		order = memcmp( termBfr + (currLvl * _SegSize), Node_GetSegment(currNode), _SegSize);

        if ( order > 0 )
        {
            // If the term is less than the stored segment (lexigraphic order) 
            // we simply go on to the next node.

            _CurrNodeNdx += Node_GetRight(currNode);
        }
        else if ( order < 0 )
        {
            // If the term is greater than the stored segment (lexigraphic order) 
            // we have gone to far !

            return(false);
        }
        else {

            // The segment and the term are equal so we have all sorts of decisions 
            // to make.

            // If there is more of the term to find

            if ( termNodeCnt > (currLvl +1) )
            {
                // if there are more terms in the trie
                // just go down a level

                if ( Node_GetRight(currNode) > 1 ) 
                {
                    currLvl++;
                    _CurrNodeNdx += 1;
                }
                else {

                    // There is no instance of this term in the trie.

                    return(false);
                }
            }
            else    // We are at the end of the term to be found.
            {
                if ( Node_GetTerminated(currNode) == true ) 
                {
                    // Populate the addendum

                    _CurrNodeNdx++;
                    currNode += _NodeSize;

                    addendumData.copy((char*) currNode, sizeof(Byte), _NodeSize);
                    addendumData.resize(_NodeSize);

                    return(true);
                }
                else {

                    // There is no instance of this term in the trie.

                    return(false);
                }

            }
        }
	}

    return(false);
}
E 2
I 1
E 1
