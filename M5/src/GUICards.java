/*
 * GUICards.java
 * 
 * 
 */

import javax.swing.*;
import java.awt.*;

public class GUICards
{
   // 52 cards + 4 jokers + i card back
   public static final int NUM_CARD_IMAGES = 57;
   
   public static Icon[] cardIcons = new Icon[NUM_CARD_IMAGES];
   
   public static void populateCardIcons()
   {
      // To identify card icon filenames
      char[] cardVals =
      {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};
      char[] cardSuits = {'C', 'D', 'H', 'S'};
      
      // Generate icon filenames and add to Icon array
      String iconFile = "";
      for (int i = 0; i < cardVals.length; ++i)
      {
         for (int j = 0; j < cardSuits.length; ++j)
         {
            iconFile = "images/" + cardVals[i] + cardSuits[j] + ".gif";
            cardIcons[i * cardSuits.length + j] = new ImageIcon(iconFile);
         }
      }
      
      // Does not fit algorithm, so fill card back here
      cardIcons[NUM_CARD_IMAGES - 1] = new ImageIcon("images/BK.gif");
   }
   
   public static void main(String[] args)
   {
      populateCardIcons();
      
      // Construct main window
      JFrame mainWindow = new JFrame("Card Room");
      mainWindow.setSize(1150, 650);
      mainWindow.setLocationRelativeTo(null);
      mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Construct main layout
      FlowLayout mainLayout = new FlowLayout(FlowLayout.CENTER, 5, 20);
      mainWindow.setLayout(mainLayout);
      
      // Prepare card labels
      JLabel[] cardLabels = new JLabel[NUM_CARD_IMAGES];
      for (int i = 0; i < NUM_CARD_IMAGES; ++i)
         cardLabels[i] = new JLabel(cardIcons[i]);
      
      // Add to main window
      for (int i = 0; i < NUM_CARD_IMAGES; ++i)
         mainWindow.add(cardLabels[i]);
      
      // Show main window
      mainWindow.setVisible(true);
   }
}
