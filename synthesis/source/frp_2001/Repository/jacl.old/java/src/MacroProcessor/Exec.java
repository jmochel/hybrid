package jacl.MacroProcessor;

import java.io.*;
import antlr.TokenStreamSelector;;

class Exec 
{
   // Define a selector that can switch from lexer to lexer
   
   static TokenStreamSelector streamSelector = new TokenStreamSelector();

    public static void main(String[] args) 
    {
        try 
        {
        
            File                       inFile = null;
            FileInputStream            inputStream = null;
            TemplateLexer     		   templateLexer = null;
            CommandLexer               commandLexer = null;
            DataInputStream            dataInputStream = null;
            
            // Open the Input stream 
            
            inFile = new File(args[0]);
        
            inputStream = new FileInputStream(inFile);
         
            // Set up the two lexers
            
            dataInputStream = new DataInputStream(inputStream);
            
            templateLexer = new TemplateLexer(dataInputStream);
            //templateLexer.setTokenObjectClass("jacl.MacroProcessor.TemplateLexerToken");

            commandLexer = new CommandLexer(dataInputStream);
            //templateLexer.setTokenObjectClass("jacl.MacroProcessor.TemplateLexerToken");
            
            streamSelector.addInputStream(templateLexer, "templateLexer");
            streamSelector.addInputStream(commandLexer, "commandLexer");
            
            streamSelector.select("templateLexer");
                        
            // Construct the parser
                                    
            TemplateParser parser = new TemplateParser(streamSelector);
            
            parser.template();
        } 
        catch (java.io.FileNotFoundException fileNotFoundError)
        {
             System.err.println("exception: "+fileNotFoundError);
        }
        catch (java.io.IOException IOError)
        {
            System.err.println("exception: "+IOError);
        }
/*        catch (antlr.ParserException  parserError)
        {
            System.err.println("exception: "+parserError);
        }
        */
        catch(RuntimeException e) 
        {
            System.err.println("some exception: "+e);
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.err.println("some exception: "+e);
            e.printStackTrace();
        }
        
    }
}

