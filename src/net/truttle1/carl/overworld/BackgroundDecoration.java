package net.truttle1.carl.overworld;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.ObjectId;

public class BackgroundDecoration extends GameObject{

	public BackgroundDecoration(Game window, int x, int y, BufferedImage[] animation) {
		super(window);
		this.x = x*100;
		this.y = y*100;
		this.currentAnimation = animation;
		this.id = ObjectId.Background;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(this.currentAnimation.equals(Sprites.palmTree()))
		{
			this.animate(x, y-15, currentAnimation, 0, g);
		}
		else if(this.currentAnimation.equals(Sprites.house1()))
		{
			this.animate(x, y-15, currentAnimation, 0, g);
		}
		else
		{
			this.animate(x, y, currentAnimation, 0, g);
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
