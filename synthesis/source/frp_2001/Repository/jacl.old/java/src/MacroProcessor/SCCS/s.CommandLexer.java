h25281
s 00379/00203/00215
d D 1.3 00/02/29 17:09:48 jmochel 4 3
c Added a bit more code.
cC
cK65460
e
s 00043/00007/00375
d D 1.2 99/12/02 17:11:06 jmochel 3 2
c Now does basic recognition of a variable expansion
cC
cK49170
e
s 00382/00000/00000
d D 1.1 99/11/23 13:24:54 jmochel 2 1
cC
cK49490
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/23 13:24:51 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/CommandLexer.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK31845
cPjava/src/MacroProcessor/CommandLexer.java
cRd7d5c05f5cb68643
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
D 4
/*
 * ANTLR-generated file resulting from grammar CommandLexer.g
 * 
 * Terence Parr, MageLang Institute
 * ANTLR Version 2.6.1; 1989-1999
 */
E 4
I 4
// $ANTLR 2.7.0: "COmmandLexer.g" -> "CommandLexer.java"$
E 4

    package jacl.MacroProcessor;

import java.io.InputStream;
I 4
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
E 4
import java.io.Reader;
D 4
import java.io.IOException;
E 4
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
D 4
import antlr.ScannerException;
E 4
I 4
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
E 4
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
I 4
import antlr.SemanticException;
E 4

D 4
    import java.io.*;
E 4
I 3
    import antlr.*;
E 3

