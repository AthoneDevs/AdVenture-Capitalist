package es.projectalpha.ac.world;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
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

			SchematicFormat schematic = SchematicFormat.getFormat(dir);

			CuboidClipboard clipboard = schematic.load(dir);

			clipboard.paste(editSession, BukkitUtil.toVector(pasteLoc), true, true);

			editSession.flushQueue();
		} catch (DataException | IOException ex) {
			ex.printStackTrace();
		} catch (MaxChangedBlocksException ex) {
			ex.printStackTrace();
		}
	}
}
