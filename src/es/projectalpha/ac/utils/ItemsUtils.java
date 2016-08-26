package es.projectalpha.ac.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.achievements.Achievements;

public class ItemsUtils {

	private AVCAPI api = new AVCAPI();

	public List<ItemStack> getItemsPerPageAchi(Player p, int page) {
		List<ItemStack> items = new ArrayList<ItemStack>();
		List<String> lore = new ArrayList<String>();
		int min = 46;
		int tot = Achievements.values().length;

		lore.clear();
		items.clear();

		lore.add(ChatColor.GOLD + "Left Click to view");

		if (page == 1) {
			for (int x = 1; x < min; x++) {
				if (x > tot) {
					break;
				}
				if (api.getAchievements().hasAchievement(p, Achievements.values()[x])) {
					ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
					ItemMeta im = i.getItemMeta();
					im.setDisplayName(Achievements.values()[x].getDispName());
					im.setLore(Arrays.asList("Description: Right Click", "Reward: " + Achievements.values()[x].getReward()));
					i.setItemMeta(im);

					items.add(i);
				} else {
					ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
					ItemMeta im = i.getItemMeta();
					im.setDisplayName(Achievements.values()[x].getDispName());
					im.setLore(Arrays.asList("Description: Right Click", "Reward: " + Achievements.values()[x].getReward()));
					i.setItemMeta(im);

					items.add(i);
				}
			}
		}

		if (page >= 2) {
			for (int x = (min * page) - min; x < min * page; x++) {
				if (x > tot) {
					break;
				}
				if (api.getAchievements().hasAchievement(p, Achievements.values()[x])) {
					ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
					ItemMeta im = i.getItemMeta();
					im.setDisplayName(Achievements.values()[x].getDispName());
					im.setLore(Arrays.asList("Description: Right Click", "Reward: " + Achievements.values()[x].getReward()));
					i.setItemMeta(im);

					items.add(i);
				} else {
					ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
					ItemMeta im = i.getItemMeta();
					im.setDisplayName(Achievements.values()[x].getDispName());
					im.setLore(Arrays.asList("Description: Right Click", "Reward: " + Achievements.values()[x].getReward()));
					i.setItemMeta(im);

					items.add(i);
				}
			}
		}

		return items;
	}
}
