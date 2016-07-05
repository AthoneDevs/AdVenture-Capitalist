package es.projectalpha.ac.world;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import es.projectalpha.ac.org.jnbt.ByteArrayTag;
import es.projectalpha.ac.org.jnbt.CompoundTag;
import es.projectalpha.ac.org.jnbt.NBTInputStream;
import es.projectalpha.ac.org.jnbt.ShortTag;
import es.projectalpha.ac.org.jnbt.StringTag;
import es.projectalpha.ac.org.jnbt.Tag;

public class Loaders {

	public static void putSchematic(Location l, String folder, String name){
		try {
			Schematic schematic = loadSchematic(new File(folder, name));

			System.out.println(schematic);

			pasteSchematic(l.getWorld(), l, schematic);

			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
			//TODO: GZIP bug
		}
	}

	//Library-API from p000ison (https://bukkit.org/members/p000ison.84488/)

	@SuppressWarnings("deprecation")
	public static void pasteSchematic(World world, Location loc, Schematic schematic){
		byte[] blocks = schematic.getBlocks();
		byte[] blockData = schematic.getData();

		short length = schematic.getLenght();
		short width = schematic.getWidth();
		short height = schematic.getHeight();

		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				for (int z = 0; z < length; ++z) {
					int index = y * width * length + z * width + x;
					Block block = new Location(world, x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();
					block.setTypeIdAndData(blocks[index], blockData[index], true);
				}
			}
		}
	}

	@SuppressWarnings("resource")
	public static Schematic loadSchematic(File file) throws IOException{
		FileInputStream stream = new FileInputStream(file);
		NBTInputStream nbtStream = new NBTInputStream(new GZIPInputStream(stream));

		CompoundTag schematicTag = (CompoundTag) nbtStream.readTag();
		if (!schematicTag.getName().equals("Schematic")) {
			throw new IllegalArgumentException("Tag \"Schematic\" does not exist or is not first");
		}

		Map<String, Tag> schematic = schematicTag.getValue();
		if (!schematic.containsKey("Blocks")) {
			throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
		}

		short width = getChildTag(schematic, "Width", ShortTag.class).getValue();
		short length = getChildTag(schematic, "Length", ShortTag.class).getValue();
		short height = getChildTag(schematic, "Height", ShortTag.class).getValue();

		String materials = getChildTag(schematic, "Materials", StringTag.class).getValue();
		if (!materials.equals("Alpha")) {
			throw new IllegalArgumentException("Schematic file is not an Alpha schematic");
		}

		byte[] blocks = getChildTag(schematic, "Blocks", ByteArrayTag.class).getValue();
		byte[] blockData = getChildTag(schematic, "Data", ByteArrayTag.class).getValue();
		return new Schematic(blocks, blockData, width, length, height);
	}

	private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException{
		if (!items.containsKey(key)) {
			throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
		}
		Tag tag = items.get(key);
		if (!expected.isInstance(tag)) {
			throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
		}
		return expected.cast(tag);
	}
}
