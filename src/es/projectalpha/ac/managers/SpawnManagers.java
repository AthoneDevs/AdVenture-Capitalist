package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.api.NPCAPI;

public class SpawnManagers {

	public static HashMap<NPCAPI, Location> loc = new HashMap<NPCAPI, Location>();
	public static ArrayList<NPCAPI> npcs = new ArrayList<NPCAPI>();

	private static AVCAPI api = new AVCAPI();

	public static void spawnManager(Location l){

		for (Managers m : Managers.values()) {
			NPCAPI npc = new NPCAPI(m.getManagerName(), l.add(m.getDistX(), 0, m.getDistZ()), "cadox8");

			npc.destroy();
			npc.spawn();
			npc.setGameMode(GameMode.CREATIVE);
			npc.setLocation(l.add(m.getDistX(), 0, m.getDistZ()));

			for (Player p : Bukkit.getOnlinePlayers()) {
				npc.show(p);
			}

			loc.put(npc, l.add(m.getDistX(), 0, m.getDistZ()));
			npcs.add(npc);

			if (api.getDebug()) {
				System.out.println(npc.getName());
				System.out.println(npc);
				System.out.println(npc.getLocation());

				System.out.println(" ");
			}
		}
	}
}
