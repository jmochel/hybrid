package SwingExperiments;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class E009_PlayingWithTextFields extends JFrame
{
    public E009_PlayingWithTextFields(String title)
    {
        super(title);

        JTextField textField = new JTextField("Hi Mom!", 25);
        
        this.getContentPane().add(textField);
        
     
    }
   
    public static void main(String[] args)
    {
        JFrame frame = new E009_PlayingWithTextFields("Texting...1.2.3.");
        
        // Add Listeners
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt)
            {
                System.exit(0);
            }
        });
        
        frame.pack();
        frame.show();
    }
    
};