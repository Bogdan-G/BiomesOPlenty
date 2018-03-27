package biomesoplenty.common.eventhandler.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.biome.decoration.BOPOverworldBiomeDecorator;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DecorationModificationEventHandler 
{
	@SubscribeEvent
	public void modifyDecor(Decorate event)
	{
		World world = event.world;

		//int chunkX = event.chunkX;
		//int chunkZ = event.chunkZ;

		//Random random = event.rand;
		
		BiomeGenBase biome = world.getBiomeGenForCoordsBody(event.chunkX + 16, event.chunkZ + 16);
		
		if (biome instanceof BOPBiome)
		{
			BOPBiome bopBiome = (BOPBiome)biome;
			
	        if (event.type == Decorate.EventType.LAKE)
	        {
	            event.setResult(Result.DENY);
	            return;
	        }
			
	        if (bopBiome.theBiomeDecorator instanceof BOPOverworldBiomeDecorator && event.type == Decorate.EventType.PUMPKIN && !(Boolean)bopBiome.theBiomeDecorator.bopFeatures.getFeature("generatePumpkins")) {
	        	event.setResult(Result.DENY);
	        }
		}
	}
}
