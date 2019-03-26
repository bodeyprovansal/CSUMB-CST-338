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
   }

}
