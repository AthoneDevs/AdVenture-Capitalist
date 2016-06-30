package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.utils.Messages;

public class ManagerCore {

	public static ArrayList<String> managers = new ArrayList<String>();

	public static ArrayList<Player> lemonade = new ArrayList<Player>();
	public static ArrayList<Player> news = new ArrayList<Player>();
	public static ArrayList<Player> car = new ArrayList<Player>();
	public static ArrayList<Player> pizza = new ArrayList<Player>();
	public static ArrayList<Player> donut = new ArrayList<Player>();
	public static ArrayList<Player> boats = new ArrayList<Player>();
	public static ArrayList<Player> hockey = new ArrayList<Player>();
	public static ArrayList<Player> movie = new ArrayList<Player>();
	public static ArrayList<Player> banks = new ArrayList<Player>();
	public static ArrayList<Player> oil = new ArrayList<Player>();

	public static void addManager(Player p, String manager){
		manager = WordUtils.capitalizeFully(manager);
		List<String> playersNames = Files.manager.getStringList(manager);

		if (hasManager(p, manager)) {
			p.sendMessage(Messages.hasManager);
			return;
		}

		playersNames.add(p.getName());

		Files.manager.set(manager, playersNames);
		Files.saveFiles();
	}

	public static void removeManager(Player p, String manager){
		manager = WordUtils.capitalizeFully(manager);
		List<String> playersNames = Files.manager.getStringList(manager);

		if (!hasManager(p, manager)) {
			p.sendMessage(Messages.notHasManager);
			return;
		}

		playersNames.remove(p.getName());

		Files.manager.set(manager, playersNames);
		Files.saveFiles();
	}

	static boolean hasManager(Player p, String manager){
		switch (manager.toLowerCase()) {
		case "lemonade":
			if (lemonade.contains(p)) {
				return true;
			}
			return false;
		case "news":
			if (news.contains(p)) {
				return true;
			}
			return false;
		case "car":
			if (car.contains(p)) {
				return true;
			}
			return false;
		case "pizza":
			if (pizza.contains(p)) {
				return true;
			}
			return false;
		case "donut":
			if (donut.contains(p)) {
				return true;
			}
			return false;
		case "boat":
			if (boats.contains(p)) {
				return true;
			}
			return false;
		case "hockey":
			if (hockey.contains(p)) {
				return true;
			}
			return false;
		case "movie":
			if (movie.contains(p)) {
				return true;
			}
			return false;
		case "banks":
			if (banks.contains(p)) {
				return true;
			}
			return false;
		case "oil":
			if (oil.contains(p)) {
				return true;
			}
			return false;
		default:
			return false;
		}
	}

	public static void loadManagers(){
		managers.clear();

		managers.add("lemonade");
		managers.add("news");
		managers.add("car");
		managers.add("pizza");
		managers.add("donut");
		managers.add("boats");
		managers.add("hockey");
		managers.add("movie");
		managers.add("banks");
		managers.add("oil");

		for (String s : Files.manager.getStringList("Lemonade")) {
			lemonade.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("News")) {
			news.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Car")) {
			car.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Pizza")) {
			pizza.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Donut")) {
			donut.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Boats")) {
			boats.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Hockey")) {
			hockey.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Movie")) {
			movie.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Banks")) {
			banks.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList("Oil")) {
			oil.add(Bukkit.getPlayer(s));
		}
	}
}
