h38849
s 00027/00004/00049
d D 1.7 00/02/29 17:09:48 jmochel 8 7
c Added a bit more code.
cC
cK40988
e
s 00002/00004/00051
d D 1.6 00/02/04 09:48:46 jmochel 7 6
c Grammar looks like it is almost finished but I am running into a problem with the Lexer.
c If the Lexer uses all protected methods (which ppears to be allowable in the ANTLR docs)
c then the Lexer acts as if it was given a 0 length file.  
cC
cK33927
e
s 00001/00000/00054
d D 1.5 00/02/03 12:21:30 jmochel 6 5
c Redesign !
cC
cK36993
e
s 00005/00002/00049
d D 1.4 00/01/11 15:30:29 jmochel 5 4
c Updated
cC
cK36727
e
s 00001/00001/00050
d D 1.3 00/01/07 14:21:14 jmochel 4 3
c Rewrote the grammar for ease of access. Having problems debugging. I will try another IDE.
cC
cK33249
e
s 00021/00014/00030
d D 1.2 99/12/17 15:04:22 jmochel 3 2
c Restart
cC
cK33956
e
s 00044/00000/00000
d D 1.1 99/12/03 08:28:04 jmochel 2 1
cC
cK32926
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/03 08:28:01 jmochel 1 0
c BitKeeper file E:/jacl/java/src/MacroProcessor/Exec.java
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK50928
cPjava/src/MacroProcessor/Exec.java
cRe86a1a5f5cb68e15
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
package jacl.MacroProcessor;

import java.io.*;
I 8
import antlr.TokenStreamSelector;;
E 8
D 3
import antlr.TokenStreamSelector;
E 3

class Exec 
{
I 8
   // Define a selector that can switch from lexer to lexer
   
   static TokenStreamSelector streamSelector = new TokenStreamSelector();

E 8
D 3
    public static TokenStreamSelector    selector = new TokenStreamSelector();
        
E 3
    public static void main(String[] args) 
    {
        try 
        {
I 6
        
E 6
            File                       inFile = null;
            FileInputStream            inputStream = null;
            TemplateLexer     		   templateLexer = null;
I 8
            CommandLexer               commandLexer = null;
            DataInputStream            dataInputStream = null;
E 8
D 3
            CommandLexer     	       commandLexer = null;
E 3
            
D 8
            // Open the lexers with a file or stdin
E 8
I 8
            // Open the Input stream 
E 8
            
D 5
            inFile = new File(args[0]);
E 5
I 5
D 7
           // inFile = new File(args[0]);
E 7
I 7
            inFile = new File(args[0]);
E 7
E 5
        
I 5
D 7
            inFile = new File("Sample.cg");

E 7
E 5
            inputStream = new FileInputStream(inFile);
D 8
        
            templateLexer = new TemplateLexer(new DataInputStream(inputStream));
E 8
I 8
         
            // Set up the two lexers
            
            dataInputStream = new DataInputStream(inputStream);
            
            templateLexer = new TemplateLexer(dataInputStream);
            //templateLexer.setTokenObjectClass("jacl.MacroProcessor.TemplateLexerToken");
E 8
D 3
            commandLexer = new CommandLexer(templateLexer.getInputState());

            //TokenStreamSelector selector = new TokenStreamSelector();
            
            selector.addInputStream(templateLexer, "templateLexer");
            selector.addInputStream(commandLexer, "commandLexer");
    
            selector.select("templateLexer");
E 3

D 3
            TemplateParser parser = new TemplateParser(selector);
E 3
I 3
D 8
            TemplateParser parser = new TemplateParser(templateLexer);
E 8
I 8
            commandLexer = new CommandLexer(dataInputStream);
            //templateLexer.setTokenObjectClass("jacl.MacroProcessor.TemplateLexerToken");
            
            streamSelector.addInputStream(templateLexer, "templateLexer");
            streamSelector.addInputStream(commandLexer, "commandLexer");
            
            streamSelector.select("templateLexer");
                        
            // Construct the parser
                                    
            TemplateParser parser = new TemplateParser(streamSelector);
E 8
E 3
            
D 4
            parser.startOfTemplate();
E 4
I 4
            parser.template();
E 4
        } 
I 3
        catch (java.io.FileNotFoundException fileNotFoundError)
        {
             System.err.println("exception: "+fileNotFoundError);
        }
        catch (java.io.IOException IOError)
        {
            System.err.println("exception: "+IOError);
        }
D 5
        catch (antlr.ParserException  parserError)
E 5
I 5
/*        catch (antlr.ParserException  parserError)
E 5
        {
            System.err.println("exception: "+parserError);
        }
I 5
        */
E 5
        catch(RuntimeException e) 
        {
            System.err.println("some exception: "+e);
            e.printStackTrace();
        }
E 3
D 7
        catch(Exception e) 
E 7
I 7
        catch(Exception e)
E 7
        {
D 3
            System.err.println("exception: "+e);
E 3
I 3
            System.err.println("some exception: "+e);
            e.printStackTrace();
E 3
        }
I 3
        
E 3
    }
}
I 8

E 8
E 2
I 1
E 1
