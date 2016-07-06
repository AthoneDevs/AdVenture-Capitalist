package es.projectalpha.ac.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class LocationUtils {

	private static ArrayList<Block> blocks = new ArrayList<Block>();
	private static HashMap<Material, Location> villagerLocs = new HashMap<Material, Location>();

	//First
	public static void searchBlocks(Location l, int area){
		blocks.clear();

		if (area < 40) {
			return;
		}

		for (int x = -area; x < area; x++) {
			for (int z = area; z < -area; z--) {
				blocks.add(l.add(x, 0, z).getBlock());
			}
		}
		getLocation();
	}

	//Second
	public static Location getVillagerLocs(Material mat){
		return villagerLocs.get(mat);
	}

	private static void getLocation(){
		villagerLocs.clear();

		for (Block b : blocks) {
			villagerLocs.put(b.getType(), b.getLocation());
		}
	}
}
