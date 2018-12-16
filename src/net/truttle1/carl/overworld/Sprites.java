package net.truttle1.carl.overworld;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.Global;

public class Sprites {
	private static boolean loaded = false;
	
	//SPRITE ADDING PART 1: Making the sprite exist;
	private static BufferedImage[][] carlIdle;
	public static BufferedImage[] carlIdle(boolean tie) {if(tie)return carlIdle[0];else return carlIdle[1];}
	private static BufferedImage[][] carlRun;
	public static BufferedImage[] carlRun(boolean tie) {if(tie)return carlRun[0];else return carlRun[1];}
	private static BufferedImage[][] carlJump;
	public static BufferedImage[] carlJump(boolean tie) {if(tie)return carlJump[0];else return carlJump[1];}
	private static BufferedImage[][] carlSkid;
	public static BufferedImage[] carlSkid(boolean tie) {if(tie)return carlSkid[0];else return carlSkid[1];}
	private static BufferedImage[][] carlSwim;
	public static BufferedImage[] carlSwim(boolean tie) {if(tie)return carlSwim[0];else return carlSwim[1];}
	private static BufferedImage[][] carlBite;
	public static BufferedImage[] carlBite(boolean tie) {if(tie)return carlBite[0];else return carlBite[1];}
	private static BufferedImage[][] carlHit;
	public static BufferedImage[] carlHit(boolean tie) {if(tie)return carlHit[0];else return carlHit[1];}
	private static BufferedImage[][] carlDie;
	public static BufferedImage[] carlDie(boolean tie) {if(tie)return carlDie[0];else return carlDie[1];}

	private static BufferedImage[] ground;
	public static BufferedImage[] ground() {return ground;}
	private static BufferedImage[] grass;
	public static BufferedImage[] grass() {return grass;}
	private static BufferedImage[] ground2;
	public static BufferedImage[] ground2() {return ground2;}
	private static BufferedImage[] grass2;
	public static BufferedImage[] grass2() {return grass2;}
	private static BufferedImage[] sand;
	public static BufferedImage[] sand() {return sand;}
	private static BufferedImage[] stone;
	public static BufferedImage[] stone() {return stone;}
	private static BufferedImage[] water;
	public static BufferedImage[] water() {return water;}
	private static BufferedImage[] flowingWater;
	public static BufferedImage[] flowingWater() {return flowingWater;}
	private static BufferedImage[] poof;
	public static BufferedImage[] poof() {return poof;}

	private static BufferedImage[] checkpoint;
	public static BufferedImage[] checkpoint() {return checkpoint;}
	private static BufferedImage[] palmTree;
	public static BufferedImage[] palmTree() {return palmTree;}
	private static BufferedImage[] money;
	public static BufferedImage[] money() {return money;}

	private static BufferedImage[] coconut;
	public static BufferedImage[] coconut() {return coconut;}
	private static BufferedImage[] crab;
	public static BufferedImage[] crab() {return crab;}
	private static BufferedImage[] crabJump;
	public static BufferedImage[] crabJump() {return crabJump;}
	private static BufferedImage[] fish;
	public static BufferedImage[] fish() {return fish;}

	private static BufferedImage[] arrowRight;
	public static BufferedImage[] arrowRight() {return arrowRight;}
	private static BufferedImage[] arrowLeft;
	public static BufferedImage[] arrowLeft() {return arrowLeft;}
	
	public static void loadOverworldAnimations(Game window)
	{
		try
		{
			ground = loadAnimation("/img/blocks/ground", 1, window);
			grass = loadAnimation("/img/blocks/grass", 1, window);
			ground2 = loadAnimation("/img/blocks/ground2", 1, window);
			grass2 = loadAnimation("/img/blocks/grass2", 1, window);
			sand = loadAnimation("/img/blocks/sand", 1, window);
			stone = loadAnimation("/img/blocks/stone", 1, window);
			water = loadAnimation("/img/blocks/water1", 1, window);
			flowingWater = loadAnimation("/img/blocks/flowwater", 24, window);
			coconut = loadAnimation("/img/enemy/coconut/coconut1", 24, window);
			poof = loadAnimation("/img/eyecandy/poof", 10, window);
			checkpoint = loadAnimation("/img/flag/flag", 2, window);
			fish = loadAnimation("/img/enemy/fish/fish1", 12, window);
			palmTree = loadAnimation("/img/bg/palmtree", 1, window);
			money = loadAnimation("/img/good/money", 1, window);
			arrowLeft = loadAnimation("/img/arrowleft/arrow", 24, window);
			arrowRight = loadAnimation("/img/arrowright/arrow", 24, window);
			
			carlIdle = loadCarlSprite("/img/carl/idle/idle", 24, window);
			carlRun = loadCarlSprite("/img/carl/run/run", 16, window);
			carlJump = loadCarlSprite("/img/carl/jump/jump", 2, window);
			carlSkid = loadCarlSprite("/img/carl/skid/skid", 1, window);
			carlSwim = loadCarlSprite("/img/carl/swim/swim", 12, window);
			carlBite = loadCarlSprite("/img/carl/chomp/chomp", 12, window);
			carlHit = loadCarlSprite("/img/carl/hit/hit", 6, window);
			carlDie = loadCarlSprite("/img/carl/die/die", 18, window);

		}
		catch(Exception e) {e.printStackTrace();}
	}

	public static boolean getLoaded()
	{
		return loaded;
	}

	private static BufferedImage[][] loadCarlSprite(String path, int sub, Game window)
	{
		BufferedImage[][] returnArray = new BufferedImage[2][sub];
		returnArray[0] = loadAnimation(path + "_tie", sub, window);
		returnArray[1] = loadAnimation(path + "_notie", sub, window);
		return returnArray;
	}
	public static BufferedImage[] loadAnimation(String path, int sub, Game window)
	{
		try
		{
			BufferedImage[] tempAnimation = new BufferedImage[3];
			tempAnimation[0] = window.getImageLoad().loadImage(path + "_strip.png");
			tempAnimation[1] = window.getImageLoad().loadImage(path + "_00001.png");
			tempAnimation[2] = new BufferedImage(sub,1,BufferedImage.TYPE_INT_ARGB);
			return tempAnimation;
		}
		catch(Exception e)
		{
			System.out.println("hi");
			e.printStackTrace();
			BufferedImage[] tempAnimation = new BufferedImage[sub];
			for(int i=0; i<sub; i+=Global.FRAME_PER_IMG)
			{
				System.out.println(i);
				if(i<9)
				{
					tempAnimation[i] = window.getImageLoad().loadImage(path + "_0000" + (i+1) + ".png");
				}
				else if(i<99)
				{
					tempAnimation[i] = window.getImageLoad().loadImage(path + "_000" + (i+1) + ".png");
				}
				else if(i<999)
				{
					tempAnimation[i] = window.getImageLoad().loadImage(path + "_00" + (i+1) + ".png");
				}
			}
			try {
				File f = new File("res/" + path + "_strip.png");
				f.createNewFile();
				ImageIO.write(combineImages(tempAnimation[0].getWidth(),tempAnimation[0].getHeight(),tempAnimation),"png",f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return tempAnimation;
		}
	}

	public static BufferedImage combineImages(int width, int height, BufferedImage[] iArray)
	{
		BufferedImage combined = new BufferedImage(width*iArray.length,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = combined.createGraphics();
		for(int i=0; i<iArray.length;i++)
		{
			g2d.drawImage(iArray[i], width*i, 0,null);
		}
		g2d.dispose();
		return combined;
	}
}
