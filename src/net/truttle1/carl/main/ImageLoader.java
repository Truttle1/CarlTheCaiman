package net.truttle1.carl.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	BufferedImage image;
	public BufferedImage loadImage(String path)
	{
	try
	{
		image = ImageIO.read(getClass().getResource(path));
		System.out.println(path);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	return image;
	}
}
