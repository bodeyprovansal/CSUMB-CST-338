/*
 * DataMatrix.java
 * 
 * Jonathan Delgado
 * 
 * Implementation of the pseudo data matrix and its operations.
 */

class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;

   /**
    * Default constuctor, blank initial image and text.
    */
   public DataMatrix()
   {
      this.image = new BarcodeImage();
      this.text = "";
      this.actualHeight = 0;
      this.actualWidth = 0;
   }

   /**
    * Constructor for an image.
    */
   public DataMatrix(BarcodeImage image)
   {
      this();
      this.scan(image);
   }

   /**
    * Constructor for text.
    */
   public DataMatrix(String text)
   {
      this();
      this.readText(text);
   }

   /**
    * Sets the text and width.
    */
   public boolean readText(String text)
   {
      if (text.length() > this.image.MAX_WIDTH)
      {
         return false;
      }

      this.text = text;
      this.actualWidth = text.length();
      return true;
   }

   /**
    * Sets the text and actual width/height.
    */
   public boolean scan(BarcodeImage image)
   {
      try
      {
         this.image = image.clone();
      } catch (CloneNotSupportedException exception)
      {
         return false;
      }

      this.cleanImage();
      this.actualWidth = this.computeSignalWidth();
      this.actualHeight = this.computeSignalHeight();
      return true;
   }

   /**
    * Gets the actual width.
    */
   public int getActualWidth()
   {
      return actualWidth;
   }

   /**
    * Gets the actual height.
    */
   public int getActualHeight()
   {
      return actualHeight;
   }

   /**
    * Determines the max width of the image.
    */
   private int computeSignalWidth()
   {
      int width = 0;
      while (this.image.getPixel(width, 0))
      {
         width++;
      }
      return width;
   }

   /**
    * Determines the max height of the image.
    */
   private int computeSignalHeight()
   {
      int height = 0;
      while (this.image.getPixel(0, height))
      {
         height++;
      }
      return height;
   }

   /**
    * Generates an image from the set text.
    */
   public boolean generateImageFromText()
   {
      this.image = new BarcodeImage();

      // Initial sweep to generate image
      int textLength = this.text.length();
      // Adds two for the border
      this.actualWidth = textLength + 2;
      // Eight rows plus 2 border
      this.actualHeight = 10;

      for (int i = 0; i < textLength; i++)
      {
         int castedCharacter = (int)this.text.charAt(i - 1);
         this.writeCharToCol(i, castedCharacter);
      }

      // Second sweep for top/bottom borders
      for (int x = 0; x < this.actualWidth; x++)
      {
         this.image.setPixel(this.actualHeight - 1, x, true);
         if (x % 2 == 0)
         {
            this.image.setPixel(0, x, true);
         }
      }

      // Third sweep for left/right borders
      for (int y = 0; y < this.actualHeight; y++)
      {
         image.setPixel(this.actualHeight - 1 - y, 0, true);
         if (y % 2 == 0)
         {
            image.setPixel(this.actualHeight - 1 - y, this.actualWidth - 1, true);
         }
      }

      return true;
   }

   /**
    * Converts set image into text.
    */
   public boolean translateImageToText()
   {
      this.text = "";
      // Padding on iterators for borders
      for (int x = 1; x < this.actualWidth - 1; x++)
      {
         this.text += readCharFromCol(x);
      }
      return true;
   }

   /**
    * Logs text to the console.
    */
   public void displayTextToConsole()
   {
      System.out.println(this.text);
   }

   /**
    * Prints the image to the console.
    */
   public void displayImageToConsole()
   {
      // Top border
      for (int x = 0; x < this.actualWidth + 2; x++)
      {
         System.out.print("-");
      }
      System.out.println();

      // Data
      for (int y = 0; y < this.image.MAX_HEIGHT; y++)
      {
         System.out.print("|");
         for (int x = 0; x < this.actualWidth; x++)
         {
            if (this.image.getPixel(x, y))
            {
               System.out.print(this.BLACK_CHAR);
            } else {
               System.out.print(this.WHITE_CHAR);
            }
         }
         System.out.println("|");
         System.out.println();
      }

      // Bottom border
      for (int x = 0; x < this.actualWidth + 2; x++)
      {
         System.out.print("-");
      }
      System.out.println();
   }

   /**
    * Normalizes the image and moves it to the lower left.
    */
   private void cleanImage()
   {
      this.moveImageToLowerLeft();
   }

   /**
    * Resets an image and moves it to the lower left.
    */
   private void moveImageToLowerLeft()
   {
      int bottom = 0;
      for (int x = 0; x < this.image.MAX_WIDTH; x++)
      {
         // Determine if there is anything on that line
         for (int y = 0; y < this.image.MAX_HEIGHT; y++)
         {
            if (this.image.getPixel(x, y))
            {
               bottom = y;
               break;
            }
         }
      }

      int left = 0;
      for (int x = 0; x < this.image.MAX_WIDTH; x++)
      {
         if (this.image.getPixel(x, bottom))
         {
            left = x;
            break;
         }
      }

      shiftImageDown(bottom);
      shiftImageLeft(left);
   }

   /**
    * Shifts the entire image down `offset`.
    */
   private void shiftImageDown(int offset)
   {
      for (int y = offset; y < this.image.MAX_HEIGHT; y++)
      {
         for (int x = 0; x < this.image.MAX_WIDTH; x++)
         {
            this.image.setPixel(x, (y - offset), this.image.getPixel(x, y));
         }
      }
   }

   /**
    * Shits the entire image left `offset`.
    */
   private void shiftImageLeft(int offset)
   {
      for (int x = offset; x < image.MAX_WIDTH; x++)
      {
         for (int y = 0; y < image.MAX_HEIGHT; y++)
         {
            image.setPixel((x - offset), y, this.image.getPixel(x, y));
         }
      }
   }

   /**
    * Read a character from a column.
    */
   private char readCharFromCol(int col)
   {
      int total = 0;
      for (int y = 1; y < this.actualHeight - 1; y++)
      {
         if (this.image.getPixel(col, y))
         {
            total += Math.pow(2, y - 1);
         }
      }
      return (char)total;
   }

   /**
    * Writes a character to a column.
    */
   private boolean writeCharToCol(int col, int code)
   {
      int bitwise = 0;
      for (int y = 0; y < image.MAX_HEIGHT; y++)
      {
         bitwise = (int)Math.pow(2, y);
         if (bitwise <= code)
         {
            // https://stackoverflow.com/questions/17256644/how-does-the-bitwise-and-work-in-java
            if ((code & bitwise) == bitwise)
            {
               this.image.setPixel(col, y + 1, true);
            }
         }
      }

      return true;
   }
}