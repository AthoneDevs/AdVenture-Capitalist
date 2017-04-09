package es.projectalpha.ac.utils;

import lombok.NonNull;
import org.bukkit.ChatColor;

public class Utils {

    public static String colorize(@NonNull String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
