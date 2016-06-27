package es.projectalpha.ac.api;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class AttackSpeedAPI {

	public static void setAttackSpeed(Player p, double speed){
		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(speed);
	}
}
