package es.projectalpha.ac.managers;

public class ManagersPrice {

	private static double limonadePrice(){
		return 1000;
	}

	private static double newsPrice(){
		return 15000;
	}

	private static double carPrice(){
		return 100000;
	}

	private static double pizzaPrice(){
		return 500000;
	}

	private static double donutPrice(){
		return 1200000;
	}

	private static double boatPrice(){
		return 10000000;
	}

	private static double hockeyPrice(){
		return 111111111;
	}

	private static double moviePrice(){
		return 555555555;
	}

	private static double bankPrice(){
		return 10000000;
	}

	private static double oilPrice(){
		return 1000000000;
	}

	public static double getPrice(String manager){
		switch (manager.toLowerCase()) {
		case "lemonade":
			return limonadePrice();
		case "news":
			return newsPrice();
		case "car":
			return carPrice();
		case "pizza":
			return pizzaPrice();
		case "donut":
			return donutPrice();
		case "boat":
			return boatPrice();
		case "hockey":
			return hockeyPrice();
		case "movie":
			return moviePrice();
		case "bank":
			return bankPrice();
		case "oil":
			return oilPrice();
		default:
			return 0;
		}
	}
}
