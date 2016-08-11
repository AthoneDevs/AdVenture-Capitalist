package es.projectalpha.ac.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import es.projectalpha.ac.api.NPCAPI;
import es.projectalpha.ac.api.fancy.BossBarAPI;
import es.projectalpha.ac.api.fancy.HoloAPI;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.managers.ManagersCore;
import es.projectalpha.ac.managers.SpawnManagers;

public class Game {

	public ArrayList<Player> playing = new ArrayList<Player>();
	public ArrayList<Location> progressBar = new ArrayList<Location>();
	public HashMap<Location, String> shopLocation = new HashMap<Location, String>();

	private ArrayList<HoloAPI> holos = new ArrayList<HoloAPI>();

	//Utils
	private ManagersCore mc = new ManagersCore();
	private Currency c = new Currency();

	public void startTimer(Plugin plugin){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run(){
				for (Player p : playing) {

					//Show NPCs
					for (NPCAPI npc : SpawnManagers.npcs) {
						npc.destroy();
						npc.spawn();
						npc.show(p);
					}

					//Destroy Hologram
					for (HoloAPI holo : holos) {
						holo.destroy(p);
					}

					//TEST
					System.out.println("Work");

					//TP Villagers
					//					for (Villager v : VillagerShops.villagerUtils) {
					//						v.teleport(VillagerShops.villagerLocs.get(v));
					//					}

					//Show Money
					int id = Files.players.getInt(p.getName() + ".id");

					double x = Files.locs.getDouble("id" + id + ".x");
					double y = Files.locs.getDouble("id" + id + ".y");
					double z = Files.locs.getDouble("id" + id + ".z");

					Location l = new Location(Bukkit.getWorld("ac"), x, y + 3, z);

					HoloAPI holo = new HoloAPI(l, "$ " + c.getMoney(p));
					holo.display(p);

					//Check if Enough Money
					for (Managers m : Managers.values()) {
						if (c.getMoney(p) >= m.getPrice()) {
							BossBarAPI.sendMessageToPlayerRecurring(ChatColor.GREEN + "You can buy " + ChatColor.RED + m.getName() + ChatColor.GREEN + " manager", 8, BarColor.WHITE, BarStyle.SOLID, p);
						}
					}
				}

				//Managers

				for (Managers m : Managers.values()) {
					for (Player p : mc.getPlayersWithManager(m)) {
						if (Cooldowns.isCooling(p.getName(), m.getName())) {
							return;
						}
						for (NPCAPI npc : SpawnManagers.npcs) {
							if (npc.getName().equalsIgnoreCase(m.getManagerName())) {
								progressBar.add(npc.getLocation().add(0, 3, 0));
							}
						}
						Cooldowns.add(p.getName(), m.getName(), (long) m.getShop().getTimer(), System.currentTimeMillis());
					}
				}
			}
		}, 0L, 20L);
	}
}
