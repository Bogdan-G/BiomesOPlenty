package biomesoplenty;

import net.minecraft.creativetab.CreativeTabs;
import biomesoplenty.common.configuration.BOPConfiguration;
import biomesoplenty.common.configuration.structures.BOPConfigurationStrongholds;
import biomesoplenty.common.configuration.structures.BOPConfigurationVillages;
import biomesoplenty.common.core.BOPBiomes;
import biomesoplenty.common.core.BOPBlocks;
import biomesoplenty.common.core.BOPCrafting;
import biomesoplenty.common.core.BOPDimensions;
import biomesoplenty.common.core.BOPEntities;
import biomesoplenty.common.core.BOPFluids;
import biomesoplenty.common.core.BOPItems;
import biomesoplenty.common.core.BOPPotions;
import biomesoplenty.common.core.BOPVanillaCompat;
import biomesoplenty.common.eventhandler.BOPEventHandlers;
import biomesoplenty.common.helpers.CreativeTabsBOP;
import biomesoplenty.common.integration.BOPIntegration;
import biomesoplenty.common.integration.TreecapitatorIntegration;
import biomesoplenty.common.network.BOPPacketHandler;
import biomesoplenty.common.utils.BOPModInfo;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.common.world.generation.WorldGenFieldAssociation;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;import java.util.Locale;

@Mod(modid = BOPModInfo.modID, name = BOPModInfo.modName, dependencies = "after:Natura; required-after:Forge@[10.13.4.1566,)")
public class BiomesOPlenty
{
    @Instance("BiomesOPlenty")
    public static BiomesOPlenty instance;
    
    @SidedProxy(clientSide = "biomesoplenty.ClientProxy", serverSide = "biomesoplenty.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tabBiomesOPlenty;
    public static String configPath;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        configPath = event.getModConfigurationDirectory() + "/biomesoplenty/";
        Locale.setDefault(Locale.ENGLISH);//info: https://github.com/Glitchfiend/BiomesOPlenty/pull/768
        BOPConfiguration.init(configPath);
        tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(), "tabBiomesOPlenty");
        BOPPacketHandler.init();
        BOPPotions.init();
        BOPFluids.preInit();
        BOPBlocks.init();
        BOPItems.init();
        BOPFluids.init();
        BOPCrafting.init();
        WorldGenFieldAssociation.init();
        BOPBiomes.init();
        BOPConfigurationVillages.init(BOPConfiguration.villagesConfigFile);
        BOPConfigurationStrongholds.init(BOPConfiguration.strongholdsConfigFile);
        BOPEntities.init();
        BOPVanillaCompat.init();
        BOPEventHandlers.init();
        proxy.registerRenderers();
        BOPIntegration.preInit();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        TreecapitatorIntegration.init();
        BOPDimensions.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	BOPIntegration.postInit();
        BOPBiomes.worldTypeBOP = new WorldTypeBOP();
    }
}