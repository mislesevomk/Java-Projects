//CSCI 470
//Assignment 5
//DUE DATE: 12/01/2015

package tiledesigner;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Jakov and Jobin
 */

public class TileCanvas extends JPanel implements MouseListener 
{    
    static final int squareSide = 25; int GridRow = 8, GridCol = 8;  
    int gridWidth,gridHeight;
    
    int[][] tilesArray = new int[GridRow][GridCol]; 
    public int selectedTile = -1;
    String[] stringImageName = {"pat1.gif","pat2.gif","pat3.gif","pat4.gif","pat5.gif","pat6.gif","pat7.gif","pat8.gif"};  
    Image[] imageA = new Image[8]; 
    Image[][] gif2dArray = new Image[GridRow][GridCol];             
    
    
    public void LoadImageArray()
    {
        for(int i = 0; i < stringImageName.length; i++)
        {
            imageA[i] = (Image) Toolkit.getDefaultToolkit().getImage(stringImageName[i]);
        }            
    }
    
    public void ResetGridTile()
    {
        for(int i = 0; i < GridRow; i++)
        {
            for(int j = 0; j < GridCol; j++)
                gif2dArray[i][j] = null;
        }
        this.repaint();         
    }  
    
    public void CreateMouseListener()
    {
        addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        int xPoint = e.getX();
        int yPoint = e.getY();
        
        gridWidth = GridCol * squareSide;
        gridHeight = GridRow * squareSide;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int startX = (panelWidth - gridWidth)/2;  
        int startY = (panelHeight - gridHeight)/2;

        if(xPoint >= startX && xPoint <= startX + gridWidth && yPoint >= startY && yPoint <= startY + gridWidth)
        {
            int xIndex = (xPoint - startX)/squareSide; 
            int yIndex = (yPoint - startY)/squareSide;
            gif2dArray[xIndex][yIndex] = imageA[selectedTile];
        }
    }
    
    @Override
    public void paintComponent(Graphics g)	
    {
        super.paintComponent(g);
        gridWidth = GridCol * squareSide;
        gridHeight = GridRow * squareSide;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int startX = (panelWidth - gridWidth)/2; 
        int startY = (panelHeight - gridHeight)/2;

        for(int i = 0; i<GridRow; i++)
        {
            for (int j = 0; j < GridCol; j++)  
            {
                g.drawRect(startX + (squareSide * i), startY + (squareSide * j), squareSide, squareSide);
            }
        }

        for(int i = 0; i < GridRow; i++)
        {
            for (int j = 0; j < GridCol; j++)  
            {
                g.drawImage(gif2dArray[i][j], startX+(squareSide * i), startY + (squareSide * j), this);
            }
        }
       this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        
    }
}