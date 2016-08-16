package es.projectalpha.ac.game;

import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.achievements.Achievements;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.utils.Messages;

public class ResetGame {

	private static AVCAPI api = new AVCAPI();

	public static void resetGame(Player p){
		if (!Files.players.contains(p.getName())) {
			p.sendMessage(Messages.noDatabase);
			return;
		}

		if (!api.getAngels().checkIfEnoughMoneyToReset(p)) {
			p.sendMessage(Messages.notEnoughMoneyToRestart);
			return;
		}

		Files.players.set(p.getName(), null);

		for (Managers m : Managers.values()) {
			api.getManagers().removeManager(p, m);
		}

		for (Shops s : Shops.values()) {
			api.getShops().removeShop(p, s);
		}

		for (Achievements a : Achievements.values()) {
			api.getAchievements().remAchievement(p, a);
		}

		api.getAngels().addAngels(p, api.getAngels().calculateAngels(p));
	}
}
