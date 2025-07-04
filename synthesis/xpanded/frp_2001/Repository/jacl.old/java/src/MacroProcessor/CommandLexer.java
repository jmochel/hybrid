// $ANTLR 2.7.0: "COmmandLexer.g" -> "CommandLexer.java"$

    package jacl.MacroProcessor;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

    import antlr.*;

public class CommandLexer extends antlr.CharScanner implements CommandTokenTypes, TokenStream
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

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
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
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
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
			}
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
			}
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
			}
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
			}
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
			}
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
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mPLUSEQUALS");
		}
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
		}
	}
	
	public final void mOPENCURLY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mOPENCURLY");
		_ttype = OPENCURLY;
		int _saveIndex;
		try { // debugging
			
			match("{");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mOPENCURLY");
		}
	}
	
	public final void mCLOSECURLY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCLOSECURLY");
		_ttype = CLOSECURLY;
		int _saveIndex;
		try { // debugging
			
			match("}");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCLOSECURLY");
		}
	}
	
	public final void mOPENPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mOPENPAREN");
		_ttype = OPENPAREN;
		int _saveIndex;
		try { // debugging
			
			match("(");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mOPENPAREN");
		}
	}
	
	public final void mCLOSEPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCLOSEPAREN");
		_ttype = CLOSEPAREN;
		int _saveIndex;
		try { // debugging
			
			match(")");
			_ttype = testLiteralsTable(_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCLOSEPAREN");
		}
	}
	
	public final void mSTRINGLITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSTRINGLITERAL");
		_ttype = STRINGLITERAL;
		int _saveIndex;
		try { // debugging
			
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
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSTRINGLITERAL");
		}
	}
	
	public final void mCOMMANDEND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCOMMANDEND");
		_ttype = COMMANDEND;
		int _saveIndex;
		try { // debugging
			
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
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCOMMANDEND");
		}
	}
	
	protected final void mIDENTIFIER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mIDENTIFIER");
		_ttype = IDENTIFIER;
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
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
			}
			}
			}
			{
			_loop20:
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
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					matchRange('0','9');
					break;
				}
				default:
				{
					break _loop20;
				}
				}
			} while (true);
			}
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIDENTIFIER");
		}
	}
	
	
	private static final long _tokenSet_0_data_[] = { -17179869192L, -1L, -1L, -1L, 0L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	
	}
