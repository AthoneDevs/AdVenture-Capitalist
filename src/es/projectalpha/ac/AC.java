package es.projectalpha.ac;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.world.Generator;

public class AC extends JavaPlugin {

	public void onEnable(){
		Files.setupFiles();
	}

	//For Multiverse or Bukkit Settings
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new Generator();
	}
}
