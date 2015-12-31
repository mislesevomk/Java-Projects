//CSCI 470
//Assignment 5
//DUE DATE: 12/01/2015

package tiledesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream; 
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Jakov and Jobin
 */

public class TileDesignerLayout extends JPanel 
{
    String[] imageStringNameA = {"pat1.gif", "pat2.gif", "pat3.gif", "pat4.gif", "pat5.gif", "pat6.gif", "pat7.gif", "pat8.gif"};	
    Image[] imageA = new Image[8];	
    Playmusic p = new Playmusic();
    
    public TileDesignerLayout()
    {
        setLayout(new GridLayout(1, 2));
        
        final TileCanvas centerPanel1ptr = new TileCanvas(); 
        JPanel WestPanel = new JPanel(new BorderLayout());
        WestPanel.setBackground(Color.BLUE);
        
        WestPanel.setSize(new Dimension(600,600));
        
        add(WestPanel);
        
        WestPanel.add(centerPanel1ptr); 
        centerPanel1ptr.ResetGridTile(); 
        centerPanel1ptr.LoadImageArray();         
        centerPanel1ptr.CreateMouseListener();	
        
        //toolbar with different images (.gif) 
        JToolBar tileToolBar = new JToolBar();
        
        //BUTTON 1
        JButton patch1btn = new JButton(new ImageIcon(imageStringNameA[0]));
        tileToolBar.add(patch1btn);
        patch1btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)              
            {
                centerPanel1ptr.selectedTile = 0;                
            }
        });
        
        //BUTTON 2
        JButton patch2btn = new JButton(new ImageIcon(imageStringNameA[1]));
        tileToolBar.add(patch2btn);
        patch2btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel1ptr.selectedTile = 1;
            }
        });
        
        //BUTTON 3
        JButton patch3btn = new JButton(new ImageIcon(imageStringNameA[2]));
        tileToolBar.add(patch3btn);
        patch3btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel1ptr.selectedTile = 2;
            }
        });
        
        //BUTTON 4
        JButton patch4btn = new JButton(new ImageIcon(imageStringNameA[3]));
        tileToolBar.add(patch4btn);
        patch4btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel1ptr.selectedTile = 3;
            }
        });
        
        //BUTTON 5
        JButton patch5btn = new JButton(new ImageIcon(imageStringNameA[4]));
        tileToolBar.add(patch5btn);
        patch5btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                centerPanel1ptr.selectedTile = 4;   
            }
        });
        
        //BUTTON 6
        JButton patch6btn = new JButton(new ImageIcon(imageStringNameA[5]));
        tileToolBar.add(patch6btn);
        patch6btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)  
            {
                centerPanel1ptr.selectedTile = 5;       
            }
        });
        
        //BUTTON 7
        JButton patch7btn = new JButton(new ImageIcon(imageStringNameA[6]));
        tileToolBar.add(patch7btn);
        patch7btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)       
            {
                centerPanel1ptr.selectedTile = 6;              
            }
        });
        
        //BUTTON 8
        JButton patch8btn = new JButton(new ImageIcon(imageStringNameA[7]));
        tileToolBar.add(patch8btn);
        patch8btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)          
            {
                centerPanel1ptr.selectedTile = 7;                  
            }
        });
        
        tileToolBar.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        WestPanel.add(tileToolBar,BorderLayout.NORTH);

        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new FlowLayout());
        
        JButton resetButton  = new JButton("RESET");                     
        
        resetButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){if(e.getSource()==resetButton){centerPanel1ptr.ResetGridTile();}}});
        resetPanel.add(resetButton);
        resetPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 
        
        WestPanel.add(resetPanel, BorderLayout.SOUTH);
        
        JPanel EastPanel= new JPanel(new BorderLayout());
        
        //sets dimension for BALL panel
        EastPanel.setSize(new Dimension(350,350));
        add(EastPanel);   
        
        JPanel otherPanel = new JPanel();
        otherPanel.setLayout(new FlowLayout());
        otherPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel subEastPanel2 = new JPanel(new FlowLayout());
        
        //EAST PANEL BUTTONS
        JButton startButton = new JButton("START");
        JButton stopButton = new JButton("STOP");
        JButton musicButton = new JButton("MUSIC ON");
        JButton nomusicButton = new JButton("MUSIC OFF");
        
        //ADDS BUTTONS TO PANEL
        nomusicButton.setEnabled(false);
        otherPanel.add(startButton);
        otherPanel.add(stopButton);
        otherPanel.add(musicButton);
        otherPanel.add(nomusicButton);
        subEastPanel2.add(otherPanel);
        EastPanel.add(subEastPanel2,BorderLayout.SOUTH);
        
        final AnimationPanel animationPanel = new AnimationPanel();
        
        EastPanel.add(animationPanel);
        EastPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        
        //Actionlistener for startButton
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == startButton)
                {
                    animationPanel.start();
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                }}});
        
        //Actionlistener for stopButton
        stopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == stopButton)
                {
                    animationPanel.stop();
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                }}});
        
        //Actionlistener for music on button
        musicButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == musicButton)
                {
                    p.start();
                    musicButton.setEnabled(false);
                    nomusicButton.setEnabled(true);
                }
            }
        });
        
        //Actionlistener for music off button
        nomusicButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == nomusicButton)
                {
                    p.stop();
                    nomusicButton.setEnabled(false);
                    musicButton.setEnabled(true);
                }
            }
        });  
    }

    //class to play music
    class Playmusic 
    {
        AudioPlayer M = AudioPlayer.player;
        AudioStream G;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        
        //Constructor that looks for file in directory
        Playmusic()
        {
            try
            { 
                G = new AudioStream(new FileInputStream("bark.wav"));
                MD = G.getData();
                loop = new ContinuousAudioDataStream(MD);
            }
            catch(IOException ioexceptionerror)
            {
                System.out.println("Error");
            }
        }
        
        //once clicked, the .WAV is looped
        public void start()
        {
            M.start(loop);
        }
        
        //once clicked, the .WAV is stopped from the loop
        public void stop()
        {
            M.stop(loop);
        }       
    }
}