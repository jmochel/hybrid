h54760
s 00210/00203/00177
d D 1.10 00/02/29 17:09:48 jmochel 11 10
c Added a bit more code.
cC
cK54309
e
s 00242/00153/00138
d D 1.9 00/02/04 09:48:47 jmochel 10 9
c Grammar looks like it is almost finished but I am running into a problem with the Lexer.
c If the Lexer uses all protected methods (which ppears to be allowable in the ANTLR docs)
c then the Lexer acts as if it was given a 0 length file.  
cC
cK52279
e
s 00102/00592/00189
d D 1.8 00/02/03 12:21:30 jmochel 9 8
c Redesign !
cC
cK20831
e
s 00314/00260/00467
d D 1.7 00/01/11 15:30:30 jmochel 8 7
c Updated
cC
cK06828
e
s 00576/00076/00151
d D 1.6 00/01/07 14:22:51 jmochel 7 6
c Rewrote the grammar.
cC
cK55587
e
s 00031/00328/00196
d D 1.5 99/12/17 15:04:22 jmochel 6 5
c Restart
cC
cK21583
e
s 00106/00006/00418
d D 1.4 99/12/07 07:46:00 jmochel 5 4
c Now recognizes include statements
cC
cK11326
e
s 00109/00017/00315
d D 1.3 99/12/07 07:06:36 jmochel 4 3
c Macro Invocations are now recognized
cC
cK59133
e
s 00171/00310/00161
d D 1.2 99/12/02 17:11:07 jmochel 3 2
c Now does basic recognition of a variable expansion
cC
cK21170
e
s 00471/00000/00000
d D 1.1 99/11/23 13:25:02 jmochel 2 1
cC
cK05931
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/23 13:24:58 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/TemplateLexer.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK31846
cPjava/src/MacroProcessor/TemplateLexer.java
cRd7d5c05d5cb68643
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
D 8
/*
 * ANTLR-generated file resulting from grammar TemplateLexer.g
 * 
 * Terence Parr, MageLang Institute
 * ANTLR Version 2.6.1; 1989-1999
 */
E 8
I 8
D 9
// $ANTLR 2.7.0a11: TemplateLexer.g -> TemplateLexer.java$
E 9
I 9
// $ANTLR 2.7.0: "TemplateLexer.g" -> "TemplateLexer.java"$
E 9
E 8

    package jacl.MacroProcessor;

import java.io.InputStream;
I 8
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
E 8
import java.io.Reader;
D 8
import java.io.IOException;
E 8
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
D 8
import antlr.ScannerException;
E 8
I 8
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
E 8
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
I 9
import antlr.SemanticException;
E 9

I 5
D 6
    import java.util.Vector;    
E 6
E 5
D 10
    import java.io.*;
I 7
    import java.util.*;
E 10
E 7
D 3
import antlr.TokenStreamSelector;
E 3
I 3
    import antlr.*;
E 3

