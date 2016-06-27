package es.projectalpha.ac.cooldowns;

import java.util.HashMap;

public class ShopCooldown {

	public String ability = "";
	public String player = "";
	public long seconds;
	public long systime;

	public HashMap<String, ShopCooldown> cooldownMap = new HashMap<String, ShopCooldown>();

	public ShopCooldown(String player, long seconds, long systime){
		this.player = player;
		this.seconds = seconds;
		this.systime = systime;
	}

	public ShopCooldown(String player){
		this.player = player;
	}
}
