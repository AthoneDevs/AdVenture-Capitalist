package es.projectalpha.ac.game;

import java.util.HashMap;

import org.bukkit.entity.Player;

import es.projectalpha.ac.utils.NumberUtils;

public class Currency {

	public static String moneyName = "Dollars";
	public static String moneySymbol = "$";

	public static HashMap<Player, String> runningCurrency = new HashMap<Player, String>();

	public static void addCurrency(Player p, double amount){
		//runningCurrency.put(p, changeCurrency(p) + amount);
	}

	public static void removeCurrency(Player p, double amount){
		//runningCurrency.put(p, changeCurrency(p) - amount);
	}

	public static double getCurrency(Player p){
		String currency = runningCurrency.get(p);
		String[] subString;

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

	public static String simpleCurrency(Player p){

		//TODO: Double to String (Number + Amount)
		//Amount = Million, Billion, etc.

		return String.valueOf(getCurrency(p));
	}
}
