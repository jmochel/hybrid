package SwingExperiments;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

class ExampleMouseListener implements MouseListener, MouseMotionListener
{
    public void mousePressed(MouseEvent evt)
    {
        System.out.println("mousePressed = " + evt);
    }

    public void mouseReleased(MouseEvent evt)
    {
        System.out.println("mouseReleased = " + evt);
    }
    
    public void mouseClicked(MouseEvent evt)
    {
        System.out.println("mouseClicked = " + evt);
    }
    
    public void mouseEntered(MouseEvent evt)
    {
        System.out.println("mouseEntered = " + evt);
    }
    
    public void mouseExited(MouseEvent evt)
    {
        System.out.println("mouseExited = " + evt);
    }
    
    public void mouseMoved(MouseEvent evt)
    {
        System.out.println("mouseMoved = " + evt);
    }

    public void mouseDragged(MouseEvent evt)
    {
        System.out.println("mouseDragged = " + evt);
    }
}

public class E005_PlayingWithMouseEvents
{
   
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Getting A Component's Position");
        JButton    button1 = new JButton("OK");
        JButton    button2 = new JButton("Cancel");

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(button1);
        contentPane.add(button2);
        
        // Add Mouse Listeners
        
        MouseListener mouseListener1 = new ExampleMouseListener();
        MouseMotionListener mouseListener2 = new ExampleMouseListener();
        
        button1.addMouseListener(mouseListener1);
        button2.addMouseListener(mouseListener1);
        button1.addMouseMotionListener(mouseListener2);
        button2.addMouseMotionListener(mouseListener2);
        
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
    