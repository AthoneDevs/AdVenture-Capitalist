package es.projectalpha.ac.shops;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.metadata.FixedMetadataValue;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.MobAPI;
import es.projectalpha.ac.utils.LocationUtils;
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

			v.setAI(false);
			v.setCustomName(""); //TODO: Get Name
			v.setCustomNameVisible(true);
			v.setProfession(Profession.values()[new Random().nextInt(Profession.values().length)]);
			v.setCanPickupItems(false);
			setVillagerID(v, id);

			MobAPI.setAiEnabled(v, false);

			ShopsCore.idVillagers.put(id, l);
		}
	}

	public static void setVillagerID(Villager v, long id){
		v.setMetadata("AVCID", new FixedMetadataValue(plugin, id));
	}

	public static long getVillagerID(Villager v){
		System.out.println(v.getMetadata("AVCID").size());
		return v.getMetadata("AVCID").get(v.getMetadata("AVCID").size()).asLong();
	}
}
