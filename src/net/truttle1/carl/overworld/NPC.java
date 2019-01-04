package net.truttle1.carl.overworld;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.main.SpeechBubble;

public class NPC extends GameObject{

	private BufferedImage[] idleAnimation;
	private BufferedImage[] talkAnimation;
	private String[] dialogue;
	private boolean[] carlTalking;
	private boolean showTalkIcon = false;
	public NPC(Game window, int x, int y, BufferedImage[] idle, BufferedImage[] talk, String... d) {
		super(window);
		this.idleAnimation = idle;
		this.talkAnimation = talk;
		this.x = x;
		this.y = y;
		dialogue = new String[d.length];
		carlTalking = new boolean[d.length];
		for(int i=0; i<d.length; i++)
		{
			if(d[i].startsWith("Carl: "))
			{
				d[i] = d[i].substring(5);
				carlTalking[i] = true;
			}
			else
			{
				carlTalking[i] = false;
			}
			this.dialogue[i] = d[i];
		}
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
		if(c != null && c.getX()>this.x)
		{
			this.flipped = true;
		}
		else
		{
			this.flipped = false;
		}
		if(Global.talking %2==0 && Global.talkingTo == this && Global.talking != 0)
		{
			if(Global.talking/2>=dialogue.length || dialogue[Global.talking/2] == null)
			{
				Global.talkingTo = null;
				Global.talking = 0;
				Global.canMove = true;
			}
			else
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				SpeechBubble.talk(dialogue[Global.talking/2]);
			}
		}
		if(c != null)
		{
			if(idleAnimation != null && this.getDistanceTo(c)<idleAnimation[1].getWidth() && Global.talking == 0)
			{
				showTalkIcon = true;
				if(Global.cPressed)
				{
					Global.talking = 1;
					Global.talkingTo = this;
					Global.canMove = false;
					SpeechBubble.talk(dialogue[0]);
				}
			}
			else
			{
				showTalkIcon = false;
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(Global.talkingTo == this)
		{
			this.animate(x, y, talkAnimation, 0, g);
		}
		else
		{
			this.animate(x, y, idleAnimation, 0, g);
		}
		if(showTalkIcon)
		{
			this.animateNoFlip(x+talkAnimation[1].getWidth()/2, y-50, Sprites.cIcon(), 1, g);
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	
}
