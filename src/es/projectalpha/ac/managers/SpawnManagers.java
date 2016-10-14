package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import es.projectalpha.ac.AVC;

public class SpawnManagers {

	public static HashMap<Villager, Location> loc = new HashMap<Villager, Location>();
	public static ArrayList<Villager> npcs = new ArrayList<Villager>();

	public static void spawnManager(Location lo){
		for(Managers m : Managers.values()){
			if(AVC.debug){
				System.out.println(lo.clone().getWorld().getBlockAt(lo.clone().add(m.getDistX(), 0, m.getDistZ())).getType());
			}

			lo.clone().getWorld().getBlockAt(lo.clone().add(m.getDistX(), 0, m.getDistZ())).setType(Material.AIR);

			//Villager v = lo.clone().getWorld().spawn(lo.clone().add(m.getDistX(), 0, m.getDistZ()), Villager.class);
			Villager v = (Villager) lo.clone().getWorld().spawnEntity(lo.clone().add(m.getDistX(), 0, m.getDistZ()), EntityType.VILLAGER);

			v.setCustomName(m.getManagerName());

			loc.put(v, lo.clone().add(m.getDistX(), 0, m.getDistZ()));
			npcs.add(v);

			if(AVC.debug){
				System.out.println(lo.clone().getWorld().getBlockAt(lo.clone().add(m.getDistX(), 0, m.getDistZ())).getType());
				System.out.println(v.getName());
				System.out.println(v.getLocation());

				System.out.println(" ");
			}
		}
	}
}
