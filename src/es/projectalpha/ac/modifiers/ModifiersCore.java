package es.projectalpha.ac.modifiers;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.shops.ShopsCore;

public class ModifiersCore {

	private ShopsCore sc = new ShopsCore();

	public void buyItem(Shops s, int amount, Player p){
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
