package es.projectalpha.ac.cmd.sub.admin;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.utils.Messages;

public class ModifiersCommand {

	private AVCAPI api = new AVCAPI();

	public void executeModifiersCommand(Player p, String[] args){
		Shops s = Shops.valueOf(args[2]);
		String shop = WordUtils.capitalizeFully(s.toString());
		Player pl = Bukkit.getPlayer(args[3]);

		p.sendMessage(Messages.prefix + ChatColor.GOLD + "Modifiers of " + ChatColor.RED + shop + ChatColor.GOLD + ": " + ChatColor.AQUA + api.getModifiers().getShopItems(s, pl));
	}
}
