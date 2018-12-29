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
			room.addObject(new Warp(room.getGame(), 12700, 2000, 200, 6100, OverworldMode.beach3, false));
			room.addObject(new Warp(room.getGame(), 12700, 600, 200, 4600, OverworldMode.beach3, false));
		}
		if(room.getId() == 2)
		{
			room.addObject(new Warp(room.getGame(), 0, 6100, 12500, 2000, OverworldMode.beach2, true));
			room.addObject(new Warp(room.getGame(), 0, 4600, 12500, 600, OverworldMode.beach2, true));
			room.addObject(new Warp(room.getGame(), 0, 600, 12500, 2100, OverworldMode.beach7, true));
			room.addObject(new Warp(room.getGame(), 12700, 5600, 200, 2100, OverworldMode.turtleIsland, false));
		}
		if(room.getId() == 3)
		{
			room.addObject(new NPC(room.getGame(), 1000, 2100,Sprites.turtleIdle(),Sprites.turtleTalk(),"Hello there! Welcome to Turtle Island!/Turtle Island is a town, meaning we have a save point and a Serpent Merchant!/There are many towns scattered around the world!"));
			room.addObject(new Warp(room.getGame(), 0, 2100, 12500, 5600, OverworldMode.beach3, true));
			room.addObject(new Warp(room.getGame(), 4700, 2100, 200, 2100, OverworldMode.beach4, false));
		}
		if(room.getId() == 4)
		{
			room.addObject(new Warp(room.getGame(), 0, 2100, 4500, 2100, OverworldMode.turtleIsland, true));
			room.addObject(new Warp(room.getGame(), 13300, 1700, 200, 1700, OverworldMode.beach5, false));
		}
		if(room.getId() == 5)
		{
			room.addObject(new Warp(room.getGame(), 0, 1700, 13100, 1700, OverworldMode.beach4, true));
			room.addObject(new Warp(room.getGame(), 12100, 1900, 200, 2700, OverworldMode.beach6, false));
		}
		if(room.getId() == 6)
		{
			room.addObject(new Warp(room.getGame(), 0, 2700, 11900, 1900, OverworldMode.beach5, true));
		}
		if(room.getId() == 7)
		{
			room.addObject(new Warp(room.getGame(), 12700, 2100, 200, 600, OverworldMode.beach3, false));
			room.addObject(new Warp(room.getGame(), 0, 1900, 6100, 2000, OverworldMode.drakon, true));
		}
		if(room.getId() == 8)
		{
			room.addObject(new NPC(room.getGame(), 3800, 1900,Sprites.dragonIdle(),Sprites.dragonTalk(),"This is Drakon City! We were once a prosperous, peaceful town of dragons, however, an evil dragonslayer/has ravaged the lands... Personally, I try to stay positive and believe the dragonslayer will be vanquished soon.../I believe good can triumph over evil!!! My name is Dante, by the way. I'm kinda the leader of Drakon City.../And no, I don't know why we wear shorts here..."));
			room.addObject(new NPC(room.getGame(), 2200, 1900,Sprites.dragon2Idle(),Sprites.dragon2Talk(),"The name's Blaze, and I'm TOTALLY MAD RIGHT NOW!!! That STUPID DRAGONSLAYER has killed and/probably eaten all of my best friends!!! There's only four of us left nowadays!!! It's barbaric, dude!!! I'm gonna/show that stupid dragonslayer with his stupid shiny armor who's boss!!! Out of everyone who has ever lived/in this town, nobody has been as tough or vengeful as me! Yeah, get on my bad side, and you will regret it!!!"));
			room.addObject(new NPC(room.getGame(), 1100, 1900,Sprites.dragon4Idle(),Sprites.dragon4Talk(),"Hey there. I'm the dragon imaginatively named Draco... I'm thinking about moving out of this little/hamlet soon. Sure, when it was more lively, there was something magical about living in a dragon community,/but everyone's all gone now... Turtle Island seems like a nice place to move to, I hear the beach is nice this/time of year... Then again, Marshall City has some pretty neat swamps there..."));
			room.addObject(new Warp(room.getGame(), 6300, 2000, 200, 1900, OverworldMode.beach7, false));
		}
	}
}
