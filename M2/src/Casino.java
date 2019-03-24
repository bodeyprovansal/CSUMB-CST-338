/*
 * Casino.java
 * 
 * Tayler Mauk
 * 
 * A slot machine simulation. The main class handles logic and I/O, the
 * TripleString class stores the pull results and a record of user winnings.
 */

import java.util.*;
import java.lang.Math;

public class Casino
{
   static Scanner consoleIn = new Scanner(System.in);
   
   public static int getBet()
   {
      int userBet = -1;
      
      // Prompt until input is valid bet
      while (userBet < 0 || userBet > 100)
      {
         System.out.print("How much would you like to bet? (1 - 100) " +
                          "or 0 to quit: ");
         userBet = consoleIn.nextInt();
      }
      
      return userBet;
   }
   
   public static TripleString pull()
   {
      TripleString pullResults = new TripleString();
      
      // Populate with random results
      pullResults.setString1(randString());
      pullResults.setString2(randString());
      pullResults.setString3(randString());
      
      return pullResults;
   }
   
   // Displays pull outcome to the user
   public static void display(TripleString thePull, int winnings)
   {
      System.out.println("whirrrrrr .... and your pull is ...");
      System.out.println(thePull);
      
      // See if pull was a winner
      if (winnings > 0)
         System.out.println("congrats, you won $" + winnings);
      else
         System.out.println("sorry - you lost");
      
      System.out.println();
   }
   
   // Determines the pay multiplier by linearly checking the pull results
   public static int getPayMultiplier(TripleString thePull)
   {
      String pullString1 = thePull.getString1();
      String pullString2 = thePull.getString2();
      String pullString3 = thePull.getString3();
      
      if (pullString1.equals("cherries"))
      {
         if (pullString2.equals("cherries"))
         {
            if (pullString3.equals("cherries"))
            {
               // Result of cherries cherries cherries
               return 30;
            }
            
            // Result of cherries cherries [not cherries]
            return 15;
         }
         else
         {
            // Result of cherries [not cherries] [any]
            return 5;
         }
      }
      else if (pullString1.equals("BAR"))
      {
         if (pullString1.equals(pullString2) &&
             pullString2.equals(pullString3))
         {
            // Result of BAR BAR BAR
            return 50;
         }
      }
      else if (pullString1.equals("7"))
      {
         if (pullString1.equals(pullString2) &&
             pullString2.equals(pullString3))
         {
            // Result of 7 7 7
            return 100;
         }
      }
      
      // Not a winning combination
      return 0;
   }
   
   // Returns random string based on given probability
   private static String randString()
   {
      double randomID = Math.random();
      
      // Ranges
      // BAR = [0.0, 0.5]
      // cherries = (0.5, 0.75]
      // (space) = (0.75, 0.875]
      // 7 = (0.875, 1.0]
      if (randomID <= 0.5)
         return "BAR";
      if (randomID <= 0.75)
         return "cherries";
      if (randomID <= 0.875)
         return "(space)";
      return "7";
   }
   
   public static void main(String[] args)
   {
      int userBet = getBet();
      TripleString userPull = new TripleString();
      
      // Game logic
      while (userBet > 0)
      {
         userPull = pull();
         int userWinnings = userBet * getPayMultiplier(userPull);
         
         // Only successful assignment will continue loop
         if (userPull.saveWinnings(userWinnings))
            display(userPull, userWinnings);
         else
            break;
         
         userBet = getBet();
      }
      
      // Summary display
      System.out.println("Thanks for playing at the Casino!");
      System.out.println(userPull.displayWinnings());
   }

}

// Helper class to hold the outcomes of pulls and winnings
class TripleString
{
   // Max length for any string to be set to
   public static final int MAX_LEN = 20;
   
   public static final int MAX_PULLS = 40;
   
   private static int[] pullWinnings = new int[MAX_PULLS];
   
   // Keeps track of current pullWinnings index
   private static int numPulls = 0;
   
   // Pull outcomes
   private String string1;
   private String string2;
   private String string3;
   
   TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   // Accessors
   
   public String getString1()
   {
      return string1;
   }
   
   public String getString2()
   {
      return string2;
   }
   
   public String getString3()
   {
      return string3;
   }
   
   // Mutators
   
   public boolean setString1(String value)
   {
      // Check for valid string
      if (validString(value))
      {
         string1 = value;
         return true;
      }
      return false;
   }
   
   public boolean setString2(String value)
   {
   // Check for valid string
      if (validString(value))
      {
         string2 = value;
         return true;
      }
      return false;
   }
   
   public boolean setString3(String value)
   {
   // Check for valid string
      if (validString(value))
      {
         string3 = value;
         return true;
      }
      return false;
   }
   
   public String toString()
   {
      return string1 + " " + string2 + " " + string3;
   }
   
   public boolean saveWinnings(int winnings)
   {
      // Avoid going out of bounds
      if (numPulls < MAX_PULLS)
      {
         pullWinnings[numPulls] = winnings;
         
         // Check for successful assignment
         if (pullWinnings[numPulls] == winnings)
         {
            ++numPulls;
            return true;
         }
      }
      return false;
   }
   
   public String displayWinnings()
   {
      String message = "Your individual winnings were:\n";
      int totalWinnings = 0;
      
      // Append individual winnings and calculate total winnings
      // Use numPulls to avoid unnecessary access of later elements, such as
      // if the user quit before reaching maximum number of pulls.
      for (int i = 0; i < numPulls; ++i)
      {
         message += " " + pullWinnings[i];
         totalWinnings += pullWinnings[i];
      }
      
      // Append total winnings
      message += "\nYour total winnings were: $" + totalWinnings;
      
      return message;
   }
   
   // Returns true if str is not empty and less than MAX_LEN characters
   private boolean validString(String str)
   {
      if (!str.equals("") && str.length() <= MAX_LEN)
         return true;
      return false;
   }
}
