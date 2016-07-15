package es.projectalpha.ac.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import es.projectalpha.ac.utils.Messages;

public class Files {

	//Config
	public static File fileConfig = new File("plugins/AC", "config.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(fileConfig);

	//Utils Folder
	public static File fileSchema = new File("plugins/AC", "Utils");
	public static YamlConfiguration schema = YamlConfiguration.loadConfiguration(fileSchema);

	//Data
	public static File filePlayers = new File("plugins/AC/Data", "players.yml");
	public static YamlConfiguration players = YamlConfiguration.loadConfiguration(filePlayers);

	public static File fileAchie = new File("plugins/AC/Data", "achievemetns.yml");
	public static YamlConfiguration achie = YamlConfiguration.loadConfiguration(fileAchie);

	public static File fileManagers = new File("plugins/AC/Data", "managers.yml");
	public static YamlConfiguration manager = YamlConfiguration.loadConfiguration(fileManagers);

	public static File fileLocs = new File("plugins/AC/Data", "locs.yml");
	public static YamlConfiguration locs = YamlConfiguration.loadConfiguration(fileLocs);

	public static File fileVill = new File("plugins/AC/Data", "villagers.yml");
	public static YamlConfiguration vill = YamlConfiguration.loadConfiguration(fileVill);

	public static void setupFiles(){
		if (!fileConfig.exists()) {
			fileConfig.mkdir();
			cfg.set("MySQL.enabled", false);
			cfg.set("MySQL.host", "localhost");
			cfg.set("MySQL.port", "3306");
			cfg.set("MySQL.db", "avc");
			cfg.set("MySQL.user", "root");
			cfg.set("MySQL.pass", "123456");
			cfg.set("Schematic.delay", 4);
		}
		if (!filePlayers.exists()) {
			filePlayers.mkdir();
		}
		if (!fileManagers.exists()) {
			fileManagers.mkdir();
		}
		if (!fileLocs.exists()) {
			fileLocs.mkdir();
			locs.set("num", 0);
		}
		if (!fileSchema.exists()) {
			fileSchema.mkdirs();
		}
		if (!fileAchie.exists()) {
			fileAchie.mkdir();
		}
		if (!fileVill.exists()) {
			fileVill.mkdir();
		}
		saveFiles();
	}

	public static void saveFiles(){
		try {
			cfg.save(fileConfig);
			cfg.load(fileConfig);
			players.save(filePlayers);
			players.load(filePlayers);
			locs.save(fileLocs);
			locs.load(fileLocs);
			manager.save(fileManagers);
			manager.load(fileManagers);
			vill.save(fileVill);
			vill.load(fileVill);
			achie.save(fileAchie);
			achie.load(fileAchie);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "There are some errors on: ");
			e.printStackTrace();
		}
	}
}
