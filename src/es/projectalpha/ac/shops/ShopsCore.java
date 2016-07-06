package es.projectalpha.ac.shops;

import java.util.HashMap;

import org.bukkit.Location;

public class ShopsCore {

	public static HashMap<Long, Location> idVillagers = new HashMap<Long, Location>();

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
		case "ship":
			return Shops.BOATS.getReward();
		case "hockey":
			return Shops.HOCKEY.getReward();
		case "cinema":
			return Shops.MOVIE.getReward();
		case "bank":
			return Shops.BANKS.getReward();
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
			return Shops.BOATS.getTimer();
		case "hockey":
			return Shops.HOCKEY.getTimer();
		case "cinema":
			return Shops.MOVIE.getTimer();
		case "bank":
			return Shops.BANKS.getTimer();
		case "oil":
			return Shops.OIL.getTimer();
		default:
			return 0;
		}
	}
}
