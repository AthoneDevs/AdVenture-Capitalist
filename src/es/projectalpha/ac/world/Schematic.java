package es.projectalpha.ac.world;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

@SuppressWarnings("deprecation")
public class Schematic {

	public static void pasteSchematic(File dir, Location pasteLoc){
		try {
			EditSession editSession = new EditSession(new BukkitWorld(pasteLoc.getWorld()), Integer.MAX_VALUE);
			editSession.enableQueue();

			Vector position = BukkitUtil.toVector(pasteLoc);

			SchematicFormat.getFormat(dir).load(dir).paste(editSession, position, true, true);

			editSession.flushQueue();
		} catch (MaxChangedBlocksException | DataException | IOException e) {
			e.printStackTrace();
		}

	}
}
