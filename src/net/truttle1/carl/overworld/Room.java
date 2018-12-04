package net.truttle1.carl.overworld;

import java.awt.image.BufferedImage;
import java.io.File;

import net.truttle1.carl.blocks.Grass;
import net.truttle1.carl.blocks.Sand;
import net.truttle1.carl.blocks.Stone;
import net.truttle1.carl.blocks.Water;
import net.truttle1.carl.main.AudioHandler;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.Theme;

public class Room {
	private BufferedImage stage;
	private OverworldMode om;
	private int height;
	private int width;
	private Theme theme;
	private BufferedImage bg;
	private int id;
	private File music;
	public Room(BufferedImage stage, OverworldMode om, boolean load, BufferedImage bg, Theme world, int id, File music)
	{
		this.stage = stage;
		this.om = om;
		this.bg = bg;
		this.theme = world;
		if(load)
		{
			loadStage();
		}
		this.id = id;
		this.music = music;
	}
	public File getMusic()
	{
		return music;
	}
	public int getId()
	{
		return id;
	}
	public void addPlayer(int x, int y)
	{
		om.addObject(new Carl(om.getGame(),x,y));
	}
	public Theme getTheme()
	{
		return theme;
	}
	public void loadStage()
	{
		Global.doneLoading = false;
		om.setCurrentRoom(this.id);
		if(theme == Theme.Tropical)
		{
			for(int x=0; x<stage.getWidth();x++)
			{
				for(int y=0; y<stage.getHeight();y++)
				{
					if(stage.getRGB(x,y) == Pallate.GRASS.getRGB())
					{
						om.addObject(new Grass(x,y,om.getGame(),om));
					}
					if(stage.getRGB(x,y) == Pallate.STONE.getRGB())
					{
						om.addObject(new Stone(x,y,om.getGame(),om));
					}
					if(stage.getRGB(x,y) == Pallate.STONE2.getRGB())
					{
						om.addObject(new Stone(x,y,om.getGame(),om));
					}
					if(stage.getRGB(x,y) == Pallate.SAND.getRGB())
					{
						om.addObject(new Sand(x,y,om.getGame(),om));
					}
					if(stage.getRGB(x,y) == Pallate.SAND2.getRGB())
					{
						om.addObject(new Sand(x,y,om.getGame(),om));
					}
					if(stage.getRGB(x,y) == Pallate.WATER.getRGB())
					{
						om.addObject(new Water(x,y,om.getGame(),om));
					}
				}
			}
		}
		Global.doneLoading = true;
		AudioHandler.playMusic(AudioHandler.TROPICAL_THEME);
		Global.currentRoom = this;
		height = stage.getHeight()*100;
		width = stage.getWidth()*100;
	}
	public void clearRoom()
	{
		for(int i=0; i<om.getObjects().size(); i++)
		{
			om.removeObject(i);
		}
	}
	public void setupWarp(int x, int y, Room r, int rx, int ry, boolean cm, File m)
	{
		/*
		for(int i=0;i<om.objects.size();i++)
		{
			if(om.objects.get(i).id == ObjectId.Warp)
			{
				Warp warp = (Warp)om.objects.get(i);
				if(warp.x == x && warp.y == y)
				{
					warp.setWarp(rx, ry, r, cm, m);
				}
				
			}
		}*/
	}
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
}
