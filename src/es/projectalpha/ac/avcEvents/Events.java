package es.projectalpha.ac.avcEvents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import es.projectalpha.ac.files.Files;

public abstract class Events{

	//Own events, not like the game (ATM)

	//TODO: Check system to get it

	//Data
	public static Events SAN_VALENTINE = new SanValentine();

	public static ArrayList<Events> events = new ArrayList<Events>();

	//Class
	private String dataName;
	private String name;
	private Date date;
	private ItemStack icon;
	private double reward;

	public Events(String dataName, String name, Date date, ItemStack icon, double reward){
		this.dataName = dataName;
		this.name = name;
		this.date = date;
		this.icon = icon;
		this.reward = reward;
	}

	public String getDataName(){
		return this.dataName;
	}

	public String getName(){
		return this.name;
	}

	public Date getDate(){
		return this.date;
	}

	public ItemStack getIcon(){
		return this.icon;
	}

	public double getReward(){
		return this.reward;
	}

	public static void loadEvents(){
		events.add(SAN_VALENTINE);
	}

	private static boolean isInDay(Date d, Events e){
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		calendar1.setTime(d);
		calendar2.setTime(e.getDate());

		boolean sameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
		boolean sameDay = calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);

		return (sameDay && sameMonth);
	}

	public static void giveEvent(Player p, Events e){
		if(isInDay(new Date(), e) && !Files.players.getBoolean(p.getName() + ".events." + e.getDataName().toLowerCase())){
			Files.players.set(p.getName() + ".events." + e.getDataName().toLowerCase(), true);

			p.sendMessage(ChatColor.GOLD + "Congratulations, you claim the event " + e.getName());

			Files.saveFiles();
		}
	}
}
