package es.projectalpha.ac.utils;

import org.bukkit.Bukkit;

public class ServerVersion {

	public static boolean isMC110(){
		return Bukkit.getBukkitVersion().contains("1.10");
	}

	public static boolean isMC19(){
		return Bukkit.getBukkitVersion().contains("1.9");
	}

	public static boolean isMC18(){
		return Bukkit.getBukkitVersion().contains("1.8");
	}
}
