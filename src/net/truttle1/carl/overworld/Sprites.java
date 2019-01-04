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
	private static BufferedImage[][] carlSpatula;
	public static BufferedImage[] carlSpatula(boolean tie) {if(tie)return carlSpatula[0];else return carlSpatula[1];}
	private static BufferedImage[][] carlTalk;
	public static BufferedImage[] carlTalk(boolean tie) {if(tie)return carlTalk[0];else return carlTalk[1];}
	private static BufferedImage[][] carlWear;
	public static BufferedImage[] carlWear(boolean tie) {if(tie)return carlWear[0];else return carlWear[1];}
	
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
	private static BufferedImage[] platform;
	public static BufferedImage[] platform() {return platform;}
	private static BufferedImage[] cloud;
	public static BufferedImage[] cloud() {return cloud;}
	private static BufferedImage[] leaves;
	public static BufferedImage[] leaves() {return leaves;}
	private static BufferedImage[] trunk;
	public static BufferedImage[] trunk() {return trunk;}
	private static BufferedImage[] water;
	public static BufferedImage[] water() {return water;}
	private static BufferedImage[] flowingWater;
	public static BufferedImage[] flowingWater() {return flowingWater;}
	private static BufferedImage[] tiles;
	public static BufferedImage[] tiles() {return tiles;}
	private static BufferedImage[] concrete;
	public static BufferedImage[] concrete() {return concrete;}
	
	
	private static BufferedImage[] poof;
	public static BufferedImage[] poof() {return poof;}
	private static BufferedImage[] inventory;
	public static BufferedImage[] inventory() {return inventory;}
	private static BufferedImage[] inventory2;
	public static BufferedImage[] inventory2() {return inventory2;}
	private static BufferedImage[] boxClosed;
	public static BufferedImage[] boxClosed() {return boxClosed;}
	private static BufferedImage[] boxOpen;
	public static BufferedImage[] boxOpen() {return boxOpen;}
	private static BufferedImage[] spatula;
	public static BufferedImage[] spatula() {return spatula;}
	private static BufferedImage[] counter;
	public static BufferedImage[] counter() {return counter;}

	private static BufferedImage[] checkpoint;
	public static BufferedImage[] checkpoint() {return checkpoint;}
	private static BufferedImage[] palmTree;
	public static BufferedImage[] palmTree() {return palmTree;}
	private static BufferedImage[] house1;
	public static BufferedImage[] house1() {return house1;}
	private static BufferedImage[] money;
	public static BufferedImage[] money() {return money;}
	private static BufferedImage[] tie;
	public static BufferedImage[] tie() {return tie;}
	private static BufferedImage[] cIcon;
	public static BufferedImage[] cIcon() {return cIcon;}
	private static BufferedImage[] savePoint;
	public static BufferedImage[] savePoint() {return savePoint;}

	private static BufferedImage[] turtleIdle;
	public static BufferedImage[] turtleIdle() {return turtleIdle;}
	private static BufferedImage[] turtleTalk;
	public static BufferedImage[] turtleTalk() {return turtleTalk;}
	private static BufferedImage[] turtle2Idle;
	public static BufferedImage[] turtle2Idle() {return turtle2Idle;}
	private static BufferedImage[] turtle2Talk;
	public static BufferedImage[] turtle2Talk() {return turtle2Talk;}
	private static BufferedImage[] dragonIdle;
	public static BufferedImage[] dragonIdle() {return dragonIdle;}
	private static BufferedImage[] dragonTalk;
	public static BufferedImage[] dragonTalk() {return dragonTalk;}
	private static BufferedImage[] dragon2Idle;
	public static BufferedImage[] dragon2Idle() {return dragon2Idle;}
	private static BufferedImage[] dragon2Talk;
	public static BufferedImage[] dragon2Talk() {return dragon2Talk;}
	private static BufferedImage[] dragon4Idle;
	public static BufferedImage[] dragon4Idle() {return dragon4Idle;}
	private static BufferedImage[] dragon4Talk;
	public static BufferedImage[] dragon4Talk() {return dragon4Talk;}
	
	private static BufferedImage[] snakeIdle;
	public static BufferedImage[] snakeIdle() {return snakeIdle;}
	private static BufferedImage[] snakeTalk;
	public static BufferedImage[] snakeTalk() {return snakeTalk;}
	private static BufferedImage[] shop;
	public static BufferedImage[] shop() {return shop;}
	private static BufferedImage[] door;
	public static BufferedImage[] door() {return door;}

	private static BufferedImage[] coconut;
	public static BufferedImage[] coconut() {return coconut;}
	private static BufferedImage[] coconut2;
	public static BufferedImage[] coconut2() {return coconut2;}
	private static BufferedImage[] coconutHit;
	public static BufferedImage[] coconutHit() {return coconutHit;}
	private static BufferedImage[] crab;
	public static BufferedImage[] crab() {return crab;}
	private static BufferedImage[] crabJump;
	public static BufferedImage[] crabJump() {return crabJump;}
	private static BufferedImage[] fish;
	public static BufferedImage[] fish() {return fish;}
	private static BufferedImage[] spike;
	public static BufferedImage[] spike() {return spike;}
	private static BufferedImage[] slug;
	public static BufferedImage[] slug() {return slug;}
	private static BufferedImage[] tree;
	public static BufferedImage[] tree() {return tree;}
	private static BufferedImage[] treeShoot;
	public static BufferedImage[] treeShoot() {return treeShoot;}
	private static BufferedImage[] tree2;
	public static BufferedImage[] tree2() {return tree2;}
	private static BufferedImage[] treeShoot2;
	public static BufferedImage[] treeShoot2() {return treeShoot2;}
	private static BufferedImage[] treeHit2;
	public static BufferedImage[] treeHit2() {return treeHit2;}
	private static BufferedImage[] projectile;
	public static BufferedImage[] projectile() {return projectile;}
	private static BufferedImage[] bird;
	public static BufferedImage[] bird() {return bird;}
	private static BufferedImage[] bird2;
	public static BufferedImage[] bird2() {return bird2;}
	private static BufferedImage[] birdHit;
	public static BufferedImage[] birdHit() {return birdHit;}

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
			cloud = loadAnimation("/img/blocks/cloud", 1, window);
			leaves = loadAnimation("/img/blocks/leaves", 1, window);
			stone = loadAnimation("/img/blocks/stone", 1, window);
			water = loadAnimation("/img/blocks/water1", 1, window);
			flowingWater = loadAnimation("/img/blocks/flowwater", 24, window);
			poof = loadAnimation("/img/eyecandy/poof", 10, window);
			checkpoint = loadAnimation("/img/flag/flag", 2, window);
			palmTree = loadAnimation("/img/bg/palmtree", 1, window);
			house1 = loadAnimation("/img/bg/modernhouse", 1, window);
			money = loadAnimation("/img/good/money", 1, window);
			tie = loadAnimation("/img/good/tie", 1, window);
			arrowLeft = loadAnimation("/img/arrowleft/arrow", 24, window);
			arrowRight = loadAnimation("/img/arrowright/arrow", 24, window);
			cIcon = loadAnimation("/img/c_icon/c", 24, window);
			savePoint = loadAnimation("/img/blocks/savepoint", 1, window);
			platform = loadAnimation("/img/blocks/platform", 1, window);
			trunk = loadAnimation("/img/blocks/trunk", 1, window);
			tiles = loadAnimation("/img/blocks/tiles", 1, window);
			concrete = loadAnimation("/img/blocks/concrete", 1, window);

			inventory = loadAnimation("/img/eyecandy/itembox", 1, window);
			inventory2 = loadAnimation("/img/eyecandy/itembox2", 1, window);
			snakeIdle = loadAnimation("/img/snake/snake_idle", 24, window);
			snakeTalk = loadAnimation("/img/snake/snake_talk", 24, window);
			shop = loadAnimation("/img/bg/merchant", 1, window);
			door = loadAnimation("/img/bg/door", 1, window);
			counter = loadAnimation("/img/bg/counter", 1, window);
			
			carlIdle = loadCarlSprite("/img/carl/idle/idle", 24, window);
			carlRun = loadCarlSprite("/img/carl/run/run", 16, window);
			carlJump = loadCarlSprite("/img/carl/jump/jump", 2, window);
			carlSkid = loadCarlSprite("/img/carl/skid/skid", 1, window);
			carlSwim = loadCarlSprite("/img/carl/swim/swim", 12, window);
			carlBite = loadCarlSprite("/img/carl/chomp/chomp", 12, window);
			carlHit = loadCarlSprite("/img/carl/hit/hit", 6, window);
			carlDie = loadCarlSprite("/img/carl/die/die", 18, window);
			carlSpatula = loadCarlSprite("/img/carl/spatula/spatula", 18, window);
			carlTalk = loadCarlSprite("/img/carl/talk/talk", 24, window);
			carlWear = loadCarlSprite("/img/carl/weartie/wear", 48, window);

			coconut = loadAnimation("/img/enemy/coconut/coconut1", 24, window);
			coconut2 = loadAnimation("/img/enemy/coconut/coconut2", 24, window);
			coconutHit = loadAnimation("/img/enemy/coconut/coconut1_hit", 1, window);
			fish = loadAnimation("/img/enemy/fish/fish1", 12, window);
			crab = loadAnimation("/img/enemy/crab/crab1", 12, window);
			crabJump = loadAnimation("/img/enemy/crab/jump1", 2, window);
			spike = loadAnimation("/img/blocks/spikes", 1, window);
			slug = loadAnimation("/img/enemy/slug/slug", 24, window);
			tree = loadAnimation("/img/enemy/tree/tree", 24, window);
			treeShoot = loadAnimation("/img/enemy/tree/shoot", 24, window);
			tree2 = loadAnimation("/img/enemy/tree/tree2", 24, window);
			treeShoot2 = loadAnimation("/img/enemy/tree/shoot2", 24, window);
			treeHit2 = loadAnimation("/img/enemy/tree/hit2", 18, window);
			treeShoot2 = loadAnimation("/img/enemy/tree/shoot2", 24, window);
			projectile = loadAnimation("/img/enemy/tree/bullet", 1, window);
			bird = loadAnimation("/img/enemy/bird/bird1", 12, window);
			bird2 = loadAnimation("/img/enemy/bird/bird2", 12, window);
			birdHit = loadAnimation("/img/enemy/bird/hit", 12, window);

			dragonIdle = loadAnimation("/img/dragon/idle/idle", 24, window);
			dragonTalk = loadAnimation("/img/dragon/talk/talk", 24, window);
			dragon2Idle = loadAnimation("/img/dragon/idle2/idle", 24, window);
			dragon2Talk = loadAnimation("/img/dragon/talk2/talk", 24, window);
			dragon4Idle = loadAnimation("/img/dragon/idle4/idle", 24, window);
			dragon4Talk = loadAnimation("/img/dragon/talk4/talk", 24, window);
			
			turtleIdle = loadAnimation("/img/npc/turtle/idle", 24, window);
			turtle2Idle = loadAnimation("/img/npc/turtle/idle2", 24, window);
			turtleTalk = loadAnimation("/img/npc/turtle/talk", 24, window);
			turtle2Talk = loadAnimation("/img/npc/turtle/talk", 24, window);

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
