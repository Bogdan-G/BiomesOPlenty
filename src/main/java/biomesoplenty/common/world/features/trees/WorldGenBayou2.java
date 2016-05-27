package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.api.content.BOPCBlocks;

public class WorldGenBayou2 extends WorldGenAbstractTree
{
    private Block wood;
    private Block leaves;

    private int woodMeta;
    private int leavesMeta;

    public WorldGenBayou2(Block wood, Block leaves, int woodMeta, int leavesMeta)
    {
        super(false);

        this.wood = wood;
        this.leaves = leaves;

        this.woodMeta = woodMeta;
        this.leavesMeta = leavesMeta;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int l;

        for (l = random.nextInt(5) + 8; world.getBlock(x, y - 1, z).getMaterial() == Material.water; --y)
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
                int y_0 = y - 1;int y_1 = y + 1;
                int x_0 = x + 1;int x_1 = x - 1;
                int z_0 = z + 1;int z_1 = z - 1;
                Block block1 = world.getBlock(x, y_0, z);
                Block block2 = world.getBlock(x_0, y_0, z);
                Block block3 = world.getBlock(x_1, y_0, z);
                Block block4 = world.getBlock(x, y_0, z_0);
                Block block5 = world.getBlock(x, y_0, z_1);

                boolean isSoil = block1.canSustainPlant(world, x, y_0, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling) &&
                        block2.canSustainPlant(world, x_0, y_0, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling) &&
                        block3.canSustainPlant(world, x_1, y_0, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling) &&
                        block4.canSustainPlant(world, x, y_0, z_0, ForgeDirection.UP, (BlockSapling)Blocks.sapling) &&
                        block5.canSustainPlant(world, x, y_0, z_1, ForgeDirection.UP, (BlockSapling)Blocks.sapling);

                if (isSoil && y < 256 - l - 1)
                {
                    block1.onPlantGrow(world, x, y_0, z, x, y, z);
                    block1.onPlantGrow(world, x_0, y_0, z, x_0, y, z);
                    block1.onPlantGrow(world, x_1, y_0, z, x_1, y, z);
                    block1.onPlantGrow(world, x, y_0, z_0, x, y, z_0);
                    block1.onPlantGrow(world, x, y_0, z_1, x, y, z_1);

                    this.setBlockAndNotifyAdequately(world, x_1, y, z, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x_0, y, z, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x, y, z_1, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x, y, z_0, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x_1, y_1, z, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x_0, y_1, z, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x, y_1, z_1, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x, y_1, z_0, this.wood, this.woodMeta);

                    int l1;
                    int l2;
                    int k2;

                    for (k2 = y - 3 + l; k2 <= y + l; ++k2)
                    {
                        j1 = k2 - (y + l);
                        k1 = 2 - j1 / 2;

                        for (l2 = x - k1; l2 <= x + k1; ++l2)
                        {
                            l1 = l2 - x;

                            for (int i2 = z - k1; i2 <= z + k1; ++i2)
                            {
                                int j2 = i2 - z;

                                if ((Math.abs(l1) != k1 || Math.abs(j2) != k1 || random.nextInt(2) != 0 && j1 != 0) && world.getBlock(l2, k2, i2).canBeReplacedByLeaves(world, l2, k2, i2))
                                {
                                    this.setBlockAndNotifyAdequately(world, l2, k2, i2, this.leaves, this.leavesMeta);
                                }
                            }
                        }
                    }

                    for (k2 = 0; k2 < l; ++k2)
                    {
                        int y_00 = y + k2;
                        Block block6 = world.getBlock(x, y_00, z);

                        if (block6.isAir(world, x, y_00, z) || block6.isLeaves(world, x, y_00, z) || block6 == Blocks.flowing_water || block6 == Blocks.water)
                        {
                            this.setBlockAndNotifyAdequately(world, x, y_00, z, this.wood, this.woodMeta);
                        }
                    }

                    for (k2 = y - 3 + l; k2 <= y + l; ++k2)
                    {
                        j1 = k2 - (y + l);
                        k1 = 2 - j1 / 2;

                        for (l2 = x - k1; l2 <= x + k1; ++l2)
                        {
                            for (l1 = z - k1; l1 <= z + k1; ++l1)
                            {
                                if (world.getBlock(l2, k2, l1).isLeaves(world, l2, k2, l1))
                                {
                                    int l_0 = l2 - 1;
                                    if (random.nextInt(4) == 0 && world.getBlock(l_0, k2, l1).isAir(world, l_0, k2, l1))
                                    {
                                        this.generateVines(world, l_0, k2, l1, 8);
                                    }

                                    int l_2 = l2 + 1;
                                    if (random.nextInt(4) == 0 && world.getBlock(l_2, k2, l1).isAir(world, l_2, k2, l1))
                                    {
                                        this.generateVines(world, l_2, k2, l1, 2);
                                    }

                                    int l_1 = l1 - 1;
                                    if (random.nextInt(4) == 0 && world.getBlock(l2, k2, l_1).isAir(world, l2, k2, l_1))
                                    {
                                        this.generateVines(world, l2, k2, l_1, 1);
                                    }

                                    int l_3 = l1 + 1;
                                    if (random.nextInt(4) == 0 && world.getBlock(l2, k2, l_3).isAir(world, l2, k2, l_3))
                                    {
                                        this.generateVines(world, l2, k2, l_3, 4);
                                    }
                                }
                            }
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

    private void generateVines(World world, int x, int y, int z, int flag)
    {
        this.setBlockAndNotifyAdequately(world, x, y, z, BOPCBlocks.willow, flag);
        int i1 = 4;

        while (true)
        {
            --y;

            if (!(world.getBlock(x, y, z).isAir(world, x, y, z)) || i1 <= 0)
            {
                return;
            }

            this.setBlockAndNotifyAdequately(world, x, y, z, BOPCBlocks.willow, flag);
            --i1;
        }
    }
}
