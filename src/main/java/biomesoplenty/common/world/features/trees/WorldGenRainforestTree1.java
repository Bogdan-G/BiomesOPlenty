package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenRainforestTree1 extends WorldGenAbstractTree
{
    private final int minTreeHeight;
    private final int randomTreeHeight;

    private final Block wood;
    private final Block leaves;
    
    private final int woodMeta;
    private final int leavesMeta;

    public WorldGenRainforestTree1(Block wood, Block leaves, int woodMeta, int leavesMeta, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
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
        int l = random.nextInt(randomTreeHeight) + this.minTreeHeight;
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = world.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(world, j1, i1, k1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = world.getBlock(x, y - 1, z);

                boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block2.onPlantGrow(world, x, y - 1, z, x, y, z);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = y - b0 + l; k1 <= y + l; ++k1)
                    {
                        i3 = k1 - (y + l);
                        l1 = b1 + 1 - i3;

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
                                        this.setBlockAndNotifyAdequately(world, i2, k1, k2, this.leaves, this.leavesMeta);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        int y_0 = y + k1;
                        block = world.getBlock(x, y_0, z);

                        if (block.isAir(world, x, y_0, z) || block.isLeaves(world, x, y_0, z))
                        {
                            int y_1 = y + (l - 3);int y_2 = y + (l - 4);int y_3 = y + (l - 5);int y_4 = y + (l - 6);int y_5 = y + (l - 2);
                            int x_0 = x - 2;int x_1 = x + 2;int x_2 = x - 1;int x_3 = x + 1;
                            int wm_0 = this.woodMeta + 4;int wm_1 = this.woodMeta + 8;
                            int z_0 = z - 1;int z_1 = z + 1;int z_2 = z - 2;int z_3 = z + 2;
                            this.setBlockAndNotifyAdequately(world, x, y_0, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 3, y_1, z, this.wood, wm_0);
                            this.setBlockAndNotifyAdequately(world, x + 3, y_1, z, this.wood, wm_0);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z - 3, this.wood, wm_1);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z + 3, this.wood, wm_1);
                            this.setBlockAndNotifyAdequately(world, x_0, y_2, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_1, y_2, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_2, z_2, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_2, z_3, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_0, y_3, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_1, y_3, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_3, z_2, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_3, z_3, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x_2, y_4, z, this.wood, wm_0);
                            this.setBlockAndNotifyAdequately(world, x_3, y_4, z, this.wood, wm_0);
                            this.setBlockAndNotifyAdequately(world, x, y_4, z_0, this.wood, wm_1);
                            this.setBlockAndNotifyAdequately(world, x, y_4, z_1, this.wood, wm_1);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z, this.leaves, this.leavesMeta);
                            this.setBlockAndNotifyAdequately(world, x, y_5, z, this.leaves, this.leavesMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 1), z, this.leaves, this.leavesMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l), z, this.leaves, this.leavesMeta);
                            this.func_150515_a(world, x, y_2, z, Blocks.air);
                            this.func_150515_a(world, x, y_3, z, Blocks.air);
                            this.setBlockAndNotifyAdequately(world, x_2, y_1, z, this.wood, wm_0);
                            this.setBlockAndNotifyAdequately(world, x_3, y_1, z, this.wood, wm_0);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z_0, this.wood, wm_1);
                            this.setBlockAndNotifyAdequately(world, x, y_1, z_1, this.wood, wm_1);
                            this.setBlockAndNotifyAdequately(world, x, y_5, z, this.wood, this.woodMeta);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}