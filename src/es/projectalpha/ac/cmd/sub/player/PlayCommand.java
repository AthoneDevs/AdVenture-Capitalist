package es.projectalpha.ac.cmd.sub.player;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.achievements.Achievements;
import es.projectalpha.ac.api.AttackSpeedAPI;
import es.projectalpha.ac.api.fancy.ActionBarAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.SpawnManagers;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.utils.Randoms;
import es.projectalpha.ac.world.Schematic;

public class PlayCommand {

	private AVCAPI api = new AVCAPI();
	private Randoms r = new Randoms();

	public void executePlayCommand(Player p, String[] args){
		World world = Bukkit.getWorld("avc");

		if(api.getGame().playing.contains(p)){
			p.sendMessage(Messages.alreadyPlaying);
			return;
		}

		//					if(Events.checkIfEvent() != null){
		//						TitleAPI.sendTitle(p, 0, 4, 0, Events.checkIfEvent().getName(), ChatColor.AQUA + "New Event!");
		//					}

		AttackSpeedAPI.setAttackSpeed(p, 16.0D);

		p.setNoDamageTicks(Integer.MAX_VALUE);

		if(Files.players.contains(p.getName())){
			int id = Files.players.getInt(p.getName() + ".id");

			double x = Files.locs.getDouble("id" + id + ".x");
			double y = Files.locs.getDouble("id" + id + ".y");
			double z = Files.locs.getDouble("id" + id + ".z");

			Location l = new Location(world, x, y, z);

			p.teleport(l.add(0, 1, 0));

			api.getCurrency().loadMoney(p);

			p.sendMessage(Messages.tpCompany);

			return;
		}

		Files.players.set(p.getName(), r.getPlayerID());

		int id = Files.locs.getInt("num");

		//New Game

		ActionBarAPI.sendActionBar(p, ChatColor.RED + "Have Fun :D");

		api.getAchievements().addAchievement(p, Achievements.START);
		api.getShops().addShop(p, Shops.LEMONADE);
		api.getAngels().startGame(p);
		api.getCurrency().newPlayerMoney(p); //INFO: If game doesn't load, remove this

		Messages.sendMapInfo(p);

		//Others
		if(id > 0){
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

			for(int k = 0; k < 5; k++){
				Block b = p.getWorld().getBlockAt(p.getLocation().clone().add(0, -k, 0));

				if(api.getDebug()){
					System.out.println(Messages.parseLoc(b.getLocation()) + " " + b.getType());
				}

				if(b.getType() == Material.BEACON){

					Files.locs.set("id" + id + ".x", b.getLocation().getX());
					Files.locs.set("id" + id + ".y", b.getLocation().getY() + 1);
					Files.locs.set("id" + id + ".z", b.getLocation().getZ());

					SpawnManagers.spawnManager(b.getLocation().add(0, 1, 0));

					p.teleport(new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1, b.getLocation().getZ()));

				}
			}

			Files.saveFiles();

			//Firts Company
		}else{
			id++;

			p.teleport(new Location(world, 0.5, 30, 0.5));

			Files.locs.set("num", id);

			Schematic.pasteSchematic(new File("plugins/AVC/Utils/build.schematic"), p.getLocation());

			for(int k = 0; k < 4; k++){
				Block b = p.getWorld().getBlockAt(p.getLocation().clone().add(0, -k, 0));

				if(api.getDebug()){
					System.out.println(Messages.parseLoc(b.getLocation()) + " " + b.getType());
				}

				if(b.getType() == Material.BEACON){

					Files.locs.set("id" + id + ".x", b.getLocation().getX());
					Files.locs.set("id" + id + ".y", b.getLocation().getY() + 1);
					Files.locs.set("id" + id + ".z", b.getLocation().getZ());

					SpawnManagers.spawnManager(b.getLocation().add(0, 1, 0));

					p.teleport(new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1, b.getLocation().getZ()));

				}
			}

			Files.players.set(p.getName() + ".id", id);

			Files.saveFiles();
		}
	}
}
