package net.truttle1.carl.overworld;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;

public class Checkpoint extends GameObject{

	private int checkpointId;
	public Checkpoint(Game window, int x, int y, int id) {
		super(window);
		this.x = x*100;
		this.y = y*100;
		this.checkpointId = id;
		this.id = ObjectId.Checkpoint;
		this.currentAnimation = Sprites.checkpoint();
	}

	@Override
	public void tick() 
	{
		for(int i=0; i<window.getOverworldMode().getObjects().size();i++)
		{
			if(window.getOverworldMode().getObject(i).getId() == ObjectId.Player)
			{
				if(window.getOverworldMode().getObject(i).getBounds().intersects(this.getBounds()))
				{
					playerTouched((Carl)window.getOverworldMode().getObject(i));
				}
			}
		}
	}
	private void playerTouched(Carl carl)
	{
		if(Global.checkpointX != this.x)
		{
			carl.setTie(true);
		}
		Global.checkpointX = this.x;
		Global.currentCheckpointId = this.checkpointId;
		Global.checkpointY = carl.getY();
		Global.checkpointRoom = Global.currentRoom;
		Global.checkpointRoomId = Global.currentRoom.getId();
	}

	@Override
	public void render(Graphics g) {
		if(Global.currentCheckpointId == this.checkpointId)
		{
			this.setFrame(0, 1);
		}
		else
		{
			this.setFrame(0, 0);
		}
		this.animateAtSpeed(x,y-15,currentAnimation,0,g,0);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x+50,this.y,50,200);
	}

}
