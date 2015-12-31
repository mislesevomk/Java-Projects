/*
 Assignment 2 Rock/Paper/Scissors Console PGM

Submitted by: Jobin Scaria (z1262764)
              Jacob Muzikoski (z1756867)
 */

// this is RPSGame.java

package assign3RoundOne; // for netbeans

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jobin & Jacob
 */
public class RPSGame {
    
    private  String[] gestureNamesAL = {"rock", "paper", "scissors"}; // gesture names
    private ArrayList<Integer> userGestureHistoryAL = new ArrayList<Integer>(); //array list to keep track of user choices
    private GameStat stats1 = new GameStat(); // new gamestat obj.
    
    
    /*********************************************************************
    * Method:       playOneRound
    * Parameters:   int - user choice is passed in
    * Description:  This method takes in an integer (user choice) and calls
    *               the method, getComputerChoice and finally it will 
    *               calculate the winner and returns a string with the 
    *               game summary.
    ********************************************************************/
    
    String playOneRound(int  InUserChoice)
    {
        userGestureHistoryAL.add(InUserChoice);
        String userChoice;
        if(InUserChoice==1)
        {
            userChoice = gestureNamesAL[0]; //"ROCK";
        }
        else if(InUserChoice==2)
        {
            userChoice= gestureNamesAL[1]; //"PAPER";
        }
        else
        {
            userChoice= gestureNamesAL[2]; //"SCISSORS";
        }
        int compChoice=getComputerChoice();
        String y;
        if(compChoice==1)
        {
            y = gestureNamesAL[0]; //"ROCK";
        }
        else if(compChoice==2)
        {
            y = gestureNamesAL[1]; //"PAPER";
        }
        else
        {
            y = gestureNamesAL[2]; //"SCISSORS";
        }
        int result=(InUserChoice-compChoice+3)%3; // InUserChoice is what the user selected....
        String outMsg;
        int computerWin=0, userWin=0, tie=0;
        if(result==0)
        {
            outMsg="You choose "+userChoice+".\nThe computer choose " + y + ".  \nIts a tie!\n*********************************************************";
            tie=1;
        }
        else if(result==1)
        {
            outMsg="You choose "+userChoice+".\nThe computer choose " + y + ".  \nYou win! Congrats!\n*********************************************************";
            userWin=1;
        }
        else
        {
            outMsg="You choose "+userChoice+".\nThe computer choose " + y + ".  \nThe computer wins! Better luck next time!\n*********************************************************";
            computerWin=1;
        }
        stats1.update(computerWin, userWin, tie, InUserChoice, compChoice);
        return outMsg; 
    }
    
    public int funcCallCount = 0;
    
    /*********************************************************************
    * Method:       getComputerChoice
    * Parameters:   none
    * Description:  This method calculate the computers choice and returns the computer choice
    *               as 1, 2 or 3. For the first round, read in a value of 1-3 in a text file.  
    *               You create the text file.  For rounds 2-4, choose a random number between 1-3. 
    *               For the fifth choice, the computer will use the users fourth gesture that was 
    *               just chosen.
    ********************************************************************/
    int getComputerChoice()
    {
        funcCallCount++; // increment the function call variable, so we know what round we are in.
        int compChoice=0;
        if(funcCallCount==1)
        {
            //compChoice=1;            
            // read from text file
            try
            {
                File file1 = new File("firstRound.txt");
                Scanner fileScanner1 = new Scanner(file1);
                while(fileScanner1.hasNextLine())       //while not eof
                {
                    int num=fileScanner1.nextInt();
                    compChoice=num;      
                }
            }
            catch (FileNotFoundException e) // display error message if the text file is not found.
            {
                System.out.println("\nfirstRound.txt file not found...Exiting program.");
                System.exit(88);  
            }
            
        }
        else if(funcCallCount==2||funcCallCount==3||funcCallCount==4)
        {
            // random number.
            Random rand = new Random();
            compChoice = rand.nextInt(3) + 1;
        }
        else // if(funcCallCount==5 or higher)
        {
            //use the users previous gesture that was just chosen
            compChoice=userGestureHistoryAL.get(userGestureHistoryAL.size()-2);
        }
        return compChoice;
    }
    
    /*********************************************************************
    * Method:       getWinner01
    * Parameters:   none
    * Description:  This method simply returns the winner of the game.
    ********************************************************************/
    public String getWinner01()
    {
        return stats1.getWinner();
    }
    
    /*********************************************************************
    * Method:       getUserStat01
    * Parameters:   none
    * Description:  This method simply returns the number of times the
    *               user picked rock, paper and scissors.
    ********************************************************************/
    public String getUserStat01()
    { 
        return stats1.getUserGestureCounts();
    }
    
    /*********************************************************************
    * Method:       getCompStat01
    * Parameters:   none
    * Description:  This method simply returns the number of times the
    *               computer picked rock, paper and scissors.
    ********************************************************************/
    public String getCompStat01()
    { 
        return stats1.getCompGestureCounts();
    }
    
    
    /*********************************************************************
    * Method:       getUserWins01
    * Parameters:   none
    * Description:  This method simply returns the number of times the
    *               user won.
    ********************************************************************/
    public String getUserWins01()
    { 
        return stats1.getUserWins();
    }
    
    /*********************************************************************
    * Method:       getCompWins01
    * Parameters:   none
    * Description:  This method simply returns the number of times the
    *               computer won.
    ********************************************************************/
    public String getCompWins01()
    { 
        return stats1.getCompWins();
    }
    
    /*********************************************************************
    * Method:       getGameStat
    * Parameters:   none
    * Description:  This method simply returns the GameStat object stats1.
    ********************************************************************/
    public GameStat getGameStat()
    {
        return stats1;
    }
}
