package es.projectalpha.ac;

import org.bukkit.plugin.java.JavaPlugin;

import es.projectalpha.ac.files.Files;

public class AC extends JavaPlugin {

	public void onEnable(){
		Files.setupFiles();
	}
}
