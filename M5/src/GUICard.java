/*
 * GUICard.java
 * 
 * Tayler Mauk
 * 
 * Allows Cards to be linked to graphical elements
 */

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GUICard
{
   static boolean iconsLoaded = false;
   
   // Icons to use to display cards
   // 14 = A thru K + joker, 4 = suits
   private static Icon[][] iconCards = new Icon[14][4];
   private static Icon iconBack;
   
   // To identify card icon filenames
   private static char[] cardVals =
   {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};
   private static char[] cardSuits = {'C', 'D', 'H', 'S'};
   
   // Populate card icons if not already done
   static void loadCardIcons()
   {
      if (iconsLoaded)
         return;
      
      // Generate icon filenames and add to Icon array
      String iconFile = "";
      for (int i = 0; i < cardVals.length; ++i)
      {
         for (int j = 0; j < cardSuits.length; ++j)
         {
            iconFile = "images/" + cardVals[i] + cardSuits[j] + ".gif";
            iconCards[i][j] = new ImageIcon(iconFile);
         }
      }

      // Does not fit algorithm, so fill card back here
      iconBack = new ImageIcon("images/BK.gif");
      
      iconsLoaded = true;
   }
   
   // returns icon for specified card
   public static Icon getIcon(Card card)
   {
      return iconCards[valueAsInt(card)][suitAsInt(card)];
   }
   
   // Returns the icon for back of card
   public static Icon getBackCardIcon()
   {
      return iconBack;
   }
   
   // Returns index of card value
   private static int valueAsInt(Card card)
   {
      int i = 0;
      while (card.getValue() != cardVals[i])
         ++i;
      
      return i;
   }
   
   // Returns index of card suit
   private static int suitAsInt(Card card)
   {
      int i = 0;
      Card.Suit suit = card.getSuit();
      
      // Clubs not checked here because CLUBS index = 0
      if (suit == Card.Suit.DIAMONDS)
         i = 1;
      else if (suit == Card.Suit.HEARTS)
         i = 2;
      else if (suit == Card.Suit.SPADES)
         i = 3;
      
      return i;
   }
}
