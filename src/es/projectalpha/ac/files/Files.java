package es.projectalpha.ac.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import es.projectalpha.ac.utils.Messages;

public class Files {

	public static File fileplayers = new File("plugins/AC", "players.yml");
	public static YamlConfiguration players = YamlConfiguration.loadConfiguration(fileplayers);

	public static void setupFiles(){
		if (!fileplayers.exists()) {
			fileplayers.mkdir();
		}
		saveFiles();
	}

	public static void saveFiles(){
		try {
			players.save(fileplayers);
			players.load(fileplayers);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "There are some errors on: ");
			e.printStackTrace();
		}
	}
}
