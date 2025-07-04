h45390
s 00082/00020/00037
d D 1.11 00/02/29 17:09:48 jmochel 12 11
c Added a bit more code.
cC
cK58503
e
s 00014/00018/00043
d D 1.10 00/02/04 09:48:47 jmochel 11 10
c Grammar looks like it is almost finished but I am running into a problem with the Lexer.
c If the Lexer uses all protected methods (which ppears to be allowable in the ANTLR docs)
c then the Lexer acts as if it was given a 0 length file.  
cC
cK61095
e
s 00008/00144/00053
d D 1.9 00/02/03 12:21:30 jmochel 10 9
c Redesign !
cC
cK61325
e
s 00068/00038/00129
d D 1.8 00/01/11 15:30:30 jmochel 9 8
c Updated
cC
cK25037
e
s 00128/00007/00039
d D 1.7 00/01/07 14:22:51 jmochel 8 7
c Rewrote the grammar.
cC
cK58866
e
s 00011/00050/00035
d D 1.6 99/12/17 15:04:22 jmochel 7 6
c Restart
cC
cK37717
e
s 00018/00005/00067
d D 1.5 99/12/07 07:46:00 jmochel 6 5
c Now recognizes include statements
cC
cK11592
e
s 00021/00002/00051
d D 1.4 99/12/07 07:06:36 jmochel 5 4
c Macro Invocations are now recognized
cC
cK06149
e
s 00023/00042/00030
d D 1.3 99/12/02 17:11:07 jmochel 4 3
c Now does basic recognition of a variable expansion
cC
cK57480
e
s 00034/00007/00038
d D 1.2 99/11/23 13:22:46 jmochel 3 2
cC
cK53977
e
s 00045/00000/00000
d D 1.1 99/11/23 12:46:59 jmochel 2 1
cC
cK41926
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/23 12:46:56 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/TemplateLexer.g
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK31617
cPjava/src/MacroProcessor/TemplateLexer.g
cR6bad9bbf5cb6865d
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
D 12
    Template Text Lexer
E 12
I 12
    Template Lexer
E 12
*/

D 4
header
E 4
I 4
header 
E 4
{
    package jacl.MacroProcessor;
}

{
D 11
    import java.io.*;
I 8
    import java.util.*;
E 11
E 8
I 3
    import antlr.*;
E 3
}

D 3
class TextLexer extends Lexer;
E 3
I 3
class TemplateLexer extends Lexer;
E 3
options
{
D 4
    filter=IGNORE;
E 4
I 4
D 10
    k = 2;
E 10
I 10
D 12
    k = 3;
E 12
I 12
    k = 4;
E 12
E 10
E 4
D 9
    charVocabulary = '\3'..'\177';
E 9
I 9
    charVocabulary = '\3'..'\377';
I 12
	importVocab=Common;
	exportVocab=Template;
E 12
E 9
D 4
  
E 4
I 4
    filter=IGNORE;
E 4
D 3
    importVocab=Common;
E 3
D 9
    exportVocab=Template;
E 9
D 8
} 
E 8
I 8
}
I 12
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
E 12
E 8
I 6
D 11
{
I 8
D 10
    /**
        Variables declared in the template
    */
    
    Hashtable    m_Variables = new Hashtable();
E 10
E 8
}
E 11
E 6

D 4
// one-or-more letters followed by a newline

DOTCOMMAND_START
D 3
    : ".include" { selector.push("commandLexer"); }
E 3
I 3
    : ".include" { Main.m_Selector.push("commandLexer"); }
E 3
    ;

DOTCOMMAND_END
D 3
    : NEWLINE { selector.pop(); }
    ;

NEWLINE
E 3
    : '\r'
    | '\n'
I 3
    { Main.m_Selector.pop(); }
    ;

LPAREN    
    : '('
    ;
    
RPAREN
    : ')'
E 4
I 4
D 7
MACRO_INVOCATION
D 5
    : '$' macroname:IDENT '(' (ARG)* ')'
E 5
I 5
D 6
    : '$' macroname:IDENT '(' ARG_LIST_OR_NOTHING ')'
E 6
I 6
    : '$' macroName:IDENT '(' ARG_LIST_OR_NOTHING ')'
