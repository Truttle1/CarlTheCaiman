package net.truttle1.carl.blocks;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.overworld.OverworldMode;
import net.truttle1.carl.overworld.Sprites;

public class Grass extends GameObject{

	private BufferedImage[] currentAnimation;
	@SuppressWarnings("unused")
	private OverworldMode om;
	protected int unmodifiedX;
	protected int unmodifiedY;
	protected boolean makesDirt = true;
	protected boolean goThroughable = false;
	public Grass(int x, int y,Game window,OverworldMode om) {
		super(window);
		unmodifiedX = x;
		unmodifiedY = y;
		this.x = x*100;
		this.y = (y*100)-24;
		this.id = ObjectId.Ground;
		this.om = om;
		currentAnimation = Sprites.grass();
			for(int i=0; i<om.getObjects().size();i++)
			{
				if(om.getObject(i) != null && om.getObject(i).getId() == ObjectId.Ground)
				{
					Grass g = (Grass)om.getObject(i);
					if(g != null && g != this && g.makesDirt && g.unmodifiedX == this.unmodifiedX & g.unmodifiedY == this.unmodifiedY-1)
					{
						currentAnimation = Sprites.ground();
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
		//System.out.println("I AM GRASS! I LIVE AT: " + x + " , " + y);
	}

	@Override
	public void render(Graphics g) {
		this.animate(x,y,currentAnimation,0,g);
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
