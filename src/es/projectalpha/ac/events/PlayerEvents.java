package es.projectalpha.ac.events;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.AVCServer;
import es.projectalpha.ac.api.AVCUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerEvents implements Listener {

    private AVC plugin;

    public PlayerEvents(AVC instance){
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (e.getResult() == PlayerLoginEvent.Result.ALLOWED) plugin.getMysql().setupTable(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        AVCUser u = AVCServer.getUser(e.getPlayer());

    }
}
