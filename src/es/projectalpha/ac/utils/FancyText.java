package es.projectalpha.ac.utils;

import es.projectalpha.ac.api.AVCUser;
import lombok.NonNull;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class FancyText {

    private TextComponent fancy;

    public FancyText(String text){
        this(text, " ");
    }

    public FancyText(@NonNull String text, @NonNull String hover){
        fancy = new TextComponent(text);
        fancy.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
    }

    public FancyText command(@NonNull String command){
        fancy.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return this;
    }

    public FancyText url(@NonNull String url){
        fancy.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        return this;
    }

    public void sendFancy(@NonNull AVCUser player){
        player.getPlayer().spigot().sendMessage(fancy);
    }
}
