package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;

public class WorldGenBogBush extends WorldGenAbstractTree
{
    public WorldGenBogBush()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }

        Block block = world.getBlock(x, y, z);

        if (block != Blocks.grass && block != Blocks.sand && block != BOPCBlocks.newBopGrass)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    int v_0 = x + var7;int v_1 = z + var8;
                    if (world.isAirBlock(v_0, y - 1, v_1) && world.isAirBlock(v_0, y - 2, v_1))
                        return false;
                }
            }

            this.setBlockAndNotifyAdequately(world, x, y + 1, z, Blocks.leaves, 4);
            return true;
        }
    }
}
