package SwingExperiments;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class E010_PlayingWithPasswords extends JPanel
{
    public E010_PlayingWithPasswords()
    {
        JLabel     passwordLabel;
        JLabel     usernameLabel;
        JButton    OKButton;
        JButton    CancelButton;
        GridBagConstraints constraints;
        
        // Set the layout for this panel
        
        setLayout(new GridBagLayout());
        
        constraints = new GridBagConstraints();
        
        // Set the constraints
        
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;

        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        
        constraints.insets = new Insets(2,2,2,2);
        constraints.anchor = GridBagConstraints.EAST;
        
        // Add username Label to the username field
        
        usernameLabel = new JLabel("User Name:", SwingConstants.RIGHT);
        usernameLabel.setDisplayedMnemonic('n');
        usernameLabel.setLabelFor(m_UserNameField);
        
        add(usernameLabel, constraints);
        
        
        
    }
   
    public static void main(String[] args)
    {
        JPanel panel = new E010_PlayingWithPasswords();
    }
    
    JTextField        m_UserNameField = new JTextField(33);
    JPasswordField    m_PasswordField = new JPasswordField(35);
};