E 6
E 5
    {
D 6
        System.out.println("============== I found a macro " + macroname );
E 6
    }
E 7
I 7
D 8
protected IGNORE
    :    .    { System.out.print($getText); }
E 8
I 8
D 10
DOTCOMMAND
    : COMMENT
    | SETCOMMAND
D 9
    | ".include" (EXPANSION | STRINGLITERAL)
    | ".append" VARNAME '=' ARGLIST
E 9
I 9
    | INCLUDECOMMAND 
    | APPENDCOMMAND
E 9
    ;

protected COMMENT
D 9
    : ".." . NEWLINE
E 9
I 9
    : ".." (~('\n'|'\r'))* NEWLINE
E 9
    ;
D 9
    
E 9
I 9
   
E 9
protected SETCOMMAND
    {
D 9
        Vector arglist;
E 9
I 9
        Object var = null;
        Object args = null;        
    }
    : ".set" WS var=VAREXPRESSION WS args=ARGLIST WS NEWLINE
    {
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
    }
    ;

protected INCLUDECOMMAND
    : ".include" WS (EXPANSION | STRINGLITERAL) WS NEWLINE
    {
    }
    ;

protected APPENDCOMMAND
    {
        Object var;
        Vector args;
E 9
    }
D 9
    : ".set" varname:VARNAME '=' arglist=ARGLIST
E 9
I 9
    :  ".append" WS var=VAREXPRESSION WS args=ARGLIST WS NEWLINE
E 9
    {
D 9
        System.out.println("SETCOMMAND " );
        m_Variables.put(varname.getText(), arglist);
        System.out.println(m_Variables);
E 9
    }
    ;
    
GENEXPRESSION
    {
        Object obj;
    }
    : obj=EXPRESSION
    ;
        
protected EXPRESSION
    returns [Object obj]
    : obj=VAREXPRESSION
    | obj=METHODINVOCATION
    | obj=EXPANSION
    {
        System.out.print((String) obj);
    }
    ;
        
protected ARGLIST
    returns [Vector list]
    {
        Object arg = null;
        
        list = new Vector();
    }
D 9
    : (arg=ARG)*
E 9
I 9
    : ((arg=ARG) WS)*
E 9
    {
        list.add(arg);
    }
    ;
    
protected ARG    
    returns [Object obj]
D 9
    : obj=EXPRESSION  
E 9
I 9
    : obj=EXPRESSION
E 9
    | str:STRINGLITERAL
    {
        obj = new String(str.getText());
    }
E 8
E 7
E 4
    ;
I 5

D 7
protected ARG_LIST_OR_NOTHING
    :   ( ARG_LIST 
        | /* Nothing */
        )
E 7
I 7
D 8
INCLUDE 
    :    ".include"
E 8
I 8
protected VAREXPRESSION
    returns [Object obj]
D 9
    : '$' varname:VARNAME
E 9
I 9
    : '$' varname:IDENT
E 9
    {
        obj = m_Variables.get(varname.getText());
        
D 9
        // Throw an exception if  the variable doesn't exist
E 9
I 9
        /* 
            If the variable doesn't exist
            return a string with the name in it.
        */
E 9
        
        if (obj == null)
        {
D 9
            System.out.println("ERROR variable " + varname + " Was not found");
            System.out.println(m_Variables);
E 9
I 9
            obj = new String(varname.getText());
E 9
        }
    }
    ;

protected METHODINVOCATION
    returns [Object obj]
    {
        obj = null;
    }
D 9
    : VAREXPRESSION '.' METHODNAME '(' ARGLIST ')' 
E 9
I 9
    : VAREXPRESSION '.' IDENT '(' ARGLIST ')' 
E 9
    ;
    
protected EXPANSION
    returns [String str]
    {
        Object obj;
    }
    : '$' '{' obj=EXPRESSION '}'
    {
        if ( obj.getClass().getName() == new String("java.lang.String") )
        {
            str = (String) obj;
        }
        else 
        {
            str = obj.toString();
        }
    }
    ;
    
D 9
protected VARNAME
    : IDENT 
    ;

protected METHODNAME
    : IDENT 
    ;
    
