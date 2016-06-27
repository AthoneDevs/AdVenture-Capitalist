package es.projectalpha.ac.api;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import es.projectalpha.ac.utils.Messages;

public class JsonAPI {

	public static void jsonURL(Player p, String text, String hover, ChatColor color, ChatColor color2, String url){
		TextComponent message = new TextComponent(Messages.prefix + color + text);
		message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color2 + hover).create()));
		p.spigot().sendMessage(message);
	}

	public static void jsonMessages(Player p, String text, String hover, ChatColor color2, String command){
		TextComponent message = new TextComponent(Messages.prefix + text);
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color2 + hover).create()));
		p.spigot().sendMessage(message);
	}
}
