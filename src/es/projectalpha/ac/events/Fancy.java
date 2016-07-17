package es.projectalpha.ac.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.utils.BlocksUtils;

public class Fancy implements Listener {

	private AVC plugin;

	public Fancy(AVC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location l = p.getLocation();

		BlocksUtils.setLights(l);
	}
}
