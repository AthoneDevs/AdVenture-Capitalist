package com.gmail.cadox8.screen.data;

import org.bukkit.map.MapFont;

public class Text {

	private int x, y;
	private MapFont font;
	private String text;
	
	public Text(int x, int y, MapFont font, String text) {
		this.x = x;
		this.y = y;
		this.font = font;
		this.text = text;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public MapFont getFont() {
		return font;
	}
	
	public String getText() {
		return text;
	}
}