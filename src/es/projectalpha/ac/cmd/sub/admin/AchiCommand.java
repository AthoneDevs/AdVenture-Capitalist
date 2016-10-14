package es.projectalpha.ac.cmd.sub.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.achievements.Achievements;
import es.projectalpha.ac.utils.Messages;

public class AchiCommand {

	private AVCAPI api = new AVCAPI();

	public void executeGetAchiCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);

		p.sendMessage(Messages.prefix + ChatColor.GOLD + "Achievements of " + ChatColor.AQUA + pl.getName() + ChatColor.GOLD + ":");
		p.sendMessage(ChatColor.RED + api.getAchievements().getAchievementByPlayer(pl).toString().replaceAll("[", "").replaceAll("]", ""));
	}

	public void executeAddAchiCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);
		Achievements a;

		if(!api.getAchievements().existAchievement(args[3])){
			p.sendMessage(Messages.prefix + ChatColor.RED + "This achievement doesn't exist");
			return;
		}

		a = Achievements.valueOf(args[3]);

		api.getAchievements().addAchievement(pl, a);

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + a.getDispName() + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}

	public void executeRemoveAchiCommand(Player p, String[] args){
		Player pl = Bukkit.getPlayer(args[2]);
		Achievements a;

		if(!api.getAchievements().existAchievement(args[3])){
			p.sendMessage(Messages.prefix + ChatColor.RED + "This achievement doesn't exist");
			return;
		}

		a = Achievements.valueOf(args[3]);

		api.getAchievements().remPlayerAchievement(p, a, pl);

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + a.getDispName() + ChatColor.GOLD + " to " + ChatColor.AQUA + pl.getName());
	}
}
