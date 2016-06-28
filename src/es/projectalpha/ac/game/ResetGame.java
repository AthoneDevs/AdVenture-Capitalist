package es.projectalpha.ac.game;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.ManagerCore;

public class ResetGame {

	public static void resetGame(Player p){
		if (!Files.players.contains(p.getName())) {
			//TODO: Messages
			return;
		}

		Files.players.set(p.getName(), null);

		for (String manager : ManagerCore.managers) {
			ManagerCore.removeManager(p, manager);
		}

		//TODO: Add Angels
	}
}
