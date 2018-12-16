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
	public static Room beach1;
	public static Room beach2;
	public static Room beach3;
	private BufferedImage background;
	private BufferedImage tropicalBackground;
	public OverworldMode(Game window) 
	{
		super(window);
		tropicalBackground = window.getImageLoad().loadImage("/img/bg/tropical_00001.png");

		BufferedImage b = window.getImageLoad().loadImage("/room/beach1.png");
		BufferedImage b2 = window.getImageLoad().loadImage("/room/beach2.png");
		BufferedImage b3 = window.getImageLoad().loadImage("/room/beach3.png");
		beach1 = new Room(b,this,false,null,Theme.Tropical,0,AudioHandler.TROPICAL_THEME);
		beach2 = new Room(b2,this,false,null,Theme.Tropical,1,AudioHandler.TROPICAL_THEME);
		beach3 = new Room(b2,this,false,null,Theme.Tropical,2,AudioHandler.TROPICAL_THEME);
		beach1.loadStage();
		beach1.addPlayer(200, 2500);
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
		Graphics2D g2d = (Graphics2D) g;			
		g.translate((int) ((-0.5*(tx))%1138), -ty);
		g.drawImage(background,0,Global.currentRoom.getHeight()-800,null);
		g.translate((int) ((0.5*(tx))%1138), ty);
		g.translate(-tx, -ty);
		//Draws Objects
		for(int i=0; i<objects.size();i++)
		{
			if(objects.get(i).getId() == ObjectId.Ground || objects.get(i).getId() == ObjectId.Water)
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