D 3
public class CommandLexer extends antlr.CharScanner implements CommandLexerTokenTypes, TokenStream
E 3
I 3
public class CommandLexer extends antlr.CharScanner implements CommandTokenTypes, TokenStream
E 3
 {
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
E 4
public CommandLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public CommandLexer(Reader in) {
	this(new CharBuffer(in));
}
public CommandLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public CommandLexer(LexerSharedInputState state) {
	super(state);
	literals = new Hashtable();
caseSensitiveLiterals = true;
setCaseSensitive(true);
}

D 4
public Token nextToken() throws IOException {
	Token _rettoken=null;
E 4
I 4
public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
E 4
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
D 4
		try {   // for error handling
			switch ( LA(1)) {
			case '.':
			{
				mINCLUDE(true);
				_rettoken=_returnToken;
				break;
			}
			case '(':
			{
				mLPAREN(true);
				_rettoken=_returnToken;
				break;
			}
			case ')':
			{
				mRPAREN(true);
				_rettoken=_returnToken;
				break;
			}
			case '{':
			{
				mLCURLY(true);
				_rettoken=_returnToken;
				break;
			}
			case '}':
			{
				mRCURLY(true);
				_rettoken=_returnToken;
				break;
E 4
I 4
		try {   // for char stream error handling
			try {   // for lexical error handling

			System.out.println("LA(1) = " + LA(1) );
            
				switch ( LA(1)) {
            
				case 's':
				{
					mSET(true);
					theRetToken=_returnToken;
					break;
				}
				case '.':
				{
					mCOMMENT(true);
					theRetToken=_returnToken;
					break;
				}
				case 'i':
				{
					mINCLUDE(true);
					theRetToken=_returnToken;
					break;
				}
				case 'r':
				{
					mREDIRECT(true);
					theRetToken=_returnToken;
					break;
				}
				case '=':
				{
					mEQUALS(true);
					theRetToken=_returnToken;
					break;
				}
				case '+':
				{
					mPLUSEQUALS(true);
					theRetToken=_returnToken;
					break;
				}
				case '$':
				{
					mEXPRESSIONLEADIN(true);
					theRetToken=_returnToken;
					break;
				}
				case '{':
				{
					mOPENCURLY(true);
					theRetToken=_returnToken;
					break;
				}
				case '}':
				{
					mCLOSECURLY(true);
					theRetToken=_returnToken;
					break;
				}
				case '(':
				{
					mOPENPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case ')':
				{
					mCLOSEPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case '"':
				{
					mSTRINGLITERAL(true);
					theRetToken=_returnToken;
					break;
				}
				case '\n':  case '\r':
				{
					mCOMMANDEND(true);
					theRetToken=_returnToken;
					break;
				}
				default:
				{
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
E 4
			}
D 4
			case '$':
			{
				mDOLLAR(true);
				_rettoken=_returnToken;
				break;
E 4
I 4
			else {
				throw new TokenStreamException(cse.getMessage());
E 4
			}
D 4
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':  case '_':  case 'a':
			case 'b':  case 'c':  case 'd':  case 'e':
			case 'f':  case 'g':  case 'h':  case 'i':
			case 'j':  case 'k':  case 'l':  case 'm':
			case 'n':  case 'o':  case 'p':  case 'q':
			case 'r':  case 's':  case 't':  case 'u':
			case 'v':  case 'w':  case 'x':  case 'y':
			case 'z':
			{
				mIDENT(true);
				_rettoken=_returnToken;
				break;
E 4
I 4
		}
	}
}

	public final void mSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSET");
		_ttype = SET;
		int _saveIndex;
		try { // debugging
			
			match("set");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 4
			}
D 4
			case '"':
			{
				mSTRINGLITERAL(true);
				_rettoken=_returnToken;
				break;
E 4
I 4
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSET");
		}
	}
	
	public final void mCOMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCOMMENT");
		_ttype = COMMENT;
		int _saveIndex;
		try { // debugging
			
			match(".");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 4
			}
I 3
D 4
			case '\n':  case '\r':
			{
				mDOTCOMMAND_END(true);
				_rettoken=_returnToken;
				break;
E 4
I 4
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCOMMENT");
		}
	}
	
	public final void mINCLUDE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mINCLUDE");
		_ttype = INCLUDE;
		int _saveIndex;
		try { // debugging
			
			match("include");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 4
			}
E 3
D 4
			default:
			{
				if (LA(1)==EOF_CHAR) {_returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());}
E 4
I 4
			_returnToken = _token;
		} finally { // debugging
			traceOut("mINCLUDE");
		}
	}
	
	public final void mREDIRECT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mREDIRECT");
		_ttype = REDIRECT;
		int _saveIndex;
		try { // debugging
			
			match("redirect");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 4
			}
I 4
			_returnToken = _token;
		} finally { // debugging
			traceOut("mREDIRECT");
		}
	}
	
	public final void mEQUALS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mEQUALS");
		_ttype = EQUALS;
		int _saveIndex;
		try { // debugging
			
			match("=");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 4
			}
D 4
			if ( _returnToken==null ) continue tryAgain; // found SKIP token
			_ttype = _returnToken.getType();
E 4
I 4
			_returnToken = _token;
		} finally { // debugging
			traceOut("mEQUALS");
		}
	}
	
	public final void mPLUSEQUALS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mPLUSEQUALS");
		_ttype = PLUSEQUALS;
		int _saveIndex;
		try { // debugging
			
			match("+=");
E 4
			_ttype = testLiteralsTable(_ttype);
D 4
			_returnToken.setType(_ttype);
			return _returnToken;
E 4
I 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mPLUSEQUALS");
E 4
		}
D 4
		catch (ScannerException e) {
			reportError(e);
			consume();
E 4
I 4
	}
	
	public final void mEXPRESSIONLEADIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mEXPRESSIONLEADIN");
		_ttype = EXPRESSIONLEADIN;
		int _saveIndex;
		try { // debugging
			
			match("$");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mEXPRESSIONLEADIN");
E 4
		}
	}
D 4
}

	public final void mINCLUDE(boolean _createToken) throws ScannerException, IOException {
E 4
I 4
	
	public final void mOPENCURLY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 4
		int _ttype; Token _token=null; int _begin=text.length();
D 4
		traceIn("mINCLUDE");
		_ttype = INCLUDE;
E 4
I 4
		traceIn("mOPENCURLY");
		_ttype = OPENCURLY;
E 4
		int _saveIndex;
		try { // debugging
			
D 4
			match(".include");
E 4
I 4
			match("{");
			_ttype = testLiteralsTable(_ttype);
E 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 4
			traceOut("mINCLUDE");
E 4
I 4
			traceOut("mOPENCURLY");
E 4
		}
	}
	
