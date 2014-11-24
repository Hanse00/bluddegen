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

	private static final Block NETHER_FILLING = Blocks.lava;
	private static final Block END_FILLING = Blocks.obsidian;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
	                     IChunkProvider chunkProvider) {
		switch (getWorldId(world)) {
			case NETHER:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;
			case END:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
		}
	}

	private int getWorldId(World world) {
		return world.provider.dimensionId;
	}

	private void generateNether(World world, Random random, int chunkStartX, int chunkStartZ) {
		setChunk(world, chunkStartX, chunkStartZ, NETHER_FILLING, 0, 2);
	}

	private void generateEnd(World world, Random random, int chunkStartX, int chunkStartZ) {
		setChunk(world, chunkStartX, chunkStartZ, END_FILLING);
	}

	private void setChunk(World world, int chunkStartX, int chunkStartZ, Block block) {
		setChunk(world, chunkStartX, chunkStartZ, block, 0, 3);
	}

	private void setChunk(World world, int chunkStartX, int chunkStartZ, Block block, int metadata, int flag) {
		for (int x = 1; x <= 16; x++) {
			genXAxis(world, chunkStartX, chunkStartZ, block, metadata, flag, x);
		}
	}

	private void genXAxis(World world, int chunkStartX, int chunkStartZ, Block block, int metadata, int flag, int x) {
		for (int z = 1; z <= 16; z++) {
			genZAxis(world, chunkStartX, chunkStartZ, block, metadata, flag, x, z);
		}
	}

	private void genZAxis(World world, int chunkStartX, int chunkStartZ, Block block, int metadata, int flag, int x,
	                      int z) {
		for (int y = 1; y <= world.getActualHeight(); y++) {
			genYAxis(world, chunkStartX, chunkStartZ, block, metadata, flag, x, z, y);
		}
	}

	private void genYAxis(World world, int chunkStartX, int chunkStartZ, Block block, int metadata, int flag, int x,
	                      int z, int y) {
		int xCoord = chunkStartX + x;
		int zCoord = chunkStartZ + z;
		int yCoord = y;

		Block blockAtLocation = world.getBlock(xCoord, yCoord, zCoord);
		if (blockIsAir(blockAtLocation)) {
			world.setBlock(xCoord, yCoord, zCoord, block, metadata, flag);
		}
	}

	private boolean blockIsAir(Block block) {
		return block.getMaterial() == Material.air;
	}
}
