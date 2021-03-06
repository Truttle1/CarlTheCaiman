package net.truttle1.carl.overworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;

import net.truttle1.carl.main.AudioHandler;
import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameMode;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.main.Theme;
import net.truttle1.carl.main.Global;

public final class OverworldMode extends GameMode
{
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private int tx;
	private int ty;
	private int currentRoom;
	//Beach World
	public static Room beach1;
	public static Room beach2;
	public static Room beach3;
	public static Room beach4;
	public static Room beach5;
	public static Room beach6;
	public static Room beach7;
	public static Room turtleIsland;
	
	//Forest World
	public static Room forest1;
	public static Room forest2;
	public static Room forest3;
	public static Room forest4;
	public static Room drakon;
	public static Room store1;
	public static Room store2;
	
	private BufferedImage background;
	private BufferedImage tropicalBackground;
	private BufferedImage forestBackground;
	private BufferedImage storeBackground;
	public OverworldMode(Game window) 
	{
		super(window);
		tropicalBackground = window.getImageLoad().loadImage("/img/bg/tropical_00001.png");
		forestBackground = window.getImageLoad().loadImage("/img/bg/forest_00001.png");
		storeBackground = window.getImageLoad().loadImage("/img/bg/store_00001.png");

		BufferedImage b = window.getImageLoad().loadImage("/room/beach1.png");
		BufferedImage b2 = window.getImageLoad().loadImage("/room/beach2.png");
		BufferedImage b3 = window.getImageLoad().loadImage("/room/beach3.png");
		BufferedImage b4 = window.getImageLoad().loadImage("/room/beach4.png");
		BufferedImage b5 = window.getImageLoad().loadImage("/room/beach5.png");
		BufferedImage b6 = window.getImageLoad().loadImage("/room/beach6.png");
		BufferedImage b7 = window.getImageLoad().loadImage("/room/beach7.png");
		BufferedImage ti = window.getImageLoad().loadImage("/room/turtleisland1.png");
		BufferedImage dk = window.getImageLoad().loadImage("/room/drakon1.png");
		BufferedImage f1 = window.getImageLoad().loadImage("/room/forest1.png");
		BufferedImage f2 = window.getImageLoad().loadImage("/room/forest2.png");
		BufferedImage f3 = window.getImageLoad().loadImage("/room/forest3.png");
		BufferedImage f4 = window.getImageLoad().loadImage("/room/forest4.png");
		
		BufferedImage st = window.getImageLoad().loadImage("/room/store.png");
		beach1 = new Room(b,this,false,null,Theme.Tropical,0,AudioHandler.TROPICAL_THEME);
		beach2 = new Room(b2,this,false,null,Theme.Tropical,1,AudioHandler.TROPICAL_THEME);
		beach3 = new Room(b3,this,false,null,Theme.Tropical,2,AudioHandler.TROPICAL_THEME);
		turtleIsland = new Room(ti,this,false,null,Theme.Tropical,3,AudioHandler.TROPICAL_THEME);
		beach4 = new Room(b4,this,false,null,Theme.Tropical,4,AudioHandler.TROPICAL_THEME);
		beach5 = new Room(b5,this,false,null,Theme.Tropical,5,AudioHandler.TROPICAL_THEME);
		beach6 = new Room(b6,this,false,null,Theme.Tropical,6,AudioHandler.TROPICAL_THEME);
		beach7 = new Room(b7,this,false,null,Theme.Tropical,7,AudioHandler.TROPICAL_THEME);
		drakon = new Room(dk,this,false,null,Theme.Forest,8,AudioHandler.FOREST_THEME);
		forest1 = new Room(f1,this,false,null,Theme.Forest,9,AudioHandler.FOREST_THEME);
		forest2 = new Room(f2,this,false,null,Theme.Forest,10,AudioHandler.FOREST_THEME);
		forest3 = new Room(f3,this,false,null,Theme.Forest,13,AudioHandler.FOREST_THEME);
		forest4 = new Room(f4,this,false,null,Theme.Forest,14,AudioHandler.FOREST_THEME);

		store1 = new Room(st,this,false,null,Theme.Store,11,AudioHandler.FOREST_THEME);
		store2 = new Room(st,this,false,null,Theme.Store,12,AudioHandler.FOREST_THEME);
		try
		{
			window.load();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			beach1.loadStage();
			beach1.addPlayer(200, 2500);
		}
		ty = 2500;
		Global.checkpointRoom = beach1;
		Global.checkpointRoomId = beach1.getId();
	}

