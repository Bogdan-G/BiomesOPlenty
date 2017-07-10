package biomesoplenty.common.world.layer;

import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

//import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.world.BOPBiomeManager;
import biomesoplenty.common.world.noise.SimplexNoise;

public class GenLayerSubBiome extends GenLayer
{
	private static final int OFFSET_RANGE = 500000;
	
	//We store primitives, we can make a two-dimensional array, but we can make the inner class, and so we can make a one-dimensional array
	//Perhaps the array structure has less data amount(volume) than a class with 2 variables
	private final int[] offsets = new int[BiomeGenBase.getBiomeGenArray().length*2];
	
	public GenLayerSubBiome(long seed, GenLayer parent) 
	{
		super(seed);
		
		this.parent = parent;
		
		this.setOffsets();
	}
	
    @Override
	public int[] getInts(int x, int z, int width, int length)
    {
        int[] inputBiomeIDs = this.parent.getInts(x - 1, z - 1, width + 2, length + 2);
        int[] outputBiomeIDs = IntCache.getIntCache(width * length);

        for (int xi = 0; xi < width; ++xi) 
        {
        	for (int zi = 0; zi < length; ++zi) 
        	{
        		this.initChunkSeed(xi + x, zi + z);
        		int currentBiomeId = inputBiomeIDs[xi + 1 + (zi + 1) * (width + 2)];
        		
        		List<BiomeEntry> currentSubBiomes = BOPBiomeManager.overworldSubBiomes[currentBiomeId];
        		BOPSubBiome selectedSubBiome = currentSubBiomes != null ? (BOPSubBiome)currentSubBiomes.get(this.nextInt(currentSubBiomes.size())).biome : null;
        		
        		if (selectedSubBiome != null)
        		{
        			int offsetLeft = offsets[selectedSubBiome.biomeID];//getOffset(selectedSubBiome);
        			int offsetRight = offsets[offsets.length/2-1+selectedSubBiome.biomeID];
        			
        			if (SimplexNoise.noise((xi + x + offsetLeft) * selectedSubBiome.zoom, (zi + z + offsetRight) * selectedSubBiome.zoom) > selectedSubBiome.threshold)  
        			{
        				outputBiomeIDs[xi + zi * width] = selectedSubBiome.biomeID;
        			}
        			else outputBiomeIDs[xi + zi * width] = currentBiomeId;
        		}
        		else
        		{
        			outputBiomeIDs[xi + zi * width] = currentBiomeId;
        		}
        	}
        }

        return outputBiomeIDs;
    }
    
    /*private Pair<Integer, Integer> getOffset(BiomeGenBase biome)
    {
    	return offsets[biome.biomeID];
    }*/

    private void setOffsets()
    {
    	for (int i = 0; i< offsets.length; i++)
    	{
    		//offsets[i] = Pair.of(this.nextInt(OFFSET_RANGE) - (OFFSET_RANGE / 2), this.nextInt(OFFSET_RANGE) - (OFFSET_RANGE / 2));
    		offsets[i] = this.nextInt(OFFSET_RANGE) - (OFFSET_RANGE / 2);
    		i++;
    		offsets[i] = this.nextInt(OFFSET_RANGE) - (OFFSET_RANGE / 2);
    		//offsets[offsets.length+i] = this.nextInt(OFFSET_RANGE) - (OFFSET_RANGE / 2);
    	}
    }
}
