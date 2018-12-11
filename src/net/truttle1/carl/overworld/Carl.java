package net.truttle1.carl.overworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import net.truttle1.carl.blocks.Grass;
import net.truttle1.carl.blocks.Water;
import net.truttle1.carl.main.AudioHandler;
import net.truttle1.carl.main.Game;
import net.truttle1.carl.main.GameObject;
import net.truttle1.carl.main.Global;
import net.truttle1.carl.main.ObjectId;

public final class Carl extends GameObject{

	private double hVelocity;
	private double vVelocity;
	private boolean tie = true;
	private OverworldMode om;
	private boolean skidding;
	private boolean onGround;
	private boolean blockedLeft;
	private boolean blockedRight;
	private boolean landing;
	private boolean facingLeft = false;
	private boolean swimming;
	private boolean attacking;
	
	private boolean hit = false;
	private boolean dead = false;
	private int hitTimer;
	
	private static final int W_BITE = 1;
	private int weapon = 0;

	public Carl(Game window, int x, int y) {
		super(window);
		this.x = x;
		this.y = y;
		this.currentAnimation = Sprites.carlIdle(tie);
		this.id = ObjectId.Player;
		this.flipped = true;
	}
	public Carl(Game window, int x, int y, boolean tie) {
		super(window);
		this.x = x;
		this.y = y;
		this.currentAnimation = Sprites.carlIdle(tie);
		this.id = ObjectId.Player;
		this.flipped = true;
		this.tie = tie;
	}

	@Override
	public void tick() {
		if(om == null)
		{
			this.om = window.getOverworldMode();
		}
		determineSwimming();
		moveCamera();
		if(dead)
		{
			dead();
		}
		else
		{
			move();
		}
		collideWithGround();
		if(!dead)
		{
			attack();
		}
		if(hit)
		{
			hitTimer++;
			if(hitTimer>12 && !dead)
			{
				hitTimer = 0;
				hit = false;
			}
		}
	}

