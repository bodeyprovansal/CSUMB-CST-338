/*
 * BarcodeImage.java
 * 
 * Jonathan Delgado
 * Tayler Mauk
 * Bodey Provansal
 * 
 * Defines the pseudo data matrix structure that will represent the optical bar
 * codes.
 */
public class BarcodeImage implements Cloneable
{
   // Exact internal dimensions of 2D data
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;

   // Data from bar code
   private boolean[][] imageData = new boolean[MAX_WIDTH][MAX_HEIGHT];

   // Constructors
   public BarcodeImage()
   {
      for (int x = 0; x < MAX_WIDTH; x++)
      {
         for (int y = 0; y < MAX_HEIGHT; y++)
         {
            setPixel(x, y, false);
         }
      }
   }

   // Read string and convert to corresponding bar code sequences
   public BarcodeImage(String[] strData)
   {
      this();

      if (checkSize(strData))
      {
         imageData = new boolean[MAX_WIDTH][MAX_HEIGHT];

         // Iterate through each char of image data
         for (int row = 0; row < strData.length; ++row)
         {
            for (int col = 0; col < strData[row].length(); ++col)
            {
               if (strData[row].charAt(col) == DataMatrix.BLACK_CHAR)
                  setPixel(col, row, true);
               else
                  setPixel(col, row, false);
            }
         }
      } else
      {
         System.out.println("Barcode Image must be under 30X65");
      }
   }

   // Accessors

   public boolean getPixel(int col, int row)
   {
      if ((row >= 0 && row < MAX_HEIGHT) && (col >= 0 && col < MAX_WIDTH))
      {
         return imageData[col][row];
      }
      return false;
   }

   // Mutators

   public boolean setPixel(int col, int row, boolean value)
   {
      if ((row >= 0 && row < MAX_HEIGHT) && (col >= 0 && col < MAX_WIDTH))
      {
         imageData[col][row] = value;
         return true;
      }
      return false;
   }

   @Override
   public BarcodeImage clone() throws CloneNotSupportedException
   {
      BarcodeImage cloneObject = new BarcodeImage();

      // Duplicate filed data
      for (int i = 0; i < MAX_WIDTH; ++i)
      {
         for (int j = 0; j < MAX_HEIGHT; ++j)
            cloneObject.imageData[i][j] = this.imageData[i][j];
      }

      return cloneObject;
   }

   public String displayToConsole()
   {
      String displayData = "";
      for (int i = 0; i < MAX_WIDTH; ++i)
      {
         // System.out.print(" i: " + i + " ");
         displayData += " i: " + i + " ";
         for (int j = 0; j < MAX_HEIGHT; ++j)
         {
            // System.out.print(t1.imageData[i][j]);
            // displayData += " j: " + j + " ";
            displayData += "\t[" + i + "]" + "[" + j + "]:" +
                           imageData[i][j] + "\n";
         }
         displayData += "\n";
      }
      return displayData;
   }

   // Makes sure data is not bigger than max
   private boolean checkSize(String[] data)
   {
      if (data.length < MAX_HEIGHT)
      {
         int widthCount = 0;
         char[][] dataChar = new char[data.length][];
         for (int i = 0; i < data.length; ++i)
         {
            widthCount = 0;
            dataChar[i] = data[i].toCharArray();
            for (int j = 0; j < dataChar[i].length; ++j)
            {
               widthCount++;
               if (widthCount >= MAX_WIDTH)
               {
                  return false;
               }
            }
         }
         return true;
      }
      return false;
   }
}