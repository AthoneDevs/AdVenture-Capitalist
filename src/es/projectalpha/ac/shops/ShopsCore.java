package es.projectalpha.ac.shops;

public class ShopsCore {

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
			return Shops.SHIP.getReward();
		case "hockey":
			return Shops.HOCKEY.getReward();
		case "cinema":
			return Shops.CINEMA.getReward();
		case "bank":
			return Shops.BANK.getReward();
		case  "oil":
			return Shops.OIL.getReward();
		default:
			return 0;
		}
	}
}
