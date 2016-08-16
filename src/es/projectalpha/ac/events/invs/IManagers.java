package es.projectalpha.ac.events.invs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.managers.ManagersCore;

public class IManagers implements Listener {

	private AVC plugin;
	private ManagersCore mc = new ManagersCore();

	public IManagers(AVC Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onInteractShops(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getName().equals(ChatColor.GREEN + "Managers")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getItemMeta() == null) {
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
				return;
			}

			for (Managers m : Managers.values()) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(m.getName())) {
					e.setCancelled(true);
					p.closeInventory();

					mc.buyManager(p, m);
				}
			}
		}
	}
}
