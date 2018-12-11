package net.truttle1.carl.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	protected Game window;
	protected int x;
	protected int y;

	protected int startX;
	protected int startY;
	protected int hVelocity;
	protected int vVelocity;
	protected boolean flipped = false;
	protected double[] currentFrame = new double[9];
	protected ObjectId id;
	protected BufferedImage[] currentAnimation;
	public GameObject(Game window)
	{
		this.window = window;
	}
	public int getHVelocity()
	{
		return hVelocity;
	}
	public int getVVelocity()
	{
		return vVelocity;
	}
	protected GameObject getPlayer()
	{
		for(int i = 0; i<window.getOverworldMode().getObjects().size();i++)
		{
			if(window.getOverworldMode().getObjects().get(i).id == ObjectId.Player)
			{
				return window.getOverworldMode().getObjects().get(i);
			}
		}
		return null;
	}
	//TICK - defines how the object behaves.
	public abstract void tick();
	//RENDER - draws the object to the screen using the graphics toolset.
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public Rectangle getTop() {return null;}
	public Rectangle getBottom() {return null;}
	public Rectangle getLeft() {return null;}
	public Rectangle getRight() {return null;}

	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public void setAnimation(BufferedImage[] animation)
	{
		this.currentAnimation = animation;
		currentAnimation = animation;
	}
	public BufferedImage[] getAnimation()
	{
		return currentAnimation;
	}
	protected void animate(int x, int y,BufferedImage[] animation, int cFrame, Graphics g)
	{
		int tx = window.getOverworldMode().getTx();
		int ty = window.getOverworldMode().getTy();
		int roundedFrame = (int)(currentFrame[cFrame]/Global.FRAME_PER_IMG)*Global.FRAME_PER_IMG;
		if(loadFrame(animation,roundedFrame) == null)
		{
			roundedFrame = 0;
			currentFrame[cFrame] = 0;
		}
		if(roundedFrame < 0) 
		{
			roundedFrame = 0;
		}
		if(window.getMode() != ModeType.Overworld || (x>tx-600 && x<tx+1400 && y>ty-600 && y<ty+800))
		{
			Graphics2D g2d = (Graphics2D) g;
			if(!flipped)
			{
				g2d.drawImage(loadFrame(animation,roundedFrame),x,y, null);
			}
			else
			{
				g2d.drawImage(loadFrame(animation,roundedFrame),x+loadFrame(animation,roundedFrame).getWidth(),y,-loadFrame(animation,roundedFrame).getWidth(),loadFrame(animation,roundedFrame).getHeight(), null);	
			}
		}

		currentFrame[cFrame]+=1;
		if(currentFrame[cFrame] > animation[2].getWidth())
		{
			currentFrame[cFrame] = 0;
		}
	}
	protected void animateBig(int x, int y,BufferedImage[] animation, int cFrame, Graphics g)
	{
		int tx = window.getOverworldMode().getTx();
		int ty = window.getOverworldMode().getTy();
		int roundedFrame = (int)(currentFrame[cFrame]/Global.FRAME_PER_IMG)*Global.FRAME_PER_IMG;
		if(window.getMode() != ModeType.Overworld || (x>tx-800 && x<tx+1400 && y>ty-800 && y<ty+600))
		{
			Graphics2D g2d = (Graphics2D) g;
			if(!flipped)
			{
				g2d.drawImage(loadFrame(animation,roundedFrame),x,y, null);
			}
			else
			{
				g2d.drawImage(loadFrame(animation,roundedFrame),x+loadFrame(animation,roundedFrame).getWidth(),y,-loadFrame(animation,roundedFrame).getWidth(),loadFrame(animation,roundedFrame).getHeight(), null);
			}
		}

		currentFrame[cFrame]+=1;
		if(currentFrame[cFrame] > animation[2].getWidth())
		{
			currentFrame[cFrame] = 0;
		}
	}
	protected void animateNoFlip(int x, int y,BufferedImage[] animation, int cFrame, Graphics g)
	{
		int tx = window.getOverworldMode().getTx();
		int ty = window.getOverworldMode().getTy();
		int roundedFrame = (int)(currentFrame[cFrame]/Global.FRAME_PER_IMG)*Global.FRAME_PER_IMG;
		if(window.getMode() != ModeType.Overworld || (x>tx-600 && x<tx+1400 && y>ty-600 && y<ty+800))
		{
			Graphics2D g2d = (Graphics2D) g;
	
			g2d.drawImage(loadFrame(animation,roundedFrame),x,y, null);
			currentFrame[cFrame]+=1;
			if(currentFrame[cFrame] > animation[2].getWidth())
			{
				currentFrame[cFrame] = 0;
			}
		}
	}
	protected void animateFlip(int x, int y,BufferedImage[] animation, int cFrame, Graphics g)
	{
		int tx = window.getOverworldMode().getTx();
		int ty = window.getOverworldMode().getTy();
		int roundedFrame = (int)(currentFrame[cFrame]/Global.FRAME_PER_IMG)*Global.FRAME_PER_IMG;
		if(window.getMode() != ModeType.Overworld || (x>tx-800 && x<tx+1400 && y>ty-600 && y<ty+800))
		{
			Graphics2D g2d = (Graphics2D) g;
	
			g2d.drawImage(loadFrame(animation,roundedFrame),x+loadFrame(animation,roundedFrame).getWidth(),y,-loadFrame(animation,roundedFrame).getWidth(),loadFrame(animation,roundedFrame).getHeight(), null);
			currentFrame[cFrame]+=1;
			if(currentFrame[cFrame] > animation[2].getWidth())
			{
				currentFrame[cFrame] = 0;
			}
		}
	}
	protected void animateAtSpeed(int x, int y,BufferedImage[] animation, int cFrame, Graphics g, double d)
	{
		Graphics2D g2d = (Graphics2D) g;
		int roundedFrame = (int)(currentFrame[cFrame]/Global.FRAME_PER_IMG)*Global.FRAME_PER_IMG;
		if(!flipped)
		{
			g2d.drawImage(loadFrame(animation,roundedFrame),x,y, null);
		}
		else
		{
			g2d.drawImage(loadFrame(animation,roundedFrame),x+loadFrame(animation,roundedFrame).getWidth(),y,-loadFrame(animation,roundedFrame).getWidth(),loadFrame(animation,roundedFrame).getHeight(), null);
		}
		currentFrame[cFrame]+=d;
		if(currentFrame[cFrame] > animation[2].getWidth())
		{
			currentFrame[cFrame] = 0;
		}
	}
	private BufferedImage loadFrame(BufferedImage[] animation, int frame)
	{
		int height = animation[1].getHeight();
		int width = animation[1].getWidth();
		int offset = (width * frame);
		try
		{
			 return animation[0].getSubimage(offset, 0, width, height);
		}
		catch(Exception e)
		{
			 return animation[0].getSubimage(0, 0, width, height);
		}
	}
	public int getFrame(int cFrame)
	{
		return (int) currentFrame[cFrame];
	}
	public void setFrame(int cFrame, double newFrame)
	{
		currentFrame[cFrame] = newFrame;
	}
	public int getAnimationLength(BufferedImage[] animation)
	{
		return animation[2].getWidth();
	}

	protected BufferedImage replaceColors(Color start, Color end, BufferedImage input)
	{
		BufferedImage img = input;
		for(int x=0; x<input.getWidth();x++)
		{
			for(int y=0; y<input.getHeight();y++)
			{
				if(img.getRGB(x, y) == start.getRGB())
				{
					img.setRGB(x, y, end.getRGB());
				}
			}	
		}
		return img;
	}
	public void turnAround()
	{
		this.hVelocity *= -1;
		this.flipped = !flipped;
	}
	protected BufferedImage replaceTwoColors(Color start, Color end,Color start2, Color end2, BufferedImage input)
	{
		BufferedImage img = input;
		for(int x=0; x<input.getWidth();x++)
		{
			for(int y=0; y<input.getHeight();y++)
			{
				if(img.getRGB(x, y) == start.getRGB())
				{
					img.setRGB(x, y, end.getRGB());
				}
				if(img.getRGB(x, y) == start2.getRGB())
				{
					img.setRGB(x, y, end2.getRGB());
				}
			}	
		}
		return img;
	}
	public ObjectId getId()
	{
		return id;
	}
	public boolean getFlipped() {return flipped;}
}
