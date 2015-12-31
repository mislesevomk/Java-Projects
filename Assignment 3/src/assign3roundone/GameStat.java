/*
 Assignment 2 Rock/Paper/Scissors Console PGM

Submitted by: Jobin Scaria (z1262764)
              Jacob Muzikoski (z1756867)
 */

// this is GameStat.java

package assign3RoundOne;

import java.util.HashMap;

/**
 *
 * @author Jobin & Jacob
 */

class GameStat {
    
    private int userWins, computerWins, ties;
    private HashMap<Integer, Integer> userGestureCounts = new HashMap<Integer, Integer>();
 // do same for  computerGestureCounts 
    private HashMap<Integer, Integer> computerGestureCounts = new HashMap<Integer, Integer>();

    public GameStat()      //constructor to set totals to zero
    {       
        userWins = 0;
        computerWins = 0;
        ties = 0;
     
        // Initialize user gesture counts to 0
        userGestureCounts.put(1, 0); // rock
        userGestureCounts.put(2, 0); // paper
        userGestureCounts.put(3, 0); // scissors
        
        // Initialize computer gesture counts to 0
        computerGestureCounts.put(1,0); //rock
        computerGestureCounts.put(2,0); //paper
        computerGestureCounts.put(3,0); //scissors
    }

    public void update(int computerWin, int userWin, int tie, int userGesture, int computerGesture)
    {  //here you add 1 to all totals and increment by 1 the proper entry in hashmap (rock, paper, scissors)
        //for the two hashmaps you have
        userWins+=userWin;
        computerWins+=computerWin;
        ties+=tie;
        
        if(userGesture==1)
        {
            //map.put(key, map.get(key) + 1);
            userGestureCounts.put(1,userGestureCounts.get(1)+1);
        }
        if(userGesture==2)
        {
            userGestureCounts.put(2,userGestureCounts.get(2)+1);
        }
        if(userGesture==3)
        {
            userGestureCounts.put(3,userGestureCounts.get(3)+1);
        }
        
        if(computerGesture==1)
        {
            //map.put(key, map.get(key) + 1);
            computerGestureCounts.put(1,computerGestureCounts.get(1)+1);
        }
        if(computerGesture==2)
        {
            computerGestureCounts.put(2,computerGestureCounts.get(2)+1);
        }
        if(computerGesture==3)
        {
            computerGestureCounts.put(3,computerGestureCounts.get(3)+1);
        }
    }


//You will also have get and set methods that will be called  to figure out who won the overall game for example:

/*********************************************************************
    * Method:       getWinner
    * Parameters:   [none]
    * Description:  returns a string indicating which player won based on totals
    *              
    ********************************************************************/
    public String getWinner()
    {
        if (computerWins > userWins)
        {
            return "Computer";
        }
        else if (userWins > computerWins)
        {
            return "User";
        }
        else
        {       
            return "Tie"; 
        }
    }
    
    /*********************************************************************
    * Method:       getUserGestureCounts
    * Parameters:   [none]
    * Description:  returns a string indicating the number of times the 
    *               user picked rock, paper and scissors
    *              
    ********************************************************************/
    public String getUserGestureCounts() 
    {
        return "Player total for : "+ userGestureCounts.get(1)+" Rock(s) , "+userGestureCounts.get(2)+" paper(s), "+userGestureCounts.get(3)+" scissor(s).";
    }
    
    /*********************************************************************
    * Method:       getCompGestureCounts
    * Parameters:   [none]
    * Description:  returns a string indicating the number of times the 
    *               computer picked rock, paper and scissors
    *              
    ********************************************************************/
    public String getCompGestureCounts() // 
    {
        return "Computer total for : "+ computerGestureCounts.get(1)+" Rock(s) , "+computerGestureCounts.get(2)+" paper(s), "+computerGestureCounts.get(3)+" scissor(s).";
    }
    
    /************************************************************************
    * Method:       getUserWins
    * Parameters:   [none]
    * Description:  returns a string indicating how many times the user won.
    *              
    *************************************************************************/
    public String getUserWins() // 
    {
        return "The user won "+userWins+" time(s).";
    }
    
    /************************************************************************
    * Method:       getCompWins
    * Parameters:   [none]
    * Description:  returns a string showing how many times the computer won.
    *              
    *************************************************************************/
    public String getCompWins() // 
    {
        return "The computer won "+computerWins+" time(s).";
    }
    
    public int getNumOfTies()
    {
        return ties;
    }
    
    public int getUserGestureCountForRock()
    {
        return userGestureCounts.get(1);
    }
    
    public int getUserGestureCountForPaper()
    {
        return userGestureCounts.get(2);
    }
    
    public int getUserGestureCountForScissors()
    {
        return userGestureCounts.get(3);
    }
    
    public int getCompGestureCountForScissors()
    {
        return computerGestureCounts.get(3);
    }
    
    public int getCompGestureCountForPaper()
    {
        return computerGestureCounts.get(2);
    }
    
    public int getCompGestureCountForRock()
    {
        return computerGestureCounts.get(1);
    }
}