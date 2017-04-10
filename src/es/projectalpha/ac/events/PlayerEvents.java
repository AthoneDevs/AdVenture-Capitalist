package es.projectalpha.ac.events;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.AVCUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    private AVC plugin;

    public PlayerEvents(AVC instance){
        this.plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        plugin.getMysql().loadUserData(p.getUniqueId());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();

        plugin.getMysql().saveUser(new AVCUser(p.getUniqueId()));
    }
}
