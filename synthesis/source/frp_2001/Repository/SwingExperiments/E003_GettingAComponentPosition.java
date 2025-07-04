package SwingExperiments;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.*;

public class E003_GettingAComponentPosition
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Getting A Component's Position");
	    JButton	button = new JButton("Press Here");

	    Container contentPane = frame.getContentPane();
	
	    contentPane.setLayout(new FlowLayout());
	    contentPane.add(button);

	    frame.setBounds(40, 50, 250, 200);
        frame.setVisible(true);

        System.out.println("Frame's position is " + frame.getLocation());
        System.out.println("Frame's screen position is " + frame.getLocationOnScreen());
        System.out.println("Content pane's position is " + contentPane.getLocation());
        System.out.println("Content pane's screen position is " + contentPane.getLocationOnScreen());
        System.out.println("Button's position is " + button.getLocation());
        System.out.println("Button's screen position is " + button.getLocationOnScreen());
        
    }

};
    