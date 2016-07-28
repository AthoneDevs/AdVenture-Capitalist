package es.projectalpha.ac.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.NPCAPI;
import es.projectalpha.ac.api.TitleAPI;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.shops.ShopsCore;

public class ManagerInteract implements Listener {

	private AVC plugin;

	public ManagerInteract(AVC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();

		if (Game.playing.contains(p)) {
			if (en instanceof NPCAPI) {
				NPCAPI v = (NPCAPI) en;
				String name = v.getName();

				for (Managers m : Managers.values()) {
					if (name.equalsIgnoreCase(m.getManagerName())) {

						if (!ShopsCore.hasShop(p, ShopsCore.getShop(m))) {
							TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.DARK_RED + "Error!", ChatColor.AQUA + "You don't have this Shop");
							return;
						}

						if (Cooldowns.isCooling(p.getName(), m.getName())) {
							Cooldowns.coolDurMessage(p, m.getName());
							return;
						}
						Game.progressBar.add(v.getLocation().add(0, 3, 0));
						Cooldowns.add(p.getName(), m.getName(), (long) ShopsCore.getShop(m).getTimer(), System.currentTimeMillis());
					}
				}
			}
		}
	}
}
