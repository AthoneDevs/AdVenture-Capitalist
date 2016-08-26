package es.projectalpha.ac.cmd.sub;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.utils.Messages;

public class NormalCommand {

	private String f = ChatColor.GRAY + " => ";

	private ChatColor dg = ChatColor.DARK_GREEN;
	private ChatColor y = ChatColor.YELLOW;

	public void executeNormalCommand(Player p, String[] args){
		p.sendMessage(" ");
		p.sendMessage(Messages.prefix + y + "AVC Help");
		p.sendMessage(dg + "/avc play" + f + y + "Play AC");
		p.sendMessage(dg + "/avc achievements" + f + y + "View your achievements");
		p.sendMessage(dg + "/avc angels" + f + y + "View your angels");
		p.sendMessage(dg + "/avc managers" + f + y + "View avariable Managers");
		p.sendMessage(dg + "/avc reset" + f + y + "Re-start your game");
		p.sendMessage(dg + "/avc admin" + f + y + "To see admin commands");
		p.sendMessage(" ");
	}

	public void executeAdminCommand(Player p, String[] args){
		if(!p.hasPermission("avc.admin")){
			p.sendMessage(Messages.noPerms);
			return;
		}

		p.sendMessage(" ");
		p.sendMessage(Messages.prefix + ChatColor.AQUA + "AVC Admin Help");
		p.sendMessage(dg + "/avc debug" + f + y + "Debug command (Console)");
		p.sendMessage(dg + "/avc achi <add/remove/get> <Achievement (only for add/remove)> <player>" + f + y + "Achievements Manager");
		p.sendMessage(dg + "/avc money <add/remove/get> <money (only for add/remove)> <player>" + f + y + "Money Manager");
		p.sendMessage(dg + "/avc shops <add/remove/get> <Shop (only for add/remove)> <player>" + f + y + "Shops Manager");
		p.sendMessage(dg + "/avc managers <add/remove/get> <Manager (only for add/remove)> <player>" + f + y + "Managers Manager");
		p.sendMessage(dg + "/avc modifiers <get> <Shop> <player>" + f + y + "Modifiers Manager");
		p.sendMessage(dg + "/avc angels <add/remove/get/check> <Angels (only for add/remove)> <player>" + f + y + "Angels Manager");
		p.sendMessage(" ");
	}
}
