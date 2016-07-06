package es.projectalpha.ac.shops;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import es.projectalpha.ac.utils.LocationUtils;

public class VillagerShops {

	public static void spawnVillagers(){
		for (Shops s : Shops.values()) {
			Location l = LocationUtils.getVillagerLocs(s.getMaterial());

			Villager v = l.getWorld().spawn(l, Villager.class);

			v.setAI(false); //TODO: NO AI
			v.setCustomName(""); //TODO: Get Name
			v.setCustomNameVisible(true);
			v.setProfession(Profession.values()[new Random().nextInt(Profession.values().length)]);
			v.setCanPickupItems(false);

			ShopsCore.idVillagers.put(4.0F, l); //TODO: Random ID
		}
	}
}
