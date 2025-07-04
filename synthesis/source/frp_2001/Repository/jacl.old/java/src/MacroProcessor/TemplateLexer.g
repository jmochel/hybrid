/*
    Template Lexer
*/

header 
{
    package jacl.MacroProcessor;
}

{
    import antlr.*;
}

class TemplateLexer extends Lexer;
options
{
    k = 4;
    charVocabulary = '\3'..'\377';
	importVocab=Common;
	exportVocab=Template;
    filter=IGNORE;
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



EXPRESSIONLEADIN
	: "$"
	;


protected IGNORE
    options 
    {
         testLiterals =  true;
    }
    :    .    { System.out.print($getText); }
	| 	(' '|'\t')(' '|'\t')* { System.out.print($getText); }
    |    '\n' 
    	{
    		newline();
    		System.out.print($getText);
    	}
    |    '\r' 
    	{
    		newline();
    		System.out.print($getText);
    	}
    ;

COMMANDBEGIN
	: "."
	{ 
		if (_Column != 1)
		{
			$setType(Token.SKIP);
		}

    	System.out.println("Pushing over to the command lexer");
		Exec.streamSelector.push("commandLexer");
 	}
	;

    
protected IDENTIFIER
    options 
    {
         testLiterals =  true;
    }
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')*
    ;