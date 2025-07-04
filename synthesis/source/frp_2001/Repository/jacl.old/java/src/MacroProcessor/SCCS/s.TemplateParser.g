h13467
s 00000/00001/00056
d D 1.12 00/03/01 14:27:12 jmochel 13 12
c Still trying to track down the reason the command lexer is dropping  a character.
cC
cK56749
e
s 00013/00101/00044
d D 1.11 00/02/29 17:09:48 jmochel 12 11
c Added a bit more code.
cC
cK56759
e
s 00005/00005/00140
d D 1.10 00/02/04 09:48:47 jmochel 11 10
c Grammar looks like it is almost finished but I am running into a problem with the Lexer.
c If the Lexer uses all protected methods (which ppears to be allowable in the ANTLR docs)
c then the Lexer acts as if it was given a 0 length file.  
cC
cK55078
e
s 00123/00001/00022
d D 1.9 00/02/03 12:21:30 jmochel 10 9
c Redesign !
cC
cK55782
e
s 00001/00002/00022
d D 1.8 00/01/11 15:30:30 jmochel 9 8
c Updated
cC
cK23972
e
s 00002/00014/00022
d D 1.7 00/01/07 14:22:51 jmochel 8 7
c Rewrote the grammar.
cC
cK24235
e
s 00007/00016/00029
d D 1.6 99/12/17 15:04:22 jmochel 7 6
c Restart
cC
cK34183
e
s 00005/00000/00040
d D 1.5 99/12/07 07:46:00 jmochel 6 5
c Now recognizes include statements
cC
cK41702
e
s 00008/00038/00032
d D 1.4 99/12/02 17:11:07 jmochel 5 4
c Now does basic recognition of a variable expansion
cC
cK38066
e
s 00033/00200/00037
d D 1.3 99/11/23 13:22:46 jmochel 4 3
cC
cK15162
cPjava/src/MacroProcessor/TemplateParser.g
e
s 00173/00012/00064
d D 1.2 99/11/19 13:17:37 jmochel 3 2
c Updated the grammar without getting it to work.
cC
cK52291
e
s 00076/00000/00000
d D 1.1 99/11/17 12:55:11 jmochel 2 1
cC
cK21390
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:55:07 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/CodeGeneratorTextParser.g
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45363
cPjava/src/MacroProcessor/CodeGeneratorTextParser.g
cR2f93d7395cb6ba86
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 4
header 
{
    package jacl.MacroProcessor;
}

{
    import java.io.*;
I 5
    import antlr.*;
E 5
}

E 4
I 2
/**
    Parses text that has dot commands and expressions interwoven.
*/

D 4
class CodeGeneratorTextParser extends Parser;
E 4
I 4
class TemplateParser extends Parser;
options 
{
I 12
	importVocab=Template;
E 12
I 10
    k = 3;
E 10
D 5
    k=2;
    
E 5
D 9
    importVocab=Template;
E 9
}
E 4

D 5
/**
    Look for commands and other text processing expressions
    
    <p>The Lexer has a filter that automatically echoes non-command text
D 4
    so we don't have to note it.
E 4
I 4
    so we don't have to take note of the unalterable text when we are processing the 
    script.
E 4
*/

E 5
D 4
startRule
D 3
    :   ( dotCommand | templateExpression )*
E 3
I 3
    :    (statement)* EOF!
E 4
I 4
D 8
startOfTemplate
D 5
    :    (statement)* EOF
E 5
I 5
    : (statement)* EOF
E 5
E 4
E 3
    ;

D 3
/**
    Dot Commands are the line commands 
*/
E 3
I 3
statement
D 4
    :    ( dotCommand )* 
    |    ( expression )*
E 4
I 4
D 7
    : expression
I 6
    | dotCommand
E 6
D 5
    | dotCommand
E 4
    ;
E 3

dotCommand
D 3
    : INCLUDE expression
    | IF expression
    | ENDIF
