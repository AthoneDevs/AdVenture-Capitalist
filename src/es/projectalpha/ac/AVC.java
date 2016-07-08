package es.projectalpha.ac;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import es.projectalpha.ac.cmd.Help;
import es.projectalpha.ac.events.BuildInteract;
import es.projectalpha.ac.events.ProtectWorld;
import es.projectalpha.ac.events.invs.IAchievements;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.managers.ManagerCore;
import es.projectalpha.ac.shops.VillagerShops;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.utils.ServerVersion;
import es.projectalpha.ac.world.Generator;

public class AVC extends JavaPlugin {

	private static AVC plugin;

	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
		Bukkit.getConsoleSender().sendMessage(" ");

		plugin = this;

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Checking Server Version. . .");
		if (!ServerVersion.isMC110() && !ServerVersion.isMC19()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please, update your server to 1.9.X or 1.10.X to use this plugin");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (ServerVersion.isMC18()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please, update your server to 1.9.X or 1.10.X to use this plugin, 1.8.X is not supported");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Checking Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Checking and Creating files. . .");
		Files.setupFiles();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Setup Files Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		if (!Bukkit.getWorlds().contains(Bukkit.getWorld("ac"))) {

			Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Creating World. . .");
			Bukkit.createWorld(new WorldCreator("ac").generator(getDefaultWorldGenerator("ac", "ac")).environment(Environment.NORMAL));
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "World Created");

			Bukkit.getConsoleSender().sendMessage(" ");

		}

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Registering Commands and Events. . .");
		regCMDs();
		regEvents();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Register Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Loading Game. . .");
		ManagerCore.loadManagers();
		VillagerShops.loadVillagers();
		Game.startTimer(this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Game Loaded");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC enabled");
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC Version: " + ChatColor.RED + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC Autor: " + ChatColor.RED + getDescription().getAuthors().toString());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC Utils: " + ChatColor.RED + "https://github.com/ProjectAlphaES/AdVenture-Capitalist");

		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
	}

	public void onDisable(){

		for (Player p : Game.playing) {
			Currency.saveCurrency(p);
		}

	}

	private void regEvents(){
		new BuildInteract(this);
		new ProtectWorld(this);
		new IAchievements(this);
	}

	private void regCMDs(){
		getCommand("avc").setExecutor(new Help(this));
	}

	public static AVC getPlugin(){
		return plugin;
	}

	//For Multiverse or Bukkit Settings
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new Generator();
	}
}
