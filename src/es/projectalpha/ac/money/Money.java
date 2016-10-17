package es.projectalpha.ac.money;

import org.bukkit.entity.Player;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.utils.NumberUtils;

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
			AVC.runningCurrency.put(p, parseMoney(getMoney(p) + amount));
		}
	}

	public void removeMoney(Player p, double amount){
		if(existPlayer(p)){
			AVC.runningCurrency.put(p, parseMoney(getMoney(p) - amount));
		}
	}

	public void saveMoney(Player p){
		if(getMaxMoney(p) < getMoney(p)){
			Files.players.set(p.getName() + ".maxMoney", getMoney(p));
		}
		Files.players.set(p.getName() + ".money", getMoney(p));
		Files.saveFiles();
	}

	public void loadMoney(Player p){
		if(existPlayer(p)){
			AVC.runningCurrency.put(p, parseMoney(Files.players.getDouble(p.getName() + ".money")));
		}
	}

	public void newPlayerMoney(Player p){
		addMoney(p, 0);
		saveMoney(p);
	}

	public double getMaxMoney(Player p){
		return Files.players.getDouble(p.getName() + ".maxMoney");
	}

	public double getMoney(Player p){
		String currency = AVC.runningCurrency.get(p);
		String[] subString;

		if(!AVC.runningCurrency.containsKey(p)){
			return 0;
		}

		if(currency.contains("Million")){
			subString = currency.split(" ");

			return NumberUtils.getMillions(subString[0]);
		}

		if(currency.contains("Billion")){
			subString = currency.split(" ");

			return NumberUtils.getBillions(subString[0]);
		}

		if(currency.contains("Trillion")){
			subString = currency.split(" ");

			return NumberUtils.getTrillions(subString[0]);
		}

		subString = currency.split(" ");

		return Double.parseDouble(subString[0]);
	}

	public String getSMoney(Player p){
		double g = 1000000;

		if(getMoney(p) == g){
			return NumberUtils.getMillions(getMoney(p)) + moneySymbol;
		}

		if(getMoney(p) > g){
			return NumberUtils.getMillions(getMoney(p)) + "s" + moneySymbol;
		}

		if(getMoney(p) == g * 1000){
			return NumberUtils.getBillions(getMoney(p)) + moneySymbol;
		}

		if(getMoney(p) > g * 1000){
			return NumberUtils.getBillions(getMoney(p)) + "s" + moneySymbol;
		}

		if(getMoney(p) == g * 100000){
			return NumberUtils.getTrillions(getMoney(p)) + moneySymbol;
		}

		if(getMoney(p) > g * 100000){
			return NumberUtils.getTrillions(getMoney(p)) + "s" + moneySymbol;
		}

		return String.valueOf(getMoney(p)) + moneySymbol;
	}

	public String parseMoney(double currency){
		double g = 1000000;

		if(currency == g){
			return NumberUtils.getMillions(currency) + moneySymbol;
		}

		if(currency > g){
			return NumberUtils.getMillions(currency) + "s" + moneySymbol;
		}

		if(currency == g * 1000){
			return NumberUtils.getBillions(currency) + moneySymbol;
		}

		if(currency > g * 1000){
			return NumberUtils.getBillions(currency) + "s" + moneySymbol;
		}

		if(currency == g * 100000){
			return NumberUtils.getTrillions(currency) + moneySymbol;
		}

		if(currency > g * 100000){
			return NumberUtils.getTrillions(currency) + "s" + moneySymbol;
		}

		return String.valueOf(currency) + moneySymbol;
	}
}
