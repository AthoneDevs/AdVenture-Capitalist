package es.projectalpha.ac.modifiers;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.shops.ShopsCore;
import es.projectalpha.ac.utils.Messages;

public class ModifiersCore {

	private ShopsCore sc = new ShopsCore();
	private Currency c = new Currency();

	public void buyItem(Shops s, int amount, Player p){
		if (c.getMoney(p) < (s.getBuyBase() * (Math.pow(s.getCoefficient(), (amount - 1))))) {
			p.sendMessage(Messages.notEnoughMoney);
			return;
		}
		c.removeMoney(p, s.getBuyBase() * (Math.pow(s.getCoefficient(), (amount - 1))));
		Files.players.set(p.getName() + "." + s.toString().toLowerCase(), getShopItems(s, p) + amount);
		Files.saveFiles();
	}

	public int getShopItems(Shops s, Player p){
		if (!sc.hasShop(p, s)) {
			return 0;
		}
		return Files.players.getInt(p.getName() + "." + s.toString().toLowerCase());
	}

	//TODO: Shop with own money per item
	public double getMoneyShopItems(Shops s, Player p){
		return getShopItems(s, p) * 0.1;
	}

	public void deteleItems(Shops s, int amount, Player p){
		Files.players.set(p.getName() + "." + s.toString().toLowerCase(), getShopItems(s, p) - amount);
		Files.saveFiles();
	}
}
