package es.projectalpha.ac.world;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class Generator extends ChunkGenerator {

	@SuppressWarnings("deprecation")
	public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid){
		byte[][] result = new byte[world.getMaxHeight() / 16][];

		world.getBlockAt(new Location(world, 0, 126, 0)).setType(Material.GLASS);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				setBlock(result, x, 0, z, (byte) Material.AIR.getId());
			}
		}

		return result;
	}

	void setBlock(byte[][] result, int x, int y, int z, byte blkid){
		if (result[y >> 4] == null) {
			result[y >> 4] = new byte[4096];
		}
		result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
	}
}
