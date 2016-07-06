package es.projectalpha.ac.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;

import es.projectalpha.ac.api.BossBarAPI;
import es.projectalpha.ac.api.HoloAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.ManagerCore;
import es.projectalpha.ac.managers.ManagersPrice;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.shops.ShopsCore;
import es.projectalpha.ac.shops.VillagerShops;

public class Game {

	public static ArrayList<Player> playing = new ArrayList<Player>();
	public static ArrayList<Location> progressBar = new ArrayList<Location>();
	public static HashMap<Location, String> shopLocation = new HashMap<Location, String>();

	private static ArrayList<HoloAPI> holos = new ArrayList<HoloAPI>();

	public static void startTimer(Plugin plugin){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run(){
				for (Player p : playing) {
					//Destroy Hologram
					for (HoloAPI holo : holos) {
						holo.destroy(p);
					}

					//TP Villagers
					for (Entity en : p.getWorld().getEntities()) {
						if (en instanceof Villager) {
							Villager v = (Villager) en;

							v.teleport(ShopsCore.idVillagers.get(VillagerShops.getVillagerID(v)));
						}
					}

					//Show Money
					int id = Files.players.getInt(p.getName() + ".id");

					double x = Files.locs.getDouble("id" + id + ".x");
					double y = Files.locs.getDouble("id" + id + ".y");
					double z = Files.locs.getDouble("id" + id + ".z");

					Location l = new Location(Bukkit.getWorld("ac"), x, y + 3, z);

					HoloAPI holo = new HoloAPI(l, "$ " + Currency.getCurrency(p));
					holo.display(p);

					//Check if Enough Money
					for (String manager : ManagerCore.managers) {
						if (Currency.getCurrency(p) >= ManagersPrice.getPrice(manager)) {
							BossBarAPI.sendMessageToPlayerRecurring(ChatColor.GREEN + "You can buy " + ChatColor.RED + manager, 8, BarColor.WHITE, BarStyle.SOLID, p);
						}
					}
				}

				//Managers
				for (Player p : ManagerCore.lemonade) {
					Currency.addCurrency(p, Shops.LEMONADE.getReward());
				}
				for (Player p : ManagerCore.news) {
					Currency.addCurrency(p, Shops.NEWS.getReward());
				}
				for (Player p : ManagerCore.car) {
					Currency.addCurrency(p, Shops.CAR.getReward());
				}
				for (Player p : ManagerCore.pizza) {
					Currency.addCurrency(p, Shops.PIZZA.getReward());
				}
				for (Player p : ManagerCore.donut) {
					Currency.addCurrency(p, Shops.DONUT.getReward());
				}
				for (Player p : ManagerCore.boats) {
					Currency.addCurrency(p, Shops.BOATS.getReward());
				}
				for (Player p : ManagerCore.hockey) {
					Currency.addCurrency(p, Shops.HOCKEY.getReward());
				}
				for (Player p : ManagerCore.movie) {
					Currency.addCurrency(p, Shops.MOVIE.getReward());
				}
				for (Player p : ManagerCore.banks) {
					Currency.addCurrency(p, Shops.BANKS.getReward());
				}
				for (Player p : ManagerCore.oil) {
					Currency.addCurrency(p, Shops.OIL.getReward());
				}

			}
		}, 0L, 20L);
	}
}
