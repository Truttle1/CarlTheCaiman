package net.truttle1.carl.main;

import java.awt.Color;
import java.awt.Graphics;

public class Fade {

	private static int fadeNum = 0;
	private static boolean fadingIn = true;
	private static boolean running = false;
	public static void render(Graphics g)
	{
		if(fadeNum>=255)fadeNum = 255;
		if(fadeNum<=0)fadeNum = 0;
		g.setColor(new Color(0,0,0,fadeNum));
		g.fillRect(0, 0, 2000, 1000);
	}
	public static void start()
	{
		running = true;
	}
	public static boolean getRunning()
	{
		return running;
	}
	public static boolean getFadingIn()
	{
		return fadingIn;
	}
	public static void tick()
	{
		if(running)
		{
			if(fadingIn)
			{
				fadeNum += 20;
			}
			else
			{
				fadeNum -= 20;
			}
			if(fadeNum>=255)
			{
				fadingIn = false;
			}
			if(fadeNum<0)
			{
				running = false;
				fadeNum = 0;
				fadingIn = true;
			}
		}
	}
}
