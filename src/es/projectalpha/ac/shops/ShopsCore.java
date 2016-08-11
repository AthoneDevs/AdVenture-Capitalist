package es.projectalpha.ac.shops;

import java.util.List;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.utils.Messages;

public class ShopsCore {

	private Currency c = new Currency();

	public void addShop(Player p, Shops s){
		List<String> playersNames = Files.shops.getStringList(s.toString().toLowerCase());

		if (hasShop(p, s)) {
			p.sendMessage(Messages.hasShop);
			return;
		}

		playersNames.add(p.getName());

		Files.shops.set(s.toString().toLowerCase(), playersNames);
		Files.saveFiles();
	}

	public void removeShop(Player p, Shops s){
		List<String> playersNames = Files.shops.getStringList(s.toString().toLowerCase());

		if (!hasShop(p, s)) {
			p.sendMessage(Messages.notHasShop);
			return;
		}

		playersNames.remove(p.getName());

		Files.shops.set(s.toString().toLowerCase(), playersNames);
		Files.saveFiles();
	}

	public void buyShop(Player p, Shops s){
		if (hasShop(p, s)) {
			p.sendMessage(Messages.hasShop);
			return;
		}

		if (c.getMoney(p) <= s.getPrice()) {
			p.sendMessage(Messages.notEnoughMoney);
			return;
		}

		c.removeMoney(p, s.getPrice());
		addShop(p, s);
		p.sendMessage(Messages.buyShop);
	}

	public boolean hasShop(Player p, Shops s){
		if (Files.shops.getStringList(s.toString().toLowerCase()).contains(p)) {
			return true;
		}
		return false;
	}
}
