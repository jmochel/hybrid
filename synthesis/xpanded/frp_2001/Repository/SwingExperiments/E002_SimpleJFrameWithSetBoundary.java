package SwingExperiments;

import javax.swing.*;

public class E002_SimpleJFrameWithSetBoundary
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("E001_SimpleJFrame");

	frame.setBounds(40, 30, 250, 200);        
        frame.setVisible(true);
    }

};
    
