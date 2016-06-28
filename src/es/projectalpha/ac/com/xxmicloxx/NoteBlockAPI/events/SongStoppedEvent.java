package es.projectalpha.ac.com.xxmicloxx.NoteBlockAPI.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import es.projectalpha.ac.com.xxmicloxx.NoteBlockAPI.SongPlayer;

public class SongStoppedEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private SongPlayer song;

	public SongStoppedEvent(SongPlayer song){
		this.song = song;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}

	public SongPlayer getSongPlayer(){
		return song;
	}

	public HandlerList getHandlers(){
		return handlers;
	}
}
