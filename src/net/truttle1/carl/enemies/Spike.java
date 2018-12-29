package net.truttle1.carl.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.overworld.Carl;
import net.truttle1.carl.overworld.Sprites;

public class Spike extends GameObject{

	public Spike(Game window, int x, int y) 
	{
		super(window);
		this.x = x*100;
		this.y = y*100;
		this.id = ObjectId.Monster;
	}

	@Override
	public void tick() 
	{
		for(int i=0; i<window.getOverworldMode().getObjects().size();i++)
		{
			if(window.getOverworldMode().getObject(i).getId() == ObjectId.Player && window.getOverworldMode().getObject(i).getBounds().intersects(this.getBounds()))
			{
				Carl carl = (Carl)window.getOverworldMode().getObject(i);
				carl.enemyCollision(this);
			}
		}
	}

	@Override
	public void render(Graphics g) 
	{
		this.animate(x, y, Sprites.spike(), 0, g);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y+33,100,66);
	}

}
