package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.utils.Messages;

public class ManagerCore {

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

	public static void addManager(Player p, Managers m){
		List<String> playersNames = Files.manager.getStringList(m.getdataName());

		if (hasManager(p, m)) {
			p.sendMessage(Messages.hasManager);
			return;
		}

		playersNames.add(p.getName());

		Files.manager.set(m.getdataName(), playersNames);
		Files.saveFiles();

		loadManagers();
	}

	public static void removeManager(Player p, Managers m){
		List<String> playersNames = Files.manager.getStringList(m.getdataName());

		if (!hasManager(p, m)) {
			p.sendMessage(Messages.notHasManager);
			return;
		}

		playersNames.remove(p.getName());

		Files.manager.set(m.getdataName(), playersNames);
		Files.saveFiles();

		loadManagers();
	}

	public static void buyManager(Player p, Managers m){
		if (hasManager(p, m)) {
			p.sendMessage(Messages.hasManager);
			return;
		}

		if (Currency.getMoney(p) <= m.getPrice()) {
			p.sendMessage(Messages.notEnoughMoney);
			return;
		}

		Currency.removeMoney(p, m.getPrice());
		addManager(p, m);
		p.sendMessage(Messages.buyManager);
	}

	public static boolean hasManager(Player p, Managers m){
		if (Files.manager.getStringList(m.getdataName()).contains(p)) {
			return true;
		}
		return false;
	}

	public static void loadManagers(){
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

		for (String s : Files.manager.getStringList(Managers.LEMONADE.getdataName())) {
			lemonade.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.NEWS.getdataName())) {
			news.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.CAR.getdataName())) {
			car.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.PIZZA.getdataName())) {
			pizza.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.DONUT.getdataName())) {
			donut.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.BOAT.getdataName())) {
			boats.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.HOCKEY.getdataName())) {
			hockey.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.MOVIE.getdataName())) {
			movie.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.BANK.getdataName())) {
			banks.add(Bukkit.getPlayer(s));
		}

		for (String s : Files.manager.getStringList(Managers.OIL.getdataName())) {
			oil.add(Bukkit.getPlayer(s));
		}
	}
}
