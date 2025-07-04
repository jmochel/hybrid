h45419
s 00000/00000/00858
d D 1.3 99/11/23 12:12:30 jmochel 4 3
c Delete: java/src/MacroProcessor/CodeGeneratorTextLexer.java
cC
cK39340
cPjava/src/MacroProcessor/.del-CodeGeneratorTextLexer.java
e
s 00612/00020/00246
d D 1.2 99/11/19 13:17:37 jmochel 3 2
c Updated the grammar without getting it to work.
cC
cK55898
e
s 00266/00000/00000
d D 1.1 99/11/17 12:54:57 jmochel 2 1
cC
cK15194
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:53 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/CodeGeneratorTextLexer.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45361
cPjava/src/MacroProcessor/CodeGeneratorTextLexer.java
cR2f93d73d5cb6ba86
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
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.ScannerException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
public class CodeGeneratorTextLexer extends antlr.CharScanner implements CodeGeneratorTextParserTokenTypes, TokenStream
 {
public CodeGeneratorTextLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public CodeGeneratorTextLexer(Reader in) {
	this(new CharBuffer(in));
}
public CodeGeneratorTextLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public CodeGeneratorTextLexer(LexerSharedInputState state) {
	super(state);
	literals = new Hashtable();
caseSensitiveLiterals = true;
setCaseSensitive(true);
}

public Token nextToken() throws IOException {
	Token _rettoken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		setCommitToPath(false);
		int _m;
		_m = mark();
		resetText();
		try {   // for error handling
			if ((LA(1)=='.') && (LA(2)=='i') && (LA(3)=='n')) {
				mINCLUDE(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='.') && (LA(2)=='i') && (LA(3)=='f')) {
				mIF(true);
				_rettoken=_returnToken;
			}
D 3
			else if ((LA(1)=='.') && (LA(2)=='e')) {
E 3
I 3
			else if ((LA(1)=='.') && (LA(2)=='e') && (LA(3)=='n')) {
E 3
				mENDIF(true);
				_rettoken=_returnToken;
			}
I 3
			else if ((LA(1)=='=') && (LA(2)=='=')) {
				mEQUAL(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='!') && (LA(2)=='=')) {
				mNOT_EQUAL(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='/') && (LA(2)=='=')) {
				mDIV_ASSIGN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='+') && (LA(2)=='=')) {
				mPLUS_ASSIGN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='+') && (LA(2)=='+')) {
				mINC(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='-') && (LA(2)=='=')) {
				mMINUS_ASSIGN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='-') && (LA(2)=='-')) {
				mDEC(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='*') && (LA(2)=='=')) {
				mMULTIPLY_ASSIGN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='%') && (LA(2)=='=')) {
				mMOD_ASSIGN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='>') && (LA(2)=='=')) {
				mGE(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='<') && (LA(2)=='=')) {
				mLE(true);
				_rettoken=_returnToken;
			}
			else if (((LA(1) >= '0' && LA(1) <= '9'))) {
				mINT(true);
				_rettoken=_returnToken;
			}
