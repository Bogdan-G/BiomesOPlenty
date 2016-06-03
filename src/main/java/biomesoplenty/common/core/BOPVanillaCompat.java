package biomesoplenty.common.core;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.BiomeFeatures;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviourDart;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviourMudball;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import net.minecraft.block.BlockDispenser;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;
import org.bogdang.modifications.random.XSTR;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class BOPVanillaCompat
{
    private static Random random = new XSTR();
    
	public static void init()
	{
		registerDispenserBehaviours();
		addDungeonLoot();
		addBonemealFlowers();
	}
	
	private static void registerDispenserBehaviours()
	{
		BlockDispenser.dispenseBehaviorRegistry.putObject(BOPCItems.mudball, new DispenserBehaviourMudball());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BOPCItems.dart, new DispenserBehaviourDart());
	}
	
	private static void addDungeonLoot()
	{
		ChestGenHooks desertTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
		ChestGenHooks dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		ChestGenHooks jungleTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
		ChestGenHooks mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		ChestGenHooks strongholdCorridor = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
		ChestGenHooks strongholdCrossing = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
		ChestGenHooks strongholdLibrary = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
		ChestGenHooks village = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
		ChestGenHooks bonusChest = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);

		if (BOPConfigurationMisc.dungeonLoot == true)
		{
			mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPCItems.misc, 1, 1), 2, 8, 25));
			mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPCBlocks.plants, 1, 5), 4, 6, 15));

			strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPCBlocks.flowers, 1, 3), 1, 4, 25));
			strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPCBlocks.flowers, 1, 2), 1, 4, 25));

			strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPCBlocks.flowers, 1, 3), 1, 4, 25));
			strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPCBlocks.flowers, 1, 2), 1, 4, 25));

			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPCBlocks.plants, 1, 5), 2, 6, 25));
			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPCItems.misc, 1, 1), 2, 8, 25));
			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPCItems.wadingBoots, 1, 0), 1, 1, 5));
			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPCItems.flippers, 1, 0), 1, 1, 5));
			
			bonusChest.addItem(new WeightedRandomChestContent(new ItemStack(BOPCItems.wadingBoots, 1, 0), 1, 1, 5));
			bonusChest.addItem(new WeightedRandomChestContent(new ItemStack(BOPCItems.flippers, 1, 0), 1, 1, 5));
		}
	}
	
	private static void addBonemealFlowers()
	{
		for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
		{
			if (biome instanceof BOPBiome)
			{
				BOPBiome bopBiome = (BOPBiome)biome;
				BiomeFeatures biomeFeatures = bopBiome.theBiomeDecorator.bopFeatures;

				if (biomeFeatures.weightedFlowerGen != null && (biomeFeatures.weightedFlowerGen.size()>0))//!biomeFeatures.weightedFlowerGen.isEmpty()
				{
					HashMap<WorldGenerator, Integer> flowerMap = biomeFeatures.weightedFlowerGen;

					for (Entry<WorldGenerator, Integer> entry : flowerMap.entrySet())
					{
						if (entry.getKey() instanceof WorldGenBOPFlora && !(entry.getKey() instanceof WorldGenBOPDoubleFlora))
						{
							WorldGenBOPFlora flowerGenerator = (WorldGenBOPFlora)entry.getKey();
							int weight = entry.getValue();

							biome.addFlower(flowerGenerator.flora, flowerGenerator.floraMeta, weight);
						}
					}
				}
			}
		}
	}
}
