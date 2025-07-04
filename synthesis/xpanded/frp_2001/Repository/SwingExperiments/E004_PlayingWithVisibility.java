package SwingExperiments;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.*;

public class E004_PlayingWithVisibility
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Getting A Component's Position");
        JButton    button1 = new JButton("First");
        JButton    button2 = new JButton("Second");
        JButton    button3 = new JButton("Third");

        Container contentPane = frame.getContentPane();
    
        contentPane.setLayout(new FlowLayout());
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        
        button2.setVisible(false);

        frame.setBounds(40, 50, 250, 200);
        frame.pack();
        frame.setVisible(true);

        System.out.println("Frame's position is " + frame.getLocation());
        System.out.println("Frame's screen position is " + frame.getLocationOnScreen());
        System.out.println("Content pane's position is " + contentPane.getLocation());
        System.out.println("Content pane's screen position is " + contentPane.getLocationOnScreen());
        System.out.println("Button 1's position is " + button1.getLocation());
        System.out.println("Button 1's screen position is " + button1.getLocationOnScreen());

        try 
        {
            Thread.sleep(5000);
            System.out.println("Make second button visble");
            
            button2.setVisible(true);
            
            Thread.sleep(5000);
            
            frame.pack();
            
            Thread.sleep(5000);
            System.out.println("Disabling second button");
            
            button2.setEnabled(false);
            
            Thread.sleep(5000);
            
            frame.pack();
        }
        catch(Exception error)
        {
        }
    }

};
    