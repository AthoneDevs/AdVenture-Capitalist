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
import es.projectalpha.ac.achievements.Achievements;
import es.projectalpha.ac.api.AttackSpeedAPI;
import es.projectalpha.ac.api.fancy.ActionBarAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Angels;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.managers.ManagersGUI;
import es.projectalpha.ac.managers.SpawnManagers;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.shops.ShopsCore;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.world.Schematic;

public class Help implements CommandExecutor {

	@SuppressWarnings("unused")
	private AVC plugin;
	private AchievementsCore achi = new AchievementsCore();
	private ShopsCore sc = new ShopsCore();
	private Currency c = new Currency();
	private Angels a = new Angels();

	public Help(AVC Main){
		this.plugin = Main;
	}

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
					//TODO: Make Methods
					return true;
				}

				if (args[0].equalsIgnoreCase("managers")) {
					ManagersGUI.openManagersGUI(p);
				}

				if (args[0].equalsIgnoreCase("debug")) {
					if (p.hasPermission("avc.admin")) {
						if (AVC.getDebug()) {
							AVC.setDebug(false);
							p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + AVC.getDebug());
							return true;
						}
						AVC.setDebug(true);
						p.sendMessage(Messages.prefix + "Debug: " + ChatColor.RED + AVC.getDebug());
					} else {
						p.sendMessage(Messages.noPerms);
					}
				}

				if (args[0].equalsIgnoreCase("play")) {
					World world = Bukkit.getWorld("ac");

					AttackSpeedAPI.setAttackSpeed(p, 16.0D);

					p.setFlying(true);
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

						p.teleport(l.add(0, 1, 0));

						c.loadMoney(p);

						p.sendMessage(Messages.tpCompany);

						return true;
					}
					int id = Files.locs.getInt("num");

					//New Game

					ActionBarAPI.sendActionBar(p, ChatColor.RED + "Have Fun :D");

					achi.addAchievement(p, Achievements.START);
					c.newPlayerMoney(p, 0);
					sc.addShop(p, Shops.LEMONADE);
					a.startGame(p);

					Messages.sendMapInfo(p);

					//Others
					if (id > 0) {
						double x = Files.locs.getDouble("id" + id + ".x");
						double y = Files.locs.getDouble("id" + id + ".y");
						double z = Files.locs.getDouble("id" + id + ".z");

						Location l = new Location(world, x, y, z);

						Location loc = l.clone().add(150, 0, 0);

						p.teleport(loc);

						id++;

						Files.locs.set("num", id);

						Files.players.set(p.getName() + ".id", id);

						Schematic.pasteSchematic(new File("plugins/AC/Utils/build.schematic"), p.getLocation());

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

						p.teleport(new Location(world, 0, 30, 0));

						Files.locs.set("num", id);

						Schematic.pasteSchematic(new File("plugins/AC/Utils/build.schematic"), p.getLocation());

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
					p.setFlying(false);
				}
			}
		}
		return false;
	}
}
