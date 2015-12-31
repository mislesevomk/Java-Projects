//CSCI 470
//Assignment 5
//DUE DATE: 12/01/2015

package tiledesigner;

/**
 * @author Jakov & Jobin
 */

import java.awt.Color;
import java.awt.Graphics;

//This class represents a single bouncing ball in the animation
class Ball 
{
    //Ball Attributes
    Color color;        //The color of the ball   
    int r;              //The radius of the ball(integer)
    int x;              //The x coordinate of the ball’s center point(integer)
    int y;              //The y coordinate of the ball’s center point(integer)
    
    //DX
    //int dx – the amount of change in the ball’s x coordinate each time the ball moves. 
    //A negative value means the ball is currently moving to the left; a positive value means the ball is currently moving to the right. 
    int dx;
    
    //DY
    //int dy – the amount of change in the ball’s y coordinate each time the ball moves. 
    //A negative value means the ball is currently moving up; a positive value means the ball is currently moving down. 
    int dy;
    
    //Ball Constructor
    Ball(Color color, int r, int x, int y, int dx, int dy)
    {
        this.color = color;
        this.r = r;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
      
    //method that moves a ball object
    void move() 
    {
        if(x <= r || x >= (550 - r))
        {
            dx = -(dx);
        }
        
        if(y <= r || y >= (500 - r))
        {
            dy = -(dy);
        }
        
        x = x + dx;
        y = y + dy;
    }

    //method that draws a ball object by calling fillOval
    void draw(Graphics g) 
    {
      g.fillOval(x, y, r, r);
      g.setColor(color);
    }
}