package es.projectalpha.ac.money;

import org.bukkit.entity.Player;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.utils.MoneyUtils;

public class Money {

	public String moneyName = "Dollars";
	public String moneySymbol = "$";

	public boolean existPlayer(Player p){
		if(Files.players.contains(p.getName())){
			return true;
		}
		return false;
	}

	public void addMoney(Player p, double amount){
		if(existPlayer(p)){
			AVC.runningCurrency.put(p, MoneyUtils.parseMoney(MoneyUtils.getMoney(p) + amount));
		}
	}

	public void removeMoney(Player p, double amount){
		if(existPlayer(p)){
			AVC.runningCurrency.put(p, MoneyUtils.parseMoney(MoneyUtils.getMoney(p) - amount));
		}
	}

	public void saveMoney(Player p){
		if(getMaxMoney(p) < MoneyUtils.getMoney(p)){
			Files.players.set(p.getName() + ".maxMoney", MoneyUtils.getMoney(p));
		}
		Files.players.set(p.getName() + ".money", MoneyUtils.getMoney(p));
		Files.saveFiles();
	}

	public void loadMoney(Player p){
		if(existPlayer(p)){
			AVC.runningCurrency.put(p, MoneyUtils.parseMoney(Files.players.getDouble(p.getName() + ".money")));
		}
	}

	public void newPlayerMoney(Player p){
		addMoney(p, 0);
		saveMoney(p);
	}

	public double getMaxMoney(Player p){
		return Files.players.getDouble(p.getName() + ".maxMoney");
	}
}
