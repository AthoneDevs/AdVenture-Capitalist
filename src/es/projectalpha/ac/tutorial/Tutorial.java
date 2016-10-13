package es.projectalpha.ac.tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import es.projectalpha.ac.utils.Messages;

public class Tutorial {

	public ItemStack getBookTutorial(){
		ItemStack i = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta im = (BookMeta) i.getItemMeta();

		im.setTitle(Messages.prefix + ChatColor.YELLOW + "Tutorial");
		im.setTitle(ChatColor.LIGHT_PURPLE + "Cadox8 & Wikitjo7");
		im.setLore(Arrays.asList(ChatColor.AQUA + "Read this book", ChatColor.AQUA + "to know how to play"));
		im.setPages(getBookPages());

		return i;
	}

	private List<String> getBookPages(){
		List<String> pages = new ArrayList<String>();

		pages.add(ChatColor.BOLD.toString() + ChatColor.RED + "Hello, welcome to AdVenture-Capitalist Game");
		pages.add("Introdction:");
		pages.add("Hope you enjoy the game");

		return pages;
	}
}
