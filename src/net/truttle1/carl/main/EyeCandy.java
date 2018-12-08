package net.truttle1.carl.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EyeCandy extends GameObject{

	public EyeCandy(Game window, int x, int y,BufferedImage[] currentAnimation) {
		super(window);
		this.x = x;
		this.y = y;
		this.currentAnimation = currentAnimation;
		this.id = ObjectId.EyeCandy;
	}

	@Override
	public void tick() 
	{
		if(getFrame(0)>=this.getAnimationLength(currentAnimation))
		{
			window.getOverworldMode().removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) 
	{
		animate(x, y, currentAnimation, 0, g);
	}

	@Override
	public Rectangle getBounds() 
	{
		return null;
	}

}
