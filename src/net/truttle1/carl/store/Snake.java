package net.truttle1.carl.store;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.main.SpeechBubble;
import net.truttle1.carl.overworld.Carl;
import net.truttle1.carl.overworld.Sprites;

public class Snake extends GameObject{

	private BufferedImage[] idleAnimation;
	private BufferedImage[] talkAnimation;
	private boolean showTalkIcon = false;
	private Item[] inventory;
	private Carl c = null;
	private int selection = 0;
	private boolean buying;
	private boolean selectedBuying;
	public Snake(Game window, int x, int y, BufferedImage[] idle, BufferedImage[] talk, Item...inventory) {
		super(window);
		this.idleAnimation = idle;
		this.talkAnimation = talk;
		this.currentAnimation = this.idleAnimation;
		this.x = x;
		this.y = y;
		this.id = ObjectId.Monster;
		this.inventory = inventory;
	}

	@Override
	public void tick() 
	{
		if(c == null)
		{
			for(int i=0; i<window.getOverworldMode().getObjects().size();i++)
			{
				if(window.getOverworldMode().getObject(i).getId() == ObjectId.Player)
				{
					c = (Carl)window.getOverworldMode().getObject(i);
				}
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
						SpeechBubble.talk("Thalutations, thir! I'm the thopkeeper of thith here Thnake Thop!/May I interetht you in any of our fine producth?");
					this.currentAnimation = this.talkAnimation;
					c.setAnimationLock(true);
					c.setAnimation(Sprites.carlIdle(c.getTie()));
					selectedBuying = false;
					buying = true;
				}
			}
			else
			{
				showTalkIcon = false;
			}
		}
		if(Global.events[Global.EV_METSNAKE]==0 && Global.talkingTo == this && Global.talking>0)
		{
			introduction();
		}
		if(Global.events[Global.EV_METSNAKE]==1 && Global.talkingTo == this && Global.talking>=4 && Global.talking <= 99)
		{
			if(Global.talking == 4)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeTalk();
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				SpeechBubble.talk("Thank you thir! Come again! :)");
			}
			if(Global.talking == 6)
			{
				Global.talking = 0;
				Global.talkingTo = null;
				Global.canMove = true;
				c.setAnimationLock(false);
				this.currentAnimation = Sprites.snakeIdle();
			}
			

