package es.projectalpha.ac;

import es.projectalpha.ac.utils.Files;
import es.projectalpha.ac.utils.MySQL;
import es.projectalpha.ac.world.Generator;
import lombok.Getter;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public class AVC extends JavaPlugin {

    @Getter private static AVC instance;

    @Getter private MySQL mysql = null;
    private Connection connection = null;
	@Getter private Files files;

	public void onEnable(){
	    instance = this;

	    register();
		loadMySQL();
	}

	private void register(){
	    files = new Files(instance);
        files.setupFiles();
    }

    private void loadMySQL(){
	    String host = getConfig().getString("MySQL.host");
        String port = getConfig().getString("MySQL.port");
        String db = getConfig().getString("MySQL.db");
        String user = getConfig().getString("MySQL.user");
        String pass = getConfig().getString("MySQL.pass");
        try {
            mysql = new MySQL(host, port, db, user, pass);
            connection = mysql.openConnection();
        } catch (SQLException | ClassNotFoundException exc) {
            getLogger().severe("Error while loading MySQL. Plugin is disabled");
            getServer().getPluginManager().disablePlugin(this);
        }
    }


	//For Multiverse, the Plugin or Bukkit Settings
	//Do not try to load a world with this, leave the plugin works...
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new Generator();
	}
}
