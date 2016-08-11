package es.projectalpha.ac.achievements;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AchievementsGUI {

	private static AchievementsCore achi = new AchievementsCore();

	public static void openAchievementsGUI(Player p){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Achievements");

		for (int g = 0; g < AchievementsType.values().length; g++) {
			AchievementsType at = AchievementsType.values()[g];
			if (achi.hasAchievement(p, at)) {
				ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(at.getDispName());
				im.setLore(Arrays.asList("Description: Right Click", "Reward: " + at.getReward()));
				i.setItemMeta(im);

				inv.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(at.getDispName());
				im.setLore(Arrays.asList("Description: Right Click", "Reward: " + at.getReward()));
				i.setItemMeta(im);

				inv.addItem(i);
			}
		}

		//		ItemStack i = new ItemStack(Material.BOW);
		//		ItemMeta im = i.getItemMeta();
		//		im.setDisplayName("Next Page");
		//		i.setItemMeta(im);
		//
		//		inv.setItem(49, i);

		p.openInventory(inv);
	}
}
