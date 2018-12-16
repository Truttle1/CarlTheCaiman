package net.truttle1.carl.overworld;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.truttle1.carl.main.Fade;
import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;

public class Warp extends GameObject{

	private int warpX;
	private int warpY;
	private Room warpRoom;
	private OverworldMode om;
	private boolean left;
	public Warp(Game window,int x, int y, int destinationX, int destinationY, Room warpRoom, boolean left) 
	{
		super(window);
		this.x = x;
		this.y = y;
		this.warpX = destinationX;
		this.warpY = destinationY;
		this.warpRoom = warpRoom;
		this.id = ObjectId.Warp;
		this.left = left;
	}

	@Override
	public void tick() 
	{
		if(om == null)
		{
			this.om = window.getOverworldMode();
			return;
		}
		for(int i=0; i<om.getObjects().size();i++)
		{
			if(om.getObject(i).getId() == ObjectId.Player)
			{
				Carl carl = (Carl)om.getObject(i);
				if(carl.getBounds().intersects(this.getBounds()))
				{
					nextRoom(carl);
				}
			}
		}
	}

	private int getDistanceToPlayer()
	{
		Carl carl = null;
		if(om == null)return Integer.MAX_VALUE;
		for(int i=0; i<om.getObjects().size();i++)
		{
			if(om.getObject(i).getId() == ObjectId.Player)
			{
				carl = (Carl)om.getObject(i);
				break;
			}
		}
		if(carl == null)return Integer.MAX_VALUE;
		int x1 = Math.abs(this.x-carl.getX());
		int y1 = Math.abs(this.y-carl.getY());
		return (int)(Math.sqrt(Math.pow(x1,2)+Math.pow(y1,2)));
	}
	@Override
	public void render(Graphics g) 
	{
		if(left && getDistanceToPlayer()<200)
		{
			this.animate(x, y, Sprites.arrowLeft(), 0, g);
		}
		else if(getDistanceToPlayer()<200)
		{
			this.animate(x, y, Sprites.arrowRight(), 0, g);
		}
	}

	private void nextRoom(Carl carl)
	{
		if(!Fade.getRunning() && Fade.getFadingIn())
		{
			Fade.start();
		}
		if(!Fade.getFadingIn()&& !Global.currentRoom.equals(this.warpRoom))
		{
			for(int j=0; j<10; j++)
			{
				for(int i=0; i<om.getObjects().size(); i++)
				{
					om.removeObject(i);
				}
			}
			warpRoom.loadStage();
			warpRoom.addPlayer(this.warpX, this.warpY,carl.getTie());

		}
	}
	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x+25,y,50,50);
	}
	
}