D 4
	public final void mLPAREN(boolean _createToken) throws ScannerException, IOException {
E 4
I 4
	public final void mCLOSECURLY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 4
		int _ttype; Token _token=null; int _begin=text.length();
D 4
		traceIn("mLPAREN");
		_ttype = LPAREN;
E 4
I 4
		traceIn("mCLOSECURLY");
		_ttype = CLOSECURLY;
E 4
		int _saveIndex;
		try { // debugging
			
D 4
			match('(');
E 4
I 4
			match("}");
			_ttype = testLiteralsTable(_ttype);
E 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 4
			traceOut("mLPAREN");
E 4
I 4
			traceOut("mCLOSECURLY");
E 4
		}
	}
	
D 4
	public final void mRPAREN(boolean _createToken) throws ScannerException, IOException {
E 4
I 4
	public final void mOPENPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 4
		int _ttype; Token _token=null; int _begin=text.length();
D 4
		traceIn("mRPAREN");
		_ttype = RPAREN;
E 4
I 4
		traceIn("mOPENPAREN");
		_ttype = OPENPAREN;
E 4
		int _saveIndex;
		try { // debugging
			
D 4
			match(')');
E 4
I 4
			match("(");
			_ttype = testLiteralsTable(_ttype);
E 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 4
			traceOut("mRPAREN");
E 4
I 4
			traceOut("mOPENPAREN");
E 4
		}
	}
	
D 4
	public final void mLCURLY(boolean _createToken) throws ScannerException, IOException {
E 4
I 4
	public final void mCLOSEPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 4
		int _ttype; Token _token=null; int _begin=text.length();
D 4
		traceIn("mLCURLY");
		_ttype = LCURLY;
E 4
I 4
		traceIn("mCLOSEPAREN");
		_ttype = CLOSEPAREN;
E 4
		int _saveIndex;
		try { // debugging
			
D 4
			match('{');
E 4
I 4
			match(")");
			_ttype = testLiteralsTable(_ttype);
E 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 4
			traceOut("mLCURLY");
E 4
I 4
			traceOut("mCLOSEPAREN");
E 4
		}
	}
	
D 4
	public final void mRCURLY(boolean _createToken) throws ScannerException, IOException {
E 4
I 4
	public final void mSTRINGLITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 4
		int _ttype; Token _token=null; int _begin=text.length();
D 4
		traceIn("mRCURLY");
		_ttype = RCURLY;
E 4
I 4
		traceIn("mSTRINGLITERAL");
		_ttype = STRINGLITERAL;
E 4
		int _saveIndex;
		try { // debugging
			
D 4
			match('}');
E 4
I 4
			match('"');
			{
			_loop15:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					{
					match(_tokenSet_0);
					}
				}
				else {
					break _loop15;
				}
				
			} while (true);
			}
			match('"');
			_ttype = testLiteralsTable(_ttype);
E 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 4
			traceOut("mRCURLY");
E 4
I 4
			traceOut("mSTRINGLITERAL");
E 4
		}
	}
	
D 4
	public final void mDOLLAR(boolean _createToken) throws ScannerException, IOException {
E 4
I 4
	public final void mCOMMANDEND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 4
		int _ttype; Token _token=null; int _begin=text.length();
D 4
		traceIn("mDOLLAR");
		_ttype = DOLLAR;
E 4
I 4
		traceIn("mCOMMANDEND");
		_ttype = COMMANDEND;
E 4
		int _saveIndex;
		try { // debugging
			
D 4
			match('$');
E 4
I 4
			switch ( LA(1)) {
			case '\n':
			{
				match('\n');
				
					newline();
						Exec.streamSelector.pop();
					
				break;
			}
			case '\r':
			{
				match('\r');
				
					newline();
						Exec.streamSelector.pop();
					
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
			}
			}
E 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 4
			traceOut("mDOLLAR");
E 4
I 4
			traceOut("mCOMMANDEND");
E 4
		}
	}
	
