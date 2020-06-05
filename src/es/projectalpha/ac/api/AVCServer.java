package es.projectalpha.ac.api;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class AVCServer {

    public static ArrayList<AVCUser> users = new ArrayList<>();


    public static AVCUser getUser(UUID id) {
        for (AVCUser u : users) {
            if (u.getUuid() == null) continue;
            if (u.getUuid().equals(id)) return u;
        }
        AVCUser us = new AVCUser(id);
        if (us.isOnline()) users.add(us);
        return us;
    }

    public static AVCUser getUser(OfflinePlayer p) {
        for (AVCUser u : users) {
            if (u.getUuid() == null) continue;
            if (u.getUuid().equals(p.getUniqueId())) return u;
        }
        AVCUser us = new AVCUser(p);
        if (us.isOnline()) users.add(us);
        return us;
    }
}
