package es.projectalpha.ac.utils;

public class NamesUtils {

	public static String getVillagerName(String s){
		switch (s.toLowerCase()) {
		case "lemonade":
			return Messages.lemonadeManager;
		case "news":
			return Messages.newspaperManager;
		case "car":
			return Messages.carManager;
		case "pizza":
			return Messages.pizzaManager;
		case "donut":
			return Messages.donutManager;
		case "boat":
			return Messages.boatManager;
		case "hockey":
			return Messages.hockeyManager;
		case "movie":
			return Messages.movieManager;
		case "bank":
			return Messages.bankManager;
		case "oil":
			return Messages.oilManager;
		default:
			return "";
		}
	}
}
