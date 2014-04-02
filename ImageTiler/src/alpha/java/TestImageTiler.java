package alpha.java;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class TestImageTiler
{
    public static void main(String[] args) throws IOException
    {
        URL url = new URL("file:\\C:\\Users\\beheerder\\Desktop\\testImage.jpg");
        BufferedImage img = ImageIO.read(url);
        
    	ImageTiler imgTiler = new ImageTiler(img);
    	imgTiler.tileImage(4, 4);
    }
}
