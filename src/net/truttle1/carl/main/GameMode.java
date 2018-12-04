package net.truttle1.carl.main;

import java.awt.Graphics;

public abstract class GameMode {
	private Game window;
	public Game getGame()
	{
		return window;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public GameMode(Game window)
	{
		this.window = window;
	}
}
