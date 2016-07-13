package biomesoplenty.common.eventhandler.potions;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import biomesoplenty.api.content.BOPCPotions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionPossessionEventHandler 
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.isPotionActive(BOPCPotions.possession))
		{
			EntityLivingBase entity = event.entityLiving;
			
			Random rand = entity.worldObj.rand;

			float posX = (float)entity.posX;
			float posY = (float)entity.posY;
			float posZ = (float)entity.posZ;

			float randX = 1.0f - (rand.nextFloat() * 2.0f);
			float randY = rand.nextInt(3);
			float randZ = 1.0f - (rand.nextFloat() * 2.0f);

			entity.motionX = 0.0;        
			entity.motionY = 0.0;
			entity.motionZ = 0.0;

			if (rand.nextInt(5) == 0)
			{
				if (!entity.worldObj.checkBlockCollision(entity.boundingBox.offset(randX, randY, randZ)))
					entity.setPosition(posX + randX, posY + randY, posZ + randZ);
			}

			if (entity.getActivePotionEffect(BOPCPotions.possession).getDuration() == 0)
			{
				entity.removePotionEffect(BOPCPotions.possession.id);
				return;
			}
		}
	}
}
