package es.projectalpha.ac.managers;

import org.bukkit.entity.Player;

import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.utils.Messages;

public class BuyManagers {

	public static void buyManager(Player p, String manager){
		if (ManagerCore.hasManager(p, manager)) {
			p.sendMessage(Messages.hasManager);
			return;
		}

		if (Currency.getCurrency(p) <= ManagersPrice.getPrice(manager)) {
			p.sendMessage(Messages.notEnoughMoney);
			return;
		}

		Currency.removeCurrency(p, ManagersPrice.getPrice(manager));
		p.sendMessage(Messages.buyManager);
	}
}
