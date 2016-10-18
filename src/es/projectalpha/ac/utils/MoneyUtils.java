package es.projectalpha.ac.utils;

import org.bukkit.entity.Player;

import es.projectalpha.ac.AVC;

public class MoneyUtils {

	private static String moneySymbol = "$";

	public static double getMoney(Player p){
		String currency = AVC.runningCurrency.get(p);
		String[] subString;

		if(!AVC.runningCurrency.containsKey(p)){
			return 0;
		}

		if(currency.contains("Million")){
			subString = currency.split(" ");

			return NumberUtils.getMillions(subString[0]);
		}

		if(currency.contains("Billion")){
			subString = currency.split(" ");

			return NumberUtils.getBillions(subString[0]);
		}

		if(currency.contains("Trillion")){
			subString = currency.split(" ");

			return NumberUtils.getTrillions(subString[0]);
		}

		subString = currency.split(" ");

		return Double.parseDouble(subString[0]);
	}

	public static String getSMoney(Player p){
		double g = 1000000;

		if(getMoney(p) == g){
			return NumberUtils.getMillions(getMoney(p)) + moneySymbol;
		}

		if(getMoney(p) > g){
			return NumberUtils.getMillions(getMoney(p)) + "s" + moneySymbol;
		}

		if(getMoney(p) == g * 1000){
			return NumberUtils.getBillions(getMoney(p)) + moneySymbol;
		}

		if(getMoney(p) > g * 1000){
			return NumberUtils.getBillions(getMoney(p)) + "s" + moneySymbol;
		}

		if(getMoney(p) == g * 100000){
			return NumberUtils.getTrillions(getMoney(p)) + moneySymbol;
		}

		if(getMoney(p) > g * 100000){
			return NumberUtils.getTrillions(getMoney(p)) + "s" + moneySymbol;
		}

		return String.valueOf(getMoney(p)) + moneySymbol;
	}

	public static String parseMoney(double currency){
		double g = 1000000;

		if(currency == g){
			return NumberUtils.getMillions(currency) + moneySymbol;
		}

		if(currency > g){
			return NumberUtils.getMillions(currency) + "s" + moneySymbol;
		}

		if(currency == g * 1000){
			return NumberUtils.getBillions(currency) + moneySymbol;
		}

		if(currency > g * 1000){
			return NumberUtils.getBillions(currency) + "s" + moneySymbol;
		}

		if(currency == g * 100000){
			return NumberUtils.getTrillions(currency) + moneySymbol;
		}

		if(currency > g * 100000){
			return NumberUtils.getTrillions(currency) + "s" + moneySymbol;
		}

		return String.valueOf(currency) + moneySymbol;
	}
}
