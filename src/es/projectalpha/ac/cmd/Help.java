package es.projectalpha.ac.cmd;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.achievements.AchievementsCore;
import es.projectalpha.ac.achievements.AchievementsGUI;
import es.projectalpha.ac.achievements.AchievementsType;
import es.projectalpha.ac.api.ActionBarAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.shops.VillagerShops;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.world.Schematic;

public class Help implements CommandExecutor {

	@SuppressWarnings("unused")
	private AVC plugin;

	public Help(AVC Main){
		this.plugin = Main;
	}

	private boolean debug = false;

	private String f = ChatColor.GRAY + " => ";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p;
		if (cmd.getName().equalsIgnoreCase("avc") && ((sender instanceof Player))) {
			p = (Player) sender;

			if (args.length == 0) {
				p.sendMessage(" ");
				p.sendMessage(Messages.prefix + ChatColor.AQUA + "AC Help");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc play" + f + ChatColor.YELLOW + "Play AC");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc achievements" + f + ChatColor.YELLOW + "View your achievements");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc angel" + f + ChatColor.YELLOW + "View your angels");
				p.sendMessage(" ");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("achievements")) {
					AchievementsGUI.openAchievementsGUI(p);
				}

				if (args[0].equalsIgnoreCase("debug")) {
					if (p.hasPermission("avc.admin")) {
						if (debug) {
							debug = false;
							p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + debug);
							return true;
						}
						debug = true;
						p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + debug);
					} else {
						p.sendMessage(Messages.noPerms);
					}
				}

				if (args[0].equalsIgnoreCase("play")) {
					World world = Bukkit.getWorld("ac");

					p.teleport(new Location(world, 0, 100, 0));
					p.setNoDamageTicks(Integer.MAX_VALUE);

					//							Song song = NBSDecoder.parse(new File("/plugins/AC/Utils/ac.nbs"));
					//							SongPlayer sp = new RadioSongPlayer(song);
					//							sp.addPlayer(p);
					//							sp.setPlaying(true);

					if (Files.players.contains(p.getName())) {
						int id = Files.players.getInt(p.getName() + ".id");

						double x = Files.locs.getDouble("id" + id + ".x");
						double y = Files.locs.getDouble("id" + id + ".y");
						double z = Files.locs.getDouble("id" + id + ".z");

						Location l = new Location(world, x, y, z);

						p.teleport(l.add(0, 2, 0));

						Currency.loadCurrency(p);

						p.sendMessage(Messages.tpCompany);

						return true;
					}
					int id = Files.locs.getInt("num");

					ActionBarAPI.sendActionBar(p, ChatColor.RED + "Have Fun :D");

					AchievementsCore.addAchievement(p, AchievementsType.START);

					Currency.newPlayerCurrency(p);

					//Others
					if (id > 0) {
						double x = Files.locs.getDouble("id" + id + ".x");
						double y = Files.locs.getDouble("id" + id + ".y");
						double z = Files.locs.getDouble("id" + id + ".z");

						Location l = new Location(world, x, y, z);

						Location loc = l.clone().add(150, 0, 0);

						if (l.getWorld().getBlockAt(l.add(150, 0, 0)) != null || l.getWorld().getBlockAt(l.add(150, 0, 0)).getType() != Material.AIR) {
							loc = l.clone().add(150, y, 150);
						}

						id++;

						Files.locs.set("num", id);

						Files.players.set(p.getName() + ".id", id);

						Schematic.pasteSchematic(new File("plugins/AC/Utils/build.schematic"), p.getLocation());

						Block b;
						for (int g = 0; g < 30; g++) {

							if (debug) {
								System.out.println(loc.getWorld().getBlockAt((int) (x + 28), (int) y - 1, (int) (z - g)) + "");

								if (loc.getWorld().getBlockAt((int) (x + 28), (int) y - 1, (int) (z - g)).getType() == Material.STAINED_GLASS) {
									System.out.print("Found Block!");
								}
							}

							b = loc.getWorld().getBlockAt((int) (x + 28), (int) y - 1, (int) (z - g));
							if (b.getType() == Material.STAINED_GLASS) {

								Files.locs.set("id" + id + ".x", b.getLocation().getX());
								Files.locs.set("id" + id + ".y", b.getLocation().getY() + 1);
								Files.locs.set("id" + id + ".z", b.getLocation().getZ());

								p.teleport(new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1, b.getLocation().getZ()));

								break;
							}
						}

						Files.saveFiles();

						//Firts Company
					} else {
						id++;

						Files.locs.set("num", id);

						Schematic.pasteSchematic(new File("plugins/AC/Utils/build.schematic"), p.getLocation());

						Block b;
						for (int g = 0; g < 30; g++) {

							if (debug) {
								System.out.println(p.getWorld().getBlockAt((int) (p.getLocation().getX() + 28), (int) p.getLocation().getY() - 1, (int) (p.getLocation().getZ() - g)) + "");

								if (p.getWorld().getBlockAt((int) (p.getLocation().getX() + 28), (int) p.getLocation().getY() - 1, (int) (p.getLocation().getZ() - g)).getType() == Material.STAINED_GLASS) {
									System.out.print("Found Block!");
								}
							}

							b = p.getWorld().getBlockAt((int) (p.getLocation().getX() + 28), (int) p.getLocation().getY() - 1, (int) (p.getLocation().getZ() - g));
							if (b.getType() == Material.STAINED_GLASS) {

								Files.locs.set("id" + id + ".x", b.getLocation().getX());
								Files.locs.set("id" + id + ".y", b.getLocation().getY() + 1);
								Files.locs.set("id" + id + ".z", b.getLocation().getZ());

								p.teleport(new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1, b.getLocation().getZ()));

								break;
							}
						}

						Files.players.set(p.getName() + ".id", id);

						Files.saveFiles();
					}
					VillagerShops.loadVillagers();
				}
			}
		}
		return false;
	}
}
