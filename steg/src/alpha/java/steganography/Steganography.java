package alpha.java.steganography;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Steganography {
	public Steganography() {
	}

	/**
	 * Handles the addition of text into an image
	 * 
	 * @param message
	 *            The text to hide in the image
	 * @return Returns The image with the text embedded in it
	 */
	public BufferedImage hideMessage(BufferedImage image, String message) {
		// Convert all items to byte arrays: image, message, message length
		byte img[] = getByteData(image);
		byte msg[] = message.getBytes();
		byte len[] = bitConversion(msg.length);

		try {
			encodeText(img, len, 0); // 0 first positiong
			encodeText(img, msg, 32); // 4 bytes of space for length:
										// 4bytes * 8bit = 32 bits
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Target File cannot hold message!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		return image;
	}

	/**
	 * Extracts the hidden text from an image
	 * 
	 * @param path
	 *            The path (folder) containing the image to extract the message
	 *            from
	 * @param name
	 *            The name of the image to extract the message from
	 * @param type
	 *            Integer representing either basic or advanced encoding
	 */
	public String decodeMessage(BufferedImage image) {
		byte[] decode;

		try {
			decode = decodeText(getByteData(image));

			return (new String(decode));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"There is no hidden message in this image!", "Error",
					JOptionPane.ERROR_MESSAGE);

			return "";
		}
	}

	/**
	 * Gets the byte array of an image
	 * 
	 * @param image
	 *            The image to get byte data from
	 * @return Returns the byte array of the image supplied
	 * @see Raster
	 * @see WritableRaster
	 * @see DataBufferByte
	 */
	private byte[] getByteData(BufferedImage image) {
		WritableRaster raster = image.getRaster();
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

		return buffer.getData();
	}

	/**
	 * Generates proper byte format of an integer
	 * 
	 * @param i
	 *            The integer to convert
	 * @return Returns a byte[4] array converting the supplied integer into
	 *         bytes
	 */
	private byte[] bitConversion(int i) {
		// Originally integers cast into bytes
		// byte byte7 = (byte)((i & 0xFF00000000000000L) >>> 56);
		// byte byte6 = (byte)((i & 0x00FF000000000000L) >>> 48);
		// byte byte5 = (byte)((i & 0x0000FF0000000000L) >>> 40);
		// byte byte4 = (byte)((i & 0x000000FF00000000L) >>> 32);

		// Only using 4 bytes
		byte byte3 = (byte) ((i & 0xFF000000) >>> 24); // 0
		byte byte2 = (byte) ((i & 0x00FF0000) >>> 16); // 0
		byte byte1 = (byte) ((i & 0x0000FF00) >>> 8); // 0
		byte byte0 = (byte) ((i & 0x000000FF));

		// {0,0,0,byte0} is equivalent, since all shifts >=8 will be 0

		return (new byte[] { byte3, byte2, byte1, byte0 });
	}

	/**
	 * Encode an array of bytes into another array of bytes at a supplied offset
	 * 
	 * @param Image
	 *            Array of data representing an image
	 * @param Addition
	 *            Array of data to add to the supplied image data array
	 * @param Offset
	 *            The offset into the image array to add the addition data
	 * @return Returns data Array of merged image and addition data
	 */
	private byte[] encodeText(byte[] image, byte[] addition, int offset) {
		// Check that the data + offset will fit in the image
		if (addition.length + offset > image.length) {
			throw new IllegalArgumentException("File not long enough!");
		}

		// Loop through each addition byte
		for (int i = 0; i < addition.length; ++i) {
			// Loop through the 8 bits of each byte
			int add = addition[i];

			for (int bit = 7; bit >= 0; --bit, ++offset) // ensure the new
															// offset value
															// carries on
															// through both
															// loops
			{
				// assign an integer to b, shifted by bit spaces AND 1
				// a single bit of the current byte
				int b = (add >>> bit) & 1;
				// assign the bit by taking: [(previous byte value) AND 0xfe] OR
				// bit to add
				// changes the last bit of the byte in the image to be the bit
				// of addition
				image[offset] = (byte) ((image[offset] & 0xFE) | b);
			}
		}
		return image;
	}

	/**
	 * Retrieves hidden text from an image
	 * 
	 * @param Image
	 *            Array of data, representing an image
	 * @return Array of data which contains the hidden text
	 */
	private byte[] decodeText(byte[] image) {
		int length = 0;
		int offset = 32;

		// Loop through 32 bytes of data to determine text length
		for (int i = 0; i < 32; ++i) // i=24 will also work, as only the 4th
										// byte contains real data
		{
			length = (length << 1) | (image[i] & 1);
		}

		byte[] result = new byte[length];

		// Loop through each byte of text
		for (int b = 0; b < result.length; ++b) {
			// Loop through each bit within a byte of text
			for (int i = 0; i < 8; ++i, ++offset) {
				// Assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
				result[b] = (byte) ((result[b] << 1) | (image[offset] & 1));
			}
		}

		return result;
	}

	
}