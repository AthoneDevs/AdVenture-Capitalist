package es.projectalpha.ac.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.shops.Shops;

public class BuildInteract implements Listener {

	private AVC plugin;

	public BuildInteract(AVC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();

		if (Game.playing.contains(p)) {
			if (en instanceof Villager) {
				Villager v = (Villager) en;
				String name = v.getCustomName();

				for (int g = 0; g < Managers.values().length; g++) {
					Managers m = Managers.values()[g];

					if (name.equalsIgnoreCase(m.getManagerName())) {
						if (Cooldowns.isCooling(p.getName(), m.getName())) {
							Cooldowns.coolDurMessage(p, m.getName());
							return;
						}
						Game.progressBar.add(v.getLocation().add(0, 3, 0));
						Cooldowns.add(p.getName(), m.getName(), (long) Shops.valueOf(m.getName().toLowerCase()).getTimer(), System.currentTimeMillis());
					}
				}
			}
		}
	}
}
