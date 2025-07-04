H07716
s 00245/00000/00000
d D 1.1 01/07/13 18:14:20 jmochel 2 1
cC
cF1
cK43796
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 01/07/13 18:14:20 jmochel 1 0
c BitKeeper file f:/Repository/mushin/C++/h/McTrie.hpp
cBjmochel@devilmountain.corp.foliage.com|ChangeSet|20010713220415|54911|c62b2c90
cHdevilmountain.corp.foliage.com
cK35025
cPC++/h/McTrie.hpp
cR474aa2d6
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
#ifndef MCTRIE_HPP
#define MCTRIE_HPP

/*
    @doc

    .Contains McTrie Class Definition

    .Author Jim Jackl-Mochel

    .Date 03.12.97

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#include <cstdlib>

#ifndef TYPES_HPP
#include "types.hpp"
#endif


// Defaults for the class configuration

const size_t  McTrie_DefSegSize = 3;
const size_t  McTrie_DefOffsetSize = 4;
const size_t  McTrie_DefTerminatorSize = 1;
const size_t  McTrie_DefRightSize = 1;
const size_t  McTrie_DefNodeCnt = 256;
const size_t  McTrie_DefTermBfrSize = 1024;

/*
    @ClassDesc

        A Indexing data structure similiar in use to a B-tree

    @ClassNotes

        A more generalized form of the McTrie detailed in :

            Multi-character tries for text searching. Cooper and Tharp.
                Information Processing & Management Vol. 29, No 2. pp 197-207 1993


        A McTrie is implemented here as an array of Nodes of the form:

        [s][e][g][T][R]

        where [s][e][g] is 3 or more bytes of the key being searched on.
        where [T] is the byte indicating that the current set of segments make a full term.
        where [R] is the byte giving the offset (in nodes) of the next node of the seme level

        If the [T] is true, then the next node will contain the addendum data as provided
            This will typically contain the offset of the data.
*/

class McTrie
{
    // @Access Public

    public:

        // @MemberDesc Constructor 

	    McTrie(void);

        // @MemberDesc Destructor

        ~McTrie(void);

        // @MemberDesc Accessor 

    	size_t GetBlockSize(void)
	    {
		    return(_BlockSize);
    	}

        // @MemberDesc Mutator

        void SetBlock(Byte* Block)
		{
			_Block = Block;
		}

        // @MemberDesc Accessor 

		Byte* GetBlock(void)
		{
			return(_Block);
		}

        // @MemberDesc Init to the defaults for a segment size of segmentSize

	    void	BeginAdd(size_t segmentSize = McTrie_DefSegSize);

        // @MemberDesc Add a term and addendum data

        bool	Add(std::string& newTerm, std::string& addendumData);

        // @MemberDesc Closeout this block of the McTrie

        void	EndAdd(void);

        // @MemberDesc Init to the defaults for a segment size of segmentSize

	    void	BeginLookup(void);

        // @MemberDesc Find the exact term specified

        bool    Lookup(const std::string& term, std::string& addendumData);

        // @MemberDesc Closeout this block of the McTrie

        void	EndLookup(void);

        // @MemberDesc Walk the McTrie and print out the contents of the raw nodes

        void    WalkAndPrintRawNodes(void);

        // @MemberDesc Walk the McTrie and print out the terms

        void    WalkAndPrintTerms(void);


    // @Access Protected

	protected:

        // @MemberDesc Set the McTrie defaults for segment size of segmentSize

        void    SetDefs(size_t segSize = McTrie_DefSegSize);

    // @Access Private

	private:

        //
		// The sizes of all of the node attributes
        //

		// @MemberDesc Size of a term segment (Bytes).

		size_t  _SegSize;

    	// @MemberDesc Size of a term offset (bytes).

		size_t  _OffsetSize;

    	// @MemberDesc Size of a term terminator (bytes).

		size_t  _TerminatorSize;

		// @MemberDesc Size of a term right ndx offset (bytes).

		size_t  _RightSize;

		// @MemberDesc Size of a Node (bytes).

		size_t	_NodeSize;

		// @MemberDesc Number of nodes in a block (nodes).

		size_t  	_NodeCnt;

		// @MemberDesc Size of the block (bytes).

		size_t  	_BlockSize;

		// @MemberDesc The actual block.

		Byte*	  	_Block;

        //
		// State and other info for walking and making the mctrie
        //

    	// @MemberDesc Index of the current node.

		size_t	_CurrNodeNdx;

    	// @MemberDesc Number of Nodes left to be filled.

		size_t	_NodesAvail;

        // @MemberDesc Tracks the current index for each active old node.

		Byte*	_RefLvlNdxs;

		// @MemberDesc Level of the deepest node in the reference (old) term (1 indexed).

		size_t	_RefLvl;

    	// @MemberDesc Tracks the current index for each node from a new term.

    	Byte*	_NewLvlNdxs;

		// @MemberDesc Old Term.

		std::string	 _OldTerm;
};

//
// A simple algorithm needed by some code somewhere
//


template <class ForwardIterator1, class ForwardIterator2>
ForwardIterator2 first_mismatch(ForwardIterator1 First1, ForwardIterator1 Last1,
	 ForwardIterator2 First2, ForwardIterator2 Last2)
{
	while ( First2 != Last2 && First1 != Last1 && *First2 == *First1 )
	{
		++First1;
		++First2;
	}

	return(First2);
}

//
// Node manipulation macros
//
// These should only be used by the McTrie class itself.
//

#define Node_GetSegment(NodeAddr)	(NodeAddr)
#define Node_GetRight(NodeAddr)	*((NodeAddr) + _SegSize + _TerminatorSize)
#define Node_GetTerminated(NodeAddr)	(bool) *((NodeAddr) + _SegSize)
#define Node_GetAddendumData(NodeAddr)	(NodeAddr)

#define Node_SetSegment(NodeAddr, NewSegAddr)	(memcpy( (void*) NodeAddr, (void*) NewSegAddr, _SegSize))
#define Node_SetRight(NodeAddr, Value)				( ((*((char*)(NodeAddr) + _SegSize + _TerminatorSize))) = Value)
#define Node_SetTerminated(NodeAddr, Value)		( ((*((char*)(NodeAddr) + _SegSize))) = Value)
#define Node_SetAddendumData(NodeAddr, AddendumDataAddr)	(memcpy( (void*) NodeAddr, (void*) AddendumDataAddr, _NodeSize))

#define GetNodeCnt(Len)  (Len / _SegSize)  +  ((Len % _SegSize) ? 1 : 0);

#endif

E 2
I 1
E 1
