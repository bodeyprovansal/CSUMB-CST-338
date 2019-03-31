/*
 * BarcodeImage.java
 * 
 * Tayler Mauk
 * 
 * Defines the pseudo data matrix structure that will represent the optical
 * bar codes.
 */

public class BarcodeImage implements Cloneable
{
   // Exact internal dimensions of 2D data
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   
   // Data from bar code
   private boolean[][] imageData;
   
   // Constructors

   // Populate data with all false values
   public BarcodeImage()
   {
      imageData = new boolean[MAX_WIDTH][MAX_HEIGHT];
      for (int i = 0; i < MAX_WIDTH; ++i)
      {
         for (int j = 0; j < MAX_HEIGHT; ++j)
            imageData[i][j] = false;
      }
   }
   
   // Read string and convert to corresponding bar code sequences
   public BarcodeImage(String[] strData)
   {
      
   }
   
   // Accessors
   
   public boolean getPixel(int row, int col)
   {
      return false;
   }
   
   // Mutators
   
   public boolean setPixel(int row, int col, boolean value)
   {
      return false;
   }
   
   @Override
   public BarcodeImage clone()
   {
      return this;
   }
   
   // Makes sure data is not bigger than max
   private boolean checkSize(String[] data)
   {
      return false;
   }
}