D 4
	public final void mIDENT(boolean _createToken) throws ScannerException, IOException {
E 4
I 4
	protected final void mIDENTIFIER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 4
		int _ttype; Token _token=null; int _begin=text.length();
D 4
		traceIn("mIDENT");
		_ttype = IDENT;
E 4
I 4
		traceIn("mIDENTIFIER");
		_ttype = IDENTIFIER;
E 4
		int _saveIndex;
		try { // debugging
			
			{
			switch ( LA(1)) {
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':
			{
				matchRange('a','z');
				break;
			}
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':
			{
				matchRange('A','Z');
				break;
			}
D 4
			case '_':
			{
				match('_');
				break;
			}
E 4
			default:
			{
D 4
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 4
I 4
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 4
			}
			}
			}
			{
D 4
			_loop10:
E 4
I 4
			_loop20:
E 4
			do {
				switch ( LA(1)) {
				case 'a':  case 'b':  case 'c':  case 'd':
				case 'e':  case 'f':  case 'g':  case 'h':
				case 'i':  case 'j':  case 'k':  case 'l':
				case 'm':  case 'n':  case 'o':  case 'p':
				case 'q':  case 'r':  case 's':  case 't':
				case 'u':  case 'v':  case 'w':  case 'x':
				case 'y':  case 'z':
				{
					matchRange('a','z');
					break;
				}
				case 'A':  case 'B':  case 'C':  case 'D':
				case 'E':  case 'F':  case 'G':  case 'H':
				case 'I':  case 'J':  case 'K':  case 'L':
				case 'M':  case 'N':  case 'O':  case 'P':
				case 'Q':  case 'R':  case 'S':  case 'T':
				case 'U':  case 'V':  case 'W':  case 'X':
				case 'Y':  case 'Z':
				{
					matchRange('A','Z');
					break;
				}
D 4
				case '_':
				{
					match('_');
					break;
				}
E 4
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					matchRange('0','9');
					break;
				}
D 3
				case '$':
				{
					match('$');
					break;
				}
E 3
				default:
				{
D 4
					break _loop10;
E 4
I 4
					break _loop20;
E 4
				}
				}
			} while (true);
			}
I 4
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
E 4
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 4
			traceOut("mIDENT");
		}
	}
	
	public final void mSTRINGLITERAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSTRINGLITERAL");
		_ttype = STRINGLITERAL;
		int _saveIndex;
		try { // debugging
			
			match('"');
			{
			_loop14:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					{
					match(_tokenSet_0);
					}
				}
				else {
					break _loop14;
				}
				
			} while (true);
			}
			match('"');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSTRINGLITERAL");
		}
	}
	
I 3
	public final void mDOTCOMMAND_END(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mDOTCOMMAND_END");
		_ttype = DOTCOMMAND_END;
		int _saveIndex;
		try { // debugging
			
			switch ( LA(1)) {
			case '\r':
			{
				match('\r');
				break;
			}
			case '\n':
			{
				match('\n');
				Exec.selector.pop();
				break;
			}
			default:
			{
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
			}
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDOTCOMMAND_END");
E 4
I 4
			traceOut("mIDENTIFIER");
E 4
		}
	}
	
E 3
	
D 3
	private static final long _tokenSet_0_data_[] = { -17179869192L, -268435457L, 0L, 0L };
E 3
I 3
D 4
	private static final long _tokenSet_0_data_[] = { 288022637173548032L, 3458764507512307710L, 0L, 0L };
E 4
I 4
	private static final long _tokenSet_0_data_[] = { -17179869192L, -1L, -1L, -1L, 0L, 0L, 0L, 0L };
E 4
E 3
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	
	}
E 2
I 1
E 1
