package alpha.java.imagetiler;


import java.awt.image.BufferedImage;   
import java.awt.*;  


public class ImageTiler
{
	public BufferedImage[] tileImage(BufferedImage image, int rows, int cols)
	{
		int chunks = rows * cols;  
        BufferedImage[] tiledImages = new BufferedImage[chunks];
        
		// Determine the chunk width and height
        int chunkWidth = image.getWidth() / cols;  
        int chunkHeight = image.getHeight() / rows;  
        
        int counter = 0;

        for (int x = 0; x < rows; x++)
        {  
            for (int y = 0; y < cols; y++)
            {  
                //Initialize the image array with image chunks  
                tiledImages[counter] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                // draws the image chunk  
                Graphics2D gr = tiledImages[counter++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            } 
        }
        
        System.out.println("\n\n\t * Succesfully tiled the image into " + chunks + " tiles");
        
        return tiledImages;
	}

	
	public BufferedImage rebuildTiles(BufferedImage[] tiles, int rows, int cols)
	{
		BufferedImage returnImage = new BufferedImage(tiles[0].getWidth() * rows, tiles[0].getHeight() * cols, tiles[0].getType());
		Graphics2D graphics = returnImage.createGraphics();
		int counter = 0;
		
		// Determine the chunk width and height
        int chunkWidth = returnImage.getWidth() / cols;  
        int chunkHeight = returnImage.getHeight() / rows; 
		
		for (int x = 0; x < rows; x++)
		{
			for (int y = 0; y < cols; y++)
			{
                // Draws the image chunks to the main image
				graphics.drawImage(tiles[counter], y * chunkWidth, x * chunkHeight, chunkWidth, chunkHeight, null);
				
				counter++;
			}
		}
		
		return returnImage;
	}
	
 }  