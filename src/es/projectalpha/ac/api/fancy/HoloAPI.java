package es.projectalpha.ac.api.fancy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class HoloAPI {

	private static String version;
	private static Class<?> craftWorld, entityClass, nmsWorld, armorStand, entityLiving, spawnPacket;

	static{
		version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";

		try{
			craftWorld = Class.forName("org.bukkit.craftbukkit." + version + "CraftWorld");
			entityClass = Class.forName("net.minecraft.server." + version + "Entity");
			nmsWorld = Class.forName("net.minecraft.server." + version + "World");
			armorStand = Class.forName("net.minecraft.server." + version + "EntityArmorStand");
			entityLiving = Class.forName("net.minecraft.server." + version + "EntityLiving");
			spawnPacket = Class.forName("net.minecraft.server." + version + "PacketPlayOutSpawnEntityLiving");
			Class.forName("net.minecraft.server." + version + "PacketPlayOutEntityDestroy");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private Location location;
	private List<String> lines = new ArrayList<String>();
	private List<Integer> ids = new ArrayList<Integer>();
	private List<Object> entities = new ArrayList<Object>();
	private double offset = 0.23D;

	public HoloAPI(Location location, String... text){
		this.location = location;
		addLine(text);
	}

	public HoloAPI(String... text){
		this(null, text);
	}

	public static String getVersion(){
		return version;
	}

	public void addLine(String... text){
		lines.addAll(Arrays.asList(text));
		update();
	}

	public List<String> getLines(){
		return lines;
	}

	public void setLines(String... text){
		lines = Arrays.asList(text);
		update();
	}

	public Location getLocation(){
		return location;
	}

	public void setLocation(Location location){
		this.location = location;
		update();
	}

	public void teleport(Location loc){
		update();
	}

	public void displayTo(Player... players){
		Location current = location.clone().add(0, (offset * lines.size()) - 1.97D, 0);

		for(String str : lines){
			Object[] packet = getCreatePacket(location, ChatColor.translateAlternateColorCodes('&', str));
			ids.add((Integer) packet[1]);

			for(Player player : players){
				sendPacket(player, packet[0]);
			}

			current.subtract(0, offset, 0);
		}
	}

	public void removeFrom(Player... players){
		Object packet = null;

		for(int id : ids){
			packet = getRemovePacket(id);
		}

		for(Player player : players){
			if(packet != null){
				sendPacket(player, packet);
			}
		}
	}

	public void spawn(){
		Location current = location.clone().add(0, (offset * lines.size()) - 1.97D, 0).add(0, offset, 0);

		for(String str : lines){
			spawnHologram(ChatColor.translateAlternateColorCodes('&', str), current.subtract(0, offset, 0));
		}
	}

	private void spawnHologram(String text, Location location){
		try{
			// The ArmorStand
			Object craftWorld = HoloAPI.craftWorld.cast(location.getWorld());
			Object entityObject = armorStand.getConstructor(nmsWorld).newInstance(HoloAPI.craftWorld.getMethod("getHandle").invoke(craftWorld));

			configureHologram(entityObject, text, location);

			HoloAPI.craftWorld.getMethod("addEntity", entityClass, CreatureSpawnEvent.SpawnReason.class).invoke(craftWorld, entityObject, CreatureSpawnEvent.SpawnReason.CUSTOM);

			entities.add(entityObject);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void remove(){
		for(Object ent : entities){
			removeEntity(ent);
		}
	}

	private void removeEntity(Object entity){
		try{
			Object craftWorld = HoloAPI.craftWorld.cast(location.getWorld());

			nmsWorld.getMethod("removeEntity", entityClass).invoke(HoloAPI.craftWorld.getMethod("getHandle").invoke(craftWorld), entity);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private Object[] getCreatePacket(Location location, String text){
		try{
			// The ArmorStand
			Object entityObject = armorStand.getConstructor(nmsWorld).newInstance(craftWorld.getMethod("getHandle").invoke(craftWorld.cast(location.getWorld())));
			Object id = entityObject.getClass().getMethod("getId").invoke(entityObject);

			configureHologram(entityObject, text, location);

			// Return the packet, and the entity id so we can later remove it.
			return new Object[] { spawnPacket.getConstructor(entityLiving).newInstance(entityObject), id };
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	private Object getRemovePacket(int id){
		try{
			Class<?> packet = Class.forName("net.minecraft.server." + version + "PacketPlayOutEntityDestroy");
			return packet.getConstructor(int[].class).newInstance(new int[] { id });
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public void update(){
		try{
			if(!entities.isEmpty()){ // spawned as an actual entity, moving is ezpz.

				for(int i = 0; i < entities.size(); i++){
					Object ent = entities.get(i);

					if(i > lines.size() - 1){
						removeEntity(ent);
					}
				}

				Location current = location.clone().add(0, (offset * lines.size()) - 1.97D, 0);

				for(int i = 0; i < lines.size(); i++){
					String text = ChatColor.translateAlternateColorCodes('&', lines.get(i));

					if(i >= entities.size()){
						spawnHologram(text, current);
					}else{
						configureHologram(entities.get(i), text, current);
					}

					current.subtract(0, offset, 0);
				}

			}else{ // TODO allow the user to update packet holograms

			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void configureHologram(Object entityObject, String text, Location location) throws Exception{
		// Methods for modifying the properties
		Method setCustomName = entityObject.getClass().getMethod("setCustomName", String.class);
		Method setCustomNameVisible = entityObject.getClass().getMethod("setCustomNameVisible", boolean.class);
		Method setNoGravity = entityObject.getClass().getMethod("setNoGravity", boolean.class); // Previously setGravity(boolean) prior to 1.10
		Method setLocation = entityObject.getClass().getMethod("setLocation", double.class, double.class, double.class, float.class, float.class);
		Method setInvisible = entityObject.getClass().getMethod("setInvisible", boolean.class);

		// Setting the properties
		setCustomName.invoke(entityObject, text);
		setCustomNameVisible.invoke(entityObject, true);
		setNoGravity.invoke(entityObject, true);
		setLocation.invoke(entityObject, location.getX(), location.getY(), location.getZ(), 0.0F, 0.0F);
		setInvisible.invoke(entityObject, true);
	}

	private void sendPacket(Player player, Object packet){
		try{
			if(packet == null){
				return;
			}

			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Object connection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
			connection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server." + version + "Packet")).invoke(connection, packet);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
