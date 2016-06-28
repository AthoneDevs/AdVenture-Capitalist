package es.projectalpha.ac.managers;

import org.bukkit.entity.Player;

import es.projectalpha.ac.game.Currency;

public class BuyManagers {

	public static void buyManager(Player p, String manager){
		if (hasManager(p, manager)) {
			//TODO: Messages
			return;
		}

		int price = 0; //TODO: Manager Price

		if (Currency.getCurrency(p) <= price) {
			//TODO: Messages
			return;
		}

		Currency.removeCurrency(p, price);
		//TODO: Messages
	}

	public static boolean hasManager(Player p, String manager){
		switch (manager.toLowerCase()) {
		case "lemonade":
			if (ManagerCore.lemonade.contains(p)) {
				return true;
			}
			return false;
		case "news":
			if (ManagerCore.news.contains(p)) {
				return true;
			}
			return false;

		default:
			return false;
		}
	}
}
