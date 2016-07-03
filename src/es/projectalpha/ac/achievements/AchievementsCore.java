package es.projectalpha.ac.achievements;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import es.projectalpha.ac.api.FireworkAPI;
import es.projectalpha.ac.api.TitleAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.utils.Messages;

public class AchievementsCore {

	public static void addAchievement(Player p, AchievementsType at){
		if (!hasAchievement(p, at)) {
			List<String> players = Files.achie.getStringList(at.toString());

			players.add(p.getName());

			Files.achie.set(at.toString(), players);
			Files.saveFiles();

			Messages.newAchievement(at, p);
			TitleAPI.sendTitle(p, 0, 3, 0, "", ChatColor.GREEN + "New Achievement!");
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5.0F, 5.0F);

			for (int g = 0; g < 4; g++) {
				FireworkAPI.spawnFirework(p);
			}
		}
		//If have achievement do not do nothing
	}

	public static void remAchievement(Player p, AchievementsType at){
		if (hasAchievement(p, at)) {
			List<String> players = Files.achie.getStringList(at.toString());

			players.remove(p.getName());

			Files.achie.set(at.toString(), players);
			Files.saveFiles();
		}
		//If not have achievement do not do nothing
	}

	public static boolean hasAchievement(Player p, AchievementsType at){
		if (Files.achie.getStringList(at.toString()).contains(p)) {
			return true;
		}
		return false;
	}

	public static void checkAchievements(){
		for (Player p : Game.playing) {
			if (Currency.getCurrency(p) >= 5000) {
				AchievementsCore.addAchievement(p, AchievementsType.PC);
			}
		}
	}
}
