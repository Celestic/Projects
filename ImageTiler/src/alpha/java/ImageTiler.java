package alpha.java;

import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;  

public class ImageTiler
{
	private BufferedImage image;
	
	
	public ImageTiler(BufferedImage image)
	{
		this.image = image;
	}
	
	
	public void tileImage(int rows, int cols)
	{
		int chunks = rows * cols;  
        BufferedImage imgs[] = new BufferedImage[chunks];
        
		// Determine the chunk width and height
        int chunkWidth = image.getWidth() / cols;  
        int chunkHeight = image.getHeight() / rows;  
        
        int counter = 0;

        for (int x = 0; x < rows; x++)
        {  
            for (int y = 0; y < cols; y++)
            {  
                //Initialize the image array with image chunks  
                imgs[counter] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                // draws the image chunk  
                Graphics2D gr = imgs[counter++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }
  
        // Writing chunks into image files  
        for (int i = 0; i < imgs.length; i++)
        {
			try
			{
				ImageIO.write(imgs[i], "jpg", new File("C:\\Users\\beheerder\\Desktop", "Tile_" + i + ".jpg"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}  
        }
	}
	
 }  