package es.projectalpha.ac.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AC;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.world.Loaders;

public class Help implements CommandExecutor {

	@SuppressWarnings("unused")
	private AC plugin;

	public Help(AC Main){
		this.plugin = Main;
	}

	private String f = ChatColor.GRAY + " => ";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		final Player p;
		if (cmd.getName().equalsIgnoreCase("lom") && ((sender instanceof Player))) {
			p = (Player) sender;

			if (args.length == 0) {
				p.sendMessage(" ");
				p.sendMessage(Messages.prefix + ChatColor.AQUA + "AC Help");
				p.sendMessage(ChatColor.DARK_GREEN + "/ac play" + f + ChatColor.YELLOW + "Play AC");
				p.sendMessage(" ");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("play")) {
					for (World w : Bukkit.getWorlds()) {
						if (w.getName().equalsIgnoreCase("ac")) {
							//TODO: Last Location
							p.teleport(new Location(w, 0, 146, 0));
							Loaders.putSchematic(p, "plugins/AC/Schematics", "build.schematic");
						}
					}
				}
			}
		}
		return false;
	}
}
