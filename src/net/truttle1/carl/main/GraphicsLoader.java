package net.truttle1.carl.main;


import net.truttle1.carl.overworld.Sprites;

public class GraphicsLoader extends Thread implements Runnable{

	Game w;
	private volatile boolean finished = false;
	public GraphicsLoader(Game window)
	{
		w = window;
	}
	@Override
	public void run() {
		if(!Sprites.getLoaded())
		{
			Sprites.loadOverworldAnimations(w);
		}
		finished = true;
	}
	
	public boolean getFinished()
	{
		return finished;
	}
}
