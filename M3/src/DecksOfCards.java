/*
 * DecksOfCards.java
 * 
 * Jonathan Delgado
 * Tayler Mauk
 * Bodey Provansal
 * 
 * Simulation of dealing playing cards.
 */

public class DecksOfCards
{

   public static void main(String[] args)
   {
      // Phase 1
      
      System.out.println("***Card Test***");
      Card c1 = new Card();
      Card c2 = new Card('H', Card.Suit.CLUBS);
      Card c3 = new Card('3', Card.Suit.HEARTS);
      
      System.out.println(c1);
      System.out.println(c2);
      System.out.println(c3);
      
      // Phase 2
      
      System.out.println();
      System.out.println("***Hand Test***");
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
      
      // Phase 3
      
      System.out.println();
      System.out.println("***Deck Test (2 Packs)***");
      Deck d1 = new Deck(2);

      int i = 0;
      while (d1.inspectCard(i).getErrorFlag() == false)
      {
         System.out.println(i + ") " + d1.dealCard());
         ++i;
      }

      d1.init(2);
      d1.shuffle();
      System.out.println("SHUFFLE==========");
      i = 0;
      while (d1.inspectCard(i).getErrorFlag() == false)
      {
         System.out.println(i + ") " + d1.dealCard());
         ++i;
      }
      
      System.out.println();
      System.out.println("***Deck Test (1 Pack)***");
      Deck d2 = new Deck();

      i = 0;
      while (d2.inspectCard(i).getErrorFlag() == false)
      {
         System.out.println(i + ") " + d2.dealCard());
         ++i;
      }

      d2.init(1);
      d2.shuffle();
      System.out.println("SHUFFLE==========");
      i = 0;
      while (d2.inspectCard(i).getErrorFlag() == false)
      {
         System.out.println(i + ") " + d2.dealCard());
         ++i;
      }
      //Phase 4
      Scanner scan = new Scanner(System.in);
      boolean validHands = false;
      int numHands = 0;
      //Loop until valid number of players are given
      while(!validHands)
      {
         System.out.println("How many hands? (1 - 10, please): ");
         numHands = scan.nextInt();
         if((numHands > 0) & (numHands < 11))
         {
            validHands = true;
         }
      }
      //Not Shuffled Deal
      Deck d3 = new Deck(1);
      Hand[] myHands = new Hand[numHands];
      int currCard = 0;
      for(int currHand = 0; currHand < numHands; ++currHand)
      {
         myHands[currHand] = new Hand();
      }
      System.out.println("Here are our hands from an unshuffled deck: ");
      while (d3.inspectCard(currCard).getErrorFlag() == false)
      {
         for(int currHand = 0; currHand < numHands; ++currHand)
         {
            if(currCard > 51)
            {
               break;
            }
            myHands[currHand].takeCard(d3.dealCard());
            ++currCard;
         }
      }
      for(int currHand = 0; currHand < numHands; ++currHand)
      {
         System.out.println(currHand + ") " + myHands[currHand].toString());
         myHands[currHand].resetHand();
      }
      System.out.println();
      //Shuffled Deal
      Deck d4 = new Deck(1); 
      d4.shuffle();
      currCard = 0;
      System.out.println("Here are our hands from a shuffled deck: ");
      while (d4.inspectCard(currCard).getErrorFlag() == false)
      {
         for(int currHand = 0; currHand < numHands; ++currHand)
         {
            if(currCard > 51)
            {
               break;
            }
            myHands[currHand].takeCard(d4.dealCard());
            ++currCard;
         }
      }
      for(int currHand = 0; currHand < numHands; ++currHand)
      {
         System.out.println(currHand + ") " + myHands[currHand].toString());
      }
      scan.close();
   }
}
