package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenBOPUndergroundDecoration extends WorldGeneratorBOP
{
    private Block tallGrass;
    private int tallGrassMetadata;
    private int moreGen = 10;

    public WorldGenBOPUndergroundDecoration(Block p_i45466_1_, int p_i45466_2_)
    {
        this.tallGrass = p_i45466_1_;
        this.tallGrassMetadata = p_i45466_2_;
    }

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        if (moreGen<10 || random.nextInt(8)==0) {
        if (random.nextInt(10)==0) moreGen=-1;
        if (moreGen<10) moreGen++;
        Block block;

        do
        {
            block = world.getBlock(x, y, z);
            if (!(block.isLeaves(world, x, y, z) || block.isAir(world, x, y, z)))
            {
                break;
            }
            --y;
        } while (y > 0);

        for (int l = 0; l < 128; ++l)
        {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && this.tallGrass.canReplace(world, i1, j1, k1, 0, new ItemStack(this.tallGrass, 1, this.tallGrassMetadata)))
            {
                world.setBlock(i1, j1, k1, this.tallGrass, this.tallGrassMetadata, 2);
            }
        }}

        return true;
    }
    
	@Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
	{
		for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); i++)
		{
			int randX = x + random.nextInt(16) + 8;
			int randZ = z + random.nextInt(16) + 8;
			int randY = random.nextInt(60);

            this.generate(world, random, randX, randY, randZ);
		}
	}
}