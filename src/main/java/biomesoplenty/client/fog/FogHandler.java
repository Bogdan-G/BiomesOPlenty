package biomesoplenty.client.fog;

import biomesoplenty.common.configuration.BOPConfigurationMisc;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.common.ForgeModContainer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FogHandler 
{
	private static double ppX0, ppY0, ppZ0 = 0;
	private static int ppXe0, ppYe0, ppZe0 = 0;
	
	@SubscribeEvent
	public void onGetFogColour(FogColors event)
	{
		if (!BOPConfigurationMisc.fogColors)
		{
			return;
		}

		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			World world = player.worldObj;

			//int x = MathHelper.floor_double(player.posX);
			//int y = MathHelper.floor_double(player.posY);
			//int z = MathHelper.floor_double(player.posZ);
			if (((ppX0 - player.posX) < 2.5D) || (ppX0 != player.posX)) {ppX0 = player.posX; ppXe0 = MathHelper.floor_double(ppX0);}
			if (((ppY0 - player.posY) < 2.5D) || (ppY0 != player.posY)) {ppY0 = player.posY; ppYe0 = MathHelper.floor_double(ppY0);}
			if (((ppZ0 - player.posZ) < 2.5D) || (ppZ0 != player.posZ)) {ppZ0 = player.posZ; ppZe0 = MathHelper.floor_double(ppZ0);}

			Block blockAtEyes = ActiveRenderInfo.getBlockAtEntityViewpoint(world, event.entity, (float)event.renderPartialTicks);
			if (blockAtEyes.getMaterial() == Material.lava)
			{
				return;
			}

			Vec3 mixedColor;
			if (blockAtEyes.getMaterial() == Material.water)
			{
				mixedColor = getFogBlendColorWater(world, player, ppXe0, ppYe0, ppZe0, event.renderPartialTicks);
			}
			else
			{
				mixedColor = getFogBlendColour(world, player, ppXe0, ppYe0, ppZe0, event.red, event.green, event.blue, event.renderPartialTicks);
			}

			event.red = (float)mixedColor.xCoord;
			event.green = (float)mixedColor.yCoord;
			event.blue = (float)mixedColor.zCoord;
		}
	}

    private static double fogX, fogZ;

    private static boolean fogInit;
    private static float fogFarPlaneDistance;
    private static double ppX1, ppY1, ppZ1 = 0;
    private static int ppXe1, ppYe1, ppZe1 = 0;
	
	@SubscribeEvent
	public void onRenderFog(EntityViewRenderEvent.RenderFogEvent event)
	{
		Entity entity = event.entity;
        World world = entity.worldObj;
        
        //int playerX = MathHelper.floor_double(entity.posX);
        //int playerY = MathHelper.floor_double(entity.posY);
        //int playerZ = MathHelper.floor_double(entity.posZ);
        if (((ppX1 - entity.posX) < 2.5D) || (ppX1 != entity.posX)) {ppX1 = entity.posX; ppXe1 = MathHelper.floor_double(ppX1);}
        if (((ppY1 - entity.posY) < 2.5D) || (ppY1 != entity.posY)) {ppY1 = entity.posY; ppYe1 = MathHelper.floor_double(ppY1);}
        if (((ppZ1 - entity.posZ) < 2.5D) || (ppZ1 != entity.posZ)) {ppZ1 = entity.posZ; ppZe1 = MathHelper.floor_double(ppZ1);}
        
        if (ppXe1 == fogX && ppZe1 == fogZ && fogInit)
        {
    		renderFog(event.fogMode, fogFarPlaneDistance, 0.75f);
        	return;
        }
        
        fogInit = true;
        
        int distance = 20;
        
        float fpDistanceBiomeFog = 0F;
		float weightBiomeFog = 0;
        
		for (int x = -distance; x <= distance; ++x)
		{
			//if (x < -15 || x > 15) {
			int p_x = ppXe1 + x;
			for (int z = -distance; z <= distance; ++z)
			{
				//if (z < -15 || z > 15) {
				int p_z = ppZe1 + z;
				BiomeGenBase biome = world.getBiomeGenForCoords(p_x, p_z);
				if (biome instanceof IBiomeFog)
				{
					float distancePart = ((IBiomeFog)biome).getFogDensity(p_x, ppYe1, p_z);
					float weightPart = 1;

					if (x == -distance)
					{
						float xDiff = (float)(1 - (entity.posX - ppXe1));
						distancePart *= xDiff;
						weightPart *= xDiff;
					}
					else if (x == distance+5)
					{
						float xDiff = (float)(entity.posX - ppXe1);
						distancePart *= xDiff;
						weightPart *= xDiff;
					}

					if (z == -distance)
					{
						float zDiff = (float)(1 - (entity.posZ - ppZe1));
						distancePart *= zDiff;
						weightPart *= zDiff;
					}
					else if (z == distance)
					{
						float zDiff = (float)(entity.posZ - ppZe1);
						distancePart *= zDiff;
						weightPart *= zDiff;
					}

					fpDistanceBiomeFog += distancePart;
					weightBiomeFog += weightPart;
				}
				//z++;
				//}
			}
			//x++;
			//}
		}

		float weightMixed = 1600;//(distance * 2) * (distance * 2);
		float weightDefault = weightMixed - weightBiomeFog;

		float fpDistanceBiomeFogAvg = (weightBiomeFog == 0) ? 0 : fpDistanceBiomeFog / weightBiomeFog;

		float farPlaneDistance = (fpDistanceBiomeFog * 240 + event.farPlaneDistance * weightDefault) / weightMixed;
		float farPlaneDistanceScaleBiome = (0.1f * (1 - fpDistanceBiomeFogAvg) + 0.75f * fpDistanceBiomeFogAvg);
		float farPlaneDistanceScale = (farPlaneDistanceScaleBiome * weightBiomeFog + 0.75f * weightDefault) / weightMixed;

		fogX = entity.posX;
		fogZ = entity.posZ;
		fogFarPlaneDistance = Math.min(farPlaneDistance, event.farPlaneDistance);

		renderFog(event.fogMode, fogFarPlaneDistance, farPlaneDistanceScale);
	}
	
	private static void renderFog(int fogMode, float farPlaneDistance, float farPlaneDistanceScale)
	{
        if (fogMode < 0)
        {
            GL11.glFogf(GL11.GL_FOG_START, 0.0F);
            GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance);
        }
        else
        {
            GL11.glFogf(GL11.GL_FOG_START, farPlaneDistance * farPlaneDistanceScale);
            GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance);
        }
	}

	private static Vec3 postProcessColor (World world, EntityLivingBase player, float r, float g, float b, double renderPartialTicks)
	{
		float darkScale = (float) ((player.lastTickPosY + (player.posY - player.lastTickPosY) * renderPartialTicks) * world.provider.getVoidFogYFactor());

		if (player.isPotionActive(Potion.blindness))
		{
			int duration = player.getActivePotionEffect(Potion.blindness).getDuration();
			darkScale *= (duration < 20) ? (1 - duration / 20f) : 0;
		}

		if (darkScale < 1)
		{
			darkScale = (darkScale < 0) ? 0 : darkScale * darkScale;
			r *= darkScale;
			g *= darkScale;
			b *= darkScale;
		}

		if (player.isPotionActive(Potion.nightVision))
		{
			// Get night vision brightness, accounting for wavering at end of potion effect
			int duration = player.getActivePotionEffect(Potion.nightVision).getDuration();
			//float brightness = ((duration > 200) ? 10 : 7 + (int)(Math.sin((double)((duration - renderPartialTicks) * 0.628f)/*3.142f * 0.2f*/) *10) * 3) / 10;
			float brightness = (duration > 200) ? 1 : 0.7f + (float)Math.sin(((duration - renderPartialTicks) * 0.628f)) * 0.3f;

			// Find scale to bring r, g, or b to 1.0
			// Vanilla will actually set the colors to +Infinity if all components are 0, explaining the terrible
			// interaction between the blindness and night vision potion effects.
			//float scale = 1 / r;
			//scale = Math.min(scale, 1 / g);
			//scale = Math.min(scale, 1 / b);
			float scale_mix = (1 / Math.max(Math.max(r, g), b)) * brightness;
			float ob = 1 - brightness;

			r = r * (ob) + r * scale_mix;
			g = g * (ob) + g * scale_mix;
			b = b * (ob) + b * scale_mix;
		}

		if (Minecraft.getMinecraft().gameSettings.anaglyph)
		{
			float r_0 = r * 30;
			float aR = (r_0 + g * 59 + b * 11) / 100;
			float aG = (r_0 + g * 70) / 100;
			float aB = (r_0 + b * 70) / 100;

			r = aR;
			g = aG;
			b = aB;
		}

		return Vec3.createVectorHelper(r, g, b);
	}

	private static Vec3 getFogBlendColorWater (World world, EntityLivingBase playerEntity, int playerX, int playerY, int playerZ, double renderPartialTicks)
	{
		int distance = 2;
		float rBiomeFog = 0;
		float gBiomeFog = 0;
		float bBiomeFog = 0;

		for (int x = -distance; x <= distance; ++x)
		{
			for (int z = -distance; z <= distance; ++z)
			{
				BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);
				int waterColorMult = biome.waterColorMultiplier;

				float rPart = (waterColorMult & 0xFF0000) >> 16;
				float gPart = (waterColorMult & 0x00FF00) >> 8;
				float bPart = waterColorMult & 0x0000FF;

				if (x == -distance)
				{
					float xDiff = (float)(1 - (playerEntity.posX - playerX));
					rPart *= xDiff;
					gPart *= xDiff;
					bPart *= xDiff;
				}
				else if (x == distance)
				{
					float xDiff = (float)(playerEntity.posX - playerX);
					rPart *= xDiff;
					gPart *= xDiff;
					bPart *= xDiff;
				}

				if (z == -distance)
				{
					float zDiff = (float)(1 - (playerEntity.posZ - playerZ));
					rPart *= zDiff;
					gPart *= zDiff;
					bPart *= zDiff;
				}
				else if (z == distance)
				{
					float zDiff = (float)(playerEntity.posZ - playerZ);
					rPart *= zDiff;
					gPart *= zDiff;
					bPart *= zDiff;
				}

				rBiomeFog += rPart;
				gBiomeFog += gPart;
				bBiomeFog += bPart;
			}
		}

		rBiomeFog /= 255f;
		gBiomeFog /= 255f;
		bBiomeFog /= 255f;

		float weight = 16;//(distance * 2) * (distance * 2);
		float respirationLevel = (float)EnchantmentHelper.getRespiration(playerEntity) * 0.2F;

		float rMixed = (rBiomeFog * 0.02f + respirationLevel) / weight;
		float gMixed = (gBiomeFog * 0.02f + respirationLevel) / weight;
		float bMixed = (bBiomeFog * 0.2f + respirationLevel) / weight;

		return postProcessColor(world, playerEntity, rMixed, gMixed, bMixed, renderPartialTicks);
	}

	private static Vec3 getFogBlendColour(World world, EntityLivingBase playerEntity, int playerX, int playerY, int playerZ, float defR, float defG, float defB, double renderPartialTicks)
	{
		GameSettings settings = Minecraft.getMinecraft().gameSettings;
		int[] ranges = ForgeModContainer.blendRanges;
		int distance = 6;
		if (settings.fancyGraphics && settings.renderDistanceChunks >= 0 && settings.renderDistanceChunks < ranges.length)
		{
			distance = ranges[settings.renderDistanceChunks];
		}

		float rBiomeFog = 0;
		float gBiomeFog = 0;
		float bBiomeFog = 0;
		float weightBiomeFog = 0;

		for (int x = -distance; x <= distance; ++x)
		{
			int p_x = playerX + x;
			for (int z = -distance; z <= distance; ++z)
			{
				int p_z = playerZ + z;
				BiomeGenBase biome = world.getBiomeGenForCoords(p_x, p_z);

				if (biome instanceof IBiomeFog)
				{
					IBiomeFog biomeFog = (IBiomeFog)biome;
					int fogColour = biomeFog.getFogColour(p_x, playerY, p_z);

					float rPart = (fogColour & 0xFF0000) >> 16;
					float gPart = (fogColour & 0x00FF00) >> 8;
					float bPart = fogColour & 0x0000FF;
					float weightPart = 1;

					if (x == -distance)
					{
						float xDiff = (float)(1 - (playerEntity.posX - playerX));
						rPart *= xDiff;
						gPart *= xDiff;
						bPart *= xDiff;
						weightPart *= xDiff;
					}
					else if (x == distance)
					{
						float xDiff = (float)(playerEntity.posX - playerX);
						rPart *= xDiff;
						gPart *= xDiff;
						bPart *= xDiff;
						weightPart *= xDiff;
					}

					if (z == -distance)
					{
						float zDiff = (float)(1 - (playerEntity.posZ - playerZ));
						rPart *= zDiff;
						gPart *= zDiff;
						bPart *= zDiff;
						weightPart *= zDiff;
					}
					else if (z == distance)
					{
						float zDiff = (float)(playerEntity.posZ - playerZ);
						rPart *= zDiff;
						gPart *= zDiff;
						bPart *= zDiff;
						weightPart *= zDiff;
					}

					rBiomeFog += rPart;
					gBiomeFog += gPart;
					bBiomeFog += bPart;
					weightBiomeFog += weightPart;
				}
			}
		}

		if (weightBiomeFog == 0 || distance == 0)
		{
			return Vec3.createVectorHelper(defR, defG, defB);
		}

		rBiomeFog /= 255f;
		gBiomeFog /= 255f;
		bBiomeFog /= 255f;

		// Calculate day / night / weather scale for BiomeFog component
		float celestialAngle = world.getCelestialAngle((float)renderPartialTicks);
		float baseScale = MathHelper.clamp_float((float)Math.cos(celestialAngle * 6.283F) * 2.0F + 0.5F, 0, 1);

		float f_2 = baseScale * 0.94F + 0.06F;
		float rScale = f_2;
		float gScale = f_2;
		float bScale = baseScale * 0.91F + 0.09F;

		float rainStrength = world.getRainStrength((float)renderPartialTicks);
		if (rainStrength > 0) {
			float t_0 = 1 - rainStrength * 0.5f;
			rScale *= t_0;
			gScale *= t_0;
			bScale *= 1 - rainStrength * 0.4f;
		}

		float thunderStrength = world.getWeightedThunderStrength((float) renderPartialTicks);
		if (thunderStrength > 0) {
			float t_1 = 1 - thunderStrength * 0.5f;
			rScale *= t_1;
			gScale *= t_1;
			bScale *= t_1;
		}

		// Apply post-processing to BiomeFog component.  Default color was already processed by Vanilla.
		rBiomeFog *= rScale / weightBiomeFog;
		gBiomeFog *= gScale / weightBiomeFog;
		bBiomeFog *= bScale / weightBiomeFog;

		Vec3 processedColor = postProcessColor(world, playerEntity, rBiomeFog, gBiomeFog, bBiomeFog, renderPartialTicks);
		rBiomeFog = (float)processedColor.xCoord;
		gBiomeFog = (float)processedColor.yCoord;
		bBiomeFog = (float)processedColor.zCoord;

		// Mix default fog component with BiomeFog component
		float weightMixed = (distance * 2) * (distance * 2);
		float weightDefault = weightMixed - weightBiomeFog;

		processedColor.xCoord = (rBiomeFog * weightBiomeFog + defR * weightDefault) / weightMixed;
		processedColor.yCoord = (gBiomeFog * weightBiomeFog + defG * weightDefault) / weightMixed;
		processedColor.zCoord = (bBiomeFog * weightBiomeFog + defB * weightDefault) / weightMixed;

		return processedColor;
	}
}
