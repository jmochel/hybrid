/*
    Template Command Lexer
*/

header 
{
    package jacl.MacroProcessor;
}

{
    import antlr.*;
}

class CommandLexer extends Lexer;
options
{
    k = 4;
    charVocabulary = '\3'..'\377';
	importVocab=Common;
	exportVocab=Command;
}
{

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

INCLUDE
    options
    {
         testLiterals =  true;
    }
	: "include"
	;

REDIRECT
    options 
    {
         testLiterals =  true;
    }
	: "redirect"
	;


EQUALS
    options 
    {
         testLiterals =  true;
    }
	: "="
	;

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
	;

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
    ;
