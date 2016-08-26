package es.projectalpha.ac.achievements;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.projectalpha.ac.utils.ItemsUtils;
import es.projectalpha.ac.utils.Messages;

public class AchievementsGUI {

	public static HashMap<Player, Integer> playerPage = new HashMap<Player, Integer>();

	private static ItemsUtils iu = new ItemsUtils();

	public static void openAchievementsGUI(Player p, int page) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Achievements");
		int tot = Achievements.values().length;
		List<ItemStack> items = iu.getItemsPerPageAchi(p, page);

		if (items.isEmpty()) {
			p.sendMessage(Messages.prefix + ChatColor.RED + "Sorry, but there is an error here");
			return;
		}

		for (ItemStack i : items) {
			inv.addItem(i);
		}

		if (page == 1 && Math.round(tot / 45) >= 1) {
			inv.setItem(50, getNextItem());
		}

		if (page >= 2) {
			if (Math.round(tot / 45) >= page) {
				inv.setItem(50, getNextItem());
			}
			inv.setItem(47, getPrevItem());
		}

		p.openInventory(inv);
	}

	public static ItemStack getNextItem() {
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Next Page");
		i.setItemMeta(im);

		return i;
	}

	public static ItemStack getPrevItem() {
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "Prev Page");
		i.setItemMeta(im);

		return i;
	}
}
