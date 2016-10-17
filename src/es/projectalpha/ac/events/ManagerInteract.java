package es.projectalpha.ac.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.fancy.HoloAPI;
import es.projectalpha.ac.api.fancy.JsonAPI;
import es.projectalpha.ac.api.fancy.TitleAPI;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.managers.ManagersCore;
import es.projectalpha.ac.shops.ShopsCore;
import es.projectalpha.ac.utils.Messages;

public class ManagerInteract implements Listener {

	private AVC plugin;
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

		System.out.println(AVC.playing.toString());
		System.out.println(en);
		System.out.println(en.getCustomName());

		if(AVC.playing.contains(p)){
			if(en instanceof Villager){
				Villager v = (Villager) en;
				String name = v.getCustomName();

				for(Managers m : Managers.values()){
					if(name.equalsIgnoreCase(m.getManagerName())){
						if(!sc.hasShop(p, m.getShop())){
							TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.DARK_RED + "Error!", ChatColor.AQUA + "You don't have this Shop");
							JsonAPI.jsonMessages(p, ChatColor.GREEN + "[Click to buy Shop]", ChatColor.AQUA + m.getShop().toString().toLowerCase(), "/avc shops " + m.getShop().toString().toLowerCase());
							return;
						}

						if(!mc.hasManager(p, m)){
							TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.DARK_RED + "Error!", ChatColor.AQUA + "You don't have this Manager");
							return;
						}

						if(Cooldowns.isCooling(p.getName(), m.getName())){
							Cooldowns.coolDurMessage(p, m.getName());
							return;
						}

						AVC.progressBar.put(v.getLocation().add(0, 3, 0), m.getManagerName());

						//TODO: Better Hologram System

						HoloAPI holo = new HoloAPI(v.getLocation().add(0, 3, 0), Messages.getProgress(p, v.getLocation().add(0, 3, 0), m.getShop()));

						holo.display(p);

						AVC.holos.add(holo);

						Cooldowns.add(p.getName(), m.getName(), (long) m.getShop().getTimer(), System.currentTimeMillis());
					}
				}
			}
		}
	}
}
