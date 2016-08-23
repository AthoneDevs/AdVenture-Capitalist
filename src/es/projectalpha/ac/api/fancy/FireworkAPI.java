package es.projectalpha.ac.api.fancy;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkAPI {

	public static void spawnFirework(Player p){
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();

		Random r = new Random();

		int rt = r.nextInt(5);
		Type type = Type.BALL;

		switch (rt) {
		case 0:
			type = Type.BALL;
			break;
		case 1:
			type = Type.BALL_LARGE;
			break;
		case 2:
			type = Type.BURST;
			break;
		case 3:
			type = Type.CREEPER;
			break;
		case 4:
			type = Type.STAR;
			break;
		default:
			type = Type.BALL;
			//Just for security (or not, line 22)
			break;
		}

		int r1i = r.nextInt(17) + 1;
		int r2i = r.nextInt(17) + 1;

		Color c1 = getColor(r1i);
		Color c2 = getColor(r2i);

		FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

		fwm.addEffect(effect);

		int rp = r.nextInt(2) + 1;
		fwm.setPower(rp);

		fw.setFireworkMeta(fwm);
	}

	private static Color getColor(int i){
		switch (i) {
		case 1:
			return Color.AQUA;
		case 2:
			return Color.BLACK;
		case 3:
			return Color.BLUE;
		case 4:
			return Color.FUCHSIA;
		case 5:
			return Color.GRAY;
		case 6:
			return Color.GREEN;
		case 7:
			return Color.LIME;
		case 8:
			return Color.MAROON;
		case 9:
			return Color.NAVY;
		case 10:
			return Color.OLIVE;
		case 11:
			return Color.ORANGE;
		case 12:
			return Color.PURPLE;
		case 13:
			return Color.RED;
		case 14:
			return Color.SILVER;
		case 15:
			return Color.TEAL;
		case 16:
			return Color.WHITE;
		case 17:
			return Color.YELLOW;
		default:
			return Color.AQUA;
		}
	}
}
