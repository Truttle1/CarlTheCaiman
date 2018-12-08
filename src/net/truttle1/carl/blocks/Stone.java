package net.truttle1.carl.blocks;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.overworld.OverworldMode;
import net.truttle1.carl.overworld.Sprites;

public class Stone extends Grass{

	private BufferedImage[] currentAnimation;
	@SuppressWarnings("unused")
	private OverworldMode om;

	private boolean topLine = true;
	private boolean bottomLine = true;
	private boolean leftLine = true;
	private boolean rightLine = true;
	Color clr = Color.gray;
	public Stone(int x, int y,Game window,OverworldMode om) {
		super(x,y,window,om);
		unmodifiedX = x;
		unmodifiedY = y;
		this.x = x*100;
		this.y = (y*100)-24;
		this.id = ObjectId.Ground;
		this.om = om;
		currentAnimation = Sprites.stone();
		
	}
	

	public int getUnmodifiedX()
	{
		return unmodifiedX;
	}
	public int getUnmodifiedY()
	{
		return unmodifiedY;
	}
	@Override
	public void tick() {
		if(topLine && bottomLine && leftLine && rightLine)
		{
			for(int i=0; i<om.getObjects().size();i++)
			{
				if(om.getObjects().get(i) != null && om.getObjects().get(i).getId() == ObjectId.Ground)
				{
					Grass g = (Grass)om.getObjects().get(i);
					if(g != null && g != this && g instanceof Stone)
					{
						if(g.getUnmodifiedX() == this.getUnmodifiedX() & g.getUnmodifiedY() == this.getUnmodifiedY()-1)
						{
							topLine = false;
						}
						if(g.getUnmodifiedX() == this.getUnmodifiedX() & g.getUnmodifiedY() == this.getUnmodifiedY()+1)
						{
							bottomLine = false;
						}
						if(g.getUnmodifiedX() == this.getUnmodifiedX()+1 & g.getUnmodifiedY() == this.getUnmodifiedY())
						{
							rightLine = false;
						}
						if(g.getUnmodifiedX() == this.getUnmodifiedX()-1 & g.getUnmodifiedY() == this.getUnmodifiedY())
						{
							leftLine = false;
						}
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(om.getTx()<this.x+164 && om.getTx()+Game.WIDTH>this.x && om.getTy()<this.y+164 && om.getTy()+Game.HEIGHT>this.y)
		{
			g.drawImage(currentAnimation[0],x,y,null);
			if(topLine)
			{
				g.setColor(clr);
				g.fillRect(x, y, 100, 4);
			}
			if(bottomLine)
			{
				g.setColor(clr);
				g.fillRect(x, y+96, 100, 4);
			}
			if(leftLine)
			{
				g.setColor(clr);
				g.fillRect(x, y, 4, 100);
			}
			if(rightLine)
			{
				g.setColor(clr);
				g.fillRect(x+96, y, 4, 100);
			}
		}
	}
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,100,100);
	}

}
