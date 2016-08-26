package es.projectalpha.ac;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import es.projectalpha.ac.avcEvents.Events;
import es.projectalpha.ac.cmd.AVCCmd;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.events.ManagerInteract;
import es.projectalpha.ac.events.PlayerEvents;
import es.projectalpha.ac.events.ProtectWorld;
import es.projectalpha.ac.events.invs.IAchievements;
import es.projectalpha.ac.events.invs.IManagers;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.mysql.Data;
import es.projectalpha.ac.mysql.MySQL;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.utils.ServerVersion;
import es.projectalpha.ac.world.Generator;

public class AVC extends JavaPlugin{

	private AVCAPI api;

	// Only World, not Moon and Mars

	public static AVC plugin;

	@Override
	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
		Bukkit.getConsoleSender().sendMessage(" ");

		api = new AVCAPI();
		api.setDebug(false);
		//api.setPlugin(this);

		plugin = this;

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Checking Server Version. . .");
		if(!ServerVersion.isMC110() && !ServerVersion.isMC19()){
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please, update your server to 1.9.X or 1.10.X to use this plugin");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if(ServerVersion.isMC18()){
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

		if(!Bukkit.getWorlds().contains(Bukkit.getWorld("avc"))){

			Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Creating World. . .");
			WorldCreator wc = new WorldCreator("avc");

			wc.generator(getDefaultWorldGenerator("avc", "avc"));
			wc.environment(Environment.THE_END);
			wc.generateStructures(false);

			Bukkit.createWorld(wc);

			World w = Bukkit.getWorld("avc");

			w.setGameRuleValue("doDaylightCycle", "false");
			System.out.println(w.getGameRuleValue("doMobSpawning")); //Test
			w.setTime(14000);
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "World Created");

			Bukkit.getConsoleSender().sendMessage(" ");

		}

		if(Files.cfg.getBoolean("MySQL.enabled")){
			Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Connecting to MySQL. . .");
			api.setMySQL(new MySQL());
			api.setData(new Data());
			Bukkit.getConsoleSender().sendMessage(" ");
		}

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Registering Commands and Events. . .");
		regCMDs();
		regEvents();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Register Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Loading Game. . .");
		Events.loadEvents();
		api.getGame().startTimer();
		startCooldowns();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Game Loaded");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AVC enabled");
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AVC Version: " + ChatColor.RED + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AVC Autors: " + ChatColor.RED + getDescription().getAuthors().toString());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AVC Utils: " + ChatColor.RED + "https://github.com/ProjectAlphaES/AdVenture-Capitalist");

		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
	}

	@Override
	public void onDisable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GOLD + "Saving all. . . ");
		Bukkit.getScheduler().cancelTasks(this);

		for(Player p : api.getGame().playing){
			api.getCurrency().saveMoney(p);
		}

		//		if (api.getMySQL().checkConnection()) {
		//			api.getMySQL().closeConnection();
		//		}

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.AQUA + "All saved");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "AVC disabled");

		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
	}

	// Registers
	private void regEvents(){
		new ManagerInteract(this);
		new ProtectWorld(this);
		new IAchievements(this);
		new IManagers(this);
		new PlayerEvents(this);
	}

	private void regCMDs(){
		getCommand("avc").setExecutor(new AVCCmd());
	}

	// Cooldowns
	private void startCooldowns(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run(){
				Cooldowns.handleCooldowns();

				for(Player p : api.getGame().playing){
					if(Cooldowns.isCooling(p.getName(), "song")){
						Cooldowns.coolDurMessage(p, "song");
						return;
					}
					//Play Sound ResourcePackç
					p.playSound(p.getLocation(), Sound.RECORD_13, 6.0F, 6.0F);
					Cooldowns.add(p.getName(), "song", 1800L, System.currentTimeMillis());
				}
			}
		}, 1L, 1L);
	}

	//DATA

	//Added if you access to the Main class instead of the API class
	public AVCAPI getAPI(){
		return this.api;
	}

	//For Multiverse, the Plugin or Bukkit Settings
	//Do not try to load a world with this, leave the plugin works...
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new Generator();
	}
}
