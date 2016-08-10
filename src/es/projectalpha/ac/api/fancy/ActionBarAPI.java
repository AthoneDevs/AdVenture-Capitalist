package es.projectalpha.ac.api.fancy;

import java.lang.reflect.Constructor;

import org.bukkit.entity.Player;

import es.projectalpha.ac.api.ReflectionAPI;

public class ActionBarAPI {
	private static Class<?> packetClass = null;
	private static Class<?> componentClass = null;
	private static Class<?> serializerClass = null;
	private static Constructor<?> packetConstructor = null;

	public static void sendActionBar(Player p, String msg){
		try {
			packetClass = ReflectionAPI.getNmsClass("PacketPlayOutChat");
			componentClass = ReflectionAPI.getNmsClass("IChatBaseComponent");
			serializerClass = ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer");
			packetConstructor = packetClass.getConstructor(componentClass, byte.class);
			Object BaseComponent = serializerClass.getMethod("a", String.class).invoke(null, "{\"text\": \"" + msg + "\"}");
			Object packet = packetConstructor.newInstance(BaseComponent, (byte) 2);
			ReflectionAPI.sendPacket(p, packet);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
