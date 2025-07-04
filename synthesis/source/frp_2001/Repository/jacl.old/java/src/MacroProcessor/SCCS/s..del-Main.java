h32473
s 00000/00000/00046
d D 1.4 00/03/01 14:27:12 jmochel 5 4
c Still trying to track down the reason the command lexer is dropping  a character.
cC
cK40110
cPjava/src/MacroProcessor/.del-Main.java
e
s 00022/00006/00024
d D 1.3 99/11/23 13:22:46 jmochel 4 3
cC
cK31880
cPjava/src/MacroProcessor/Main.java
e
s 00005/00012/00025
d D 1.2 99/11/19 13:17:37 jmochel 3 2
c Updated the grammar without getting it to work.
cC
cK56072
e
s 00037/00000/00000
d D 1.1 99/11/17 12:55:00 jmochel 2 1
cC
cK02722
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 12:54:57 jmochel 1 0
c BitKeeper file e:/jacl/java/src/MacroProcessor/CodeGeneratorMain.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK45362
cPjava/src/MacroProcessor/CodeGeneratorMain.java
cR2f93d73c5cb6ba86
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
package jacl.MacroProcessor;

E 4
I 2
import java.io.*;
I 4
import antlr.*;
E 4

D 4
class CodeGeneratorMain 
E 4
I 4
class Main 
E 4
{
I 4
    static TokenStreamSelector m_Selector = new TokenStreamSelector();
    
E 4
    public static void main(String[] args) 
    {
        try 
        {
            File                       inFile = null;
            FileInputStream            inputStream = null;
D 4
            CodeGeneratorTextLexer     lexer = null;
E 4
I 4
            TemplateLexer     		        templateLexer = null;
            CommandLexer     	        commandLexer = null;
E 4
            
D 4
            // Open the lexer with a file or stdin
E 4
I 4
            // Open the lexers with a file or stdin
E 4
            
D 3
            if ( args != null )
            {
                inFile = new File(args[0]);
            
                inputStream = new FileInputStream(inFile);
            
                lexer = new CodeGeneratorTextLexer(new DataInputStream(inputStream));
            }
            else 
            {
                lexer = new CodeGeneratorTextLexer(new DataInputStream(System.in));
            }
E 3
I 3
            inFile = new File(args[0]);
        
            inputStream = new FileInputStream(inFile);
        
D 4
            lexer = new CodeGeneratorTextLexer(new DataInputStream(inputStream));
E 4
I 4
            templateLexer = new TemplateLexer(new DataInputStream(inputStream));
            commandLexer = new CommandLexer(templateLexer.getInputState());
E 4
E 3
            
D 4
            CodeGeneratorTextParser parser = new CodeGeneratorTextParser(lexer);
E 4
I 4
	    // Open the stream sleector

	    TokenStreamSelector selector = new TokenStreamSelector();

	    selector.addInputStream(templateLexer, "templateLexer");
	    selector.addInputStream(commandLexer, "commandLexer");

	    selector.select("templateLexer");

            TemplateParser parser = new TemplateParser(selector);
E 4
            
D 4
            parser.startRule();
E 4
I 4
            parser.startOfTemplate();
E 4
        } 
        catch(Exception e) 
        {
            System.err.println("exception: "+e);
        }
    }
}
E 2
I 1
E 1
