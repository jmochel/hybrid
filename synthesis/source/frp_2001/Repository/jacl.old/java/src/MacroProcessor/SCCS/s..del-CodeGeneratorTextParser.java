h37237
s 00000/00000/00451
d D 1.3 99/11/23 12:12:39 jmochel 4 3
c Delete: java/src/MacroProcessor/CodeGeneratorTextParser.java
cC
cK39340
cPjava/src/MacroProcessor/.del-CodeGeneratorTextParser.java
e
s 00327/00018/00124
d D 1.2 99/11/19 13:17:37 jmochel 3 2
c Updated the grammar without getting it to work.
cC
cK53476
e
s 00142/00000/00000
d D 1.1 99/11/17 12:54:45 jmochel 2 1
cC
cK23773
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:42 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/CodeGeneratorTextParser.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45360
cPjava/src/MacroProcessor/CodeGeneratorTextParser.java
cR2f93d7005cb6ba86
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
D 3
    <???>
E 3
I 3
    Parses text that has dot commands and expressions interwoven.
E 3
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

I 3
/**
    Look for commands and other text processing expressions
    
    <p>The Lexer has a filter that automatically echoes non-command text
    so we don't have to note it.
*/
E 3
	public final void startRule() throws ParserException, IOException {
		
		
		try {      // for error handling
			{
			_loop3:
			do {
D 3
				if (((LA(1) >= INCLUDE && LA(1) <= ENDIF))) {
					dotCommand();
E 3
I 3
				if ((_tokenSet_0.member(LA(1)))) {
					statement();
E 3
				}
				else {
					break _loop3;
				}
				
			} while (true);
			}
I 3
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
			
E 3
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
			consumeUntil(_tokenSet_0);
		}
	}
	
D 3
/**
    Dot Commands are the line commands 
*/
E 3
	public final void dotCommand() throws ParserException, IOException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case INCLUDE:
			{
I 3
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
E 3
				match(INCLUDE);
				expression();
I 3
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
E 3
				break;
			}
D 3
			case IF:
E 3
I 3
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
E 3
			{
D 3
				match(IF);
E 3
				expression();
				break;
			}
D 3
			case ENDIF:
E 3
I 3
			case IDENT:
E 3
			{
D 3
				match(ENDIF);
E 3
I 3
				variable();
E 3
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
D 3
			consumeUntil(_tokenSet_1);
E 3
I 3
			consumeUntil(_tokenSet_6);
E 3
		}
	}
	
D 3
	public final void expression() throws ParserException, IOException {
E 3
I 3
	public final void variable() throws ParserException, IOException {
E 3
		
		
		try {      // for error handling
D 3
			match(STRINGLITERAL);
E 3
I 3
			identifier();
E 3
		}
		catch (ParserException ex) {
			reportError(ex);
			consume();
D 3
			consumeUntil(_tokenSet_1);
E 3
I 3
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
E 3
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"INCLUDE",
I 3
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
E 3
		"IF",
		"ENDIF",
D 3
		"STRINGLITERAL",
		"IGNORE"
E 3
I 3
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
E 3
	};
	
D 3
	private static final long _tokenSet_0_data_[] = { 2L, 0L };
E 3
I 3
	private static final long _tokenSet_0_data_[] = { 115026L, 0L };
E 3
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
D 3
	private static final long _tokenSet_1_data_[] = { 114L, 0L };
E 3
I 3
	private static final long _tokenSet_1_data_[] = { 2L, 0L };
E 3
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
I 3
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
E 3
	
	}
E 2
I 1
E 1
