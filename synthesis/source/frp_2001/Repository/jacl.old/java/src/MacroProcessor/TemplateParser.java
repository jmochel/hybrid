// $ANTLR 2.7.0: "TemplateParser.g" -> "TemplateParser.java"$

    package jacl.MacroProcessor;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

    import java.io.*;
    import antlr.*;

/**
    Parses text that has dot commands and expressions interwoven.
*/
public class TemplateParser extends antlr.LLkParser
       implements TemplateParserTokenTypes
 {

protected TemplateParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TemplateParser(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected TemplateParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TemplateParser(TokenStream lexer) {
  this(lexer,3);
}

public TemplateParser(ParserSharedInputState state) {
  super(state,3);
  tokenNames = _tokenNames;
}

	public final void template() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case COMMANDBEGIN:
			{
				command();
				break;
			}
			case EXPRESSIONLEADIN:
			{
				expansion();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop4:
			do {
				switch ( LA(1)) {
				case COMMANDBEGIN:
				{
					command();
					break;
				}
				case EXPRESSIONLEADIN:
				{
					expansion();
					break;
				}
				default:
				{
					break _loop4;
				}
				}
			} while (true);
			}
			match(Token.EOF_TYPE);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
	}
	
	public final void command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(COMMANDBEGIN);
			
					CommandParser commandParser = new CommandParser(getInputState());
					commandParser.command();
				
			match(COMMANDEND);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_1);
		}
	}
	
	public final void expansion() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((LA(1)==EXPRESSIONLEADIN) && (LA(2)==OPENCURLY)) {
				varExpansion();
			}
			else if ((LA(1)==EXPRESSIONLEADIN) && (LA(2)==IDENTIFIER)) {
				macroInvocation();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_2);
		}
	}
	
	public final void varExpansion() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(EXPRESSIONLEADIN);
			match(OPENCURLY);
			var();
			match(CLOSECURLY);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_2);
		}
	}
	
	public final void macroInvocation() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(EXPRESSIONLEADIN);
			match(IDENTIFIER);
			match(OPENPAREN);
			expansion();
			match(CLOSEPAREN);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_2);
		}
	}
	
	public final void var() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(EXPRESSIONLEADIN);
			match(IDENTIFIER);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_3);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"<4>",
		"COMMANDBEGIN",
		"COMMANDEND",
		"EXPRESSIONLEADIN",
		"IGNORE",
		"IDENTIFIER",
		"OPENCURLY",
		"CLOSECURLY",
		"OPENPAREN",
		"CLOSEPAREN"
	};
	
	private static final long _tokenSet_0_data_[] = { 2L, 0L };
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	private static final long _tokenSet_1_data_[] = { 162L, 0L };
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
	private static final long _tokenSet_2_data_[] = { 8354L, 0L };
	public static final BitSet _tokenSet_2 = new BitSet(_tokenSet_2_data_);
	private static final long _tokenSet_3_data_[] = { 2048L, 0L };
	public static final BitSet _tokenSet_3 = new BitSet(_tokenSet_3_data_);
	
	}
