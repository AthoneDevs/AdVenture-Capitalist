package com.gmail.cadox8.screen;

public class Image {

	private int x, y;
	private java.awt.Image image;
	
	public Image(int x, int y, java.awt.Image image) {
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public java.awt.Image getImage() {
		return image;
	}
}