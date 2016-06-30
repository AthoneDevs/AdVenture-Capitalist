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
		default:
			return 0;
		}
	}
}
