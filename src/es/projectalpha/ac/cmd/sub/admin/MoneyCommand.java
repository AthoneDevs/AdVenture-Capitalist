package es.projectalpha.ac.cmd.sub.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.utils.NumberUtils;

public class MoneyCommand {

	private AVCAPI api = new AVCAPI();

	public void executeGetMoneyCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		p.sendMessage(Messages.prefix + ChatColor.GOLD + "Money of " + ChatColor.AQUA + pl.getName() + ChatColor.GOLD + ": " + ChatColor.RED + api.getCurrency().getSMoney(pl));
	}

	public void executeAddMoneyCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		if(!NumberUtils.isDouble(args[3])){
			p.sendMessage(Messages.prefix + ChatColor.GRAY + args[3] + ChatColor.RED + " isn't a number");
			return;
		}

		api.getCurrency().addMoney(pl, Double.parseDouble(args[3]));

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + Double.parseDouble(args[3]) + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}

	public void executeRemoveMoneyCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		if(!NumberUtils.isDouble(args[3])){
			p.sendMessage(Messages.prefix + ChatColor.GRAY + args[3] + ChatColor.RED + " isn't a number");
			return;
		}

		api.getCurrency().removeMoney(pl, Double.parseDouble(args[3]));

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Removed " + ChatColor.RED + Double.parseDouble(args[3]) + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}
}
