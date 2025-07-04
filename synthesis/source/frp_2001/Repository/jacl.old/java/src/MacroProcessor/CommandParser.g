header 
{
    package jacl.MacroProcessor;
}

{
    import java.io.*;
    import antlr.*;
}

/**
    Parses text that has dot commands and expressions interwoven.
*/

class CommandParser extends Parser;
options 
{
    importVocab=Command;
} 

/**
    Look for commands and other text processing expressions
*/

command
    : generalCommand
	| variableCommand
    ;

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

expression
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

macroInvocation
	: EXPRESSIONLEADIN IDENTIFIER OPENPAREN expression CLOSEPAREN
	;