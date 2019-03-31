/*
 * BarcodeIO.java
 * 
 * Tayler Mauk
 * 
 * Outlines basic operations for BarcodeImages
 */

public interface BarcodeIO
{
   // Stores copy of the image
   public boolean scan(BarcodeImage bc);
   
   // Stores string to eventually be encoded into BarcodeImage
   public boolean readText(String text);
   
   // Analyzes internal text to encode into BarcodeImage
   public boolean generateImageFromText();
   
   // Analyzes internal BarcodeImage to decode into text
   public boolean translateImageToText();
   
   // Print text string to console
   public void displayTextToConsole();
   
   // Print dot matrix to console
   public void displayImageToConsole();
}
