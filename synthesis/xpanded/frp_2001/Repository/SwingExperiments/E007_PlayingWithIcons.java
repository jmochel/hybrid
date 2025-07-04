package SwingExperiments;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class E007_PlayingWithIcons extends JPanel implements ActionListener 
{
    public E007_PlayingWithIcons(Container container)
    {
        super(true);    // Use double buffering
        
        ImageIcon    icon1 = null;
        ImageIcon    icon2 = null;
        
        icon1 = new ImageIcon(getClass().getResource("images/T1.gif"));
        icon2 = new ImageIcon(getClass().getResource("images/T2.gif"));
        
        JButton    button = new JButton("Gen", icon1);
        
        m_TextField.setEditable(false);
        
        button.setPressedIcon(icon2);
        
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

        E007_PlayingWithIcons    playing = new E007_PlayingWithIcons(frame.getContentPane());
        
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