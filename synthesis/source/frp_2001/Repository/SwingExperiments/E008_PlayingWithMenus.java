package SwingExperiments;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class E008_PlayingWithMenus extends JFrame
{
    public E008_PlayingWithMenus(String title)
    {
        super(title);
        
        // Create a menu bar
        
        JMenuBar menuBar = new JMenuBar();
        
        this.getContentPane().add(menuBar);
        
        // Create the file menu
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        // Menu items
        
        JMenuItem newItem = new JMenuItem("New", new ImageIcon(getClass().getResource("images/T1.gif")));
        JMenuItem openItem = new JMenuItem("Open", new ImageIcon(getClass().getResource("images/T2.gif")));
        JMenuItem closeItem = new JMenuItem("Close");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        
        fileMenu.addSeparator();
        
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As ...");

        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
      
    }
   
    public static void main(String[] args)
    {
        JFrame frame = new E008_PlayingWithMenus("Testing...");
        
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