package biomesoplenty.common.world.features;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenSplotches extends WorldGeneratorBOP
{
    private Block splotchBlock;
    private int splotchBlockMeta;

    private int numberOfBlocks;

    private List blockList;

    public WorldGenSplotches(Block quicksandBlock, int quicksandBlockMeta, int numberOfBlocks, Block... blockList)
    {
        super(true);

        this.splotchBlock = quicksandBlock;
        this.splotchBlockMeta = quicksandBlockMeta;
        this.numberOfBlocks = numberOfBlocks;

        this.blockList = Arrays.asList(blockList);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        float match_pi = 3.1416F;float mh_2=match_pi / numberOfBlocks;
        float var6 = random.nextFloat() * match_pi;
        float var1c = numberOfBlocks / 8.0F;
        float mh_s_0 = MathHelper.sin(var6) * var1c;
        float mh_c_0 = MathHelper.cos(var6) * var1c;
        int x_0 = x + 8;int z_0 = z + 8;
        float var7 = x_0 + mh_s_0;
        //float var9 = x_0 - mh_s_0;
        float var11 = z_0 + mh_c_0;
        //float var13 = z_0 - mh_c_0;
        int temp_r_0 = random.nextInt(3);int temp_r_1 = random.nextInt(3);
        int temp_0 = temp_r_1 - temp_r_0;
        float var15 = y + temp_r_0 - 2;
        //float var17 = y + temp_r_1  - 2;
        float mh_0 = -2*mh_s_0;float mh_1 = -2*mh_c_0;

        for (int var19 = 0; var19 <= numberOfBlocks; ++var19)
        {
            float var2c = var19 / numberOfBlocks;
            //var9 - var7, school mathematics :/, x_0 - mh_s_0 - x_0 - mh_s_0 = -2*mh_s_0
            float var20 = var7 + (mh_0) * var2c;
            float var22 = var15 + (temp_0) * var2c;
            float var24 = var11 + (mh_1) * var2c;
            float var26 = random.nextFloat() * numberOfBlocks / 16.0F;
            float var28 = (MathHelper.sin(var19 * mh_2) + 1.0F) * var26 + 1.0F;
            //float var30 = var28;//(MathHelper.sin(var19 * match_pi / numberOfBlocks) + 1.0F) * var26 + 1.0F;
            float var3c = var28 / 2.0F;
            int var32 = MathHelper.floor_float(var20 - var3c);
            int var33 = MathHelper.floor_float(var22 - var3c);
            int var34 = MathHelper.floor_float(var24 - var3c);
            int var35 = MathHelper.floor_float(var20 + var3c);
            int var36 = MathHelper.floor_float(var22 + var3c);
            int var37 = MathHelper.floor_float(var24 + var3c);

            for (int var38 = var32; var38 <= var35; ++var38)
            {
                float var39 = (var38 + 0.5F - var20) / (var3c);
                float var39degree2 = var39 * var39;

                if (var39degree2 < 1.0F)
                {
                    for (int var41 = var33; var41 <= var36; ++var41)
                    {
                        float var42 = (var41 + 0.5F - var22) / (var3c);
                        //float var42degree2 = var42 * var42;
                        float var39d2_var42d2_sum = var39degree2 + var42 * var42;

                        if (var39d2_var42d2_sum < 1.0F)
                        {
                            for (int var44 = var34; var44 <= var37; ++var44)
                            {
                                float var45 = (var44 + 0.5F - var24) / (var3c);

                                if (var39d2_var42d2_sum + var45 * var45 < 1.0F && world.getBlock(var38, var41, var44) != Blocks.air && (blockList.contains(world.getBlock(var38, var41, var44))))
                                {
                                    this.setBlockAndNotifyAdequately(world, var38, var41, var44, splotchBlock, splotchBlockMeta);
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
    {
        if (featureName.equals("generateQuicksand") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateQuicksand"))
        {
            for (int i = 0; i < 5; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateCanyon") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateCanyon"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateStoneInGrass") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateStoneInGrass"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateStoneInGrass2") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateStoneInGrass2"))
        {
            for (int i = 0; i < 20; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateGrass") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateGrass"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateSand") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateSand"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateQuagmire") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateQuagmire"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateAsh") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateAsh"))
        {
            for (int i = 0; i < 10; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateMycelium") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateMycelium"))
        {
            for (int i = 0; i < 10; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateSponge") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateSponge"))
        {
            for (int i = 0; i < 5; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
    }
}
