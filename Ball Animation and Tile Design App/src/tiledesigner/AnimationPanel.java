//CSCI 470
//Assignment 5
//DUE DATE: 12/01/2015

package tiledesigner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import static javax.swing.text.StyleConstants.setBackground;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Jakov and Jobin
 */

/*
PROGRAMMING NOTES:
Your AnimationPanel should create several Ball objects with different colors, 
starting x and y coordinates, and dx / dy values. 
The radius for each ball may be the same, or they may be different. For example: 
ballList.add(new Ball(Color.GREEN, (d.width * 2 / 3), (d.height - 28), -2, -4,       20)); 

Due to the way the collision detection code works, starting a ball too close to the edge of the panel 
may cause it to “thrash”, “dribbling” along the edge of the panel

You may want to override the getSize() method for the AnimationPanel; the panel in the screenshot above is 350 x 350.

This subclass of JPanel will be used to display the animation in a separate background thread.
Therefore, it should implement the Runnable interface.
*/

public class AnimationPanel extends JPanel implements Runnable 
{
    //An ArrayList of Ball objects
    ArrayList<Ball> ballList = new ArrayList<Ball>();
    
    //A reference to a Dimension object that is initially set to null
    Dimension dimension = null;
    
    //A reference to a Thread object that is initially set to null
    Thread thread = null;
    
    int flag = 0;
    
    //method that creates a new thread object
    public void start() 
    {
        if(thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }
    }
    
    //method that sets the thread to null
    public void stop() 
    {
       if(thread != null)
       {
           thread = null;
       }
    }
    
    //allows us to paint the ball objects we create and make them move
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(flag == 0)
        {
            //create ball objects
            Ball ball1 = new Ball(Color.RED, 23, 45, 92, -20, 5);
            Ball ball2 = new Ball(Color.PINK, 50, 100, 78, -5, 5);
            Ball ball3 = new Ball(Color.BLUE, 36, 345, 69, 5, -8);
            Ball ball4 = new Ball(Color.YELLOW, 44, 120, 77, 5, 5);
            Ball ball5 = new Ball(Color.ORANGE, 14, 520, 50, 3, 3);
            
            //Add the ball objects to ArrayList ballList
            ballList.add(ball1);
            ballList.add(ball2);
            ballList.add(ball3);
            ballList.add(ball4);
            ballList.add(ball5);
         
            flag = 1;
        }
       
        //For each ball call the move method to change x and y co ordinate of the ball
        ballList.get(0).move();
        ballList.get(1).move();
        ballList.get(2).move();
        ballList.get(3).move();
        ballList.get(4).move();
        
        //Draw each ball to the panel from ballList
        ballList.get(0).draw(g);
        ballList.get(1).draw(g);
        ballList.get(2).draw(g);
        ballList.get(3).draw(g);
        ballList.get(4).draw(g);
    }
    
    @Override
    public void run() 
    {
        while (Thread.currentThread() == thread) 
        {
            try 
            {
                //how long the balls "sleep" before they move again in milliseconds 
                Thread.sleep(25);
            } 
            catch (InterruptedException ex) 
            {
                System.out.println("Error");
            }            
            repaint();
        }
    }   
}