package es.projectalpha.ac.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import es.projectalpha.ac.AC;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.game.Game;

public class BuildInteract implements Listener {

	private AC plugin;

	public BuildInteract(AC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();

		if (Game.playing.contains(p)) {
			if (en instanceof Villager) {
				//Villager ATM, Next NPC?
				Villager v = (Villager) en;
				String name = v.getCustomName();

				switch (name) {
				case "Limonade": //TODO: Villager's name in Messages
					if (Cooldowns.isCooling(p.getName(), "limonade")) {
						Cooldowns.coolDurMessage(p, "limonade");
						return;
					}
					Game.progressBar.add(v.getLocation().add(0, 3, 0));
					Cooldowns.add(p.getName(), "limonade", 60L, System.currentTimeMillis());
					break;

				default:
					break;
				}
			}
		}
	}
}
