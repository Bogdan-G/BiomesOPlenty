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

public class WorldGenRedwoodTree extends WorldGenAbstractTree
{
    private final Block wood;
    private final Block leaves;
    
    private final int woodMeta;
    private final int leavesMeta;
    
    private final int minTreeHeight;
    private final int randomTreeHeight;

    public WorldGenRedwoodTree(Block wood, Block leaves, int woodMeta, int leavesMeta, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
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
        int l = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;

        if (y >= 1 && y < (256-2))
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                b0 = 7;

                if (i1 == y)
                {
                    b0 = 7;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 8;
                }

                for (int j1 = x - b0; j1 <= x + b0; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = world.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(world, j1, i1, k1))
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
            }

            {
                
                for (int ix = -3; ix <= 3; ix++)
                {
                    for (int iz = -3; iz <= 3; iz++)
                    {
                        int v_0 = x + ix;int v_1 = z + iz;int v_2 = y - 1;
                        Block block2 = world.getBlock(v_0, v_2, v_1);
                        
                        if (!block2.canSustainPlant(world, v_0, v_2, v_1, ForgeDirection.UP, (BlockSapling)Blocks.sapling))
                        {
                            // Not enough Soil
                            return false;
                        }
                    }
                }
                
                for (int ix = -3; ix <= 3; ix++)
                {
                    for (int iy = 0; iy <= l; iy++)
                    {
                        for (int iz = -3; iz <= 3; iz++)
                        {
                            int v_0 = x + ix;int v_1 = z + iz;int v_2 = y + iy;
                            Block block2 = world.getBlock(v_0, v_2, v_1);

                            if (!block2.isAir(world, v_0, v_2, v_1))
                            {
                                // Not enough Space
                                return false;
                            }
                        }
                    }
                }
                
                {
                    for (int ix = -3; ix <= 3; ix++)
                    {
                        for (int iz = -3; iz <= 3; iz++)
                        {
                            if (((ix != -3 && ix != 3) || (iz >= -1 && iz <= 1)) && ((iz != -3 && iz != 3) || (ix >= -1 && ix <= 1)))
                            {
                                int v_0 = x + ix;int v_1 = z + iz;int v_2 = y - 1;
                                Block block2 = world.getBlock(v_0, v_2, v_1);

                                block2.onPlantGrow(world, v_0, v_2, v_1, v_0, v_2, v_1);
                            }
                        }
                    }
                    
                    b0 = 9;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = y - b0 + l; k1 <= y + l; ++k1)
                    {
                        i3 = k1 - (y + l);
                        l1 = b1 + 1 - i3 / 4;

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
                                        this.setBlockAndNotifyAdequately(world, i2, k1 + 10, k2, this.leaves, this.leavesMeta);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        int y_1 = y + k1;
                        block = world.getBlock(x, y_1, z);

                        if (block.isAir(world, x, y_1, z) || block.isLeaves(world, x, y_1, z))
                        {
                            int y_0 = y + (l);int y_2 = y + MathHelper.floor_double(k1 * 0.75);int y_3 = y + (k1 / 2);int y_4 = y + (k1 / 4);int y_5 = y + (k1 / 6);
                            int x_2 = x - 1;int x_3 = x + 1;int x_4 = x - 2;int x_5 = x + 2;int x_6 = x - 3;int x_7 = x + 3;
                            int z_8 = z - 1;int z_9 = z + 1;int z_10 = z - 2;int z_11 = z + 2;int z_12 = z - 3;int z_13 = z + 3;
                            //1
                            this.setBlockAndNotifyAdequately(world, x, y_0, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l + 1), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l + 2), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l + 3), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_0, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_0, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_2, y_1, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_3, y_1, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z_8, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z_9, this.wood, this.woodMeta);

                            //2
                            this.setBlockAndNotifyAdequately(world, x_2, y_2, z_8, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_3, y_2, z_8, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_2, y_2, z_9, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_3, y_2, z_9, this.wood, this.woodMeta);

                            //3
                            this.setBlockAndNotifyAdequately(world, x_4, y_3, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_4, y_3, z_8, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_4, y_3, z_9, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_5, y_3, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_5, y_3, z_8, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_5, y_3, z_9, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_3, z_10, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_2, y_3, z_10, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_3, y_3, z_10, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_3, z_11, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_2, y_3, z_11, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_3, y_3, z_11, this.wood, this.woodMeta);

                            //4
                            this.setBlockAndNotifyAdequately(world, x_4, y_4, z_10, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_5, y_4, z_11, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_5, y_4, z_10, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_4, y_4, z_11, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_6, y_4, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_7, y_4, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_4, z_12, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_4, z_13, this.wood, this.woodMeta);

                            //5
                            this.setBlockAndNotifyAdequately(world, x_6, y_5, z_8, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_6, y_5, z_9, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_7, y_5, z_8, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_7, y_5, z_9, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_2, y_5, z_12, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_3, y_5, z_12, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_2, y_5, z_13, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_3, y_5, z_13, this.wood, this.woodMeta);
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
            }
        }

        return false;
    }
}
