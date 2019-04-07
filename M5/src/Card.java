/*
 * Card.java
 * 
 * Tayler Mauk
 * 
 * Basic structures for a playing card.
 */

public class Card
{
   public enum Suit
   {
      CLUBS,
      DIAMONDS,
      HEARTS,
      SPADES
   };
   
   // Face value of card (2, 3, K) NOTE: T = 10
   private char value;
   private Suit suit;
   
   // True if card is in illegal state
   private boolean errorFlag;
   
   // Constructors
   
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   public Card()
   {
      this('A', Suit.SPADES);
   }
   
   // Accessors
   
   public Suit getSuit()
   {
      return suit;
   }
   
   public char getValue()
   {
      return value;
   }
   
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   // Mutators
   
   public boolean set(char value, Suit suit)
   {
      // Check for validity
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      
      // Not valid, no data is changed
      errorFlag = true;
      return false;
   }
   
   // Only returns data if card is valid
   public String toString()
   {
      if (errorFlag)
         return "[INVALID CARD]";
      return value + " of " + suit;
   }
   
   // Determines if this is equal to card based on field values
   public boolean equals(Card card)
   {
      if (card == null)
         return false;
      
      // Compare field values
      if (this.value == card.value)
      {
         if (this.suit == card.suit)
            return true;
      }
      
      return false;
   }
   
   // Determines validity of value
   private boolean isValid(char value, Suit suit)
   {
      // Acceptable card values
      char[] validCardValues =
      {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
      
      // Check if value is in validValues
      for (int i = 0; i < validCardValues.length; ++i)
      {
         if (value == (validCardValues[i]))
            return true;
      }
      
      // Not a valid value
      return false;
   }
}
