package net.truttle1.carl.overworld;

public class RoomAdditions {

	public static void setupRoom(Room room)
	{
		if(room.getId() == 0)
		{
			room.addObject(new Warp(room.getGame(), 12700, 2200, 200, 2000, OverworldMode.beach2, false));
		}
		if(room.getId() == 1)
		{
			room.addObject(new Warp(room.getGame(), 0, 2000, 12500, 2200, OverworldMode.beach1, true));
		}
	}
}
