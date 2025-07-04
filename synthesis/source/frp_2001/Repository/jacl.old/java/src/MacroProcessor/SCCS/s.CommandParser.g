h19229
s 00107/00034/00031
d D 1.3 00/02/29 17:09:48 jmochel 4 3
c Added a bit more code.
cC
cK50515
e
s 00017/00000/00048
d D 1.2 99/12/02 17:11:06 jmochel 3 2
c Now does basic recognition of a variable expansion
cC
cK03890
e
s 00048/00000/00000
d D 1.1 99/11/23 13:25:25 jmochel 2 1
cC
cK57727
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/23 13:25:21 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/CommandParser.g
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK31848
cPjava/src/MacroProcessor/CommandParser.g
cRc5c2c1ad5cb68643
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 3
header 
{
    package jacl.MacroProcessor;
}

{
    import java.io.*;
    import antlr.*;
}

E 3
I 2
/**
    Parses text that has dot commands and expressions interwoven.
*/

class CommandParser extends Parser;
options 
{
D 4
    k=2;
I 3

E 4
    importVocab=Command;
E 3
} 

/**
    Look for commands and other text processing expressions
D 4
    
    <p>The Lexer has a filter that automatically echoes non-command text
    so we don't have to take note of the unalterable text when we are processing the 
    script.
E 4
*/

command
D 4
    : includeCommand
E 4
I 4
    : generalCommand
	| variableCommand
E 4
    ;

D 4
includeCommand
    : INCLUDE expression NEWLINE
    ;
E 4
I 4
//
//	General Commands
// 
    
generalCommand
	: comment
	| include
	| redirection
	;

comment
	: COMMENT . NEWLINE
	;
	
include
	: INCLUDE expression NEWLINE
	;
	
redirection
	: REDIRECT expression NEWLINE
	;

//
//	Commands for manipulating flow of control
//

//flowOfControlCommand
//	: ifConstruct
//	| whileLoop
//	| forLoop
//	| exitCommand
//	;

/*
ifConstruct
	:	
	;

whileLoop
	: 
	;

forLoop
	: 
	;

exitCommand
	: ".exit" WS expression  WS NEWLINE
	;
*/

//
//	Commands for manipulating variables
//

variableCommand
	: setCommand
	| appendCommand
	;

setCommand
	:  SET var EQUALS expression  (expression)*  NEWLINE
	;

appendCommand
	: SET var PLUSEQUALS expression  (expression)*  NEWLINE
	;

//
//	Commands for manipulating macros
//


/*
macroCommand
	: macroDefinition
	;

macroDefinition
	:
	;
*/
E 4

expression
D 4
    : macroInvocation
    | variableExpansion
    | literal
I 3
    | ident
E 3
    ;
E 4
I 4
	: expansion
	| STRINGLITERAL
	;

//
//	Expansions
//


expansion
	: varExpansion
	| macroInvocation
	;

varExpansion
	: EXPRESSIONLEADIN OPENCURLY var CLOSECURLY
	;

var
	: EXPRESSIONLEADIN  IDENTIFIER 
	;
E 4

macroInvocation
D 4
    : DOLLAR IDENT LPAREN ( arg )* RPAREN
    ;

arg
    : expression
    ;

I 3
ident
    : IDENT
    ;
    
E 3
literal
    : STRINGLITERAL
    ;

variableExpansion
    : DOLLAR LCURLY IDENT RCURLY
    ;

E 4
I 4
	: EXPRESSIONLEADIN IDENTIFIER OPENPAREN expression CLOSEPAREN
	;
E 4N
E 2
I 1
E 1
