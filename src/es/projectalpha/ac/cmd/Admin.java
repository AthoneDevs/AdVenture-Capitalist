package es.projectalpha.ac.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.ac.utils.Messages;

public class Admin implements CommandExecutor {

	private String f = ChatColor.GRAY + " => ";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p;
		if (cmd.getName().equalsIgnoreCase("avca") && ((sender instanceof Player))) {
			p = (Player) sender;

			if (!p.hasPermission("avc.admin")) {
				p.sendMessage(Messages.noPerms);
				return true;
			}

			if (args.length == 0) {
				p.sendMessage(" ");
				p.sendMessage(Messages.prefix + ChatColor.AQUA + "AVC Admin Help");
				p.sendMessage(ChatColor.DARK_GREEN + "/avca play" + f + ChatColor.YELLOW + "Play AC");
				p.sendMessage(" ");
			}
		}
		return false;
	}
}
