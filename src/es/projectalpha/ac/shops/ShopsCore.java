package es.projectalpha.ac.shops;

import java.util.HashMap;

import org.bukkit.Location;

public class ShopsCore {

	public static HashMap<Float, Location> idVillagers = new HashMap<Float, Location>();

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
}
