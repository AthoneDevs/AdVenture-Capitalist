package es.projectalpha.ac;

import es.projectalpha.ac.achievements.AchievementsCore;
import es.projectalpha.ac.angels.Angels;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.managers.ManagersCore;
import es.projectalpha.ac.modifiers.ModifiersCore;
import es.projectalpha.ac.money.Money;
import es.projectalpha.ac.mysql.Data;
import es.projectalpha.ac.mysql.MySQL;
import es.projectalpha.ac.shops.ShopsCore;

public class AVCAPI {

	private Game game = new Game();
	private Money c = new Money();
	private Angels a = new Angels();

	private AchievementsCore ac = new AchievementsCore();
	private ManagersCore mc = new ManagersCore();
	private ShopsCore sc = new ShopsCore();
	private ModifiersCore moc = new ModifiersCore();

	private MySQL mysql;
	private Data data;

	public AVCAPI(){
	}

	public void loadAPIs(){
		game = new Game();
		c = new Money();
		a = new Angels();

		ac = new AchievementsCore();
		mc = new ManagersCore();
		sc = new ShopsCore();
		moc = new ModifiersCore();
	}

	public Game getGame(){
		return this.game;
	}

	public Money getCurrency(){
		return this.c;
	}

	public Angels getAngels(){
		return this.a;
	}

	public AchievementsCore getAchievements(){
		return this.ac;
	}

	public ManagersCore getManagers(){
		return this.mc;
	}

	public ShopsCore getShops(){
		return this.sc;
	}

	public ModifiersCore getModifiers(){
		return this.moc;
	}

	public void setMySQL(MySQL mysql){
		this.mysql = mysql;
	}

	public void setData(Data data){
		this.data = data;
	}

	public MySQL getMySQL(){
		return this.mysql;
	}

	public Data getData(){
		return this.data;
	}
}
