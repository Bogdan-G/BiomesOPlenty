package biomesoplenty.common.helpers;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCacheBlockHell
{
	/** An array of chunk temperatures saved by this cache. */
	public float[] temperatureValues;

	/** An array of chunk rainfall values saved by this cache. */
	public float[] rainfallValues;

	/** The array of biome types stored in this BiomeCacheBlock. */
	public BiomeGenBase[] biomes;

	/** The x coordinate of the BiomeCacheBlock. */
	public int xPosition;

	/** The z coordinate of the BiomeCacheBlock. */
	public int zPosition;

	/** The last time this BiomeCacheBlock was accessed, in milliseconds. */
	public long lastAccessTime;

	/** The BiomeCache object that contains this BiomeCacheBlock */
	final BiomeCacheHell theBiomeCache;
	private static final int n_0 = 16;
	private static final int n_1 = 256;

	public BiomeCacheBlockHell(BiomeCacheHell par1BiomeCache, int par2, int par3)
	{
		theBiomeCache = par1BiomeCache;
		temperatureValues = new float[n_1];
		rainfallValues = new float[n_1];
		biomes = new BiomeGenBase[n_1];
		xPosition = par2;
		zPosition = par3;
		int p_0 = par2 << 4;int p_1 = par3 << 4;
		BiomeCacheHell.getChunkManager(par1BiomeCache).getTemperatures(temperatureValues, p_0, p_1, n_0, n_0);
		BiomeCacheHell.getChunkManager(par1BiomeCache).getRainfall(rainfallValues, p_0, p_1, n_0, n_0);
		BiomeCacheHell.getChunkManager(par1BiomeCache).getBiomeGenAt(biomes, p_0, p_1, n_0, n_0, false);
	}

	/**
	 * Returns the BiomeGenBase related to the x, z position from the cache block.
	 */
	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return biomes[par1 & 15 | (par2 & 15) << 4];
	}
}
