h30382
s 02001/00000/00000
d D 1.1 99/11/17 12:52:28 jmochel 2 1
cC
cK51864
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:52:24 jmochel 1 0
c BitKeeper file e:/jacl/java/src/testingtools/gauntlet/GauntletScriptLexer.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45346
cPjava/src/testingtools/gauntlet/GauntletScriptLexer.java
cR2f93d7e65cb6ba86
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
 * ANTLR-generated file resulting from grammar GauntletScriptParser.g
 * 
 * Terence Parr, MageLang Institute
 * with John Lilley, Empathy Software
 * ANTLR Version 2.4.0; 1996,1997
 */

    package jacl.test;


    import java.util.Vector;
    import java.util.Hashtable;    

    import java.lang.Character;

    import java.lang.reflect.Constructor;    
    import java.lang.reflect.Method;    
    import java.lang.reflect.Array;    
    
    import java.io.*;
    
    import java.util.Enumeration;
    
    import com.objectspace.jgl.*;

    import jacl.util.hash.*;


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
import antlr.Tokenizer;
import antlr.ANTLRHashString;
import antlr.collections.impl.BitSet;
public class GauntletScriptLexer extends antlr.CharScanner implements AlmostJavaTokenTypes, Tokenizer
 {
public GauntletScriptLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public GauntletScriptLexer(Reader in) {
	this(new CharBuffer(in));
}
public GauntletScriptLexer(InputBuffer ib) {
	super(ib);
	literals = new Hashtable();
	literals.put(new ANTLRHashString("synchronized", this), new Integer(37));
	literals.put(new ANTLRHashString("long", this), new Integer(27));
	literals.put(new ANTLRHashString("transient", this), new Integer(33));
	literals.put(new ANTLRHashString("protected", this), new Integer(31));
	literals.put(new ANTLRHashString("void", this), new Integer(20));
	literals.put(new ANTLRHashString("int", this), new Integer(25));
	literals.put(new ANTLRHashString("class", this), new Integer(4));
	literals.put(new ANTLRHashString("static", this), new Integer(32));
	literals.put(new ANTLRHashString("const", this), new Integer(38));
	literals.put(new ANTLRHashString("exclude", this), new Integer(39));
	literals.put(new ANTLRHashString("char", this), new Integer(23));
	literals.put(new ANTLRHashString("short", this), new Integer(24));
	literals.put(new ANTLRHashString("abstract", this), new Integer(34));
	literals.put(new ANTLRHashString("native", this), new Integer(35));
	literals.put(new ANTLRHashString("public", this), new Integer(30));
	literals.put(new ANTLRHashString("final", this), new Integer(12));
	literals.put(new ANTLRHashString("float", this), new Integer(26));
	literals.put(new ANTLRHashString("private", this), new Integer(29));
	literals.put(new ANTLRHashString("double", this), new Integer(28));
	literals.put(new ANTLRHashString("byte", this), new Integer(22));
	literals.put(new ANTLRHashString("boolean", this), new Integer(21));
	literals.put(new ANTLRHashString("threadsafe", this), new Integer(36));
caseSensitiveLiterals = true;
setCaseSensitive(true);
}

public Token nextToken() throws IOException {
	Token _rettoken=null;
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for error handling
			switch ( LA(1)) {
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
			case '[':
			{
				mLBRACK(true);
				_rettoken=_returnToken;
				break;
			}
			case ']':
			{
				mRBRACK(true);
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
			}
			case ':':
			{
				mCOLON(true);
				_rettoken=_returnToken;
				break;
			}
			case ',':
			{
				mCOMMA(true);
				_rettoken=_returnToken;
				break;
			}
			case '~':
			{
				mBNOT(true);
				_rettoken=_returnToken;
				break;
			}
			case ';':
			{
				mSEMI(true);
				_rettoken=_returnToken;
				break;
			}
			case '$':  case 'A':  case 'B':  case 'C':
			case 'D':  case 'E':  case 'F':  case 'G':
			case 'H':  case 'I':  case 'J':  case 'K':
			case 'L':  case 'M':  case 'N':  case 'O':
			case 'P':  case 'Q':  case 'R':  case 'S':
			case 'T':  case 'U':  case 'V':  case 'W':
			case 'X':  case 'Y':  case 'Z':  case '_':
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':
			{
				mIDENT(true);
				_rettoken=_returnToken;
				break;
			}
			case '\t':  case '\n':  case '\14':  case '\r':
			case ' ':
			{
				mWS(true);
				_rettoken=_returnToken;
				break;
			}
			case '\'':
			{
				mCHAR_LITERAL(true);
				_rettoken=_returnToken;
				break;
			}
			case '"':
			{
				mSTRING_LITERAL(true);
				_rettoken=_returnToken;
				break;
			}
			default:
				if ((LA(1)=='>') && (LA(2)=='>') && (LA(3)=='>') && (LA(4)=='=')) {
					mBSR_ASSIGN(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='>') && (LA(2)=='>') && (LA(3)=='=')) {
					mSR_ASSIGN(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='>') && (LA(2)=='>') && (LA(3)=='>')) {
					mBSR(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='<') && (LA(3)=='=')) {
					mSL_ASSIGN(true);
					_rettoken=_returnToken;
				}
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
				else if ((LA(1)=='*') && (LA(2)=='=')) {
					mSTAR_ASSIGN(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='%') && (LA(2)=='=')) {
					mMOD_ASSIGN(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='>') && (LA(2)=='>')) {
					mSR(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='>') && (LA(2)=='=')) {
					mGE(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='<')) {
					mSL(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='=')) {
					mLE(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='^') && (LA(2)=='=')) {
					mBXOR_ASSIGN(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='|') && (LA(2)=='=')) {
					mBOR_ASSIGN(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='|') && (LA(2)=='|')) {
					mLOR(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='&') && (LA(2)=='=')) {
					mBAND_ASSIGN(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='&') && (LA(2)=='&')) {
					mLAND(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='/') && (LA(2)=='/')) {
					mSL_COMMENT(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='/') && (LA(2)=='*')) {
					mML_COMMENT(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='.')) {
					mDOT(true);
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
				else if ((LA(1)=='/')) {
					mDIV(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='+')) {
					mPLUS(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='*')) {
					mSTAR(true);
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
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='^')) {
					mBXOR(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='|')) {
					mBOR(true);
					_rettoken=_returnToken;
				}
				else if ((LA(1)=='&')) {
					mBAND(true);
					_rettoken=_returnToken;
				}
				else if ((_tokenSet_0.member(LA(1)))) {
					mNUM_INT(true);
					_rettoken=_returnToken;
				}
			else {
				if (LA(1)!=EOF_CHAR) throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());_returnToken = makeToken(Token.EOF_TYPE);
			}
			}
			_ttype = _returnToken.getType();
			if ( _ttype!=Token.SKIP ) {
				_returnToken.setType(_ttype);
				return _returnToken;
			}
		}
		catch (ScannerException e) {
			consume();
			reportError(e);
		}
	}
}

	public final void mLPAREN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LPAREN;
		int _saveIndex;
		
		match('(');
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLBRACK(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LBRACK;
		int _saveIndex;
		
		match('[');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRBRACK(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RBRACK;
		int _saveIndex;
		
		match(']');
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOLON(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COLON;
		int _saveIndex;
		
		match(':');
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDOT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DOT;
		int _saveIndex;
		
		match('.');
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTAR(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STAR;
		int _saveIndex;
		
		match('*');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTAR_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STAR_ASSIGN;
		int _saveIndex;
		
		match("*=");
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSR(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SR;
		int _saveIndex;
		
		match(">>");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSR_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SR_ASSIGN;
		int _saveIndex;
		
		match(">>=");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBSR(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BSR;
		int _saveIndex;
		
		match(">>>");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBSR_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BSR_ASSIGN;
		int _saveIndex;
		
		match(">>>=");
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SL;
		int _saveIndex;
		
		match("<<");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSL_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SL_ASSIGN;
		int _saveIndex;
		
		match("<<=");
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
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
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBXOR(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BXOR;
		int _saveIndex;
		
		match('^');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBXOR_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BXOR_ASSIGN;
		int _saveIndex;
		
		match("^=");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBOR(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BOR;
		int _saveIndex;
		
		match('|');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBOR_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BOR_ASSIGN;
		int _saveIndex;
		
		match("|=");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLOR(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LOR;
		int _saveIndex;
		
		match("||");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBAND(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BAND;
		int _saveIndex;
		
		match('&');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBAND_ASSIGN(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BAND_ASSIGN;
		int _saveIndex;
		
		match("&=");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLAND(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LAND;
		int _saveIndex;
		
		match("&&");
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSEMI(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SEMI;
		int _saveIndex;
		
		match(';');
		if ( _createToken && _token==null ) {
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
		case '$':
		{
			match('$');
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		{
		_loop76:
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
			case '.':
			{
				match('.');
				break;
			}
			default:
			{
				break _loop76;
			}
			}
		} while (true);
		}
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mWS(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = WS;
		int _saveIndex;
		
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
		case '\14':
		{
			match('\f');
			break;
		}
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
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		_ttype = Token.SKIP;
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSL_COMMENT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SL_COMMENT;
		int _saveIndex;
		
		match("//");
		{
		_loop83:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				{
				match(_tokenSet_1);
				}
			}
			else {
				break _loop83;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case '\n':
		{
			match('\n');
			break;
		}
		case '\r':
		{
			match('\r');
			{
			if ((LA(1)=='\n')) {
				match('\n');
			}
			else {
			}
			
			}
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		_ttype = Token.SKIP; newline();
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mML_COMMENT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ML_COMMENT;
		int _saveIndex;
		
		match("/*");
		{
		_loop89:
		do {
			switch ( LA(1)) {
			case '\n':
			{
				match('\n');
				newline();
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
			case ')':  case '+':  case ',':  case '-':
			case '.':  case '/':  case '0':  case '1':
			case '2':  case '3':  case '4':  case '5':
			case '6':  case '7':  case '8':  case '9':
			case ':':  case ';':  case '<':  case '=':
			case '>':  case '?':  case '@':  case 'A':
			case 'B':  case 'C':  case 'D':  case 'E':
			case 'F':  case 'G':  case 'H':  case 'I':
			case 'J':  case 'K':  case 'L':  case 'M':
			case 'N':  case 'O':  case 'P':  case 'Q':
			case 'R':  case 'S':  case 'T':  case 'U':
			case 'V':  case 'W':  case 'X':  case 'Y':
			case 'Z':  case '[':  case '\\':  case ']':
			case '^':  case '_':  case '`':  case 'a':
			case 'b':  case 'c':  case 'd':  case 'e':
			case 'f':  case 'g':  case 'h':  case 'i':
			case 'j':  case 'k':  case 'l':  case 'm':
			case 'n':  case 'o':  case 'p':  case 'q':
			case 'r':  case 's':  case 't':  case 'u':
			case 'v':  case 'w':  case 'x':  case 'y':
			case 'z':  case '{':  case '|':  case '}':
			case '~':  case '\177':  case '\200':  case '\201':
			case '\202':  case '\203':  case '\204':  case '\205':
			case '\206':  case '\207':  case '\210':  case '\211':
			case '\212':  case '\213':  case '\214':  case '\215':
			case '\216':  case '\217':  case '\220':  case '\221':
			case '\222':  case '\223':  case '\224':  case '\225':
			case '\226':  case '\227':  case '\230':  case '\231':
			case '\232':  case '\233':  case '\234':  case '\235':
			case '\236':  case '\237':  case '\240':  case '\241':
			case '\242':  case '\243':  case '\244':  case '\245':
			case '\246':  case '\247':  case '\250':  case '\251':
			case '\252':  case '\253':  case '\254':  case '\255':
			case '\256':  case '\257':  case '\260':  case '\261':
			case '\262':  case '\263':  case '\264':  case '\265':
			case '\266':  case '\267':  case '\270':  case '\271':
			case '\272':  case '\273':  case '\274':  case '\275':
			case '\276':  case '\277':  case '\300':  case '\301':
			case '\302':  case '\303':  case '\304':  case '\305':
			case '\306':  case '\307':  case '\310':  case '\311':
			case '\312':  case '\313':  case '\314':  case '\315':
			case '\316':  case '\317':  case '\320':  case '\321':
			case '\322':  case '\323':  case '\324':  case '\325':
			case '\326':  case '\327':  case '\330':  case '\331':
			case '\332':  case '\333':  case '\334':  case '\335':
			case '\336':  case '\337':  case '\340':  case '\341':
			case '\342':  case '\343':  case '\344':  case '\345':
			case '\346':  case '\347':  case '\350':  case '\351':
			case '\352':  case '\353':  case '\354':  case '\355':
			case '\356':  case '\357':  case '\360':  case '\361':
			case '\362':  case '\363':  case '\364':  case '\365':
			case '\366':  case '\367':  case '\370':  case '\371':
			case '\372':  case '\373':  case '\374':  case '\375':
			case '\376':  case '\377':
			{
				{
				match(_tokenSet_2);
				}
				break;
			}
			default:
				if ((LA(1)=='\r') && (LA(2)=='\n') && ((LA(3) >= '\3' && LA(3) <= '\377')) && ((LA(4) >= '\3' && LA(4) <= '\377'))) {
					match('\r');
					match('\n');
					newline();
				}
				else if (((LA(1)=='*') && ((LA(2) >= '\3' && LA(2) <= '\377')) && ((LA(3) >= '\3' && LA(3) <= '\377')))&&( LA(2)!='/' )) {
					match('*');
				}
				else if ((LA(1)=='\r') && ((LA(2) >= '\3' && LA(2) <= '\377')) && ((LA(3) >= '\3' && LA(3) <= '\377'))) {
					match('\r');
					newline();
				}
			else {
				break _loop89;
			}
			}
		} while (true);
		}
		match("*/");
		_ttype = Token.SKIP;
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCHAR_LITERAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = CHAR_LITERAL;
		int _saveIndex;
		
		match('\'');
		{
		switch ( LA(1)) {
		case '\\':
		{
			mESC(false);
			break;
		}
		case '\3':  case '\4':  case '\5':  case '\6':
		case '\7':  case '\10':  case '\t':  case '\n':
		case '\13':  case '\14':  case '\r':  case '\16':
		case '\17':  case '\20':  case '\21':  case '\22':
		case '\23':  case '\24':  case '\25':  case '\26':
		case '\27':  case '\30':  case '\31':  case '\32':
		case '\33':  case '\34':  case '\35':  case '\36':
		case '\37':  case ' ':  case '!':  case '"':
		case '#':  case '$':  case '%':  case '&':
		case '(':  case ')':  case '*':  case '+':
		case ',':  case '-':  case '.':  case '/':
		case '0':  case '1':  case '2':  case '3':
		case '4':  case '5':  case '6':  case '7':
		case '8':  case '9':  case ':':  case ';':
		case '<':  case '=':  case '>':  case '?':
		case '@':  case 'A':  case 'B':  case 'C':
		case 'D':  case 'E':  case 'F':  case 'G':
		case 'H':  case 'I':  case 'J':  case 'K':
		case 'L':  case 'M':  case 'N':  case 'O':
		case 'P':  case 'Q':  case 'R':  case 'S':
		case 'T':  case 'U':  case 'V':  case 'W':
		case 'X':  case 'Y':  case 'Z':  case '[':
		case ']':  case '^':  case '_':  case '`':
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':  case '{':  case '|':
		case '}':  case '~':  case '\177':  case '\200':
		case '\201':  case '\202':  case '\203':  case '\204':
		case '\205':  case '\206':  case '\207':  case '\210':
		case '\211':  case '\212':  case '\213':  case '\214':
		case '\215':  case '\216':  case '\217':  case '\220':
		case '\221':  case '\222':  case '\223':  case '\224':
		case '\225':  case '\226':  case '\227':  case '\230':
		case '\231':  case '\232':  case '\233':  case '\234':
		case '\235':  case '\236':  case '\237':  case '\240':
		case '\241':  case '\242':  case '\243':  case '\244':
		case '\245':  case '\246':  case '\247':  case '\250':
		case '\251':  case '\252':  case '\253':  case '\254':
		case '\255':  case '\256':  case '\257':  case '\260':
		case '\261':  case '\262':  case '\263':  case '\264':
		case '\265':  case '\266':  case '\267':  case '\270':
		case '\271':  case '\272':  case '\273':  case '\274':
		case '\275':  case '\276':  case '\277':  case '\300':
		case '\301':  case '\302':  case '\303':  case '\304':
		case '\305':  case '\306':  case '\307':  case '\310':
		case '\311':  case '\312':  case '\313':  case '\314':
		case '\315':  case '\316':  case '\317':  case '\320':
		case '\321':  case '\322':  case '\323':  case '\324':
		case '\325':  case '\326':  case '\327':  case '\330':
		case '\331':  case '\332':  case '\333':  case '\334':
		case '\335':  case '\336':  case '\337':  case '\340':
		case '\341':  case '\342':  case '\343':  case '\344':
		case '\345':  case '\346':  case '\347':  case '\350':
		case '\351':  case '\352':  case '\353':  case '\354':
		case '\355':  case '\356':  case '\357':  case '\360':
		case '\361':  case '\362':  case '\363':  case '\364':
		case '\365':  case '\366':  case '\367':  case '\370':
		case '\371':  case '\372':  case '\373':  case '\374':
		case '\375':  case '\376':  case '\377':
		{
			matchNot('\'');
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		match('\'');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mESC(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ESC;
		int _saveIndex;
		
		match('\\');
		{
		switch ( LA(1)) {
		case 'n':
		{
			match('n');
			break;
		}
		case 'r':
		{
			match('r');
			break;
		}
		case 't':
		{
			match('t');
			break;
		}
		case 'b':
		{
			match('b');
			break;
		}
		case 'f':
		{
			match('f');
			break;
		}
		case '"':
		{
			match('"');
			break;
		}
		case '\'':
		{
			match('\'');
			break;
		}
		case '\\':
		{
			match('\\');
			break;
		}
		case 'u':
		{
			{
			int _cnt99=0;
			_loop99:
			do {
				if ((LA(1)=='u')) {
					match('u');
				}
				else {
					if ( _cnt99>=1 ) { break _loop99; } else {throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());}
				}
				
				_cnt99++;
			} while (true);
			}
			mHEX_DIGIT(false);
			mHEX_DIGIT(false);
			mHEX_DIGIT(false);
			mHEX_DIGIT(false);
			break;
		}
		case '0':  case '1':  case '2':  case '3':
		{
			{
			matchRange('0','3');
			}
			{
			if (((LA(1) >= '0' && LA(1) <= '9')) && ((LA(2) >= '\3' && LA(2) <= '\377'))) {
				{
				matchRange('0','9');
				}
				{
				if (((LA(1) >= '0' && LA(1) <= '9')) && ((LA(2) >= '\3' && LA(2) <= '\377'))) {
					matchRange('0','9');
				}
				else if (((LA(1) >= '\3' && LA(1) <= '\377'))) {
				}
				else {
					throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
				}
				
				}
			}
			else if (((LA(1) >= '\3' && LA(1) <= '\377'))) {
			}
			else {
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
			}
			
			}
			break;
		}
		case '4':  case '5':  case '6':  case '7':
		{
			{
			matchRange('4','7');
			}
			{
			if (((LA(1) >= '0' && LA(1) <= '9')) && ((LA(2) >= '\3' && LA(2) <= '\377'))) {
				{
				matchRange('0','9');
				}
			}
			else if (((LA(1) >= '\3' && LA(1) <= '\377'))) {
			}
			else {
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
			}
			
			}
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTRING_LITERAL(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STRING_LITERAL;
		int _saveIndex;
		
		match('"');
		{
		_loop95:
		do {
			switch ( LA(1)) {
			case '\\':
			{
				mESC(false);
				break;
			}
			case '\3':  case '\4':  case '\5':  case '\6':
			case '\7':  case '\10':  case '\t':  case '\n':
			case '\13':  case '\14':  case '\r':  case '\16':
			case '\17':  case '\20':  case '\21':  case '\22':
			case '\23':  case '\24':  case '\25':  case '\26':
			case '\27':  case '\30':  case '\31':  case '\32':
			case '\33':  case '\34':  case '\35':  case '\36':
			case '\37':  case ' ':  case '!':  case '#':
			case '$':  case '%':  case '&':  case '\'':
			case '(':  case ')':  case '*':  case '+':
			case ',':  case '-':  case '.':  case '/':
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':  case ':':  case ';':
			case '<':  case '=':  case '>':  case '?':
			case '@':  case 'A':  case 'B':  case 'C':
			case 'D':  case 'E':  case 'F':  case 'G':
			case 'H':  case 'I':  case 'J':  case 'K':
			case 'L':  case 'M':  case 'N':  case 'O':
			case 'P':  case 'Q':  case 'R':  case 'S':
			case 'T':  case 'U':  case 'V':  case 'W':
			case 'X':  case 'Y':  case 'Z':  case '[':
			case ']':  case '^':  case '_':  case '`':
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':  case '{':  case '|':
			case '}':  case '~':  case '\177':  case '\200':
			case '\201':  case '\202':  case '\203':  case '\204':
			case '\205':  case '\206':  case '\207':  case '\210':
			case '\211':  case '\212':  case '\213':  case '\214':
			case '\215':  case '\216':  case '\217':  case '\220':
			case '\221':  case '\222':  case '\223':  case '\224':
			case '\225':  case '\226':  case '\227':  case '\230':
			case '\231':  case '\232':  case '\233':  case '\234':
			case '\235':  case '\236':  case '\237':  case '\240':
			case '\241':  case '\242':  case '\243':  case '\244':
			case '\245':  case '\246':  case '\247':  case '\250':
			case '\251':  case '\252':  case '\253':  case '\254':
			case '\255':  case '\256':  case '\257':  case '\260':
			case '\261':  case '\262':  case '\263':  case '\264':
			case '\265':  case '\266':  case '\267':  case '\270':
			case '\271':  case '\272':  case '\273':  case '\274':
			case '\275':  case '\276':  case '\277':  case '\300':
			case '\301':  case '\302':  case '\303':  case '\304':
			case '\305':  case '\306':  case '\307':  case '\310':
			case '\311':  case '\312':  case '\313':  case '\314':
			case '\315':  case '\316':  case '\317':  case '\320':
			case '\321':  case '\322':  case '\323':  case '\324':
			case '\325':  case '\326':  case '\327':  case '\330':
			case '\331':  case '\332':  case '\333':  case '\334':
			case '\335':  case '\336':  case '\337':  case '\340':
			case '\341':  case '\342':  case '\343':  case '\344':
			case '\345':  case '\346':  case '\347':  case '\350':
			case '\351':  case '\352':  case '\353':  case '\354':
			case '\355':  case '\356':  case '\357':  case '\360':
			case '\361':  case '\362':  case '\363':  case '\364':
			case '\365':  case '\366':  case '\367':  case '\370':
			case '\371':  case '\372':  case '\373':  case '\374':
			case '\375':  case '\376':  case '\377':
			{
				{
				match(_tokenSet_3);
				}
				break;
			}
			default:
			{
				break _loop95;
			}
			}
		} while (true);
		}
		match('"');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mHEX_DIGIT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = HEX_DIGIT;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case '0':  case '1':  case '2':  case '3':
		case '4':  case '5':  case '6':  case '7':
		case '8':  case '9':
		{
			matchRange('0','9');
			break;
		}
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':
		{
			matchRange('A','F');
			break;
		}
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':
		{
			matchRange('a','f');
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mVOCAB(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = VOCAB;
		int _saveIndex;
		
		matchRange('\3','\377');
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNUM_INT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NUM_INT;
		int _saveIndex;
		boolean isDecimal=false;
		
		switch ( LA(1)) {
		case '.':
		{
			match('.');
			_ttype = DOT;
			{
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				{
				int _cnt113=0;
				_loop113:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						if ( _cnt113>=1 ) { break _loop113; } else {throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());}
					}
					
					_cnt113++;
				} while (true);
				}
				{
				if ((LA(1)=='E'||LA(1)=='e')) {
					mEXPONENT(false);
				}
				else {
				}
				
				}
				{
				if ((_tokenSet_4.member(LA(1)))) {
					mFLOAT_SUFFIX(false);
				}
				else {
				}
				
				}
				_ttype = NUM_FLOAT;
			}
			else {
			}
			
			}
			break;
		}
		case '-':  case '0':  case '1':  case '2':
		case '3':  case '4':  case '5':  case '6':
		case '7':  case '8':  case '9':
		{
			{
			switch ( LA(1)) {
			case '0':
			{
				match('0');
				isDecimal = true;
				{
				switch ( LA(1)) {
				case 'X':  case 'x':
				{
					{
					switch ( LA(1)) {
					case 'x':
					{
						match('x');
						break;
					}
					case 'X':
					{
						match('X');
						break;
					}
					default:
					{
						throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
					}
					}
					}
					{
					int _cnt120=0;
					_loop120:
					do {
						if ((_tokenSet_5.member(LA(1)))) {
							mHEX_DIGIT(false);
						}
						else {
							if ( _cnt120>=1 ) { break _loop120; } else {throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());}
						}
						
						_cnt120++;
					} while (true);
					}
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				{
					{
					int _cnt122=0;
					_loop122:
					do {
						if (((LA(1) >= '0' && LA(1) <= '7'))) {
							matchRange('0','7');
						}
						else {
							if ( _cnt122>=1 ) { break _loop122; } else {throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());}
						}
						
						_cnt122++;
					} while (true);
					}
					break;
				}
				default:
					{
					}
				}
				}
				break;
			}
			case '-':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				{
				_loop124:
				do {
					if ((LA(1)=='-')) {
						match('-');
					}
					else {
						break _loop124;
					}
					
				} while (true);
				}
				{
				matchRange('1','9');
				}
				{
				_loop127:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						break _loop127;
					}
					
				} while (true);
				}
				isDecimal=true;
				break;
			}
			default:
			{
				throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
			}
			}
			}
			{
			if ((LA(1)=='L'||LA(1)=='l')) {
				{
				switch ( LA(1)) {
				case 'l':
				{
					match('l');
					break;
				}
				case 'L':
				{
					match('L');
					break;
				}
				default:
				{
					throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
				}
				}
				}
			}
			else if (((_tokenSet_6.member(LA(1))))&&(isDecimal)) {
				{
				switch ( LA(1)) {
				case '-':  case '.':
				{
					{
					_loop132:
					do {
						if ((LA(1)=='-')) {
							match('-');
						}
						else {
							break _loop132;
						}
						
					} while (true);
					}
					match('.');
					{
					_loop134:
					do {
						if (((LA(1) >= '0' && LA(1) <= '9'))) {
							matchRange('0','9');
						}
						else {
							break _loop134;
						}
						
					} while (true);
					}
					{
					if ((LA(1)=='E'||LA(1)=='e')) {
						mEXPONENT(false);
					}
					else {
					}
					
					}
					{
					if ((_tokenSet_4.member(LA(1)))) {
						mFLOAT_SUFFIX(false);
					}
					else {
					}
					
					}
					break;
				}
				case 'E':  case 'e':
				{
					mEXPONENT(false);
					{
					if ((_tokenSet_4.member(LA(1)))) {
						mFLOAT_SUFFIX(false);
					}
					else {
					}
					
					}
					break;
				}
				case 'D':  case 'F':  case 'd':  case 'f':
				{
					mFLOAT_SUFFIX(false);
					break;
				}
				default:
				{
					throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
				}
				}
				}
				_ttype = NUM_FLOAT;
			}
			else {
			}
			
			}
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mEXPONENT(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EXPONENT;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case 'e':
		{
			match('e');
			break;
		}
		case 'E':
		{
			match('E');
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		{
		switch ( LA(1)) {
		case '+':
		{
			match('+');
			break;
		}
		case '-':
		{
			match('-');
			break;
		}
		case '0':  case '1':  case '2':  case '3':
		case '4':  case '5':  case '6':  case '7':
		case '8':  case '9':
		{
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		}
		{
		int _cnt142=0;
		_loop142:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				matchRange('0','9');
			}
			else {
				if ( _cnt142>=1 ) { break _loop142; } else {throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());}
			}
			
			_cnt142++;
		} while (true);
		}
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mFLOAT_SUFFIX(boolean _createToken) throws ScannerException, IOException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = FLOAT_SUFFIX;
		int _saveIndex;
		
		switch ( LA(1)) {
		case 'f':
		{
			match('f');
			break;
		}
		case 'F':
		{
			match('F');
			break;
		}
		case 'd':
		{
			match('d');
			break;
		}
		case 'D':
		{
			match('D');
			break;
		}
		default:
		{
			throw new ScannerException("no viable alt for char: "+(char)LA(1),getLine());
		}
		}
		if ( _createToken && _token==null ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long _tokenSet_0_data_[] = { 288054454291267584L, 0L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_0 = new BitSet(_tokenSet_0_data_);
	private static final long _tokenSet_1_data_[] = { -9224L, -1L, -1L, -1L, 0L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_1 = new BitSet(_tokenSet_1_data_);
	private static final long _tokenSet_2_data_[] = { -4398046520328L, -1L, -1L, -1L, 0L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_2 = new BitSet(_tokenSet_2_data_);
	private static final long _tokenSet_3_data_[] = { -17179869192L, -268435457L, -1L, -1L, 0L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_3 = new BitSet(_tokenSet_3_data_);
	private static final long _tokenSet_4_data_[] = { 0L, 343597383760L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_4 = new BitSet(_tokenSet_4_data_);
	private static final long _tokenSet_5_data_[] = { 287948901175001088L, 541165879422L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_5 = new BitSet(_tokenSet_5_data_);
	private static final long _tokenSet_6_data_[] = { 105553116266496L, 481036337264L, 0L, 0L, 0L };
	public static final BitSet _tokenSet_6 = new BitSet(_tokenSet_6_data_);
	
	}
E 2
I 1
E 1
