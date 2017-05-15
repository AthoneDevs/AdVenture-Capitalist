package es.projectalpha.ac.api;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.utils.ReflectionAPI;
import es.projectalpha.ac.utils.Utils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    }


    public void save(){
        plugin.getMysql().saveUser(this);

        setUserData(plugin.getMysql().loadUserData(getUuid()));
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

    public void setAttackSpeed(double speed){
        getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(speed);
    }


    @Data
    public static class UserData {
        @Getter @Setter private double money;
    }
}
