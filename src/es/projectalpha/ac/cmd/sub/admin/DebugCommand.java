package es.projectalpha.ac.cmd.sub.admin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.utils.Messages;

public class DebugCommand {

	public void executeDebugCommand(Player p, String[] args){
		if(p.hasPermission("avc.admin")){
			if(AVC.debug){
				AVC.debug = false;
				p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + AVC.debug);
				return;
			}
			AVC.debug = true;
			p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + AVC.debug);
			return;
		}else{
			p.sendMessage(Messages.noPerms);
		}
	}
}
