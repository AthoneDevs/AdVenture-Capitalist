package es.projectalpha.ac.api;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.jobs.Job;
import es.projectalpha.ac.utils.Utils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.UUID;

public class AVCUser {

    private final AVC plugin = AVC.getInstance();

    @Getter private final UUID uuid;
    @Getter @Setter private UserData userData;

    public AVCUser(OfflinePlayer p) {
        this(p.getUniqueId());
    }
    public AVCUser(UUID id) {
        uuid = id;
        setUserData(plugin.getMysql().loadUserData(uuid));
        loadJobs();
    }

    public void save() {
        plugin.getMysql().saveUser(this);
        AVCServer.users.remove(this);
        plugin.getMysql().loadUserData(uuid);
        AVCServer.users.add(this);
    }


    public OfflinePlayer getOfflinePlayer() {
        return plugin.getServer().getOfflinePlayer(uuid);
    }
    public Player getPlayer() {
        return plugin.getServer().getPlayer(uuid);
    }
    public String getName() {
        return getOfflinePlayer().getName();
    }
    public boolean isOnline() {
        return getOfflinePlayer().isOnline();
    }



    public void sendMessage(String msg) {
        if (isOnline()) getPlayer().sendMessage(Utils.colorize(msg));
    }

    public void openInventory(Inventory inv){
        getPlayer().closeInventory();
        getPlayer().openInventory(inv);
    }


    private void loadJobs() {
        getUserData().getJobs().forEach(j -> {
            if (j.getLevel() != 0) j.activeJob();
        });
    }


    @Data @Getter @Setter
    public static class UserData {
        private double money = 0;

        private ArrayList<Job> jobs = AVC.getInstance().getJobManager().getJobs();
    }
}
