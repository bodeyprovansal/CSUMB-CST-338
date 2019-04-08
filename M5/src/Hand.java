public class Hand {
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
   public void sort(){
      Card.arraySort(myCards, myCards.length);
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
   public Card playCard(int cardIndex)
   {
      if ( numCards == 0 ) //error
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }
      //Decreases numCards.
      Card card = myCards[cardIndex];
      
      numCards--;
      for(int i = cardIndex; i < numCards; i++)
      {
         myCards[i] = myCards[i+1];
      }
      
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
