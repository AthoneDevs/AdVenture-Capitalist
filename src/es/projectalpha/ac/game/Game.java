package es.projectalpha.ac.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import es.projectalpha.ac.api.HologramAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.ManagerCore;
import es.projectalpha.ac.utils.ShopRewards;

public class Game {

	public static ArrayList<Player> playing = new ArrayList<Player>();
	public static ArrayList<Location> progressBar = new ArrayList<Location>();
	public static HashMap<Location, String> shopLocation = new HashMap<Location, String>();

	private static ArrayList<HologramAPI> holos = new ArrayList<HologramAPI>();

	public static void startTimer(Plugin plugin){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run(){
				for (HologramAPI holo : holos) {
					holo.destroy();
				}

				for (Player p : playing) {
					int id = Files.players.getInt(p.getName() + ".id");

					double x = Files.locs.getDouble("id" + id + ".x");
					double y = Files.locs.getDouble("id" + id + ".y");
					double z = Files.locs.getDouble("id" + id + ".z");

					Location l = new Location(Bukkit.getWorld("ac"), x, y + 3, z);

					HologramAPI holo = new HologramAPI("$ " + Currency.getCurrency(p));
					holo.show(p, l);
				}

				//Managers
				for (Player p : ManagerCore.lemonade) {
					Currency.addCurrency(p, ShopRewards.getLimonade());
				}
				for (Player p : ManagerCore.news) {
					Currency.addCurrency(p, ShopRewards.getNews());
				}
				for (Player p : ManagerCore.car) {
					Currency.addCurrency(p, ShopRewards.getCar());
				}

			}
		}, 0L, 20L);
	}
}