E 3
			else if ((LA(1)=='"')) {
D 3
				mSTRINGLITERAL(true);
E 3
I 3
				mDELIMITEDSTRINGLITERAL(true);
				_rettoken=_returnToken;
			}
			else if ((_tokenSet_0.member(LA(1)))) {
				mIDENT(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='(')) {
				mLPAREN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)==')')) {
				mRPAREN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='{')) {
				mLCURLY(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='}')) {
				mRCURLY(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)==',')) {
				mCOMMA(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='=')) {
				mASSIGN(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='!')) {
				mLNOT(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='~')) {
				mBNOT(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='/')) {
				mDIV(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='+')) {
				mPLUS(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='-')) {
				mMINUS(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='*')) {
				mMULTIPLY(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='%')) {
				mMOD(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='>')) {
				mGT(true);
				_rettoken=_returnToken;
			}
			else if ((LA(1)=='<')) {
				mLT(true);
E 3
				_rettoken=_returnToken;
			}
			else {
D 3
				if (LA(1)==EOF_CHAR) {_returnToken = makeToken(Token.EOF_TYPE);}
				else {
					commit();
					try {mIGNORE(false);}
					catch(ScannerException e) {
						// catastrophic failure
						reportError(e);
						consume();
					}
					continue tryAgain;
				}
E 3
I 3
				mSTRINGLITERAL(true);
				_rettoken=_returnToken;
E 3
			}
			
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
			}
			reportError(e);
			consume();
		}
	}
}

	protected final void mIGNORE(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = IGNORE;
		int _saveIndex;
		char  plaintext = '\0';
		
		switch ( LA(1)) {
		case '\n':  case '\r':
		{
			{
			if ((LA(1)=='\r') && (LA(2)=='\n')) {
				match("\r\n");
			}
			else if ((LA(1)=='\r')) {
				match('\r');
			}
			else if ((LA(1)=='\n')) {
				match('\n');
			}
			else {
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
			}
			
			}
			
			newline(); 
			System.out.println("");
			
			break;
		}
		case '\3':  case '\4':  case '\5':  case '\6':
		case '\7':  case '\10':  case '\t':  case '\13':
		case '\14':  case '\16':  case '\17':  case '\20':
		case '\21':  case '\22':  case '\23':  case '\24':
		case '\25':  case '\26':  case '\27':  case '\30':
		case '\31':  case '\32':  case '\33':  case '\34':
		case '\35':  case '\36':  case '\37':  case ' ':
		case '!':  case '"':  case '#':  case '$':
		case '%':  case '&':  case '\'':  case '(':
		case ')':  case '*':  case '+':  case ',':
		case '-':  case '.':  case '/':  case '0':
		case '1':  case '2':  case '3':  case '4':
		case '5':  case '6':  case '7':  case '8':
		case '9':  case ':':  case ';':  case '<':
		case '=':  case '>':  case '?':  case '@':
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':  case '[':  case '\\':
		case ']':  case '^':  case '_':  case '`':
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':  case '{':  case '|':
		case '}':  case '~':  case '\177':
		{
			plaintext = LA(1);
			matchNot(EOF_CHAR);
			
			System.out.print(plaintext);
			
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
	}
	
	public final void mINCLUDE(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = INCLUDE;
		int _saveIndex;
		
		match(".include");
		
		System.out.println("\nFound an Include statement\n");
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mIF(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = IF;
		int _saveIndex;
		
		match(".if");
		
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mENDIF(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ENDIF;
		int _saveIndex;
		
		match(".endif");
		
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
D 3
	public final void mSTRINGLITERAL(boolean _createToken) throws ScannerException, IOException {
E 3
I 3
	public final void mINT(boolean _createToken) throws ScannerException, IOException {
E 3
		int _ttype; Token _token=null; int _begin=text.length();
D 3
		_ttype = STRINGLITERAL;
E 3
I 3
		_ttype = INT;
		int _saveIndex;
		
		{
		int _cnt30=0;
		_loop30:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				matchRange('0','9');
			}
			else {
				if ( _cnt30>=1 ) { break _loop30; } else {throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());}
			}
			
			_cnt30++;
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDELIMITEDSTRINGLITERAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DELIMITEDSTRINGLITERAL;
E 3
		int _saveIndex;
		
		match('"');
		{
D 3
		_loop14:
E 3
I 3
		_loop34:
E 3
		do {
D 3
			if ((_tokenSet_0.member(LA(1)))) {
E 3
I 3
			if ((_tokenSet_1.member(LA(1)))) {
E 3
				{
D 3
				match(_tokenSet_0);
E 3
I 3
				match(_tokenSet_1);
E 3
				}
			}
			else {
D 3
				break _loop14;
E 3
I 3
				break _loop34;
E 3
			}
			
		} while (true);
		}
		match('"');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
I 3
	public final void mSTRINGLITERAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STRINGLITERAL;
		int _saveIndex;
		
		{
		_loop38:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				{
				match(_tokenSet_1);
				}
			}
			else {
				break _loop38;
			}
			
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mIDENT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = IDENT;
		int _saveIndex;
		
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
		}
		}
		}
		{
		_loop42:
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
				break _loop42;
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
	
	public final void mLPAREN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LPAREN;
		int _saveIndex;
		
		match('(');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRPAREN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RPAREN;
		int _saveIndex;
		
		match(')');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLCURLY(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LCURLY;
		int _saveIndex;
		
		match('{');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRCURLY(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RCURLY;
		int _saveIndex;
		
		match('}');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMMA(boolean _createToken) throws ScannerException, IOException {
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
	
	public final void mASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ASSIGN;
		int _saveIndex;
		
		match('=');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEQUAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EQUAL;
		int _saveIndex;
		
		match("==");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLNOT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LNOT;
		int _saveIndex;
		
		match('!');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBNOT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BNOT;
		int _saveIndex;
		
		match('~');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNOT_EQUAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NOT_EQUAL;
		int _saveIndex;
		
		match("!=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIV(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIV;
		int _saveIndex;
		
		match('/');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIV_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIV_ASSIGN;
		int _saveIndex;
		
		match("/=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPLUS(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PLUS;
		int _saveIndex;
		
		match('+');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPLUS_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PLUS_ASSIGN;
		int _saveIndex;
		
		match("+=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mINC(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = INC;
		int _saveIndex;
		
		match("++");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMINUS(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MINUS;
		int _saveIndex;
		
		match('-');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMINUS_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MINUS_ASSIGN;
		int _saveIndex;
		
		match("-=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDEC(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DEC;
		int _saveIndex;
		
		match("--");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMULTIPLY(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MULTIPLY;
		int _saveIndex;
		
		match('*');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMULTIPLY_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MULTIPLY_ASSIGN;
		int _saveIndex;
		
		match("*=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMOD(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MOD;
		int _saveIndex;
		
		match('%');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMOD_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MOD_ASSIGN;
		int _saveIndex;
		
		match("%=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mGE(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = GE;
		int _saveIndex;
		
		match(">=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mGT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = GT;
		int _saveIndex;
		
		match(">");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLE(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LE;
		int _saveIndex;
		
		match("<=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LT;
		int _saveIndex;
		
		match('<');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
E 3
	
D 3
	private static final long _tokenSet_0_data_[] = { -17179869192L, -268435457L, 0L, 0L };
E 3
I 3
	private static final long _tokenSet_0_data_[] = { 0L, 576460745995190270L, 0L, 0L };
E 3
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
I 3
	private static final long _tokenSet_1_data_[] = { -17179869192L, -268435457L, 0L, 0L };
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
E 3
	
	}
E 2
I 1
E 1
