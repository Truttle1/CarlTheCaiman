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

public class MovingPlatform extends Grass{

	private BufferedImage[] currentAnimation;
	@SuppressWarnings("unused")
	private OverworldMode om;

	private boolean topLine = true;
	private boolean bottomLine = true;
	private boolean leftLine = true;
	private boolean rightLine = true;
	Color clr = Color.gray;
	int movement = 4;
	public MovingPlatform(Game window,int x, int y,OverworldMode om) {
		super(x,y,window,om);
		unmodifiedX = x;
		unmodifiedY = y;
		this.x = x*100;
		this.id = ObjectId.Ground;
		this.om = om;
		currentAnimation = Sprites.platform();
		
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
	public void tick() 
	{
		this.x += movement;
		for(int i=0; i<om.getObjects().size(); i++)
		{
			if(om.getObject(i).getId() == ObjectId.Ground && om.getObject(i) != this && om.getObject(i).getBounds().intersects(this.getBounds()))
			{
				movement *= -1;
				this.x += movement;
			}
			if(om.getObject(i).getId() == ObjectId.Player && om.getObject(i).getBounds().intersects(this.getBounds()))
			{
				om.getObject(i).setX(om.getObject(i).getX()+movement);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(om.getTx()<this.x+164 && om.getTx()+Game.WIDTH>this.x && om.getTy()<this.y+164 && om.getTy()+Game.HEIGHT>this.y)
		{
			g.drawImage(currentAnimation[0],x,y,null);
		}
	}
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,200,30);
	}

}
