package net.truttle1.carl.overworld;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.main.SpeechBubble;

public class Save extends GameObject{

	private boolean showTalkIcon = false;
	public Save(Game window, int x, int y) {
		super(window);
		this.x = x*100;
		this.y = y*100;
		this.id = ObjectId.Monster;
	}

	@Override
	public void tick() 
	{
		Carl c = null;
		for(int i=0; i<window.getOverworldMode().getObjects().size();i++)
		{
			if(window.getOverworldMode().getObject(i).getId() == ObjectId.Player)
			{
				c = (Carl)window.getOverworldMode().getObject(i);
			}
		}
		if(c != null)
		{
			if(this.getDistanceTo(c)<200 && Global.talking == 0)
			{
				showTalkIcon = true;
				if(Global.cPressed)
				{
					Global.talking = 1;
					Global.talkingTo = this;
					Global.canMove = false;
					SpeechBubble.yesNo("Would you like to save the game?",Global.TEXT_FONT);
				}
			}
			else
			{
				showTalkIcon = false;
			}
		}
		if(Global.talking == 2 && Global.talkingTo == this)
		{
			if(SpeechBubble.selection == 0)
			{
				window.save(c.getX(), c.getY());
				SpeechBubble.talk("Game saved!");
				Global.talking++;
			}
			else
			{
				SpeechBubble.talk("The game was not saved!/Why did you even come here?");
				Global.talking++;
			}
		}
		if(Global.talking == 4 && Global.talkingTo == this)
		{
			Global.talkingTo = null;
			Global.talking = 0;
			Global.canMove = true;
		}
	}

	@Override
	public void render(Graphics g) {
		this.animate(x, y-17, Sprites.savePoint(), 0, g);

		if(showTalkIcon)
		{
			this.animateNoFlip(x+Sprites.savePoint()[1].getWidth()/2, y-50, Sprites.cIcon(), 1, g);
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	
}
