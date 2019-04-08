import javax.swing.*;
import java.awt.*;

public class CardTable extends JFrame
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
   
   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;
   
   CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      this.setTitle(title);
      //this.setLayout(new GridLayout(numPlayers + 1, numCardsPerHand));
      JPanel bg = new JPanel();
      this.add(bg);
      pnlComputerHand = new JPanel();
      pnlPlayArea = new JPanel();
      pnlHumanHand = new JPanel();
      bg.setLayout(new GridLayout(numPlayers + 1, numCardsPerHand));
      
      pnlComputerHand.add(new JLabel("Computer Hand"));
      pnlPlayArea.add(new JLabel("Play Area"));
      pnlHumanHand.add(new JLabel("Player Hand"));
      
      bg.add(pnlComputerHand);
      bg.add(pnlPlayArea);
      bg.add(pnlHumanHand);      
   }
   
   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }
   
   public int getNumPlayers()
   {
      return numPlayers;
   }
   
}