D 8
public class TemplateLexer extends antlr.CharScanner implements TemplateTokenTypes, TokenStream
E 8
I 8
D 11
public class TemplateLexer extends antlr.CharScanner implements TemplateLexerTokenTypes, TokenStream
E 11
I 11
public class TemplateLexer extends antlr.CharScanner implements TemplateTokenTypes, TokenStream
E 11
E 8
 {
I 11

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
E 11
I 5
D 10

E 10
I 7
D 9
    /**
        Variables declared in the template
    */
    
    Hashtable    m_Variables = new Hashtable();
E 9
E 7
E 5
public TemplateLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public TemplateLexer(Reader in) {
	this(new CharBuffer(in));
}
public TemplateLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public TemplateLexer(LexerSharedInputState state) {
	super(state);
	literals = new Hashtable();
caseSensitiveLiterals = true;
setCaseSensitive(true);
}

D 8
public Token nextToken() throws IOException {
E 8
I 8
public Token nextToken() throws TokenStreamException {
E 8
D 9
	Token _rettoken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		setCommitToPath(false);
		int _m;
		_m = mark();
		resetText();
D 8
		try {   // for error handling
D 3
			switch ( LA(1)) {
			case '.':
			{
				mDOTCOMMAND_START(true);
				_rettoken=_returnToken;
				break;
			}
			case '\n':  case '\r':
			{
				mDOTCOMMAND_END(true);
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
E 3
I 3
D 6
			if ((LA(1)=='$') && (_tokenSet_0.member(LA(2)))) {
				mMACRO_INVOCATION(true);
E 3
				_rettoken=_returnToken;
D 3
				break;
			}
			case '}':
			{
				mRCURLY(true);
				_rettoken=_returnToken;
				break;
			}
			case '$':
			{
				mDOLLAR(true);
				_rettoken=_returnToken;
				break;
E 3
			}
D 3
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
E 3
I 3
			else if ((LA(1)=='$') && (LA(2)=='{')) {
				mVAR_EXPANSION(true);
E 3
				_rettoken=_returnToken;
D 3
				break;
E 3
			}
I 5
			else if ((LA(1)=='.')) {
				mDOT_COMMAND(true);
E 6
I 6
D 7
			if ((LA(1)=='.')) {
				mINCLUDE(true);
E 7
I 7
			switch ( LA(1)) {
			case '.':
			{
				mDOTCOMMAND(true);
E 7
E 6
				_rettoken=_returnToken;
I 7
				break;
E 7
			}
E 5
D 3
			case '"':
			{
				mSTRINGLITERAL(true);
				_rettoken=_returnToken;
				break;
			}
			default:
			{
E 3
I 3
D 7
			else {
E 7
I 7
			case '$':
			{
				mGENEXPRESSION(true);
				_rettoken=_returnToken;
				break;
			}
			default:
			{
E 7
E 3
				if (LA(1)==EOF_CHAR) {_returnToken = makeToken(Token.EOF_TYPE);}
E 8
I 8
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '.':
				{
					mDOTCOMMAND(true);
					_rettoken=_returnToken;
					break;
				}
				case '$':
				{
					mGENEXPRESSION(true);
					_rettoken=_returnToken;
					break;
				}
				default:
				{
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
E 8
				else {
					commit();
					try {mIGNORE(false);}
D 8
					catch(ScannerException e) {
E 8
I 8
					catch(RecognitionException e) {
E 8
						// catastrophic failure
						reportError(e);
						consume();
					}
					continue tryAgain;
				}
I 8
				}
				}
				commit();
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				if ( !getCommitToPath() ) {
					rewind(_m);
					resetText();
					try {mIGNORE(false);}
					catch(RecognitionException ee) {
						// horrendous failure: error in filter rule
						reportError(ee);
						consume();
					}
					continue tryAgain;
				}
				reportError(e);
				consume();
E 8
			}
I 8
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
E 8
D 3
			}
E 3
I 3
D 7
			
E 7
I 7
			}
E 7
E 3
D 8
			commit();
			if ( _returnToken==null ) continue tryAgain; // found SKIP token
			_ttype = _returnToken.getType();
			_ttype = testLiteralsTable(_ttype);
			_returnToken.setType(_ttype);
			return _returnToken;
		}
		catch (ScannerException e) {
			if ( !getCommitToPath() ) {
				rewind(_m);
				resetText();
				try {mIGNORE(false);}
				catch(ScannerException ee) {
					// horrendous failure: error in filter rule
					reportError(ee);
					consume();
				}
				continue tryAgain;
E 8
I 8
			else {
				throw new TokenStreamException(cse.getMessage());
E 8
			}
D 8
			reportError(e);
			consume();
E 8
		}
E 9
I 9
D 10
	try {uponEOF();}
	catch(CharStreamIOException csioe) {
		throw new TokenStreamIOException(csioe.io);
	}
	catch(CharStreamException cse) {
		throw new TokenStreamException(cse.getMessage());
E 10
I 10
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		setCommitToPath(false);
		int _m;
		_m = mark();
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
D 11
				if ((LA(1)=='\t'||LA(1)==' ')) {
					mWS(true);
E 11
I 11
				switch ( LA(1)) {
				case '$':
				{
					mEXPRESSIONLEADIN(true);
E 11
					theRetToken=_returnToken;
I 11
					break;
E 11
				}
D 11
				else {
E 11
I 11
				case '.':
				{
					mCOMMANDBEGIN(true);
					theRetToken=_returnToken;
					break;
				}
				default:
				{
E 11
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {
					commit();
					try {mIGNORE(false);}
					catch(RecognitionException e) {
						// catastrophic failure
						reportError(e);
						consume();
					}
					continue tryAgain;
				}
				}
D 11
				
E 11
I 11
				}
E 11
				commit();
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				if ( !getCommitToPath() ) {
					rewind(_m);
					resetText();
					try {mIGNORE(false);}
					catch(RecognitionException ee) {
						// horrendous failure: error in filter rule
						reportError(ee);
						consume();
					}
					continue tryAgain;
				}
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
E 10
E 9
	}
I 9
D 10
	return new CommonToken(Token.EOF_TYPE, "");
E 10
E 9
}

I 11
	public final void mEXPRESSIONLEADIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mEXPRESSIONLEADIN");
		_ttype = EXPRESSIONLEADIN;
		int _saveIndex;
		try { // debugging
			
			match("$");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mEXPRESSIONLEADIN");
		}
	}
	
E 11
D 3
	public final void mDOTCOMMAND_START(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mDOTCOMMAND_START");
		_ttype = DOTCOMMAND_START;
		int _saveIndex;
		try { // debugging
			
			match(".include");
			Main.m_Selector.push("commandLexer");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDOTCOMMAND_START");
		}
	}
	
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
				Main.m_Selector.pop();
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
		}
	}
	
	public final void mLPAREN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLPAREN");
		_ttype = LPAREN;
		int _saveIndex;
		try { // debugging
			
			match('(');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLPAREN");
		}
	}
	
	public final void mRPAREN(boolean _createToken) throws ScannerException, IOException {
E 3
I 3
D 6
	public final void mMACRO_INVOCATION(boolean _createToken) throws ScannerException, IOException {
E 3
		int _ttype; Token _token=null; int _begin=text.length();
D 3
		traceIn("mRPAREN");
		_ttype = RPAREN;
E 3
I 3
		_ttype = MACRO_INVOCATION;
E 3
		int _saveIndex;
D 3
		try { // debugging
			
			match(')');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mRPAREN");
E 3
I 3
D 5
		Token macroname=null;
E 5
I 5
		Token macroName=null;
E 5
		
		match('$');
		mIDENT(true);
D 5
		macroname=_returnToken;
E 5
I 5
		macroName=_returnToken;
E 5
		match('(');
D 4
		{
		_loop3:
		do {
			if ((LA(1)=='"'||LA(1)=='$')) {
				mARG(false);
			}
			else {
				break _loop3;
			}
			
		} while (true);
		}
E 4
I 4
		mARG_LIST_OR_NOTHING(false);
E 4
		match(')');
		
D 5
		System.out.println("============== I found a macro " + macroname );
E 5
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 3
		}
I 3
		_returnToken = _token;
E 3
	}
	
D 3
	public final void mLCURLY(boolean _createToken) throws ScannerException, IOException {
E 3
I 3
	protected final void mIDENT(boolean _createToken) throws ScannerException, IOException {
E 6
I 6
D 7
	protected final void mIGNORE(boolean _createToken) throws ScannerException, IOException {
E 7
I 7
D 8
	public final void mDOTCOMMAND(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
D 9
	public final void mDOTCOMMAND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 9
I 9
D 10
	protected final void mIDENTIFIER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 10
I 10
	protected final void mIGNORE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 10
E 9
E 8
E 7
E 6
E 3
		int _ttype; Token _token=null; int _begin=text.length();
I 11
		traceIn("mIGNORE");
E 11
D 3
		traceIn("mLCURLY");
		_ttype = LCURLY;
E 3
I 3
D 6
		_ttype = IDENT;
E 6
I 6
D 7
		_ttype = IGNORE;
E 7
I 7
D 9
		traceIn("mDOTCOMMAND");
		_ttype = DOTCOMMAND;
E 9
I 9
D 10
		traceIn("mIDENTIFIER");
		_ttype = IDENTIFIER;
E 10
I 10
		_ttype = IGNORE;
E 10
E 9
		int _saveIndex;
D 10
		try { // debugging
			
E 10
I 10
D 11
		
		if (((LA(1) >= '\3' && LA(1) <= '\377')) && (true) && (true)) {
			matchNot(EOF_CHAR);
			System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
		}
		else if ((LA(1)=='\t'||LA(1)==' ') && (true) && (true)) {
E 10
D 9
			if ((LA(1)=='.') && (LA(2)=='.')) {
				mCOMMENT(false);
			}
			else if ((LA(1)=='.') && (LA(2)=='s')) {
				mSETCOMMAND(false);
			}
			else if ((LA(1)=='.') && (LA(2)=='i')) {
D 8
				match(".include");
				{
				switch ( LA(1)) {
				case '$':
				{
					mEXPANSION(false);
					break;
				}
				case '"':
				{
					mSTRINGLITERAL(false);
					break;
				}
				default:
				{
					throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
				}
				}
				}
E 8
I 8
				mINCLUDECOMMAND(false);
E 8
			}
			else if ((LA(1)=='.') && (LA(2)=='a')) {
D 8
				match(".append");
				mVARNAME(false);
				match('=');
				mARGLIST(false);
E 8
I 8
				mAPPENDCOMMAND(false);
E 8
			}
			else {
D 8
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 8
I 8
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 8
			}
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDOTCOMMAND");
		}
	}
	
D 8
	protected final void mCOMMENT(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final void mCOMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCOMMENT");
		_ttype = COMMENT;
E 7
E 6
E 3
		int _saveIndex;
D 3
		try { // debugging
			
			match('{');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLCURLY");
E 3
I 3
D 7
		
D 6
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
		case '_':
		{
			match('_');
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 3
		}
D 3
	}
	
	public final void mRCURLY(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mRCURLY");
		_ttype = RCURLY;
		int _saveIndex;
		try { // debugging
			
			match('}');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mRCURLY");
E 3
		}
D 3
	}
	
	public final void mDOLLAR(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mDOLLAR");
		_ttype = DOLLAR;
		int _saveIndex;
		try { // debugging
			
			match('$');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDOLLAR");
E 3
		}
D 3
	}
	
	public final void mIDENT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mIDENT");
		_ttype = IDENT;
		int _saveIndex;
		try { // debugging
			
			{
E 3
I 3
		{
D 4
		_loop13:
E 4
I 4
		_loop20:
E 4
		do {
E 3
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
			case '_':
			{
				match('_');
				break;
			}
D 3
			default:
E 3
I 3
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
E 3
			{
D 3
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
			}
E 3
I 3
				matchRange('0','9');
				break;
E 3
			}
I 3
			case '$':
			{
				match('$');
				break;
E 3
			}
I 3
			default:
E 3
			{
D 3
			_loop11:
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
				case '_':
				{
					match('_');
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					matchRange('0','9');
					break;
				}
				case '$':
				{
					match('$');
					break;
				}
				default:
				{
					break _loop11;
				}
				}
			} while (true);
E 3
I 3
D 4
				break _loop13;
E 4
I 4
				break _loop20;
E 4
E 3
			}
D 3
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIDENT");
E 3
I 3
			}
		} while (true);
		}
E 6
I 6
		matchNot(EOF_CHAR);
		System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
E 6
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 7
I 7
		try { // debugging
			
			match("..");
D 8
			matchNot(EOF_CHAR);
E 8
I 8
			{
			_loop5:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					{
					match(_tokenSet_0);
					}
				}
				else {
					break _loop5;
				}
				
			} while (true);
			}
E 8
			mNEWLINE(false);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCOMMENT");
E 7
E 3
		}
I 3
D 7
		_returnToken = _token;
E 7
E 3
	}
	
I 4
D 6
	protected final void mARG_LIST_OR_NOTHING(boolean _createToken) throws ScannerException, IOException {
E 6
I 6
D 7
	public final void mINCLUDE(boolean _createToken) throws ScannerException, IOException {
E 7
I 7
D 8
	protected final void mSETCOMMAND(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final void mSETCOMMAND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
E 7
E 6
		int _ttype; Token _token=null; int _begin=text.length();
D 6
		_ttype = ARG_LIST_OR_NOTHING;
E 6
I 6
D 7
		_ttype = INCLUDE;
E 7
I 7
		traceIn("mSETCOMMAND");
		_ttype = SETCOMMAND;
E 7
E 6
		int _saveIndex;
D 7
		
D 6
		{
		switch ( LA(1)) {
		case '"':  case '$':
		{
			mARG_LIST(false);
			break;
		}
		case ')':
		{
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
E 6
I 6
		match(".include");
E 6
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 7
I 7
		try { // debugging
D 8
			Token varname=null;
E 8
			
D 8
			Vector arglist;
E 8
I 8
			Object var = null;
			Object args = null;        
E 8
			
			
			match(".set");
D 8
			mVARNAME(true);
			varname=_returnToken;
			match('=');
			arglist=mARGLIST(false);
E 8
I 8
			mWS(false);
			var=mVAREXPRESSION(false);
			mWS(false);
			args=mARGLIST(false);
			mWS(false);
			mNEWLINE(false);
E 8
			
D 8
			System.out.println("SETCOMMAND " );
			m_Variables.put(varname.getText(), arglist);
			System.out.println(m_Variables);
E 8
I 8
			/* 
			If we get a String back rather than a Vector then the 
			variable does not already exist.
			*/
			
			if ( var.getClass().getName() == "java.lang.String" )
			{
			m_Variables.put(var, args);
			}
			else 
			{
			var = args;
			}
E 8
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSETCOMMAND");
		}
	}
	
D 8
	protected final String  mEXPANSION(boolean _createToken) throws ScannerException, IOException {
		String str;
E 8
I 8
	protected final void mINCLUDECOMMAND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
		int _ttype; Token _token=null; int _begin=text.length();
D 8
		traceIn("mEXPANSION");
		_ttype = EXPANSION;
E 8
I 8
		traceIn("mINCLUDECOMMAND");
		_ttype = INCLUDECOMMAND;
E 8
		int _saveIndex;
		try { // debugging
			
D 8
			Object obj;
E 8
I 8
			match(".include");
			mWS(false);
E 9
			{
			switch ( LA(1)) {
D 9
			case '$':
			{
				mEXPANSION(false);
				break;
			}
			case '"':
			{
				mSTRINGLITERAL(false);
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
			}
			}
			}
			mWS(false);
			mNEWLINE(false);
E 8
			
			
D 8
			match('$');
			match('{');
			obj=mEXPRESSION(false);
			match('}');
E 8
I 8
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mINCLUDECOMMAND");
		}
	}
	
	protected final void mAPPENDCOMMAND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mAPPENDCOMMAND");
		_ttype = APPENDCOMMAND;
		int _saveIndex;
		try { // debugging
E 8
			
D 8
			if ( obj.getClass().getName() == new String("java.lang.String") )
E 8
I 8
			Object var;
			Vector args;
			
			
			match(".append");
			mWS(false);
			var=mVAREXPRESSION(false);
			mWS(false);
			args=mARGLIST(false);
			mWS(false);
			mNEWLINE(false);
			
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mAPPENDCOMMAND");
		}
	}
	
	protected final void mNEWLINE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mNEWLINE");
		_ttype = NEWLINE;
		int _saveIndex;
		try { // debugging
			
			switch ( LA(1)) {
			case '\n':
E 9
I 9
D 10
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':
E 10
I 10
			case ' ':
E 10
E 9
E 8
			{
D 8
			str = (String) obj;
E 8
I 8
D 9
				match('\n');
				newline();
E 9
I 9
D 10
				matchRange('a','z');
E 10
I 10
				match(' ');
E 10
E 9
				break;
E 8
			}
D 8
			else 
E 8
I 8
D 9
			case '\r':
E 9
I 9
D 10
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':
E 10
I 10
			case '\t':
E 10
E 9
E 8
			{
D 8
			str = obj.toString();
E 8
I 8
D 9
				match('\r');
				newline();
E 9
I 9
D 10
				matchRange('A','Z');
E 10
I 10
				match('\t');
E 10
E 9
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
			}
E 8
			}
E 11
I 11
		try { // debugging
			
			if (((LA(1) >= '\3' && LA(1) <= '\377')) && (true) && (true) && (true)) {
				matchNot(EOF_CHAR);
				System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
E 11
D 8
			
E 8
D 9
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 9
			}
D 9
			_returnToken = _token;
D 8
			return str;
E 8
		} finally { // debugging
D 8
			traceOut("mEXPANSION");
E 8
I 8
			traceOut("mNEWLINE");
E 8
E 7
		}
D 7
		_returnToken = _token;
E 7
	}
	
D 6
	protected final void mARG_LIST(boolean _createToken) throws ScannerException, IOException {
E 6
I 6
D 8
	protected final void mSTRINGLITERAL(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
E 6
		int _ttype; Token _token=null; int _begin=text.length();
I 7
D 8
		traceIn("mSTRINGLITERAL");
E 7
D 6
		_ttype = ARG_LIST;
E 6
I 6
		_ttype = STRINGLITERAL;
E 8
I 8
		traceIn("mWS");
		_ttype = WS;
E 8
E 6
		int _saveIndex;
D 7
		
D 6
		mARG(false);
E 6
I 6
		match('"');
E 6
		{
		_loop6:
		do {
D 6
			if ((LA(1)=='\t'||LA(1)==' '||LA(1)==',')) {
				mWS(false);
				mCOMMA(false);
				mWS(false);
				mARG(false);
E 6
I 6
			if ((_tokenSet_0.member(LA(1)))) {
				{
				match(_tokenSet_0);
E 7
I 7
		try { // debugging
			
E 9
D 8
			match('"');
E 8
D 11
			{
D 8
			_loop24:
E 8
I 8
D 9
			_loop32:
E 9
I 9
			_loop4:
E 9
E 8
			do {
E 11
I 11
			else if ((LA(1)=='\t'||LA(1)==' ') && (true) && (true) && (true)) {
				{
E 11
D 8
				if ((_tokenSet_0.member(LA(1)))) {
					{
					match(_tokenSet_0);
					}
E 8
I 8
D 9
				if ((LA(1)=='\t') && (_tokenSet_1.member(LA(2)))) {
					match('\t');
E 9
I 9
				switch ( LA(1)) {
D 10
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
E 9
				}
D 9
				else if ((LA(1)==' ') && (_tokenSet_1.member(LA(2)))) {
					match(' ');
E 9
I 9
				case 'A':  case 'B':  case 'C':  case 'D':
				case 'E':  case 'F':  case 'G':  case 'H':
				case 'I':  case 'J':  case 'K':  case 'L':
				case 'M':  case 'N':  case 'O':  case 'P':
				case 'Q':  case 'R':  case 'S':  case 'T':
				case 'U':  case 'V':  case 'W':  case 'X':
				case 'Y':  case 'Z':
E 10
I 10
				case ' ':
E 10
				{
D 10
					matchRange('A','Z');
E 10
I 10
					match(' ');
E 10
					break;
E 9
E 8
				}
D 9
				else {
D 8
					break _loop24;
E 8
I 8
					break _loop32;
E 9
I 9
D 10
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
E 10
I 10
				case '\t':
E 10
				{
D 10
					matchRange('0','9');
E 10
I 10
					match('\t');
E 10
					break;
E 9
E 8
E 7
				}
I 7
D 9
				
			} while (true);
E 7
E 6
			}
D 7
			else {
				break _loop6;
E 7
I 7
D 8
			match('"');
E 8
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 7
			}
I 7
			_returnToken = _token;
		} finally { // debugging
D 8
			traceOut("mSTRINGLITERAL");
E 8
I 8
			traceOut("mWS");
E 8
		}
	}
	
D 8
	protected final void mVARNAME(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final Object  mVAREXPRESSION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		Object obj;
E 8
		int _ttype; Token _token=null; int _begin=text.length();
D 8
		traceIn("mVARNAME");
		_ttype = VARNAME;
E 8
I 8
		traceIn("mVAREXPRESSION");
		_ttype = VAREXPRESSION;
E 8
		int _saveIndex;
		try { // debugging
I 8
			Token varname=null;
			
			match('$');
			mIDENT(true);
			varname=_returnToken;
			
			obj = m_Variables.get(varname.getText());
			
			/* 
			If the variable doesn't exist
			return a string with the name in it.
			*/
			
			if (obj == null)
			{
			obj = new String(varname.getText());
			}
E 8
E 7
			
D 7
		} while (true);
E 7
I 7
D 8
			mIDENT(false);
E 8
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
I 8
			return obj;
E 8
		} finally { // debugging
D 8
			traceOut("mVARNAME");
E 8
I 8
			traceOut("mVAREXPRESSION");
E 8
E 7
		}
I 6
D 7
		match('"');
E 6
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 7
I 7
	}
	
D 8
	protected final Vector  mARGLIST(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final Vector  mARGLIST(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
		Vector list;
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mARGLIST");
		_ttype = ARGLIST;
		int _saveIndex;
		try { // debugging
			
			Object arg = null;
			
			list = new Vector();
			
			
			{
D 8
			_loop9:
E 8
I 8
			_loop15:
E 8
			do {
				if ((LA(1)=='"'||LA(1)=='$')) {
I 8
					{
E 8
					arg=mARG(false);
I 8
					}
					mWS(false);
E 9
I 9
				default:
				{
D 11
					break _loop4;
E 11
I 11
					throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
				}
E 11
E 9
E 8
				}
D 9
				else {
D 8
					break _loop9;
E 8
I 8
					break _loop15;
E 9
E 8
				}
D 9
				
E 9
D 11
			} while (true);
			}
D 9
			
			list.add(arg);
			
E 9
I 9
D 10
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
E 9
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
D 9
			return list;
E 9
		} finally { // debugging
D 9
			traceOut("mARGLIST");
E 9
I 9
			traceOut("mIDENTIFIER");
E 10
I 10
			System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
E 10
E 9
E 7
		}
D 7
		_returnToken = _token;
E 7
D 10
	}
	
E 4
D 3
	public final void mSTRINGLITERAL(boolean _createToken) throws ScannerException, IOException {
E 3
I 3
D 6
	protected final void mARG(boolean _createToken) throws ScannerException, IOException {
E 6
I 6
D 8
	protected final void mNEWLINE(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
D 9
	protected final String  mEXPANSION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		String str;
E 9
I 9
	protected final void mIGNORE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 9
E 8
E 6
E 3
		int _ttype; Token _token=null; int _begin=text.length();
I 7
D 8
		traceIn("mNEWLINE");
E 7
D 3
		traceIn("mSTRINGLITERAL");
		_ttype = STRINGLITERAL;
E 3
I 3
D 6
		_ttype = ARG;
E 6
I 6
		_ttype = NEWLINE;
E 8
I 8
D 9
		traceIn("mEXPANSION");
		_ttype = EXPANSION;
E 9
I 9
		traceIn("mIGNORE");
		_ttype = IGNORE;
E 9
E 8
E 6
E 3
		int _saveIndex;
D 3
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
E 3
I 3
D 7
		
		switch ( LA(1)) {
D 6
		case '$':
E 6
I 6
		case '\n':
E 6
		{
D 6
			mVAR_EXPANSION(false);
E 6
I 6
			match('\n');
E 6
			break;
		}
D 6
		case '"':
E 6
I 6
		case '\r':
E 6
		{
D 6
			mLITERAL(false);
E 6
I 6
			match('\r');
E 6
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
E 7
I 7
		try { // debugging
			
D 8
			switch ( LA(1)) {
			case '\n':
E 8
I 8
D 9
			Object obj;
			
			
			match('$');
			match('{');
			obj=mEXPRESSION(false);
			match('}');
			
			if ( obj.getClass().getName() == new String("java.lang.String") )
E 8
			{
D 8
				match('\n');
				break;
E 8
I 8
			str = (String) obj;
E 9
I 9
			if (((LA(1) >= '\3' && LA(1) <= '\377')) && (true) && (true)) {
				matchNot(EOF_CHAR);
				System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
E 9
E 8
			}
D 8
			case '\r':
E 8
I 8
D 9
			else 
E 8
			{
D 8
				match('\r');
				break;
E 8
I 8
			str = obj.toString();
E 9
I 9
			else if ((LA(1)=='\n') && (true) && (true)) {
				match('\n');
				newline();System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
			}
			else if ((LA(1)=='\r') && (true) && (true)) {
				match('\r');
				newline();System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 9
E 8
			}
D 8
			default:
			{
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 8
I 8
			
I 9
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
E 9
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 8
			}
I 8
			_returnToken = _token;
D 9
			return str;
E 9
		} finally { // debugging
D 9
			traceOut("mEXPANSION");
E 9
I 9
			traceOut("mIGNORE");
E 10
I 10
		else if ((LA(1)=='\n') && (true) && (true)) {
			match('\n');
			newline();System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
		}
		else if ((LA(1)=='\r') && (true) && (true)) {
			match('\r');
			newline();System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 10
E 9
		}
I 10
		
		_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
E 10
	}
	
	protected final void mSTRINGLITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
D 10
		traceIn("mSTRINGLITERAL");
E 10
		_ttype = STRINGLITERAL;
		int _saveIndex;
D 10
		try { // debugging
			
			match('"');
			{
D 9
			_loop28:
E 9
I 9
			_loop9:
E 9
			do {
D 9
				if ((_tokenSet_2.member(LA(1)))) {
E 9
I 9
				if ((_tokenSet_0.member(LA(1)))) {
E 9
					{
D 9
					match(_tokenSet_2);
E 9
I 9
					match(_tokenSet_0);
E 9
					}
				}
				else {
D 9
					break _loop28;
E 9
I 9
					break _loop9;
E 10
I 10
		
		match('"');
		{
		_loop8:
		do {
			if ((_tokenSet_0.member(LA(1)))) {
E 11
				{
D 11
				match(_tokenSet_0);
E 11
I 11
				_loop5:
				do {
					switch ( LA(1)) {
					case ' ':
					{
						match(' ');
						break;
					}
					case '\t':
					{
						match('\t');
						break;
					}
					default:
					{
						break _loop5;
					}
					}
				} while (true);
E 11
E 10
E 9
				}
I 11
				System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
E 11
D 10
				
			} while (true);
E 10
E 8
			}
I 8
D 10
			match('"');
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
E 8
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 8
			traceOut("mNEWLINE");
E 8
I 8
			traceOut("mSTRINGLITERAL");
E 10
I 10
D 11
			else {
				break _loop8;
			}
			
		} while (true);
		}
		match('"');
		_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 10
E 8
E 7
		}
I 10
		_returnToken = _token;
E 10
D 7
		_returnToken = _token;
E 7
	}
	
I 4
D 7
	protected final void mWS(boolean _createToken) throws ScannerException, IOException {
E 7
I 7
D 8
	public final void mGENEXPRESSION(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
D 9
	public final void mGENEXPRESSION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
E 7
		int _ttype; Token _token=null; int _begin=text.length();
D 7
		_ttype = WS;
E 7
I 7
		traceIn("mGENEXPRESSION");
		_ttype = GENEXPRESSION;
E 7
		int _saveIndex;
D 7
		
		{
D 6
		_loop11:
E 6
I 6
		_loop10:
E 6
		do {
E 7
I 7
		try { // debugging
			
			Object obj;
			
			
			obj=mEXPRESSION(false);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mGENEXPRESSION");
		}
	}
	
D 8
	protected final Object  mEXPRESSION(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final Object  mEXPRESSION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
		Object obj;
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mEXPRESSION");
		_ttype = EXPRESSION;
		int _saveIndex;
		try { // debugging
			
D 8
			if ((LA(1)=='$') && (_tokenSet_1.member(LA(2)))) {
E 8
I 8
			if ((LA(1)=='$') && (_tokenSet_3.member(LA(2)))) {
E 8
				obj=mVAREXPRESSION(false);
			}
D 8
			else if ((LA(1)=='$') && (_tokenSet_1.member(LA(2)))) {
E 8
I 8
			else if ((LA(1)=='$') && (_tokenSet_3.member(LA(2)))) {
E 8
				obj=mMETHODINVOCATION(false);
			}
			else if ((LA(1)=='$') && (LA(2)=='{')) {
				obj=mEXPANSION(false);
				
				System.out.print((String) obj);
				
			}
			else {
D 8
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 8
I 8
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 8
			}
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
			return obj;
		} finally { // debugging
			traceOut("mEXPRESSION");
		}
	}
	
D 8
	protected final Object  mVAREXPRESSION(boolean _createToken) throws ScannerException, IOException {
		Object obj;
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mVAREXPRESSION");
		_ttype = VAREXPRESSION;
		int _saveIndex;
		try { // debugging
			Token varname=null;
			
			match('$');
			mVARNAME(true);
			varname=_returnToken;
			
			obj = m_Variables.get(varname.getText());
			
			// Throw an exception if  the variable doesn't exist
			
			if (obj == null)
			{
			System.out.println("ERROR variable " + varname + " Was not found");
			System.out.println(m_Variables);
			}
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
			return obj;
		} finally { // debugging
			traceOut("mVAREXPRESSION");
		}
	}
	
	protected final Object  mMETHODINVOCATION(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final Object  mMETHODINVOCATION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
		Object obj;
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mMETHODINVOCATION");
		_ttype = METHODINVOCATION;
		int _saveIndex;
		try { // debugging
			
			obj = null;
			
			
			mVAREXPRESSION(false);
			match('.');
D 8
			mMETHODNAME(false);
E 8
I 8
			mIDENT(false);
E 8
			match('(');
			mARGLIST(false);
			match(')');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
			return obj;
		} finally { // debugging
			traceOut("mMETHODINVOCATION");
		}
	}
	
D 8
	protected final Object  mARG(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final Object  mARG(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
		Object obj;
E 9
I 9
D 10
	protected final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 10
I 10
	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 10
E 9
		int _ttype; Token _token=null; int _begin=text.length();
D 9
		traceIn("mARG");
		_ttype = ARG;
E 9
I 9
D 10
		traceIn("mWS");
E 10
		_ttype = WS;
E 9
		int _saveIndex;
D 10
		try { // debugging
D 9
			Token str=null;
E 9
			
E 10
I 10
		
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
		}
		}
		}
		{
		_loop12:
		do {
E 10
			switch ( LA(1)) {
D 9
			case '$':
E 9
I 9
D 10
			case '\t':
E 10
I 10
			case ' ':
E 10
E 9
			{
D 9
				obj=mEXPRESSION(false);
E 9
I 9
D 10
				match('\t');
E 10
I 10
				match(' ');
E 10
E 9
				break;
			}
D 9
			case '"':
E 9
I 9
D 10
			case ' ':
E 10
I 10
			case '\t':
E 10
E 9
			{
D 9
				mSTRINGLITERAL(true);
				str=_returnToken;
				
				obj = new String(str.getText());
				
E 9
I 9
D 10
				match(' ');
E 10
I 10
				match('\t');
E 10
E 9
				break;
E 11
I 11
			else if ((LA(1)=='\n') && (true) && (true) && (true)) {
				match('\n');
				
						newline();
						System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
					
E 11
			}
D 11
			default:
			{
D 8
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 8
I 8
D 10
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 10
I 10
				break _loop12;
E 11
I 11
			else if ((LA(1)=='\r') && (true) && (true) && (true)) {
				match('\r');
				
						newline();
						System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
					
E 11
E 10
E 8
			}
I 11
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 11
			}
D 10
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
D 9
			return obj;
E 9
		} finally { // debugging
D 9
			traceOut("mARG");
E 9
I 9
			traceOut("mWS");
E 10
I 10
D 11
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 11
I 11
			
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIGNORE");
E 11
E 10
E 9
		}
I 10
D 11
		_returnToken = _token;
E 11
E 10
	}
	
D 8
	protected final void mMETHODNAME(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mMETHODNAME");
		_ttype = METHODNAME;
		int _saveIndex;
		try { // debugging
			
			mIDENT(false);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mMETHODNAME");
		}
	}
	
	protected final void mIDENT(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
D 9
	protected final void mIDENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 9
I 9
D 11
	protected final void mNEWLINE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 11
I 11
	public final void mCOMMANDBEGIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 11
E 9
E 8
		int _ttype; Token _token=null; int _begin=text.length();
D 9
		traceIn("mIDENT");
		_ttype = IDENT;
E 9
I 9
D 10
		traceIn("mNEWLINE");
E 10
D 11
		_ttype = NEWLINE;
E 11
I 11
		traceIn("mCOMMANDBEGIN");
		_ttype = COMMANDBEGIN;
E 11
E 9
		int _saveIndex;
D 10
		try { // debugging
			
E 10
I 10
D 11
		
		switch ( LA(1)) {
		case '\n':
		{
			match('\n');
			newline();
			break;
		}
		case '\r':
		{
			match('\r');
			newline();
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
E 11
I 11
		try { // debugging
			
			match(".");
			
					if (_Column != 1)
					{
						_ttype = Token.SKIP;
					}
			
				System.out.println("Pushing over to the command lexer");
					Exec.streamSelector.push("commandLexer");
				
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCOMMANDBEGIN");
E 11
		}
D 11
		_returnToken = _token;
E 11
	}
	
	protected final void mIDENTIFIER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
I 11
		traceIn("mIDENTIFIER");
E 11
		_ttype = IDENTIFIER;
		int _saveIndex;
D 11
		
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
		_loop17:
		do {
E 11
I 11
		try { // debugging
			
			{
E 11
E 10
D 9
			{
E 9
E 7
			switch ( LA(1)) {
D 7
			case ' ':
E 7
I 7
D 9
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':
E 9
I 9
D 10
			case '\n':
E 10
I 10
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
E 10
E 9
E 7
			{
D 7
				match(' ');
E 7
I 7
D 9
				matchRange('a','z');
E 9
I 9
D 10
				match('\n');
				newline();
E 10
I 10
				matchRange('A','Z');
E 10
E 9
E 7
				break;
			}
D 7
			case '\t':
E 7
I 7
D 9
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':
E 9
I 9
D 10
			case '\r':
E 10
I 10
D 11
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
E 10
E 9
E 7
			{
D 7
				match('\t');
E 7
I 7
D 9
				matchRange('A','Z');
E 9
I 9
D 10
				match('\r');
				newline();
E 10
I 10
				matchRange('0','9');
E 10
E 9
E 7
				break;
			}
E 11
D 6
			default:
E 6
I 6
D 7
			case '\14':
E 7
I 7
D 8
			case '_':
E 7
E 6
			{
D 6
				break _loop11;
			}
			}
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mCOMMA(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMA;
		int _saveIndex;
		
		match(',');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
E 4
	public final void mVAR_EXPANSION(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = VAR_EXPANSION;
		int _saveIndex;
D 5
		Token identifier=null;
E 5
I 5
		Token varName=null;
E 5
		
		match('$');
		match('{');
		mIDENT(true);
D 5
		identifier=_returnToken;
E 5
I 5
		varName=_returnToken;
E 5
		match('}');
		
D 5
		System.out.println("============== I found a variable " + identifier );
E 5
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mLITERAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LITERAL;
		int _saveIndex;
		
		match('"');
		{
D 4
		_loop9:
E 4
I 4
		_loop16:
E 4
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				{
				match(_tokenSet_1);
E 3
				}
D 3
				
			} while (true);
E 3
			}
D 3
			match('"');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSTRINGLITERAL");
E 3
I 3
			else {
D 4
				break _loop9;
E 4
I 4
				break _loop16;
E 4
			}
			
		} while (true);
E 3
		}
I 3
		match('"');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
E 3
	}
	
	protected final void mIGNORE(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
D 3
		traceIn("mIGNORE");
E 3
		_ttype = IGNORE;
		int _saveIndex;
D 3
		try { // debugging
			char  plaintext = '\0';
			
			plaintext = LA(1);
			matchNot(EOF_CHAR);
			
			System.out.print(plaintext);
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIGNORE");
E 3
I 3
		
		matchNot(EOF_CHAR);
		System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
I 5
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDOT_COMMAND(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DOT_COMMAND;
		int _saveIndex;
		Token varName=null;
		
		if ((LA(1)=='.') && (LA(2)=='s')) {
			match(".set");
			mWS(false);
			mIDENT(true);
			varName=_returnToken;
			mWS(false);
			match('=');
			mWS(false);
			{
			if ((LA(1)=='$') && (LA(2)=='{')) {
				mVAR_EXPANSION(false);
			}
			else if ((LA(1)=='$') && (_tokenSet_0.member(LA(2)))) {
				mMACRO_INVOCATION(false);
			}
			else if ((LA(1)=='"')) {
				mLITERAL(false);
			}
			else {
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
			}
			
E 6
I 6
D 7
				match('\f');
E 7
I 7
				match('_');
E 7
				break;
E 6
			}
E 8
D 6
			mWS(false);
			mNEWLINE(false);
			
			
		}
		else if ((LA(1)=='.') && (LA(2)=='i')) {
			match(".include");
			mWS(false);
E 6
I 6
			default:
E 6
			{
D 6
			if ((LA(1)=='$') && (LA(2)=='{')) {
				mVAR_EXPANSION(false);
			}
			else if ((LA(1)=='$') && (_tokenSet_0.member(LA(2)))) {
				mMACRO_INVOCATION(false);
E 6
I 6
D 7
				break _loop10;
E 7
I 7
D 8
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 8
I 8
D 10
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 10
I 10
D 11
				break _loop17;
E 11
I 11
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 11
E 10
E 8
			}
			}
D 9
			}
			{
D 8
			_loop19:
E 8
I 8
			_loop23:
E 8
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
D 8
				case '_':
				{
					match('_');
					break;
				}
E 8
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					matchRange('0','9');
					break;
				}
				default:
				{
D 8
					break _loop19;
E 8
I 8
					break _loop23;
E 8
				}
				}
			} while (true);
E 7
E 6
			}
I 7
D 8
			_ttype = testLiteralsTable(_ttype);
E 8
I 8
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
E 8
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 7
D 6
			else if ((LA(1)=='"')) {
				mLITERAL(false);
			}
			else {
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
E 6
			}
D 6
			
			}
			mWS(false);
			mNEWLINE(false);
			
			
		}
		else {
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mNEWLINE(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NEWLINE;
		int _saveIndex;
		
		switch ( LA(1)) {
		case '\n':
		{
			match('\n');
			break;
		}
		case 'r':
		{
			match('r');
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
E 6
I 6
D 7
		} while (true);
E 7
I 7
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIDENT");
E 7
E 6
		}
E 5
D 7
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 7
I 7
	}
	
D 8
	protected final void mIGNORE(boolean _createToken) throws ScannerException, IOException {
E 8
I 8
	protected final void mIGNORE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
E 8
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mIGNORE");
		_ttype = IGNORE;
		int _saveIndex;
		try { // debugging
			
D 8
			matchNot(EOF_CHAR);
			System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 8
I 8
			if (((LA(1) >= '\3' && LA(1) <= '\377')) && (true)) {
				matchNot(EOF_CHAR);
				System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
E 8
			}
D 8
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIGNORE");
		}
	}
	
	protected final void mWS(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mWS");
		_ttype = WS;
		int _saveIndex;
		try { // debugging
			
			{
			_loop28:
			do {
				switch ( LA(1)) {
				case ' ':
				{
					match(' ');
					break;
				}
				case '\t':
				{
					match('\t');
					break;
				}
				case '\14':
				{
					match('\f');
					break;
				}
				default:
				{
					break _loop28;
				}
				}
			} while (true);
E 8
I 8
			else if ((LA(1)=='\n') && (true)) {
				match('\n');
				newline();System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
			}
			else if ((LA(1)=='\r') && (true)) {
				match('\r');
				newline();System.out.print(new String(text.getBuffer(),_begin,text.length()-_begin));
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine());
E 8
			}
			
D 8
			_ttype = Token.SKIP;
			
E 8
I 8
			_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
E 9
E 8
D 10
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
D 8
			traceOut("mWS");
E 8
I 8
D 9
			traceOut("mIGNORE");
E 9
I 9
			traceOut("mNEWLINE");
E 10
I 10
D 11
		} while (true);
		}
		_ttype = testLiteralsTable(new String(text.getBuffer(),_begin,text.length()-_begin),_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
E 11
I 11
			}
			{
			_loop10:
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
					break _loop10;
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
E 11
E 10
E 9
E 8
E 7
E 3
		}
I 10
D 11
		_returnToken = _token;
E 11
E 10
I 3
D 7
		_returnToken = _token;
E 7
E 3
	}
	
	
D 3
	private static final long _tokenSet_0_data_[] = { -17179869192L, -268435457L, 0L, 0L };
E 3
I 3
D 6
	private static final long _tokenSet_0_data_[] = { 0L, 576460745995190270L, 0L, 0L };
E 6
I 6
D 8
	private static final long _tokenSet_0_data_[] = { -17179869192L, -268435457L, 0L, 0L };
E 8
I 8
D 9
	private static final long _tokenSet_0_data_[] = { -9224L, -1L, -1L, -1L, 0L, 0L, 0L, 0L };
E 9
I 9
D 11
	private static final long _tokenSet_0_data_[] = { -17179869192L, -1L, -1L, -1L, 0L, 0L, 0L, 0L };
E 9
E 8
E 6
E 3
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
E 11
I 7
D 8
	private static final long _tokenSet_1_data_[] = { 0L, 576460745995190270L, 0L, 0L };
E 8
I 8
D 9
	private static final long _tokenSet_1_data_[] = { 2289217578496L, 0L, 0L, 0L, 0L };
E 8
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
I 8
	private static final long _tokenSet_2_data_[] = { -17179869192L, -1L, -1L, -1L, 0L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_2 = new BitSet(_tokenSet_2_data_);
	private static final long _tokenSet_3_data_[] = { 0L, 576460743847706622L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_3 = new BitSet(_tokenSet_3_data_);
E 9
E 8
E 7
I 3
D 4
	private static final long _tokenSet_1_data_[] = { -17179869192L, -268435457L, 0L, 0L };
E 4
I 4
D 6
	private static final long _tokenSet_1_data_[] = { -17179869192L, -1L, 0L, 0L };
E 4
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
E 6
E 3
	
	}
E 2
I 1
E 1
