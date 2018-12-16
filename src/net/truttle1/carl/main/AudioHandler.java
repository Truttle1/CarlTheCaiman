package net.truttle1.carl.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioHandler {
	
	public static int clipTime;
	
	public static final File TROPICAL_THEME  = new File("res/music/tropical.wav");
	private static File currentMusic = null;

	public static File getMusic()
	{
		return currentMusic;
	}
	public static void setMusic(File cm)
	{
		currentMusic = cm;
	}
	public static Clip[] speechClips = new Clip[10];
	public static void init()
	{
		/*
		for(int i=0;i<10;i++)
		{
			try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(talk0);
				speechClips[i] = AudioSystem.getClip();
				speechClips[i].open(audioIn);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	private static ArrayList<Clip> effectsOld = new ArrayList<Clip>();
	private static ArrayList<Clip> effects = new ArrayList<Clip>();
	private static ArrayList<File> effectFiles = new ArrayList<File>();
	private static Clip music;
	

	public static void stopMusic()
	{
		music.stop();
		currentMusic = null;
	}

	public static void playMusicAtPosition(File sound, long pos)
	{
		if(currentMusic != sound)
			{
			try {
				try
				{
				music.close();
				}
				catch(Exception e) {System.out.println("There is no music. Playing some now.");}
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
				music = AudioSystem.getClip();
				music.open(audioIn);
				music.setMicrosecondPosition(pos);
				music.start();
				music.setMicrosecondPosition(pos);
				music.loop(Clip.LOOP_CONTINUOUSLY);
				currentMusic = sound;
				} catch (UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	public static void playMusic(File sound)
	{
		if(currentMusic != sound)
			{
			try {
				try
				{
				music.close();
				}
				catch(Exception e) {System.out.println("There is no music. Playing some now.");}
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
				music = AudioSystem.getClip();
				music.open(audioIn);
				music.start();
				music.loop(Clip.LOOP_CONTINUOUSLY);
				currentMusic = sound;
				} catch (UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	public static void playSound(File sound)
	{
		try {
			/*
			for(int i=0; i<effects.size(); i++)
			{
				Clip e = effects.get(i);
				if(!e.isRunning())
				{
					e.close();
				}
			}*/
			if(effectFiles.contains(sound))
			{
				for(int i=0; i<effectFiles.size();i++)
				{
					if(effectFiles.get(i).equals(sound))
					{
						effects.get(i).setFramePosition(0);
						effects.get(i).start();
					}
				}
			}
			else
			{
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
				Clip c = AudioSystem.getClip();
				c.open(audioIn);
				c.setMicrosecondPosition(0);
				c.start();
				effects.add(c);
				effectFiles.add(sound);
			}
			if(effects.size() != effectFiles.size())
			{
				System.out.println("AUDIO WARNING!");
				effects.clear();
				effectFiles.clear();
			}
			
			//effects.add(c);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
	public static void playSoundSpeech(int soundToPlay)
	{
		speechClips[soundToPlay%10].setMicrosecondPosition(0);
		speechClips[soundToPlay%10].start();
	}
	public static void playSoundOld(File sound)
	{
		try {
			for(int i=0; i<effectsOld.size(); i++)
			{
				Clip e = effectsOld.get(i);
				if(!e.isRunning())
				{
					e.close();
				}
			}
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
			Clip c = AudioSystem.getClip();
			c.open(audioIn);
			c.setMicrosecondPosition(0);
			c.start();
			effectsOld.add(c);
			
			//effects.add(c);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
	public static void stopSoundEffects()
	{
		for(int i=0; i<effects.size();i++)
		{
			effects.get(i).setMicrosecondPosition(99999999);
		}
	}
}
