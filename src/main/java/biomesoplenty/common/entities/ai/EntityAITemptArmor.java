package biomesoplenty.common.entities.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityAITemptArmor extends EntityAIBase
{
	/** The entity using this AI that is tempted by the player. */
	private EntityCreature temptedEntity;
	private float speedTowardsTarget;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double field_75278_f;
	private double field_75279_g;

	/** The player that is tempting the entity that is using this AI. */
	private EntityPlayer temptingPlayer;

	/**
	 * A counter that is decremented each time the shouldExecute method is called. The shouldExecute method will always
	 * return false if delayTemptCounter is greater than 0.
	 */
	private boolean isRunning;

	/**
	 * This field saves the ID of the items that can be used to breed entities with this behaviour.
	 */
	private Item armorItem;
	private int armorMeta;

	/**
	 * Whether the entity using this AI will be scared by the tempter's sudden movement.
	 */
	private boolean scaredByPlayerMovement;
	private boolean field_75286_m;

	public EntityAITemptArmor(EntityCreature entityCreature, float speedTowardsTarget, Item armorItem, int armorMeta, boolean scaredByPlayerMovement)
	{
		temptedEntity = entityCreature;
		this.speedTowardsTarget = speedTowardsTarget;
		this.armorItem = armorItem;
		this.armorMeta = armorMeta;
		this.scaredByPlayerMovement = scaredByPlayerMovement;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		temptingPlayer = temptedEntity.worldObj.getClosestPlayerToEntity(temptedEntity, 10.0D);

		if (temptingPlayer == null)
			return false;
		else
		{
			ItemStack itemstack = temptingPlayer.inventory.armorInventory[3];
			return !(itemstack == null) && ((itemstack.getItem() == armorItem) && (itemstack.getItemDamage() >= armorMeta));
		}
	}

	@Override
	public boolean continueExecuting()
	{
		if (scaredByPlayerMovement)
		{
			if (temptedEntity.getDistanceSqToEntity(temptingPlayer) < 36.0D)
			{
				if (temptingPlayer.getDistanceSq(targetX, targetY, targetZ) > 0.010000000000000002D || Math.abs(temptingPlayer.rotationPitch - field_75278_f) > 5.0D || Math.abs(temptingPlayer.rotationYaw - field_75279_g) > 5.0D)
					return false;
			}
			else
			{
				targetX = temptingPlayer.posX;
				targetY = temptingPlayer.posY;
				targetZ = temptingPlayer.posZ;
			}

			field_75278_f = temptingPlayer.rotationPitch;
			field_75279_g = temptingPlayer.rotationYaw;
		}

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{
		targetX = temptingPlayer.posX;
		targetY = temptingPlayer.posY;
		targetZ = temptingPlayer.posZ;
		isRunning = true;
		field_75286_m = temptedEntity.getNavigator().getAvoidsWater();
		temptedEntity.getNavigator().setAvoidsWater(false);
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		temptingPlayer = null;
		temptedEntity.getNavigator().clearPathEntity();
		isRunning = false;
		temptedEntity.getNavigator().setAvoidsWater(field_75286_m);
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		temptedEntity.getLookHelper().setLookPositionWithEntity(temptingPlayer, 30.0F, temptedEntity.getVerticalFaceSpeed());

		if (temptedEntity.getDistanceSqToEntity(temptingPlayer) < 6.25D)
		{
			temptedEntity.getNavigator().clearPathEntity();
		}
		else
		{
			temptedEntity.getNavigator().tryMoveToEntityLiving(temptingPlayer, speedTowardsTarget);
		}
	}

	public boolean isRunning()
	{
		return isRunning;
	}
}
