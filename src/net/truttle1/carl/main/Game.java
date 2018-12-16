package net.truttle1.carl.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.truttle1.carl.main.KeyboardInput;
import net.truttle1.carl.overworld.OverworldMode;
import net.truttle1.carl.main.ModeType;
import net.truttle1.carl.main.Global;

public final class Game extends Canvas implements Runnable
{
	private Thread display;
	private static final long serialVersionUID = 7953333723971652915L;
	public final static int WIDTH = 1138;
	public final static int HEIGHT = 640;
	private boolean running = false;
	private OverworldMode overworldMode;
	private ModeType mode;
	private GraphicsLoader graphicsLoader;
	private ImageLoader imageLoader;
	private JLabel lbl = new JLabel();
	@Override
	public void run() 
	{
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 24;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		int FPS = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
				render();
				try
				{

				}
				catch(Exception e){e.printStackTrace();}
			}
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("UPDATES: " + FPS + " FPS: " + updates);
				FPS = frames;
				frames = 0;
				updates = 0;
			}
		}
	}
	public void tick()
	{
		Fade.tick();
		if(mode == ModeType.Overworld)
		{
			if(overworldMode == null)
			{
				overworldMode = new OverworldMode(this);
			}
			overworldMode.tick();
		}
		if(mode == ModeType.Loading)
		{
			if(graphicsLoader.getFinished())
			{
				mode = ModeType.Overworld;
			}
		}

		Global.zPressed = false;
		Global.xPressed = false;
		Global.cPressed = false;
		Global.vPressed = false;
		Global.upPressed = false;
		Global.downPressed = false;
		Global.leftPressed = false;
		Global.rightPressed = false;
		
		Global.zReleased = false;
		Global.xReleased = false;
		Global.cReleased = false;
		Global.vReleased = false;
		Global.upReleased = false;
		Global.downReleased = false;
		Global.leftReleased = false;
		Global.rightReleased = false;
	}
	public void render()
	{

		if(Global.OS.contains("Linux"))
		{
			Toolkit.getDefaultToolkit().sync();
		}
		BufferStrategy bs = null;
		try
		{
		bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		}
		catch(Exception e) {System.out.println("THERE IS PROBLEM!!!");return;}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g = bs.getDrawGraphics();
		
		if(mode == ModeType.Overworld && overworldMode != null)
		{
			overworldMode.render(g);
		}
		if(mode == ModeType.Loading)
		{
			g.setColor(Color.black);
			g.setFont(Global.BIG_FONT);
			g.drawString("Loading...", 64, 64);
		}
		Fade.render(g);
		g.dispose();
		bs.show();
	}
	public ImageLoader getImageLoad()
	{
		return imageLoader;
	}
	public GraphicsLoader getGraphicsLoad()
	{
		return graphicsLoader;
	}
	public ModeType getMode()
	{
		return mode;
	}
	private void init()
	{
		imageLoader = new ImageLoader();
		graphicsLoader = new GraphicsLoader(this);
		graphicsLoader.start();
		this.addKeyListener(new KeyboardInput());
		running = true;
	}
	public void load()
	{

		Global.disableMovement = false;
		Global.talking = 0;
		Global.talkingTo = null;
	}
	
	public static void main(String[] args) throws IOException
	{
		JFrame frame = new JFrame("Carl the Caiman: The Ten Dollar Bill of Glory");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game window = new Game();
		frame.add(window);
		frame.setResizable(false);
		frame.pack();
		frame.setFocusable(true);
		frame.setVisible(true);
	}

	public Game() throws IOException
	{
		setBackground(Color.white);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		mode = ModeType.Loading;
		if(Global.OS.toLowerCase().contains("mac"))
		{
			Runtime.getRuntime().exec("defaults write -g ApplePressAndHoldEnabled -bool false");
		}
		Runtime.getRuntime().addShutdownHook(new Thread()
				{
					@Override
					public void run()
					{
						System.out.println("BYE");

						if(Global.OS.toLowerCase().contains("mac"))
						{
							try {
								Runtime.getRuntime().exec("defaults write -g ApplePressAndHoldEnabled -bool true");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				});
	}
	public OverworldMode getOverworldMode()
	{
		return overworldMode;
	}

	public void addNotify()
	{
		super.addNotify();
		start();
	}

	private void start()
	{

		if(display == null)
		{
			display = new Thread(this);
			display.start();
		}
	}
}
