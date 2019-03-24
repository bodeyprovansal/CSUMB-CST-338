/* 
 * StringManipulator.java
 * 
 * Tayler Mauk
 * 
 * Takes user first and last name and performs concatenation and length
 * output. Prompts user to enter number of hours spent on school and outputs
 * the value rounded to the first decimal place.
*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class StringManipulator
{
   // Range for time spent on school work
   static final int MIN_HOURS = 12;
   static final int MAX_HOURS = 20;
   
   public static void main(String[] args)
   {
      // Provides access to user keyboard input
      Scanner consoleIn = new Scanner(System.in);
      
      // Request first name
      System.out.println("NOTE: Please capitalize the first letter");
      System.out.print("Enter your first name: ");
      String firstName = consoleIn.next();
      
      // Request last name
      System.out.println();
      System.out.println("NOTE: Please capitalize the first letter");
      System.out.print("Enter your last name: ");
      String lastName = consoleIn.next();

      // Concatenate and output information to user
      String fullName = firstName + " " + lastName;
      
      // Minus 1 to account for space
      int fullNameLength = fullName.length() - 1;
      System.out.println();
      System.out.println("Your full name is " + fullName + ", which is " +
                         fullNameLength + " characters long.");
      
      // Upper and lower case output
      System.out.println("All UPPER CASE: " + fullName.toUpperCase());
      System.out.println("All lower case: " + fullName.toLowerCase());
      
      // Request number of hours spent on school
      System.out.println();
      System.out.println("NOTE: You can include up to 3 decimal places");
      System.out.println("Between " + MIN_HOURS + " and " + MAX_HOURS +
                         " hours...");
      System.out.print("How long have you spent on school this week?: ");
      double hoursSpentOnSchool = consoleIn.nextDouble();
         
      // Output the rounded value
      // Format outputs a string of at least 1 number before and exactly 1
      // number after decimal point
      DecimalFormat decimalFormatter = new DecimalFormat("0.0");
      System.out.println("Your hours as a rounded value: " +
                         decimalFormatter.format(hoursSpentOnSchool));
      
      // Close stream
      consoleIn.close();
   }

}