	@Override
	public void tick() 
	{
		for(int i=0; i<objects.size();i++)
		{
			objects.get(i).tick();
		}
		if(Global.currentRoom.getTheme() == Theme.Tropical)
		{
			background = tropicalBackground;
		}
		if(Global.currentRoom.getTheme() == Theme.Forest)
		{
			background = forestBackground;
		}
		if(Global.currentRoom.getTheme() == Theme.Store)
		{
			background = storeBackground;
		}
	}
	//getTx and getTy return the location of the viewport.
	public int getTx()
	{
		return tx;
	}
	public int getTy()
	{
		return ty;
	}
	public void setTx(int x)
	{
		tx = x;
	}
	public void setTy(int y)
	{
		ty = y;
	}
	@Override
	public void render(Graphics g) 
	{
		if(Global.currentRoom.getTheme() == Theme.Tropical)
		{
			g.setColor(new Color(0x8CFFFF));
			g.fillRect(0,0,1500,640);
			g.setColor(new Color(0x38ACFF));
			g.translate(0,-ty);
			g.fillRect(0,Global.currentRoom.getHeight()-200,1500,450);
			g.translate(0,ty);
			
		}
		if(Global.currentRoom.getTheme() == Theme.Forest)
		{
			g.setColor(new Color(0xC1FFE7));
			g.fillRect(0,0,1500,640);
			g.setColor(new Color(0x239E46));
			g.translate(0,-ty);
			g.fillRect(0,Global.currentRoom.getHeight()-200,1500,450);
			g.translate(0,ty);
			
		}
		if(Global.currentRoom.getTheme() == Theme.Store)
		{
			g.setColor(new Color(0xF7F7F7));
			g.fillRect(0,0,1500,640);
			g.setColor(new Color(0xF7F7F7));
			g.translate(0,-ty);
			g.fillRect(0,Global.currentRoom.getHeight()-200,1500,450);
			g.translate(0,ty);
			
		}
		Graphics2D g2d = (Graphics2D) g;			
		if(Global.currentRoom.getTheme() == Theme.Store)
		{
			g.drawImage(background,0,Global.currentRoom.getHeight()-700,null);
			g.translate(-tx, -ty);
		}
		else
		{
			g.translate((int) ((-0.5*(tx))%1138), -ty);
			g.drawImage(background,0,Global.currentRoom.getHeight()-800,null);
			g.translate((int) ((0.5*(tx))%1138), ty);
			g.translate(-tx, -ty);
		}
		//Draws Objects
		for(int i=0; i<objects.size();i++)
		{
			if(objects.get(i).getId() == ObjectId.Ground || objects.get(i).getId() == ObjectId.Water || objects.get(i).getId() == ObjectId.Cloud)
			{
				objects.get(i).render(g);
			}
		}
		for(int i=0; i<objects.size();i++)
		{
			if(objects.get(i).getId() == ObjectId.Background)
			{
				objects.get(i).render(g);
			}
		}
		for(int i=0; i<objects.size();i++)
		{
			if(objects.get(i).getId() == ObjectId.Monster)
			{
				objects.get(i).render(g);
			}
		}
		for(int i=0; i<objects.size();i++)
		{
			if(objects.get(i).getId() == ObjectId.EyeCandy || objects.get(i).getId() == ObjectId.Checkpoint)
			{
				objects.get(i).render(g);
			}
		}
		for(int i=0; i<objects.size();i++)
		{
			if(objects.get(i).getId() == ObjectId.Player)
			{
				objects.get(i).render(g);
			}
		}
		for(int i=0; i<objects.size();i++)
		{
			if(objects.get(i).getId() == ObjectId.Warp)
			{
				objects.get(i).render(g);
			}
		}
		g.translate(tx, ty);
		g.drawImage(Sprites.money()[0],900,8,null);
		g.setFont(Global.HUD_FONT);
		g.setColor(Color.white);
		g.drawString("$"+Global.money,880,80);
		g.setColor(Color.green.darker());
		g.drawString("$"+Global.money,878,78);
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(9);
		nf.setGroupingUsed(false);
		g.setFont(Global.BIG_FONT);
		g.drawString(nf.format(Global.score) + "",100,55);
		g.setColor(Color.white);
		g.drawString(nf.format(Global.score) + "",98,53);
		
		for(int i=0; i<Global.inventory.length; i++)
		{
			BufferedImage bi;
			if(i==Global.currentCell)
			{
				bi = Sprites.inventory2()[0];
			}
			else
			{
				bi = Sprites.inventory()[0];
			}
			g.drawImage(bi,460+(i*80),10,null);
			if(Global.inventory[i] != 0)
			{
				g.drawImage(Game.ITEM_LIST[Global.inventory[i]].getPicture(),470+(i*80),20,null);
			}
		}
	}
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}
	public void addObject(GameObject object)
	{
		objects.add(object);
	}
	public void removeObject(GameObject object)
	{
		objects.remove(object);
	}
	public void removeObject(int object)
	{
		objects.remove(object);
	}

	public int getCurrentRoom()
	{
		return currentRoom;
	}
	public GameObject getObject(int i)
	{
		return objects.get(i);
	}
	public void setCurrentRoom(int croom)
	{
		currentRoom = croom;
	}
	public void resetToCheckpoint()
	{
		for(int j=0; j<10; j++)
		{
			for(int i=0; i<objects.size(); i++)
			{
				objects.remove(i);
			}
		}
		Global.checkpointRoom.loadStage();
		Global.checkpointRoom.addPlayer(Global.checkpointX, Global.checkpointY,false);

		AudioHandler.playMusic(Global.checkpointRoom.getMusic());
		System.out.println(Global.checkpointRoom.getMusic());
	}
}
