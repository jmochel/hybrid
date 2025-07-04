package SwingExperiments;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class E006_PlayingWithButtonEvents implements ActionListener
{
    public E006_PlayingWithButtonEvents(Container container)
    {
        JButton    button = new JButton("Press Me");
        
        m_TextField.setEditable(false);
        
        button.requestFocus();
        button.addActionListener(this);
        button.setMnemonic('p');

        container.setLayout(new BorderLayout());
        container.add(button, "East");
        container.add(m_TextField, "Center");
    }
   
    public void actionPerformed(ActionEvent evt)
    {
        double r = Math.random();
        Date d = new Date();
        
        r *= d.getTime() - m_Date.getTime();
        
        m_TextField.setText(new Double(r).toString());
       
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Generating Random Numbers");

        E006_PlayingWithButtonEvents    playing = new E006_PlayingWithButtonEvents(frame.getContentPane());
        
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
    
    
    private JTextField    m_TextField = new JTextField(20);
    private Date          m_Date = new Date();
};
