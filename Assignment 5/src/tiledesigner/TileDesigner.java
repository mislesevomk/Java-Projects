//CSCI 470
//Assignment 5
//DUE DATE: 12/01/2015

package tiledesigner;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Jakov and Jobin
 */

public class TileDesigner extends JFrame 
{
    public TileDesigner()
    {
        super("TILE DESIGNER AND BALL THREADS");
          
        TileDesignerLayout tiledesign = new TileDesignerLayout();
        setLayout(new BorderLayout());
        add(tiledesign, BorderLayout.CENTER);
    }            
    
    public static void createAndShowGUI() 
    {
        TileDesigner frame = new TileDesigner();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(1200,600));
        frame.setVisible(true);      
    }
    
    public static void main(String[] args) 
    {
        createAndShowGUI();
    }
}