E 9
protected IDENT
E 10
I 10
D 11
protected IDENTIFIER
E 10
D 9
    options { testLiterals=true;}
    :    ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
E 9
I 9
    options 
    {
         testLiterals =  true;
    }
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')*
E 9
    ;
D 9
        
E 9
I 9
 
E 11
I 11

E 11
E 9
protected IGNORE
I 9
    options 
    {
         testLiterals =  true;
    }
E 9
    :    .    { System.out.print($getText); }
I 11
	| 	(' '|'\t')(' '|'\t')* { System.out.print($getText); }
E 11
I 9
D 12
    |    '\n' {newline();System.out.print($getText);}
    |    '\r' {newline();System.out.print($getText);}
E 9
E 8
E 7
    ;

D 7
protected ARG_LIST
    : ARG  ( WS COMMA WS ARG )*
E 7
I 7
protected STRINGLITERAL
D 8
    :    '"' (~('"'|'\\'))* '"'
E 8
I 8
D 9
    :    '"' (~('"'|'\\'))* '"' 
E 9
I 9
    options 
    {
         testLiterals =  true;
    }
    :    '"' (~('"'))* '"'
E 12
I 12
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
E 12
E 9
E 8
E 7
    ;
I 10

D 11
protected WS
    :   '\t'
	|   ' '
    ;
E 10
E 5
    
E 11
I 11
D 12
WS
	:	(' '|'\t')(' '|'\t')*
E 12
I 12
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
E 12
	;
D 12
	    
E 11
D 4
LCURLY
    : '{'
E 4
I 4
D 7
protected ARG
    : VAR_EXPANSION
    | LITERAL
E 4
E 3
    ;
I 3
D 4
    
RCURLY
    : '}'
E 4
I 4

I 5
protected COMMA
    : ','
    ;

protected WS 
    : ( ' ' | '\t' )*
    ; 

D 6

E 6
E 5
VAR_EXPANSION
D 6
    : '$' '{' identifier:IDENT '}'
E 6
I 6
    : '$' '{' varName:IDENT '}'
E 6
    {
D 6
        System.out.println("============== I found a variable " + identifier );
E 6
    }
E 7
I 7
D 9
protected NEWLINE 
    :    '\n'
    |    '\r'
E 9
I 9
protected NEWLINE
    :    '\n' {newline();}
    |    '\r' {newline();}
E 9
E 7
E 4
    ;
E 12
I 12

E 12
D 10
    
D 4
DOLLAR
    : '$'
E 4
I 4
D 7
protected LITERAL
D 5
    :    '"' (~('"'|'\\'))* '"'
E 5
I 5
    :    '"' (~('"'))* '"'
E 7
I 7
D 9
protected WS    
    :    ( ' '  | '\t'  | '\f' )*
I 8
    { 
        $setType(Token.SKIP);
    }
E 9
I 9
protected WS
    : ('\t' | ' ')*
E 9
E 8
E 7
E 5
E 4
    ;
E 10
I 10
D 11
    
E 11
I 11
    
protected IDENTIFIER
    options 
    {
         testLiterals =  true;
    }
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')*
D 12
    ;
E 12
I 12
    ;
E 12N
E 11
E 10N
D 8

E 8
D 4
IDENT
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$')*
E 4
I 4
D 7
protected IDENT
	:	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$')*
E 4
	;

D 4
STRINGLITERAL
	:	'"' (~('"'|'\\'))* '"'
	;


E 3
    
E 4
protected IGNORE
D 4
  : plaintext:.
    {
        System.out.print(plaintext);
    }
  ;

E 4
I 4
    :    . {System.out.print($getText);}
I 6
    ;

DOT_COMMAND
    :    ".set" WS varName:IDENT WS '=' WS ( VAR_EXPANSION | LITERAL | MACRO_INVOCATION ) WS NEWLINE
    {
    }
    |    ".include" WS ( VAR_EXPANSION | LITERAL | MACRO_INVOCATION ) WS NEWLINE
    {
    }
    ;
    
protected NEWLINE
    : '\n'
    | 'r'
E 6
    ;
E 7
E 4
E 2
I 1
E 1
