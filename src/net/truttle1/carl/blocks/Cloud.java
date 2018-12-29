package net.truttle1.carl.blocks;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.overworld.OverworldMode;
import net.truttle1.carl.overworld.Sprites;

public class Cloud extends Grass{

	private BufferedImage[] currentAnimation;
	private OverworldMode om;
	public Cloud(int x, int y,Game window,OverworldMode om) {
		super(x,y,window,om);
		unmodifiedX = x;
		unmodifiedY = y;
		this.x = x*100;
		this.y = (y*100)-24;
		this.id = ObjectId.Ground;
		this.om = om;
		this.goThroughable = true;
		currentAnimation = Sprites.cloud();
		
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
		return new Rectangle(x,y,100,100);
	}

}
