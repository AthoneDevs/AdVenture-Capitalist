package es.projectalpha.ac.game;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.ManagersCore;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.utils.Messages;

public class ResetGame {

	private static ManagersCore mc = new ManagersCore();

	public static void resetGame(Player p){
		if (!Files.players.contains(p.getName())) {
			p.sendMessage(Messages.noDatabase);
			return;
		}

		Files.players.set(p.getName(), null);

		for (Managers m : Managers.values()) {
			mc.removeManager(p, m);
		}

		//TODO: Add Angels
	}
}
