/*
 * DecksOfCards.java
 * 
 * Tayler Mauk
 * Bodey Provansal
 * Jonathan Delgado
 */

public class DecksOfCards
{

   public static void main(String[] args)
   {
      // Phase 1
      Card c1 = new Card();
      Card c2 = new Card('H', Card.Suit.CLUBS);
      Card c3 = new Card('3', Card.Suit.HEARTS);
      
      System.out.println(c1);
      System.out.println(c2);
      System.out.println(c3);

      // Phase 2
      Hand h1 = new Hand();
      while (true)
      {
         h1.takeCard(new Card('A', Card.Suit.HEARTS));
         h1.takeCard(new Card('7', Card.Suit.CLUBS));
         h1.takeCard(new Card('K', Card.Suit.CLUBS));
         h1.takeCard(new Card('7', Card.Suit.DIAMONDS));
         boolean tookLast = h1.takeCard(new Card('J', Card.Suit.SPADES));
         if (!tookLast)
         {
            break;
         }
      }

      System.out.println(h1.toString());

      System.out.println("Testing inspectCard()");
      System.out.println(h1.inspectCard(5));
      System.out.println(h1.inspectCard(500));

      while (h1.getNumCards() != 0)
      {
         System.out.println("Playing " + h1.playCard());
      }

      System.out.println(h1.toString());
   }

}

class Card
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
   
   Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   Card()
   {
      set('A', Suit.SPADES);
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

/**
 * Hand of cards.
 */
class Hand
{
   public static final int MAX_CARDS = 50;
   private Card[] myCards = new Card[MAX_CARDS];
   private int numCards;

   /**
    * Initializes hand, creates empty array of cards and sets `numCards`.
    */
   public Hand()
   {
      resetHand();
   }

   /**
    * Resets the hand by emptying the array of cards and zeroing `numCards`.
    */
   public void resetHand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   /**
    * Adds a card to the hand. We reject this with `false` if the hand is full.
    * We copy the data and cast it into a new card to prevent mutation upstream.
    */
   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS)
      {
         return false;
      }

      Card newCard = new Card(card.getValue(), card.getSuit());
      myCards[numCards++] = newCard;
      return true;
   }

   /**
    * Finds the most recent card, removed it from the hand, and returns it.
    */
   public Card playCard()
   {
      numCards--;
      Card card = myCards[numCards];
      myCards[numCards] = null;
      return card;
   }

   /**
    * Stringifies the current Hand.
    */
   public String toString()
   {
      String output = "";
      for (int i = 0; i < numCards; i++)
      {
         output += myCards[i] + " ";
      }

      return "Hand = ( " + output + ")";
   }

   /**
    * Returns the number of cards in the hand.
    */
   public int getNumCards()
   {
      return numCards;
   }

   /**
    * Returns an individual card.
    */
   public Card inspectCard(int k)
   {
      if (k >= 0 && k < MAX_CARDS)
      {
         return myCards[k];
      }

      return new Card('Z', Card.Suit.CLUBS);
   }
}
