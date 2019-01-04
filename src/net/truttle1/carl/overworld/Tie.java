package net.truttle1.carl.overworld;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;

public class Tie extends GameObject{

	public Tie(Game window, int x, int y) {
		super(window);
		this.x = x;
		this.y = y;
		this.vVelocity = -30;
		this.id = ObjectId.EyeCandy;
	}

	@Override
	public void tick() 
	{
		vVelocity += 5;
		this.y += vVelocity;
		for(int i=0; i<window.getOverworldMode().getObjects().size();i++)
		{
			if(window.getOverworldMode().getObject(i).getId() == ObjectId.Player && this.vVelocity>-1)
			{
				if(window.getOverworldMode().getObject(i).getBottom().intersects(this.getBounds()))
				{
					Carl c = (Carl)window.getOverworldMode().getObject(i);
					c.setTie(true);
					Global.score += 325;
					window.getOverworldMode().removeObject(this);
				}
			}
			if(window.getOverworldMode().getObject(i).getId() == ObjectId.Ground || window.getOverworldMode().getObject(i).getId() == ObjectId.Cloud)
			{
				if(window.getOverworldMode().getObject(i).getBounds().intersects(this.getBounds()))
				{
					vVelocity = 0;
					this.y = window.getOverworldMode().getObject(i).getY()-25;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		this.animate(x, y, Sprites.tie(), 0, g);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x,this.y+10,50,30);
	}

}
