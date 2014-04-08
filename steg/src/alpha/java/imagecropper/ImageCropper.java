package alpha.java.imagecropper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ImageCropper
{
	private BufferedImage bi;
	private Rectangle finalImage;
	
	
	public ImageCropper(BufferedImage image)
	{
		this.bi = image;
		this.setImageSize(); // Create a rectangle representing the finalImage
	}
	
	
	private void setImageSize()
	{
		int width = bi.getWidth();
		int height = bi.getHeight();
		
		if (width < height)
			this.finalImage = new Rectangle(width, width);
		else if (height < width)
			this.finalImage = new Rectangle(height, height);
		else
			this.finalImage = new Rectangle(width, height);
	}

	
	public BufferedImage cropImage()
	{
		// Intersect with the dimensions of your image
		Rectangle clip = finalImage.intersection(new Rectangle(this.bi.getWidth(), this.bi.getHeight()));
	
		// Calculate coordinates that will represent the top-left corner of the cropped image
		int a = (int) ((bi.getWidth() / 2) - (finalImage.getWidth() / 2));
		int b = (int) ((bi.getHeight() / 2) - (finalImage.getHeight() / 2));
		
		// Get the subImage using the calculated coordinates and the measurement values of clip
		BufferedImage clippedImg = bi.getSubimage(a, b, clip.width, clip.height);
		
		// Create a new BufferedImage with the size of finalImage:
		BufferedImage bi2 = new BufferedImage(finalImage.width, finalImage.height, this.bi.getType());
	
		// Fill it with white
		Graphics2D graphics = (Graphics2D) bi2.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, finalImage.width, finalImage.height);
	
		// Draw the clipped image onto it
		int x = finalImage.width - (clip.width / 2);
		int y = finalImage.height - (clip.height / 2);
		graphics.drawImage(clippedImg, x, y, null);
		
		return clippedImg;
	}
	
}
