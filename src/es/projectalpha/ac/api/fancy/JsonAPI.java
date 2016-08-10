package es.projectalpha.ac.api.fancy;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class JsonAPI {

	public static void sendJSONMessages(Player p, TextComponent... messages){
		p.spigot().sendMessage(messages);
	}

	public static TextComponent getJSONMessagesURL(String text, String hover, String url){
		TextComponent message = new TextComponent(text);
		message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));

		return message;
	}

	public static TextComponent getJSONMessages(String text, String hover, String command){
		TextComponent message = new TextComponent(text);
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));

		return message;
	}

	public static void jsonURL(Player p, String text, String hover, String url){
		TextComponent message = new TextComponent(text);
		message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
		p.spigot().sendMessage(message);
	}

	public static void jsonMessages(Player p, String text, String hover, String command){
		TextComponent message = new TextComponent(text);
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
		p.spigot().sendMessage(message);
	}
}
