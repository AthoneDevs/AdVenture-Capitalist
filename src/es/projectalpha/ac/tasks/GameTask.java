package es.projectalpha.ac.tasks;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.AVCServer;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable {

    private AVC plugin;

    public GameTask(AVC instance){
        this.plugin = instance;
    }

    public void run(){
        AVCServer.users.forEach(u -> {

        });
    }
}
