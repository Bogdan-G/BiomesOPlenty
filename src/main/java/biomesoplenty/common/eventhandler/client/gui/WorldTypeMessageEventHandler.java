package biomesoplenty.common.eventhandler.client.gui;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.client.gui.WorldTypeMessageGUI;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldTypeMessageEventHandler
{
    public static WorldTypeMessageEventHandler instance = new WorldTypeMessageEventHandler();
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void openCreateWorld(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiCreateWorld)
        {
            File nameHashFile = new File(Minecraft.getMinecraft().mcDataDir.getPath() + File.separator + "BOPChecks".hashCode());
            String nameHash = String.valueOf(Minecraft.getMinecraft().getSession().func_148256_e().hashCode());
            
            try
            {
                if (!nameHashFile.exists() || !FileUtils.readFileToString(nameHashFile).contains(nameHash + "StartupWarning".hashCode()))
                {
                    event.gui = new WorldTypeMessageGUI(event.gui, nameHashFile, nameHash);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            MinecraftForge.EVENT_BUS.unregister(instance);
        }
    }
}
