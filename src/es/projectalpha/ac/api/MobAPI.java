package es.projectalpha.ac.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class MobAPI {

	public static void noAI(Entity entity){
		LivingEntity le = (LivingEntity) entity;
		le.setAI(false);
	}
}
