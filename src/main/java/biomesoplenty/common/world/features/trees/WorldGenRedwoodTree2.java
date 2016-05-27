package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.api.content.BOPCBlocks;

public class WorldGenRedwoodTree2 extends WorldGenAbstractTree
{
    private final Block wood;
    private final Block leaves;
    
    private final int woodMeta;
    private final int leavesMeta;
    
    private final int minTreeHeight;
    private final int randomTreeHeight;

    public WorldGenRedwoodTree2(Block wood, Block leaves, int woodMeta, int leavesMeta, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
    {
        super(doBlockNotify);
        
        this.wood = wood;
        this.leaves = leaves;
        
        this.woodMeta = woodMeta;
        this.leavesMeta = leavesMeta;
        
        this.minTreeHeight = minTreeHeight;
        this.randomTreeHeight = randomTreeHeight;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int treeHeight = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;

        if (y >= 1 && y + treeHeight + 1 <= 256)
        {
        	byte width;
        	int k1;
        	Block block;

        	boolean isSoil = true;
        	boolean hasSpace = true;

        	for (int ix = -2; ix <= 2 && isSoil; ix++)
        	{
        		for (int iz = -2; iz <= 2; iz++)
        		{
        			Block block2 = world.getBlock(x + ix, y - 1, z + iz);

        			if (!block2.canSustainPlant(world, x + ix, y - 1, z + iz, ForgeDirection.UP, (BlockSapling)Blocks.sapling))
        			{
        				isSoil = false;
        				break;
        			}
        		}
        	}

        	for (int ix = -2; ix <= 2 && hasSpace; ix++)
        	{
        		for (int iy = 0; iy <= treeHeight && hasSpace; iy++)
        		{
        			for (int iz = -2; iz <= 2; iz++)
        			{
        				Block block2 = world.getBlock(x + ix, y + iy, z + iz);

        				if (!block2.isAir(world, x + ix, y + iy, z + iz))
        				{
        					hasSpace = false;
        					break;
        				}
        			}
        		}
        	}

        	if (isSoil && hasSpace && y < 256 - treeHeight - 1)
        	{
        		for (int ix = -2; ix <= 2; ix++)
        		{
        			for (int iz = -2; iz <= 2; iz++)
        			{
        				if (((ix != -2 && ix != 2) || (iz == 0)) && ((iz != -2 && iz != 2) || (ix == 0)))
        				{
        					int v_0 = x + ix;int v_1 = z + iz;int v_2 = y - 1;
        					Block block2 = world.getBlock(v_0, v_2, v_1);

        					block2.onPlantGrow(world, v_0, v_2, v_1, v_0, v_2, v_1);
        				}
        			}
        		}

        		width = 9;
        		byte b1 = 0;
        		int l1;
        		int i2;
        		int j2;
        		int i3;

        		for (k1 = y - width + treeHeight; k1 <= y + treeHeight; ++k1)
        		{
        			i3 = k1 - (y + treeHeight);
        			l1 = b1 + 1 - i3 / 6;

        			for (i2 = x - l1; i2 <= x + l1; ++i2)
        			{
        				j2 = i2 - x;

        				for (int k2 = z - l1; k2 <= z + l1; ++k2)
        				{
        					int l2 = k2 - z;

        					if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0 && i3 != 0)
        					{
        						Block block1 = world.getBlock(i2, k1, k2);

        						if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2))
        						{
        							//this.setBlockAndNotifyAdequately(world, i2, k1, k2, this.leaves, this.leavesMeta);
        						this.setBlockAndNotifyAdequately(world, i2, k1 + 6, k2, this.leaves, this.leavesMeta);
        						this.setBlockAndNotifyAdequately(world, i2, k1 + 12, k2, this.leaves, this.leavesMeta);
        						}
        					}
        				}
        			}
        		}

        		for (k1 = 0; k1 < treeHeight; ++k1)
        		{
        			int y_1 = y + k1;
        			block = world.getBlock(x, y_1, z);

        			if (block.isAir(world, x, y_1, z) || block.isLeaves(world, x, y_1, z))
        			{
        				int y_2 = y + MathHelper.floor_double(k1 * 0.75);int y_3 = y + (k1 / 2);int y_4 = y + (k1 / 4);
        				int z_8 = z - 1;int z_9 = z + 1;
        				int x_8 = x - 1;int x_9 = x + 1;
        				//1
        				this.setBlockAndNotifyAdequately(world, x, y + (treeHeight), z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y + (treeHeight + 1), z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y + (treeHeight + 2), z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y + (treeHeight + 3), z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y + (treeHeight + 4), z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y + (treeHeight + 5), z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y_1, z, this.wood, this.woodMeta);

        				//2
        				this.setBlockAndNotifyAdequately(world, x_8, y_2, z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x_9, y_2, z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y_2, z_8, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y_2, z_9, this.wood, this.woodMeta);

        				//3
        				this.setBlockAndNotifyAdequately(world, x_8, y_3, z_8, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x_9, y_3, z_8, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x_8, y_3, z_9, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x_9, y_3, z_9, this.wood, this.woodMeta);

        				//4
        				this.setBlockAndNotifyAdequately(world, x - 2, y_4, z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x + 2, y_4, z, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y_4, z - 2, this.wood, this.woodMeta);
        				this.setBlockAndNotifyAdequately(world, x, y_4, z + 2, this.wood, this.woodMeta);
        			}
        		}

        		for (int i = 0; i < 90; ++i)
        		{
        			int randX = x - random.nextInt(8) + random.nextInt(8);
        			int randY = y + random.nextInt(64);
        			int randZ = z - random.nextInt(8) + random.nextInt(8);
        			new WorldGenBOPShrub(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves1, 0, 3, BOPCBlocks.logs3).generate(world, random, randX, randY, randZ);
        		}

        		return true;
        	}
        	else
        	{
        		return false;
        	}
        }
        else
        {
        	return false;
        }
    }
}
