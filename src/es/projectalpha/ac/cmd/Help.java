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

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.achievements.Achievements;
import es.projectalpha.ac.achievements.AchievementsGUI;
import es.projectalpha.ac.api.AttackSpeedAPI;
import es.projectalpha.ac.api.fancy.ActionBarAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.ManagersGUI;
import es.projectalpha.ac.managers.SpawnManagers;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.world.Schematic;

public class Help implements CommandExecutor {

	private AVCAPI api = new AVCAPI();

	private String f = ChatColor.GRAY + " => ";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p;
		if (cmd.getName().equalsIgnoreCase("avc") && ((sender instanceof Player))) {
			p = (Player) sender;

			if (args.length == 0) {
				p.sendMessage(" ");
				p.sendMessage(Messages.prefix + ChatColor.AQUA + "AVC Help");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc play" + f + ChatColor.YELLOW + "Play AC");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc achievements" + f + ChatColor.YELLOW + "View your achievements");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc angels" + f + ChatColor.YELLOW + "View your angels");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc debug" + f + ChatColor.YELLOW + "Debug command");
				p.sendMessage(ChatColor.DARK_GREEN + "/avc managers" + f + ChatColor.YELLOW + "View avariable Managers");
				p.sendMessage(" ");
			}

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("achievements")) {
					AchievementsGUI.openAchievementsGUI(p);
				}

				if (args[0].equalsIgnoreCase("angels")) {
					Messages.sendAngelsInfo(p);
					return true;
				}

				if (args[0].equalsIgnoreCase("managers")) {
					ManagersGUI.openManagersGUI(p);
				}

				if (args[0].equalsIgnoreCase("debug")) {
					if (p.hasPermission("avc.admin")) {
						if (api.getDebug()) {
							api.setDebug(false);
							p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + api.getDebug());
							return true;
						}
						api.setDebug(true);
						p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + api.getDebug());
					} else {
						p.sendMessage(Messages.noPerms);
					}
				}

				if (args[0].equalsIgnoreCase("play")) {
					World world = Bukkit.getWorld("ac");

					AttackSpeedAPI.setAttackSpeed(p, 16.0D);

					p.setNoDamageTicks(Integer.MAX_VALUE);

					if (Files.players.contains(p.getName())) {
						int id = Files.players.getInt(p.getName() + ".id");

						double x = Files.locs.getDouble("id" + id + ".x");
						double y = Files.locs.getDouble("id" + id + ".y");
						double z = Files.locs.getDouble("id" + id + ".z");

						Location l = new Location(world, x, y, z);

						p.teleport(l.add(0, 1, 0));

						api.getCurrency().loadMoney(p);

						p.sendMessage(Messages.tpCompany);

						return true;
					}
					int id = Files.locs.getInt("num");

					//New Game

					ActionBarAPI.sendActionBar(p, ChatColor.RED + "Have Fun :D");

					api.getAchievements().addAchievement(p, Achievements.START);
					api.getCurrency().newPlayerMoney(p, 0);
					api.getShops().addShop(p, Shops.LEMONADE);
					api.getAngels().startGame(p);

					Messages.sendMapInfo(p);

					//Others
					if (id > 0) {
						double x = Files.locs.getDouble("id" + id + ".x");
						double y = Files.locs.getDouble("id" + id + ".y");
						double z = Files.locs.getDouble("id" + id + ".z");

						Location l = new Location(world, x, y, z);

						Location loc = l.clone().add(150, 0, 0);

						p.teleport(loc.add(0, 2, 0));

						id++;

						Files.locs.set("num", id);

						Files.players.set(p.getName() + ".id", id);

						Schematic.pasteSchematic(new File("plugins/AVC/Utils/build.schematic"), p.getLocation().add(0, -2, 0));

						Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0));

						if (b.getType() == Material.BEACON) {

							Files.locs.set("id" + id + ".x", b.getLocation().getX());
							Files.locs.set("id" + id + ".y", b.getLocation().getY() + 1);
							Files.locs.set("id" + id + ".z", b.getLocation().getZ());

							SpawnManagers.spawnManager(b.getLocation().add(0, 1, 0));

							p.teleport(new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1, b.getLocation().getZ()));

						}

						Files.saveFiles();

						//Firts Company
					} else {
						id++;

						p.teleport(new Location(world, 0, 30, 0).add(0, 2, 0));

						Files.locs.set("num", id);

						Schematic.pasteSchematic(new File("plugins/AVC/Utils/build.schematic"), p.getLocation().add(0, -2, 0));

						Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0));

						if (b.getType() == Material.BEACON) {

							Files.locs.set("id" + id + ".x", b.getLocation().getX());
							Files.locs.set("id" + id + ".y", b.getLocation().getY() + 1);
							Files.locs.set("id" + id + ".z", b.getLocation().getZ());

							SpawnManagers.spawnManager(b.getLocation().add(0, 1, 0));

							p.teleport(new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1, b.getLocation().getZ()));

						}

						Files.players.set(p.getName() + ".id", id);

						Files.saveFiles();
					}
				}
			}

			if (args.length == 2) {
				//JSON only
				if (args[0].equalsIgnoreCase("shops")) {
					String shop = args[1];

					api.getShops().buyShop(p, Shops.valueOf(shop.toUpperCase()));
				}
			}
		}
		return false;
	}
}
