package net.truttle1.carl.overworld;

import java.awt.image.BufferedImage;
import java.io.File;

import net.truttle1.carl.blocks.Cloud;
import net.truttle1.carl.blocks.Tiles;
import net.truttle1.carl.blocks.Grass;
import net.truttle1.carl.blocks.Leaves;
import net.truttle1.carl.blocks.MovingPlatform;
import net.truttle1.carl.blocks.PassthroughGrass;
import net.truttle1.carl.blocks.Sand;
import net.truttle1.carl.blocks.Stone;
import net.truttle1.carl.blocks.Concrete;
import net.truttle1.carl.blocks.Water;
import net.truttle1.carl.enemies.Bird2;
import net.truttle1.carl.enemies.Coconut;
import net.truttle1.carl.enemies.Coconut2;
import net.truttle1.carl.enemies.Crab;
import net.truttle1.carl.enemies.Fish;
import net.truttle1.carl.enemies.Slug;
import net.truttle1.carl.enemies.Spike;
import net.truttle1.carl.enemies.Tree;
import net.truttle1.carl.enemies.Tree2;
import net.truttle1.carl.main.AudioHandler;
import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
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
	private static int checkpointIds = 1;
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
		Carl player = new Carl(om.getGame(),x,y,true);
		om.addObject(player);
	}
	public void addPlayer(int x, int y, boolean tie)
	{
		Carl player = new Carl(om.getGame(),x,y,tie);
		om.addObject(player);
	}
	public Theme getTheme()
	{
		return theme;
	}
	public void loadStage()
	{
		Global.doneLoading = false;
		om.setCurrentRoom(this.id);
		for(int x=0; x<stage.getWidth();x++)
		{
			for(int y=0; y<stage.getHeight();y++)
			{
				if(theme == Theme.Tropical)
				{
					System.out.println(this.id);
					loadTropical(x,y);
				}
				if(theme == Theme.Forest)
				{
					System.out.println(this.id);
					loadForest(x,y);
				}
				if(theme == Theme.Store)
				{
					System.out.println(this.id);
					loadStore(x,y);
				}
			}
		}
		Global.doneLoading = true;
		AudioHandler.playMusic(music);
		Global.currentRoom = this;
		RoomAdditions.setupRoom(this);
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
	public void addObject(GameObject g)
	{
		om.addObject(g);
	}
	public Game getGame()
	{
		return om.getGame();
	}
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public void loadTropical(int x, int y)
	{
		if(stage.getRGB(x,y) == Pallate.GRASS.getRGB())
		{
			om.addObject(new Grass(x,y,om.getGame(),om));
		}
		if(stage.getRGB(x,y) == Pallate.GRASS2.getRGB())
		{
			om.addObject(new PassthroughGrass(x,y,om.getGame(),om));
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
		if(stage.getRGB(x,y) == Pallate.MONSTER1.getRGB())
		{
			om.addObject(new Coconut(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.MONSTER2.getRGB())
		{
			om.addObject(new Fish(om.getGame(),x,y));
			om.addObject(new Water(x,y,om.getGame(),om));
		}
		if(stage.getRGB(x,y) == Pallate.MONSTER3.getRGB())
		{
			om.addObject(new Crab(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.MONSTER4.getRGB())
		{
			om.addObject(new Coconut2(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.SPIKE.getRGB())
		{
			om.addObject(new Spike(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.PLATFORM_H.getRGB())
		{
			om.addObject(new MovingPlatform(om.getGame(),x,y,om));
		}
		if(stage.getRGB(x,y) == Pallate.CHECKPOINT.getRGB())
		{
			om.addObject(new Checkpoint(om.getGame(),x,y,checkpointIds));
			checkpointIds++;
		}
		if(stage.getRGB(x,y) == Pallate.CHECKPOINT2.getRGB())
		{
			om.addObject(new Checkpoint(om.getGame(),x,y,checkpointIds));
			checkpointIds++;
		}
		if(stage.getRGB(x,y) == Pallate.SAVE.getRGB())
		{
			om.addObject(new Save(om.getGame(),x,y));
			checkpointIds++;
		}
		if(stage.getRGB(x,y) == Pallate.BACKGROUND1.getRGB())
		{
			om.addObject(new BackgroundDecoration(om.getGame(),x,y,Sprites.palmTree()));
		}
		if(stage.getRGB(x,y) == Pallate.BACKGROUND2.getRGB())
		{
			om.addObject(new BackgroundDecoration(om.getGame(),x,y,Sprites.house1()));
		}
		if(stage.getRGB(x,y) == Pallate.BACKGROUND3.getRGB())
		{
			om.addObject(new BackgroundDecoration(om.getGame(),x,y,Sprites.shop()));
		}
	}
	public void loadForest(int x, int y)
	{
		if(stage.getRGB(x,y) == Pallate.GRASS.getRGB())
		{
			om.addObject(new Grass(x,y,om.getGame(),om));
		}
		if(stage.getRGB(x,y) == Pallate.STONE.getRGB())
		{
			om.addObject(new Stone(x,y,om.getGame(),om));
		}
		if(stage.getRGB(x,y) == Pallate.CLOUD.getRGB())
		{
			om.addObject(new Cloud(x,y,om.getGame(),om));
		}
		if(stage.getRGB(x,y) == Pallate.LEAVES.getRGB())
		{
			om.addObject(new Leaves(x,y,om.getGame(),om));
		}
		if(stage.getRGB(x,y) == Pallate.MONSTER1.getRGB())
		{
			om.addObject(new Slug(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.MONSTER3.getRGB())
		{
			om.addObject(new Tree(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.MONSTER5.getRGB())
		{
			om.addObject(new Bird2(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.MONSTER6.getRGB())
		{
			om.addObject(new Tree2(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.SPIKE.getRGB())
		{
			om.addObject(new Spike(om.getGame(),x,y));
		}
		if(stage.getRGB(x,y) == Pallate.PLATFORM_H.getRGB())
		{
			om.addObject(new MovingPlatform(om.getGame(),x,y,om));
		}
		if(stage.getRGB(x,y) == Pallate.CHECKPOINT.getRGB())
		{
			om.addObject(new Checkpoint(om.getGame(),x,y,checkpointIds));
			checkpointIds++;
		}
		if(stage.getRGB(x,y) == Pallate.SAVE.getRGB())
		{
			om.addObject(new Save(om.getGame(),x,y));
			checkpointIds++;
		}
		if(stage.getRGB(x,y) == Pallate.TRUNK.getRGB())
		{
			om.addObject(new BackgroundDecoration(om.getGame(),x,y,Sprites.trunk()));
		}
		if(stage.getRGB(x,y) == Pallate.BACKGROUND2.getRGB())
		{
			om.addObject(new BackgroundDecoration(om.getGame(),x,y,Sprites.house1()));
		}
		if(stage.getRGB(x,y) == Pallate.BACKGROUND3.getRGB())
		{
			om.addObject(new BackgroundDecoration(om.getGame(),x,y,Sprites.shop()));
		}
	}
	public void loadStore(int x, int y)
	{
		if(stage.getRGB(x,y) == Pallate.SAND.getRGB())
		{
			om.addObject(new Concrete(x,y,om.getGame(),om));
		}
		if(stage.getRGB(x,y) == Pallate.STONE.getRGB())
		{
			om.addObject(new Tiles(x,y,om.getGame(),om));
		}
	}
}
