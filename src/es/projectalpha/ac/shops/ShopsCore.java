package es.projectalpha.ac.shops;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.money.Money;
import es.projectalpha.ac.utils.Messages;

public class ShopsCore {

	private Money c = new Money();

	public void addShop(Player p, Shops s){
		List<String> playersNames = Files.shops.getStringList(s.toString().toLowerCase());

		if(hasShop(p, s)){
			p.sendMessage(Messages.hasShop);
			return;
		}

		playersNames.add(p.getName());

		Files.shops.set(s.toString().toLowerCase(), playersNames);
		Files.saveFiles();
	}

	public void removeShop(Player p, Shops s){
		List<String> playersNames = Files.shops.getStringList(s.toString().toLowerCase());

		if(!hasShop(p, s)){
			p.sendMessage(Messages.notHasShop);
			return;
		}

		playersNames.remove(p.getName());

		Files.shops.set(s.toString().toLowerCase(), playersNames);
		Files.saveFiles();
	}

	public void buyShop(Player p, Shops s){
		if(hasShop(p, s)){
			p.sendMessage(Messages.hasShop);
			return;
		}

		if(c.getMoney(p) < s.getPrice()){
			p.sendMessage(Messages.notEnoughMoney);
			return;
		}

		c.removeMoney(p, s.getPrice());
		addShop(p, s);
		p.sendMessage(Messages.buyShop);
	}

	public List<String> getShopsByPlayer(Player p){
		List<String> shops = new ArrayList<String>();

		for(Shops s : Shops.values()){
			if(hasShop(p, s)){
				shops.add(WordUtils.capitalizeFully(s.toString().toLowerCase()));
			}
		}

		return shops;
	}

	public boolean existShop(String s){
		if(Shops.valueOf(s.toUpperCase()) == null){
			return false;
		}
		return true;
	}

	public boolean hasShop(Player p, Shops s){
		if(Files.shops.getStringList(s.toString().toLowerCase()).contains(p)){
			return true;
		}
		return false;
	}
}
