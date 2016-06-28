package es.projectalpha.ac.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import es.projectalpha.ac.AC;
import es.projectalpha.ac.game.Game;

public class ProtectWorld implements Listener {

	private AC plugin;

	public ProtectWorld(AC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();

		if (Game.playing.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();

		if (Game.playing.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		Player p = e.getPlayer();

		if (Game.playing.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e){
		Entity en = e.getEntity();

		if (en instanceof Player) {
			Player p = (Player) en;
			if (Game.playing.contains(p)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void noDamage(EntityDamageByEntityEvent e){
		Entity en = e.getDamager();
		Entity en2 = e.getEntity();

		if (en instanceof Player || en2 instanceof Player) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void noDamage(EntityDamageByBlockEvent e){
		Entity en = e.getEntity();

		if (en instanceof Player) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void noFire(EntityCombustEvent e){
		if (e.getEntityType() == EntityType.ARMOR_STAND) {
			e.setCancelled(true);
		}
	}
}
