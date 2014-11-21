package dk.philiphansen.bluddegen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class BluddeGenerator implements IWorldGenerator {
	private static final int NETHER = -1;
	private static final int END = 1;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
	                     IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
			case NETHER:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;
			case END:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
		}
	}

	private void generateNether(World world, Random random, int xCoord, int zCoord) {
		setWorld(world, xCoord, zCoord, Blocks.lava, 0, 2);
	}

	private void generateEnd(World world, Random random, int xCoord, int zCoord) {
		setWorld(world, xCoord, zCoord, Blocks.obsidian);
	}

	private void setWorld(World world, int xCoord, int zCoord, Block block) {
		setWorld(world, xCoord, zCoord, block, 0, 3);
	}

	private void setWorld(World world, int xCoord, int zCoord, Block block, int metadata, int flag) {
		for (int i = 1; i <= 16; i++) {
			for (int j = 1; j <= 16; j++) {
				for (int k = 1; k <= world.getActualHeight(); k++) {
					int xCoordinate = xCoord + i;
					int zCoordinate = zCoord + j;
					int yCoordinate = k;

					Block blockAtLocation = world.getBlock(xCoordinate, yCoordinate, zCoordinate);
					if (blockAtLocation.getMaterial() == Material.air) {
						world.setBlock(xCoordinate, yCoordinate, zCoordinate, block, metadata, flag);
					}
				}
			}
		}
	}
}
