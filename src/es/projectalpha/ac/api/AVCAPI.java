package es.projectalpha.ac.api;

import es.projectalpha.ac.achievements.AchievementsCore;
import es.projectalpha.ac.game.Angels;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.managers.ManagersCore;
import es.projectalpha.ac.modifiers.ModifiersCore;
import es.projectalpha.ac.shops.ShopsCore;

public class AVCAPI {

	private Game game = new Game();
	private Currency c = new Currency();
	private Angels a = new Angels();

	private AchievementsCore ac = new AchievementsCore();
	private ManagersCore mc = new ManagersCore();
	private ShopsCore sc = new ShopsCore();
	private ModifiersCore moc = new ModifiersCore();

	public Game getGame(){
		return this.game;
	}

	public Currency getCurrency(){
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
}