	private void dead()
	{
		if(this.getFrame(0)<=2)
		{
			AudioHandler.stopMusic();
		}
		this.currentAnimation = Sprites.carlDie(tie);
		if(this.getFrame(0)>=17)
		{
			this.setFrame(0, 17);
		}
		if(hitTimer>24)
		{
			om.resetToCheckpoint();
		}
	}
	public boolean getHit()
	{
		return hit;
	}
	public void enemyCollision(GameObject enemy)
	{
		if(!hit)
		{
			System.out.println(!(enemy.getHVelocity()>0 && this.hVelocity>0));
			System.out.println(!(enemy.getHVelocity()<0 && this.hVelocity<0));
			if(!(enemy.getHVelocity()>0 && this.hVelocity>0) && !(enemy.getHVelocity()<0 && this.hVelocity<0))
			{
				enemy.turnAround();
			}
			if(this.hVelocity==0)
			{
				this.hVelocity = -enemy.getHVelocity();
			}
			else if(Math.abs(this.hVelocity)<8)
			{
				this.hVelocity = -hVelocity*2;
			}
			else
			{
				this.hVelocity = -hVelocity;
			}
			this.currentAnimation = Sprites.carlHit(tie);
			if(tie)
			{
				this.tie = false;
			}
			else if(!dead)
			{

				setFrame(0,0);
				this.dead = true;
			}
		}
		if(this.hitTimer==11)
		{
			this.hitTimer = 0;
		}
		hit = true;
	}
	private void attack()
	{
		if(Global.xPressed && !attacking && vVelocity<=0)
		{
			//this.hVelocity = 0;
			this.setFrame(0, 0);
			this.attacking = true;

		}
		if(Global.leftPressed || Global.rightPressed || Global.zPressed)
		{
			this.attacking = false;
		}
		if(attacking)
		{
			if(hVelocity>0)
			{
				hVelocity -= 1;
			}
			if(hVelocity<0)
			{
				hVelocity += 1;
			}
			this.currentAnimation = Sprites.carlBite(tie);
			if(this.getFrame(0)>=11)
			{
				attacking = false;
			}
		}
	}
	private void determineSwimming()
	{

		boolean touchingWater = false;
		for(int i=0; i<om.getObjects().size();i++)
		{
			if(om.getObject(i).getId() == ObjectId.Water)
			{
				Water g = (Water)om.getObject(i);
				if(g.getBounds().intersects(getTop()))
				{
					if(!swimming)
					{
						vVelocity = 2;
					}
					swimming = true;
					touchingWater = true;
				}
			}
		}
		if(!touchingWater)
		{
			if(swimming == true && Global.zDown && vVelocity<0)
			{
				vVelocity = -60;
				landing = false;
			}
			swimming = false;
		}
	}
	private void moveCamera()
	{
		if(x>400 && x<Global.currentRoom.getWidth()-735)
		{
			om.setTx(x-400);
		}
		else if(x<400)
		{
			om.setTx(0);
		}
		else if(x>Global.currentRoom.getWidth()-Game.WIDTH/2)
		{
			om.setTx(Global.currentRoom.getWidth()-Game.WIDTH);
		}

		if(y<=Global.currentRoom.getHeight()-200)
		{
			window.getOverworldMode().setTy(y-200);
		}
		if(window.getOverworldMode().getTy()+668>Global.currentRoom.getHeight())
		{
			window.getOverworldMode().setTy(Global.currentRoom.getHeight()-668);
		}
		if(y<175)
		{
			window.getOverworldMode().setTy(-25);
		}
	}
	private void move()
	{
		x+=hVelocity;
		y+=vVelocity;
		if(Global.leftDown && !blockedLeft && !attacking && !hit)
		{
			if(!facingLeft && hVelocity != 0)
			{
				skidding = true;
			}
			if(hVelocity>-14)
			{
				facingLeft = true;
				hVelocity-=2;
				this.flipped = false;
				if(skidding)
				{
					hVelocity -= 1;
				}
				blockedRight = false;
			}
		}
		else if(Global.rightDown && !blockedRight && !attacking && !hit)
		{
			if(facingLeft && hVelocity != 0)
			{
				skidding = true;
			}
			if(hVelocity<14)
			{
				facingLeft = false;
				hVelocity+=2;
				this.flipped = true;
				if(skidding)
				{
					hVelocity += 1;
				}
			}
			blockedLeft = false;
		}
		else if((!Global.leftDown && !Global.rightDown)||hit)
		{
			if(hVelocity>0)
			{
				hVelocity-=1;
			}
			else if(hVelocity<0)
			{
				hVelocity+=1;
			}
			if(hVelocity == 1 || hVelocity == -1)
			{
				hVelocity = 0;
			}
		}

		if(Global.zPressed && onGround && !swimming)
		{
			vVelocity = -50;
			y += -60;
			onGround = false;
		}
		if(Global.zPressed && swimming)
		{
			vVelocity = -10;
			y += -30;
			onGround = false;
		}
		if(Global.zReleased && !swimming)
		{
			landing = true;
		}
		if(landing && vVelocity<0 && !swimming)
		{
			vVelocity /= 1.5;
		}
		if(swimming && !onGround)
		{
			if((this.getFrame(0)>12) || (Math.abs(hVelocity) <= 3 && vVelocity >= 0))
			{
				this.setFrame(0,0);
			}
			this.currentAnimation = Sprites.carlSwim(tie);
		}
		else if(!onGround && vVelocity>0)
		{
			this.currentAnimation = Sprites.carlJump(tie);
			this.setFrame(0, 1);
		}
		else if(!onGround && vVelocity<=0)
		{
			this.currentAnimation = Sprites.carlJump(tie);
			this.setFrame(0, 0);
		}
		else if(onGround)
		{
			if(skidding && ((hVelocity>-6 && Global.leftDown) ||(hVelocity<6 && Global.rightDown)))
			{
				this.currentAnimation = Sprites.carlSkid(tie);
			}
			else if((Math.abs(hVelocity) > 6 || !skidding) && hVelocity!=0)
			{
				this.currentAnimation = Sprites.carlRun(tie);
				skidding = false;
			}
			else
			{
				this.currentAnimation = Sprites.carlIdle(tie);
				skidding = false;
			}
		}
		if(hit)
		{
			this.currentAnimation = Sprites.carlHit(tie);
		}
		if(vVelocity != 0)
		{
			blockedLeft = false;
			blockedRight = false;
		}
		onGround = false;
		if(swimming)
		{
			vVelocity += .5;
		}
		else
		{
			vVelocity += 5;
		}
	}
	private void collideWithGround()
	{
		for(int i=0; i<om.getObjects().size();i++)
		{
			if(om.getObject(i).getId() == ObjectId.Ground)
			{
				Grass g = (Grass)om.getObject(i);
				if(g.getBounds().intersects(getBottom()))
				{
					this.y = g.getY()-175;
					if(this.vVelocity>0)
					{
						this.vVelocity = 0;
						onGround = true;
						landing = false;
					}
				}
				if(g.getBounds().intersects(getTop()) && !g.getGoThroughable())
				{
					this.vVelocity = 0;
					this.y += 30;
				}
				else if(vVelocity<80 && g.getBounds().intersects(getLeft()) || g.getBounds().intersects(getRight()))
				{
					if(g.getBounds().intersects(getLeft()) && this.hVelocity<0)
					{
						blockedLeft = true;
						this.x = g.getX()+(g.getBounds().width-24);
						this.hVelocity = 0;
					}
					if(g.getBounds().intersects(getRight()) && this.hVelocity>0)
					{
						blockedRight = true;
						this.x = g.getX()-130;
						this.hVelocity = 0;
					}
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		if(!hit || dead || hitTimer%6<3)
		{
			if(flipped)
			{
				this.animate(x, y, currentAnimation, 0, g);
			}
			else
			{
				this.animate(x-40, y, currentAnimation, 0, g);
			}
		}
		
		g.setColor(Color.red);
		//g.drawRect(attackBounds().x,attackBounds().y,attackBounds().width,attackBounds().height);
		
		/*
		g.drawRect(getBottom().x,getBottom().y,getBottom().width,getBottom().height);
		g.drawRect(getTop().x,getTop().y,getTop().width,getTop().height);
		g.drawRect(getLeft().x,getLeft().y,getLeft().width,getLeft().height);
		g.drawRect(getRight().x,getRight().y,getRight().width,getRight().height);
		*/
		
		
	}

	@Override
	public Rectangle getBounds() {
		if(flipped)
		{
			return new Rectangle(x+18,y+8,140,190);
		}
		else
		{
			return new Rectangle(x,y+8,140,190);
		}
	}

	public Rectangle getBottom()
	{
		return new Rectangle(x+32,y+154,94,42);
	}
	public Rectangle getTop()
	{
		return new Rectangle(x+32,y+8,94,32);
	}
	public Rectangle getLeft()
	{
		return new Rectangle(x+18,y+40,40,114);
	}
	public Rectangle getRight()
	{
		return new Rectangle(x+100,y+40,40,114);
	}
	public int getAttack()
	{
		int attack = 0;
		if(attacking && this.getFrame(0)>=6)
		{
			attack = 1;
		}
		if(attacking && this.getFrame(0)<6)
		{
			attack = 9999;
		}
		return attack;
	}
	public void setTie(boolean tie)
	{
		this.tie = tie;
	}
	public Rectangle attackBounds()
	{
		if(getAttack()<=1 || getAttack()>1000)
		{
			if(flipped)
			{
				return new Rectangle(x+110,y+40,70,50);
			}
			else
			{
				return new Rectangle(x-20,y+40,70,50);
			}
		}
		else
		{
			return new Rectangle(x+100,y,50,50);
		}
	}
}
