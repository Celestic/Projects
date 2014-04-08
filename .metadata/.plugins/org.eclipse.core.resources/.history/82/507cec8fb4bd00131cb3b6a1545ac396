package alpha.java.controllers;

import java.awt.image.BufferedImage;
import alpha.java.imagecropper.ImageCropper;


public class ImageCropperController
{
	private BufferedImage image;
	
	
	// Constructor taking the image to crop as argument
	public ImageCropperController(BufferedImage imageToCrop)
	{
		this.image = imageToCrop;
	}
	
	
	// Method that executes the cropping
	public BufferedImage cropImage()
	{
		try
		{
			ImageCropper imageCropper = new ImageCropper(image);
			BufferedImage croppedImage = imageCropper.cropImage();
			
			System.out.println("\t * The image with dimensions " + image.getWidth() + "x" + image.getHeight() + " was successfully cropped.\n\n" +
						       "\t   Printing new resulting dimensions:\n" +
							   "\t   ===============================\n" +
							   "\t   Height:\t" + croppedImage.getHeight() + "\n" +
							   "\t   Width:\t" + croppedImage.getWidth());
			
			return croppedImage;
	    }
	    catch (Exception ex)
	    {
	       ex.printStackTrace();
	    }
		
		return null;
	}
	
}
