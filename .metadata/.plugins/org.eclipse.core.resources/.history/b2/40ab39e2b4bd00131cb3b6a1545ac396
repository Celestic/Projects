package alpha.java.controllers;

import java.awt.image.BufferedImage;

import alpha.java.steganography.Steganography;

public class SteganographyController
{
	private Steganography steganography;
	
	
	public SteganographyController()
	{
		steganography = new Steganography();
	}
	
	
	public BufferedImage hideMessage(BufferedImage image, String message)
	{
		return steganography.hideMessage(image, message);
	}
	
	
	public String decodeMessage(BufferedImage image)
	{
		return steganography.decodeMessage(image);
	}
}
