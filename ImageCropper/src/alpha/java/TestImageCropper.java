package alpha.java;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TestImageCropper
{

	public static void main(String[] args)
	{
		 try
		 {
            URL url = new URL("file:\\C:\\Users\\beheerder\\Desktop\\testImage.jpg");
            BufferedImage img = ImageIO.read(url);
    		
            JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)), "BUFFEREDIMAGE", JOptionPane.INFORMATION_MESSAGE);
            
    		ImageCropper ic = new ImageCropper(img);
    		BufferedImage bi = ic.cropImage();
    		
    		System.out.println("Printing resulting dimensions:\n" +
    						   "===============================\n" +
    						   "Height:\t" + bi.getHeight() + "\n" +
    						   "Width:\t" + bi.getWidth());
    		
    		JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(bi)), "Yeah!", JOptionPane.INFORMATION_MESSAGE);
        }
		catch (Exception ex)
		{
	        ex.printStackTrace();
	    }
	}

}
