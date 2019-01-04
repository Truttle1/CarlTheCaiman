package net.truttle1.carl.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.truttle1.carl.main.Global;
public class KeyboardInput implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
		if(key == KeyEvent.VK_S)
		{
			Global.save = true;
		}
		if(key == KeyEvent.VK_L)
		{
			Global.load = true;
		}
		if(key == KeyEvent.VK_Z && !Global.zDown)
		{
			Global.zDown = true;
			Global.zPressed = true;
		}
		if(key == KeyEvent.VK_D && !Global.dDown)
		{
			Global.dDown = true;
			Global.dPressed = true;
		}
		if(key == KeyEvent.VK_A && !Global.aDown)
		{
			Global.aDown = true;
			Global.aPressed = true;
		}
		if(key == KeyEvent.VK_X && !Global.xDown)
		{
			Global.xDown = true;
			Global.xPressed = true;
		}
		if(key == KeyEvent.VK_C && !Global.cDown)
		{
			Global.cDown = true;
			Global.cPressed = true;
		}
		if(key == KeyEvent.VK_V && !Global.vDown)
		{
			Global.vDown = true;
			Global.vPressed = true;
		}
		if(key == KeyEvent.VK_LEFT && !Global.leftDown)
		{
			Global.leftDown = true;
			Global.leftPressed = true;
		}
		if(key == KeyEvent.VK_RIGHT && !Global.rightDown)
		{
			Global.rightDown = true;
			Global.rightPressed = true;
		}
		if(key == KeyEvent.VK_UP && !Global.upDown)
		{
			Global.upDown = true;
			Global.upPressed = true;
		}
		if(key == KeyEvent.VK_DOWN && !Global.downDown)
		{
			Global.downDown = true;
			Global.downPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_Z)
		{
			Global.zDown = false;
			Global.zReleased = true;
		}
		if(key == KeyEvent.VK_X)
		{
			Global.xDown = false;
			Global.xReleased = true;
		}	
		if(key == KeyEvent.VK_C)
		{
			Global.cDown = false;
			Global.cReleased = true;
		}
		if(key == KeyEvent.VK_V)
		{
			Global.vDown = false;
			Global.vReleased = true;
		}
		if(key == KeyEvent.VK_LEFT)
		{
			Global.leftDown = false;
			Global.leftReleased = true;
		}
		if(key == KeyEvent.VK_RIGHT)
		{
			Global.rightDown = false;
			Global.rightReleased = true;
		}
		if(key == KeyEvent.VK_UP)
		{
			Global.upDown = false;
			Global.upReleased = true;
		}
		if(key == KeyEvent.VK_DOWN)
		{
			Global.downDown = false;
			Global.downReleased = true;
		}
		if(key == KeyEvent.VK_D)
		{
			Global.dDown = false;
		}
		if(key == KeyEvent.VK_A)
		{
			Global.aDown = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e.getKeyCode());
	}

}
