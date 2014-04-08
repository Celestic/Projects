package alpha.java.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import alpha.java.commonUtilities.CommonUtilities;


public class MainController
{
	private CLIController cliController;
	private SteganographyController steganographyController;
	private ImageTilerController imageTilerController;
	private ImageRotatorController imageRotatorController;
	private BufferedImage inputImage;
	private BufferedImage[] tiledImages;
	private File fileToEmbed;
	private File outputDirectory;
	private int tileRows, tileCols, rotation90, rotation180, rotation270;
	
	

	// APACHE COMMAND LINE CONTROLLER
	public boolean processCommand(String[] arguments)
	{
		cliController = new CLIController(arguments);
		cliController.initiateCommandLineOptions();
		cliController.processCommandLine();
		
		File[] paths = cliController.extractArgumentPaths();
		int[] parameters = cliController.extractParameters();
		
		if (paths != null)
		{
			setFiles(paths); // inputFile - fileToEmbed - outputDirectory
			setParameters(parameters);
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Arguments could not be processed correctly, please make sure you entered all of the needed values.",
									      "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	
	// Uses the extracted arguments to set the image & paths & parameters
	private void setFiles(File[] paths)
	{
		String imagePath = paths[0].getAbsolutePath().replace("\\", "/");
		
		try
		{
			inputImage = ImageIO.read(new URL("file:///" + imagePath));
			fileToEmbed = paths[1];
			outputDirectory = paths[2];
		}
		catch (IIOException e)
		{
			e.printStackTrace();
			System.out.println("An IOException occurred while reading in the input Image.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("An IOException occurred while reading in the input Image.");
		}
	}
	
	
	// Uses the extracted parameters to set the tiles and rotation values
	private void setParameters(int[] parameters)
	{
		tileRows = parameters[0];
		tileCols = parameters[1];
		rotation90 = parameters[2];
		rotation180 = parameters[3];
		rotation270 = parameters[4];
	}
	
	
	// Main execution of the program
	public void executeProgram()
	{
		if (inputImage.getWidth() != inputImage.getHeight())
			inputImage = new ImageCropperController(inputImage).cropImage();
		
		if (tileRows != 0 && tileCols != 0)
		{
			imageTilerController = new ImageTilerController();
			tiledImages = imageTilerController.tileImage(inputImage, tileRows, tileCols);
			
			if (rotation90 != 0 || rotation180 != 0 || rotation270 != 0)
			{
				imageRotatorController = new ImageRotatorController(tiledImages);
				tiledImages = imageRotatorController.rotateTiles(rotation90, rotation180, rotation270);
				//testOutputImages(tiledImages);
			}
			
			BufferedImage test = imageTilerController.rebuildTiles(tiledImages, tileRows, tileCols);
			
			try
			{
				ImageIO.write(test, "jpg", new File(CommonUtilities.PATH_TO_TILED_IMAGES, "JAAAAA.jpg"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				System.out.println("Exception caught with message: " + e.getMessage());
			}
		}
		
		// STEGANOGRAPHY
		steganographyController = new SteganographyController();
		BufferedImage encryptedImage = steganographyController.hideMessage(inputImage, "KAKKERLAKKEN");
		System.out.println("\n\n\t * Successfully hidden the message into the image");
		
		System.out.println("\t * Attempting to decode the message");
		String hiddenMessage = steganographyController.decodeMessage(encryptedImage);
		
		try
		{
			ImageIO.write(encryptedImage, "jpg", new File(CommonUtilities.PATH_TO_TILED_IMAGES, "encryption_test.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Exception caught with message: " + e.getMessage());
		}
		
		if (hiddenMessage.length() > 0)
			System.out.println("\t * Successfully decoded the hidden message: " + hiddenMessage);
		else
			System.out.println("\t * No hidden message was found in the image");
		
	}
	
	
	private void testOutputImages(BufferedImage[] rotatedTiles)
	{
        // Writing chunks into image files  
	      for (int i = 0; i < rotatedTiles.length; i++)
	      {
			try
			{
				ImageIO.write(rotatedTiles[i], "jpg", new File(CommonUtilities.PATH_TO_TILED_IMAGES, "Tile_" + i + ".jpg"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				System.out.println("Exception caught with message: " + e.getMessage());
			}
	      }
	}
}
