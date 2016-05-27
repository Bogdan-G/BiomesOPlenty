package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBayou3 extends WorldGenAbstractTree
{
    private final Block wood;

    private final int metaWood;

    public WorldGenBayou3(Block wood, int metaWood)
    {
        this(wood, metaWood, false);
    }

    public WorldGenBayou3(Block wood, int metaWood, boolean doBlockNotify)
    {
        super(doBlockNotify);

        this.wood = wood;
        this.metaWood = metaWood;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int l;

        for (l = 7; world.getBlock(x, y - 1, z).getMaterial() == Material.water; --y)
        {
            ;
        }

        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            int j1;
            int k1;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                byte b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 3;
                }

                for (j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            Block block = world.getBlock(j1, i1, k1);

                            if (!(block.isAir(world, j1, i1, k1) || block.isLeaves(world, j1, i1, k1)))
                            {
                                if (block != Blocks.water && block != Blocks.flowing_water)
                                {
                                    flag = false;
                                }
                                else if (i1 > y)
                                {
                                    flag = false;
                                }
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
                int y_0 = y - 1;
                Block block1 = world.getBlock(x, y_0, z);

                boolean isSoil = block1.canSustainPlant(world, x, y_0, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block1.onPlantGrow(world, x, y_0, z, x, y, z);

                    int x_0 = x - 1;int x_1 = x + 1;
                    int y_1 = y + 4;int y_2 = y + 2;int y_3 = y + 3;int y_4 = y + 1;
                    int z_0 = z - 1;int z_1 = z + 1;
                    this.setBlockAndNotifyAdequately(world, x_0, y, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_1, y, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y, z_0, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y, z_1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_0, y_4, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_1, y_4, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_4, z_0, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_4, z_1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_0, y_2, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_1, y_2, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_2, z_0, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_2, z_1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_0, y_3, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_1, y_3, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_3, z_0, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_3, z_1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_0, y_1, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x_1, y_1, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_1, z_0, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y_1, z_1, this.wood, this.metaWood);

                    int l1;
                    int l2;
                    int k2;

                    for (k2 = 0; k2 < l; ++k2)
                    {
                        int v_0 = y + k2;
                        Block block2 = world.getBlock(x, v_0, z);

                        if (block2.isAir(world, x, v_0, z) || block2.isLeaves(world, x, v_0, z) || block2 == Blocks.flowing_water || block2 == Blocks.water)
                        {
                            this.setBlockAndNotifyAdequately(world, x, v_0, z, this.wood, this.metaWood);
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
