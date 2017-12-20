package es.projectalpha.ac;

import es.projectalpha.ac.events.PlayerEvents;
import es.projectalpha.ac.jobs.JobManager;
import es.projectalpha.ac.utils.MySQL;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class AVC extends JavaPlugin {

    @Getter private static AVC instance;

    @Getter private MySQL mysql = null;
    private Connection connection = null;

    @Getter private JobManager jobManager;

	public void onEnable(){
	    instance = this;

        File fConf = new File(getDataFolder(), "config.yml");

        if (!fConf.exists()) {
            try {
                getConfig().options().copyDefaults(true);
                saveConfig();
            } catch (Exception e) {}
        }

        loadMySQL();

	    register();
	    registerEvents();
	}

	private void register(){
        jobManager = new JobManager();
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerEvents(instance), instance);
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
}
