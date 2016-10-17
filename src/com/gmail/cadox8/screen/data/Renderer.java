package com.gmail.cadox8.screen.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class Renderer extends MapRenderer {
	
	private List<Pixel> pixelsToDraw = new ArrayList<>();

	@Override
	public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
		for (Pixel pixel : pixelsToDraw)
			mapCanvas.setPixel(pixel.getX(), pixel.getY(), pixel.getColor());
		
		pixelsToDraw.clear();

	}
	
	public void setPixel(int x, int y, byte color) {
		pixelsToDraw.add(new Pixel(x, y, color));
	}

}