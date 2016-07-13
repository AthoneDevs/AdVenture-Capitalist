package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.NPCAPI;

public class SpawnManagers {

	public static HashMap<NPCAPI, Location> loc = new HashMap<NPCAPI, Location>();
	public static ArrayList<NPCAPI> npcs = new ArrayList<NPCAPI>();

	public static void spawnManager(Location l){

		for (Managers m : Managers.values()) {
			NPCAPI npc = new NPCAPI(m.getManagerName(), l.add(m.getDistX(), 0, m.getDistZ()), "cadox8");

			npc.destroy();
			npc.spawn();
			npc.setGameMode(GameMode.CREATIVE);

			loc.put(npc, l.add(m.getDistX(), 0, m.getDistZ()));
			npcs.add(npc);

			if (AVC.getDebug()) {
				System.out.println(npc.getName());
				System.out.println(npc);
				System.out.println(npc.getLocation());

				System.out.println(" ");
			}
		}
	}
}
