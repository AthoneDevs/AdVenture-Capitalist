package es.projectalpha.ac.shops;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.metadata.FixedMetadataValue;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.utils.LocationUtils;
import es.projectalpha.ac.utils.NamesUtils;
import es.projectalpha.ac.utils.Randoms;

public class VillagerShops {

	private static AVC plugin;

	public VillagerShops(AVC Main){
		VillagerShops.plugin = Main;
	}

	public static void spawnVillagers(){
		for (Shops s : Shops.values()) {
			long id = Randoms.getVillagerID();

			Location l = LocationUtils.getVillagerLocs(s.getMaterial());

			Villager v = l.getWorld().spawn(l, Villager.class);

			v.setAI(false); //Test

			v.setCustomName(NamesUtils.getVillagerName(s.toString().toLowerCase()));
			v.setCustomNameVisible(true);
			v.setProfession(Profession.values()[new Random().nextInt(Profession.values().length)]);
			v.setCanPickupItems(false);

			//Village ID
			setVillagerID(v, id);

			//NO AI
			//MobAPI.setAiEnabled(v, false);

			//Save Data
			Files.vill.set("v_" + id + ".x", l.getX());
			Files.vill.set("v_" + id + ".y", l.getY());
			Files.vill.set("v_" + id + ".z", l.getZ());

			Files.saveFiles();

			//Data
			ShopsCore.idVillagers.put(id, l);
		}
	}

	public static void loadVillagers(){
		List<Long> ids = Files.vill.getLongList("allIDs");

		for (long id : ids) {

			double x = Files.vill.getDouble("v_" + id + ".x");
			double y = Files.vill.getDouble("v_" + id + ".y");
			double z = Files.vill.getDouble("v_" + id + ".z");

			ShopsCore.idVillagers.put(id, new Location(Bukkit.getWorld("ac"), x, y, z));
		}
	}

	public static void setVillagerID(Villager v, long id){
		v.setMetadata("AVCID", new FixedMetadataValue(plugin, id));
	}

	public static long getVillagerID(Villager v){
		System.out.println(v.getMetadata("AVCID").size());
		System.out.println(v.getMetadata("AVCID").get(v.getMetadata("AVCID").size()).asLong());
		return v.getMetadata("AVCID").get(v.getMetadata("AVCID").size()).asLong();
	}
}
