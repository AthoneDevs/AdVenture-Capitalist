package es.projectalpha.ac;

import es.projectalpha.ac.api.ReflectionAPI;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class AVCPlayer {

    @Getter private final UUID uuid;

    private static final AVC plugin = AVC.getInstance();

    public AVCPlayer(OfflinePlayer p) {
        this(p.getUniqueId());
    }

    public AVCPlayer(UUID id) {
        uuid = id;
    }

    public void save() {
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

    public void sendMessage(String str) {
        plugin.getServer().getScheduler().runTask(plugin, () -> {
            if (isOnline()) {
                getPlayer().sendMessage(str);
            }
        });
    }

    //Reflection
    public void sendActionBar(String msg) {
        if (getPlayer() == null) return;
        try {
            Constructor<?> constructor = ReflectionAPI.getNMSClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNMSClass("IChatBaseComponent"), byte.class);

            Object icbc = ReflectionAPI.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + msg + "\"}");
            Object packet = constructor.newInstance(icbc, (byte) 2);
            Object entityPlayer= getPlayer().getClass().getMethod("getHandle").invoke(getPlayer());
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

            playerConnection.getClass().getMethod("sendPacket", ReflectionAPI.getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | InstantiationException ex) {}
    }

}
