package es.projectalpha.ac.game;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.ManagerCore;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.utils.Messages;

public class ResetGame {

	public static void resetGame(Player p){
		if (!Files.players.contains(p.getName())) {
			p.sendMessage(Messages.noDatabase);
			return;
		}

		Files.players.set(p.getName(), null);

		for (int g = 0; g < Managers.values().length; g++) {
			ManagerCore.removeManager(p, Managers.values()[g]);
		}

		//TODO: Add Angels
	}
}
