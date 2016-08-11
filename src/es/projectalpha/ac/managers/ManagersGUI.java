package es.projectalpha.ac.managers;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ManagersGUI {

	private static ManagersCore mc = new ManagersCore();

	public static void openManagersGUI(Player p){
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.GREEN + "Managers");

		for (int g = 0; g < Managers.values().length; g++) {
			Managers m = Managers.values()[g];

			if (!mc.hasManager(p, m)) {
				ItemStack i = new ItemStack(m.getMaterial());
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(m.getName());
				im.setLore(Arrays.asList(ChatColor.AQUA + "Meet " + ChatColor.RED + m.getManagerName(), ChatColor.GRAY + "Right Click to buy"));
				i.setItemMeta(im);

				inv.addItem(i);
			}
		}

		p.openInventory(inv);
	}
}
