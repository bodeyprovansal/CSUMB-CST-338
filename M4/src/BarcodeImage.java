/*
 * BarcodeImage.java
 * 
 * Tayler Mauk
 * Bodey Provansal
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
      if(checkSize(strData))
      {   
         imageData = new boolean[MAX_WIDTH][MAX_HEIGHT];       
         char[][] strChar = new char[MAX_WIDTH][MAX_HEIGHT];
         for (int i = 0; i < strData.length; i++)
         {
            strChar[i] = strData[i].toCharArray();
         }
         int charX = 0;
         int charY = 0;
         
         for (int y = MAX_HEIGHT - 1; y >= 0; y--)
         {
            charX = 0;
            charY = 0;
            //System.out.println("y: " + y);
            for (int x = 0; x < MAX_WIDTH - 1; x++)
            {
               //System.out.println("x: " + x);
               if (strChar[charX][charY] == ' ')
               {   
                  this.setPixel(x, y, false);
               }
               if (strChar[charX][charY] == '*')
               {   
                  this.setPixel(x, y, true);
               }
               charX++;
            }
            charY++;
         }
      } 
      else
      {
         System.out.println("Barcode Image must be under 30X65");
      }
   }
   
   // Accessors
   
   public boolean getPixel(int row, int col)
   {
      if((row < MAX_WIDTH) & (col < MAX_HEIGHT))
      {
         return imageData[row][col];
      }
      return false;
   }
   
   // Mutators
   
   public boolean setPixel(int row, int col, boolean value)
   {
      if((row < MAX_WIDTH) & (col < MAX_HEIGHT))
      {
         imageData[row][col] = value;
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
   
   public String displayToConsole(){
      String displayData = "";
      for (int i = 0; i < MAX_HEIGHT; ++i)
      {
         //System.out.print(" i: " + i + " ");
         displayData += " i: " + i + " ";
         for (int j = 0; j < MAX_WIDTH; ++j)
         {
            //System.out.print(t1.imageData[i][j]);
            displayData += " j: " + j + " ";
            displayData += imageData[i][j];            
         }
         displayData += "/n";        
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