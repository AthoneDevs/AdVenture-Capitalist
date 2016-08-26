package es.projectalpha.ac.cmd.sub.admin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.utils.Messages;

public class DebugCommand {

	private AVCAPI api = new AVCAPI();

	public void executeDebugCommand(Player p, String[] args){
		if(p.hasPermission("avc.admin")){
			if(api.getDebug()){
				api.setDebug(false);
				p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + api.getDebug());
				return;
			}
			api.setDebug(true);
			p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + api.getDebug());
			return;
		}else{
			p.sendMessage(Messages.noPerms);
		}
	}
}
