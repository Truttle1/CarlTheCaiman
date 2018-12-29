package net.truttle1.carl.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import net.truttle1.carl.main.EyeCandy;
import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;
import net.truttle1.carl.overworld.Carl;
import net.truttle1.carl.overworld.Money;
import net.truttle1.carl.overworld.Sprites;
import net.truttle1.carl.overworld.Tie;

public class Crab extends GameObject{

	private boolean dead;
	private int deathTimer;
	private boolean finallyDead = false;
	private boolean carlHasTie = false;
	public Crab(Game window, int x, int y) {
		super(window);
		//Sets up the enemy's location, animation, id, and speed.
		this.x = x*100;
		this.y = y*100;
		this.currentAnimation = Sprites.crab();
		this.id = ObjectId.Monster;
		hVelocity = 12;
	}

	@Override
	public void tick() {

		if(!finallyDead)
		{
			runWhileAlive();	//Runs when monster is alive.
		}
		else
		{
			runWhileDead();		//Runs when monster is dead.
		}
	}

	@Override
	public void render(Graphics g) {
		if(!finallyDead)	//If the monster is NOT dead
		{
			this.animate(x, y, currentAnimation, 0, g);		//Animation!
		}
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.YELLOW);
		/*
		g2d.draw(getLeft());
		g2d.draw(getRight());
		g2d.draw(getBottom());
		g2d.draw(getBounds());
		*/
	}

	private void runWhileDead()
	{
		//If the player is far away enough, the enemy is marked as no longer being dead.
		if(window.getOverworldMode().getTx()>this.x+1500 || window.getOverworldMode().getTx()<this.x-1500)
		{
			finallyDead = false;
			dead = false;
			deathTimer = 0;
		}
	}
	private void runWhileAlive()
	{
		//Determines whether or not the sprite is flipped.
		if(hVelocity<0)
		{
			this.flipped = false;
		}
		else
		{
			this.flipped = true;
		}
		//Gravity
		vVelocity += 8;
		boolean touchingLeft = false;
		boolean touchingRight = false;
		//Look through all the objects
		if(Math.abs(this.vVelocity)>20)
		{
			touchingLeft = true;
			touchingRight = true;
		}
		for(int i=0; i < window.getOverworldMode().getObjects().size();i++)
		{
			GameObject tempObject = window.getOverworldMode().getObject(i);
			//Found a ground
			if(tempObject.getId() == ObjectId.Ground)
			{
				//Turn if there is a crash
				if(tempObject.getBounds().intersects(this.getBounds()) && Math.abs(this.vVelocity)<10)
				{
					hVelocity *= -1;
					x += hVelocity*2;
				}
				//Notice if there is a block on the ground to the left of the enemy
				if(tempObject.getBounds().intersects(this.getLeft())&& Math.abs(this.vVelocity)<10)
				{
					touchingLeft = true;
				}
				//Notice if there is a block on the ground to the right of the enemy
				if(tempObject.getBounds().intersects(this.getRight())&& Math.abs(this.vVelocity)<10)
				{
					touchingRight = true;
				}
				//Stop falling if on ground
				if(tempObject.getBounds().intersects(this.getBottom()))
				{
					vVelocity = 0;
					this.y = tempObject.getY()-130;
				}
				
			}
			//Player related things
			if(tempObject.getId() == ObjectId.Player && !dead)
			{
				playerCollision((Carl)tempObject);
				Carl carl = (Carl)tempObject;
				if((carl.getX()>this.x && this.hVelocity>0) || (carl.getX()<this.x && this.hVelocity<0))
				{
					if(this.vVelocity == 0 && ((this.hVelocity > 0 && this.getDistanceTo(carl)>175 && this.getDistanceTo(carl)<310)||(this.hVelocity < 0 && this.getDistanceTo(carl)>230 && this.getDistanceTo(carl)<310)))
					{
						System.out.println(this.getDistanceTo(carl));
						this.vVelocity = -50;
						this.y -= 50;
						touchingRight = false;
						touchingLeft = false;
					}
				}
			}
		}
		//Move left if there is no ground to your right
		if(!touchingRight && vVelocity == 0)
		{
			hVelocity = -9;
		}
		//Move right if there is no ground to your left
		if(!touchingLeft && vVelocity == 0)
		{
			hVelocity = 9;
		}
		//Walk if you are not marked as newly dead
		if(!dead)
		{
			x += hVelocity;
			y += vVelocity;
		}
		//Newly dead? Here's how much time you have left until you are ACTUALLY dead.
		if(dead)
		{
			deathTimer++;
			if(deathTimer == 1)
			{
				window.getOverworldMode().addObject(new Money(window,this.x+50,this.y));
			}
			if(deathTimer>6)
			{
				this.finallyDead = true;
				Global.score += 100;
				if(!carlHasTie && Math.random()<.25)
				{
					window.getOverworldMode().addObject(new Tie(window,this.x+30,this.y));
				}
				else
				{
					window.getOverworldMode().addObject(new Money(window,this.x+30,this.y));
				}
				
			}
		}
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x+30,this.y+20,95,110);
	}
	@Override
	public Rectangle getLeft()
	{
		return new Rectangle(this.x+10,this.y,25,175);
	}
	@Override
	public Rectangle getRight()
	{
		return new Rectangle(this.x+120,this.y,25,175);
	}
	@Override
	public Rectangle getBottom()
	{
		return new Rectangle(this.x+40,this.y+115,80,25);
	}
	private void playerCollision(Carl carl)
	{
		//Die if the player attacks
		if(this.getBounds().intersects(carl.attackBounds()) && carl.getAttack()>0 && carl.getAttack()<9999)
		{
			die();
			this.carlHasTie = carl.getTie();
		}
		else if(this.getBounds().intersects(carl.getBounds()) && !dead && carl.getAttack()<9999)
		{
			//Player takes damage when he collides with an enemy.
			//How this damage is calculated is the player's problem.
			carl.enemyCollision(this);
		}
	}
	private void die()
	{
		window.getOverworldMode().addObject(new EyeCandy(window,x-50,y-50,Sprites.poof()));
		dead = true;
		
	}
}
