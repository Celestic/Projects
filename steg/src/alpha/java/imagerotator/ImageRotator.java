package alpha.java.imagerotator;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class ImageRotator
{
	private BufferedImage[] tiledImages;
	
	
	public ImageRotator(BufferedImage[] tiles)
	{
		this.tiledImages = tiles;
	}
	
	
	public BufferedImage[] rotateTiles(int rotation90, int rotation180, int rotation270)
	{
		Random random = new Random();
		int randomIndex;
		ArrayList<Integer> usedRandoms = new ArrayList<Integer>();
		float amountOfTiles = tiledImages.length;
		int amountToRotate;
		AffineTransform affineTransformer = new AffineTransform();
		AffineTransformOp affineTransformOp;
		
		// ROTATION 90
		if (rotation90 > 0)
		{
			amountToRotate = (int) Math.round((amountOfTiles / 100) * rotation90);
			System.out.println("\n\n\t * Starting with 90° rotation of " + amountToRotate + " tiles");
			
			for (int i = 0; i < amountToRotate; i++)
			{
				do
				{
					randomIndex = random.nextInt((int) amountOfTiles);
				}
				while (isDuplicate(usedRandoms, randomIndex));
				
				usedRandoms.add(randomIndex);
				
			    affineTransformer.rotate(Math.PI / 2, tiledImages[randomIndex].getWidth() / 2, tiledImages[randomIndex].getHeight() / 2);
			    affineTransformOp = new AffineTransformOp(affineTransformer, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			    tiledImages[randomIndex] = affineTransformOp.filter(tiledImages[randomIndex], null);
				
				System.out.println("\t * Successfully rotated random tile with index " + randomIndex + " at an angle of 90°");
			}
		}
		// ROTATION 180
		if (rotation180 > 0)
		{
			amountToRotate = (int) Math.round((amountOfTiles / 100) * rotation180);
			System.out.println("\n\n\t * Starting with 180° rotation of " + amountToRotate + " tiles");
			
			for (int i = 0; i < amountToRotate; i++)
			{
				do
				{
					randomIndex = random.nextInt((int) amountOfTiles);
				}
				while (isDuplicate(usedRandoms, randomIndex));
				
				usedRandoms.add(randomIndex);
				
			    affineTransformer.rotate(Math.PI, tiledImages[randomIndex].getWidth() / 2, tiledImages[randomIndex].getHeight() / 2);
			    affineTransformOp = new AffineTransformOp(affineTransformer, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			    tiledImages[randomIndex] = affineTransformOp.filter(tiledImages[randomIndex], null);
			    
				System.out.println("\t * Successfully rotated random tile with index " + randomIndex + " at an angle of 180°");
			}
		}
		// ROTATION 270
		if (rotation270 > 0)
		{
			amountToRotate = (int) Math.round((amountOfTiles / 100) * rotation270);
			System.out.println("\n\n\t * Starting with 270° rotation of " + amountToRotate + " tiles");
			
			for (int i = 0; i < amountToRotate; i++)
			{
				do
				{
					randomIndex = random.nextInt((int) amountOfTiles);
				}
				while (isDuplicate(usedRandoms, randomIndex));
				
				usedRandoms.add(randomIndex);

			    affineTransformer.rotate(Math.PI * 3 / 2, tiledImages[randomIndex].getWidth() / 2, tiledImages[randomIndex].getHeight() / 2);
			    affineTransformOp = new AffineTransformOp(affineTransformer, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			    tiledImages[randomIndex] = affineTransformOp.filter(tiledImages[randomIndex], null);
			    
				System.out.println("\t * Successfully rotated random tile with index " + randomIndex + " at an angle of 270°");
			}
		}
		
		return tiledImages;
	}


	private boolean isDuplicate(ArrayList<Integer> usedRandoms, int randomIndex)
	{
		for (int i : usedRandoms)
		{
			if (i == randomIndex)
				return true;
		}
		
		return false;
	}
	
}
