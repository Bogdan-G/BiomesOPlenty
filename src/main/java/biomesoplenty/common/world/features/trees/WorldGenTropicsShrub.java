package biomesoplenty.common.world.features.trees;

import java.util.Random;

import biomesoplenty.api.content.BOPCBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTropicsShrub extends WorldGenAbstractTree
{
    public WorldGenTropicsShrub()
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
                    {
                        return false;
                    }
                }
            }

            int y_0  = y + 1;int y_1  = y + 2;
            int z_0  = z + 1;int z_2  = z - 1;
            world.setBlock(x, y, z, Blocks.dirt);
            world.setBlock(x, y_0, z, Blocks.log, 3, 2);
            world.setBlock(x, y_1, z, Blocks.log, 3, 2);
            world.setBlock(x + 1, y_1, z, Blocks.leaves, 3, 2);
            world.setBlock(x - 1, y_1, z, Blocks.leaves, 3, 2);
            world.setBlock(x, y_1, z_0, Blocks.leaves, 3, 2);
            world.setBlock(x, y_1, z_2, Blocks.leaves, 3, 2);
            world.setBlock(x, y + 3, z, Blocks.leaves, 3, 2);

            int var999 = random.nextInt(12);
            int var998 = random.nextInt(4);

            if (var998 == 0)
            {
                if (var999 == 0)
                {
                    world.setBlock(x - 1, y_0, z, Blocks.cocoa, 11, 2);
                }
                if (var999 == 1)
                {
                    world.setBlock(x + 1, y_0, z, Blocks.cocoa, 9, 2);
                }
                if (var999 == 2)
                {
                    world.setBlock(x, y_0, z_2, Blocks.cocoa, 8, 2);
                }
                if (var999 == 3)
                {
                    world.setBlock(x, y_0, z_0, Blocks.cocoa, 10, 2);
                }
            }
            else if (var998 == 1)
            {
                if (var999 == 0)
                {
                    world.setBlock(x - 1, y_0, z, Blocks.cocoa, 7, 2);
                }
                if (var999 == 1)
                {
                    world.setBlock(x + 1, y_0, z, Blocks.cocoa, 5, 2);
                }
                if (var999 == 2)
                {
                    world.setBlock(x, y_0, z_2, Blocks.cocoa, 4, 2);
                }
                if (var999 == 3)
                {
                    world.setBlock(x, y_0, z_0, Blocks.cocoa, 6, 2);
                }
            }
            else if (var998 == 2)
            {
                if (var999 == 0)
                {
                    world.setBlock(x - 1, y_0, z, Blocks.cocoa, 3, 2);
                }
                if (var999 == 1)
                {
                    world.setBlock(x + 1, y_0, z, Blocks.cocoa, 1, 2);
                }
                if (var999 == 2)
                {
                    world.setBlock(x, y_0, z_2, Blocks.cocoa, 0, 2);
                }
                if (var999 == 3)
                {
                    world.setBlock(x, y_0, z_0, Blocks.cocoa, 2, 2);
                }
            }
            return true;
        }
    }
}