			if(Global.talking == 20)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeIdle();
				c.setAnimation(Sprites.carlTalk(c.getTie()));
				SpeechBubble.talk("Can I purchase the nothing, please? I really want this nothing!");
			}
			if(Global.talking == 22)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeTalk();
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				SpeechBubble.talk("Thorry thir, but the nothing ith not able to be purchathed...at all...becauthe/it doeth not ekthitht... Would you like to purchathe thomthing elthe?");
			}
			if(Global.talking == 24)
			{
				Global.talking = 100;
				Global.canMove = true;
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				this.currentAnimation = Sprites.snakeIdle();
			}
			

			if(Global.talking == 30)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeIdle();
				c.setAnimation(Sprites.carlTalk(c.getTie()));
				SpeechBubble.talk("Can I buy the " + inventory[selection].getName() + "?");
			}
			if(Global.talking == 32)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeTalk();
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				SpeechBubble.talk("Thure thing! That will be $" + inventory[selection].getPrice() + "! Have a nithe day!");
				Global.money -= inventory[selection].getPrice();
				for(int i=0; i<Global.inventory.length; i++)
				{
					if(Global.inventory[i] == 0)
					{
						for(int j=0; j<Game.ITEM_LIST.length; j++)
						{
							if(Game.ITEM_LIST[j] != null && Game.ITEM_LIST[j].getName().equals(inventory[selection].getName()))
							{
								Global.inventory[i] = j;
							}
						}
						break;
					}
				}
			}
			if(Global.talking == 34)
			{
				Global.talking = 100;
				Global.canMove = true;
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				this.currentAnimation = Sprites.snakeIdle();
			}
			
			if(Global.talking == 40)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeIdle();
				c.setAnimation(Sprites.carlTalk(c.getTie()));
				SpeechBubble.talk("Can I buy the " + inventory[selection].getName() + "?");
			}
			if(Global.talking == 42)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeTalk();
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				SpeechBubble.talk("Thorry, but your inventory theemth to be full. Come back onthe you uthe up thome of thothe itemth/that you have...");
			}
			if(Global.talking == 44)
			{
				Global.talking = 100;
				Global.canMove = true;
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				this.currentAnimation = Sprites.snakeIdle();
			}
			
			if(Global.talking == 50)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeIdle();
				c.setAnimation(Sprites.carlTalk(c.getTie()));
				SpeechBubble.talk("Can I buy the " + inventory[selection].getName() + "?");
			}
			if(Global.talking == 52)
			{
				Global.talking++;
				Global.talkingTo = this;
				Global.canMove = false;
				this.currentAnimation = Sprites.snakeTalk();
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				SpeechBubble.talk("Thorry, but you do not have enough money...");
			}
			if(Global.talking == 54)
			{
				Global.talking = 100;
				Global.canMove = true;
				c.setAnimation(Sprites.carlIdle(c.getTie()));
				this.currentAnimation = Sprites.snakeIdle();
			}
		}
		if(Global.events[Global.EV_METSNAKE]==1 && Global.talkingTo == this && (Global.talking==2||Global.talking>=100) )
		{
			store();
		}
		
	}

	@Override
	public void render(Graphics g) {
		this.animate(x, y-50, currentAnimation, 0, g);
		if(showTalkIcon)
		{
			this.animateNoFlip(x+talkAnimation[1].getWidth()/2, y-100, Sprites.cIcon(), 1, g);
		}
		this.animate(x+55, y+80,Sprites.counter(), 2, g);
	}

	public void store()
	{
		Global.talking = 100;
		Global.canMove = false;
		if(Global.upPressed)
		{
			if(selection==0)
			{
				selection = 2;
			}
			else
			{
				selection--;
			}
		}
		if(Global.downPressed)
		{
			if(selection==2)
			{
				selection = 0;
			}
			else
			{
				selection++;
			}
		}
		if(Global.xPressed)
		{
			Global.talking = 4;
		}
		if(Global.zPressed)
		{
			System.out.println(selection);
			if(selection>=inventory.length)
			{
				Global.talking = 20;
			}
			else if(Global.inventory[3] != 0)
			{
				Global.talking = 40;
			}
			else if(Global.money>=inventory[selection].getPrice())
			{
				Global.talking = 30;
			}
			else
			{
				Global.talking = 50;
			}
		}
	}
	public void storeRender(Graphics g)
	{
		this.currentAnimation = Sprites.snakeIdle();
		g.setColor(Color.GRAY);
		g.fillRect(48, 140, 570, 400);
		g.setColor(Color.WHITE);
		g.fillRect(32, 124, 570, 400);
		
		g.setColor(Color.CYAN);
		g.fillRect(48, 140+(selection*100), 538, 96);
		for(int i=0; i<inventory.length;i++)
		{
			g.drawImage(inventory[i].getPicture(),64,140+(i*100),null);
		}
		g.setFont(Global.HUD_FONT);
		g.setColor(Color.RED);
		g.drawString("Press [Z] to Select An Item.", 96, 464);
		g.drawString("Press [X] to Quit Shopping.", 96, 500);
		g.setColor(Color.BLUE);
		for(int i=0; i<3; i++)
		{
			if(inventory.length>i)
			{
				g.drawString(inventory[i].getName(),148,170+(i*100));
			}
			else
			{
				g.drawString("Nothing",148,170+(i*100));
			}
		}
		g.setFont(Global.SMALL_FONT);
		g.setColor(Color.BLACK);
		for(int i=0; i<3; i++)
		{
			if(inventory.length>i)
			{
				String[] text = inventory[i].getFlavorText().split("/");
				for(int j = 0; j<text.length; j++)
				{
					g.drawString(text[j],148,190+(i*100)+(j*16));
				}
			}
			else
			{
				String ft = "This item is not in right now. What could it be? Well, If I/told you, it would get rid of the suspense, wouldn't it!";
				String[] text = ft.split("/");
				for(int j = 0; j<text.length; j++)
				{
					g.drawString(text[j],148,190+(i*100)+(j*16));
				}
			}
		}
		g.setFont(Global.TEXT_FONT);
		g.setColor(Color.GREEN);
		for(int i=0; i<3; i++)
		{
			if(inventory.length>i)
			{
				g.drawString("$" + inventory[i].getPrice(),70,222+(i*100));
			}
			else
			{
				g.drawString("Sold Out!",50,222+(i*100));
			}
		}
		g.setColor(Color.GREEN.darker());
		for(int i=0; i<3; i++)
		{
			if(inventory.length>i)
			{
				g.drawString("$" + inventory[i].getPrice(),68,220+(i*100));
			}
			else
			{
				g.drawString("Sold Out!",48,220+(i*100));
			}
		}
	}
	@Override
	public Rectangle getBounds() {
		return null;
	}
	private void introduction()
	{
		if(Global.talking == 2)
		{
			Global.talking++;
			Global.talkingTo = this;
			Global.canMove = false;
			this.currentAnimation = Sprites.snakeIdle();
			c.setAnimation(Sprites.carlTalk(c.getTie()));
			if(Global.events[Global.EV_METSNAKE]==0)
			{
				SpeechBubble.talk("Hold on a second, you're a snake, right? How on earth are you unable to pronounce the \"s\" sound? I mean,/that's the snakiest sound that exists in the entire English language!");
			}
		}
		if(Global.talking == 4)
		{
			Global.talking++;
			Global.talkingTo = this;
			Global.canMove = false;
			this.currentAnimation = Sprites.snakeTalk();
			c.setAnimation(Sprites.carlIdle(c.getTie()));
			if(Global.events[Global.EV_METSNAKE]==0)
			{
				SpeechBubble.talk("Yeth, thir...I am in fact a thnake... Ath for my ability to pronounthe the \"Eth\" thound, I uthed/to have that ability before that evil \"Literally Thatan\" guy game around and thtarted/uthing dark magic to curthe everybody! And to make matterth worthe, HE APPEARED ATH A THNAKE!!!/Buthineth hath been terrible thinthe then!!!");
			}
		}
		if(Global.talking == 6)
		{
			Global.talking++;
			Global.talkingTo = this;
			Global.canMove = false;
			this.currentAnimation = Sprites.snakeIdle();
			c.setAnimation(Sprites.carlTalk(c.getTie()));
			if(Global.events[Global.EV_METSNAKE]==0)
			{
				SpeechBubble.talk("Yikes, I can imagine how hard that has been on your entire livelihood... So, what kinds of/products do you sell in this fine establishment?");
			}
		}
		if(Global.talking == 8)
		{
			Global.talking++;
			Global.talkingTo = this;
			Global.canMove = false;
			this.currentAnimation = Sprites.snakeTalk();
			c.setAnimation(Sprites.carlIdle(c.getTie()));
			if(Global.events[Global.EV_METSNAKE]==0)
			{
				SpeechBubble.talk("Well, there are theveral thtores around the land, and each one thells different/producths. Each product hath a dethcription written on a thign, tho you don't have to deal with my/thtupid lithp...You can buy my thtuff with the Dollarth that enemieth drop! Come and talk to/me when you want to purchathe a product!");
			}
		}
		if(Global.talking == 10)
		{
			Global.talking++;
			Global.talkingTo = this;
			Global.canMove = false;
			this.currentAnimation = Sprites.snakeIdle();
			c.setAnimation(Sprites.carlTalk(c.getTie()));
			if(Global.events[Global.EV_METSNAKE]==0)
			{
				SpeechBubble.talk("Oh, OK, cool!");
			}
		}
		if(Global.talking == 12)
		{
			Global.talking = 0;
			Global.talkingTo = null;
			Global.canMove = true;
			c.setAnimationLock(false);
			Global.events[Global.EV_METSNAKE] = 1;
		}
	}

	
}
