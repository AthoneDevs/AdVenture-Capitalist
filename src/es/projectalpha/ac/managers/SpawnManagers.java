package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import es.projectalpha.ac.AVCAPI;

public class SpawnManagers {

	public static HashMap<Villager, Location> loc = new HashMap<Villager, Location>();
	public static ArrayList<Villager> npcs = new ArrayList<Villager>();

	private static AVCAPI api = new AVCAPI();

	public static void spawnManager(Location lo){
		for(Managers m : Managers.values()){
			Location l = lo.clone();

			if(api.getDebug()){
				System.out.println(l.getWorld().getBlockAt(l.add(m.getDistX(), 0, m.getDistZ())).getType());
			}

			l.getWorld().getBlockAt(l.add(m.getDistX(), 0, m.getDistZ())).setType(Material.AIR);

			//Villager v = l.getWorld().spawn(l.add(m.getDistX(), 0, m.getDistZ()), Villager.class);
			Villager v = (Villager) l.getWorld().spawnEntity(l.add(m.getDistX(), 0, m.getDistZ()), EntityType.VILLAGER);

			v.setCustomName(m.getManagerName());

			loc.put(v, l.add(m.getDistX(), 0, m.getDistZ()));
			npcs.add(v);

			if(api.getDebug()){
				System.out.println(l.getWorld().getBlockAt(l.add(m.getDistX(), 0, m.getDistZ())).getType());
				System.out.println(v.getName());
				System.out.println(v.getLocation());

				System.out.println(" ");
			}
		}
	}
}
