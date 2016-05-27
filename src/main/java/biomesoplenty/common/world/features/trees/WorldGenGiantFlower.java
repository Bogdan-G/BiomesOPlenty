package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBlocks;

public class WorldGenGiantFlower extends WorldGenAbstractTree
{
	private int metadata;
	
    public WorldGenGiantFlower(int metadata) 
    {
		super(false);
		
		this.metadata = metadata;
	}

	@Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
		while (world.isAirBlock(x, y, z) && y > 2)
		{
			--y;
		}

		Block block = world.getBlock(x, y, z);

		if (block != BOPCBlocks.longGrass) return false;
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
			
			int y_0 = y + 5;int y_1 = y + 6;int y_2 = y + 7;int y_3 = y + 8;int y_4 = y + 9;
			int x_0 = x - 1;int x_1 = x + 1;int x_2 = x - 2;int x_3 = x + 2;
			int z_0 = z - 1;int z_1 = z + 1;int z_2 = z - 2;int z_3 = z + 2;
			world.setBlock(x, y, z, Blocks.dirt);
			world.setBlock(x, y + 1, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y + 2, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y + 3, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y + 4, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y_0, z, BOPCBlocks.logs3, 3, 2);

			//Red
			if (metadata == 0)
			{
				world.setBlock(x_0, y_0, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_1, y_0, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_0, z_0, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_0, z_1, BOPCBlocks.petals, 0, 2);

				world.setBlock(x, y_1, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_0, y_1, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_1, y_1, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_1, z_0, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_1, z_1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_1, y_1, z_1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_1, y_1, z_0, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_0, y_1, z_1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_0, y_1, z_0, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_3, y_1, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_2, y_1, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_1, z_3, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_1, z_2, BOPCBlocks.petals, 0, 2);

				world.setBlock(x_1, y_2, z_1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_1, y_2, z_0, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_0, y_2, z_1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_0, y_2, z_0, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_3, y_2, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_2, y_2, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_2, z_3, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_2, z_2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_3, y_2, z_3, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_3, y_2, z_2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_2, y_2, z_3, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_2, y_2, z_2, BOPCBlocks.petals, 0, 2);

				world.setBlock(x_3, y_3, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x_2, y_3, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_3, z_3, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_3, z_2, BOPCBlocks.petals, 0, 2);

				world.setBlock(x + 3, y_4, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 3, y_4, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_4, z + 3, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y_4, z - 3, BOPCBlocks.petals, 0, 2);
			}
			else
			{
				//Yellow
				world.setBlock(x_0, y_0, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_1, y_0, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_0, z_0, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_0, z_1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_3, y_0, z_3, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_3, y_0, z_2, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_2, y_0, z_3, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_2, y_0, z_2, BOPCBlocks.petals, 1, 2);

				world.setBlock(x, y_1, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_0, y_1, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_1, y_1, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_1, z_0, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_1, z_1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_1, y_1, z_1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_1, y_1, z_0, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_0, y_1, z_1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_0, y_1, z_0, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_3, y_1, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x_2, y_1, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_1, z_3, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_1, z_2, BOPCBlocks.petals, 1, 2);

				world.setBlock(x, y_2, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 3, y_2, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 3, y_2, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_2, z + 3, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y_2, z - 3, BOPCBlocks.petals, 1, 2);
			}

			return true;
		}
	}
}
