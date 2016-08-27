package es.projectalpha.ac.cmd.sub.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.utils.Messages;

public class ManagersCommand {

	private AVCAPI api = new AVCAPI();

	public void executeGetShopsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		p.sendMessage(Messages.prefix + ChatColor.GOLD + "Managers of " + ChatColor.AQUA + pl.getName() + ChatColor.GOLD + ":");
		p.sendMessage(ChatColor.RED + api.getManagers().getManagersByPlayer(pl).toString().replaceAll("[", "").replaceAll("]", ""));
	}

	public void executeAddShopsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);
		Managers m;

		if(api.getManagers().existManager(args[3])){
			p.sendMessage(Messages.prefix); //TODO: No manager exist message
			return;
		}

		m = Managers.valueOf(args[3]);

		api.getManagers().addManager(pl, m);

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + m.getManagerName() + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}

	public void executeRemoveShopsCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);
		Managers m;

		if(api.getManagers().existManager(args[3])){
			p.sendMessage(Messages.prefix); //TODO: No manager exist message
			return;
		}

		m = Managers.valueOf(args[3]);

		api.getManagers().removeManager(pl, m);

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Removed " + ChatColor.RED + m.getManagerName() + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}
}
