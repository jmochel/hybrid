package bardictales;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator; 

public class HTMLImageExtractor extends Object
{     
    public static void main(String args[])
    {         
        if (args.length != 1)
        {
            System.err.println("usage: java HTMLImageExtractor <file>");
            System.exit(-1);
        }
        try
       {
			// Convert urlString into a BufferedReader
			URL url = new URL(args[0]);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr); // Create empty HTMLDocument to read into

			HTMLEditorKit htmlKit = new HTMLEditorKit();
			HTMLDocument htmlDoc = (HTMLDocument)htmlKit.createDefaultDocument();
			// Create parser (javax.swing.text.html.parser.ParserDelegator)
			HTMLEditorKit.Parser parser = new ParserDelegator();
			// Get parser callback from document
			HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
			// Load it (true means to ignore character set)
			parser.parse(br, callback, true);
			
			Element elt = htmlDoc.getDefaultRootElement();
			
			ElementIterator iter = new ElementIterator(elt);
			
			while ((elt = iter.next()) != null )
			{
				AttributeSet attrs = elt.getAttributes();
				
				System.out.println(attrs.toString());
				System.out.println(attrs.getClass());

			}
/*
        ===== Element Class: HTMLDocument$BlockElement
        Offsets [599, 601]
        ATTRIBUTES:
         (name, td) [StyleConstants/HTML$Tag]
          ===== Element Class: HTMLDocument$BlockElement
          Offsets [599, 601]
          ATTRIBUTES:
           (name, p-implied) [StyleConstants/HTML$Tag]
            ===== Element Class: HTMLDocument$RunElement
            Offsets [599, 600]
            ATTRIBUTES:
             (src, kamon/asahina.jpg) [HTML$Attribute/String]
             (name, img) [StyleConstants/HTML$Tag]
             (alt, Bessho: daimy? family of Harima) [HTML$Attribute/String]
            [ ]
            ===== Element Class: HTMLDocument$RunElement
            Offsets [600, 601]
            ATTRIBUTES:
             (name, content) [StyleConstants/HTML$Tag]
*/	
       }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}