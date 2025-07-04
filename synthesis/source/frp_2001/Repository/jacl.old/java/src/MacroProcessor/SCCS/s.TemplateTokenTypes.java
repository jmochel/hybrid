h09724
s 00006/00017/00007
d D 1.8 00/02/29 17:09:48 jmochel 9 8
c Added a bit more code.
cC
cK21271
e
s 00010/00016/00014
d D 1.7 00/01/11 15:30:30 jmochel 8 7
c Updated
cC
cK36432
e
s 00017/00005/00013
d D 1.6 00/01/07 14:22:51 jmochel 7 6
c Rewrote the grammar.
cC
cK43228
e
s 00005/00012/00013
d D 1.5 99/12/17 15:04:23 jmochel 6 5
c Restart
cC
cK26671
e
s 00002/00000/00023
d D 1.4 99/12/07 07:46:00 jmochel 5 4
c Now recognizes include statements
cC
cK36484
e
s 00009/00005/00014
d D 1.3 99/12/07 07:06:36 jmochel 4 3
c Macro Invocations are now recognized
cC
cK33782
e
s 00006/00010/00013
d D 1.2 99/12/02 17:11:07 jmochel 3 2
c Now does basic recognition of a variable expansion
cC
cK28445
e
s 00023/00000/00000
d D 1.1 99/11/23 13:25:05 jmochel 2 1
cC
cK33703
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/23 13:25:02 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/TemplateTokenTypes.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK31846
cPjava/src/MacroProcessor/TemplateTokenTypes.java
cRd7d5c05c5cb68643
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

public interface TemplateTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
D 3
	int DOTCOMMAND_START = 4;
	int DOTCOMMAND_END = 5;
	int LPAREN = 6;
	int RPAREN = 7;
	int LCURLY = 8;
	int RCURLY = 9;
	int DOLLAR = 10;
	int IDENT = 11;
	int STRINGLITERAL = 12;
	int IGNORE = 13;
E 3
I 3
D 6
	int MACRO_INVOCATION = 4;
D 4
	int ARG = 5;
	int VAR_EXPANSION = 6;
	int LITERAL = 7;
	int IDENT = 8;
	int IGNORE = 9;
E 4
I 4
	int ARG_LIST_OR_NOTHING = 5;
	int ARG_LIST = 6;
	int ARG = 7;
	int COMMA = 8;
	int WS = 9;
	int VAR_EXPANSION = 10;
	int LITERAL = 11;
	int IDENT = 12;
	int IGNORE = 13;
I 5
	int DOT_COMMAND = 14;
	int NEWLINE = 15;
E 6
I 6
D 7
	int IGNORE = 4;
	int INCLUDE = 5;
	int STRINGLITERAL = 6;
	int NEWLINE = 7;
	int WS = 8;
E 7
I 7
D 9
	int DOTCOMMAND = 4;
	int COMMENT = 5;
	int SETCOMMAND = 6;
D 8
	int GENEXPRESSION = 7;
	int EXPRESSION = 8;
	int ARGLIST = 9;
	int ARG = 10;
	int VAREXPRESSION = 11;
	int METHODINVOCATION = 12;
	int EXPANSION = 13;
	int VARNAME = 14;
	int METHODNAME = 15;
E 8
I 8
	int INCLUDECOMMAND = 7;
	int APPENDCOMMAND = 8;
	int GENEXPRESSION = 9;
	int EXPRESSION = 10;
	int ARGLIST = 11;
	int ARG = 12;
	int VAREXPRESSION = 13;
	int METHODINVOCATION = 14;
	int EXPANSION = 15;
E 8
	int IDENT = 16;
	int IGNORE = 17;
	int STRINGLITERAL = 18;
	int NEWLINE = 19;
E 9
I 9
	int COMMANDBEGIN = 5;
	int COMMANDEND = 6;
	int EXPRESSIONLEADIN = 7;
	int IGNORE = 8;
	int IDENTIFIER = 9;
E 9
D 8
	int WS = 20;
E 8
E 7
E 6
E 5
E 4
E 3
}
E 2
I 1
E 1
