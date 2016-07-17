package es.projectalpha.ac.utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlocksUtils {

	public static ArrayList<Block> getBlocks(Block start, int radius){
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++) {
			for (double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++) {
				for (double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++) {
					Location loc = new Location(start.getWorld(), x, y, z);
					blocks.add(loc.getBlock());
				}
			}
		}
		return blocks;
	}

	public static void setLights(Location l){
		for (Block b : getBlocks(l.getBlock(), 10)) {
			while (b.getType() == Material.REDSTONE_LAMP_OFF) {
				b.setType(Material.REDSTONE_LAMP_ON);
				setLights(l);
			}
			b.setType(Material.REDSTONE_LAMP_OFF);
		}
	}
}