E 3
I 3
D 4
    :    includeCommand
    |    assignmentCommand
    ;
    
includeCommand
    : INCLUDE expression NEWLINE
    | INCLUDE DELIMITEDSTRINGLITERAL NEWLINE
E 3
    ;

D 3
templateExpression 
    : SET identifier expression
E 3
I 3
assignmentCommand
    : SET identifier ASSIGN expression NEWLINE
E 4
I 4
    :    DOTCOMMAND_START
         {
             CommandParser commandParser = new CommandParser(getInputState());
             commandParser.command();
         }
         DOTCOMAND_END
E 5
E 4
E 3
    ;
D 3
    
E 3
I 3

E 3
expression
D 3
    : CHARLITERAL
E 3
I 3
D 5
    : macroInvocation
    | variableExpansion
    | literal
    ;
D 4
    
E 4
I 4

E 4
macroInvocation
D 4
    : DOLLAR identifier LPAREN (argument)* RPAREN 
E 4
I 4
    : DOLLAR IDENT LPAREN ( arg )* RPAREN
E 4
    ;

D 4
argument
E 4
I 4
arg
E 4
    : expression
D 4
    | variable
    ;
    
variableExpansion
    : DOLLAR LCURLY identifier RCURLY
    ;

variable
    : identifier
    ;
    
identifier
    : IDENT
E 4
    ;

literal
D 4
    : numericLiteral
    | stringLiteral
    ;

numericLiteral
    : INT
    ;
    
stringLiteral
E 4
E 3
    : STRINGLITERAL
E 5
I 5
    : variableExpansion
    | macroInvocation
E 5
I 3
D 4
    | DELIMITEDSTRINGLITERAL
E 3
    ;
    
class CodeGeneratorTextLexer extends Lexer;
options 
{
  k=3;
  filter=IGNORE;
  charVocabulary = '\3'..'\177';
} 

// one-or-more letters followed by a newline

protected IGNORE
  : ( "\r\n" | '\r' | '\n' )
    {
        newline(); 
        System.out.println("");
    }
  | plaintext:.
    {
        System.out.print(plaintext);
    }
  ;
  
INCLUDE: ".include"
{
    System.out.println("\nFound an Include statement\n");
}    
E 4
    ;

I 3
D 4

E 3
IF:   ".if"
{
}    
    ;

ENDIF:   ".endif"
{
}    
    ;    
    
D 3
STRINGLITERAL: '"' (~('"'|'\\'))* '"'
E 3
I 3
INT 
    : ('0'..'9')+
    ;
  
DELIMITEDSTRINGLITERAL
    : '"' (~('"'|'\\'))* '"'
    ;

STRINGLITERAL
    : (~('"'|'\\'))*
E 4
I 4
variableExpansion
D 5
    : DOLLAR LCURLY IDENT RCURLY
E 5
I 5
    : VAR_EXPANSION
E 5
E 4
    ;

D 4
IDENT
    :    ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$')*
    ;
    
    
LPAREN            
    :    '('        
    ;
    
RPAREN            
    :    ')'        
    ;
    
LCURLY
    :    '{'        
    ;
    
RCURLY            
    :    '}'        
    ;
    
COMMA            
    :    ','        
    ;
    
ASSIGN            
    :    '='        
E 3
    ;
I 3
    
EQUAL            
    :    "=="    
    ;
    
LNOT            
    :    '!'        
    ;
    
BNOT            
    :    '~'        
    ;
    
NOT_EQUAL        
    :    "!="    
    ;
E 4
D 5

E 5
I 5
macroInvocation
    : MACRO_INVOCATION
E 7
I 7
    : dotCommand
E 7
I 6
    ;

dotCommand
D 7
    : DOT_COMMAND
E 7
I 7
    : INCLUDE WS expression WS NEWLINE
    ;
    
protected expression
    : STRINGLITERAL
E 8
I 8
template
D 9
    : (DOTCOMMAND | GENEXPRESSION) * EOF
