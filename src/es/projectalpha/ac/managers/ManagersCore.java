package es.projectalpha.ac.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.money.Money;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.utils.MoneyUtils;

public class ManagersCore {

	private Money c = new Money();

	public void addManager(Player p, Managers m){
		List<String> playersNames = Files.manager.getStringList(m.getdataName());

		if(hasManager(p, m)){
			p.sendMessage(Messages.hasManager);
			return;
		}

		playersNames.add(p.getName());

		Files.manager.set(m.getdataName(), playersNames);
		Files.saveFiles();
	}

	public void removeManager(Player p, Managers m){
		List<String> playersNames = Files.manager.getStringList(m.getdataName());

		if(!hasManager(p, m)){
			return;
		}

		playersNames.remove(p.getName());

		Files.manager.set(m.getdataName(), playersNames);
		Files.saveFiles();
	}

	public void removePlayerManager(Player p, Managers m, Player pl){
		List<String> playersNames = Files.manager.getStringList(m.getdataName());

		if(!hasManager(pl, m)){
			p.sendMessage(Messages.notHasManager);
			return;
		}

		playersNames.remove(pl.getName());

		Messages.remManager(m, p, pl);

		Files.manager.set(m.getdataName(), playersNames);
		Files.saveFiles();
	}

	public void buyManager(Player p, Managers m){
		if(hasManager(p, m)){
			p.sendMessage(Messages.hasManager);
			return;
		}

		if(MoneyUtils.getMoney(p) < m.getPrice()){
			p.sendMessage(Messages.notEnoughMoney);
			return;
		}

		c.removeMoney(p, m.getPrice());
		addManager(p, m);
		p.sendMessage(Messages.buyManager);
	}

	public ArrayList<Player> getPlayersWithManager(Managers m){
		ArrayList<Player> players = new ArrayList<Player>();

		for(String s : Files.manager.getStringList(m.getdataName())){
			players.add(Bukkit.getPlayer(s));
		}

		return players;
	}

	public List<String> getManagersByPlayer(Player p){
		List<String> manager = new ArrayList<String>();

		for(Managers m : Managers.values()){
			if(hasManager(p, m)){
				manager.add(m.getManagerName());
			}
		}

		return manager;
	}

	public boolean existManager(String m){
		if(Managers.valueOf(m.toUpperCase()) == null){
			return false;
		}
		return true;
	}

	public boolean hasManager(Player p, Managers m){
		if(Files.manager.getStringList(m.getdataName()).contains(p)){
			return true;
		}
		return false;
	}
}
