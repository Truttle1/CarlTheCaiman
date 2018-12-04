package net.truttle1.carl.blocks;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.overworld.OverworldMode;
import net.truttle1.carl.overworld.Sprites;

public class Water extends GameObject{

	private BufferedImage[] currentAnimation;
	@SuppressWarnings("unused")
	private OverworldMode om;
	protected int unmodifiedX;
	protected int unmodifiedY;
	protected boolean makesDirt = true;
	protected boolean goThroughable = false;
	private boolean doneLoading;
	private static Color col;
	public Water(int x, int y,Game window,OverworldMode om) {
		super(window);
		unmodifiedX = x;
		unmodifiedY = y;
		this.x = x*100;
		this.y = (y*100)-24;
		this.id = ObjectId.Water;
		this.om = om;
		currentAnimation = Sprites.flowingWater();
		col = new Color(0xAED9F7);
		for(int i=0; i<om.getObjects().size();i++)
		{
			if(om.getObject(i) != null && om.getObject(i).getId() == ObjectId.Ground)
			{
				Grass g = (Grass)om.getObject(i);
				if(g != null && g.getUnmodifiedX() == this.unmodifiedX & g.getUnmodifiedY() == this.unmodifiedY+1)
				{
					System.out.println("aaaaaa");
					om.addObject(new Water(unmodifiedX,unmodifiedY+1,window,om));
				}
			}
			if(om.getObject(i) != null && om.getObject(i).getId() == ObjectId.Water)
			{
				Water g = (Water)om.getObject(i);
				if(g != null && g != this && g.makesDirt && g.unmodifiedX == this.unmodifiedX & g.unmodifiedY == this.unmodifiedY-1)
				{
				currentAnimation = Sprites.water();
				}
			}
		}
	}

	public boolean getGoThroughable()
	{
		return goThroughable;
	}
	@Override
	public void tick() {
		/*
		if(!Global.doneLoading || !this.doneLoading)
		{
			for(int i=0; i<om.getObjects().size();i++)
			{
				if(om.getObject(i) != null && om.getObject(i).getId() == ObjectId.Ground)
				{
					Grass g = (Grass)om.getObject(i);
					if(g != null && g.getUnmodifiedX() == this.unmodifiedX & g.getUnmodifiedY() == this.unmodifiedY+1)
					{
						om.addObject(new Water(unmodifiedX,unmodifiedY+1,window,om));
						this.doneLoading = true;
					}
				}
			}
		}*/
	}

	@Override
	public void render(Graphics g) {
		if(this.currentAnimation == Sprites.flowingWater())
		{
			this.animate(x,y,currentAnimation,0,g);
		}
		else
		{
			g.setColor(col);
			g.fillRect(x, y, 100, 100);
		}
	}
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,100,100);
	}
	public int getUnmodifiedX()
	{
		return unmodifiedX;
	}
	public int getUnmodifiedY()
	{
		return unmodifiedY;
	}

}
