package net.truttle1.carl.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.overworld.Carl;

public class Projectile extends GameObject{

	private int counter = 0;
	public Projectile(Game window, int x, int y, int hVelocity, int vVelocity, BufferedImage[] currentAnimation) {
		super(window);
		this.x = x;
		this.y = y;
		this.hVelocity = hVelocity;
		this.vVelocity = vVelocity;
		this.currentAnimation = currentAnimation;
		this.id = ObjectId.Monster;
	}

	@Override
	public void tick() {
		counter++;
		if(counter>48)
		{
			window.getOverworldMode().removeObject(this);
		}
		for(int i=0; i<window.getOverworldMode().getObjects().size(); i++)
		{
			GameObject tempObject = window.getOverworldMode().getObject(i);
			if(window.getOverworldMode().getObject(i).getId() == ObjectId.Player)
			{
				playerCollision((Carl)window.getOverworldMode().getObject(i));
			}
			if((tempObject.getId() == ObjectId.Ground || tempObject.getId() == ObjectId.Cloud) && this.getBounds().intersects(tempObject.getBounds()))
			{
				window.getOverworldMode().removeObject(this);
			}
		}
		this.x += this.hVelocity;
		this.y += this.vVelocity;
	}

	@Override
	public void render(Graphics g) {
		this.animate(x, y, currentAnimation, 0, g);
	}

	private void playerCollision(Carl carl)
	{
		if(this.getBounds().intersects(carl.getBounds()))
		{
			//Player takes damage when he collides with an enemy.
			//How this damage is calculated is the player's problem.
			carl.enemyCollision(this);
			window.getOverworldMode().removeObject(this);
		}
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+5,y+5,20,20);
	}

}
