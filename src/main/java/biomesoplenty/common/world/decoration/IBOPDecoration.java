package biomesoplenty.common.world.decoration;

import java.util.Random;

import biomesoplenty.common.world.features.WorldGenBOPFlora;

public interface IBOPDecoration
{
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random);
	
	public WorldGenBOPFlora getRandomWorldGenForBOPPlants(Random random);
	
	public int getWorldGenPerChunk(String fieldName);
}
