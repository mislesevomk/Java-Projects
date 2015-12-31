package assign3RoundOne;

/*
Assignment 3 Rock/Paper/Scissors SWING PGM

Submitted by: Jobin Scaria (z1262764)
              Jacob Muzikoski (z1756867)
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Assign3RoundOne extends JFrame implements ActionListener,ListSelectionListener  {
    
    private final JButton startGameBtn = new JButton("New Game");
    private final JButton go = new JButton("Go");
    
    private final DefaultListModel<String> model = new DefaultListModel<String>();
    private final JList<String> finishedGamesList = new JList<String>(model);
   
    private final JScrollPane gamesListPane = new JScrollPane(finishedGamesList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    private JTextArea roundResultsTA = new JTextArea(15,35);
    private JScrollPane outputAreaPaneScroll = new JScrollPane(roundResultsTA);
    private JTextField rockTextF = new JTextField(15);
    private JPanel rockPanel = new JPanel();
    private JTextField paperTextF = new JTextField(15);
    private JPanel paperPanel = new JPanel();
    private JTextField scissorsTextF = new JTextField(15);
    private JPanel scissorsPanel = new JPanel();
    
    private JPanel playerPanel = new JPanel();
    private JPanel computerPanel = new JPanel();
    
    private JTextField rockTextF1 = new JTextField(15);
    private JTextField paperTextF1 = new JTextField(15);
    private JTextField scissorsTextF1 = new JTextField(15);  
   
    private JPanel numOfTies = new JPanel();
    private JTextField numOfTiesTF = new JTextField(15);
    private JPanel winnerIs = new JPanel();
    private JTextField winnerIsTF = new JTextField(15);
    
    JRadioButton rockRadio = new JRadioButton("Rock");
    JRadioButton paperRadio = new JRadioButton("Paper");
    JRadioButton scissorsRadio = new JRadioButton("Scissors");

    private int usergesture=0;
    
    HashMap<String, GameStat> gameStats = new HashMap<String, GameStat>();
       
    private RPSGame currentGameRPS = null;  

    public static void main(String[] args)
    {
        createAndShowGUI();
    }

    public static void createAndShowGUI() 
    {
        Assign3RoundOne frame1 = new Assign3RoundOne();
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
        frame1.setResizable(false);
    }

    public Assign3RoundOne()
    {
        super("ROCK | PAPER | SCISSORS");
        setLayout(new BorderLayout()); 
        currentGameRPS = new RPSGame();
                
        //----------------------------------------------------------------------
        //WestPanel - Initializatons
        //----------------------------------------------------------------------
        
        //initializes WestPanel and sets the color to YELLOW
        JPanel WestPanel = new JPanel(new BorderLayout());
        WestPanel.setBackground(Color.YELLOW);
        
        //sets PANEL size for WestPanel and creates TITLE for the PANEL
        WestPanel.setPreferredSize(new Dimension(400, 40));
        WestPanel.setBorder(BorderFactory.createTitledBorder("List of Games Played"));
        
        //sets WestPanel to the left side of the FRAME
        add(WestPanel, BorderLayout.WEST);
        
        //----------------------------------------------------------------------
        //EastPanel - Initializations
        //----------------------------------------------------------------------
        
        //initializes EastPanel and sets the color to WHITE
        JPanel EastPanel = new JPanel(new BorderLayout());
        EastPanel.setBackground(Color.YELLOW);
        
        //sets PANEL size for EastPanel and creates a TITLE for the PANEL
        EastPanel.setPreferredSize(new Dimension(600, 400));
        EastPanel.setBorder(BorderFactory.createTitledBorder("Welcome to Rock | Paper | Scissors Game!"));
        EastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //sets EastPanel to the right side of the FRAME
        add(EastPanel,BorderLayout.EAST);
        
        //----------------------------------------------------------------------
        //WestPanel - GridPanels
        //----------------------------------------------------------------------
        
        //sets JList to the NORTH and adds it to the WestPanel
        WestPanel.add(gamesListPane, BorderLayout.NORTH);
        
        //2X1 GRIDPANEL subWestPanel1
        //(JList)
        //(player&computer gestureDisplay)(numberOfTies&winner display)
        JPanel subWestPanel1= new JPanel(new GridLayout(2,1));
        
        //3X4 GRIDPANEL subWestPanelA
        //BLANK     ROCK            PAPER           SCISSORS
        //PLAYER    DISPLAYBOX      DISPLAYBOX      DISPLAYBOX
        //COMPUTER  DISPLAYBOX      DISPLAYBOX      DISPLAYBOX
        JPanel subWestPanelA = new JPanel(new GridLayout(3,4));
        
        //2X2 GRIDPANEL subWestPanelB
        //NUMBER OF TIES:   DISPLAYBOX
        //WINNER:           DISPLAYBOX
        JPanel subWestPanelB = new JPanel(new GridLayout(2,2));
        
        //----------------------------------------------------------------------
        //EastPanel - GridPanels
        //----------------------------------------------------------------------
       
        //2X1 GRIDPANEL subEastPanel1
        //BUTTONS
        //DISPLAY BOX
        JPanel subEastPanel1 = new JPanel(new GridLayout(2,1));
        
        //1X4 GridLayout subEastPanel2
        //Start a New Game ROCK PAPER SCISSORS GO
        JPanel subEastPanel2 = new JPanel(new GridLayout(1,4));
        
        //adds NEW GAME button to subEastPanel3
        JPanel subEastPanel3 = new JPanel(new FlowLayout());
        subEastPanel3.add(startGameBtn);
        
        //add subEastPanel3 to subEastPanel1
        subEastPanel1.add(subEastPanel3);
        
        //adds radio buttons for possible gesture choices and a go button to submit choice to the subEastPanel2
        subEastPanel2.add(rockRadio);
        subEastPanel2.add(paperRadio);
        subEastPanel2.add(scissorsRadio);
        subEastPanel2.add(go);
        
        //adds subEastPanel2 to go to the SOUTH of subEastPanel1
        subEastPanel1.add(subEastPanel2, BorderLayout.SOUTH);
        
        //adds subEastPanel1 to the NORTH of the EastPanel, sets the background to WHITE, and makes the DISPLAY BOX not editable
        EastPanel.add(subEastPanel1, BorderLayout.NORTH);
        roundResultsTA.setBackground(Color.WHITE);
        roundResultsTA.setEditable(false);
        
        //sets size of ROUND OUTPUT DISPLAY and sets it to the SOUTH of the EastPanel
        outputAreaPaneScroll.setPreferredSize(new Dimension(400,336));
        EastPanel.add(outputAreaPaneScroll, BorderLayout.SOUTH);
        
        //----------------------------------------------------------------------
        //WestPanel - 3X4 GridPanel Labels for GAME RESULTS
        //----------------------------------------------------------------------
        
        subWestPanelA.add(new JLabel("     "));
        rockPanel.add(new JLabel("ROCK"));
        subWestPanelA.add(rockPanel);
        
        paperPanel.add(new JLabel("PAPER"));
        subWestPanelA.add(paperPanel);
        
        scissorsPanel.add(new JLabel("SCISSORS"));
        subWestPanelA.add(scissorsPanel);
        
        playerPanel.add(new JLabel("PLAYER"));
        subWestPanelA.add(playerPanel);
        
        rockTextF.setEditable(false);
        subWestPanelA.add(rockTextF);
        
        paperTextF.setEditable(false);
        subWestPanelA.add(paperTextF);
        
        scissorsTextF.setEditable(false);
        subWestPanelA.add(scissorsTextF);
        
        computerPanel.add(new JLabel("COMPUTER"));
        subWestPanelA.add(computerPanel);
        
        rockTextF1.setEditable(false);
        subWestPanelA.add(rockTextF1);
        
        paperTextF1.setEditable(false);
        subWestPanelA.add(paperTextF1);
        
        scissorsTextF1.setEditable(false);
        subWestPanelA.add(scissorsTextF1);
        
        //----------------------------------------------------------------------
        //WestPanel - 2X2 GridPanels for NUMBER OF TIES and WINNER
        //----------------------------------------------------------------------
        
        numOfTies.add(new JLabel("Number of ties : "));
        subWestPanelB.add(numOfTies);
        
        numOfTiesTF.setEditable(false);
        subWestPanelB.add(numOfTiesTF);
        
        winnerIs.add(new JLabel("Winner : "));
        subWestPanelB.add(winnerIs);
        
        winnerIsTF.setEditable(false);
        subWestPanelB.add(winnerIsTF);
        
        subWestPanel1.add(subWestPanelA);
        subWestPanel1.add(subWestPanelB);
        
        //adds subWestPanel1 to the WestPanel
        WestPanel.add(subWestPanel1);
        
        //----------------------------------------------------------------------
        //BUTTON LISTENERS
        //----------------------------------------------------------------------
        startGameBtn.addActionListener(this);
        go.addActionListener(this);
        rockRadio.addActionListener(this);
        paperRadio.addActionListener(this);
        scissorsRadio.addActionListener(this);                 
        finishedGamesList.addListSelectionListener(this);
        
        //----------------------------------------------------------------------
        //enables GO and STARTGAMEBUTTON to false
        //----------------------------------------------------------------------
        startGameBtn.setEnabled(false);
        go.setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if (e.getSource() == startGameBtn)
        {
            roundResultsTA.setText(null);
            startGameBtn.setEnabled(false);
            if(currentGameRPS != null)
            {
                //enables all BUTTONS to FALSE when first starting a game
                rockRadio.setSelected(false);    
                paperRadio.setSelected(false);
                scissorsRadio.setSelected(false);
                go.setEnabled(false);
                
                //puts a TIMESTAMP on each currentGameRPS object after NEW GAME is clicked
                String timeStamp = new SimpleDateFormat("EEE,MMM dd HH:mm:ss zzz yyyy").format(Calendar.getInstance().getTime());
                gameStats.put(timeStamp, currentGameRPS.getGameStat());
                
                //show results in the text areas
                model.addElement(timeStamp);
                //finishedGamesList.addElement(model); ????????????????????????????????????????? 
            }
            
            //news up another currentGameRPS object after the NEW GAME button is clicked
            currentGameRPS = new RPSGame();
        }
        else if (e.getSource() == rockRadio)
        {
            //sets usergesture to 1 for ROCK and enables the GO button once this radio button is selected
            usergesture = 1;
            go.setEnabled(true);

            //when ROCK radio button is selected it sets other buttons to be false
            //Only one radio button can be selected at a time
            paperRadio.setSelected(false);
            scissorsRadio.setSelected(false);
            
            //if none of the RADIO BUTTONS are selected set the GO button to FALSE
            if (rockRadio.isSelected() == false && paperRadio.isSelected() == false && scissorsRadio.isSelected() == false)
            {
                go.setEnabled(false);
            }
        }
        else if (e.getSource() == paperRadio)
        {
            //sets usergesture to 2 for PAPER and enables the GO button once this radio button is selected
            usergesture = 2;
            go.setEnabled(true);   
            
            //when PAPER radio button is selected it sets other buttons to be false
            //Only one radio button can be selected at a time
            rockRadio.setSelected(false);
            scissorsRadio.setSelected(false);
            
            //if none of the RADIO BUTTONS are selected set the GO button to FALSE
            if (rockRadio.isSelected() == false && paperRadio.isSelected() == false && scissorsRadio.isSelected() == false)
            {
                go.setEnabled(false);
            }
        }
        else if (e.getSource() == scissorsRadio)
        {
            //sets usergesture to 3 for SCISSORS and enables the GO button once this radio button is selected
            usergesture = 3;
            go.setEnabled(true);

            //when SCISSORS radio button is selected it sets other buttons to be false
            //only one radio button can be selected at a time
            rockRadio.setSelected(false);
            paperRadio.setSelected(false);
            
            //if none of the RADIO BUTTONS are selected set the GO button to FALSE
            if (rockRadio.isSelected() == false && paperRadio.isSelected() == false && scissorsRadio.isSelected() == false)
            {
                go.setEnabled(false);
            }
        }
        else if (e.getSource() == go)
        {
            //once GO has been clicked allow the user to click NEW BUTTON
            startGameBtn.setEnabled(true);
            
            //stores usergesture after one round from currentGameRPS to result
            String result = currentGameRPS.playOneRound(usergesture);
            roundResultsTA.append(result + "\n");
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e)
    {   
        //if a timeStamp is not selected or empty
        if(!finishedGamesList.isSelectionEmpty())
        {
            String  key = (String)finishedGamesList.getSelectedValue();
            GameStat gameStat = gameStats.get(key);
            
            //populates USER GESTURES in 3X4 GridPanel
            rockTextF.setText(Integer.toString(gameStat.getUserGestureCountForRock()));
            rockTextF.setFont( new Font("SansSerif", Font.BOLD, 18));
            paperTextF.setText(Integer.toString(gameStat.getUserGestureCountForPaper()));
            paperTextF.setFont( new Font("SansSerif", Font.BOLD, 18));
            scissorsTextF.setText(Integer.toString(gameStat.getUserGestureCountForScissors()));
            scissorsTextF.setFont( new Font("SansSerif", Font.BOLD, 18));
            
            //populates COMPUTER GESTURES in 3X4 GridPanel
            rockTextF1.setText(Integer.toString(gameStat.getCompGestureCountForRock()));
            rockTextF1.setFont( new Font("SansSerif", Font.BOLD, 18));
            paperTextF1.setText(Integer.toString(gameStat.getCompGestureCountForPaper()));
            paperTextF1.setFont( new Font("SansSerif", Font.BOLD, 18));
            scissorsTextF1.setText(Integer.toString(gameStat.getCompGestureCountForScissors()));
            scissorsTextF1.setFont( new Font("SansSerif", Font.BOLD, 18));
            
            //populates 2X2 GridPanel for the Game's Number of Ties and the Winner of the Game
            numOfTiesTF.setText(Integer.toString(gameStat.getNumOfTies()));
            numOfTiesTF.setFont( new Font("SansSerif", Font.BOLD, 18));
            winnerIsTF.setText((gameStat.getWinner()));
            winnerIsTF.setFont( new Font("SansSerif", Font.BOLD, 18));
        }
    }
}