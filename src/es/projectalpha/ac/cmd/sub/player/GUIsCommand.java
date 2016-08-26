package es.projectalpha.ac.cmd.sub.player;

import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.achievements.AchievementsGUI;
import es.projectalpha.ac.game.ResetGame;
import es.projectalpha.ac.managers.ManagersGUI;
import es.projectalpha.ac.shops.Shops;
import es.projectalpha.ac.utils.Messages;

public class GUIsCommand {

	private AVCAPI api = new AVCAPI();

	public void executeAchiCommand(Player p, String[] args){
		AchievementsGUI.openAchievementsGUI(p, AchievementsGUI.playerPage.get(p));
	}

	public void executeManagersCommand(Player p, String[] args){
		ManagersGUI.openManagersGUI(p);
	}

	public void executeResetCommand(Player p, String[] args){
		ResetGame.resetGame(p);
	}

	public void executeAngelsCommand(Player p, String[] args){
		Messages.sendAngelsInfo(p);
	}

	public void executeShopsCommand(Player p, String[] args){
		String shop = args[1];

		api.getShops().buyShop(p, Shops.valueOf(shop.toUpperCase()));
	}
}
