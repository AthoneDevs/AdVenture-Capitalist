package es.projectalpha.ac;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.world.Generator;
import lombok.Getter;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class AVC extends JavaPlugin {

    @Getter private static AVC instance;

	private Files files = new Files();

	public void onEnable(){
	    instance = this;

		files.setupFiles();
	}

	//For Multiverse, the Plugin or Bukkit Settings
	//Do not try to load a world with this, leave the plugin works...
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new Generator();
	}
}
