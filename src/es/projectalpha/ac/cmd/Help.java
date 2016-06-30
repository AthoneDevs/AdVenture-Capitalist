package es.projectalpha.ac.cmd;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AC;
import es.projectalpha.ac.achievements.AchievementsCore;
import es.projectalpha.ac.achievements.AchievementsType;
import es.projectalpha.ac.api.TitleAPI;
import es.projectalpha.ac.com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import es.projectalpha.ac.com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import es.projectalpha.ac.com.xxmicloxx.NoteBlockAPI.Song;
import es.projectalpha.ac.com.xxmicloxx.NoteBlockAPI.SongPlayer;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
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
		if (cmd.getName().equalsIgnoreCase("ac") && ((sender instanceof Player))) {
			p = (Player) sender;

			if (args.length == 0) {
				p.sendMessage(" ");
				p.sendMessage(Messages.prefix + ChatColor.AQUA + "AC Help");
				p.sendMessage(ChatColor.DARK_GREEN + "/ac play" + f + ChatColor.YELLOW + "Play AC");
				p.sendMessage(" ");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("play")) {
					for (World world : Bukkit.getWorlds()) {
						if (world.getName().equalsIgnoreCase("ac")) {
							//TODO: Messages

							Song song = NBSDecoder.parse(new File("/plugins/AC/Utils/ac.nbs"));
							SongPlayer sp = new RadioSongPlayer(song);
							sp.addPlayer(p);
							sp.setPlaying(true);

							if (Files.players.contains(p.getName())) {
								int id = Files.players.getInt(p.getName() + ".id");

								double x = Files.locs.getDouble("id" + id + ".x");
								double y = Files.locs.getDouble("id" + id + ".y");
								double z = Files.locs.getDouble("id" + id + ".z");

								Location l = new Location(world, x, y, z);

								p.teleport(l.add(0, 2, 0));

								Currency.loadCurrency(p);

								return true;
							}
							int id = Files.locs.getInt("num");

							TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.RED + "Have Fun :D", "");

							AchievementsCore.addAchievement(p, AchievementsType.START);

							if (id > 0) {
								double x = Files.locs.getDouble("id" + id + ".x");
								double y = Files.locs.getDouble("id" + id + ".y");
								double z = Files.locs.getDouble("id" + id + ".z");

								Location l = new Location(world, x, y, z);

								Location loc = l.clone().add(100, 0, 0);

								if (l.getWorld().getBlockAt(l.add(100, 0, 0)) != null || l.getWorld().getBlockAt(l.add(100, 0, 0)).getType() != Material.AIR) {
									loc = l.clone().add(100, y, 100);
								}

								id++;

								Files.locs.set("num", id);

								Files.locs.set("id" + id + ".x", loc.getX());
								Files.locs.set("id" + id + ".y", loc.getY());
								Files.locs.set("id" + id + ".z", loc.getZ());
								Files.saveFiles();

								Loaders.putSchematic(loc, "plugins/AC/Utils", "build.schematic");
								p.teleport(loc.add(0, 2, 0));
							} else {
								id++;

								Files.locs.set("num", id);

								Files.locs.set("id" + id + ".x", p.getLocation().getX());
								Files.locs.set("id" + id + ".y", p.getLocation().getY());
								Files.locs.set("id" + id + ".z", p.getLocation().getZ());
								Files.saveFiles();

								Loaders.putSchematic(p.getLocation(), "plugins/AC/Utils", "build.schematic");
								p.teleport(p.getLocation().add(0, 2, 0));
							}
						}
					}
				}
			}
		}
		return false;
	}
}
