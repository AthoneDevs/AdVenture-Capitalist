package com.chasechocolate.portablebuildings.building;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.chasechocolate.portablebuildings.schematic.Schematic;
import com.chasechocolate.portablebuildings.schematic.SchematicUtils;
import com.chasechocolate.portablebuildings.utils.Utilities;

import es.projectalpha.ac.AVC;

public class BuildingUtils {
	private static List<Building> allBuildings = new ArrayList<Building>();

	private static HashMap<String, Building> playerBuildings = new HashMap<String, Building>();

	public static void initBuildings(){
		allBuildings.clear();

		for (Schematic schematic : SchematicUtils.getAllSchematics()) {
			Building building = new Building(schematic);

			allBuildings.add(building);
		}
	}

	public static boolean isBuilding(String name){
		boolean isBuilding = false;

		for (Building building : allBuildings) {
			if (building.getName().equalsIgnoreCase(name)) {
				isBuilding = true;
			}
		}

		return isBuilding;
	}

	public static Building getBuilding(String name){
		Building theBuilding = null;

		for (Building building : allBuildings) {
			if (building.getName().equalsIgnoreCase(name)) {
				theBuilding = building;
			}
		}

		return theBuilding;
	}

	public static void setBuilding(Player player, Building building){
		playerBuildings.put(player.getName(), building);
	}

	public static Building getPlayerBuilding(Player player){
		return playerBuildings.get(player.getName());
	}

	public static boolean isBuildingBlock(Location loc){
		boolean isBuildingBlock = true;

		for (Building building : allBuildings) {
			Schematic schematic = building.getSchematic();

			for (int x = 0; x < schematic.getWidth(); x++) {
				for (int y = 0; y < schematic.getHeight(); y++) {
					for (int z = 0; z < schematic.getLength(); ++z) {
						Location temp = loc.clone().add(x, y, z);
						Block schematicBlock = temp.getBlock();
						Block worldBlock = loc.getBlock().getRelative(x, y, z);

						if (!(schematicBlock.equals(worldBlock))) {
							isBuildingBlock = false;
						}
					}
				}
			}
		}

		if (allBuildings.size() == 0) {
			return false;
		} else {
			return isBuildingBlock;
		}
	}

	public static void removeBuilding(final Location loc){
		for (Building building : allBuildings) {
			final HashMap<Block, Integer> blocks = new HashMap<Block, Integer>();
			List<Block> allBlocks = new ArrayList<Block>();
			Schematic schematic = building.getSchematic();

			for (int x = 0; x < schematic.getWidth(); x++) {
				for (int y = 0; y < schematic.getHeight(); y++) {
					for (int z = 0; z < schematic.getLength(); ++z) {
						Location temp = loc.clone().add(x, y, z);
						Block block = temp.getBlock();
						int index = y * schematic.getWidth() * schematic.getLength() + z * schematic.getWidth() + x;

						blocks.put(block, index);
						allBlocks.add(block);
					}
				}
			}

			final List<Block> orderedBlocks = new ArrayList<Block>();

			orderedBlocks.addAll(allBlocks);

			Collections.sort(orderedBlocks, new Comparator<Block>() {
				@Override
				public int compare(Block block1, Block block2){
					return Double.compare(block1.getY(), block2.getY());
				}
			});

			final int size = orderedBlocks.size();
			final int blocksPerTime = 1;
			final long delay = 1L;

			if (size > 0) {
				new BukkitRunnable() {
					int index = size - 1;

					@Override
					public void run(){
						for (int i = 0; i < blocksPerTime; i++) {
							if (index >= 0) {
								Block block = orderedBlocks.get(index);

								Utilities.regenerateBlock(block, Material.AIR, (byte) 0);

								index -= 1;
							} else {
								this.cancel();
								return;
							}
						}
					}
				}.runTaskTimer(AVC.getPlugin(), 40L, delay);
			}
		}
	}
}