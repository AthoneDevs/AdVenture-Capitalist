package es.projectalpha.ac.cmd.sub.admin;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.utils.Messages;

public class ShopsCommand {

	private AVCAPI api = new AVCAPI();

	public void executeGetShopsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		p.sendMessage(Messages.prefix + ChatColor.GOLD + "Shops of " + ChatColor.AQUA + pl.getName() + ChatColor.GOLD + ":");
		p.sendMessage(ChatColor.RED + api.getShops().getShopsByPlayer(pl).toString().replaceAll("[", "").replaceAll("]", ""));
	}

	public void executeAddShopsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);
		Shops s;

		if(api.getShops().existShop(args[3])){
			p.sendMessage(Messages.prefix); //TODO: No shop exist message
			return;
		}

		s = Shops.valueOf(args[3]);

		api.getShops().addShop(pl, s);

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + WordUtils.capitalizeFully(s.toString().toLowerCase()) + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}

	public void executeRemoveShopsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);
		Shops s;

		if(api.getShops().existShop(args[3])){
			p.sendMessage(Messages.prefix); //TODO: No shop exist message
			return;
		}

		s = Shops.valueOf(args[3]);

		api.getShops().removeShop(pl, s);

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Removed " + ChatColor.RED + WordUtils.capitalizeFully(s.toString().toLowerCase()) + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}
}
