package es.projectalpha.ac.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import es.projectalpha.ac.AVC;

public class ChairInteract implements Listener {

	private AVC plugin;

	public ChairInteract(AVC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (e.getClickedBlock() != null) {
			Block block = e.getClickedBlock();
			if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (!p.isInsideVehicle())) {
				if (block.getType() == Material.ACACIA_STAIRS || block.getType() == Material.BIRCH_WOOD_STAIRS || block.getType() == Material.WOOD_STAIRS || block.getType() == Material.DARK_OAK_STAIRS || block.getType() == Material.JUNGLE_WOOD_STAIRS) {

					Entity chair = p.getWorld().spawnEntity(p.getLocation(), EntityType.ARROW);

					chair.teleport(block.getLocation().add(0.2D, 0.5D, 0.2D));

					chair.setPassenger(p);

					e.setCancelled(true);
				}
			}
		}
	}
}
