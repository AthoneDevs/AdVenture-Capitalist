package es.projectalpha.ac.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.files.Files;

public class PlayerEvents implements Listener {

	private AVC plugin;
	private AVCAPI api;

	public PlayerEvents(AVC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		this.api = this.plugin.getAPI();
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();

		if(api.getGame().playing.contains(p)){
			api.getCurrency().saveMoney(p);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();

		if(Files.cfg.getBoolean("Dedicated_Server")){
			p.performCommand("/avc play");
		}
	}
}
