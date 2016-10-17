package es.projectalpha.ac.angels;

import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.money.Money;

public class Angels {

	private Money c = new Money();

	public void startGame(Player p){
		Files.players.set(p.getName() + ".angels", 0);
		Files.saveFiles();
	}

	public void addAngels(Player p, int amount){
		Files.players.set(p.getName() + ".angels", getAngels(p) + amount);
		Files.saveFiles();
	}

	public void removeAngels(Player p, int amount){
		if(getAngels(p) - amount <= 0){
			Files.players.set(p.getName() + ".angels", 0);
			Files.saveFiles();
			return;
		}
		Files.players.set(p.getName() + ".angels", getAngels(p) - amount);
		Files.saveFiles();
	}

	public int getAngels(Player p){
		return Files.players.getInt(p.getName() + ".angels");
	}

	public boolean checkIfEnoughMoneyToReset(Player p){
		if(calculateAngels(p) >= 1){
			return true;
		}
		return false;
	}

	//This uses an equation created by Kongregate's user Sodapants
	public int calculateAngels(Player p){
		return (int) (150 * Math.sqrt((c.getMaxMoney(p) / 1000000000000000D))); //Small number, no?
	}
}
