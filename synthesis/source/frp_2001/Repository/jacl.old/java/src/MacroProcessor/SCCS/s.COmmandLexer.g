h60740
s 00178/00033/00020
d D 1.3 00/02/29 17:09:48 jmochel 4 3
c Added a bit more code.
cC
cK30690
e
s 00011/00008/00042
d D 1.2 99/12/02 17:11:06 jmochel 3 2
c Now does basic recognition of a variable expansion
cC
cK31834
e
s 00050/00000/00000
d D 1.1 99/11/23 13:24:39 jmochel 2 1
cC
cK28678
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/23 13:24:35 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/COmmandLexer.g
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK31843
cPjava/src/MacroProcessor/COmmandLexer.g
cRae99b12f5cb68643
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 4
/*
    Template Command Lexer
*/

E 4
I 2
D 3
/*
    Template Text Lexer
*/

header
E 3
I 3
header 
E 3
{
    package jacl.MacroProcessor;
}

{
D 4
    import java.io.*;
E 4
I 3
    import antlr.*;
E 3
}

class CommandLexer extends Lexer;
D 4
options 
E 4
I 4
options
{
    k = 4;
    charVocabulary = '\3'..'\377';
	importVocab=Common;
	exportVocab=Command;
}
E 4
{
D 3
  charVocabulary = '\3'..'\177';
E 3
I 3
D 4
    exportVocab=Command;
E 3
} 
E 4
I 4

	// Column tracking information

	protected int _TokenColumn = 1;

  	protected int _Column = 1;


	/*
		Override the consume method tpo track column information
	*/

	public void consume() throws CharStreamException 
	{

		if ( inputState.guessing==0 ) 
		{
			if (text.length()==0) 
			{
				// remember token start column

				_TokenColumn = _Column;
			}
			
			if ( LA(1) == '\n')
			{ 
				_Column = 0; 
			}
			else 
			{ 
				_Column++; 
			}
		}

		super.consume();
	}

	/*
		Override the makeToken method to create a special version of tokens that track column information
	*/

  	protected Token makeToken(int tokenId) 
  	{
	    Token tkn = super.makeToken(tokenId);

	    tkn.setColumn(_TokenColumn);

	    return tkn;
	}
}


/*
	These are the series of command "keywords" that only occur atthe start of the line
*/

SET
    options 
    {
         testLiterals =  true;
    }
	: "set"
	;



COMMENT
    options 
    {
         testLiterals =  true;
    }
	: "."	
	;
E 4

INCLUDE
D 4
    : ".include"
    ;
E 4
I 4
    options
    {
         testLiterals =  true;
    }
	: "include"
	;
E 4

D 4
LPAREN    
    : '('
    ;
    
RPAREN
    : ')'
    ;
    
LCURLY
    : '{'
    ;
    
RCURLY
    : '}'
    ;
    
DOLLAR
    : '$'
    ;
E 4
I 4
REDIRECT
    options 
    {
         testLiterals =  true;
    }
	: "redirect"
	;
E 4

D 4
IDENT
D 3
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$')*
E 3
I 3
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
E 4
I 4

EQUALS
    options 
    {
         testLiterals =  true;
    }
	: "="
E 4
E 3
	;

D 4
STRINGLITERAL
D 3
	:	'"' (~('"'|'\\'))* '"'
E 3
I 3
	:	'"' (~('"'))* '"'
E 4
I 4
PLUSEQUALS
    options
    {
         testLiterals =  true;
    }
	: "+="
	;

EXPRESSIONLEADIN
    options 
    {
         testLiterals =  true;
    }
	: "$"
	;

OPENCURLY
    options 
    {
         testLiterals =  true;
    }
	: "{"
E 4
E 3
	;
I 3

D 4
DOTCOMMAND_END
    : '\r'
    | '\n'
    { Exec.selector.pop(); }
E 4
I 4
CLOSECURLY
    options 
    {
         testLiterals =  true;
    }
	: "}"
	;

OPENPAREN
    options 
    {
         testLiterals =  true;
    }
	: "("
	;

CLOSEPAREN
    options 
    {
         testLiterals =  true;
    }
	: ")"
	;

STRINGLITERAL
    options 
    {
         testLiterals =  true;
    }
    :    '"' (~('"'))* '"'
    ;

    
COMMANDEND
	: '\n'
    {
    	newline();
		Exec.streamSelector.pop();
	}
    |    '\r' 
    {
    	newline();
		Exec.streamSelector.pop();
	}
    ;
    
protected IDENTIFIER
    options 
    {
         testLiterals =  true;
    }
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')*
E 4
    ;
E 3
E 2
I 1
E 1
