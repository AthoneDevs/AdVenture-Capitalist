package es.projectalpha.ac.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.NPCAPI;
import es.projectalpha.ac.api.fancy.JsonAPI;
import es.projectalpha.ac.api.fancy.TitleAPI;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.managers.ManagersCore;
import es.projectalpha.ac.shops.ShopsCore;

public class ManagerInteract implements Listener {

	private AVC plugin;
	private Game game = new Game();
	private ShopsCore sc = new ShopsCore();
	private ManagersCore mc = new ManagersCore();

	public ManagerInteract(AVC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();

		if (game.playing.contains(p)) {
			if (en instanceof NPCAPI) {
				NPCAPI v = (NPCAPI) en;
				String name = v.getName();

				for (Managers m : Managers.values()) {
					if (name.equalsIgnoreCase(m.getManagerName())) {
						if (!sc.hasShop(p, m.getShop())) {
							TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.DARK_RED + "Error!", ChatColor.AQUA + "You don't have this Shop");
							JsonAPI.jsonMessages(p, ChatColor.GREEN + "[Click to buy Shop]", ChatColor.AQUA + m.getShop().toString().toLowerCase(), "/avc shops " + m.getShop().toString().toLowerCase());
							return;
						}

						if (mc.hasManager(p, m)) {
							TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.DARK_RED + "Error!", ChatColor.AQUA + "You can't click in a shop with a Manager");
							return;
						}

						if (Cooldowns.isCooling(p.getName(), m.getName())) {
							Cooldowns.coolDurMessage(p, m.getName());
							return;
						}
						game.progressBar.add(v.getLocation().add(0, 3, 0));
						Cooldowns.add(p.getName(), m.getName(), (long) m.getShop().getTimer(), System.currentTimeMillis());
					}
				}
			}
		}
	}
}
