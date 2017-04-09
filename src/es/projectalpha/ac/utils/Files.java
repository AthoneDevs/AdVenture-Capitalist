package es.projectalpha.ac.utils;

import es.projectalpha.ac.AVC;
import lombok.Getter;

import java.io.File;

public class Files {

	private AVC plugin;
    private Download download = new Download();

    @Getter private File fConf;
    @Getter private File fUtils;

	public Files(AVC instance){
	    this.plugin = instance;
    }

	public void setupFiles(){
	    fConf = new File(plugin.getDataFolder(), "config.yml");
	    fUtils = new File(plugin.getDataFolder(), "/utils");

        if (!fConf.exists()) {
            try {
                plugin.getConfig().options().copyDefaults(true);
                plugin.saveConfig();
            } catch (Exception e) {}
        }

		if(!fUtils.exists()){
            fUtils.mkdir();
			download.downloadSchematic();
            plugin.saveConfig();
		}
	}
}
