/*
 * ANTLR-generated file resulting from grammar CodeGeneratorTextParser.g
 * 
 * Terence Parr, MageLang Institute
 * with John Lilley, Empathy Software
 * ANTLR Version 2.6.0; 1996-1999
 */
import java.io.IOException;
import antlr.TokenBuffer;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.ParserException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
/**
    Parses text that has dot commands and expressions interwoven.
*/
public class CodeGeneratorTextParser extends antlr.LLkParser
       implements CodeGeneratorTextParserTokenTypes
 {

protected CodeGeneratorTextParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public CodeGeneratorTextParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected CodeGeneratorTextParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public CodeGeneratorTextParser(TokenStream lexer) {
  this(lexer,1);
}

public CodeGeneratorTextParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

/**
    Look for commands and other text processing expressions
    
    <p>The Lexer has a filter that automatically echoes non-command text
    so we don't have to note it.
*/
	public final void startRule() throws ParserException, IOException {
		
		
		try {      // for error handling
			{
			_loop3:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					statement();
				}
				else {
					break _loop3;
				}
				
			} while (true);
			}
			match(Token.EOF_TYPE);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_1);
		}
	}
	
	public final void statement() throws ParserException, IOException {
		
		
		try {      // for error handling
			if ((_tokenSet_0.member(LA(1)))) {
				{
				_loop6:
				do {
					if ((LA(1)==INCLUDE||LA(1)==SET)) {
						dotCommand();
					}
					else {
						break _loop6;
					}
					
				} while (true);
				}
			}
			else if ((_tokenSet_0.member(LA(1)))) {
				{
				_loop8:
				do {
					if ((_tokenSet_2.member(LA(1)))) {
						expression();
					}
					else {
						break _loop8;
					}
					
				} while (true);
				}
			}
			else {
				throw new NoViableAltException(LT(1));
			}
			
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
	}
	
	public final void dotCommand() throws ParserException, IOException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case INCLUDE:
			{
				includeCommand();
				break;
			}
			case SET:
			{
				assignmentCommand();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
	}
	
	public final void expression() throws ParserException, IOException {
		
		
		try {      // for error handling
			if ((LA(1)==DOLLAR)) {
				macroInvocation();
			}
			else if ((LA(1)==DOLLAR)) {
				variableExpansion();
			}
			else if (((LA(1) >= INT && LA(1) <= DELIMITEDSTRINGLITERAL))) {
				literal();
			}
			else {
				throw new NoViableAltException(LT(1));
			}
			
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_3);
		}
	}
	
	public final void includeCommand() throws ParserException, IOException {
		
		
		try {      // for error handling
			if ((LA(1)==INCLUDE)) {
				match(INCLUDE);
				expression();
				match(NEWLINE);
			}
			else if ((LA(1)==INCLUDE)) {
				match(INCLUDE);
				stringLiteral();
				match(NEWLINE);
			}
			else {
				throw new NoViableAltException(LT(1));
			}
			
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
	}
	
	public final void assignmentCommand() throws ParserException, IOException {
		
		
		try {      // for error handling
			match(SET);
			identifier();
			match(ASSIGN);
			expression();
			match(NEWLINE);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
	}
	
	public final void stringLiteral() throws ParserException, IOException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case STRINGLITERAL:
			{
				match(STRINGLITERAL);
				break;
			}
			case DELIMITEDSTRINGLITERAL:
			{
				match(DELIMITEDSTRINGLITERAL);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_3);
		}
	}
	
	public final void identifier() throws ParserException, IOException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_4);
		}
	}
	
	public final void macroInvocation() throws ParserException, IOException {
		
		
		try {      // for error handling
			match(DOLLAR);
			identifier();
			match(LPAREN);
			{
			_loop15:
			do {
				if ((_tokenSet_5.member(LA(1)))) {
					argument();
				}
				else {
					break _loop15;
				}
				
			} while (true);
			}
			match(RPAREN);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_3);
		}
	}
	
	public final void variableExpansion() throws ParserException, IOException {
		
		
		try {      // for error handling
			match(DOLLAR);
			match(LCURLY);
			identifier();
			match(RCURLY);
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_3);
		}
	}
	
	public final void literal() throws ParserException, IOException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case INT:
			{
				numericLiteral();
				break;
			}
			case STRINGLITERAL:
			case DELIMITEDSTRINGLITERAL:
			{
				stringLiteral();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_3);
		}
	}
	
	public final void argument() throws ParserException, IOException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case DOLLAR:
			case INT:
			case STRINGLITERAL:
			case DELIMITEDSTRINGLITERAL:
			{
				expression();
				break;
			}
			case IDENT:
			{
				variable();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1));
			}
			}
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_6);
		}
	}
	
	public final void variable() throws ParserException, IOException {
		
		
		try {      // for error handling
			identifier();
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_6);
		}
	}
	
	public final void numericLiteral() throws ParserException, IOException {
		
		
		try {      // for error handling
			match(INT);
		}
		catch (ParserException ex) {
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
		"INCLUDE",
		"NEWLINE",
		"SET",
		"ASSIGN",
		"DOLLAR",
		"LPAREN",
		"RPAREN",
		"LCURLY",
		"RCURLY",
		"IDENT",
		"INT",
		"STRINGLITERAL",
		"DELIMITEDSTRINGLITERAL",
		"IGNORE",
		"IF",
		"ENDIF",
		"COMMA",
		"EQUAL",
		"LNOT",
		"BNOT",
		"NOT_EQUAL",
		"DIV",
		"DIV_ASSIGN",
		"PLUS",
		"PLUS_ASSIGN",
		"INC",
		"MINUS",
		"MINUS_ASSIGN",
		"DEC",
		"MULTIPLY",
		"MULTIPLY_ASSIGN",
		"MOD",
		"MOD_ASSIGN",
		"GE",
		"GT",
		"LE",
		"LT"
	};
	
	private static final long _tokenSet_0_data_[] = { 115026L, 0L };
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	private static final long _tokenSet_1_data_[] = { 2L, 0L };
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
	private static final long _tokenSet_2_data_[] = { 114944L, 0L };
	public static final BitSet _tokenSet_2 = new BitSet(_tokenSet_2_data_);
	private static final long _tokenSet_3_data_[] = { 124274L, 0L };
	public static final BitSet _tokenSet_3 = new BitSet(_tokenSet_3_data_);
	private static final long _tokenSet_4_data_[] = { 128896L, 0L };
	public static final BitSet _tokenSet_4 = new BitSet(_tokenSet_4_data_);
	private static final long _tokenSet_5_data_[] = { 123136L, 0L };
	public static final BitSet _tokenSet_5 = new BitSet(_tokenSet_5_data_);
	private static final long _tokenSet_6_data_[] = { 124160L, 0L };
	public static final BitSet _tokenSet_6 = new BitSet(_tokenSet_6_data_);
	
	}
