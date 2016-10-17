package es.projectalpha.ac.cmd.sub.player;

import org.bukkit.entity.Player;

import com.gmail.cadox8.screen.data.Screen;

public class StatsCommand {

	public void executeStatsCommand(Player p, String[] args){
		Screen screen = new Screen(p);

		screen.drawText(30, 50, "Hello World");
		screen.drawRect(0, 0, 5, 20);

		//Test API
	}
}
