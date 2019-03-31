/*
 * DataMatrix.java
 * 
 * [Authors]
 * 
 * Implementation of the pseudo data matrix and its operations.
 */

public class DataMatrix implements BarcodeIO
{
   // Characters to display for bar code images
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ''; // FIXME: Can this be a space instead?
   
   // The optical bar code
   private BarcodeImage image;
   
   // Text to be encoded or decoded
   private String text;
   
   // Dimensions of the image field data
   private int actualWidth, actualHeight;
   
   // Constructors
   
   public DataMatrix()
   {
      
   }
   
   public DataMatrix(BarcodeImage image)
   {
      
   }
   
   public DataMatrix(String text)
   {
      
   }
   
   // Accessors
   
   public int getSignalWidth()
   {
      return actualWidth;
   }
   
   public int getSignalHeight()
   {
      return actualHeight;
   }
   
   // Uses bottom BLACK row of bar code to determine actualWidth
   private int computeSignalWidth()
   {
      return 0;
   }
   
   // Uses left column of bar code to determine actualHeight
   private int computeSignalHeight()
   {
      return 0;
   }
   
   // Aligns signal to lower-left corner of 2D array
   private void cleanImage()
   {
      
   }
   
   @Override
   public boolean scan(BarcodeImage bc)
   {
      return false;
   }

   @Override
   public boolean readText(String text)
   {
      return false;
   }

   @Override
   public boolean generateImageFromText()
   {
      return false;
   }

   @Override
   public boolean translateImageToText()
   {
      return false;
   }

   @Override
   public void displayTextToConsole()
   {
      
   }

   @Override
   public void displayImageToConsole()
   {
      
   }

}
