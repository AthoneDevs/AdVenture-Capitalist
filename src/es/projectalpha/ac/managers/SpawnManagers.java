package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Villager;

import es.projectalpha.ac.AVCAPI;

public class SpawnManagers {

	// public static HashMap<NPCAPI, Location> loc = new HashMap<NPCAPI, Location>();
	// public static ArrayList<NPCAPI> npcs = new ArrayList<NPCAPI>();

	public static HashMap<Villager, Location> loc = new HashMap<Villager, Location>();
	public static ArrayList<Villager> npcs = new ArrayList<Villager>();

	private static AVCAPI api = new AVCAPI();

	public static void spawnManager(Location lo) {
		for (Managers m : Managers.values()) {
			Location l = lo.clone();

			if (api.getDebug()) {
				System.out.println(l.getWorld().getBlockAt(l.add(m.getDistX(), 0, m.getDistZ())).getType());
			}

			l.getWorld().getBlockAt(l.add(m.getDistX(), 0, m.getDistZ())).setType(Material.AIR);

			Villager v = l.getWorld().spawn(l.add(m.getDistX(), 0, m.getDistZ()), Villager.class);

			// TODO: Fix not spawning bug

			v.setCustomName(m.getManagerName());

			loc.put(v, l.add(m.getDistX(), 0, m.getDistZ()));
			npcs.add(v);

			System.out.println(api.getDebug());

			if (api.getDebug()) {
				System.out.println(l.getWorld().getBlockAt(l.add(m.getDistX(), 0, m.getDistZ())).getType());
				System.out.println(v.getName());
				System.out.println(v.getLocation());

				System.out.println(" ");
			}
		}
	}

	// public static void spawnManager(Location l){
	// for (Managers m : Managers.values()) {
	// NPCAPI npc = new NPCAPI(m.getManagerName(), l.add(m.getDistX(), 0, m.getDistZ()), "cadox8");
	//
	// npc.destroy();
	// npc.spawn();
	// npc.setGameMode(GameMode.CREATIVE);
	// npc.setLocation(l.add(m.getDistX(), 0, m.getDistZ()));
	//
	// for (Player p : Bukkit.getOnlinePlayers()) {
	// npc.show(p);
	// }
	//
	// loc.put(npc, l.add(m.getDistX(), 0, m.getDistZ()));
	// npcs.add(npc);
	//
	// if (api.getDebug()) {
	// System.out.println(npc.getName());
	// System.out.println(npc);
	// System.out.println(npc.getLocation());
	//
	// System.out.println(" ");
	// }
	// }
	// }
}
