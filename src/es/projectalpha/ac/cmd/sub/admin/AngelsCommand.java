package es.projectalpha.ac.cmd.sub.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.utils.NumberUtils;

public class AngelsCommand {

	private AVCAPI api = new AVCAPI();

	public void executeGetAngelsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		Messages.sendAngelsInfo(p, pl);
	}

	public void executeAddAngelsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		if(!NumberUtils.isInt(args[3])){
			p.sendMessage(Messages.prefix + ChatColor.GRAY + args[3] + ChatColor.RED + " isn't a number");
			return;
		}

		api.getAngels().addAngels(pl, Integer.parseInt(args[3]));

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + Integer.parseInt(args[3]) + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}

	public void executeRemoveAngelsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		if(!NumberUtils.isInt(args[3])){
			p.sendMessage(Messages.prefix + ChatColor.GRAY + args[3] + ChatColor.RED + " isn't a number");
			return;
		}

		api.getAngels().removeAngels(pl, Integer.parseInt(args[3]));

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + Integer.parseInt(args[3]) + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}
}
