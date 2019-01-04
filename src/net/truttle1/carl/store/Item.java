package net.truttle1.carl.store;

import java.awt.image.BufferedImage;

public class Item {
	private int price;
	private BufferedImage picture;
	private String flavorText;
	private String name;
	public Item(int price, BufferedImage picture,String name, String flavorText)
	{
		this.price = price;
		this.picture = picture;
		this.flavorText = flavorText;
		this.name = name;
	}
	public int getPrice() {return price;}
	public BufferedImage getPicture() {return picture;}
	public String getFlavorText() {return flavorText;}
	public String getName() {return name;}
	
}
