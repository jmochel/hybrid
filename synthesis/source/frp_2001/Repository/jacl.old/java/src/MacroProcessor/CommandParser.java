// $ANTLR 2.7.0: "CommandParser.g" -> "CommandParser.java"$

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
public class CommandParser extends antlr.LLkParser
       implements CommandParserTokenTypes
 {

protected CommandParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public CommandParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected CommandParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public CommandParser(TokenStream lexer) {
  this(lexer,1);
}

public CommandParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

/**
    Look for commands and other text processing expressions
*/
	public final void command() throws RecognitionException, TokenStreamException {
		
		traceIn("command");
		try { // debugging
			
			try {      // for error handling
				switch ( LA(1)) {
				case COMMENT:
				case INCLUDE:
				case REDIRECT:
				{
					generalCommand();
					break;
				}
				case SET:
				{
					variableCommand();
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("command");
		}
	}
	
	public final void generalCommand() throws RecognitionException, TokenStreamException {
		
		traceIn("generalCommand");
		try { // debugging
			
			try {      // for error handling
				switch ( LA(1)) {
				case COMMENT:
				{
					comment();
					break;
				}
				case INCLUDE:
				{
					include();
					break;
				}
				case REDIRECT:
				{
					redirection();
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("generalCommand");
		}
	}
	
	public final void variableCommand() throws RecognitionException, TokenStreamException {
		
		traceIn("variableCommand");
		try { // debugging
			
			try {      // for error handling
				if ((LA(1)==SET)) {
					setCommand();
				}
				else if ((LA(1)==SET)) {
					appendCommand();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("variableCommand");
		}
	}
	
	public final void comment() throws RecognitionException, TokenStreamException {
		
		traceIn("comment");
		try { // debugging
			
			try {      // for error handling
				match(COMMENT);
				matchNot(EOF);
				match(NEWLINE);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("comment");
		}
	}
	
	public final void include() throws RecognitionException, TokenStreamException {
		
		traceIn("include");
		try { // debugging
			
			try {      // for error handling
				match(INCLUDE);
				expression();
				match(NEWLINE);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("include");
		}
	}
	
	public final void redirection() throws RecognitionException, TokenStreamException {
		
		traceIn("redirection");
		try { // debugging
			
			try {      // for error handling
				match(REDIRECT);
				expression();
				match(NEWLINE);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("redirection");
		}
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		traceIn("expression");
		try { // debugging
			
			try {      // for error handling
				switch ( LA(1)) {
				case EXPRESSIONLEADIN:
				{
					expansion();
					break;
				}
				case STRINGLITERAL:
				{
					match(STRINGLITERAL);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_1);
			}
		} finally { // debugging
			traceOut("expression");
		}
	}
	
	public final void setCommand() throws RecognitionException, TokenStreamException {
		
		traceIn("setCommand");
		try { // debugging
			
			try {      // for error handling
				match(SET);
				var();
				match(EQUALS);
				expression();
				{
				_loop9:
				do {
					if ((LA(1)==EXPRESSIONLEADIN||LA(1)==STRINGLITERAL)) {
						expression();
					}
					else {
						break _loop9;
					}
					
				} while (true);
				}
				match(NEWLINE);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("setCommand");
		}
	}
	
	public final void appendCommand() throws RecognitionException, TokenStreamException {
		
		traceIn("appendCommand");
		try { // debugging
			
			try {      // for error handling
				match(SET);
				var();
				match(PLUSEQUALS);
				expression();
				{
				_loop12:
				do {
					if ((LA(1)==EXPRESSIONLEADIN||LA(1)==STRINGLITERAL)) {
						expression();
					}
					else {
						break _loop12;
					}
					
				} while (true);
				}
				match(NEWLINE);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
		} finally { // debugging
			traceOut("appendCommand");
		}
	}
	
	public final void var() throws RecognitionException, TokenStreamException {
		
		traceIn("var");
		try { // debugging
			
			try {      // for error handling
				match(EXPRESSIONLEADIN);
				match(IDENTIFIER);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_2);
			}
		} finally { // debugging
			traceOut("var");
		}
	}
	
	public final void expansion() throws RecognitionException, TokenStreamException {
		
		traceIn("expansion");
		try { // debugging
			
			try {      // for error handling
				if ((LA(1)==EXPRESSIONLEADIN)) {
					varExpansion();
				}
				else if ((LA(1)==EXPRESSIONLEADIN)) {
					macroInvocation();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_1);
			}
		} finally { // debugging
			traceOut("expansion");
		}
	}
	
	public final void varExpansion() throws RecognitionException, TokenStreamException {
		
		traceIn("varExpansion");
		try { // debugging
			
			try {      // for error handling
				match(EXPRESSIONLEADIN);
				match(OPENCURLY);
				var();
				match(CLOSECURLY);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_1);
			}
		} finally { // debugging
			traceOut("varExpansion");
		}
	}
	
	public final void macroInvocation() throws RecognitionException, TokenStreamException {
		
		traceIn("macroInvocation");
		try { // debugging
			
			try {      // for error handling
				match(EXPRESSIONLEADIN);
				match(IDENTIFIER);
				match(OPENPAREN);
				expression();
				match(CLOSEPAREN);
			}
			catch (RecognitionException ex) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_1);
			}
		} finally { // debugging
			traceOut("macroInvocation");
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
		"SET",
		"COMMENT",
		"INCLUDE",
		"REDIRECT",
		"EQUALS",
		"PLUSEQUALS",
		"EXPRESSIONLEADIN",
		"OPENCURLY",
		"CLOSECURLY",
		"OPENPAREN",
		"CLOSEPAREN",
		"STRINGLITERAL",
		"IDENTIFIER",
		"NEWLINE"
	};
	
	private static final long _tokenSet_0_data_[] = { 2L, 0L };
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	private static final long _tokenSet_1_data_[] = { 1449984L, 0L };
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
	private static final long _tokenSet_2_data_[] = { 38912L, 0L };
	public static final BitSet _tokenSet_2 = new BitSet(_tokenSet_2_data_);
	
	}
