package com.gmail.cadox8.screen.data;

public class Pixel {

	private int x, y;
	private byte color;
	
	public Pixel(int x, int y, byte color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public byte getColor() {
		return color;
	}
}