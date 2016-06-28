package es.projectalpha.ac.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import es.projectalpha.ac.utils.Messages;

public class Files {

	public static File filePlayers = new File("plugins/AC/Data", "players.yml");
	public static YamlConfiguration players = YamlConfiguration.loadConfiguration(filePlayers);

	public static File fileLocs = new File("plugins/AC/Data", "locs.yml");
	public static YamlConfiguration locs = YamlConfiguration.loadConfiguration(fileLocs);

	public static File fileSchema = new File("plugins/AC/Schematics", "delete.schematic");
	public static YamlConfiguration schema = YamlConfiguration.loadConfiguration(fileSchema);

	public static void setupFiles(){
		if (!filePlayers.exists()) {
			filePlayers.mkdir();
		}
		if (!fileLocs.exists()) {
			fileLocs.mkdir();
			locs.set("num", 0);
		}
		if (!fileSchema.exists()) {
			fileSchema.mkdir();
		}
		saveFiles();
	}

	public static void saveFiles(){
		try {
			players.save(filePlayers);
			players.load(filePlayers);
			schema.save(fileSchema);
			schema.load(fileSchema);
			locs.save(fileLocs);
			locs.load(fileLocs);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "There are some errors on: ");
			e.printStackTrace();
		}
	}
}
