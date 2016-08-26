package es.projectalpha.ac.avcEvents;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SanValentine extends Events {

	public SanValentine() {
		super("SanValentine", ChatColor.RED + "San Valentine", Date.valueOf(LocalDate.of(2016, 2, 14)), sanValentine(), 1000D);
	}

	private static ItemStack sanValentine() {
		List<String> lore = new ArrayList<String>();

		ItemStack i = new ItemStack(Material.RED_ROSE);
		ItemMeta im = i.getItemMeta();

		lore.add(ChatColor.AQUA + "To all people how have or haven't a partner today ^^");

		im.setDisplayName(ChatColor.RED + "San Valentine Rose");

		i.setItemMeta(im);

		return i;
	}
}
