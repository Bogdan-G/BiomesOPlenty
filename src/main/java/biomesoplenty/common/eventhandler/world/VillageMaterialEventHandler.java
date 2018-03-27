package biomesoplenty.common.eventhandler.world;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VillageMaterialEventHandler
{
	//TODO: FEATURE All commented out
	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		//Brushland //Gravel
		if (event.biome == BOPCBiomes.brushland && event.original == Blocks.gravel) {
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
		} else if (event.biome == BOPCBiomes.grove) //Grove
		{	
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = Blocks.log2;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.dark_oak_stairs;
				event.setResult(Result.DENY);
			}
		} else if (event.biome == BOPCBiomes.heathland) //Heathland
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPCBlocks.logs4;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPCBlocks.planks;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = BOPCBlocks.jacarandaStairs;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		} else if (event.biome == BOPCBiomes.meadow && event.original == Blocks.oak_stairs) { //Meadow //Wooden Stairs
				event.replacement = Blocks.spruce_stairs;
				event.setResult(Result.DENY);
		} else if (event.biome == BOPCBiomes.outback) //Outback
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = Blocks.log2;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = Blocks.planks;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.acacia_stairs;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		} else if (event.biome == BOPCBiomes.prairie && event.original == Blocks.gravel) { //Prairie //Gravel
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
		}
	}
	
	@SubscribeEvent
	public void getVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
	{	
		//Grove
		if (event.biome == BOPCBiomes.grove)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 5;
				event.setResult(Result.DENY);
			}
		} else if (event.biome == BOPCBiomes.heathland) //Heathland
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 2;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 13;
				event.setResult(Result.DENY);
			}
		} else if (event.biome == BOPCBiomes.meadow) //Meadow
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		} else if (event.biome == BOPCBiomes.outback && event.original == Blocks.planks) { //Outback //Wooden Planks
				event.replacement = 4;
				event.setResult(Result.DENY);
		}
	}
}