package es.projectalpha.ac.achievements;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import es.projectalpha.ac.api.fancy.FireworkAPI;
import es.projectalpha.ac.api.fancy.TitleAPI;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.money.Money;
import es.projectalpha.ac.utils.Messages;

public class AchievementsCore {

	private Game game = new Game();
	private Money c = new Money();

	public void addAchievement(Player p, Achievements a){
		if(hasAchievement(p, a)){
			return;
		}else{
			List<String> players = Files.achie.getStringList(a.toString());

			players.add(p.getName());

			Files.achie.set(a.toString(), players);
			Files.saveFiles();

			Messages.newAchievement(a, p);
			TitleAPI.sendTitle(p, 0, 3, 0, "", ChatColor.GREEN + "New Achievement!");
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5.0F, 5.0F);

			for(int g = 0; g < 4; g++){
				FireworkAPI.spawnFirework(p);
			}
		}
	}

	public void remAchievement(Player p, Achievements a){
		if(hasAchievement(p, a)){
			List<String> players = Files.achie.getStringList(a.toString());

			players.remove(p.getName());

			Files.achie.set(a.toString(), players);
			Files.saveFiles();
		}
	}

	public boolean hasAchievement(Player p, Achievements a){
		if(Files.achie.getStringList(a.toString()).contains(p)){
			return true;
		}
		return false;
	}

	public List<String> getAchievementByPlayer(Player p){
		List<String> achi = new ArrayList<String>();

		for(Achievements a : Achievements.values()){
			if(hasAchievement(p, a)){
				achi.add(a.getDispName());
			}
		}

		return achi;
	}

	public boolean existAchievement(String a){
		if(Achievements.valueOf(a.toUpperCase()) == null){
			return false;
		}
		return true;
	}

	public void checkAchievements(){
		for(Player p : game.playing){
			if(c.getMoney(p) >= 5000){
				addAchievement(p, Achievements.PC);
			}
		}
	}
}
