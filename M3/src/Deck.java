/*
 * Deck.java
 * 
 * Tayler Mauk
 * 
 * Provides structure for the workings of a deck of cards as they would be
 * used in a card game. The cards currently in the deck are represented by any
 * array element whose index is greater than or equal to topCard.
 */

import java.util.Random;

public class Deck
{
   // Maximum number of allowed cards (currently set to 6 decks of 52 cards)
   public static final int MAX_CARDS = 6 * 52;
   
   // Number of cards in a standard deck
   private static final int STD_DECK_SIZE = 52;
   
   // Contains the standard 52 cards of a deck
   private static Card[] masterPack;
   
   // Information about the current deck
   private Card[] cards;
   private int topCard;
   
   // Constructors
   
   public Deck(int numPacks)
   {
      allocateMasterPack();
      
      // Default to maximum number of packs if numPacks > maxPacks
      int maxPacks = MAX_CARDS / STD_DECK_SIZE;
      if (numPacks > maxPacks)
         numPacks = maxPacks;
      
      int numCards = numPacks * STD_DECK_SIZE;
      cards = new Card[numCards];
      
      // Populate cards
      init(numPacks);
   }
   
   public Deck()
   {
      // 1 pack is assumed
      this(1);
   }
   
   // Accessors
   
   public int getTopCard()
   {
      return topCard;
   }
   
   public Card inspectCard(int k)
   {
      // Check if k is a valid card index
      if (k < topCard || k >= cards.length)
      {
         // Return card with error
         return new Card('Z', Card.Suit.CLUBS);
      }
      return cards[k];
   }
   
   // Populate and initialize cards array
   public void init(int numPacks)
   {
      // For every pack in numPacks, add a deck's worth of cards using the
      // masterPack
      for (int i = 0; i < numPacks; ++i)
      {
         for (int j = 0; j < masterPack.length; ++j)
         {
            cards[(STD_DECK_SIZE * i) + j] = masterPack[j];
         }
      }
      
      // Reset top card position when initializing deck
      topCard = 0;
   }
   
   // Linearly traverses the cards and swaps the current element with another
   // random element between topCard and the end of the array.
   public void shuffle()
   {
      // Random number generator
      Random rand = new Random();
      
      // Index to swap with
      int destinationIndex = 0;
      
      // Random number generator range info
      int min = topCard, max = cards.length - 1;
      
      // Temporary swap location
      Card tempCard;
      
      for (int i = topCard; i < cards.length; ++i)
      {
         // Get random index in range
         destinationIndex = rand.nextInt((max - min) + 1) + min;
         
         // Assign card at destination index to temp and perform swap
         tempCard = cards[destinationIndex];
         cards[destinationIndex] = cards[i];
         cards[i] = tempCard;
      }
   }
   
   // Returns top card
   public Card dealCard()
   {
      // Increment topCard and return Card object
      int topPosition = topCard;
      topCard += 1;
      return cards[topPosition];
   }
   
   // Populates master pack if not already done
   private static void allocateMasterPack()
   {
      // Populate if master pack is empty
      if (masterPack == null)
      {
         // Acceptable card values (can I expose this from Card class?)
         char[] cardValues =
         {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
         
         // Populate
         masterPack = new Card[STD_DECK_SIZE];
         int packIndex = 0;
         for (int i = 0; i < cardValues.length; ++i)
         {
            packIndex = i * 4;
            masterPack[packIndex] = new Card(cardValues[i], Card.Suit.CLUBS);
            masterPack[packIndex + 1] =
               new Card(cardValues[i], Card.Suit.DIAMONDS);
            masterPack[packIndex + 2] =
               new Card(cardValues[i], Card.Suit.HEARTS);
            masterPack[packIndex + 3] =
               new Card(cardValues[i], Card.Suit.SPADES);
         }
      }
   }
}