E 9
I 9
D 10
    : (DOTCOMMAND | GENEXPRESSION)(DOTCOMMAND | GENEXPRESSION)* EOF
E 10
I 10
    : (command | expansion)(command | expansion)* EOF
E 10
E 9
E 8
E 7
E 6
    ;
I 10

D 12
command
    : generalCommand
//    | flowOfControlCommand
	| variableCommand
//    | macroCommand
    ;

//
//	General Commands
// 
E 10
I 7
    
I 10
generalCommand
	: comment
	| include
	| redirection
	;

comment
	: ".." . NEWLINE
	;
	
include
D 11
	: ".include" WS* expression WS* NEWLINE
E 11
I 11
	: ".include" WS expression WS NEWLINE
E 11
	;
	
redirection
D 11
	: ".redirect" WS* expression WS* NEWLINE
E 11
I 11
	: ".redirect" WS expression WS NEWLINE
E 11
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
D 11
	: ".exit"  WS* expression  WS* NEWLINE
E 11
I 11
	: ".exit" WS expression  WS NEWLINE
E 11
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
D 11
	: ".set"  WS* var  WS* "="  WS* expression  WS* (expression)*  WS* NEWLINE
E 11
I 11
	: ".set"  WS var  WS "="  WS expression  WS (expression)*  WS NEWLINE
E 11
	;

appendCommand
D 11
	: ".set"  WS* var  WS* "+="  WS* expression  WS* (expression)*  WS* NEWLINE
E 11
I 11
	: ".set"  WS var  WS "+="  WS expression  WS (expression)*  WS NEWLINE
E 11
	;

//
//	Commands for manipulating macros
//

E 12

D 12
/*
macroCommand
	: macroDefinition
	;
E 12
I 12
command
	: COMMANDBEGIN
	{
		CommandParser commandParser = new CommandParser(getInputState());
E 12
D 13

E 13
D 12
macroDefinition
	:
E 12
I 12
		commandParser.command();
	}
	COMMANDEND
E 12
	;
D 12
*/
E 12

//
D 12
//	Expressions
E 12
I 12
//	Expansions
E 12
//

D 12
protected expression
	: expansion
	| stringLiteral
	;
E 12

D 12
protected expansion
E 12
I 12
expansion
E 12
	: varExpansion
	| macroInvocation
	;

varExpansion
D 12
	: "$" "{" var "}" 
E 12
I 12
	: EXPRESSIONLEADIN OPENCURLY var CLOSECURLY
E 12
	;

var
D 12
	: "$" IDENTIFIER 
E 12
I 12
	: EXPRESSIONLEADIN  IDENTIFIER 
E 12
	;

macroInvocation
D 12
	: "$" IDENTIFIER "(" expression ")"
	;

stringLiteral
	: STRINGLITERAL
E 12
I 12
	: EXPRESSIONLEADIN IDENTIFIER OPENPAREN expansion CLOSEPAREN
E 12
	;
E 10N
E 7
E 5
D 4
DIV                
    :    '/'        
    ;
    
DIV_ASSIGN        
    :    "/="    
    ;
    
PLUS            
    :    '+'        
    ;
    
PLUS_ASSIGN        
    :    "+="    
    ;
    
INC                
    :    "++"    
    ;
    
MINUS            
    :    '-'        
    ;
    
MINUS_ASSIGN    
    :    "-="    
    ;
    
DEC                
    :    "--"    
    ;
    
MULTIPLY            
    :    '*'        
    ;
    
MULTIPLY_ASSIGN        
    :    "*="    
    ;
    
MOD                
    :    '%'        
    ;
    
MOD_ASSIGN        
    :    "%="    
    ;
    
GE                
    :    ">="    
    ;
    
GT                
    :    ">"        
    ;
    
LE                
    :    "<="    
    ;
    
LT                
    :    '<'        
    ;
    
E 4
E 3
E 2
I 1
E 1
