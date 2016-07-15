package es.projectalpha.ac.world;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.fromgate.scload.ScLoad;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

@SuppressWarnings("deprecation")
public class Schematic {

	public synchronized static void pasteSchematic(File dir, Location pasteLoc){

		try {

			ScLoad s = (ScLoad) Bukkit.getPluginManager().getPlugin("SCLoad");

			EditSession editSession = new EditSession(new BukkitWorld(pasteLoc.getWorld()), Integer.MAX_VALUE);
			editSession.enableQueue();

			Vector position = BukkitUtil.toVector(pasteLoc);

			SchematicFormat.getFormat(dir).load(dir).paste(editSession, position, true, true);

			editSession.flushQueue();

		} catch (DataException | IOException ex) {
			ex.printStackTrace();
		} catch (MaxChangedBlocksException ex) {
			ex.printStackTrace();
		}
	}
}
