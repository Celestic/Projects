package alpha.java.controllers;

import java.awt.image.BufferedImage;

import alpha.java.imagerotator.ImageRotator;


public class ImageRotatorController
{
	private BufferedImage[] imageTiles;
	
	
	public ImageRotatorController(BufferedImage[] tiles)
	{
		this.imageTiles = tiles;
	}
	
	
	public BufferedImage[] rotateTiles(int rotation90, int rotation180, int rotation270)
	{
		return new ImageRotator(imageTiles).rotateTiles(rotation90, rotation180, rotation270);
	}
}
