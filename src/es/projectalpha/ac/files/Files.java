package es.projectalpha.ac.files;

import es.projectalpha.ac.utils.Download;
import es.projectalpha.ac.utils.Messages;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Files {

	//Config
	@Getter private File fileConfig = new File("plugins/AVC/", "config.yml");
	@Getter private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(fileConfig);

	//Utils Folder
	@Getter private File fileSchema = new File("plugins/AVC", "Utils");

	//Data
	@Getter private File filePlayers = new File("plugins/AVC/Data/", "players.yml");
	@Getter private YamlConfiguration players = YamlConfiguration.loadConfiguration(filePlayers);

	//
	private Download download = new Download();

	public void setupFiles(){
		if(!fileConfig.exists()){
			fileConfig.mkdir();
			cfg.set("MySQL.enabled", false);
			cfg.set("MySQL.host", "localhost");
			cfg.set("MySQL.port", "3306");
			cfg.set("MySQL.db", "avc");
			cfg.set("MySQL.user", "root");
			cfg.set("MySQL.pass", "123456");

			cfg.set("Dedicated_Server", false);
		}
		if(!filePlayers.exists()){
			filePlayers.mkdir();
			players.set("locs", 0);
		}
		if(!fileSchema.exists()){
			fileSchema.mkdirs();
			download.downloadSchematic();
		}
		saveFiles();
	}

	public void saveFiles(){
		try{
			cfg.save(fileConfig);
			cfg.load(fileConfig);

			players.save(filePlayers);
			players.load(filePlayers);
		}catch(IOException | InvalidConfigurationException e){
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "There are some errors on: ");
			e.printStackTrace();
		}
	}
}
