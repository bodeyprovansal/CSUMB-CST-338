/*
 * DataMatrix.java
 * 
 * Jonathan Delgado
 * Tayler Mauk
 * 
 * Implementation of the pseudo data matrix and its operations.
 */
public class DataMatrix implements BarcodeIO
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
      return this.actualWidth;
   }

   /**
    * Gets the actual height.
    */
   public int getActualHeight()
   {
      return this.actualHeight;
   }

   /**
    * Determines the max width of the image.
    */
   private int computeSignalWidth()
   {
      int width = 0;
      while (this.image.getPixel(width, this.image.MAX_HEIGHT - 1))
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
      int height = this.image.MAX_HEIGHT - 1;
      while (this.image.getPixel(0, height))
      {
         height--;
      }
      return (this.image.MAX_HEIGHT - 1) - height;
   }

   /**
    * Generates an image from the set text.
    */
   public boolean generateImageFromText()
   {
      this.image = new BarcodeImage();

      int textLength = this.text.length();
      // Adds two for the border
      this.actualWidth = textLength + 2;
      // Eight rows plus 2 border
      this.actualHeight = 10;

      // Initial sweep to generate image
      for (int i = 1; i < textLength; i++)
      {
         int castedCharacter = (int) this.text.charAt(i);
         this.writeCharToCol(i, castedCharacter);
      }

      // Adjusted for placement of barcode in lower left corner
      int startPoint = this.image.MAX_HEIGHT - this.actualHeight;

      // Second sweep for top/bottom borders
      for (int x = 1; x < this.actualWidth - 1; x++)
      {
         this.image.setPixel(x, this.image.MAX_HEIGHT - 1, true);
         if (x % 2 == 0)
         {
            this.image.setPixel(x, startPoint, true);
         }
      }

      // Third sweep for left/right borders
      for (int y = this.image.MAX_HEIGHT - 1; y >= startPoint; --y)
      {
         image.setPixel(0, y, true);
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
      int startPoint = this.image.MAX_HEIGHT - this.actualHeight;
      for (int y = startPoint; y < this.image.MAX_HEIGHT; y++)
      {
         System.out.print("|");
         for (int x = 0; x < this.actualWidth; x++)
         {
            if (this.image.getPixel(x, y))
            {
               System.out.print(this.BLACK_CHAR);
            } else
            {
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
         for (int y = this.image.MAX_HEIGHT; y > 0; y--)
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
      if (offset == 0)
         return;

      int shiftedY;
      for (int y = offset; y >= 0; --y)
      {
         for (int x = 0; x < this.image.MAX_WIDTH; ++x)
         {
            shiftedY = (this.image.MAX_HEIGHT - 1) - (offset - y);
            this.image.setPixel(x, shiftedY, this.image.getPixel(x, y));
            this.image.setPixel(x, y, false);
         }
      }
   }

   /**
    * Shifts the entire image left `offset`.
    */
   private void shiftImageLeft(int offset)
   {
      if (offset == 0)
         return;

      for (int x = offset; x < image.MAX_WIDTH; x++)
      {
         for (int y = 0; y < image.MAX_HEIGHT; y++)
         {
            this.image.setPixel((x - offset), y, this.image.getPixel(x, y));
            this.image.setPixel(x, y, false);
         }
      }
   }

   /**
    * Read a character from a column.
    */
   private char readCharFromCol(int col)
   {
      // Adjusted value for barcode placement
      // (should now be in lower left corner)
      int placementArea = this.image.MAX_HEIGHT - this.actualHeight;

      int total = 0;
      for (int y = this.image.MAX_HEIGHT - 1; y > placementArea; --y)
      {
         if (this.image.getPixel(col, y))
         {
            total += Math.pow(2, this.image.MAX_HEIGHT - (y + 2));
         }
      }
      return (char) total;
   }

   /**
    * Writes a character to a column.
    */
   private boolean writeCharToCol(int col, int code)
   {
      // Decompose code into binary equivalents using subtraction
      // (starting with larger values)
      int row;
      int binaryDecompositionFactor = 128;
      while (code > 0)
      {
         if (code - binaryDecompositionFactor >= 0)
         {
            // Use log base 2 on code to get row number
            row = (this.image.MAX_HEIGHT - 2) - (int) (Math.log(code) / Math.log(2));
            this.image.setPixel(col, row, true);
            code -= binaryDecompositionFactor;
         }
         binaryDecompositionFactor /= 2;
      }

      return true;
   }
}