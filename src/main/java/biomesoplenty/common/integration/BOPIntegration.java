package biomesoplenty.common.integration;

import biomesoplenty.common.utils.BOPLogger;
import cpw.mods.fml.common.Loader;

public class BOPIntegration 
{
	public static void preInit()
	{
		/*if (Loader.isModLoaded("ATG"))
		{
			ATGIntegration.init();
		}*/
	}

	public static void init()
	{

		if (Loader.isModLoaded("ForgeMultipart"))
		{
			try
			{
				ForgeMultipartIntegration.init();
			}
			catch (Exception e)
			{
				BOPLogger.warning("There was an error while integrating ForgeMultipart with Biomes O' Plenty", e);
			}
		}

	}

	public static void postInit()
	{
		
		if (Loader.isModLoaded("Forestry"))
		{
			try
			{
				ForestryIntegration.init();
			}
			catch (Exception e)
			{
				BOPLogger.warning("There was an error while integrating Forestry with Biomes O' Plenty", e);
			}
		}

		if (Loader.isModLoaded("Thaumcraft"))
		{
			try
			{
				ThaumcraftIntegration.init();
			}
			catch (Exception e)
			{
				BOPLogger.warning("There was an error while integrating Thaumcraft with Biomes O' Plenty", e);
			}
		}

		if (Loader.isModLoaded("ThermalExpansion"))
		{
			try
			{
				TEIntegration.init();
			}
			catch (Exception e)
			{
				BOPLogger.warning("There was an error while integrating Thermal Expansion with Biomes O' Plenty", e);
			}
		}
	}
}