package es.projectalpha.ac.shops;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.utils.Messages;

public class ShopsCore {

	//Data
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

	public static void addShop(Player p, Shops s){
		List<String> playersNames = Files.shops.getStringList(s.toString().toLowerCase());

		if (hasShop(p, s)) {
			p.sendMessage(Messages.hasShop);
			return;
		}

		playersNames.add(p.getName());

		Files.shops.set(s.toString().toLowerCase(), playersNames);
		Files.saveFiles();

		loadShops();
	}

	public static void removeShop(Player p, Shops s){
		List<String> playersNames = Files.shops.getStringList(s.toString().toLowerCase());

		if (!hasShop(p, s)) {
			p.sendMessage(Messages.notHasShop);
			return;
		}

		playersNames.remove(p.getName());

		Files.shops.set(s.toString().toLowerCase(), playersNames);
		Files.saveFiles();

		loadShops();
	}

	public static void buyShop(Player p, Shops s){
		if (hasShop(p, s)) {
			p.sendMessage(Messages.hasShop);
			return;
		}

		if (Currency.getMoney(p) <= s.getPrice()) {
			p.sendMessage(Messages.notEnoughMoney);
			return;
		}

		Currency.removeMoney(p, s.getPrice());
		addShop(p, s);
		p.sendMessage(Messages.buyShop);
	}

	public static boolean hasShop(Player p, Shops s){
		if (Files.shops.getStringList(s.toString().toLowerCase()).contains(p)) {
			return true;
		}
		return false;
	}

	public static void loadShops(){
		lemonade.clear();
		news.clear();
		car.clear();
		pizza.clear();
		donut.clear();
		boats.clear();
		hockey.clear();
		movie.clear();
		banks.clear();
		oil.clear();

		for (String s : Files.shops.getStringList(Shops.LEMONADE.toString().toLowerCase())) {
			lemonade.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.NEWS.toString().toLowerCase())) {
			news.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.CAR.toString().toLowerCase())) {
			car.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.PIZZA.toString().toLowerCase())) {
			pizza.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.DONUT.toString().toLowerCase())) {
			donut.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.BOAT.toString().toLowerCase())) {
			boats.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.HOCKEY.toString().toLowerCase())) {
			hockey.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.MOVIE.toString().toLowerCase())) {
			movie.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.shops.getStringList(Shops.BANK.toString().toLowerCase())) {
			banks.add(Bukkit.getPlayer(s));
		}
		for (String s : Files.shops.getStringList(Shops.OIL.toString().toLowerCase())) {
			oil.add(Bukkit.getPlayer(s));
		}
	}

	public static double getReward(String shop){
		switch (shop.toLowerCase()) {
		case "lemonade":
			return Shops.LEMONADE.getReward();
		case "news":
			return Shops.NEWS.getReward();
		case "car":
			return Shops.CAR.getReward();
		case "pizza":
			return Shops.PIZZA.getReward();
		case "donut":
			return Shops.DONUT.getReward();
		case "boat":
			return Shops.BOAT.getReward();
		case "hockey":
			return Shops.HOCKEY.getReward();
		case "movie":
			return Shops.MOVIE.getReward();
		case "bank":
			return Shops.BANK.getReward();
		case "oil":
			return Shops.OIL.getReward();
		default:
			return 0;
		}
	}

	public static double getTimer(String shop){
		switch (shop.toLowerCase()) {
		case "lemonade":
			return Shops.LEMONADE.getTimer();
		case "news":
			return Shops.NEWS.getTimer();
		case "car":
			return Shops.CAR.getTimer();
		case "pizza":
			return Shops.PIZZA.getTimer();
		case "donut":
			return Shops.DONUT.getTimer();
		case "ship":
			return Shops.BOAT.getTimer();
		case "hockey":
			return Shops.HOCKEY.getTimer();
		case "cinema":
			return Shops.MOVIE.getTimer();
		case "bank":
			return Shops.BANK.getTimer();
		case "oil":
			return Shops.OIL.getTimer();
		default:
			return 0;
		}
	}

	public static Shops getShop(Managers m){
		for (Shops s : Shops.values()) {
			if (s.toString().toLowerCase().equalsIgnoreCase(m.getdataName().toLowerCase())) {
				return s;
			}
		}
		return null;
	}
}
