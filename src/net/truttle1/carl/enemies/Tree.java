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

public class Tree extends GameObject{

	private boolean dead;
	private int deathTimer;
	private boolean finallyDead = false;
	private boolean carlHasTie = false;
	private int counter = 0;
	public Tree(Game window, int x, int y) {
		super(window);
		//Sets up the enemy's location, animation, id, and speed.
		this.x = x*100;
		this.y = y*100;
		this.currentAnimation = Sprites.tree();
		this.id = ObjectId.Monster;
		hVelocity = 0;
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
		if(!dead)
		{
			//Shooting
			counter++;
			if(counter >= 48)
			{
				this.setFrame(0, 0);
				this.setAnimation(Sprites.treeShoot());
				counter = 0;
			}
			if(this.currentAnimation.equals(Sprites.treeShoot()) && this.getFrame(0) == 12)
			{
				if(flipped)
				{
					window.getOverworldMode().addObject(new Projectile(window, this.x+200, this.y+48, 14, 0, Sprites.projectile()));
				}
				else
				{
					window.getOverworldMode().addObject(new Projectile(window, this.x, this.y+48, -14, 0, Sprites.projectile()));
				}
			}
			if(this.currentAnimation.equals(Sprites.treeShoot()) && this.getFrame(0)>=23)
			{
				this.currentAnimation = Sprites.tree();
				counter = 0;
			}
			//Look through all the objects
			for(int i=0; i < window.getOverworldMode().getObjects().size();i++)
			{
				GameObject tempObject = window.getOverworldMode().getObject(i);
				//Player related things
				if(tempObject.getId() == ObjectId.Player && !dead)
				{
					playerCollision((Carl)tempObject);
					if(tempObject != null && tempObject.getX()>this.x)
					{
						this.flipped = true;
					}
					else
					{
						this.flipped = false;
					}
				}
			}
			//Walk if you are not marked as newly dead
			if(!dead)
			{
				x += hVelocity;
				y += vVelocity;
			}
		}
		
		//Newly dead? Here's how much time you have left until you are ACTUALLY dead.
		if(dead)
		{
			deathTimer++;
			if(deathTimer==1)
			{
				Global.score += 200;
				window.getOverworldMode().addObject(new Money(window,this.x+30,this.y));
			}
			if(deathTimer>6)
			{
				this.finallyDead = true;
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
	public Rectangle getBoundsDamage() {
		return new Rectangle(this.x,this.y,185,150);
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
		if(this.getBoundsDamage().intersects(carl.attackBounds()) && carl.getAttack()>0 && carl.getAttack()<9999 && carl.getAttackDamage()>0)
		{
			this.carlHasTie = carl.getTie();
			die();
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
