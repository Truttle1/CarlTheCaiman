package net.truttle1.carl.main;

import java.awt.Font;

import net.truttle1.carl.overworld.Room;

public class Global {

	public static boolean save;
	public static boolean load;
	public static boolean zDown;
	public static boolean zPressed;
	public static boolean xDown;
	public static boolean xPressed;
	public static boolean cDown;
	public static boolean cPressed;
	public static boolean vDown;
	public static boolean vPressed;

	public static boolean upDown;
	public static boolean upPressed;
	public static boolean downDown;
	public static boolean downPressed;
	public static boolean leftDown;
	public static boolean leftPressed;
	public static boolean rightDown;
	public static boolean rightPressed;

	public static boolean zReleased;
	public static boolean xReleased;
	public static boolean cReleased;
	public static boolean vReleased;
	public static boolean upReleased;
	public static boolean downReleased;
	public static boolean rightReleased;
	public static boolean leftReleased;
	
	public static boolean zNewPressed;
	public static boolean xNewPressed;
	public static boolean cNewPressed;
	public static boolean vNewPressed;
	public static boolean upNewPressed;
	public static boolean downNewPressed;
	public static boolean rightNewPressed;
	public static boolean leftNewPressed;
	
	public static boolean disableMovement;
	public static boolean doneLoading;
	
	public static int talking = 0;
	public static GameObject talkingTo;
	public static final int FRAME_PER_IMG = 1;
	
	public static Room currentRoom;

	public static final String OS = System.getProperty("os.name");
	public static final Font TEXT_FONT = new Font("Corbel", Font.PLAIN, 24);
	public static final Font BIG_FONT = new Font("Corbel", Font.BOLD, 72);

	public static int checkpointX = 200;
	public static int checkpointY = 2500;
	public static int checkpointRoomId;
	public static Room checkpointRoom;
	public static int currentCheckpointId;

}
