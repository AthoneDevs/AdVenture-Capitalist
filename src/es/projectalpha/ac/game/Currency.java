package es.projectalpha.ac.game;

import java.util.HashMap;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.utils.NumberUtils;

public class Currency {

	public static String moneyName = "Dollars";
	public static String moneySymbol = "$";

	public static HashMap<Player, String> runningCurrency = new HashMap<Player, String>();

	public static boolean existPlayer(Player p){
		if (Files.players.contains(p.getName())) {
			return true;
		}
		return false;
	}

	public static void addMoney(Player p, double amount){
		if (existPlayer(p)) {
			runningCurrency.put(p, parseMoney(getMoney(p) + amount));
		}
	}

	public static void removeMoney(Player p, double amount){
		if (existPlayer(p)) {
			runningCurrency.put(p, parseMoney(getMoney(p) - amount));
		}
	}

	public static void saveMoney(Player p){
		Files.players.set(p.getName() + ".money", getMoney(p));
		Files.saveFiles();
	}

	public static void loadMoney(Player p){
		if (existPlayer(p)) {
			runningCurrency.put(p, parseMoney(Files.players.getDouble(p.getName() + ".money")));
		}
	}

	public static void newPlayerMoney(Player p, double amount){
		runningCurrency.put(p, parseMoney(amount));
		saveMoney(p);
	}

	public static double getMoney(Player p){
		String currency = runningCurrency.get(p);
		String[] subString;

		if (!runningCurrency.containsKey(p)) {
			return 0;
		}

		if (currency.contains("Million")) {
			subString = currency.split(" ");

			return NumberUtils.getMillions(subString[0]);
		}

		if (currency.contains("Billion")) {
			subString = currency.split(" ");

			return NumberUtils.getBillions(subString[0]);
		}

		if (currency.contains("Trillion")) {
			subString = currency.split(" ");

			return NumberUtils.getTrillions(subString[0]);
		}

		subString = currency.split(" ");

		return Double.parseDouble(subString[0]);
	}

	public static String getSMoney(Player p){
		double g = 1000000;

		if (getMoney(p) == g) {
			return NumberUtils.getMillions(getMoney(p));
		}

		if (getMoney(p) > g) {
			return NumberUtils.getMillions(getMoney(p)) + "s";
		}

		if (getMoney(p) == g * 1000) {
			return NumberUtils.getBillions(getMoney(p));
		}

		if (getMoney(p) > g * 1000) {
			return NumberUtils.getBillions(getMoney(p)) + "s";
		}

		if (getMoney(p) == g * 100000) {
			return NumberUtils.getTrillions(getMoney(p));
		}

		if (getMoney(p) > g * 100000) {
			return NumberUtils.getTrillions(getMoney(p)) + "s";
		}

		return String.valueOf(getMoney(p));
	}

	public static String parseMoney(double currency){
		double g = 1000000;

		if (currency == g) {
			return NumberUtils.getMillions(currency);
		}

		if (currency > g) {
			return NumberUtils.getMillions(currency) + "s";
		}

		if (currency == g * 1000) {
			return NumberUtils.getBillions(currency);
		}

		if (currency > g * 1000) {
			return NumberUtils.getBillions(currency) + "s";
		}

		if (currency == g * 100000) {
			return NumberUtils.getTrillions(currency);
		}

		if (currency > g * 100000) {
			return NumberUtils.getTrillions(currency) + "s";
		}

		return String.valueOf(currency);
	}
}
