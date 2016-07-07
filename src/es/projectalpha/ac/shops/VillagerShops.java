package es.projectalpha.ac.shops;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;

import es.projectalpha.ac.api.MobAPI;

public class VillagerShops {

	public static HashMap<Villager, Location> villagerLocs = new HashMap<Villager, Location>();

	public static ArrayList<Villager> villagerUtils = new ArrayList<Villager>();

	public static void loadVillagers(){
		villagerLocs.clear();
		villagerUtils.clear();

		for (Entity en : Bukkit.getWorld("ac").getEntities()) {
			if (en instanceof Villager) {
				Villager v = (Villager) en;

				MobAPI.setAiEnabled(v, false);

				villagerLocs.put(v, v.getLocation());
				villagerUtils.add(v);
			}
		}
	}
}
